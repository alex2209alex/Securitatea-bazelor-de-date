package ro.unibuc.fmi.sbd.dto;

import ro.unibuc.fmi.sbd.entity.StatusCredit;

public class CreditDto {
    private Long id;
    private String ibanUtilizator;
    private String ibanRestituire;
    private Double suma;
    private StatusCredit status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIbanUtilizator() {
        return ibanUtilizator;
    }

    public void setIbanUtilizator(String ibanUtilizator) {
        this.ibanUtilizator = ibanUtilizator;
    }

    public String getIbanRestituire() {
        return ibanRestituire;
    }

    public void setIbanRestituire(String ibanRestituire) {
        this.ibanRestituire = ibanRestituire;
    }

    public Double getSuma() {
        return suma;
    }

    public void setSuma(Double suma) {
        this.suma = suma;
    }

    public StatusCredit getStatus() {
        return status;
    }

    public void setStatus(StatusCredit status) {
        this.status = status;
    }
}
