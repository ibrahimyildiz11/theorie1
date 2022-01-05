package be.vdab.theorie.domain;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;
public class ArtikelTest {
    @Test
    void prijsExclusief30enBtwPercentage6Moet31komma8PrijsInclusiefZijn() {
        var artikel = new Artikel(BigDecimal.valueOf(30), BigDecimal.valueOf(6));
        assertThat(artikel.getPrijsInclusiefBtw()).isEqualByComparingTo("31.8");
    }
}
