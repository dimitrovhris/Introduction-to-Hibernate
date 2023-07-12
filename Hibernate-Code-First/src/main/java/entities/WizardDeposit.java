package entities;

import org.hibernate.type.DateType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "wizard_deposits")
public class WizardDeposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 60)
    private String lastName;

    @Column(name = "notes", length = 100)
    private String notes;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "magic_wand_creator", length = 100)
    private String magicWandCreator;

    @Column(name = "magic_wand_size")
    private Integer magicWandSize;

    @Column(name = "deposit_group", length = 20)
    private String depositGroup;

    @Column(name = "deposit_start_date")
    private Date depositStartDate;

    @Column(name = "deposit_amount")
    private Double depositAmount;

    @Column(name = "deposit_interest")
    private Double depositInterest;

    @Column(name = "deposit_charge")
    private Double depositCharge;

    @Column(name = "depoit_expiration_date")
    private LocalDateTime depositExpirationDate;

    @Column(name = "is_deposit_expired")
    private Boolean isDepositExpired;


}
