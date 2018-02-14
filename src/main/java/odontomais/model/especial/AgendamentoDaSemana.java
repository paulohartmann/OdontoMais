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
                segunda = new AgendamentoDoDia(hoje);
                terca = new AgendamentoDoDia(hoje.plusDays(1));
                quarta = new AgendamentoDoDia(hoje.plusDays(2));
                quinta = new AgendamentoDoDia(hoje.plusDays(3));
                sexta = new AgendamentoDoDia(hoje.plusDays(4));
                sabado = new AgendamentoDoDia(hoje.plusDays(5));
                break;
            case 2:
                //terca
                segunda = new AgendamentoDoDia(hoje.minusDays(1));
                terca = new AgendamentoDoDia(hoje);
                quarta = new AgendamentoDoDia(hoje.plusDays(1));
                quinta = new AgendamentoDoDia(hoje.plusDays(2));
                sexta = new AgendamentoDoDia(hoje.plusDays(3));
                sabado = new AgendamentoDoDia(hoje.plusDays(4));
                break;
            case 3:
                segunda = new AgendamentoDoDia(hoje.minusDays(2));
                terca = new AgendamentoDoDia(hoje.minusDays(1));
                quarta = new AgendamentoDoDia(hoje);
                quinta = new AgendamentoDoDia(hoje.plusDays(1));
                sexta = new AgendamentoDoDia(hoje.plusDays(2));
                sabado = new AgendamentoDoDia(hoje.plusDays(3));
                break;
            case 4:
                segunda = new AgendamentoDoDia(hoje.minusDays(3));
                terca = new AgendamentoDoDia(hoje.minusDays(2));
                quarta = new AgendamentoDoDia(hoje.minusDays(1));
                quinta = new AgendamentoDoDia(hoje);
                sexta = new AgendamentoDoDia(hoje.plusDays(1));
                sabado = new AgendamentoDoDia(hoje.plusDays(2));
                break;
            case 5:
                segunda = new AgendamentoDoDia(hoje.minusDays(4));
                terca = new AgendamentoDoDia(hoje.minusDays(3));
                quarta = new AgendamentoDoDia(hoje.minusDays(2));
                quinta = new AgendamentoDoDia(hoje.minusDays(1));
                sexta = new AgendamentoDoDia(hoje);
                sabado = new AgendamentoDoDia(hoje.plusDays(1));
                break;
            case 6:
                segunda = new AgendamentoDoDia(hoje.minusDays(5));
                terca = new AgendamentoDoDia(hoje.minusDays(4));
                quarta = new AgendamentoDoDia(hoje.minusDays(3));
                quinta = new AgendamentoDoDia(hoje.minusDays(2));
                sexta = new AgendamentoDoDia(hoje.minusDays(1));
                sabado = new AgendamentoDoDia(hoje);
                break;
            case 7:
                segunda = new AgendamentoDoDia(hoje.plusDays(1));
                terca = new AgendamentoDoDia(hoje.plusDays(2));
                quarta = new AgendamentoDoDia(hoje.plusDays(3));
                quinta = new AgendamentoDoDia(hoje.plusDays(4));
                sexta = new AgendamentoDoDia(hoje.plusDays(5));
                sabado = new AgendamentoDoDia(hoje.plusDays(6));
                break;
        }
    }

    public void nextWeek() {
        segunda.nextWeek();
        terca.nextWeek();
        quarta.nextWeek();
        quinta.nextWeek();
        sexta.nextWeek();
        sabado.nextWeek();

    }

    public void previusWeek() {
        segunda.previusWeek();
        terca.previusWeek();
        quarta.previusWeek();
        quinta.previusWeek();
        sexta.previusWeek();
        sabado.previusWeek();
    }

}
