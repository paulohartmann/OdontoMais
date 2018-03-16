package odontomais.model;

import odontomais.view.tabmod.TabConvenio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/*
 * Author: phlab
 * Date: 16/03/18
 */
@Entity
public class Tratamento implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String nome;

    public Tratamento(String nome) {
        this.nome = nome;
    }

    public Tratamento(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
