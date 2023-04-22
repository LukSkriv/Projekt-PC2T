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

    public boolean nactiFilm(String jmenoSouboru, String[] film) {
        FileReader fr=null;
        BufferedReader in=null;
        try {
            fr = new FileReader(jmenoSouboru);
            in = new BufferedReader(fr);
            String radek=in.readLine();
            film = radek.split(";");
        }
        catch (IOException e) {
            System.out.println("Soubor  nelze otevřít");
            return false;
        }
        catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            System.out.println("Chyba integrity dat v souboru");
            return false;
        }
        finally
        {
            try
            {	if (in!=null)
            {
                in.close();
                fr.close();
            }
            }
            catch (IOException e) {
                System.out.println("Soubor  nelze zavrit");
                return false;
            }
        }

        return true;
    }
}
