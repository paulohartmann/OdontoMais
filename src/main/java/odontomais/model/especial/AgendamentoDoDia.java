/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odontomais.model.especial;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import odontomais.model.Agendamento;
import odontomais.model.Clinica;
import odontomais.model.Convenio;
import odontomais.model.Paciente;
import odontomais.model.Profissional;
import odontomais.service.AgendamentoService;
import odontomais.service.ClinicaService;

/**
 *
 * @author paulohar
 */
public class AgendamentoDoDia {

    private List<Agendamento> lista;
    private LocalTime inicio;
    private LocalTime fim;
    private LocalDate dia;
    private int intervalo;

    private AgendamentoService service;

    public AgendamentoDoDia(List<Agendamento> l) {
        lista = l;
        init();
    }

    public AgendamentoDoDia(LocalDate d) {
        dia = d;
        init();
        novaAgendaDia();
    }

    public List<Agendamento> getLista() {
        return lista;
    }

    public void setLista(List<Agendamento> lista) {
        this.lista = lista;
    }

    public LocalDate getDia() {
        return dia;
    }

    //   public void setDia(LocalDate dia) {
    //       this.dia = dia;
    //  }
    private void init() {
        service = new AgendamentoService();

        ClinicaService serviceClinica = new ClinicaService();
        //Clinica clinica = serviceClinica.find();
        inicio = LocalTime.of(8, 0); //clinica.getHorarioInicio();
        fim = LocalTime.of(21, 0);  //clinica.getHorarioFim();
        inicio.format(DateTimeFormatter.ofPattern("HH:mm"));
        fim.format(DateTimeFormatter.ofPattern("HH:mm"));
        intervalo = 15; //clinica.getIntervaloAgenda();
    }

    private void novaAgendaDia() {
        lista = new ArrayList<>();
        long elapsedMinutes = Duration.between(inicio, fim).toMinutes();
        long x = elapsedMinutes / intervalo;

        LocalTime time = inicio;
        for (long i = 0; i < x; i++) {
            Agendamento a = new Agendamento();
            a.setConvenio(new Convenio());
            a.setDataAgenda(dia);
            a.setPaciente(new Paciente());
            a.setProfissional(new Profissional());
            a.setStatus("");
            a.setTipoAgendamento("");
            a.setHoraInicio(time);
            a.setObservacao("Horário Vago");
            time = time.plusMinutes(intervalo);
            a.setHoraFim(time);
            lista.add(a);
        }
        atualizaAgendaDia();
    }

    public void atualizaAgendaDia() {
        List<Agendamento> listaDia = service.findAgendaByData(dia);
        for (Agendamento a : lista) {
            for (Agendamento o : listaDia) {
                if (a.getHoraInicio().equals(o.getHoraInicio())) {
                    completaObj(a,o);
                }
                if(a.getHoraInicio().isBefore(o.getHoraFim()) && a.getHoraFim().isAfter(o.getHoraInicio())){
                    completaObj(a,o);
                }
                if (a.getHoraFim().equals(o.getHoraFim())) {
                    completaObj(a,o);
                }
            }
        }

    }

    public Agendamento completaObj(Agendamento a, Agendamento o){
        a.setObservacao(o.getObservacao());
        a.setConvenio(o.getConvenio());
        a.setPaciente(o.getPaciente());
        a.setProfissional(o.getProfissional());
        a.setStatus(o.getStatus());
        a.setTipoAgendamento(o.getTipoAgendamento());
        return a;
    }

    public void nextWeek() {
        dia.plusDays(7);
        novaAgendaDia();
        atualizaAgendaDia();

    }

    public void previusWeek() {
        dia.minusDays(7);
        novaAgendaDia();
        atualizaAgendaDia();
    }
}
