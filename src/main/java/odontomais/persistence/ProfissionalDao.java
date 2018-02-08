package odontomais.persistence;

import odontomais.model.Profissional;
import odontomais.persistence.jpa.GenericDAO;

public class ProfissionalDao extends GenericDAO<Profissional, Long> {
    public ProfissionalDao() { super(Profissional.class);}

    public int fingByName(String nome){
        return 0;
    }
}
