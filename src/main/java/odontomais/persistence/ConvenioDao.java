package odontomais.persistence;

import javax.persistence.Query;
import odontomais.model.Convenio;
import odontomais.persistence.jpa.GenericDAO;

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

    public Convenio findExisteByName(String nome) {
        Convenio resultado = null;
        String consulta = "SELECT c FROM Convenio c WHERE "
                + "c.nome = :param";
        try {
            Query query = criarQuery(consulta);
            query.setParameter("param", nome);
            resultado = (Convenio) query.getSingleResult();
        } catch (Exception rx) {
            getLogger().error("Erro ao procurar convênio pelo nome", rx.getCause());
        }
        return resultado;
    }
}
