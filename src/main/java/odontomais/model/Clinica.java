package odontomais.model;

import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Clinica implements Serializable {

    final static Logger logger = Logger.getLogger(Clinica.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String email;
    private String pathLogotipo;
    private String telComercial;
    private String telEmergencial;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;
    private LocalTime horarioInicioAlmoco;
    private LocalTime horarioFimAlmoco;
    private int intervaloAgenda;

    private String endereco;
    private String bairro;
    private String cidade;
    private String estado;

    public BufferedImage carregaLogotipo() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(this.pathLogotipo));
        } catch (IOException e) {
            logger.error("Erro ao carregar logotipo: " + e.getLocalizedMessage());
        }
        return image;
    }

    public LocalTime getHorarioInicioAlmoco() {
        return horarioInicioAlmoco;
    }

    public void setHorarioInicioAlmoco(LocalTime horarioInicioAlmoco) {
        this.horarioInicioAlmoco = horarioInicioAlmoco;
    }

    public LocalTime getHorarioFimAlmoco() {
        return horarioFimAlmoco;
    }

    public void setHorarioFimAlmoco(LocalTime horarioFimAlmoco) {
        this.horarioFimAlmoco = horarioFimAlmoco;
    }

    public int getIntervaloAgenda() {
        return intervaloAgenda;
    }

    public void setIntervaloAgenda(int intervaloAgenda) {
        this.intervaloAgenda = intervaloAgenda;
    }
    
    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalTime getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(LocalTime horarioFim) {
        this.horarioFim = horarioFim;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPathLogotipo() {
        return pathLogotipo;
    }

    public void setPathLogotipo(String pathLogotipo) {
        this.pathLogotipo = pathLogotipo;
    }

    public String getTelComercial() {
        return telComercial;
    }

    public void setTelComercial(String telComercial) {
        this.telComercial = telComercial;
    }

    public String getTelEmergencial() {
        return telEmergencial;
    }

    public void setTelEmergencial(String telEmergencial) {
        this.telEmergencial = telEmergencial;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
