package sn.edu.isepdiamniadio.dbe.WorkingExpress.entite;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "compte")
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login", nullable = false, unique = true)
    private Long login; // ID numérique auto-incrémenté

    @Column(name = "mot_passe", nullable = false)
    private String motPasse;

    @Override
    public String toString() {
        return "Compte{" +
                "login=" + login +
                ", motPasse='" + motPasse + '\'' +
                '}';
    }
}
