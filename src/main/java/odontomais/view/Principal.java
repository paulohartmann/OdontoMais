package odontomais.view;


import odontomais.model.Agendamento;
import odontomais.model.Clinica;
import odontomais.model.Profissional;
import odontomais.model.especial.AgendamentoDaSemana;
import odontomais.service.ClinicaService;
import odontomais.service.ProfissionalService;
import odontomais.service.util.DataUtil;
import odontomais.view.tabmod.RenAgendamento;
import odontomais.view.tabmod.TabAgendamento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

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
    private JLabel txtCabSeg;
    private JLabel txtCabTer;
    private JLabel txtCabQua;
    private JLabel txtCabQui;
    private JLabel txtCabSex;
    private JLabel txtCabSab;
    private JButton btnPrevWeek;
    private JButton btnNextWeek;
    private JComboBox jcbProfissional;
    private JLabel txtAniversariantes;
    private JLabel txtAgendFaltantes;
    private JLabel txtAgendTotal;

    AgendamentoDaSemana semana;

    private TabAgendamento tabAgendaSegunda;
    private TabAgendamento tabAgendaTerca;
    private TabAgendamento tabAgendaQuarta;
    private TabAgendamento tabAgendaQuinta;
    private TabAgendamento tabAgendaSexta;
    private TabAgendamento tabAgendaSabado;

    public Principal() {
        setContentPane(contentPane);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("OdontoMais");

        novoAgendamentoButton.addActionListener(e -> goNovoAgendamento(null));

        btnNextWeek.addActionListener(e -> goNextWeek());
        btnPrevWeek.addActionListener(e -> goPrevWeek());
        tblSegunda.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tblSegunda.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    goClickAgendamento(1);
                }
            }
        });
        tblTerca.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tblTerca.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    goClickAgendamento(2);
                }
            }
        });
        tblQuarta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tblQuarta.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    goClickAgendamento(3);
                }
            }
        });
        tblQuinta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tblQuinta.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    goClickAgendamento(4);
                }
            }
        });
        tblSexta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tblSexta.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    goClickAgendamento(5);
                }
            }
        });
        tblSabado.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tblSabado.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    goClickAgendamento(6);
                }
            }
        });

        jcbProfissional.addActionListener(e -> atualizaAgenda());

    }

    private void goClickAgendamento(int diaSemana) {
        switch (diaSemana) {
            case 1:
                goNovoAgendamento(tabAgendaSegunda.get(tblSegunda.getSelectedRow()));
                break;
            case 2:
                goNovoAgendamento(tabAgendaTerca.get(tblTerca.getSelectedRow()));
                break;
            case 3:
                goNovoAgendamento(tabAgendaQuarta.get(tblQuarta.getSelectedRow()));
                break;
            case 4:
                goNovoAgendamento(tabAgendaQuinta.get(tblQuinta.getSelectedRow()));
                break;
            case 5:
                goNovoAgendamento(tabAgendaSexta.get(tblSexta.getSelectedRow()));
                break;
            case 6:
                goNovoAgendamento(tabAgendaSabado.get(tblSabado.getSelectedRow()));
                break;
        }

    }

    private void atualizaAgenda() {

        semana = new AgendamentoDaSemana(pegaProfissionalSelecionado());
        updateTables();

    }

    private void goPrevWeek() {
        semana.previusWeek(1);
        updateTables();
    }

    private void goNextWeek() {
        semana.nextWeek(1);
        updateTables();
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
        //itemNovoPaciente.setIcon(new ImageIcon(getClass().getResource("image/16/adicionar.png")));
        itemNovoPaciente.addActionListener(e -> goNovoPaciente());
        menuPaciente.add(itemNovoPaciente);

        JMenuItem itemProcurarPaciente = new JMenuItem("Procurar Pacientes");
        //itemProcurarPaciente.setIcon(new ImageIcon(getClass().getResource("/image/16/procurar.png")));
        itemProcurarPaciente.addActionListener(e -> goProcurarPaciente());
        menuPaciente.add(itemProcurarPaciente);
        menuBar1.add(menuPaciente);

        JMenu menuConvenio = new JMenu("Convênio");

        JMenuItem itemNovoConvenio = new JMenuItem("Novo Convênio");
        //itemNovoConvenio.setIcon(new ImageIcon(getClass().getResource("/image/16/adicionar.png")));
        itemNovoConvenio.addActionListener(e -> goNovoConvenio());
        menuConvenio.add(itemNovoConvenio);

        JMenuItem itemProcurarConvenio = new JMenuItem("Procurar Convênios");
        //itemProcurarConvenio.setIcon(new ImageIcon(getClass().getResource("/image/16/procurar.png")));
        itemProcurarConvenio.addActionListener(e -> goProcurarConvenio());
        menuConvenio.add(itemProcurarConvenio);
        menuBar1.add(menuConvenio);

        JMenu menuProfissional = new JMenu("Profissional");

        JMenuItem itemNovoProfissional = new JMenuItem("Novo Profissional");
        //itemNovoProfissional.setIcon(new ImageIcon(getClass().getResource("/image/16/adicionar.png")));
        itemNovoProfissional.addActionListener(e -> goNovoProfissional());
        menuProfissional.add(itemNovoProfissional);

        JMenuItem itemProcurarProfissional = new JMenuItem("Procurar Profissionais");
        //itemProcurarProfissional.setIcon(new ImageIcon(getClass().getResource("/image/16/procurar.png")));
        itemProcurarProfissional.addActionListener(e -> goProcurarProfissional());
        menuProfissional.add(itemProcurarProfissional);
        menuBar1.add(menuProfissional);

        JMenu menuConfig = new JMenu("Configurações");

        JMenuItem itemTipoAgendamento = new JMenuItem("Config Tp. Agendamento");
        //itemTipoAgendamento.setIcon(new ImageIcon(getClass().getResource("/image/16/configuracao.png")));
        itemTipoAgendamento.addActionListener(e -> goConfigTpAgendamento());
        menuConfig.add(itemTipoAgendamento);

        JMenuItem itemEditaClinica = new JMenuItem("Config Clínica");
        //itemEditaClinica.setIcon(new ImageIcon(getClass().getResource("/image/16/configuracao.png")));
        itemEditaClinica.addActionListener(e -> goConfigClinica());
        menuConfig.add(itemEditaClinica);
        menuBar1.add(menuConfig);

        JMenu menuFinancas = new JMenu("Finanças");
        JMenu menuRelat = new JMenu("Relatórios");
        menuBar1.add(menuFinancas);
        menuBar1.add(menuRelat);
    }

    private void formWindowOpened(WindowEvent evt) {

        atualizaComboProfissional();
        atualizaAgenda();


    }

    private void atualizaComboProfissional() {
        ProfissionalService service = new ProfissionalService();
        List<Profissional> profissionalList = service.findAll();
        jcbProfissional.removeAllItems();
        for (Profissional p : profissionalList) {
            jcbProfissional.addItem(p.getNome());
        }

    }

    private Profissional pegaProfissionalSelecionado() {
        String nome = jcbProfissional.getSelectedItem().toString();
        ProfissionalService service = new ProfissionalService();
        return service.findByName(nome);
    }

    private void updateTables() {

        DefaultTableModel model = new DefaultTableModel();
        tabAgendaSegunda = new TabAgendamento(semana.getSegunda().getLista());
        tblSegunda.setModel(tabAgendaSegunda);
        tblSegunda.setDefaultRenderer(Object.class, new RenAgendamento(semana.getSegunda().getLista()));
        tblSegunda.getTableHeader().setReorderingAllowed(false);
        tblSegunda.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tblSegunda.setRowHeight(25);
        tabAgendaSegunda.fireTableDataChanged();

        tabAgendaTerca = new TabAgendamento(semana.getTerca().getLista());
        tblTerca.setModel(tabAgendaTerca);
        tblTerca.setDefaultRenderer(Object.class, new RenAgendamento(semana.getTerca().getLista()));
        tblTerca.getTableHeader().setReorderingAllowed(false);
        tblTerca.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tblTerca.setRowHeight(25);
        tabAgendaTerca.fireTableDataChanged();

        tabAgendaQuarta = new TabAgendamento(semana.getQuarta().getLista());
        tblQuarta.setModel(tabAgendaQuarta);
        tblQuarta.setDefaultRenderer(Object.class, new RenAgendamento(semana.getQuarta().getLista()));
        tblQuarta.getTableHeader().setReorderingAllowed(false);
        tblQuarta.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tblQuarta.setRowHeight(25);
        tabAgendaQuarta.fireTableDataChanged();

        tabAgendaQuinta = new TabAgendamento(semana.getQuinta().getLista());
        tblQuinta.setModel(tabAgendaQuinta);
        tblQuinta.setDefaultRenderer(Object.class, new RenAgendamento(semana.getQuinta().getLista()));
        tblQuinta.getTableHeader().setReorderingAllowed(false);
        tblQuinta.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tblQuinta.setRowHeight(25);
        tabAgendaQuinta.fireTableDataChanged();

        tabAgendaSexta = new TabAgendamento(semana.getSexta().getLista());
        tblSexta.setModel(tabAgendaSexta);
        tblSexta.setDefaultRenderer(Object.class, new RenAgendamento(semana.getSexta().getLista()));
        tblSexta.getTableHeader().setReorderingAllowed(false);
        tblSexta.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tblSexta.setRowHeight(25);
        tabAgendaSexta.fireTableDataChanged();

        tabAgendaSabado = new TabAgendamento(semana.getSabado().getLista());
        tblSabado.setModel(tabAgendaSabado);
        tblSabado.setDefaultRenderer(Object.class, new RenAgendamento(semana.getSabado().getLista()));
        tblSabado.getTableHeader().setReorderingAllowed(false);
        tblSabado.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tblSabado.setRowHeight(25);
        tabAgendaSabado.fireTableDataChanged();

        txtCabSeg.setText(DataUtil.converteDataToString(semana.getSegunda().getDia()));
        txtCabTer.setText(DataUtil.converteDataToString(semana.getTerca().getDia()));
        txtCabQua.setText(DataUtil.converteDataToString(semana.getQuarta().getDia()));
        txtCabQui.setText(DataUtil.converteDataToString(semana.getQuinta().getDia()));
        txtCabSex.setText(DataUtil.converteDataToString(semana.getSexta().getDia()));
        txtCabSab.setText(DataUtil.converteDataToString(semana.getSabado().getDia()));

    }

    private void goNovoPaciente() {
        NovoPaciente dialog = new NovoPaciente(null);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void goProcurarPaciente() {
        ListaPacientes listaPacientes = new ListaPacientes();
        listaPacientes.pack();
        listaPacientes.setLocationRelativeTo(null);
        listaPacientes.setVisible(true);
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

    private void goNovoAgendamento(Agendamento ag) {
        NovoAgendamento dialog = new NovoAgendamento(ag);
        dialog.pack();
        dialog.setVisible(true);
        atualizaAgenda();
    }
}
