public class Film
{
    private String nazev;
    private String reziser;
    private int rok;
    private String herci;
    private String hodnoceniCisla;
    private String hodnoceniSlova;

    public Film(String nazev, String reziser, int rok, String herci, String hodnoceniCisla, String hodnoceniSlova)
    {
        this.nazev = nazev;
        this.reziser = reziser;
        this.rok = rok;
        this.herci = herci;
        this.hodnoceniCisla = hodnoceniCisla;
        this.hodnoceniSlova = hodnoceniSlova;
    }

    public String getNazev() {
        return nazev;
    }

    public String getReziser() {
        return reziser;
    }

    public int getRok() {
        return rok;
    }

    public String getHerci() {
        return herci;
    }

    public String getHodnoceniCisla() {
        return hodnoceniCisla;
    }

    public String getHodnoceniSlova() {
        return hodnoceniSlova;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public void setReziser(String reziser) {
        this.reziser = reziser;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }

    public void setHerci(String herci) {
        this.herci = herci;
    }

    public void setHodnoceniCisla(String hodnoceniCisla) {
        this.hodnoceniCisla += hodnoceniCisla;
    }

    public void setHodnoceniSlova(String hodnoceniSlova) {
        this.hodnoceniSlova += hodnoceniSlova;
    }
}
