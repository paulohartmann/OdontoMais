package odontomais.persistence;

import odontomais.model.Agendamento;
import odontomais.persistence.jpa.GenericDAO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.Query;

public class AgendamentoDao extends GenericDAO<Agendamento, Long> {

    public AgendamentoDao() {
        super(Agendamento.class);
    }

    public int findHorarioByHoraData(LocalTime hora, LocalDate dia) {
        int resultado = 0;
        String consulta = "SELECT c FROM Agendamento c WHERE "
                + "c.horaInicio >= :inicial AND c.horaFim <= :inicial AND c.dataAgenda = :dia";
        try {
            Query query = criarQuery(consulta);
            query.setParameter("inicial", hora);
            query.setParameter("dia", dia);
            resultado = (int) query.getFirstResult();
        } catch (Exception rx) {

        }
        return resultado;
    }

    public List<Agendamento> findAgendaByData(LocalDate dia) {
        List<Agendamento> resultado = null;
        String consulta = "SELECT c FROM Agendamento c WHERE "
                + "c.dataAgenda = :dia";
        try {
            Query query = criarQuery(consulta);
            query.setParameter("dia", dia);
            resultado = (List<Agendamento>) query.getResultList();
        } catch (Exception rx) {

        }
        return resultado;
    }

    public int findHorarioByPaciente(String nome) {
        int resultado = 0;
        String consulta = "SELECT c FROM Agendamento c WHERE "
                + "c.paciente.nomeCompleto LIKE :inicial";
        try {
            Query query = criarQuery(consulta);
            query.setParameter("inicial", nome + "%");
            resultado = (int) query.getFirstResult();
        } catch (Exception rx) {

        }
        return resultado;
    }

}
