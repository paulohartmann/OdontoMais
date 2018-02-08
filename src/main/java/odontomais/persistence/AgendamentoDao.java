package odontomais.persistence;

import odontomais.model.Agendamento;
import odontomais.persistence.jpa.GenericDAO;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.time.LocalDate;


public class AgendamentoDao extends GenericDAO<Agendamento, Long> {
    public AgendamentoDao() { super(Agendamento.class);}

    public int findQntByHora(LocalDate param) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Agendamento> query = builder.createQuery(Agendamento.class);
        Root<Agendamento> c = query.from(Agendamento.class);
        ParameterExpression<LocalDate> inicio = builder.parameter(LocalDate.class);

        query.select(c).where(builder.lessThanOrEqualTo(c.get("horaInicio"), inicio));
        query.select(c).where(builder.greaterThanOrEqualTo(c.get("horaFim"), inicio));

        TypedQuery<Agendamento> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter(inicio, param);
        int results = typedQuery.getMaxResults();

        return results;
    }

}
