package odontomais.view;

import odontomais.model.Profissional;
import odontomais.service.ProfissionalService;
import odontomais.service.util.MensagensAlerta;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NovoProfissional extends JDialog {

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField edtNomeProfissional;
    private JTextArea edtObs;
    private JTextField edtEmail;

    private Profissional profissional;

    public NovoProfissional(Profissional p) {
        setLocationRelativeTo(null);
        setContentPane(contentPane);

        if (p != null) {
            this.profissional = p;
            completaCampos();
        } else {
            profissional = new Profissional();
        }

        buttonOK.addActionListener((ActionEvent e) -> {
            onOK();
        });

        buttonCancel.addActionListener((ActionEvent e) -> {
            onCancel();
        });

    }

    private void onOK() {
        if (completaObjeto()) {
            ProfissionalService service = new ProfissionalService();
            if (profissional.getId() > 0) {
                service.atualizar(profissional);
                MensagensAlerta.msgCadastroAtualizado(this);
                dispose();
            } else {
                if (service.salvar(profissional)) {
                    MensagensAlerta.msgCadastroOK(this);
                    dispose();
                } else {
                    MensagensAlerta.msgErroCadastro(this);
                }
            }
        }

    }

    private void completaCampos() {
        edtNomeProfissional.setText(profissional.getNome());
        edtObs.setText(profissional.getObservacao());
        edtEmail.setText(profissional.getEmail());
    }

    private boolean completaObjeto() {
        if (testaCampos()) {
            profissional.setNome(edtNomeProfissional.getText());
            profissional.setObservacao(edtObs.getText());
            profissional.setEmail(edtEmail.getText());
            return true;
        } else {
            MensagensAlerta.msgCamposObrigatorios(this);
            return false;
        }
    }

    private boolean testaCampos() {
        if (edtNomeProfissional.getText().equals("")) return false;

        if (edtEmail.getText().equals("")) return false;

        ProfissionalService service = new ProfissionalService();
        if (service.findQtdByName(edtNomeProfissional.getText()) > 0) {
            MensagensAlerta.msgCadastroExistente(this);
            return false;
        }
        return true;
    }

    private void onCancel() {
        dispose();
    }

    private void createUIComponents() {

        getRootPane().setDefaultButton(buttonOK);

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
    }
}
