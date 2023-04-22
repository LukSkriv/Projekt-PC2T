public class DuplicitniHerci
{
    private String jmeno;
    private String indexy;
    DuplicitniHerci(String jmeno)
    {
        this.jmeno = jmeno;
        this.indexy = "0";
    }
    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getIndexy() {
        return indexy;
    }

    public void setIndexy(String indexy) {
        this.indexy += indexy;
    }
    public void resetIndexy(){this.indexy = "0";}
}
