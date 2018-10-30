package odontomais.view;

import odontomais.model.Tratamento;
import odontomais.service.TratamentoService;
import odontomais.service.util.MensagensAlerta;
import odontomais.view.tabmod.TabTratamento;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class NovoTipoTratamento extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField edtNomeTipoTratamento;
    private JTable tblTratamentos;
    private JButton removerSelecionadoButton;
    private TratamentoService serviceTratamento;

    private TabTratamento tabTratamento;

    public NovoTipoTratamento() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);
        serviceTratamento = new TratamentoService();

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        removerSelecionadoButton.addActionListener(e -> onRemoverSelecionado());

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

        atualizaTabela(serviceTratamento.getList());
    }

    private void onOK() {
        if(edtNomeTipoTratamento.getText().equals("")){
            MensagensAlerta.msgCamposObrigatorios(this);
        }else{
            if(serviceTratamento.findExisteByName(edtNomeTipoTratamento.getText()) > 0){
                MensagensAlerta.msgCadastroExistente(this);
            }
            Tratamento tratamento = new Tratamento(edtNomeTipoTratamento.getText());
            if(serviceTratamento.salvar(tratamento)){
                MensagensAlerta.msgCadastroOK(this);
                dispose();
            }else{
                MensagensAlerta.msgErroCadastro(this);
            }
        }

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void atualizaTabela(List<Tratamento> c) {
        tabTratamento = new TabTratamento(c);
        tblTratamentos.setModel(tabTratamento);
        tblTratamentos.getTableHeader().setReorderingAllowed(false);
        tblTratamentos.getTableHeader().setVisible(true);
        tblTratamentos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabTratamento.fireTableDataChanged();
    }

    private void onRemoverSelecionado(){
        if(tblTratamentos.getSelectedRowCount() > 0){
            int result = JOptionPane.showConfirmDialog(this,"Deseja realmente remover este tratamento da clínica?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION) {
                Tratamento tratamento = tabTratamento.get(tblTratamentos.getSelectedRow());
                serviceTratamento.remover(tratamento);

                atualizaTabela(serviceTratamento.getList());
            }
        }

    }

}
