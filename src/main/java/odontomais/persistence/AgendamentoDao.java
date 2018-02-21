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
            getLogger().error("Erro ao procurar agendamento do dia", rx.getCause());
        }
        return resultado;
    }
    
    public List<Agendamento> findAgendaByDataProfissional(LocalDate dia, long id) {
        List<Agendamento> resultado = null;
        String consulta = "SELECT c FROM Agendamento c WHERE "
                + "c.dataAgenda = :dia and c.profissional.id = id";
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
    
    public int findHorarioByPaciente(String nome) {
        int resultado = 0;
        String consulta = "SELECT c FROM Agendamento c WHERE "
                + "c.paciente.nomeCompleto LIKE :inicial";
        try {
            Query query = criarQuery(consulta);
            query.setParameter("inicial", nome + "%");
            resultado = (int) query.getFirstResult();
        } catch (Exception rx) {
            getLogger().error("Erro ao procurar agendamento por paciente", rx.getCause());
        }
        return resultado;
    }
    
}
