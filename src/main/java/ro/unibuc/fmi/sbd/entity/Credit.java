package ro.unibuc.fmi.sbd.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "credite")
public class Credit {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "credite_gen"
    )
    @SequenceGenerator(
            name = "credite_gen",
            sequenceName = "seq_credite",
            allocationSize = 1
    )
    private Long id;

    @Column(nullable = false)
    private Double suma;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cont_utilizator", nullable = false)
    private Cont contUtilizator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cont_restituire")
    private Cont contRestituire;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private StatusCredit status;

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

    public Cont getContUtilizator() {
        return contUtilizator;
    }

    public void setContUtilizator(Cont contUtilizator) {
        this.contUtilizator = contUtilizator;
    }

    public Cont getContRestituire() {
        return contRestituire;
    }

    public void setContRestituire(Cont contRestituire) {
        this.contRestituire = contRestituire;
    }

    public StatusCredit getStatus() {
        return status;
    }

    public void setStatus(StatusCredit status) {
        this.status = status;
    }
}
