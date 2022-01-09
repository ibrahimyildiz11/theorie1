package be.vdab.taken.domain;
import java.util.StringTokenizer;
public class WoordTeller {
    public int telWoorden(String zin) {
        return new StringTokenizer(zin, " ,").countTokens();
    }
}
