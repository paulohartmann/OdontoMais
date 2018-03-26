package odontomais.view;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import odontomais.model.*;
import odontomais.service.*;
import odontomais.service.util.FormatadoresTexto;

import javax.swing.*;
import java.awt.event.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import odontomais.service.util.MensagensAlerta;
import org.apache.log4j.Logger;

public class NovoAgendamento extends JDialog {

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox edtComboTipoAgendamento;
    private JTextField edtNomePaciente;
    private JButton btnProcurarPaciente;
    private JTextArea edtObs;
    private JFormattedTextField edtCelular;
    private JFormattedTextField edtResidencial;
    private JComboBox edtComboConvenio;
    private JButton btnNovoConvenio;
    private JButton btnNovoTipoAgendamento;
    private DatePicker edtData;
    private TimePicker edtHoraIni;
    private TimePicker edtHoraFim;
    private JComboBox edtComboProfissional;
    private JButton liberarHorarioButton;

    private Convenio convenio;
    private Profissional profissional;
    private Paciente paciente;

    private Agendamento agendamento;

    final static Logger logger = Logger.getLogger(NovoAgendamento.class);

    public NovoAgendamento(Agendamento ag) {
        init();
        if (ag != null) {
            agendamento = ag;
            preencheTela();
        } else {
            agendamento = new Agendamento();
            edtData.setDateToToday();
        }
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener((ActionEvent e) -> {
            onOK();
        });

        buttonCancel.addActionListener((ActionEvent e) -> {
            onCancel();
        });

        liberarHorarioButton.addActionListener(e -> goLiberaHorario());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction((ActionEvent e) -> {
            onCancel();
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        btnNovoTipoAgendamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goNovoTipoAgendamento();
            }
        });

