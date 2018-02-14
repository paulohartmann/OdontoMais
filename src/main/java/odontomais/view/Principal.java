package odontomais.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*
 * Author: phlab
 * Date: 06/12/17
 */
public class Principal extends JFrame {

    private JPanel contentPane;
    private JMenuBar menuBar1;
    private JTable tblSegunda;
    private JTable tblTerca;
    private JTable tblQuarta;
    private JTable tblQuinta;
    private JTable tblSexta;
    private JTable tblSabado;
    private JButton novoAgendamentoButton;

    public Principal() {

        setContentPane(contentPane);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("OdontoMais");
        
        novoAgendamentoButton.addActionListener(e -> goNovoAgendamento());

    }

    private void completeGUI() {

    }

    private void createUIComponents() {

        addWindowListener(new WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        menuBar1 = new JMenuBar();
        menuBar1.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());

        JMenu menuPaciente = new JMenu("Paciente");

        JMenuItem itemNovoPaciente = new JMenuItem("Novo Paciente");
        itemNovoPaciente.setIcon(new ImageIcon(getClass().getResource("/image/16/adicionar.png")));
        itemNovoPaciente.addActionListener(e -> goNovoPaciente());
        menuPaciente.add(itemNovoPaciente);

        JMenuItem itemProcurarPaciente = new JMenuItem("Procurar Pacientes");
        itemProcurarPaciente.setIcon(new ImageIcon(getClass().getResource("/image/16/procurar.png")));
        itemProcurarPaciente.addActionListener(e -> goProcurarPaciente());
        menuPaciente.add(itemProcurarPaciente);
        menuBar1.add(menuPaciente);

        JMenu menuConvenio = new JMenu("Convênio");

        JMenuItem itemNovoConvenio = new JMenuItem("Novo Convênio");
        itemNovoConvenio.setIcon(new ImageIcon(getClass().getResource("/image/16/adicionar.png")));
        itemNovoConvenio.addActionListener(e -> goNovoConvenio());
        menuConvenio.add(itemNovoConvenio);

        JMenuItem itemProcurarConvenio = new JMenuItem("Procurar Convênios");
        itemProcurarConvenio.setIcon(new ImageIcon(getClass().getResource("/image/16/procurar.png")));
        itemProcurarConvenio.addActionListener(e -> goProcurarConvenio());
        menuConvenio.add(itemProcurarConvenio);
        menuBar1.add(menuConvenio);

        JMenu menuProfissional = new JMenu("Profissional");

        JMenuItem itemNovoProfissional = new JMenuItem("Novo Profissional");
        itemNovoProfissional.setIcon(new ImageIcon(getClass().getResource("/image/16/adicionar.png")));
        itemNovoProfissional.addActionListener(e -> goNovoProfissional());
        menuProfissional.add(itemNovoProfissional);

        JMenuItem itemProcurarProfissional = new JMenuItem("Procurar Profissionais");
        itemProcurarProfissional.setIcon(new ImageIcon(getClass().getResource("/image/16/procurar.png")));
        itemProcurarProfissional.addActionListener(e -> goProcurarProfissional());
        menuProfissional.add(itemProcurarProfissional);
        menuBar1.add(menuProfissional);

        JMenu menuConfig = new JMenu("Configurações");

        JMenuItem itemTipoAgendamento = new JMenuItem("Config Tp. Agendamento");
        itemTipoAgendamento.setIcon(new ImageIcon(getClass().getResource("/image/16/configuracao.png")));
        itemTipoAgendamento.addActionListener(e -> goConfigTpAgendamento());
        menuConfig.add(itemTipoAgendamento);

        JMenuItem itemEditaClinica = new JMenuItem("Config Clínica");
        itemEditaClinica.setIcon(new ImageIcon(getClass().getResource("/image/16/configuracao.png")));
        itemEditaClinica.addActionListener(e -> goConfigClinica());
        menuConfig.add(itemEditaClinica);
        menuBar1.add(menuConfig);

        JMenu menuFinancas = new JMenu("Finanças");
        JMenu menuRelat = new JMenu("Relatórios");
        menuBar1.add(menuFinancas);
        menuBar1.add(menuRelat);
    }

    private void formWindowOpened(WindowEvent evt) {

    }

    private void goNovoPaciente() {
        NovoPaciente dialog = new NovoPaciente(null);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void goProcurarPaciente() {

    }

    private void goNovoConvenio() {
        NovoConvenio dialog = new NovoConvenio();
        dialog.pack();
        dialog.setVisible(true);
    }

    private void goProcurarConvenio() {

    }

    private void goNovoProfissional() {
        NovoProfissional dialog = new NovoProfissional(null);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void goProcurarProfissional() {

    }

    private void goConfigClinica() {
        NovoClinica dialog = new NovoClinica();
        dialog.pack();
        dialog.setVisible(true);
    }

    private void goConfigTpAgendamento() {
        NovoTipoAgendamento dialog = new NovoTipoAgendamento();
        dialog.pack();
        dialog.setVisible(true);
    }

    private void goNovoAgendamento() {
        NovoAgendamento dialog = new NovoAgendamento();
        dialog.pack();
        dialog.setVisible(true);
    }
}
