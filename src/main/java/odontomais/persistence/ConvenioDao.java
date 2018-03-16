package odontomais.persistence;

import javax.persistence.Query;

import odontomais.model.Convenio;
import odontomais.persistence.jpa.GenericDAO;

import java.util.List;

public class ConvenioDao extends GenericDAO<Convenio, Long> {

    public ConvenioDao() {
        super(Convenio.class);
    }

    public int findQtdByName(String nome) {
        int resultado = 0;
        String consulta = "SELECT c FROM Convenio c WHERE "
                + "c.nome = :param";
        try {
            Query query = criarQuery(consulta);
            query.setParameter("param", nome);
            resultado = (int) query.getFirstResult();
        } catch (Exception rx) {
            getLogger().error("Erro ao procurar qnt convênio", rx.getCause());
        }
        return resultado;
    }

    public List<Convenio> findExisteByName(String nome) {
        List<Convenio> resultado = null;
        String consulta = "SELECT c FROM Convenio c WHERE "
                + "c.nome LIKE :param";
        try {
            Query query = criarQuery(consulta);
            query.setParameter("param", nome + "%");
            resultado = (List<Convenio>) query.getResultList();
        } catch (Exception rx) {
            getLogger().error("Erro ao procurar convênio pelo nome", rx.getCause());
        }
        return resultado;
    }

    public Convenio findById(long id) {
        Convenio resultado = null;
        String consulta = "SELECT c FROM Convenio c WHERE "
                + "c.id = :id";
        try {
            Query query = criarQuery(consulta);
            query.setParameter("id", id);
            resultado = (Convenio) query.getSingleResult();
        } catch (Exception rx) {
            getLogger().error("Erro ao procurar convênio pelo id", rx.getCause());
        }
        return resultado;
    }

    public Convenio get(String nome) {
        Convenio resultado = null;
        String consulta = "SELECT c FROM Convenio c WHERE "
                + "c.nome = :nome";
        try {
            Query query = criarQuery(consulta);
            query.setParameter("nome", nome);
            resultado = (Convenio) query.getSingleResult();
        } catch (Exception rx) {
            getLogger().error("Erro ao procurar convênio pelo nome", rx.getCause());
        }
        return resultado;
    }
}
