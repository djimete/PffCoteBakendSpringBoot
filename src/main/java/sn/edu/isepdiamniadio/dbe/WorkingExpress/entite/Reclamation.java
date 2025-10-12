package sn.edu.isepdiamniadio.dbe.WorkingExpress.entite;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "reclamation")
public class Reclamation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReclamation", nullable = false)
    private Integer idReclamation;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "dateReclamation", nullable = false)
    private LocalDate dateReclamation;


    @Column(name = "statusReclamation", nullable = false)
    private String statusReclamation;
    @Override
    public String toString() {
        return "Reclamation{" +
                "idReclamation=" + idReclamation +
                ", message='" + message + '\'' +
                ", dateReclamation=" + dateReclamation +
                ", statusReclamation='" + statusReclamation + '\'' +
                '}';
    }
}
