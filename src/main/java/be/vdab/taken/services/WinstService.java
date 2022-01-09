package be.vdab.taken.services;

import be.vdab.taken.repositories.KostRepository;
import be.vdab.taken.repositories.OpbrengstRepository;

import java.math.BigDecimal;

public class WinstService {
    private final OpbrengstRepository opbrengstRepository;
    private final KostRepository kostRepository;
    public WinstService (OpbrengstRepository opbrengstRepository, KostRepository kostRepository) {
        this.opbrengstRepository = opbrengstRepository;
        this.kostRepository = kostRepository;
    }
    public BigDecimal getWinst() {
        return opbrengstRepository.findTotaleOpbrengst()
                .subtract(kostRepository.findTotaleKost());
    }
}
