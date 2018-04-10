package odontomais.persistence;

import odontomais.model.EntradaValor;
import odontomais.persistence.jpa.GenericDAO;

public class EntradaValorDao extends GenericDAO<EntradaValor, Long> {
    public EntradaValorDao() { super(EntradaValor.class);}
}
