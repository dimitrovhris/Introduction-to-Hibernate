package models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private BigDecimal balance;

    @ManyToOne
    private User user;


    public Account() {
        balance = BigDecimal.ZERO;
    }
}
