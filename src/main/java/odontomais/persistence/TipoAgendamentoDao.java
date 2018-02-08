package odontomais.persistence;

import javax.persistence.Query;
import odontomais.model.TipoAgendamento;
import odontomais.persistence.jpa.GenericDAO;

public class TipoAgendamentoDao extends GenericDAO<TipoAgendamento, Long> {

    public TipoAgendamentoDao() {
        super(TipoAgendamento.class);
    }

    public int findExisteByName(String nome) {
        int resultado = 0;
        String consulta = "SELECT c FROM TipoAgendamento c WHERE "
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
