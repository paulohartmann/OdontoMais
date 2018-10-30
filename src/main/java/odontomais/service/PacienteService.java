package odontomais.service;

import odontomais.model.Paciente;
import odontomais.model.Pagamento;
import odontomais.persistence.PacienteDao;
import odontomais.view.ListaPacientes;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/*
 * Author: phlab
 * Date: 15/01/18
 */
public class PacienteService {

    final static Logger logger = Logger.getLogger(PacienteService.class);
    PacienteDao dao;

    public PacienteService() {
        dao = new PacienteDao();
    }

    public boolean salvar(Paciente p) {
        return dao.salvar(p).getId() > 0;
    }

    public boolean atualizarDebito(Pagamento pagamento){
        try {
            Paciente paciente = pagamento.getPaciente();
            paciente.setDebito(calculaDebito(paciente.getDebito(), pagamento.getDebito()));
            atualizar(paciente);
        }catch (Exception ex){
            logger.error("Erro ao atualizar débito do paciente", ex.getCause());
        }
        return true;
    }

    public BigDecimal calculaDebito(BigDecimal debitoAtual, BigDecimal pagamento){
        return debitoAtual.add(pagamento);
    }

    public Paciente goProcuraPaciente() {
        ListaPacientes listaPacientes = new ListaPacientes();
        listaPacientes.pack();
        listaPacientes.setLocationRelativeTo(null);
        listaPacientes.setVisible(true);

        if (listaPacientes.getPaciente() != null) {
            return listaPacientes.getPaciente();

        }
        return null;
    }

    public void remover(Paciente p){
        dao.remover(p.getId());
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

    public Paciente findById(long id){
        return dao.encontrar(id);
    }
    
    public Paciente findByRG(String rg){
        return dao.findFromRG(rg);
    }
    
    public List<Paciente> findFromName(String nome){
        return dao.findFromNome(nome);
    }

    public List<Paciente> findDataAniver(LocalDate date) { return dao.findFromDataAniver(date); }
}
