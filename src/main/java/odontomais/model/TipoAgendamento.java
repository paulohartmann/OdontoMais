package odontomais.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class TipoAgendamento implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String nome;

    public TipoAgendamento(String nome){
        this.nome = nome;
    }

    /*
     * Hibernate need a non-args constructor method
     */
    public TipoAgendamento() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
