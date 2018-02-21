package odontomais.service;

import java.util.List;
import odontomais.model.TipoAgendamento;
import odontomais.persistence.TipoAgendamentoDao;

/*
 * Author: phlab
 * Date: 01/02/18
 */
public class TipoAgendamentoService {

    TipoAgendamentoDao dao;

    public TipoAgendamentoService() {
        dao = new TipoAgendamentoDao();
    }

    public boolean salvar(TipoAgendamento t) {
        if (dao.salvar(t).getId() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public int findExisteByName(String nome){
        return dao.findExisteByName(nome);
    }
    
    public List<TipoAgendamento> findAll(){
        return dao.getList();
    }
}
