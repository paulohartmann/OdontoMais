/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odontomais.model.especial;

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

    public AgendamentoDaSemana() {
        init();
    }

    private void init() {

        LocalDate hoje = LocalDate.now();
        int diaSemana = hoje.getDayOfWeek().getValue();

        switch (diaSemana) {
            case 1:
                //segunda
                setSegunda(new AgendamentoDoDia(hoje));
                setTerca(new AgendamentoDoDia(hoje.plusDays(1)));
                setQuarta(new AgendamentoDoDia(hoje.plusDays(2)));
                setQuinta(new AgendamentoDoDia(hoje.plusDays(3)));
                setSexta(new AgendamentoDoDia(hoje.plusDays(4)));
                setSabado(new AgendamentoDoDia(hoje.plusDays(5)));
                break;
            case 2:
                //terca
                setSegunda(new AgendamentoDoDia(hoje.minusDays(1)));
                setTerca(new AgendamentoDoDia(hoje));
                setQuarta(new AgendamentoDoDia(hoje.plusDays(1)));
                setQuinta(new AgendamentoDoDia(hoje.plusDays(2)));
                setSexta(new AgendamentoDoDia(hoje.plusDays(3)));
                setSabado(new AgendamentoDoDia(hoje.plusDays(4)));
                break;
            case 3:
                setSegunda(new AgendamentoDoDia(hoje.minusDays(2)));
                setTerca(new AgendamentoDoDia(hoje.minusDays(1)));
                setQuarta(new AgendamentoDoDia(hoje));
                setQuinta(new AgendamentoDoDia(hoje.plusDays(1)));
                setSexta(new AgendamentoDoDia(hoje.plusDays(2)));
                setSabado(new AgendamentoDoDia(hoje.plusDays(3)));
                break;
            case 4:
                setSegunda(new AgendamentoDoDia(hoje.minusDays(3)));
                setTerca(new AgendamentoDoDia(hoje.minusDays(2)));
                setQuarta(new AgendamentoDoDia(hoje.minusDays(1)));
                setQuinta(new AgendamentoDoDia(hoje));
                setSexta(new AgendamentoDoDia(hoje.plusDays(1)));
                setSabado(new AgendamentoDoDia(hoje.plusDays(2)));
                break;
            case 5:
                setSegunda(new AgendamentoDoDia(hoje.minusDays(4)));
                setTerca(new AgendamentoDoDia(hoje.minusDays(3)));
                setQuarta(new AgendamentoDoDia(hoje.minusDays(2)));
                setQuinta(new AgendamentoDoDia(hoje.minusDays(1)));
                setSexta(new AgendamentoDoDia(hoje));
                setSabado(new AgendamentoDoDia(hoje.plusDays(1)));
                break;
            case 6:
                setSegunda(new AgendamentoDoDia(hoje.minusDays(5)));
                setTerca(new AgendamentoDoDia(hoje.minusDays(4)));
                setQuarta(new AgendamentoDoDia(hoje.minusDays(3)));
                setQuinta(new AgendamentoDoDia(hoje.minusDays(2)));
                setSexta(new AgendamentoDoDia(hoje.minusDays(1)));
                setSabado(new AgendamentoDoDia(hoje));
                break;
            case 7:
                setSegunda(new AgendamentoDoDia(hoje.plusDays(1)));
                setTerca(new AgendamentoDoDia(hoje.plusDays(2)));
                setQuarta(new AgendamentoDoDia(hoje.plusDays(3)));
                setQuinta(new AgendamentoDoDia(hoje.plusDays(4)));
                setSexta(new AgendamentoDoDia(hoje.plusDays(5)));
                setSabado(new AgendamentoDoDia(hoje.plusDays(6)));
                break;
        }
    }

    public void nextWeek() {
        getSegunda().nextWeek();
        getTerca().nextWeek();
        getQuarta().nextWeek();
        getQuinta().nextWeek();
        getSexta().nextWeek();
        getSabado().nextWeek();

    }

    public void previusWeek() {
        getSegunda().previusWeek();
        getTerca().previusWeek();
        getQuarta().previusWeek();
        getQuinta().previusWeek();
        getSexta().previusWeek();
        getSabado().previusWeek();
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
