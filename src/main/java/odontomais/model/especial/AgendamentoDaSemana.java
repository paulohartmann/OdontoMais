/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odontomais.model.especial;

import odontomais.model.Profissional;

import java.time.LocalDate;

/**
 *
 * @author paulohar
 */
public class AgendamentoDaSemana {

    private AgendamentoDoDia segunda;
    private AgendamentoDoDia terca;
    private AgendamentoDoDia quarta;
    private AgendamentoDoDia quinta;
    private AgendamentoDoDia sexta;
    private AgendamentoDoDia sabado;

    private Profissional profissional;

    public AgendamentoDaSemana(Profissional p) {
        this.profissional = p;
        init();

    }

    private void init() {

        LocalDate hoje = LocalDate.now();
        int diaSemana = hoje.getDayOfWeek().getValue();

        switch (diaSemana) {
            case 1:
                //segunda
                setSegunda(new AgendamentoDoDia(hoje, profissional));
                setTerca(new AgendamentoDoDia(hoje.plusDays(1), profissional));
                setQuarta(new AgendamentoDoDia(hoje.plusDays(2), profissional));
                setQuinta(new AgendamentoDoDia(hoje.plusDays(3), profissional));
                setSexta(new AgendamentoDoDia(hoje.plusDays(4), profissional));
                setSabado(new AgendamentoDoDia(hoje.plusDays(5), profissional));
                break;
            case 2:
                //terca
                setSegunda(new AgendamentoDoDia(hoje.minusDays(1), profissional));
                setTerca(new AgendamentoDoDia(hoje, profissional));
                setQuarta(new AgendamentoDoDia(hoje.plusDays(1), profissional));
                setQuinta(new AgendamentoDoDia(hoje.plusDays(2), profissional));
                setSexta(new AgendamentoDoDia(hoje.plusDays(3), profissional));
                setSabado(new AgendamentoDoDia(hoje.plusDays(4), profissional));
                break;
            case 3:
                setSegunda(new AgendamentoDoDia(hoje.minusDays(2), profissional));
                setTerca(new AgendamentoDoDia(hoje.minusDays(1), profissional));
                setQuarta(new AgendamentoDoDia(hoje, profissional));
                setQuinta(new AgendamentoDoDia(hoje.plusDays(1), profissional));
                setSexta(new AgendamentoDoDia(hoje.plusDays(2), profissional));
                setSabado(new AgendamentoDoDia(hoje.plusDays(3), profissional));
                break;
            case 4:
                setSegunda(new AgendamentoDoDia(hoje.minusDays(3), profissional));
                setTerca(new AgendamentoDoDia(hoje.minusDays(2), profissional));
                setQuarta(new AgendamentoDoDia(hoje.minusDays(1), profissional));
                setQuinta(new AgendamentoDoDia(hoje, profissional));
                setSexta(new AgendamentoDoDia(hoje.plusDays(1), profissional));
                setSabado(new AgendamentoDoDia(hoje.plusDays(2), profissional));
                break;
            case 5:
                setSegunda(new AgendamentoDoDia(hoje.minusDays(4), profissional));
                setTerca(new AgendamentoDoDia(hoje.minusDays(3), profissional));
                setQuarta(new AgendamentoDoDia(hoje.minusDays(2), profissional));
                setQuinta(new AgendamentoDoDia(hoje.minusDays(1), profissional));
                setSexta(new AgendamentoDoDia(hoje, profissional));
                setSabado(new AgendamentoDoDia(hoje.plusDays(1), profissional));
                break;
            case 6:
                setSegunda(new AgendamentoDoDia(hoje.minusDays(5), profissional));
                setTerca(new AgendamentoDoDia(hoje.minusDays(4), profissional));
                setQuarta(new AgendamentoDoDia(hoje.minusDays(3), profissional));
                setQuinta(new AgendamentoDoDia(hoje.minusDays(2), profissional));
                setSexta(new AgendamentoDoDia(hoje.minusDays(1), profissional));
                setSabado(new AgendamentoDoDia(hoje, profissional));
                break;
            case 7:
                setSegunda(new AgendamentoDoDia(hoje.plusDays(1), profissional));
                setTerca(new AgendamentoDoDia(hoje.plusDays(2), profissional));
                setQuarta(new AgendamentoDoDia(hoje.plusDays(3), profissional));
                setQuinta(new AgendamentoDoDia(hoje.plusDays(4), profissional));
                setSexta(new AgendamentoDoDia(hoje.plusDays(5), profissional));
                setSabado(new AgendamentoDoDia(hoje.plusDays(6), profissional));
                break;
        }
    }

    public void nextWeek(int i) {
        getSegunda().nextWeek(i);
        getTerca().nextWeek(i);
        getQuarta().nextWeek(i);
        getQuinta().nextWeek(i);
        getSexta().nextWeek(i);
        getSabado().nextWeek(i);

    }

    public void previusWeek(int i) {
        getSegunda().previusWeek(i);
        getTerca().previusWeek(i);
        getQuarta().previusWeek(i);
        getQuinta().previusWeek(i);
        getSexta().previusWeek(i);
        getSabado().previusWeek(i);
    }


    public AgendamentoDoDia getSegunda() {
        return segunda;
    }

    public void setSegunda(AgendamentoDoDia segunda) {
        this.segunda = segunda;
    }

    public AgendamentoDoDia getTerca() {
        return terca;
    }

    public void setTerca(AgendamentoDoDia terca) {
        this.terca = terca;
    }

    public AgendamentoDoDia getQuarta() {
        return quarta;
    }

    public void setQuarta(AgendamentoDoDia quarta) {
        this.quarta = quarta;
    }

    public AgendamentoDoDia getQuinta() {
        return quinta;
    }

    public void setQuinta(AgendamentoDoDia quinta) {
        this.quinta = quinta;
    }

    public AgendamentoDoDia getSexta() {
        return sexta;
    }

    public void setSexta(AgendamentoDoDia sexta) {
        this.sexta = sexta;
    }

    public AgendamentoDoDia getSabado() {
        return sabado;
    }

    public void setSabado(AgendamentoDoDia sabado) {
        this.sabado = sabado;
    }
}
