/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odontomais.view.main;

import java.awt.GraphicsEnvironment;
import java.util.List;

import odontomais.model.Clinica;
import odontomais.model.Profissional;
import odontomais.service.AgendamentoService;
import odontomais.service.ClinicaService;
import odontomais.service.ProfissionalService;
import odontomais.view.NovoClinica;
import odontomais.view.NovoProfissional;
import odontomais.view.Principal;

import javax.swing.*;

/**
 * @author paulohar
 */
public class MainOdontoMais {

    public static void main(String[] args) {

        ClinicaService clinicaService = new ClinicaService();
        Clinica c;
        c = clinicaService.find();
        if (c == null) {
            NovoClinica novo = new NovoClinica();
            novo.pack();
            novo.setVisible(true);
            //c = clinicaService.find();
        }

        ProfissionalService profissionalService = new ProfissionalService();
        List<Profissional> profissionalList = profissionalService.findAll();
        if (profissionalList.size() <= 0) {
            NovoProfissional novo = new NovoProfissional(null);
            novo.pack();
            novo.setVisible(true);
            //profissionalList = profissionalService.findAll();
        }


        Principal dialog = new Principal();
        dialog.pack();
        dialog.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

}
