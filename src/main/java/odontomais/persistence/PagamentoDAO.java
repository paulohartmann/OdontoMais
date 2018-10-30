package odontomais.persistence;

import odontomais.model.Paciente;
import odontomais.model.Pagamento;
import odontomais.persistence.jpa.GenericDAO;

import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

/*
 * Author: phlab
 * Date: 18/10/18
 */
public class PagamentoDAO extends GenericDAO<Pagamento, Long> {

    public List<Pagamento> findFilter(Paciente paciente, LocalDate dataIni, LocalDate dataFim) {
        List<Pagamento> resultado = null;
        String consulta = "SELECT c FROM Pagamento c WHERE " +
                "(c.dataHora >= :inicial AND c.dataHora < :final AND c.paciente = :p) OR " +
                "(c.dataHora > :inicial AND c.dataHora <= :final AND c.paciente = :p) order by c.dataHora";
        try {
            Query query = criarQuery(consulta);
            query.setParameter("inicial", dataIni);
            query.setParameter("final", dataFim);
            query.setParameter("p", paciente);
            resultado = (List<Pagamento>) query.getResultList();
        } catch (Exception rx) {
            getLogger().error("Erro ao filtra Pagamentos", rx.getCause());
        }
        return resultado;
    }


    public List<Pagamento> findFilter(Paciente paciente) {
        List<Pagamento> resultado = null;
        String consulta = "SELECT c FROM Pagamento c WHERE c.paciente = :p order by c.dataHora";
        try {
            Query query = criarQuery(consulta);
            query.setParameter("p", paciente);
            resultado = (List<Pagamento>) query.getResultList();
        } catch (Exception rx) {
            getLogger().error("Erro ao filtra Pagamentos", rx.getCause());
        }
        return resultado;
    }

    public List<Pagamento> findFilter(String nomeProfissional) {
        List<Pagamento> resultado = null;
        String consulta = "SELECT c FROM Pagamento c WHERE c.profissional = :p order by c.dataHora";
        try {
            Query query = criarQuery(consulta);
            query.setParameter("p", nomeProfissional);
            resultado = (List<Pagamento>) query.getResultList();
        } catch (Exception rx) {
            getLogger().error("Erro ao filtra Pagamentos", rx.getCause());
        }
        return resultado;
    }

    public List<Pagamento> findFilter(LocalDate dataIni, LocalDate dataFim, String nomeProfissional) {
        List<Pagamento> resultado = null;
        String consulta = "SELECT c FROM Pagamento c WHERE " +
                "(c.dataHora >= :inicial AND c.dataHora < :final and c.profissional = :p) OR " +
                "(c.dataHora > :inicial AND c.dataHora <= :final and c.profissional = :p) order by c.dataHora";
        try {
            Query query = criarQuery(consulta);
            query.setParameter("inicial", dataIni);
            query.setParameter("final", dataFim);
            query.setParameter("p", nomeProfissional);
            resultado = (List<Pagamento>) query.getResultList();
        } catch (Exception rx) {
            getLogger().error("Erro ao filtra Pagamentos", rx.getCause());
        }
        return resultado;
    }

    public List<Pagamento> findFilter(LocalDate dataIni, LocalDate dataFim) {
        List<Pagamento> resultado = null;
        String consulta = "SELECT c FROM Pagamento c WHERE " +
                "(c.dataHora >= :inicial AND c.dataHora < :final) OR " +
                "(c.dataHora > :inicial AND c.dataHora <= :final) order by c.dataHora";
        try {
            Query query = criarQuery(consulta);
            query.setParameter("inicial", dataIni);
            query.setParameter("final", dataFim);
            resultado = (List<Pagamento>) query.getResultList();
        } catch (Exception rx) {
            getLogger().error("Erro ao filtra Pagamentos", rx.getCause());
        }
        return resultado;
    }
}
