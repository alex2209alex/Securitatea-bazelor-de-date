package ro.unibuc.fmi.sbd.dto;

public class CreareCreditDto {
    private String iban;
    private Double suma;

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Double getSuma() {
        return suma;
    }

    public void setSuma(Double suma) {
        this.suma = suma;
    }
}
