package odontomais.service;

import java.util.List;
import odontomais.model.Paciente;
import odontomais.persistence.PacienteDao;

/*
 * Author: phlab
 * Date: 15/01/18
 */
public class PacienteService {

    PacienteDao dao;

    public PacienteService() {
        dao = new PacienteDao();
    }

    public boolean salvarPaciente(Paciente p) {
        if (dao.salvar(p).getId() > 0) {
            return true;
        }else{
            return false;
        }
    }
    
    public void atualizar(Paciente p){
        dao.atualizar(p);
    }

    public int findExisteByCPF(String cpf){
        return dao.findExisteByCPF(cpf);
    }

    public int findExisteByRG(String rg){
        return dao.findExisteByRG(rg);
    }
    
    public Paciente findByCPF(String cpf){
        return dao.findFromCPF(cpf);
    }
    
    public Paciente findByRG(String rg){
        return dao.findFromRG(rg);
    }
    
    public List<Paciente> findFromName(String nome){
        return dao.findFromNome(nome);
    }
}
