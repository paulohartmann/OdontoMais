package odontomais.service;

import odontomais.model.Tratamento;
import odontomais.persistence.TratamentoDao;

import java.util.List;

/*
 * Author: phlab
 * Date: 16/03/18
 */
public class TratamentoService {

    TratamentoDao dao;

    public TratamentoService(){
        dao = new TratamentoDao();
    }

    public boolean salvar(Tratamento t){
        return dao.salvar(t).getId() > 0;
    }

    public void remover(Tratamento t){
        dao.remover(t.getId());
    }

    public List<Tratamento> getList(){
        try {
            return dao.getList();
        }catch (NullPointerException ex){
            return null;
        }
    }

    public Tratamento findByName(String name){
        return dao.findbyName(name);
    }

    public int findExisteByName(String name){
        return dao.findExisteByName(name);
    }



}
