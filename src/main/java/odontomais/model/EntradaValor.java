/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odontomais.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author paulohar
 */
@Entity
public class EntradaValor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int tipoPagamento;
    private int idPaciente;
    private String nomePaciente;
    private String operacao;
    private Double valorPago;
    private int profissional;
    private String nomeProfissional;
    private Double taxaProfissional;
    private Double taxaClinica;
    private Double taxaLaboratorio;
    private LocalDate diaPagamento;
    private LocalDate diaProcessado;

}
