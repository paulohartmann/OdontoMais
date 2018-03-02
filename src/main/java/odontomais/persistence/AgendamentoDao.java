package odontomais.persistence;

import odontomais.model.Agendamento;
import odontomais.model.Paciente;
import odontomais.model.Profissional;
import odontomais.persistence.jpa.GenericDAO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

public class AgendamentoDao extends GenericDAO<Agendamento, Long> {

    public AgendamentoDao() {
        super(Agendamento.class);
    }

    public List<Agendamento> findHorarioByHoraData(LocalTime hora, LocalTime horaFim, LocalDate dia, Profissional p) {
        List<Agendamento> resultado = null;
        String consulta = "SELECT c FROM Agendamento c WHERE "
                + "(c.horaInicio <= :final AND c.horaFim >= :final AND c.dataAgenda = :dia AND c.profissional = :p)";
        try {
            Query query = criarQuery(consulta);
           // query.setParameter("inicial", hora);
            query.setParameter("final", horaFim);
            query.setParameter("dia", dia);
            query.setParameter("p", p);
            resultado = (List<Agendamento>) query.getResultList();
        } catch (Exception rx) {
            getLogger().error("Erro ao procurar agendamento do dia", rx.getCause());
        }
        return resultado;
    }

    public List<Agendamento> findAgendaByDataProfissional(LocalDate dia, long id) {
        List<Agendamento> resultado = null;
        String consulta = "SELECT c FROM Agendamento c WHERE "
                + "c.dataAgenda = :dia and c.profissional.id = :id";
        try {
            Query query = criarQuery(consulta);
            query.setParameter("dia", dia);
            query.setParameter("id", id);
            resultado = (List<Agendamento>) query.getResultList();
        } catch (Exception rx) {
            getLogger().error("Erro ao procurar agendamento do dia e profissional", rx.getCause());
        }
        return resultado;
    }

    public List<Agendamento> findAgendaDia(LocalDate dia) {
        List<Agendamento> resultado = new ArrayList<>();
        String consulta = "SELECT c FROM Agendamento c WHERE "
                + "c.dataAgenda = :dia";
        try {
            Query query = criarQuery(consulta);
            query.setParameter("dia", dia);
            resultado = (List<Agendamento>) query.getResultList();
        } catch (Exception rx) {
            getLogger().error("Erro ao procurar agendamento por paciente", rx.getCause());
        }
        return resultado;
    }

    public Agendamento findById(long id) {
        Agendamento resultado = null;
        String consulta = "SELECT c FROM Agendamento c WHERE "
                + "c.id = :id";
        try {
            Query query = criarQuery(consulta);
            query.setParameter("id", id);
            resultado = (Agendamento) query.getSingleResult();
        } catch (Exception rx) {
            getLogger().error("Erro ao procurar agendamento por paciente", rx.getCause());
        }
        return resultado;
    }


    public List<Agendamento> findByPaciente(Paciente p) {
        List<Agendamento> resultado = null;
        String consulta = "SELECT c FROM Agendamento c WHERE "
                + "c.paciente = :p";
        try {
            Query query = criarQuery(consulta);
            query.setParameter("p", p);
            resultado = (List<Agendamento>) query.getResultList();
        } catch (Exception rx) {
            getLogger().error("Erro ao procurar agendamento por paciente", rx.getCause());
        }
        return resultado;
    }
}
