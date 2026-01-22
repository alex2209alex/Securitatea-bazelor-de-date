package ro.unibuc.fmi.sbd.dto;

public class ModificareDateAngajatDto {
    private String nume;

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    private String prenume;
}
