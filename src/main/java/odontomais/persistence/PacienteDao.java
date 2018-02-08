package odontomais.persistence;

import java.util.List;
import odontomais.model.Paciente;
import odontomais.persistence.jpa.GenericDAO;

import javax.persistence.Query;

public class PacienteDao extends GenericDAO<Paciente, Long> {

    public PacienteDao() {
        super(Paciente.class);
    }

    public Paciente findFromCPF(String cpf) {
        Paciente resultado = null;
        String consulta = "SELECT c FROM Paciente c WHERE "
                + "c.cpf = :param";
        try {
            Query query = criarQuery(consulta);
            query.setParameter("param", cpf);
            resultado = (Paciente) query.getSingleResult();
        } catch (Exception rx) {

        }
        return resultado;
    }
    
    public Paciente findFromRG(String rg) {
        Paciente resultado = null;
        String consulta = "SELECT c FROM Paciente c WHERE "
                + "c.rg = :param";
        try {
            Query query = criarQuery(consulta);
            query.setParameter("param", rg);
            resultado = (Paciente) query.getSingleResult();
        } catch (Exception rx) {

        }
        return resultado;
    }

    public List<Paciente> findFromNome(String nome) {
        List<Paciente> resultado = null;
        String consulta = "SELECT c FROM Paciente c WHERE "
                + "c.nomeCompleto = :param";
        try {
            Query query = criarQuery(consulta);
            query.setParameter("param", nome + "%");
            resultado = (List<Paciente>) query.getResultList();
        } catch (Exception rx) {

        }
        return resultado;
    }

    public int findExisteByCPF(String param) {
        int resultado = 0;
        String consulta = "SELECT c FROM Paciente c WHERE "
                + "c.cpf = :param";
        try {
            Query query = criarQuery(consulta);
            query.setParameter("param", param);
            resultado = (int) query.getFirstResult();
        } catch (Exception rx) {

        }
        return resultado;
    }

    public int findExisteByRG(String param) {
        int resultado = 0;
        String consulta = "SELECT c FROM Paciente c WHERE "
                + "c.rg = :param";
        try {
            Query query = criarQuery(consulta);
            query.setParameter("param", param);
            resultado = (int) query.getFirstResult();
        } catch (Exception rx) {

        }
        return resultado;
    }
}
