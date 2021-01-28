package odontomais.service;

import odontomais.model.ContasPagar;
import odontomais.persistence.ContasPagarDAO;

import java.time.LocalDate;
import java.util.List;

/*
 * Author: phlab
 * Date: 07/02/18
 */
public class ContasPagarService {

    ContasPagarDAO dao;

    public ContasPagarService() {
        dao = new ContasPagarDAO();
    }

    public boolean salvar(ContasPagar p) {
        if (dao.salvar(p).getId() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void atualizar(ContasPagar p) {
        dao.atualizar(p);
    }

    public List<ContasPagar> getList() {
        return dao.getList();
    }

    public List<ContasPagar> getListVencimento(int max) {
        return dao.getListVencimento(max);
    }

    public List<ContasPagar> getList(LocalDate inicio, LocalDate fim) {
        /*
        Bug MySQL trazendo dataPagamento um dia antes
         */
        List<ContasPagar> list = dao.getList(inicio, fim);
        for (ContasPagar c : list) {
            if (c.getDataPagamento() != null) {
                c.setDataPagamento(c.getDataPagamento().plusDays(1));
            }
        }
        /*
        Fim Bug
         */
        return dao.getList(inicio, fim);
    }

    public void remover(ContasPagar p) {
        dao.remover(p.getId());
    }

}
