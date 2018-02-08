package odontomais.persistence;

import javax.persistence.Query;
import odontomais.model.Profissional;
import odontomais.persistence.jpa.GenericDAO;

public class ProfissionalDao extends GenericDAO<Profissional, Long> {
    public ProfissionalDao() { super(Profissional.class);}

    public int findExisteByName(String param){
        int resultado = 0;
        String consulta = "SELECT c FROM Profissional c WHERE "
                + "c.nome = :param";
        try {
            Query query = criarQuery(consulta);
            query.setParameter("param", param);
            resultado = (int) query.getFirstResult();
        } catch (Exception rx) {

        }
        return resultado;
    }
}
