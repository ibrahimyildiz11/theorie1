package be.vdab.taken.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class PaswoordTest {
    @Test
    void Joske123IsEenCorrectPaswoord() {
        new Paswoord("Joske123");
    }
    @ParameterizedTest
    @ValueSource(strings = {"1234567", "1234567A", "1234567a", "AaBbCcDd"})
    void verkeerdPaswoord(String string) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Paswoord(string));
    }
    @Test
    void dePuntjesZijnEvenLangAlsHetPaswoord() {
        assertThat(new Paswoord("Joske123").getPuntjes()).isEqualTo("........");
    }
    @Test
    void eenPaswoordKomtOvereenMetZichZelf() {
        var string = "Joske123";
        assertThat(new Paswoord(string).komtOvereenMet(string)).isTrue();
    }
    @Test
    void nullIsEenOngeldigPaswoord() {
        assertThatNullPointerException().isThrownBy(() -> new Paswoord(null));
    }
}