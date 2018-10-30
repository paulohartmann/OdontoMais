package odontomais.view;

import odontomais.model.Agendamento;
import odontomais.model.Profissional;
import odontomais.service.AgendamentoService;
import odontomais.service.ProfissionalService;
import odontomais.view.tabmod.TabProfissional;

import javax.swing.*;
import java.util.List;

public class ListaProfissional extends JDialog {
    private JPanel contentPane;
    private JButton btnEditar;
    private JButton buttonCancel;
    private JTextField edtNomeProfissional;
    private JButton procurarButton;
    private JTable tblProfissional;

    TabProfissional tabProfissional;

    public ListaProfissional() {
        setContentPane(contentPane);

        btnEditar.addActionListener(e -> onOK());

        edtNomeProfissional.addActionListener(e -> goProcurarProfissional());

        buttonCancel.addActionListener(e -> onCancel());

        procurarButton.addActionListener(e -> goProcurarProfissional());


    }

    private void goProcurarProfissional() {
        ProfissionalService profissionalService = new ProfissionalService();
        List<Profissional> list = profissionalService.findLikeName(edtNomeProfissional.getText());
        atualizaTabela(list);
    }

    private void atualizaTabela(List<Profissional> l) {
        tabProfissional = new TabProfissional(l);
        tblProfissional.setModel(tabProfissional);
        tblProfissional.getTableHeader().setReorderingAllowed(false);
        tblProfissional.getTableHeader().setVisible(true);
        tblProfissional.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabProfissional.fireTableDataChanged();
    }


    private void onOK() {
        if (tblProfissional.getSelectedRow() >= 0) {
            Profissional p = tabProfissional.get(tblProfissional.getSelectedRow());
            goNovoProfissional(p);
        }
    }

    private void goNovoProfissional(Profissional p) {
        NovoProfissional dialog = new NovoProfissional(p);
        dialog.pack();
        dialog.setModal(false);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private void onCancel() {
        if (tblProfissional.getSelectedRow() >= 0) {
            Profissional p = tabProfissional.get(tblProfissional.getSelectedRow());
            int opc = JOptionPane.showConfirmDialog(this, "Deseja realmente remover o profissional " + p.getNome() + " ?", "Remover", JOptionPane.YES_NO_OPTION);
            if(opc == JOptionPane.YES_OPTION) {
                AgendamentoService agendamentoService = new AgendamentoService();
                List<Agendamento> listAgendamento = agendamentoService.findAgendaByProfissional(p.getId());
                agendamentoService.removeAgendamentosDoProfissional(listAgendamento);

                ProfissionalService profissionalService = new ProfissionalService();
                profissionalService.remover(p);
                goProcurarProfissional();
            }


        }
    }
}