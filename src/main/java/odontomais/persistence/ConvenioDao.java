package odontomais.persistence;

import javax.persistence.Query;
import odontomais.model.Convenio;
import odontomais.persistence.jpa.GenericDAO;

public class ConvenioDao extends GenericDAO<Convenio, Long> {

    public ConvenioDao() {
        super(Convenio.class);
    }

    public int findExisteByName(String nome) {
        int resultado = 0;
        String consulta = "SELECT c FROM Convenio c WHERE "
                + "c.nome = :param";
        try {
            Query query = criarQuery(consulta);
            query.setParameter("param", nome);
            resultado = (int) query.getFirstResult();
        } catch (Exception rx) {

        }
        return resultado;
    }
}
