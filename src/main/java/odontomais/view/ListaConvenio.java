package odontomais.view;

import odontomais.model.Convenio;
import odontomais.service.ConvenioService;
import odontomais.view.tabmod.TabConvenio;
import odontomais.view.tabmod.TabPaciente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListaConvenio extends JDialog {
    private JPanel contentPane;
    private JButton buttonExcluir;
    private JButton buttonNovo;
    private JTextField edtNomeConvenio;
    private JButton procurarButton;
    private JTable tblConvenio;
    private TabConvenio tabConvenio;

    public ListaConvenio() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonNovo);

        buttonExcluir.addActionListener(e -> onBloquear());

        buttonNovo.addActionListener(e -> onNovoConvenio());

        procurarButton.addActionListener(e -> onProcurar());

        edtNomeConvenio.addActionListener(e -> onProcurar());
    }

    private void onProcurar() {
        ConvenioService service = new ConvenioService();
        List<Convenio> list = service.findByName(edtNomeConvenio.getText());
        atualizaTabela(list);
    }

    private void onNovoConvenio() {
        NovoConvenio dialog = new NovoConvenio();
        dialog.pack();
        dialog.setVisible(true);
        dispose();
    }

    private void onBloquear() {
        if (tblConvenio.getSelectedRow() >= 0) {
            Convenio c = tabConvenio.get(tblConvenio.getSelectedRow());
            if (c.isSemValidade()) {
                c.setSemValidade(false);
            } else {
                c.setSemValidade(true);
            }
            ConvenioService service = new ConvenioService();
            service.atualizar(c);
        }
        onProcurar();

    }

    private void atualizaTabela(List<Convenio> c) {
        tabConvenio = new TabConvenio(c);
        tblConvenio.setModel(tabConvenio);
        tblConvenio.getTableHeader().setReorderingAllowed(false);
        tblConvenio.getTableHeader().setVisible(true);
        tblConvenio.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabConvenio.fireTableDataChanged();
    }
}
