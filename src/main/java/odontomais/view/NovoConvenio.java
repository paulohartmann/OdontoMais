package odontomais.view;

import odontomais.model.Convenio;
import odontomais.service.ConvenioService;
import odontomais.service.util.MensagensAlerta;

import javax.swing.*;
import java.awt.event.*;

public class NovoConvenio extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField edtDescricao;
    private JTextField edtNumero;
    private ConvenioService serviceConvenio;

    public NovoConvenio() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);
        serviceConvenio = new ConvenioService();

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
        if (testaCampos()) {

            Convenio c = new Convenio();
            c.setNome(edtDescricao.getText());
            c.setNumero(edtNumero.getText());
            

            if (serviceConvenio.salvar(c)) {
                MensagensAlerta.msgCadastroOK(this);
                dispose();
            } else {
                MensagensAlerta.msgErroCadastro(this);
            }
        } else {
            MensagensAlerta.msgCamposObrigatorios(this);
        }
        //dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private boolean testaCampos() {
        if (edtDescricao.equals("")) return false;
        //TODO: testa se já existe no banco
        return serviceConvenio.findByName(edtDescricao.getText()).size() <= 0;
    }
}
