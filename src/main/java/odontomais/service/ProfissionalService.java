package odontomais.service;

import java.util.List;
import odontomais.model.Profissional;
import odontomais.persistence.ProfissionalDao;

/*
 * Author: phlab
 * Date: 07/02/18
 */
public class ProfissionalService {

    ProfissionalDao dao;

    public ProfissionalService() {
        dao = new ProfissionalDao();
    }

    public boolean salvar(Profissional p) {
        if (dao.salvar(p).getId() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void atualizar(Profissional p) {
        dao.atualizar(p);
    }

    public int findQtdByName(String nome) {
        return dao.findQtdByName(nome);
    }
    
    public Profissional findByName(String nome){
        return dao.findExisteByName(nome);
    }
    
    public List<Profissional> getList(){
        return dao.getList();
    }

    public Profissional encontrar(long id){
        return dao.encontrar(id);
    }
}
