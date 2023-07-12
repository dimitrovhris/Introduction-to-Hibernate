import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("university")
                .createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.getTransaction().commit();
    }
}