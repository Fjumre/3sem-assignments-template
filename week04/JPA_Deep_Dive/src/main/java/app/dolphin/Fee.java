package app.dolphin;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "fee")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Fee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id", nullable = false, unique = true)
    private int id;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "paydate", nullable = false)
    private LocalDate payDate;

    @ManyToOne
    private Person person;


    public Fee(double amount, LocalDate payDate) {
        this.amount = amount;
        this.payDate = payDate;
    }
}
