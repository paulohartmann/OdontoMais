package odontomais.service;

import odontomais.model.Clinica;
import odontomais.persistence.ClinicaDao;

/*
 * Author: phlab
 * Date: 01/02/18
 */
public class ClinicaService {

    ClinicaDao dao;

    public ClinicaService(){
        dao = new ClinicaDao();
    }

    public boolean salvar(Clinica c){
        if(dao.salvar(c).getId() > 0){
            return true;
        }else{
            return false;
        }
    }
}
