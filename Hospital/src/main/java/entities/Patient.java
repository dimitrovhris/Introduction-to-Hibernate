package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "patients")
public class Patient extends BaseEntity{

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

   @Column(name = "medical_insurance")
    private Boolean medicalInsurance;

   @OneToMany(mappedBy = "patient")
    private Set<Visitation> visitations;

   @OneToMany(mappedBy = "patient")
    private Set<Diagnose> diagnoses;

   @OneToMany (mappedBy = "patient")
    private Set<Medicament> medicaments;

    public Patient() {
        this.visitations = new HashSet<>();
        this.diagnoses = new HashSet<>();
        this.medicaments = new HashSet<>();
    }
    public void addVisitation(Visitation visitation){
        this.visitations.add(visitation);
    }
    public void addMedicament(Medicament medicament){
        this.medicaments.add(medicament);
    }
    public void addDiagnose(Diagnose diagnose){
        this.diagnoses.add(diagnose);
    }
}
