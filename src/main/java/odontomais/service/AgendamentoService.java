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
import odontomais.model.Paciente;
import odontomais.model.Profissional;
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

    public List<Agendamento> findHorarioByHoraData(LocalTime hora, LocalTime horaFim, LocalDate dia, Profissional p) {
        return dao.findHorarioByHoraData(hora, horaFim, dia, p);
    }

    public void remove(Agendamento a){
        dao.remover(a.getId());
    }

    public List<Agendamento> findByPaciente(Paciente p){
       return dao.findByPaciente(p);
    }

    public List<Agendamento> findAgendaByDataProfissional(LocalDate d, long id) {
        List<Agendamento> lista = dao.findAgendaByDataProfissional(d, id);
        if(lista == null){
            return new ArrayList<Agendamento>();
        }else{
            return lista;
        }
    }

    public boolean salvar(Agendamento a){
        if(dao.salvar(a).getId() > 0){
            return true;
        }else{
            return false;
        }
    }

    public void atualizar(Agendamento a){
        dao.atualizar(a);
    }
    public int findQtdAgendaDia(LocalDate dia){
        return dao.findAgendaDia(dia).size();
    }

    public Agendamento findById(long id){
        return dao.findById(id);
    }

}
