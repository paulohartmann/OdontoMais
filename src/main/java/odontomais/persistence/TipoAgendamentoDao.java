package odontomais.persistence;

import odontomais.model.TipoAgendamento;
import odontomais.persistence.jpa.GenericDAO;

import javax.persistence.Query;

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
            getLogger().error("Erro ao procurar tipoagendamento por nome", rx.getCause());
        }
        return resultado;
    }
}
