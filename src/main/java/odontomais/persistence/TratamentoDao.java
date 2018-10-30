package odontomais.persistence;

import odontomais.model.Tratamento;
import odontomais.persistence.jpa.GenericDAO;

import javax.persistence.Query;

/*
 * Author: phlab
 * Date: 16/03/18
 */
public class TratamentoDao extends GenericDAO<Tratamento, Long> {

    public TratamentoDao() {super(Tratamento.class);}

    public Tratamento findbyName(String name) {
        Tratamento resultado = null;
        String consulta = "SELECT c FROM Tratamento c WHERE "
                + "c.nome = :param";
        try {
            Query query = criarQuery(consulta);
            query.setParameter("param", name);
            resultado = (Tratamento) query.getSingleResult();
        } catch (Exception rx) {
            getLogger().error("Erro ao procurar tratamenti pelo nome", rx.getCause());
        }
        return resultado;
    }

    public int findExisteByName(String nome) {
        int resultado = 0;
        String consulta = "SELECT c FROM Tratamento c WHERE "
                + "c.nome = :param";
        try {
            Query query = criarQuery(consulta);
            query.setParameter("param", nome);
            resultado = query.getFirstResult();
        } catch (Exception rx) {
            getLogger().error("Erro ao procurar tratamento por nome", rx.getCause());
        }
        return resultado;
    }

}
