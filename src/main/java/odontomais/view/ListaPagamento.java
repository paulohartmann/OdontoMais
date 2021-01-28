package odontomais.view;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import odontomais.model.Paciente;
import odontomais.model.Pagamento;
import odontomais.model.Profissional;
import odontomais.service.PacienteService;
import odontomais.service.PagamentoService;
import odontomais.service.ProfissionalService;
import odontomais.service.util.DataUtil;
import odontomais.view.tabmod.TabPagamento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.util.List;

public class ListaPagamento extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField edtPaciente;
    private JButton procurarButton;
    private DatePicker dataIni;
    private DatePicker dataFim;

    private JTable tblPagamento;
    private JCheckBox checkBoxAllData;
    private JComboBox comboBoxProfissional;
    private JLabel lblPagamento;
    private JLabel lblDebito;
    private JLabel lblFiltros;
    private JLabel lblSoma;
    private JButton btnExcluir;
    private TabPagamento tabPagamento;

    private Paciente paciente;

    public ListaPagamento() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());
        btnExcluir.addActionListener(e -> excluiRegistro());

        procurarButton.addActionListener(e -> goProcuraPaciente());
        dataIni.addDateChangeListener(e -> atualizaTabela());
        dataFim.addDateChangeListener(e -> atualizaTabela());
        checkBoxAllData.addActionListener(e -> atualizaTabela());
        edtPaciente.addActionListener(e -> atualizaTabela());
        comboBoxProfissional.addItemListener(e -> atualizaTabela());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        completaProfissional();
        atualizaTabela();
    }

    private void onOK() {

        //exportar lista e infos de tela para pdf.

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void goProcuraPaciente() {
        PacienteService service = new PacienteService();
        paciente = service.goProcuraPaciente();
        completaPaciente();
        atualizaTabela();
    }

    private void completaPaciente() {
        if (paciente != null) {
            edtPaciente.setText(paciente.getNomeCompleto());
        }
    }

    private void completaProfissional() {
        ProfissionalService service = new ProfissionalService();
        comboBoxProfissional.removeAllItems();
        comboBoxProfissional.addItem(new Profissional("TODOS").getNome());
        for (Profissional p : service.getList()) {
            comboBoxProfissional.addItem(p.getNome());
        }
    }

    private void atualizaTabela() {

        List<Pagamento> list = buscaListaPagamento();

        tabPagamento = new TabPagamento(list);
        tblPagamento.setModel(tabPagamento);
        tblPagamento.getTableHeader().setReorderingAllowed(false);
        tblPagamento.getTableHeader().setVisible(true);
        tblPagamento.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabPagamento.fireTableDataChanged();
    }

    private void excluiRegistro() {
        if (tblPagamento.getSelectedRowCount() > 0) {
            Pagamento pgmt = tabPagamento.get(tblPagamento.getSelectedRow());
            String str = "";
            if (pgmt.getDebito().compareTo(new BigDecimal(0)) > 0) {
                str = "o débito de ";
            } else {
                str = "o pagamento de ";
            }
            int result = JOptionPane.showConfirmDialog(this, "Deseja realmente remover " + str + pgmt.getDebito().toString() +
                    " do paciente " + pgmt.getPaciente().getNomeCompleto(), "Atenção", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                PagamentoService service = new PagamentoService();
                service.remover(pgmt);
            }
            atualizaTabela();
        }
    }

    private List<Pagamento> buscaListaPagamento() {

        lblFiltros.setText("");
        if (paciente != null && !paciente.getNomeCompleto().equals(edtPaciente.getText())) {
            paciente = null;
        }

        String nomeProfissional = (String) comboBoxProfissional.getSelectedItem();
        Profissional profissional = null;
        if (!nomeProfissional.equals("TODOS")) {
            ProfissionalService service = new ProfissionalService();
            profissional = service.findByName(nomeProfissional);
        }

        PagamentoService service = new PagamentoService();
        List<Pagamento> list;
        if (checkBoxAllData.isSelected()) {
            lblFiltros.setText("Todo Histórico de ");
            if (paciente == null) {
                if (profissional == null) {
                    JOptionPane.showMessageDialog(this, "Histórico completo habilitado apenas por Paciente ou Profissional");
                    list = service.findFilter(dataIni.getDate(), dataFim.getDate());
                } else {
                    lblFiltros.setText(lblFiltros.getText() + profissional.getNome());
                    list = service.findFilter(profissional.getNome());
                }
            } else {
                lblFiltros.setText(lblFiltros.getText() + paciente.getNomeCompleto());
                if (profissional == null) {
                    list = service.findFilter(paciente);
                } else {
                    JOptionPane.showMessageDialog(this, "Selecione 'TODOS' no campo Profissional ou remova o nome do Paciente para concluir a pesquisa.\nEsta pesquisa será feita pelo Paciente.");
                    list = service.findFilter(paciente);
                }
            }
        } else {
            lblFiltros.setText("De " + DataUtil.converteDataToString(dataIni.getDate()) + " até " + DataUtil.converteDataToString(dataFim.getDate()) + " ");
            if (paciente == null) {
                if (profissional == null) {
                    list = service.findFilter(dataIni.getDate(), dataFim.getDate());
                } else {
                    lblFiltros.setText(lblFiltros.getText() + "de " + profissional.getNome());
                    list = service.findFilter(dataIni.getDate(), dataFim.getDate(), profissional.getNome());
                }
            } else {
                if (profissional == null) {
                    lblFiltros.setText(lblFiltros.getText() + "de " + paciente.getNomeCompleto());
                    list = service.findFilter(paciente, dataIni.getDate(), dataFim.getDate());
                } else {
                    lblFiltros.setText(lblFiltros.getText() + "de " + paciente.getNomeCompleto());
                    JOptionPane.showMessageDialog(this, "Selecione 'TODOS' no campo Profissional ou remova o nome do Paciente para concluir a pesquisa.\nEsta pesquisa será feita pelo Paciente.");
                    list = service.findFilter(paciente, dataIni.getDate(), dataFim.getDate());
                }
            }
        }
        lblFiltros.setText(lblFiltros.getText() + ":");

        BigDecimal debito = new BigDecimal(0);
        BigDecimal pagamento = new BigDecimal(0);
        for (Pagamento pg : list) {
            if (pg.getDebito().compareTo(new BigDecimal(0)) > 0) {
                debito = debito.add(pg.getDebito());
            } else {
                pagamento = pagamento.add(pg.getDebito());
            }
        }
        lblDebito.setText(debito.toString());
        lblPagamento.setText(pagamento.toString());

        BigDecimal soma = pagamento.add(debito);
        lblSoma.setText(soma.toString());
        if (soma.compareTo(new BigDecimal(0)) > 0) {
            lblSoma.setForeground(Color.RED);
        } else {
            lblSoma.setForeground(Color.GREEN);
        }
        return list;
    }

    private void createUIComponents() {
        DatePickerSettings ds = new DatePickerSettings();
        ds.setFormatForDatesCommonEra("dd/MM/yyyy");
        ds.setFormatForDatesBeforeCommonEra("dd/MM/yyyy");
        dataFim = new DatePicker(ds);
        dataFim.setDateToToday();

        DatePickerSettings ds2 = new DatePickerSettings();
        ds2.setFormatForDatesCommonEra("dd/MM/yyyy");
        ds2.setFormatForDatesBeforeCommonEra("dd/MM/yyyy");
        dataIni = new DatePicker(ds2);
        dataIni.setDate(dataFim.getDate().minusDays(30));


    }
}
