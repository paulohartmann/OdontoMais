package odontomais.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Author: phlab
 * Date: 06/12/17
 */
public class Principal extends JFrame{
    private JPanel contentPane;
    private JMenuBar menuBar1;
    private JTable tblSegunda;
    private JTable tblTerca;
    private JTable tblQuarta;
    private JTable tblQuinta;
    private JTable tblSexta;
    private JTable tblSabado;
    private JButton novoAgendamentoButton;

    public Principal(){

        setContentPane(contentPane);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("OdontoMais");


    }

    public static void main(String[] args) {
        Principal dialog = new Principal();
        dialog.pack();
        dialog.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
        dialog.setVisible(true);

    }
    private void completeGUI(){

    }


    private void createUIComponents() {

        menuBar1 = new JMenuBar();
        menuBar1.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
        JMenu menuFile = new JMenu("File");
        JMenu menuPaciente = new JMenu("Paciente");
        JMenu menuConvenio = new JMenu("Convênio");
        JMenu menuProfissional = new JMenu("Profissional");
        JMenu menuConfig = new JMenu("Configurações");
        JMenu menuFinancas = new JMenu("Finanças");
        JMenu menuRelat = new JMenu("Relatórios");
        menuBar1.add(menuFile);
        menuBar1.add(menuPaciente);
        menuBar1.add(menuConvenio);
        menuBar1.add(menuProfissional);
        menuBar1.add(menuConfig);
        menuBar1.add(menuFinancas);
        menuBar1.add(menuRelat);
        //setJMenuBar(menuBar1);
    }
}
