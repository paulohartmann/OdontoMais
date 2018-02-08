package odontomais.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Profissional implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private LocalDate horarioInicio;
    private LocalDate horarioFim;
    private LocalDate horarioAlmocoInicio;
    private LocalDate horarioAlmocoFim;
    //TODO: forma de mostrar dias de atendimento
    private int diasServico;
    private String observacao;

    public int montaInteiroDiasServico(boolean[] bits) {
        int n = 0, l = bits.length;
        for (int i = 0; i < l; ++i) {
            n = (n << 1) + (bits[i] ? 1 : 0);
        }
        return n;
    }

    public boolean[] montaArrayDiasServico() {
        boolean[] bits = new boolean[7];
        for (int i = 6; i >= 0; i--) {
            bits[i] = (diasServico & (1 << i)) != 0;
        }
        return bits;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalDate horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalDate getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(LocalDate horarioFim) {
        this.horarioFim = horarioFim;
    }

    public LocalDate getHorarioAlmocoInicio() {
        return horarioAlmocoInicio;
    }

    public void setHorarioAlmocoInicio(LocalDate horarioAlmocoInicio) {
        this.horarioAlmocoInicio = horarioAlmocoInicio;
    }

    public LocalDate getHorarioAlmocoFim() {
        return horarioAlmocoFim;
    }

    public void setHorarioAlmocoFim(LocalDate horarioAlmocoFim) {
        this.horarioAlmocoFim = horarioAlmocoFim;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getDiasServico() {
        return diasServico;
    }

    public void setDiasServico(int diasServico) {
        this.diasServico = diasServico;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
