package be.vdab.theorie.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PeriodeTest {
    private LocalDate datum20190101;
    private LocalDate datum20190109;
    private Periode periode1;
    private Periode periode2;

    @BeforeEach
    void beforeEach() {
        datum20190101 = LocalDate.of(2019,1,1);
        datum20190109 = LocalDate.of(2019,1,9);
        periode1 = new Periode(datum20190101, datum20190109);
        periode2 = new Periode(LocalDate.of(2019,2,1), LocalDate.of(2019,2,9));
    }

    @Test
    void vanMoetIngevuldZijn() {
        assertThatNullPointerException().isThrownBy(
                () -> new Periode(null, datum20190101));
    }

    @Test
    void totMoetIngevuldZijn() {
        assertThatNullPointerException().isThrownBy(
                () -> new Periode(datum20190101, null));
    }

    @Test
    void totMoetGroeterOfGelijkZijnAanVan() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new Periode(datum20190109, datum20190101));
    }

    @Test
    void eenPeriodeVanEenDagIsOK() {
        new Periode(datum20190101, datum20190101);
    }

    @Test
    void periodeInKomtNaMoetIngevuldZijn() {
        assertThatNullPointerException().isThrownBy(() -> periode1.komtNa(null));
    }
    @Test
    void eenPeriodeKomtNaEenPeriodeDieOuderIs() {
        assertThat(periode2.komtNa(periode1)).isTrue();
    }
    @Test
    void eenPeriodeKomtNietNaEenPeriodeDieJongerIs() {
        assertThat(periode1.komtNa(periode2)).isFalse();
    }

    @Test
    void eenPeriodeKomtNietNaEenPeriodeMetGelijkeVans() {
        assertThat(new Periode(LocalDate.of(2019,1,1), LocalDate.of(2019,1,10))
                .komtNa(periode1)).isFalse();
    }
    @Test
    void periodeInOverlaptMetMoetIngevuldZijn() {
        assertThatNullPointerException().isThrownBy(
                () -> periode1.overlaptMet(null));
    }
    @Test
    void eenPeriodeOverlaptMetZichzelf() {
        assertThat(periode1.overlaptMet(periode1)).isTrue();
    }

    @Test
    void eenPeriodeOverlaptNietMetEenPeriodeNaDeEerstePeriode() {
        assertThat(periode1.overlaptMet(periode2)).isFalse();
    }
    @Test
    void eenPeriodeOverlaptNietMetEenPeriodeVoorDeEerstePeriode() {
        assertThat(periode2.overlaptMet(periode1)).isFalse();
    }
    @Test
    void eenPeriodeOverlaptMetEenPeriodeMetEenVanInDeEerstePeriode() {
        assertThat(periode1.overlaptMet(new Periode(
                LocalDate.of(2019, 1, 2), LocalDate.of(2019, 1, 15)))).isTrue();
    }
    @Test
    void eenPeriodeOverlaptMetEenPeriodMetEenTotInDeEerstePeriode() {
        assertThat(periode1.overlaptMet(new Periode(
                LocalDate.of(2018, 12, 31), LocalDate.of(2019, 1, 5)))).isTrue();
    }
    @Test
    void eenPeriodeOverlaptMetEenPeriodeDieHelemaalInDeEerstePeriodeLigt() {
        assertThat(periode1.overlaptMet(new Periode(
                LocalDate.of(2019, 1, 2), LocalDate.of(2019, 1, 3)))).isTrue();
    }
    @Test
    void eenPeriodeOverlaptMetEenPeriodeMetGelijkeVans() {
        assertThat(periode1.overlaptMet(new Periode(
                LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 10)))).isTrue();
    }
    @Test
    void eenPeriodeOverlaptMetEenPeriodeMetGelijkeTots() {
        assertThat(periode1.overlaptMet(new Periode(
                LocalDate.of(2018, 12, 31), LocalDate.of(2019, 1, 9)))).isTrue();
    }

}