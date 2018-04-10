package odontomais.persistence;

import odontomais.model.SaidaValor;
import odontomais.persistence.jpa.GenericDAO;

public class SaidaValorDao extends GenericDAO<SaidaValor, Long> {
    public SaidaValorDao() { super(SaidaValor.class);}
}
