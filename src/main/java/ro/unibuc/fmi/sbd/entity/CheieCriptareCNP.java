package ro.unibuc.fmi.sbd.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "chei_criptari_cnpuri")
public class CheieCriptareCNP {
    @Id
    @Column(name = "id_utilizator")
    private Long idUtilizator;

    @OneToOne
    @MapsId
    @JoinColumn(
            name = "id_utilizator",
            foreignKey = @ForeignKey(name = "fk_id_utilizator")
    )
    private Utilizator utilizator;

    @Column(name = "cheie_criptare", nullable = false)
    private byte[] cheieCriptare;

    @Column(name = "vector_initializare", nullable = false)
    private byte[] vectorInitializare;

    public Long getIdUtilizator() {
        return idUtilizator;
    }

    public void setIdUtilizator(Long idUtilizator) {
        this.idUtilizator = idUtilizator;
    }

    public Utilizator getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
    }

    public byte[] getCheieCriptare() {
        return cheieCriptare;
    }

    public void setCheieCriptare(byte[] cheieCriptare) {
        this.cheieCriptare = cheieCriptare;
    }

    public byte[] getVectorInitializare() {
        return vectorInitializare;
    }

    public void setVectorInitializare(byte[] vectorInitializare) {
        this.vectorInitializare = vectorInitializare;
    }
}
