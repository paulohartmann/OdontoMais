package odontomais.model;

import odontomais.view.tabmod.TabConvenio;

import javax.persistence.*;
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

    public Tratamento(){}

    public Tratamento(String nome){
        this.nome = nome;
    }

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
