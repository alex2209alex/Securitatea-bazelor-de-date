package ro.unibuc.fmi.sbd.dto;

import ro.unibuc.fmi.sbd.entity.TipCont;

public class ContDto {
    private Long id;
    private String iban;
    private Double suma;
    private TipCont tipCont;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public TipCont getTipCont() {
        return tipCont;
    }

    public void setTipCont(TipCont tipCont) {
        this.tipCont = tipCont;
    }
}
