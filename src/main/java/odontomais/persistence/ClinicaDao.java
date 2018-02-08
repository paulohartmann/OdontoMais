package odontomais.persistence;

import odontomais.model.Clinica;
import odontomais.persistence.jpa.GenericDAO;

public class ClinicaDao extends GenericDAO<Clinica, Long> {
    public ClinicaDao() { super(Clinica.class);}
}