        btnNovoConvenio.addActionListener((ActionEvent e) -> {
            goNovoConvenio();
        });
        btnProcurarPaciente.addActionListener(((e) -> {
            goProcuraPaciente();
        }));


    }

    private void goLiberaHorario() {
        AgendamentoService service = new AgendamentoService();
        Agendamento ag = service.findById(agendamento.getId());
        if (ag != null) {
            service.remove(agendamento);
            dispose();
        }
    }

    private void createUIComponents() {
        edtCelular = new JFormattedTextField(FormatadoresTexto.foneCelFormat());
        edtResidencial = new JFormattedTextField(FormatadoresTexto.foneFixoFormat());

        DatePickerSettings ds = new DatePickerSettings();
        ds.setFormatForDatesCommonEra("dd/MM/yyyy");
        ds.setFormatForDatesBeforeCommonEra("dd/MM/yyyy");

        edtData = new DatePicker(ds);
        edtData.setDateToToday();

        TimePickerSettings timeSettings = new TimePickerSettings();
        timeSettings.use24HourClockFormat();
        ClinicaService service = new ClinicaService();
        Clinica c = service.find();
        timeSettings.initialTime = c.getHorarioInicio();
        timeSettings.generatePotentialMenuTimes(TimePickerSettings.TimeIncrement.FifteenMinutes, c.getHorarioInicio(), c.getHorarioFim());
        edtHoraFim = new TimePicker(timeSettings);
        edtHoraIni = new TimePicker(timeSettings);

    }

    private void init() {
        completaComboTpAgendamento();
        completaComboConvenio();
        completaProfissional();

        completaHorariosDoDia();
    }

    private void completaHorariosDoDia() {

    }

    private void completaComboTpAgendamento() {
        TipoAgendamentoService service = new TipoAgendamentoService();
        edtComboTipoAgendamento.removeAllItems();
        for (TipoAgendamento t : service.findAll()) {
            edtComboTipoAgendamento.addItem(t.getNome());
        }
    }

    private void completaComboConvenio() {
        ConvenioService service = new ConvenioService();
        edtComboConvenio.removeAllItems();
        service.getLista().forEach((c) -> {
            edtComboConvenio.addItem(c.getNome());
        });
    }

    private void completaProfissional() {
        ProfissionalService service = new ProfissionalService();
        edtComboProfissional.removeAllItems();
        for (Profissional p : service.getList()) {
            edtComboProfissional.addItem(p.getNome());
        }
    }

    private void goNovoConvenio() {
        NovoConvenio dialog = new NovoConvenio();
        dialog.pack();
        dialog.setVisible(true);
        completaComboConvenio();
    }

    private void goNovoTipoAgendamento() {
        NovoTipoAgendamento dialog = new NovoTipoAgendamento();
        dialog.pack();
        dialog.setVisible(true);
        completaComboTpAgendamento();
    }

    private void goProcuraPaciente() {
        ListaPacientes listaPacientes = new ListaPacientes();
        listaPacientes.pack();
        listaPacientes.setLocationRelativeTo(null);
        listaPacientes.setVisible(true);

        if (listaPacientes.getPaciente() != null) {
            paciente = listaPacientes.getPaciente();
            completaPaciente();
        }
    }

    private void completaPaciente() {
        edtNomePaciente.setText(paciente.getNomeCompleto());
        edtCelular.setText(paciente.getTelCel());
        edtResidencial.setText(paciente.getTelRes());
        edtComboConvenio.setSelectedItem(paciente.getConvenio().getNome());
    }

    private void onOK() {
        if (testaCampos()) {
            Agendamento agenda = preencheObjeto();
            AgendamentoService service = new AgendamentoService();
            try {
                if (agenda.getId() > 0) {
                    service.atualizar(agenda);
                } else {
                    service.salvar(agenda);
                }
                MensagensAlerta.msgCadastroOK(this);
                dispose();
            } catch (Exception ex) {
                logger.error("Erro ao salvar novo agendamento", ex.getCause());
                MensagensAlerta.msgErroCadastro(this);
            }
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private Agendamento preencheObjeto() {
        agendamento.setConvenio(convenio);
        agendamento.setDataAgenda(edtData.getDate());
        agendamento.setHoraFim(edtHoraFim.getTime());
        agendamento.setHoraInicio(edtHoraIni.getTime());
        agendamento.setObservacao(edtObs.getText());
        agendamento.setPaciente(paciente);
        agendamento.setProfissional(profissional);
        agendamento.setTipoAgendamento(Objects.requireNonNull(edtComboTipoAgendamento.getSelectedItem()).toString());
        return agendamento;
    }

    private void preencheTela() {
        AgendamentoService service = new AgendamentoService();
        Agendamento ag = service.findById(agendamento.getId());
        if (ag == null) {
            ag = agendamento;
        } else {
            paciente = agendamento.getPaciente();
            convenio = agendamento.getConvenio();
            profissional = agendamento.getProfissional();
            edtComboConvenio.setSelectedItem(agendamento.getConvenio().getNome());
            edtResidencial.setText(agendamento.getPaciente().getTelRes());
            edtCelular.setText(agendamento.getPaciente().getTelCel());
            edtNomePaciente.setText(agendamento.getPaciente().getNomeCompleto());
        }


        edtHoraIni.setTime(ag.getHoraInicio());
        edtHoraIni.setEnabled(false);
        edtHoraFim.setTime(ag.getHoraFim());
        edtHoraFim.setEnabled(false);
        edtData.setDate(agendamento.getDataAgenda());
        edtData.setEnabled(false);
        edtComboProfissional.setSelectedItem(agendamento.getProfissional().getNome());
        edtComboProfissional.setEnabled(false);
        edtComboTipoAgendamento.setSelectedItem(agendamento.getTipoAgendamento());
        edtObs.setText(agendamento.getObservacao());
    }

    private boolean testaCampos() {
        if (edtData.getDate().getDayOfWeek() == DayOfWeek.SUNDAY) {
            JOptionPane.showMessageDialog(this, "A data marcada é um domingo", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (edtHoraFim.getTime().isBefore(edtHoraIni.getTime())) {
            JOptionPane.showMessageDialog(this, "Os horários não estão compatíveis", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if (edtHoraFim.getTime().equals(edtHoraIni.getTime())) {
            JOptionPane.showMessageDialog(this, "Os horários não podem ser iguais", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if (edtData.getDate().isBefore(LocalDate.now())) {
            JOptionPane.showMessageDialog(this, "Não é possível selecionar esta data", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if (edtCelular.getText().equals("(__)_____-____") || edtResidencial.getText().equals("(__)_____-____")) {
            JOptionPane.showMessageDialog(this, "Pelo menos um telefone para contato deve ser preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        ConvenioService convenioService = new ConvenioService();
        convenio = convenioService.get((String) edtComboConvenio.getSelectedItem());
        if (convenio == null) {
            JOptionPane.showMessageDialog(this, "Nenhum convênio selecionado", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        ProfissionalService profissionalService = new ProfissionalService();
        profissional = profissionalService.findByName((String) edtComboProfissional.getSelectedItem());
        if (profissional == null) {
            JOptionPane.showMessageDialog(this, "Nenhum profissional selecionado para o agendamento", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if (paciente == null) {
            JOptionPane.showMessageDialog(this, "Nenhum paciente selecionado para o agendamento", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if (agendamento.getId() == 0) {
            AgendamentoService agendamentoService = new AgendamentoService();
            List<Agendamento> i = agendamentoService.findHorarioByHoraData(edtHoraIni.getTime(), edtHoraFim.getTime(), edtData.getDate(), profissional);
            if (i.size() > 0) {
                JOptionPane.showMessageDialog(this, "Esse horário já esta ocupado", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }


        return true;
    }

}
