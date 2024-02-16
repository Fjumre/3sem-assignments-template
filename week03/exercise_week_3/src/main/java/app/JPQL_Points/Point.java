package app.JPQL_Points;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "point")
@Entity
public class Point {

    @Id // Marks the id field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures the way ID is generated
    @Column(name="id", nullable = false, unique = true)
    long id;
    @Column(name = "x")
    int x;
    @Column(name = "y")
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
