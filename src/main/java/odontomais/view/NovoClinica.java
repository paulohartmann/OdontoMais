package odontomais.view;

import odontomais.model.Clinica;
import odontomais.service.ClinicaService;
import odontomais.service.util.FormatadoresTexto;
import odontomais.service.util.MensagensAlerta;

import javax.swing.*;
import java.awt.event.*;

public class NovoClinica extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField edtNomeClinica;
    private JButton procurarButton;
    private JTextField edtEndereco;
    private JFormattedTextField edtTelComercial;
    private JFormattedTextField edtTelEmergencial;
    private JTextField edtBairro;
    private JTextField edtCidade;

    public NovoClinica() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);


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
        if (testaCamposTela()) {
            Clinica c = new Clinica();
            c.setBairro(edtBairro.getText());
            c.setCidade(edtCidade.getText());
            c.setEndereco(edtEndereco.getText());
            c.setNome(edtNomeClinica.getText());
            c.setTelComercial(edtTelComercial.getText());
            c.setTelEmergencial(edtTelEmergencial.getText());
            ClinicaService service = new ClinicaService();
            if (service.salvar(c)) {
                MensagensAlerta.msgCadastroOK(this);
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

    private void createUIComponents() {
        edtTelComercial = new JFormattedTextField(FormatadoresTexto.foneFixoFormat());
        edtTelEmergencial = new JFormattedTextField(FormatadoresTexto.foneFixoFormat());
    }

    private boolean testaCamposTela() {
        if (edtNomeClinica.getText().equals("")) return false;
        if (edtTelComercial.getText().equals("")) return false;

        return true;
    }
}
