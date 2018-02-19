package odontomais.view;

import odontomais.model.Agendamento;
import odontomais.model.Convenio;
import odontomais.model.Paciente;
import odontomais.model.Profissional;
import odontomais.model.especial.AgendamentoDaSemana;
import odontomais.service.AgendamentoService;
import odontomais.service.ConvenioService;
import odontomais.service.PacienteService;
import odontomais.service.ProfissionalService;
import odontomais.view.tabmod.RenAgendamento;
import odontomais.view.tabmod.TabAgendamento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.LocalTime;

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
        //itemNovoPaciente.setIcon(new ImageIcon(getClass().getResource("/image/16/adicionar.png")));
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

        Convenio convenio = new Convenio();
        convenio.setNome("teste");
        ConvenioService convenioService = new ConvenioService();
        convenioService.salvar(convenio);

        Paciente paciente = new Paciente();
        paciente.setNomeCompleto("Paulo H.");
        PacienteService pacienteService = new PacienteService();
        pacienteService.salvarPaciente(paciente);

        Profissional profissional = new Profissional();
        profissional.setNome("Profi");
        ProfissionalService profissionalService = new ProfissionalService();
        profissionalService.salvar(profissional);

        Agendamento novo = new Agendamento();
        novo.setObservacao("Horario Ocupado");
        novo.setConvenio(new Convenio());
        novo.setHoraFim(LocalTime.of(8, 45));
        novo.setHoraInicio(LocalTime.of(8, 30));
        novo.setPaciente(new Paciente());
        novo.setProfissional(new Profissional());
        novo.setStatus("oie");
        novo.setTipoAgendamento("Tipo looc");
        novo.setDataAgenda(LocalDate.now());
        AgendamentoService service = new AgendamentoService();
        if (service.salvar(novo)) {
            System.out.println("salvou");
        }

        semana = new AgendamentoDaSemana();
        updateTables();

    }

    private void updateTables() {

        //semana.getSegunda().atualizaAgendaDia();
        tabAgendaSegunda = new TabAgendamento(semana.getSegunda().getLista());
        tblSegunda.setModel(tabAgendaSegunda);
        tblSegunda.setDefaultRenderer(Object.class, new RenAgendamento(semana.getSegunda().getLista()));
        tblSegunda.getTableHeader().setReorderingAllowed(false);
        tblSegunda.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tabAgendaSegunda.fireTableDataChanged();

        //semana.getTerca().atualizaAgendaDia();
        tabAgendaTerca = new TabAgendamento(semana.getTerca().getLista());
        tblTerca.setModel(tabAgendaTerca);
        tblTerca.setDefaultRenderer(Object.class, new RenAgendamento(semana.getTerca().getLista()));
        tblTerca.getTableHeader().setReorderingAllowed(false);
        tblTerca.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tabAgendaTerca.fireTableDataChanged();

        semana.getQuarta().atualizaAgendaDia();
        tabAgendaQuarta = new TabAgendamento(semana.getQuarta().getLista());
        tblQuarta.setModel(tabAgendaQuarta);
        tblQuarta.setDefaultRenderer(Object.class, new RenAgendamento(semana.getQuarta().getLista()));
        tblQuarta.getTableHeader().setReorderingAllowed(false);
        tblQuarta.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tabAgendaQuarta.fireTableDataChanged();

        semana.getQuinta().atualizaAgendaDia();
        tabAgendaQuinta = new TabAgendamento(semana.getQuinta().getLista());
        tblQuinta.setModel(tabAgendaQuinta);
        tblQuinta.setDefaultRenderer(Object.class, new RenAgendamento(semana.getQuinta().getLista()));
        tblQuinta.getTableHeader().setReorderingAllowed(false);
        tblQuinta.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tabAgendaQuinta.fireTableDataChanged();

        semana.getSexta().atualizaAgendaDia();
        tabAgendaSexta = new TabAgendamento(semana.getSexta().getLista());
        tblSexta.setModel(tabAgendaSexta);
        tblSexta.setDefaultRenderer(Object.class, new RenAgendamento(semana.getSexta().getLista()));
        tblSexta.getTableHeader().setReorderingAllowed(false);
        tblSexta.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tabAgendaSexta.fireTableDataChanged();

        semana.getSabado().atualizaAgendaDia();
        tabAgendaSabado = new TabAgendamento(semana.getSabado().getLista());
        tblSabado.setModel(tabAgendaSabado);
        tblSabado.setDefaultRenderer(Object.class, new RenAgendamento(semana.getSabado().getLista()));
        tblSabado.getTableHeader().setReorderingAllowed(false);
        tblSabado.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tabAgendaSabado.fireTableDataChanged();

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
