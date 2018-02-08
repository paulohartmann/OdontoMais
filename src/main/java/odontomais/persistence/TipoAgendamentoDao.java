package odontomais.persistence;

import odontomais.model.TipoAgendamento;
import odontomais.persistence.jpa.GenericDAO;

public class TipoAgendamentoDao extends GenericDAO<TipoAgendamento, Long> {
    public TipoAgendamentoDao() { super(TipoAgendamento.class);}

    public int findByName(String nome){
        return 0;
    }
}
