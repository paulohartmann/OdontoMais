package odontomais.persistence.jpa;

import org.apache.log4j.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    final static Logger logger = Logger.getLogger(JPAUtil.class);

    private static final String PERSISTENCE_UNIT_NAME = "persistence-odontomais";
    private static EntityManagerFactory factory;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (factory == null) {
            try {
                factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            }catch(NullPointerException ex){
                logger.error("não foi possivel abrir conexão: " + ex.fillInStackTrace());
            }
        }
        return factory;
    }

    public static void shutdown() {
        if (factory != null) {
            factory.close();
        }
    }
}
