package odontomais.persistence;

import odontomais.model.Paciente;
import odontomais.persistence.jpa.GenericDAO;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;

public class PacienteDao extends GenericDAO<Paciente, Long> {
    public PacienteDao() {
        super(Paciente.class);
    }

    public void findFromCPF(String cpf) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> query = builder.createQuery(String.class);
    }

    public int findByCPF(String param) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Paciente> query = builder.createQuery(Paciente.class);
        Root<Paciente> c = query.from(Paciente.class);
        ParameterExpression<String> inicio = builder.parameter(String.class);

        query.select(c).where(builder.equal(c.get("cpf"), inicio));

        TypedQuery<Paciente> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter(inicio, param);
        int results = typedQuery.getMaxResults();

        return results;
    }

    public int findByRG(String param) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Paciente> query = builder.createQuery(Paciente.class);
        Root<Paciente> c = query.from(Paciente.class);
        ParameterExpression<String> inicio = builder.parameter(String.class);

        query.select(c).where(builder.equal(c.get("rg"), inicio));

        TypedQuery<Paciente> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter(inicio, param);
        int results = typedQuery.getMaxResults();

        return results;
    }
}