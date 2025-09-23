package sn.edu.isepdiamniadio.dbe.WorkingExpress.entite;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Entity
@Table(name = "support")

public class Support {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSupport", nullable = false)
    private Integer idSupport;

    @Column(name = "typeSupport", nullable = false)
    private String typeSupport;

    @ManyToMany
    @JoinTable(
            name = "support_contact",
            joinColumns = @JoinColumn(name = "idSupport"),
            inverseJoinColumns = @JoinColumn(name = "idContact")
    )
    private List<sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Contact> contacts;

    @Override
    public String toString() {
        return "Support{" +
                "idSupport=" + idSupport +
                ", typeSupport='" + typeSupport + '\'' +
                ", contacts=" + contacts +
                '}';
    }
}
