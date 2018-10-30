package odontomais.service;

import odontomais.model.Paciente;
import odontomais.model.Pagamento;
import odontomais.persistence.PagamentoDAO;

import java.time.LocalDate;
import java.util.List;

/*
 * Author: phlab
 * Date: 18/10/18
 */
public class PagamentoService {

    PagamentoDAO dao;

    public PagamentoService(){
        dao = new PagamentoDAO();
    }

    public boolean salvar(Pagamento c){
        return dao.salvar(c).getId() > 0;
    }

    public List<Pagamento> findFilter(Paciente paciente, LocalDate dataIni, LocalDate dataFim){
        return dao.findFilter(paciente, dataIni, dataFim);

    }

    public List<Pagamento> findFilter(LocalDate dataIni, LocalDate dataFim, String nomeProfissional){
        return dao.findFilter(dataIni, dataFim, nomeProfissional);

    }

    public List<Pagamento> findFilter(LocalDate dataIni, LocalDate dataFim){
        return dao.findFilter(dataIni, dataFim);

    }

    public List<Pagamento> findFilter(Paciente paciente){
        return dao.findFilter(paciente);

    }

    public List<Pagamento> findFilter(String nomeProfissional){
        return dao.findFilter(nomeProfissional);

    }

}
