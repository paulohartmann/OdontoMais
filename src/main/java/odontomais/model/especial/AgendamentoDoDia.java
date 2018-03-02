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

import odontomais.model.*;
import odontomais.service.AgendamentoService;
import odontomais.service.ClinicaService;

/**
 * @author paulohar
 */
public class AgendamentoDoDia {

    private List<Agendamento> lista;
    private LocalTime inicio;
    private LocalTime fim;
    private LocalDate dia;
    private int intervalo;

    private AgendamentoService service;
    private Profissional profissional;
    private Clinica clinica;

    public AgendamentoDoDia(List<Agendamento> l, Profissional p) {
        lista = l;
        this.profissional = p;
        init();
    }

    public AgendamentoDoDia(LocalDate d, Profissional p) {
        dia = d;
        this.profissional = p;
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
        clinica = serviceClinica.find();
        inicio = clinica.getHorarioInicio();
        fim = clinica.getHorarioFim();
        inicio.format(DateTimeFormatter.ofPattern("HH:mm"));
        fim.format(DateTimeFormatter.ofPattern("HH:mm"));
        intervalo = 15; //clinica.getIntervaloAgenda();
    }

    private void novaAgendaDia() {
        lista = new ArrayList<>();
        long elapsedMinutes = Duration.between(inicio, fim).toMinutes();
        long x = elapsedMinutes / intervalo;

        LocalTime time = inicio;
        System.out.println("tam. array: " + x);
        boolean jaAlmoco = false;
        for (long i = 0; i < x; i++) {
            if (!testaAlmoco(time)) {
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
            } else {
                time = time.plusMinutes(intervalo);
            }

        }
        atualizaAgendaDia();
    }

    private boolean testaAlmoco(LocalTime time) {
        if (time.isAfter(clinica.getHorarioInicioAlmoco()) && time.isBefore(clinica.getHorarioFimAlmoco())) {
            return true;
        } else {
            return false;
        }
    }

    public void atualizaAgendaDia() {
        List<Agendamento> listaDia = service.findAgendaByDataProfissional(dia, profissional.getId());
        System.out.println(dia.toString() + listaDia.size());
        for (Agendamento a : lista) {
            for (Agendamento o : listaDia) {
                if (a.getHoraInicio().equals(o.getHoraInicio())) {
                    completaObj(a, o);
                }
                if (a.getHoraInicio().isBefore(o.getHoraFim()) && a.getHoraFim().isAfter(o.getHoraInicio())) {
                    completaObj(a, o);
                }
                if (a.getHoraFim().equals(o.getHoraFim())) {
                    completaObj(a, o);
                }
            }
        }

    }

    public Agendamento completaObj(Agendamento a, Agendamento o) {
        a.setId(o.getId());
        a.setObservacao(o.getObservacao());
        a.setConvenio(o.getConvenio());
        a.setPaciente(o.getPaciente());
        a.setProfissional(o.getProfissional());
        a.setStatus(o.getStatus());
        a.setTipoAgendamento(o.getTipoAgendamento());

        return a;
    }

    public void nextWeek(int i) {
        dia = dia.plusDays(7 * i);
        novaAgendaDia();

    }

    public void previusWeek(int i) {
        dia = dia.minusDays(7 * i);
        novaAgendaDia();
    }
}
