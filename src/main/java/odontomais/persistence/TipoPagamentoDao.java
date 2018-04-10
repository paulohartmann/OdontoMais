package odontomais.persistence;

import odontomais.model.TipoPagamento;
import odontomais.persistence.jpa.GenericDAO;

public class TipoPagamentoDao extends GenericDAO<TipoPagamento, Long> {

    public TipoPagamentoDao() {
        super(TipoPagamento.class);
    }
}
