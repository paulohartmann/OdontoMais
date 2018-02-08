package odontomais.persistence;

import odontomais.model.Convenio;
import odontomais.persistence.jpa.GenericDAO;

public class ConvenioDao extends GenericDAO<Convenio, Long> {
    public ConvenioDao(){
        super(Convenio.class);
    }

    //TODO: criar teste de integração com o banco de dados
    //qualquer outra pesquisa que não for as default do Generic
}
