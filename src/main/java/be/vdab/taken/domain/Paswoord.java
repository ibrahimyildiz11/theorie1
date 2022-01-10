package be.vdab.taken.domain;

import java.util.regex.Pattern;

public class Paswoord {
    private final static Pattern MINSTENS_1_KLEINE_LETTER =
            Pattern.compile("^.*[a-z]{1,}.*$");
    private final static Pattern MINSTENS_1_HOOFDLETTER =
            Pattern.compile("^.*[A-Z]{1,}.*$");
    private final static Pattern MINSTENS_1_CIJFER =
            Pattern.compile("^.*[0-9]{1,}.*$");
    private final String waarde;
    public Paswoord(String waarde) {
        if (waarde.length() < 8) {
            throw new IllegalArgumentException("Minstens 8 tekens");
        }
        if (!MINSTENS_1_KLEINE_LETTER.matcher(waarde).matches()){
            throw new IllegalArgumentException("Minstens 1 kleine letter");
        }
        if (!MINSTENS_1_HOOFDLETTER.matcher(waarde).matches()){
            throw new IllegalArgumentException("Minstens 1 hoofdletter");
        }
        if (!MINSTENS_1_CIJFER.matcher(waarde).matches()){
            throw new IllegalArgumentException("Minstens 1 cijfer");
        }
        this.waarde = waarde;
    }

    public String getPuntjes() {
        return ".".repeat(waarde.length());
    }

    public boolean komtOvereenMet(String paswoord) {
        return this.waarde.equals(paswoord);
    }
}
