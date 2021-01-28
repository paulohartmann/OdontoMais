package odontomais.view;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import odontomais.model.ContasPagar;
import odontomais.service.ContasPagarService;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NovaCobranca extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField edtPagamento;
    private DatePicker dataValidade;
    private JCheckBox checkBoxPago;
    private DatePicker dataPagamento;
    private JTextField edtValor;
    private JTextField edtParcelas;

    public NovaCobranca() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        checkBoxPago.addActionListener(e -> jaPaguei());

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void jaPaguei() {
        if (checkBoxPago.isSelected()) {
            dataPagamento.setEnabled(true);
        } else {
            dataPagamento.setEnabled(false);
        }
    }

    private void onOK() {
        if (testaCampos()) {
            int parcelas = Integer.valueOf(edtParcelas.getText());
            for (int i = 1; i <= parcelas; i++) {
                ContasPagar cp = new ContasPagar();
                if (i == 1) {
                    cp.setDataValidade(dataValidade.getDate().plusDays(1));
                } else {
                    LocalDate date = dataValidade.getDate().plusDays(1);
                    date = date.plusDays(30 * i);
                    cp.setDataValidade(date);
                }
                BigDecimal soma = BigDecimal.valueOf(new Double(edtValor.getText()));
                soma = soma.divide(BigDecimal.valueOf(new Integer(edtParcelas.getText())));
                cp.setValor(soma);
                if (parcelas > 1) {
                    cp.setDestinatario(edtPagamento.getText() + " (" + i + "/" + parcelas + ")");
                }else{
                    cp.setDestinatario(edtPagamento.getText());
                }
                if (i == 1 && checkBoxPago.isSelected()) {
                    cp.setDataPagamento(dataPagamento.getDate());
                    cp.setStatusPagamento(1);
                }

                ContasPagarService service = new ContasPagarService();
                service.salvar(cp);
            }
            dispose();
        }

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private boolean testaCampos() {
        if (edtPagamento.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Campo 'Pagamento' deve estar preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (edtValor.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Campo 'Valor' deve estar preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        try {
            BigDecimal valor = BigDecimal.valueOf(new Double(edtValor.getText()));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "O valor deve ser numérico(Ex: 220.00)", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        Integer valor = null;
        try {
            valor = Integer.valueOf(edtParcelas.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "O valor deve ser numérico(Ex: 4)", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if ((valor == null) || (valor < 1)) {
            return false;
        }
        return true;
    }

    private void createUIComponents() {
        DatePickerSettings ds = new DatePickerSettings();
        ds.setFormatForDatesCommonEra("dd/MM/yyyy");
        ds.setFormatForDatesBeforeCommonEra("dd/MM/yyyy");
        dataValidade = new DatePicker(ds);
        dataValidade.setDateToToday();

        DatePickerSettings ds2 = new DatePickerSettings();
        ds2.setFormatForDatesCommonEra("dd/MM/yyyy");
        ds2.setFormatForDatesBeforeCommonEra("dd/MM/yyyy");

        dataPagamento = new DatePicker(ds2);
        dataPagamento.setEnabled(false);
        dataPagamento.setDateToToday();
    }
}
