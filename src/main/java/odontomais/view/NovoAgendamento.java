package odontomais.view;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import odontomais.service.util.FormatadoresTexto;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import odontomais.model.Agendamento;
import odontomais.model.Convenio;
import odontomais.model.Paciente;
import odontomais.model.Profissional;
import odontomais.model.TipoAgendamento;
import odontomais.service.AgendamentoService;
import odontomais.service.ConvenioService;
import odontomais.service.PacienteService;
import odontomais.service.ProfissionalService;
import odontomais.service.TipoAgendamentoService;
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

    private Convenio convenio;
    private Profissional profissional;
    private Paciente paciente;

    final static Logger logger = Logger.getLogger(NovoAgendamento.class);

    public NovoAgendamento() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener((ActionEvent e) -> {
            onOK();
        });

        buttonCancel.addActionListener((ActionEvent e) -> {
            onCancel();
        });

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

        edtData.setDateToToday();
        edtHoraIni.setTimeToNow();
        edtHoraFim.setTimeToNow();

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

        init();
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
        edtHoraFim = new TimePicker(timeSettings);
        edtHoraIni = new TimePicker(timeSettings);

    }

    private void init() {
        completaComboTpAgendamento();
        completaComboConvenio();
        completaProfissional();
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
        for (Profissional p : service.findAll()) {
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
        //tela para procurar paciente, passando por parametro o getText do campo.
    }

    private void onOK() {
        if (testaCampos()) {
            Agendamento agenda = preencheObjeto();
            AgendamentoService service = new AgendamentoService();
            try {
                service.salvar(agenda);
                MensagensAlerta.msgCadastroOK(this);
            } catch (Exception ex) {
                logger.error("Erro ao salvar novo agendamento", ex.getCause());
                MensagensAlerta.msgErroCadastro(this);
            }
        }
        //dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private Agendamento preencheObjeto() {
        Agendamento agenda = new Agendamento();
        agenda.setConvenio(convenio);
        agenda.setDataAgenda(edtData.getDate());
        agenda.setHoraFim(edtHoraFim.getTime());
        agenda.setHoraInicio(edtHoraIni.getTime());
        agenda.setObservacao(edtObs.getText());
        agenda.setPaciente(paciente);
        agenda.setProfissional(profissional);
        agenda.setTipoAgendamento(edtComboTipoAgendamento.getSelectedItem().toString());
        return agenda;
    }

    private boolean testaCampos() {
        if (edtHoraFim.getTime().isBefore(edtHoraIni.getTime())) {
            JOptionPane.showMessageDialog(this, "Os horários não estão compatíveis", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if (edtData.getDate().isBefore(LocalDate.now())) {
            JOptionPane.showMessageDialog(this, "Não é possível selecionar esta data", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if (edtCelular.getText().equals("") || edtResidencial.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Pelo menos um telefone para contato deve ser preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        ConvenioService convenioService = new ConvenioService();
        convenio = convenioService.findByName(edtComboConvenio.getSelectedItem().toString());
        if (convenio == null) {
            JOptionPane.showMessageDialog(this, "Nenhum convênio selecionado", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        ProfissionalService profissionalService = new ProfissionalService();
        profissional = profissionalService.findByName(edtComboProfissional.getSelectedItem().toString());
        if (profissional == null) {
            JOptionPane.showMessageDialog(this, "Nenhum profissional selecionado para o agendamento", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        PacienteService pacienteService = new PacienteService();
        paciente = pacienteService.findFromName(edtNomePaciente.getText()).get(0);
        if (paciente == null) {
            JOptionPane.showMessageDialog(this, "Nenhum paciente selecionado para o agendamento", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        return true;
    }

}
