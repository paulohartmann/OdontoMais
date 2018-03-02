package odontomais.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Paciente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nomeCompleto;
    private LocalDate dataNascimento;
    private String sexo;
    private String email;
    private String cpf;
    private String rg;

    private String endResidencial;
    private String bairro;
    private String cidade;
    private String estado;

    //TODO: telRes realmente precisa?
    private String telRes;
    private String telCel;
    private String telTrab;
    private String profissao;
    //TODO: apenas um convênio por paciente?

    @ManyToOne(cascade=CascadeType.MERGE)
    private Convenio convenio;

    private boolean tratamentoMedicoRecente;
    private String medicamentosRecorrentes;
    private String alergias;

    public Paciente(String nomeCompleto, LocalDate dataNascimento, String sexo, String endResidencial,
                    String bairro, String cidade, String telRes, String telCel, String telTrab, String profissao,
                    Convenio convenio, String cpf, String rg, String email) {
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.endResidencial = endResidencial;
        this.bairro = bairro;
        this.cidade = cidade;
        this.telRes = telRes;
        this.telCel = telCel;
        this.telTrab = telTrab;
        this.profissao = profissao;
        this.convenio = convenio;
        this.cpf = cpf;
        this.rg = rg;
        this.email = email;
    }

    public Paciente() {
        convenio = new Convenio();
    }

    public long getId() {
        return id;
    }


    //problemas de saúde, separados por vírgula
    private String problemasSaude;

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getTelTrab() {
        return telTrab;
    }

    public void setTelTrab(String telTrab) {
        this.telTrab = telTrab;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEndResidencial() {
        return endResidencial;
    }

    public void setEndResidencial(String endResidencial) {
        this.endResidencial = endResidencial;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTelRes() {
        return telRes;
    }

    public void setTelRes(String telRes) {
        this.telRes = telRes;
    }

    public String getTelCel() {
        return telCel;
    }

    public void setTelCel(String telCel) {
        this.telCel = telCel;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }


    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getProblemasSaude() {
        return problemasSaude;
    }

    public void setProblemasSaude(String problemasSaude) {
        this.problemasSaude = problemasSaude;
    }

    public boolean isTratamentoMedicoRecente() {
        return tratamentoMedicoRecente;
    }

    public void setTratamentoMedicoRecente(boolean tratamentoMedicoRecente) {
        this.tratamentoMedicoRecente = tratamentoMedicoRecente;
    }

    public String getMedicamentosRecorrentes() {
        return medicamentosRecorrentes;
    }

    public void setMedicamentosRecorrentes(String medicamentosRecorrentes) {
        this.medicamentosRecorrentes = medicamentosRecorrentes;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

