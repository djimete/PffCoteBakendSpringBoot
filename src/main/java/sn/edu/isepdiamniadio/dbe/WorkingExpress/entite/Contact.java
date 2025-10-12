package sn.edu.isepdiamniadio.dbe.WorkingExpress.entite;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Entity
@Table(name = "contact")

public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idContact", nullable = false)
    private Integer idContact;

    @Column(name = "contenu", nullable = false, columnDefinition = "TEXT")
    private String contenu;

    @Column(name = "dateContact", nullable = false)
    private LocalDate dateContact;

    @Column(name = "motif", nullable = false)
    private String motif;

    @Override
    public String toString() {
        return "Contact{" +
                "idContact=" + idContact +
                ", contenu='" + contenu + '\'' +
                ", dateContact=" + dateContact +
                ", motif='" + motif + '\'' +
                '}';
    }
}
