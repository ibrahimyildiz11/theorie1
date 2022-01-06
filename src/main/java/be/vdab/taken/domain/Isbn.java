package be.vdab.taken.domain;

import java.util.Set;

public class Isbn {
    private static final long KLEINSTE_GETAL_MET_13_CIJFERS = 1000_000_000_000L;
    private static final long GROOTSTE_GETAL_MET_13_CIJFERS = 9999_999_999_999L;
    private static final Set<Short> MOGELIJKE_EERSTE_3_CIJFERS =
            Set.of((short) 978, (short) 979);
    private final long nummer;
    public Isbn (long nummer) {
        if (nummer < KLEINSTE_GETAL_MET_13_CIJFERS
                || nummer > GROOTSTE_GETAL_MET_13_CIJFERS) {
            throw new IllegalArgumentException("Bevat geen 13 cijfers");
        }
        var eerste3Cijfers = (short) (nummer / 10_000_000_000L);
        if (!MOGELIJKE_EERSTE_3_CIJFERS.contains(eerste3Cijfers)) {
            throw new IllegalArgumentException("Begint niet met " + MOGELIJKE_EERSTE_3_CIJFERS);
        }

        var somEvenCijfers = 0;
        var somOnEvenCijfers = 0;
        var teVerwerkenCijfers = nummer / 10;
        for (var teller = 0; teller != 6; teller++) {
            somEvenCijfers += teVerwerkenCijfers % 10;
            teVerwerkenCijfers /= 10;
            somOnEvenCijfers += teVerwerkenCijfers % 10;
            teVerwerkenCijfers /= 10;
        }

        var controleGetal = somEvenCijfers * 3 + somOnEvenCijfers;
        var naastGelegenHoger10Tal = controleGetal - controleGetal % 10 + 10;
        var verschil = naastGelegenHoger10Tal - controleGetal;
        var laatsteCijfer = nummer % 10;

        if (verschil == 10) {
            if (laatsteCijfer != 0) {
                throw new IllegalArgumentException("Verkeerd controlegetal");
            }
        }else {
            if (laatsteCijfer != verschil) {
                throw new IllegalArgumentException("Verkeerd controlegetal");
            }
        }
        this.nummer = nummer;
    }

    @Override
    public String toString() {
        return String.valueOf(nummer);
    }
}
