/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odontomais.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import odontomais.model.Agendamento;
import odontomais.persistence.AgendamentoDao;

/**
 *
 * @author paulohar
 */
public class AgendamentoService {
    
    AgendamentoDao dao;
    
    public AgendamentoService(){
        dao = new AgendamentoDao();
    }
    
    public int findHorarioByHoraData(LocalTime hora, LocalDate dia) {
        return dao.findHorarioByHoraData(hora, dia);
    }
    
    public int findHorarioByPaciente(String nome) {
        return dao.findHorarioByPaciente(nome);
    }
    
    public List<Agendamento> findAgendamentosSemana(){
        LocalDate hoje = LocalDate.now();
        LocalDate fim = LocalDate.now();
        fim.plusDays(6);
        return null;
    }
    
    
    
}
