package odontomais.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Convenio implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String numero;
    private boolean semValidade;

    public Convenio(String nome, String numero, LocalDate validade, boolean semValidade) {
        this.nome = nome;
        this.numero = numero;
        this.semValidade = semValidade;
    }

    public Convenio() {
    }


    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public boolean isSemValidade() {
        return semValidade;
    }

    public void setSemValidade(boolean semValidade) {
        this.semValidade = semValidade;
    }
}
