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
    private JButton button1;
    private JPanel contentPane;

    public Principal(){

        setContentPane(contentPane);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NovoPaciente novo = new NovoPaciente(null);
                novo.pack();
                novo.setVisible(true);

            }
        });
    }

    public static void main(String[] args) {
        Principal dialog = new Principal();
        dialog.pack();
        dialog.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
        dialog.setVisible(true);

    }
    private void completeGUI(){

    }


}
