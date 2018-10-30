package odontomais.view;

import odontomais.model.*;
import odontomais.service.PacienteService;
import odontomais.service.PagamentoService;
import odontomais.service.ProfissionalService;
import odontomais.service.TratamentoService;
import odontomais.service.util.MensagensAlerta;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;

public class NovoPagamento extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField edtPaciente;
    private JButton btnProcurarPaciente;
    private JFormattedTextField edtDebito;
    private JComboBox comboFormaPagamento;
    private JComboBox comboTratamento;
    private JComboBox comboProfissional;
    private JLabel lblDebitoAtivo;
    private JPanel pnlDebitoAtivo;
    private JTextArea edtObs;

    private Paciente paciente;

    final static Logger logger = Logger.getLogger(NovoPagamento.class);

    public NovoPagamento() {
        init();
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        btnProcurarPaciente.addActionListener(e -> goProcuraPaciente());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    private void init() {
        completaProfissional();
        completaTratamento();
    }

    private void onOK() {
        Pagamento pagamento = completaObjeto();
        if (pagamento != null) {
            int i = 0;
            if (pagamento.getDebito().floatValue() > 0) {
                i = JOptionPane.showConfirmDialog(this, "Confirma o débito de " + pagamento.getDebito().toString() + ", para o paciente " + pagamento.getPaciente().getNomeCompleto() + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
            } else {
                i = JOptionPane.showConfirmDialog(this, "Confirma o pagamento de " + pagamento.getDebito().toString() + ", para o paciente " + pagamento.getPaciente().getNomeCompleto() + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
            }
            if (i == JOptionPane.YES_OPTION) {
                PagamentoService service = new PagamentoService();
                service.salvar(pagamento);

                PacienteService pacienteService = new PacienteService();
                pacienteService.atualizarDebito(pagamento);
                MensagensAlerta.msgPagamentoOK(this);
                dispose();
            }
        }

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void completaProfissional() {
        ProfissionalService service = new ProfissionalService();
        comboProfissional.removeAllItems();
        for (Profissional p : service.getList()) {
            comboProfissional.addItem(p.getNome());
        }
    }

    private void completaTratamento() {
        TratamentoService service = new TratamentoService();
        comboTratamento.removeAllItems();

        comboTratamento.addItem(new Tratamento("").getNome());
        for (Tratamento p : service.getList()) {
            comboTratamento.addItem(p.getNome());
        }
    }

    private void goProcuraPaciente() {
        PacienteService service = new PacienteService();
        paciente = service.goProcuraPaciente();
        completaPaciente();
    }


    private void completaPaciente() {
        if (paciente != null) {
            edtPaciente.setText(paciente.getNomeCompleto());
            lblDebitoAtivo.setText(paciente.getDebito().toString());
            if (paciente.getDebito().compareTo(new BigDecimal(0)) > 0) {
                lblDebitoAtivo.setForeground(Color.RED);
            } else {
                lblDebitoAtivo.setForeground(Color.GREEN);
            }

        }

    }

    private Pagamento completaObjeto() {
        if (!testaCampos()) {
            return null;
        }
        Pagamento pagamento = new Pagamento();
        pagamento.setDebito(new BigDecimal(edtDebito.getText()));
        FormaPagamento forma = (FormaPagamento) comboFormaPagamento.getSelectedItem();
        pagamento.setFormaPagamento(forma.name());
        pagamento.setObsTratamento(edtObs.getText());
        pagamento.setPaciente(paciente);
        pagamento.setProfissional((String) comboProfissional.getSelectedItem());
        pagamento.setTratamento((String) comboTratamento.getSelectedItem());

        return pagamento;
    }

    private boolean testaCampos() {
        BigDecimal testBigDecimal = null;
        try {
            testBigDecimal = new BigDecimal(edtDebito.getText());
        } catch (Exception ex) {
            logger.error("Erro ao converter string: " + edtDebito.getText() + " para Objeto BigDecimal", ex.getCause());
        }
        if (testBigDecimal == null) {
            JOptionPane.showMessageDialog(this, "Informação do campo Débito não é um número (ex. 40, -5, 100.00).", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (paciente == null) {
            JOptionPane.showMessageDialog(this, "Nenhum paciente foi selecionado, pesquise clicando no botão 'Procurar'.", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    private void createUIComponents() {
        comboFormaPagamento = new JComboBox(FormaPagamento.values());
    }


}
