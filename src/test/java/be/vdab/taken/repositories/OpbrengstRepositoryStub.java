package be.vdab.taken.repositories;

import java.math.BigDecimal;

public class OpbrengstRepositoryStub implements OpbrengstRepository{
    @Override
    public BigDecimal findTotaleOpbrengst() {
        return BigDecimal.valueOf(200);
    }
}
