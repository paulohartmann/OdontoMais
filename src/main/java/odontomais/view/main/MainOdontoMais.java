/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odontomais.view.main;

import java.awt.GraphicsEnvironment;

import odontomais.service.AgendamentoService;
import odontomais.view.Principal;

/**
 * @author paulohar
 */
public class MainOdontoMais {

    public static void main(String[] args) {


        Principal dialog = new Principal();
        dialog.pack();
        dialog.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
        dialog.setVisible(true);
    }

}
