package sn.edu.isepdiamniadio.dbe.WorkingExpress.entite;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "express_service")
public class ExpressService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idService", nullable = false)
    private Integer idService;

    @Column(name = "nomService", nullable = false)
    private String nomService;

    @Column(name = "typeService", nullable = false)
    private String typeService;

    @Column(name = "description")
    private String description;

    @Override
    public String toString() {
        return "ExpressService{" +
                "idService=" + idService +
                ", nomService='" + nomService + '\'' +
                ", typeService='" + typeService + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
