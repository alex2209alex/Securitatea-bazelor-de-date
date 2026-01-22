package ro.unibuc.fmi.sbd.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "conturi",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "iban")
        }
)
public class Cont {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "conturi_gen"
    )
    @SequenceGenerator(
            name = "conturi_gen",
            sequenceName = "secventa_conturi",
            allocationSize = 1
    )
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "suma", nullable = false)
    private Double suma;

    @Enumerated(EnumType.STRING)
    @Column(name = "tip_cont", nullable = false, length = 10)
    private TipCont tipCont;

    @Column(name = "iban", nullable = false, length = 24)
    private String iban;

    @ManyToMany(mappedBy = "conturi", fetch = FetchType.LAZY)
    private List<Utilizator> utilizatori = new ArrayList<>();

    @OneToMany(
            mappedBy = "contUtilizator",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Credit> crediteLivrateInCont = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSuma() {
        return suma;
    }

    public void setSuma(Double suma) {
        this.suma = suma;
    }

    public TipCont getTipCont() {
        return tipCont;
    }

    public void setTipCont(TipCont tipCont) {
        this.tipCont = tipCont;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public List<Utilizator> getUtilizatori() {
        return utilizatori;
    }

    public void setUtilizatori(List<Utilizator> utilizatori) {
        this.utilizatori = utilizatori;
    }

    public List<Credit> getCrediteLivrateInCont() {
        return crediteLivrateInCont;
    }

    public void setCrediteLivrateInCont(List<Credit> crediteLivrateInCont) {
        this.crediteLivrateInCont = crediteLivrateInCont;
    }
}
