package odontomais.service;

import odontomais.model.Profissional;
import odontomais.persistence.ProfissionalDao;

import java.util.List;

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

    public Profissional findByName(String nome) {
        return dao.findExisteByName(nome);
    }

    public List<Profissional> getList() {
        return dao.getList();
    }

    public List<Profissional> findLikeName(String nome) {

        return dao.findLikeName(nome);

    }

    public void remover(Profissional p){
        dao.remover(p.getId());
    }

}
