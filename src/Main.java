import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int pouzeCelaCisla(Scanner sc) {
        int cislo = 0;
        try {
            cislo = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Nastala vyjimka typu " + e.toString());
            System.out.println("zadejte prosim cele cislo ");
            sc.nextLine();
            cislo = pouzeCelaCisla(sc);
        }
        return cislo;
    }
    public static List<Film> filmyHrane = new ArrayList<Film>();
    public static List<Film> filmyAnim = new ArrayList<Film>();
    public static List<DuplicitniHerci> herciList = new ArrayList<DuplicitniHerci>();
    public static List<DuplicitniHerci> duplicitniHerciList = new ArrayList<DuplicitniHerci>();
    public static String[] hranyFilm = new String[6];
    public static String[] animovanyFilm = new String[7];
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] tempHrany = new String[4]; //na uložení dat když je uživatel zadává
        String[] tempAnim = new String[5];

        Soubory soubor = new Soubory();
        int vyberAkce;
        boolean run = true;
        DuplicitniHerci párek = new DuplicitniHerci("Antonín Dvořák");
        herciList.add(párek);
        duplicitniHerciList.add(párek);
        while (run) {
            System.out.println("Možné akce:");
            System.out.println("1 .. Přidání nového filmu");
            System.out.println("2 .. Upravení filmu");
            System.out.println("3 .. Smazání filmu");
            System.out.println("4 .. Přidání hodnocení danému filmu");
            System.out.println("5 .. Výpis filmů");
            System.out.println("6 .. Vyhledání filmu");
            System.out.println("7 .. Výpis herců/animátorů, co se podíleli na více filmech");
            System.out.println("8 .. Výpis filmů podle herce/animátora");
            System.out.println("9 .. Uložení daného filmu do souboru");
            System.out.println("10 .. Načtení filmu ze souboru");
            System.out.println("11 .. ukonceni aplikace");
            vyberAkce = pouzeCelaCisla(sc);
            switch (vyberAkce) {
                case 1: // přidání filmu
                    System.out.println("1 .. Hraný film");
                    System.out.println("2 .. Animovaný film");
                    vyberAkce = pouzeCelaCisla(sc);
                    if(vyberAkce == 1)
                    {
                        System.out.println("Zadejte");
                        System.out.println("Název:");
                        tempHrany[0] = reader.readLine();
                        System.out.println("Režisér:");
                        tempHrany[1] = reader.readLine();
                        System.out.println("Rok vydání:");
                        tempHrany[2] = reader.readLine();
                        System.out.println("Herci (oddělení čárkou):");
                        tempHrany[3] = reader.readLine();
                        Film film = new Film(tempHrany[0],tempHrany[1],Integer.parseInt(tempHrany[2]),tempHrany[3],"0","");
                        filmyHrane.add(film);


                        String[] herciTohoFilmu = tempHrany[3].split(", ", -1);
                        int idx = filmyHrane.size()-1;
                        int delkaListu = herciList.size();
                        int delkaDuplicit = duplicitniHerciList.size();
                        boolean existuje = false;
                        boolean existujeVubec = false;
                        for(int j = 0; j < herciTohoFilmu.length; j++)
                        {
                            existuje = false;
                            existujeVubec = false;
                            for (int i = 0; i < delkaListu; i++)
                            {
                                if(herciList.get(i).getJmeno().equals(herciTohoFilmu[j])) {//pokud se už herec nachází v seznamu herců, přidá se do seznamu duplicitních
                                    for(int k = 0; k<delkaDuplicit;k++)
                                    {
                                        if(duplicitniHerciList.get(k).getJmeno().equals(herciTohoFilmu[j]))
                                        {
                                            duplicitniHerciList.get(k).setIndexy(" " + Integer.toString(idx));
                                            existuje = true;
                                            existujeVubec = true;
                                        }
                                    }
                                    if(!existuje)
                                    {
                                        duplicitniHerciList.add(herciList.get(i));
                                        duplicitniHerciList.get(duplicitniHerciList.size() - 1).setIndexy(" " + Integer.toString(idx));
                                        existuje = false;
                                        existujeVubec = true;
                                    }
                                }
                            }
                            if(!existujeVubec) {
                                DuplicitniHerci fachman = new DuplicitniHerci(herciTohoFilmu[j]);
                                fachman.setIndexy(" " + Integer.toString(idx));
                                herciList.add(fachman);
                            }
                        }
                    }
                    else
                    {
                        //napsat pridani animaku
                    }
                    break;
                case 2: //upravení filmu
                    System.out.println("Zadejte název filmu, který chcete upravit: ");
                    String jmenoFilmu = sc.next();
                    boolean Hrany = false;
                    int index = -1;
                    for(int i = 0; i <= filmyHrane.size()-1;i++)
                    {//projde nejdřív hrané filmy, potom animované, když nenajde ani tam, skončí
                        if(jmenoFilmu.equals(filmyHrane.get(i).getNazev())) {
                            Hrany = true;
                            index = i;
                        }
                    }
                    if(Hrany != true)
                    {
                        for(int i = 0; i <= filmyAnim.size()-1;i++)
                        {
                            if(jmenoFilmu.equals(filmyAnim.get(i).getNazev())) {
                                index = i;
                            }
                        }
                    }
                    if(index == -1) {//pokud to nenašlo stejné jméno ve hraných, ani animovaných
                        System.out.println("Film s tímto jménem neexistuje");
                        break;
                    }


                    else
                    {
                        if(Hrany)
                        {//pro hrané filmy
                            System.out.println("Vybraný film: " + filmyHrane.get(index).getNazev() + " " + filmyHrane.get(index).getReziser()
                                    + " " + Integer.toString(filmyHrane.get(index).getRok()) + " " + filmyHrane.get(index).getHerci());
                            System.out.println("Zadejte");
                            System.out.println("Nový název: ");
                            filmyHrane.get(index).setNazev(sc.next());
                            System.out.println("Nového režiséra: ");
                            filmyHrane.get(index).setReziser(sc.next());
                            System.out.println("Nový rok vydání: ");
                            filmyHrane.get(index).setRok(pouzeCelaCisla(sc));
                            System.out.println("Nový seznam herců (odděleni čárkou): ");
                            filmyHrane.get(index).setHerci(sc.next());
                            break;
                        }
                        else
                        {//pro animované filmy
                            System.out.println("Vybraný film: " + filmyAnim.get(index).getNazev() + " " + filmyAnim.get(index).getReziser()
                                    + " " + Integer.toString(filmyAnim.get(index).getRok()) + " " + filmyAnim.get(index).getHerci());
                            System.out.println("Zadejte");
                            System.out.println("Nový název: ");
                            filmyAnim.get(index).setNazev(sc.next());
                            System.out.println("Nového režiséra: ");
                            filmyAnim.get(index).setReziser(sc.next());
                            System.out.println("Nový rok vydání: ");
                            filmyAnim.get(index).setRok(pouzeCelaCisla(sc));
                            System.out.println("Nový seznam herců (odděleni čárkou): ");
                            filmyAnim.get(index).setHerci(sc.next());
                            break;
                        }
                    }
                case 3: //smazání filmu
                    System.out.println("Zadejte název filmu, který chcete smazat: ");
                    jmenoFilmu = sc.next();
                    Hrany = false;
                    index = -1;
                    for(int i = 0; i <= filmyHrane.size()-1;i++)
                    {
                        if(jmenoFilmu.equals(filmyHrane.get(i).getNazev())) {
                            Hrany = true;
                            index = i;
                        }
                    }
                    if(Hrany != true)
                    {
                        for(int i = 0; i <= filmyAnim.size()-1;i++)
                        {
                            if(jmenoFilmu.equals(filmyHrane.get(i).getNazev())) {
                                index = i;
                            }
                        }
                    }
                    if(index == -1) {//pokud to nenašlo stejné jméno ve hraných, ani animovaných
                        System.out.println("Film s tímto jménem neexistuje");
                        break;
                    }


                    if(Hrany)
                    {
                        String[] herciTohoFilmu = filmyHrane.get(index).getHerci().split(", ", -1);
                        for(int i = 0; i < herciList.size(); i++)
                        {
                            for(int j = 0; j < herciTohoFilmu.length; j++) {
                                if (herciList.get(i).getJmeno().equals(herciTohoFilmu[j]))
                                {
                                    String[] indexyRetezec = herciList.get(i).getIndexy().split(" ", -1);
                                    int[] jednotliveIndexy = new int[indexyRetezec.length];
                                    for(int l = 0; l < indexyRetezec.length; l++)
                                        jednotliveIndexy[l] = Integer.parseInt(indexyRetezec[l]);
                                    for(int k = 0; k < jednotliveIndexy.length; k++)
                                    {
                                        if(jednotliveIndexy[k] == index)
                                        {
                                            jednotliveIndexy[k] = -1;
                                        } else if (jednotliveIndexy[k] > index) {
                                            jednotliveIndexy[k]--;
                                        }
                                    }
                                    herciList.get(i).resetIndexy();
                                    for(int o = 0; o < jednotliveIndexy.length; o++)
                                    {
                                        if(jednotliveIndexy[o] != -1)
                                        {
                                            herciList.get(i).setIndexy(Integer.toString(jednotliveIndexy[o]));
                                        }
                                    }
                                }
                            }

                        }
                        filmyHrane.remove(index);
                    }
                    else
                    {
                        filmyAnim.remove(index);
                    }
                    break;
                case 4: //přidání hodnocení filmu
                    System.out.println("Zadejte název filmu, kterému chcete přidat hodnocení: ");
                    jmenoFilmu = sc.next();
                    Hrany = false;
                    index = -1;
                    for(int i = 0; i <= filmyHrane.size()-1;i++)
                    {
                        if(jmenoFilmu.equals(filmyHrane.get(i).getNazev())) {
                            Hrany = true;
                            index = i;
                        }
                    }
                    if(Hrany != true)
                    {
                        for(int i = 0; i <= filmyAnim.size()-1;i++)
                        {
                            if(jmenoFilmu.equals(filmyHrane.get(i).getNazev())) {
                                index = i;
                            }
                        }
                    }
                    if(index == -1) {//pokud to nenašlo stejné jméno ve hraných, ani animovaných
                        System.out.println("Film s tímto jménem neexistuje");
                        break;
                    }


                    if(Hrany) {
                        System.out.println("Zadejte hodnocení od 1-5: ");
                        int hodnoceni = sc.nextInt();
                        if (hodnoceni >= 1 && hodnoceni <= 5)
                        {
                            filmyHrane.get(index).setHodnoceniCisla(" "+Integer.toString(hodnoceni));
                        }
                        else
                        {
                            System.out.println("Neplatná hodnota");
                            break;
                        }
                    }
                    else
                    {
                        System.out.println("Zadejte hodnocení od 1-10: ");
                        int hodnoceni = sc.nextInt();
                        if (hodnoceni >= 1 && hodnoceni <= 10)
                        {
                            filmyAnim.get(index).setHodnoceniCisla(" " + Integer.toString(hodnoceni));
                        }
                        else
                        {
                            System.out.println("Neplatná hodnota");
                            break;
                        }
                    }
                    System.out.println("Chcete přidat i slovní hodnocení? ano - 1, ne - 0");
                    if(pouzeCelaCisla(sc) == 1)
                    {
                        System.out.println("Zadejte slovní hodnocení: ");
                        String hodn = reader.readLine();
                        if(Hrany)
                        {
                            filmyHrane.get(index).setHodnoceniSlova(hodn);
                        }
                        else
                        {
                            filmyAnim.get(index).setHodnoceniSlova(hodn);
                        }
                    }
                    break;

                case 5: //výpis filmů
                    System.out.println("Seznam filmů v databázi (název, režisér, rok vydání, herci): ");
                    for(int i = 0; i <= filmyHrane.size()-1;i++)
                    {
                        System.out.println(filmyHrane.get(i).getNazev()+" "+filmyHrane.get(i).getReziser()+" "
                                +Integer.toString(filmyHrane.get(i).getRok())+" "+filmyHrane.get(i).getHerci()+" ");
                    }
                    for(int i = 0; i <= filmyAnim.size()-1;i++)
                    {
                        System.out.println(filmyAnim.get(i).getNazev()+" "+filmyAnim.get(i).getReziser()+" "
                                +Integer.toString(filmyAnim.get(i).getRok())+" "+filmyAnim.get(i).getHerci()+" "+"doporučený věk: ");
                    }
                    break;
                case 6: //vyhledání filmu
                    System.out.println("Zadejte název filmu, který chcete najít: ");
                    jmenoFilmu = sc.next();
                    Hrany = false;
                    index = -1;
                    for(int i = 0; i <= filmyHrane.size()-1;i++)
                    {
                        if(jmenoFilmu.equals(filmyHrane.get(i).getNazev())) {
                            Hrany = true;
                            index = i;
                        }
                    }
                    if(Hrany != true)
                    {
                        for(int i = 0; i <= filmyAnim.size()-1;i++)
                        {
                            if(jmenoFilmu.equals(filmyHrane.get(i).getNazev())) {
                                index = i;
                            }
                        }
                    }
                    if(index == -1) {//pokud to nenašlo stejné jméno ve hraných, ani animovaných
                        System.out.println("Film s tímto jménem neexistuje");
                        break;
                    }


                    //seřazení hodnocení
                    if(Hrany)
                    {
                        String Hodnoceni = filmyHrane.get(index).getHodnoceniCisla();
                        String[] jednotlivaHodnoceni = filmyHrane.get(index).getHodnoceniCisla().split(" ", -1);
                        int[] hodnoceni = new int[jednotlivaHodnoceni.length];
                        for (int i = 0; i < jednotlivaHodnoceni.length; i++) {
                            hodnoceni[i] = Integer.parseInt(jednotlivaHodnoceni[i]);
                        }
                        Arrays.sort(hodnoceni);
                        String vyslednaHodnoceni = "";
                        for(int i = hodnoceni.length-1; i > 0; i--)
                            vyslednaHodnoceni += Integer.toString(hodnoceni[i]) + " ";

                        //výpis informací o filmu
                        System.out.println("Název: " + filmyHrane.get(index).getNazev());
                        System.out.println("Režisér: " + filmyHrane.get(index).getReziser());
                        System.out.println("Rok vydání: " + filmyHrane.get(index).getRok());
                        System.out.println("Herci: " + filmyHrane.get(index).getHerci());
                        System.out.println("Hodnocení: " + vyslednaHodnoceni);
                        System.out.println(filmyHrane.get(index).getHodnoceniSlova());
                    }
                    else
                    {
                        String[] jednotlivaHodnoceni = filmyAnim.get(index).getHodnoceniCisla().split(" ");
                        int[] hodnoceni = new int[jednotlivaHodnoceni.length];
                        for (int i = 0; i < jednotlivaHodnoceni.length; i++) {
                            hodnoceni[i] = Integer.parseInt(jednotlivaHodnoceni[i]);
                        }
                        Arrays.sort(hodnoceni);
                        String vyslednaHodnoceni = "";
                        for(int i = hodnoceni.length-1; i > 0; i--)
                            vyslednaHodnoceni += Integer.toString(hodnoceni[i]) + " ";

                        //výpis informací o filmu
                        System.out.println("Název: " + filmyAnim.get(index).getNazev());
                        System.out.println("Režisér: " + filmyAnim.get(index).getReziser());
                        System.out.println("Rok vydání: " + filmyAnim.get(index).getRok());
                        System.out.println("Animátoři: " + filmyAnim.get(index).getHerci());
                        System.out.println("Hodnocení: " + vyslednaHodnoceni);
                        System.out.println(filmyAnim.get(index).getHodnoceniSlova());
                    }
                    break;
                case 7: //výpis herců/animátorů, co se vyskytují ve více filmech
                    System.out.println("Všichni herci/animátoři, kteří se podíleli na dvou a více filmech: ");
                    for(int i = 0; i < duplicitniHerciList.size(); i++)
                    {
                        System.out.println(duplicitniHerciList.get(i).getJmeno()+": ");
                        String[] indexyRetezec = herciList.get(i).getIndexy().split(" ", -1);
                        int[] jednotliveIndexy = new int[indexyRetezec.length];
                        for(int l = 0; l < indexyRetezec.length; l++)
                            jednotliveIndexy[l] = Integer.parseInt(indexyRetezec[l]);
                        for(int j = 0; j < jednotliveIndexy.length; j++)
                        {
                            System.out.println(filmyHrane.get(jednotliveIndexy[j]).getNazev());
                        }
                        System.out.println();
                    }
                    break;
                case 8: //výpis filmů podle herce
                    System.out.println("Hledáte herce(0), nebo animátora(1)?");
                    System.out.println();
                    if(sc.nextInt() == 0)
                    {
                        System.out.println("Zadejte jméno hledaného herce: ");
                        System.out.println();
                        String jmeno = reader.readLine();
                        for(int i = 0; i < herciList.size(); i++)
                        {
                            if(herciList.get(i).getJmeno().equals(jmeno))
                            {
                                String[] indexyRetezec = herciList.get(i).getIndexy().split(" ", -1);
                                int[] jednotliveIndexy = new int[indexyRetezec.length];
                                for(int l = 0; l < indexyRetezec.length; l++)
                                    jednotliveIndexy[l] = Integer.parseInt(indexyRetezec[l]);
                                for(int j = 0; j < jednotliveIndexy.length; j++)
                                {
                                    System.out.println(filmyHrane.get(jednotliveIndexy[j]).getNazev());
                                }
                                break;
                            }
                        }
                    }
                    else
                    {
                        //animák
                    }
                case 9://uložení do souboru
                    System.out.println("Zadejte název filmu k uložení: ");
                    String jmeno = reader.readLine();
                    Hrany = false;
                    index = -1;
                    for(int i = 0; i <= filmyHrane.size()-1;i++)
                    {//projde nejdřív hrané filmy, potom animované, když nenajde ani tam, skončí
                        if(jmeno.equals(filmyHrane.get(i).getNazev())) {
                            Hrany = true;
                            index = i;
                        }
                    }
                    if(Hrany != true)
                    {
                        for(int i = 0; i <= filmyAnim.size()-1;i++)
                        {
                            if(jmeno.equals(filmyAnim.get(i).getNazev())) {
                                index = i;
                            }
                        }
                    }
                    if(index == -1) {//pokud to nenašlo stejné jméno ve hraných, ani animovaných
                        System.out.println("Film s tímto jménem neexistuje");
                        break;
                    }
                    if(Hrany)
                    {
                        for(int i = 0; i < filmyHrane.size(); i++)
                        {
                            if(filmyHrane.get(i).getNazev().equals(jmeno)) {
                                hranyFilm[0] = jmeno;
                                hranyFilm[1] = filmyHrane.get(i).getReziser();
                                hranyFilm[2] = Integer.toString(filmyHrane.get(i).getRok());
                                hranyFilm[3] = filmyHrane.get(i).getHerci();
                                hranyFilm[4] = filmyHrane.get(i).getHodnoceniCisla();
                                hranyFilm[5] = filmyHrane.get(i).getHodnoceniSlova();
                            }
                        }
                        soubor.ulozFilm(jmeno+(".txt"), hranyFilm);
                    }
                    break;

                case 10: //načtení ze souboru
                    System.out.println("Chcete načíst hraný film(1), nebo animovaný(2)?");
                    int volbaAkce = sc.nextInt();
                    System.out.println("Zadejte název souboru k načtení: ");
                    jmeno = reader.readLine();
                    Hrany = false;
                    if(volbaAkce == 1)
                        Hrany = true;
                    if(Hrany)
                    {
                        hranyFilm = soubor.nactiFilm(jmeno);

                        Film film = new Film(hranyFilm[0],hranyFilm[1],Integer.parseInt(hranyFilm[2]),hranyFilm[3],hranyFilm[4],hranyFilm[5]);
                        filmyHrane.add(film);

                        String[] herciTohoFilmu = hranyFilm[3].split(", ", -1);
                        int idx = filmyHrane.size()-1;
                        int delkaListu = herciList.size();
                        int delkaDuplicit = duplicitniHerciList.size();
                        boolean existuje = false;
                        boolean existujeVubec = false;
                        for(int j = 0; j < herciTohoFilmu.length; j++)
                        {
                            existuje = false;
                            existujeVubec = false;
                            for (int i = 0; i < delkaListu; i++)
                            {
                                if(herciList.get(i).getJmeno().equals(herciTohoFilmu[j])) {//pokud se už herec nachází v seznamu herců, přidá se do seznamu duplicitních
                                    for(int k = 0; k<delkaDuplicit;k++)
                                    {
                                        if(duplicitniHerciList.get(k).getJmeno().equals(herciTohoFilmu[j]))
                                        {
                                            duplicitniHerciList.get(k).setIndexy(" " + Integer.toString(idx));
                                            existuje = true;
                                            existujeVubec = true;
                                        }
                                    }
                                    if(!existuje)
                                    {
                                        duplicitniHerciList.add(herciList.get(i));
                                        duplicitniHerciList.get(duplicitniHerciList.size() - 1).setIndexy(" " + Integer.toString(idx));
                                        existuje = false;
                                        existujeVubec = true;
                                    }
                                }
                            }
                            if(!existujeVubec) {
                                DuplicitniHerci fachman = new DuplicitniHerci(herciTohoFilmu[j]);
                                fachman.setIndexy(" " + Integer.toString(idx));
                                herciList.add(fachman);
                            }
                        }
                    }
                    else
                    {
                        //animák
                    }
                    break;
                case 11:
                    run = false;
                    break;
            }
        }
    }
}
