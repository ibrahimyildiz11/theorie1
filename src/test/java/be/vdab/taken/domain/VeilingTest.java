package be.vdab.taken.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class VeilingTest {
    private Veiling veiling;
    @BeforeEach
    void beforeEach() {
        veiling = new Veiling();
    }
    @Test
    void hetHoogsteBodVanEenNieuweVeilingIs0€() {
        assertThat(veiling.getHoogsteBod()).isZero();
    }

    @Test
    void naEenBodVan10€IsHetHoogsteBod10€() {
        veiling.doeBod(BigDecimal.TEN);
        assertThat(veiling.getHoogsteBod()).isEqualByComparingTo("10");
    }

    @Test
    void naEenBodVan1€En10€En1€IsHetHoogsteBod10€() {
        veiling.doeBod(BigDecimal.ONE);
        veiling.doeBod(BigDecimal.TEN);
        veiling.doeBod(BigDecimal.ONE);

        assertThat(veiling.getHoogsteBod()).isEqualByComparingTo("10");
    }
}