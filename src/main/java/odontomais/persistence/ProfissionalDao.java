package odontomais.persistence;

import javax.persistence.Query;
import odontomais.model.Profissional;
import odontomais.persistence.jpa.GenericDAO;

public class ProfissionalDao extends GenericDAO<Profissional, Long> {

    public ProfissionalDao() {
        super(Profissional.class);
    }
    
    public int findQtdByName(String param) {
        int resultado = 0;
        String consulta = "SELECT c FROM Profissional c WHERE "
                + "c.nome = :param";
        try {
            Query query = criarQuery(consulta);
            query.setParameter("param", param);
            resultado = (int) query.getFirstResult();
        } catch (Exception rx) {
            getLogger().error("Erro ao procurar qtd de profissionais", rx.getCause());
        }
        return resultado;
    }
    
    public Profissional findExisteByName(String param) {
        Profissional resultado = null;
        String consulta = "SELECT c FROM Profissional c WHERE "
                + "c.nome = :param";
        try {
            Query query = criarQuery(consulta);
            query.setParameter("param", param);
            resultado = (Profissional) query.getSingleResult();
        } catch (Exception rx) {
            getLogger().error("Erro ao procurar profissional pelo nome", rx.getCause());
        }
        return resultado;
    }
}
