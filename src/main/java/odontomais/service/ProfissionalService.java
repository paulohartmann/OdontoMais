package odontomais.service;

import odontomais.model.Profissional;
import odontomais.persistence.ProfissionalDao;

/*
 * Author: phlab
 * Date: 07/02/18
 */
public class ProfissionalService {

    ProfissionalDao dao;

    public ProfissionalService(){
        dao = new ProfissionalDao();
    }

    public boolean salvar(Profissional p){
        if(dao.salvar(p).getId() > 0){
            return true;
        }else{
            return false;
        }
    }

    public int findExisteByName(String nome){
        return dao.findExisteByName(nome);
    }
}
