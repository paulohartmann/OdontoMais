package odontomais.service;

import odontomais.model.Convenio;
import odontomais.persistence.ConvenioDao;

import java.util.List;

/*
 * Author: phlab
 * Date: 05/02/18
 */
public class ConvenioService {

    ConvenioDao dao;

    public ConvenioService(){
        dao = new ConvenioDao();
    }

    public boolean salvar(Convenio c){
        if(dao.salvar(c).getId() > 0){
            return true;
        }else{
            return false;
        }
    }

    public List<Convenio> getLista(){
        return dao.getList();
    }
}
