/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odontomais.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import odontomais.model.Agendamento;
import odontomais.persistence.AgendamentoDao;

/**
 *
 * @author paulohar
 */
public class AgendamentoService {

    AgendamentoDao dao;

    public AgendamentoService() {
        dao = new AgendamentoDao();
    }

    public int findHorarioByHoraData(LocalTime hora, LocalDate dia) {
        return dao.findHorarioByHoraData(hora, dia);
    }

    public int findHorarioByPaciente(String nome) {
        return dao.findHorarioByPaciente(nome);
    }
    
    public List<Agendamento> findAgendaByData(LocalDate d){
        return dao.findAgendaByData(d);
    }

    public boolean salvar(Agendamento a){
        if(dao.salvar(a).getId() > 0){
            return true;
        }else{
            return false;
        }
    }

    public List<Agendamento> findAgendamentosSemana() {

        List<Agendamento> diaAgendado;
        diaAgendado = new ArrayList<>();
        int min = 15;

        LocalTime time = LocalTime.of(8, 0);
        time.format(DateTimeFormatter.ofPattern("HH:mm"));
        for (int i = 0; i < 50; i++) {
            Agendamento a = new Agendamento();
            a.setHoraInicio(time);
            a.setObservacao("Horário Vago");
            time = time.plusMinutes(min);
            a.setHoraFim(time);
            diaAgendado.add(a);
        }

        time = LocalTime.of(16, 00);
        Agendamento o = new Agendamento();
        o.setObservacao("Manut. Clareamento");
        o.setHoraInicio(time);
        time = LocalTime.of(16, 30);
        o.setHoraFim(time);

        for (Agendamento a : diaAgendado) {
            if (a.getHoraInicio().equals(o.getHoraInicio())) {
                a.setObservacao(o.getObservacao());
            }
            if (a.getHoraFim().equals(o.getHoraFim())) {
                a.setObservacao(o.getObservacao());
            }
        }

        for (Agendamento a : diaAgendado) {
            System.out.println(a.getHoraInicio().toString());
            System.out.println(a.getObservacao());
            System.out.println(a.getHoraFim().toString());
            System.out.println("");
        }

        return null;
    }

}
