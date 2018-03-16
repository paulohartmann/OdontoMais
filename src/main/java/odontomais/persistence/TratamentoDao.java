package odontomais.persistence;

import odontomais.model.Tratamento;
import odontomais.persistence.jpa.GenericDAO;

/*
 * Author: phlab
 * Date: 16/03/18
 */
public class TratamentoDao extends GenericDAO<Tratamento, Long> {

    public TratamentoDao() {super(Tratamento.class);}

}
