package odontomais.persistence;

import odontomais.model.ContasPagar;
import odontomais.persistence.jpa.GenericDAO;

import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;


/*
 * Author: phlab
 * Date: 18/10/18
 */
public class ContasPagarDAO extends GenericDAO<ContasPagar, Long> {

    public ContasPagarDAO() {
        super(ContasPagar.class);
    }

    public List<ContasPagar> getListVencimento(int max) {
        List<ContasPagar> resultado = null;
        String consulta = "SELECT c FROM ContasPagar c WHERE "
                +  "c.dataPagamento = null ORDER BY c.dataValidade";
        try {
            Query query = criarQuery(consulta);
            resultado = (List<ContasPagar>) query.setMaxResults(max).getResultList();
        } catch (Exception rx) {
            getLogger().error("Erro ao procurar ContasPagar por vencimento", rx.getCause());
        }
        return resultado;
    }

    public List<ContasPagar> getList(LocalDate inicio, LocalDate fim){
        List<ContasPagar> resultado = null;
        String consulta = "SELECT c FROM ContasPagar c WHERE " +
                "(c.dataPagamento >= :inicial AND c.dataPagamento < :final) OR " +
                "(c.dataPagamento > :inicial AND c.dataPagamento <= :final) order by c.dataPagamento";
        try {
            Query query = criarQuery(consulta);
            query.setParameter("inicial", inicio);
            query.setParameter("final", fim);
            resultado = (List<ContasPagar>) query.getResultList();
        } catch (Exception rx) {
            getLogger().error("Erro ao filtra Pagamentos", rx.getCause());
        }
        return resultado;
    }

}
