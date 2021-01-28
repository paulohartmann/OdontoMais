package odontomais.model.especial;

import odontomais.model.ContasPagar;
import odontomais.model.Pagamento;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Author: phlab
 * Date: 11/12/18
 */
public class HistoricoMovimentoCaixa {

    private List<MovimentoCaixa> listaMovimentoCaixa;

    public HistoricoMovimentoCaixa(List<MovimentoCaixa> list) {
        this.listaMovimentoCaixa = list;
    }

    public HistoricoMovimentoCaixa() {
        listaMovimentoCaixa = new ArrayList<>();
    }

    public void addEntrada(Pagamento p) {
        MovimentoCaixa add = new MovimentoCaixa();
        add.setData(p.getDataHora());
        String desc = "";
        if(p.getPaciente().getNomeCompleto() != null){
            desc += p.getPaciente().getNomeCompleto();
        }
        if(p.getTratamento() != null){
            desc += " - " + p.getTratamento();
        }
        if(p.getProfissional() != null){
            desc += " (" + p.getProfissional().substring(0, 5) + ")";
        }

        add.setDescricao(desc);
        BigDecimal soma = p.getDebito().multiply(new BigDecimal(-1));
        add.setEntrada(soma);
        listaMovimentoCaixa.add(add);
    }

    public void addSaida(ContasPagar c) {
        MovimentoCaixa add = new MovimentoCaixa();
        add.setDescricao(c.getDestinatario());
        add.setData(c.getDataPagamento());
        add.setSaida(c.getValor());
        listaMovimentoCaixa.add(add);
    }

    public BigDecimal somaSaida() {
        BigDecimal result = new BigDecimal(0);
        for (MovimentoCaixa m : listaMovimentoCaixa) {
            if (m.getSaida() != null) {
                result = result.add(m.getSaida());
            }
        }
        return result;
    }

    public BigDecimal somaEntrada() {
        BigDecimal result = new BigDecimal(0);
        for (MovimentoCaixa m : listaMovimentoCaixa) {
            if (m.getEntrada() != null) {
                result = result.add(m.getEntrada());
            }
        }
        return result;
    }

    public List<MovimentoCaixa> getListaMovimentoCaixa() {
        Collections.sort(listaMovimentoCaixa);
        return listaMovimentoCaixa;
    }

}
