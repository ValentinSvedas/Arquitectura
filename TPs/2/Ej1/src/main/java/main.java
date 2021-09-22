import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class main {
    public static void main(String args[]){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
        EntityManager em =emf.createEntityManager();

        em.close();
        emf.close();

    }
}
