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
        List<Pagamento> list = dao.findFilter(paciente, dataIni, dataFim);
        /*
        Bug onde o MySQL salva data correta e traz a data um dia antes
         */
        for(Pagamento p : list){
            p.setDataHora(p.getDataHora().plusDays(1));
        }
        /*
        Fim
         */
        return list;
    }

    public List<Pagamento> findFilter(LocalDate dataIni, LocalDate dataFim, String nomeProfissional){
        List<Pagamento> list = dao.findFilter(dataIni, dataFim, nomeProfissional);
        /*
        Bug onde o MySQL salva data correta e traz a data um dia antes
         */
        for(Pagamento p : list){
            p.setDataHora(p.getDataHora().plusDays(1));
        }
        /*
        Fim
         */
        return list;

    }

    public List<Pagamento> findFilter(LocalDate dataIni, LocalDate dataFim){
        List<Pagamento> list = dao.findFilter(dataIni, dataFim);
        /*
        Bug onde o MySQL salva data correta e traz a data um dia antes
         */
        for(Pagamento p : list){
            p.setDataHora(p.getDataHora().plusDays(1));
        }
        /*
        Fim
         */
        return list;

    }

    public List<Pagamento> findFilter(Paciente paciente){
        List<Pagamento> list = dao.findFilter(paciente);
        /*
        Bug onde o MySQL salva data correta e traz a data um dia antes
         */
        for(Pagamento p : list){
            p.setDataHora(p.getDataHora().plusDays(1));
        }
        /*
        Fim
         */
        return list;

    }

    public List<Pagamento> findFilter(String nomeProfissional){
        List<Pagamento> list = dao.findFilter(nomeProfissional);
        /*
        Bug onde o MySQL salva data correta e traz a data um dia antes
         */
        for(Pagamento p : list){
            p.setDataHora(p.getDataHora().plusDays(1));
        }
        /*
        Fim
         */
        return list;
    }

    public void remover(Pagamento p){
        dao.remover(p.getId());
    }

}
