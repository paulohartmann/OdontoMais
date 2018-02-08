package odontomais.view;

import odontomais.model.TipoAgendamento;
import odontomais.service.TipoAgendamentoService;
import odontomais.service.util.MensagensAlerta;

import javax.swing.*;
import java.awt.event.*;

public class NovoTipoAgendamento extends JDialog {

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField edtNomeTipoAgendamento;
    private TipoAgendamentoService serviceTipoAgendamento;

    public NovoTipoAgendamento() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        serviceTipoAgendamento = new TipoAgendamentoService();

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

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

    private void onOK() {
        if (edtNomeTipoAgendamento.getText().equals("")) {
            MensagensAlerta.msgCamposObrigatorios(this);
        } else {
            if (serviceTipoAgendamento.findExisteByName(edtNomeTipoAgendamento.getText()) > 0) {
                MensagensAlerta.msgCadastroExistente(this);
            }
            TipoAgendamento tipoAgend = new TipoAgendamento(edtNomeTipoAgendamento.getText());
            if (serviceTipoAgendamento.salvar(tipoAgend)) {
                MensagensAlerta.msgCadastroOK(this);
                dispose();
            } else {
                MensagensAlerta.msgErroCadastro(this);
            }
            //dispose();
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
