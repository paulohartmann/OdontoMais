package odontomais.view;

import odontomais.model.Paciente;
import odontomais.service.PacienteService;
import odontomais.service.util.FormatadoresTexto;
import odontomais.service.util.MensagensAlerta;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
 * Author: phlab
 * Date: 04/12/17
 */
public class NovoPaciente extends JDialog {

    private JButton btnSalvar;
    private JButton btnSalvarEContinuar;
    private JFormattedTextField edtCelular;
    private JFormattedTextField edtResidencial;
    private JFormattedTextField edtTrabalho;
    private JFormattedTextField edtEndereco;
    private JFormattedTextField edtBairro;
    private JFormattedTextField edtCidade;
    private JFormattedTextField edtEstado;
    private JFormattedTextField edtNome;
    private JRadioButton selectSexoF;
    private JRadioButton selectSexoM;
    private JFormattedTextField edtDataNascimento;
    private JFormattedTextField edtRG;
    private JFormattedTextField edtCPF;
    private JFormattedTextField edtEmail;
    private JPanel contentPane;

    private Paciente pacienteAtual;

    public NovoPaciente(Paciente paciente) {

        if (paciente != null) {
            pacienteAtual = paciente;
            completaTela();
        } else {
            pacienteAtual = new Paciente();
        }

        setContentPane(contentPane);
        setModal(true);

        btnSalvar.addActionListener(e -> salvar());

        btnSalvarEContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarEContinuar();
            }
        });
    }

    private void salvar() {
        if (testaCampos()) {
            completaObjeto();
            PacienteService service = new PacienteService();
            if (service.salvarPaciente(pacienteAtual)) {
                MensagensAlerta.msgCadastroOK(this);

            } else {
                MensagensAlerta.msgErroCadastro(this);
            }
        } else {
            MensagensAlerta.msgCamposObrigatorios(this);
        }
    }

    private void salvarEContinuar() {
        salvar();
        NovoPacienteComplementar novo = new NovoPacienteComplementar(pacienteAtual);
        novo.pack();
        novo.setVisible(true);
        dispose();

    }

    private void completaTela() {
        edtBairro.setText(pacienteAtual.getBairro());
        edtCelular.setText(pacienteAtual.getTelCel());
        edtCidade.setText(pacienteAtual.getCidade());
        edtCPF.setText(pacienteAtual.getCpf());
        edtDataNascimento.setText(pacienteAtual.getDataNascimento().toString());
        edtEmail.setText(pacienteAtual.getEmail());
        edtEndereco.setText(pacienteAtual.getEndResidencial());
        edtEstado.setText(pacienteAtual.getEstado());
        edtNome.setText(pacienteAtual.getNomeCompleto());
        edtResidencial.setText(pacienteAtual.getTelRes());
        edtRG.setText(pacienteAtual.getRg());
        edtTrabalho.setText(pacienteAtual.getTelTrab());
        if (pacienteAtual.getSexo().equals("M")) {
            selectSexoM.setSelected(true);
            selectSexoF.setSelected(false);
        } else {
            selectSexoM.setSelected(false);
            selectSexoF.setSelected(true);
        }
    }

    private void completaObjeto() {
        pacienteAtual.setBairro(edtBairro.getText());
        pacienteAtual.setTelCel(edtCelular.getText());
        pacienteAtual.setCidade(edtCidade.getText());
        pacienteAtual.setCpf(edtCPF.getText());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        pacienteAtual.setDataNascimento(LocalDate.parse(edtDataNascimento.getText(), dtf));
        pacienteAtual.setEmail(edtEmail.getText());
        pacienteAtual.setEndResidencial(edtEndereco.getText());
        pacienteAtual.setEstado(edtEstado.getText());
        pacienteAtual.setNomeCompleto(edtNome.getText());
        pacienteAtual.setTelRes(edtResidencial.getText());
        pacienteAtual.setRg(edtRG.getText());
        pacienteAtual.setTelTrab(edtTrabalho.getText());
        if (selectSexoF.isSelected()) {
            pacienteAtual.setSexo("F");
        } else {
            pacienteAtual.setSexo("M");
        }

    }

    private boolean testaCampos() {
        if (edtRG.getText().equals("")) {
            return false;
        }
        if (edtCPF.getText().equals("")) {
            return false;
        }
        if (edtDataNascimento.getText().equals("")) {
            return false;
        }
        if (edtNome.getText().equals("")) {
            return false;
        }
        if (edtCelular.getText().equals("")) {
            return false;
        }
        PacienteService service = new PacienteService();
        if (service.findExisteByCPF(edtCPF.getText()) > 0) {
            MensagensAlerta.msgCadastroExistente(this);
            return false;
        }
        if (service.findExisteByRG(edtRG.getText()) > 0) {
            MensagensAlerta.msgCadastroExistente(this);
            return false;
        }

        return true;
    }

    private void createUIComponents() {
        edtCPF = new JFormattedTextField(FormatadoresTexto.cpfFormat());
        edtDataNascimento = new JFormattedTextField(FormatadoresTexto.dataFormat());
        edtCelular = new JFormattedTextField(FormatadoresTexto.foneCelFormat());
        edtResidencial = new JFormattedTextField(FormatadoresTexto.foneFixoFormat());
        edtRG = new JFormattedTextField(FormatadoresTexto.rgFormat());
        edtTrabalho = new JFormattedTextField(FormatadoresTexto.foneFixoFormat());
    }
}
