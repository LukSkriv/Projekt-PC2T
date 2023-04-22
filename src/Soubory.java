import java.io.*;

public class Soubory {
    Soubory(){}
    public boolean ulozFilm(String jmenoSouboru, String[] film)
    {
        try
        {
            FileWriter fw = new FileWriter(jmenoSouboru);
            BufferedWriter out = new BufferedWriter(fw);
            for(int i = 0; i < film.length; i++)
            {
                out.write(film[i]+";");
            }
        out.close();
            fw.close();
        }
        catch (IOException e)
        {
            System.out.println("Soubor nelze vytvorit");
            return false;
        }
        return true;
    }

    public String[] nactiFilm(String jmenoSouboru) {
        FileReader fr=null;
        BufferedReader in=null;
        try {
            fr = new FileReader(jmenoSouboru);
            in = new BufferedReader(fr);
            String radek=in.readLine();
            String[] vysledek = radek.split(";",-1);
            return vysledek;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
