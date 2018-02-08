package odontomais.view;

import odontomais.model.Profissional;
import odontomais.service.ProfissionalService;
import odontomais.service.util.FormatadoresTexto;
import odontomais.service.util.MensagensAlerta;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NovoProfissional extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField edtNomeProfissional;
    private JTextArea edtObs;
    private JFormattedTextField edtIniExpe;
    private JFormattedTextField edtIniAlmoco;
    private JFormattedTextField edtFimExpe;
    private JFormattedTextField edtFimAlmoco;
    private JCheckBox DOMCheckBox;
    private JCheckBox SEGCheckBox;
    private JCheckBox TERCheckBox;
    private JCheckBox QUACheckBox;
    private JCheckBox QUICheckBox;
    private JCheckBox SEXCheckBox;
    private JCheckBox SABCheckBox;

    private Profissional profissional;
    private boolean[] diasSemana;

    public NovoProfissional(Profissional p) {
        if (p != null) {
            this.profissional = p;
            completaCampos();
        }
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
        if(completaObjeto()){
            ProfissionalService service = new ProfissionalService();
            if(service.salvar(profissional)){
                MensagensAlerta.msgCadastroOK(this);
            }else{
                MensagensAlerta.msgErroCadastro(this);
            }
        }
        dispose();
    }

    private void completaCampos(){
        edtNomeProfissional.setText(profissional.getNome());
        edtIniExpe.setText(FormatadoresTexto.horaToString(profissional.getHorarioInicio()));
        edtFimExpe.setText(FormatadoresTexto.horaToString(profissional.getHorarioFim()));
        edtIniAlmoco.setText(FormatadoresTexto.horaToString(profissional.getHorarioAlmocoInicio()));
        edtFimAlmoco.setText(FormatadoresTexto.horaToString(profissional.getHorarioAlmocoFim()));
        diasSemana = new boolean[7];
        diasSemana = profissional.montaArrayDiasServico();
        DOMCheckBox.setSelected(diasSemana[0]);
        SEGCheckBox.setSelected(diasSemana[1]);
        TERCheckBox.setSelected(diasSemana[2]);
        QUACheckBox.setSelected(diasSemana[3]);
        QUICheckBox.setSelected(diasSemana[4]);
        SEXCheckBox.setSelected(diasSemana[5]);
        SABCheckBox.setSelected(diasSemana[6]);
        edtObs.setText(profissional.getObservacao());
    }

    private boolean completaObjeto() {
        if (testaCampos()) {
            if (profissional == null) {
                profissional = new Profissional();
            }
            profissional.setHorarioAlmocoFim(getHora(edtFimAlmoco.getText()));
            profissional.setHorarioAlmocoInicio(getHora(edtIniAlmoco.getText()));
            profissional.setHorarioFim(getHora(edtFimExpe.getText()));
            profissional.setHorarioInicio(getHora(edtIniExpe.getText()));
            profissional.setNome(edtNomeProfissional.getText());
            diasSemana = new boolean[7];
            diasSemana[0] = DOMCheckBox.isSelected();
            diasSemana[1] = SEGCheckBox.isSelected();
            diasSemana[2] = TERCheckBox.isSelected();
            diasSemana[3] = QUACheckBox.isSelected();
            diasSemana[4] = QUICheckBox.isSelected();
            diasSemana[5] = SEXCheckBox.isSelected();
            diasSemana[6] = SABCheckBox.isSelected();
            profissional.setObservacao(edtObs.getText());
            return true;
        }else{
            MensagensAlerta.msgCamposObrigatorios(this);
            return false;
        }
    }

    private LocalDate getHora(String hora) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            return LocalDate.parse(hora, formatter);
        } catch (Exception ex) {
            //erro ao converter
        }
        return null;
    }

    private boolean testaCampos() {
        if (edtNomeProfissional.getText().equals("")) return false;
        if (edtFimAlmoco.getText().equals("")) return false;
        if (edtFimExpe.getText().equals("")) return false;
        if (edtIniAlmoco.getText().equals("")) return false;
        if (edtIniExpe.getText().equals("")) return false;
        ProfissionalService service = new ProfissionalService();
        if(service.findExisteByName(edtNomeProfissional.getText()) > 0){
            MensagensAlerta.msgCadastroExistente(this);
            return false;
        }
        return true;
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
