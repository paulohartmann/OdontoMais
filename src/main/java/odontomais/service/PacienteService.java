package odontomais.service;

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

    public int findByCPF(String cpf){
        return dao.findByCPF(cpf);
    }

    public int findByRG(String rg){
        return dao.findByRG(rg);
    }
}
