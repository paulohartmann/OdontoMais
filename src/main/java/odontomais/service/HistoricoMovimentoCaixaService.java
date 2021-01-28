package odontomais.service;

import odontomais.model.ContasPagar;
import odontomais.model.Pagamento;
import odontomais.model.especial.HistoricoMovimentoCaixa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/*
 * Author: phlab
 * Date: 11/12/18
 */
public class HistoricoMovimentoCaixaService {

    public HistoricoMovimentoCaixa getMovimento(LocalDate inicio, LocalDate fim) {
        List<ContasPagar> listContasPagar = new ContasPagarService().getList(inicio, fim);
        List<Pagamento> listPagamento = new PagamentoService().findFilter(inicio, fim);

        HistoricoMovimentoCaixa historico = new HistoricoMovimentoCaixa();
        for (ContasPagar c : listContasPagar) {
            historico.addSaida(c);
        }
        for (Pagamento p : listPagamento) {
            if (p.getDebito().compareTo(new BigDecimal(0)) < 0)
                historico.addEntrada(p);
        }

        return historico;

    }
}
