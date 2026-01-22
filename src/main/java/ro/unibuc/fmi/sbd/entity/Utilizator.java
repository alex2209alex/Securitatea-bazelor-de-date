package ro.unibuc.fmi.sbd.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@NamedStoredProcedureQuery(
        name = "Utilizator.creareCont",
        procedureName = "creare_cont",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "nume", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "prenume", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "cnp", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "username", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "parola", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "rol", type = String.class)
        }
)
@Entity
@Table(
        name = "utilizatori",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username")
        }
)
public class Utilizator {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "utilizatori_gen"
    )
    @SequenceGenerator(
            name = "utilizatori_gen",
            sequenceName = "secventa_utilizatori",
            allocationSize = 1
    )
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nume", nullable = false, length = 50)
    private String nume;

    @Column(name = "prenume", nullable = false, length = 50)
    private String prenume;

    @Column(name = "cnp")
    private byte[] cnp;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "hash_parola", nullable = false, length = 64)
    private String hashParola;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false, length = 10)
    private Rol rol;

    @OneToOne(
            mappedBy = "utilizator",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private CheieCriptareCNP cheieCriptareCNP;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "conturi_utilizatori",
            joinColumns = @JoinColumn(name = "id_utilizator"),
            inverseJoinColumns = @JoinColumn(name = "id_cont")
    )
    private List<Cont> conturi = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public byte[] getCnp() {
        return cnp;
    }

    public void setCnp(byte[] cnp) {
        this.cnp = cnp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashParola() {
        return hashParola;
    }

    public void setHashParola(String hashParola) {
        this.hashParola = hashParola;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public CheieCriptareCNP getCheieCriptareCNP() {
        return cheieCriptareCNP;
    }

    public void setCheieCriptareCNP(CheieCriptareCNP cheieCriptareCNP) {
        this.cheieCriptareCNP = cheieCriptareCNP;
    }

    public List<Cont> getConturi() {
        return conturi;
    }

    public void setConturi(List<Cont> conturi) {
        this.conturi = conturi;
    }
}