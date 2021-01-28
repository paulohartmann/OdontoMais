package odontomais.model.especial;

import java.math.BigDecimal;
import java.time.LocalDate;

/*
 * Author: phlab
 * Date: 11/12/18
 */
public class MovimentoCaixa implements Comparable<MovimentoCaixa> {

    private String descricao;
    private BigDecimal entrada;
    private BigDecimal saida;
    private LocalDate data;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getEntrada() {
        return entrada;
    }

    public void setEntrada(BigDecimal entrada) {
        this.entrada = entrada;
    }

    public BigDecimal getSaida() {
        return saida;
    }

    public void setSaida(BigDecimal saida) {
        this.saida = saida;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public int compareTo(MovimentoCaixa o) {
        if (this.data.isAfter(o.data)) {
            return -1;
        }
        if (this.data.isBefore(o.data)) {
            return 1;
        }
        return 0;
    }
}
