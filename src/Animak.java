public class Animak extends Film{
    private int vekDivaka;
    Animak(String nazev, String reziser, int rok, String animatori, String hodnoceniCisla, String hodnoceniSlova, int vekDivaka)
    {
        super(nazev, reziser, rok, animatori, hodnoceniCisla, hodnoceniSlova);
        this.vekDivaka = vekDivaka;
    }
    public int getVekDivaka() {
        return vekDivaka;
    }

    public void setVekDivaka(int vekDivaka) {
        this.vekDivaka = vekDivaka;
    }
}
