package be.vdab.taken.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assertions.assertThat;

class WoordTellerTest {
    private WoordTeller woordTeller;
    @BeforeEach
    void beforeEach() {
        woordTeller = new WoordTeller();
    }

    @Test
    void eenNullWaardeKanNiet() {
        assertThatNullPointerException()
                .isThrownBy(() -> woordTeller.telWoorden(null));
    }
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", ",", ",,,", ", ,, "})
    void bevat0Woorden(String tekst) {
        assertThat(woordTeller.telWoorden(tekst)).isZero();
    }

    @ParameterizedTest
    @ValueSource(strings = {"woord", " woord", "woord "})
    void bevat1woord(String tekst) {
        assertThat(woordTeller.telWoorden(tekst)).isOne();
    }

    @ParameterizedTest
    @ValueSource(strings = {"ik ook", "ik  ook", "ik,ook", "ik, ook", "ik , ook"})
    void bevat2Woorden(String tekst) {
        assertThat(woordTeller.telWoorden(tekst)).isEqualTo(2);
    }

}