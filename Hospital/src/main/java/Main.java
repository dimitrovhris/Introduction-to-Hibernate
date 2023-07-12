import entities.Patient;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ParseException {
        EntityManager entityManager = Persistence.createEntityManagerFactory("hospital")
                .createEntityManager();
        entityManager.getTransaction().begin();
        Patient patient1 = new Patient();
        patient1.setFirstName("Hristiyan");
        patient1.setLastName("Dimitrov");
        patient1.setAddress("Bul. Knyaz Alexander Batenberg 85 f.3 ap.11");
        patient1.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2006-03-14"));
        patient1.setPhoneNumber("0885866409");
        patient1.setMedicalInsurance(true);

        entityManager.persist(patient1);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}