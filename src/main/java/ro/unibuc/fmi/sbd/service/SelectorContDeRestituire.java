package ro.unibuc.fmi.sbd.service;

import ro.unibuc.fmi.sbd.entity.Cont;

import java.security.SecureRandom;
import java.util.List;

public class SelectorContDeRestituire {
    public static Cont selectContRandom(List<Cont> conturi) {
        if (conturi == null || conturi.isEmpty()) {
            throw new RuntimeException("Nu exista conturi interne");
        }
        SecureRandom random = new SecureRandom();
        int index = random.nextInt(conturi.size());
        return conturi.get(index);
    }

    private SelectorContDeRestituire() {
    }
}
