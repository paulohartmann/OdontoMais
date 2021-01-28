package odontomais.view;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.sun.codemodel.internal.JOp;
import odontomais.model.ContasPagar;
import odontomais.service.ContasPagarService;
import odontomais.service.util.DataUtil;
import odontomais.view.tabmod.TabContasPagar;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PagamentoCobranca extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable tblContasPagar;
    private DatePicker dataPagamento;
    private JButton btnRemover;

    private TabContasPagar tabContasPagar;

    public PagamentoCobranca() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());
        btnRemover.addActionListener(e -> onRemover());
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
        atualizaTableContasPagar();
    }


    private void onOK() {
        if (tblContasPagar.getSelectedRowCount() > 0) {
            ContasPagar cp = tabContasPagar.get(tblContasPagar.getSelectedRow());
            cp.setDataPagamento(dataPagamento.getDate());
            cp.setStatusPagamento(1);
            int result = JOptionPane.showConfirmDialog(this, "Confirma o pagamento de " + cp.getDestinatario() +
                    "\nna data " + DataUtil.converteDataToString(cp.getDataPagamento()) + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                ContasPagarService service = new ContasPagarService();
                service.atualizar(cp);
                dispose();
            }
        }
    }

    private void onRemover(){
        if (tblContasPagar.getSelectedRowCount() > 0) {
            ContasPagar cp = tabContasPagar.get(tblContasPagar.getSelectedRow());
            int result = JOptionPane.showConfirmDialog(this, "Deseja realmente remover a cobrança de "+ cp.getDestinatario() + "" +
                    "\nna data de vencimento " + DataUtil.converteDataToString(cp.getDataValidade()) + " no valor de R$"+ cp.getValor().toString());
            if(result == JOptionPane.YES_OPTION){
                new ContasPagarService().remover(cp);
                atualizaTableContasPagar();
            }
        }

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void atualizaTableContasPagar() {
        ContasPagarService service = new ContasPagarService();
        tabContasPagar = new TabContasPagar(service.getListVencimento(10));
        tblContasPagar.setModel(tabContasPagar);
        tblContasPagar.getTableHeader().setReorderingAllowed(false);
        tblContasPagar.getTableHeader().setVisible(true);
        tblContasPagar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabContasPagar.fireTableDataChanged();
    }

    private void createUIComponents() {
        DatePickerSettings ds2 = new DatePickerSettings();
        ds2.setFormatForDatesCommonEra("dd/MM/yyyy");
        ds2.setFormatForDatesBeforeCommonEra("dd/MM/yyyy");

        dataPagamento = new DatePicker(ds2);
        dataPagamento.setDateToToday();
    }
}
