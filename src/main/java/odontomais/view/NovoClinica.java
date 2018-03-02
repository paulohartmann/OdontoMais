package odontomais.view;

import odontomais.model.Clinica;
import odontomais.service.ClinicaService;
import odontomais.service.util.DataUtil;
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
    private JFormattedTextField edtHorarioIni;
    private JFormattedTextField edtHorarioFim;
    private JFormattedTextField edtHoraIniAlmoco;
    private JFormattedTextField edtHoraFimAlmoco;

    public NovoClinica() {
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);


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
            c.setHorarioInicio(DataUtil.converteStringToTime(edtHorarioIni.getText()));
            c.setHorarioFim(DataUtil.converteStringToTime(edtHorarioFim.getText()));
            c.setHorarioInicioAlmoco(DataUtil.converteStringToTime(edtHoraIniAlmoco.getText()));
            c.setHorarioFimAlmoco(DataUtil.converteStringToTime(edtHoraFimAlmoco.getText()));
            ClinicaService service = new ClinicaService();
            if (service.salvar(c)) {
                MensagensAlerta.msgCadastroOK(this);
                dispose();
            } else {
                MensagensAlerta.msgErroCadastro(this);
            }
        } else {
            MensagensAlerta.msgCamposObrigatorios(this);
        }

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void createUIComponents() {
        edtTelComercial = new JFormattedTextField(FormatadoresTexto.foneFixoFormat());
        edtTelEmergencial = new JFormattedTextField(FormatadoresTexto.foneFixoFormat());

        edtHorarioIni = new JFormattedTextField(FormatadoresTexto.horaMinFormat());
        edtHorarioIni.setText("08:00");
        edtHorarioFim = new JFormattedTextField(FormatadoresTexto.horaMinFormat());
        edtHorarioFim.setText("21:00");

        edtHoraIniAlmoco = new JFormattedTextField(FormatadoresTexto.horaMinFormat());
        edtHoraIniAlmoco.setText("12:00");
        edtHoraFimAlmoco = new JFormattedTextField(FormatadoresTexto.horaMinFormat());
        edtHoraFimAlmoco.setText("13:30");
    }

    private boolean testaCamposTela() {
        if (edtNomeClinica.getText().equals("")) return false;
        if (edtTelComercial.getText().equals("(__)____-____")) return false;
        if(edtHorarioIni.getText().equals("__:__")) return false;
        if(edtHorarioFim.getText().equals("__:__")) return false;
        if(edtHoraFimAlmoco.getText().equals("__:__")) return false;
        if(edtHoraIniAlmoco.getText().equals("__:__")) return false;
        return true;
    }
}
