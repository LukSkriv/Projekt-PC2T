import java.sql.*;
import java.util.List;

public class Connect
{
    private Connection conn;

    public boolean connect()
    {
        conn = null;
        try {

            conn = DriverManager.getConnection("jdbc:sqlite:myDB.db");
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public void disconnect()
    {
        if (conn != null)
        {
            try
            {
                conn.close();
            } catch (SQLException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }
    public boolean createTableHrane()
    {
        if (conn==null)
            return false;
        String sql = "CREATE TABLE IF NOT EXISTS HraneFilmy (" + "nazev TEXT PRIMARY KEY NOT NULL," + "reziser TEXT NOT NULL," + "rok INT NOT NULL, " + "herci TEXT NOT NULL, " + "hodnoceni TEXT NOT NULL," +
                "" + " slovniHodnoceni TEXT NULL DEFAULT '-');";
        try{
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            return true;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public boolean createTableAnimovane()
    {
        if (conn==null)
            return false;
        String sql = "CREATE TABLE IF NOT EXISTS AnimovaneFilmy (" + "nazev TEXT PRIMARY KEY NOT NULL," + "reziser TEXT NOT NULL," + "rok INT NOT NULL, " + "animatori TEXT NOT NULL, " + "hodnoceni TEXT NOT NULL," +
                "" + " slovniHodnoceni TEXT NULL DEFAULT '-', " + "doporucenyVek INT NOT NULL);";
        try{
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            return true;
        }
        catch (SQLException e) {
        }
        return false;
    }

    public void insertHrany(String nazev, String reziser, int rok, String herci, String hodnoceni, String slovniHodnoceni) {
        String sql = "INSERT INTO HraneFilmy(nazev,reziser,rok,herci,hodnoceni,slovniHodnoceni) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nazev);
            pstmt.setString(2, reziser);
            pstmt.setInt(3, rok);
            pstmt.setString(4, herci);
            pstmt.setString(5,hodnoceni);
            pstmt.setString(6, slovniHodnoceni);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void insertAnim(String nazev, String reziser, int rok, String animatori, String hodnoceni, String slovniHodnoceni, int doporucenyVek) {
        String sql = "INSERT INTO AnimovaneFilmy(nazev,reziser,rok,animatori,hodnoceni,slovniHodnoceni,doporucenyVek) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nazev);
            pstmt.setString(2, reziser);
            pstmt.setInt(3, rok);
            pstmt.setString(4, animatori);
            pstmt.setString(5,hodnoceni);
            pstmt.setString(6, slovniHodnoceni);
            pstmt.setInt(7, doporucenyVek);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void selectAllHrane(String hranyFilm[], List<Film> seznam){
        String SQL = "SELECT nazev,reziser,rok,herci,hodnoceni,slovniHodnoceni FROM HraneFilmy";
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(SQL);
            while (rs.next()) {
                hranyFilm[0] = rs.getString("nazev");
                hranyFilm[1] = rs.getString("reziser");
                hranyFilm[2] = Integer.toString(rs.getInt("rok"));
                hranyFilm[3] = rs.getString("herci");
                hranyFilm[4] = rs.getString("hodnoceni");
                hranyFilm[5] = rs.getString("slovniHodnoceni");
                Film film = new Film(hranyFilm[0],hranyFilm[1],Integer.parseInt(hranyFilm[2]),hranyFilm[3],hranyFilm[4],hranyFilm[5]);
                seznam.add(film);
             }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectAllAnim(String animovanyFilm[], List<Animak> seznam){
        String sql = "SELECT nazev,reziser,rok,animatori,hodnoceni,slovniHodnoceni,doporucenyVek FROM AnimovaneFilmy";
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next()) {
                animovanyFilm[0] = rs.getString("nazev");
                animovanyFilm[1] = rs.getString("reziser");
                animovanyFilm[2] = Integer.toString(rs.getInt("rok"));
                animovanyFilm[3] = rs.getString("animatori");
                animovanyFilm[4] = rs.getString("hodnoceni");
                animovanyFilm[5] = rs.getString("slovniHodnoceni");
                animovanyFilm[6] = Integer.toString(rs.getInt("doporucenyVek"));
                Animak film = new Animak(animovanyFilm[0],animovanyFilm[1],Integer.parseInt(animovanyFilm[2]),animovanyFilm[3],animovanyFilm[4],animovanyFilm[5], Integer.parseInt(animovanyFilm[6]));
                seznam.add(film);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropAll()
    {
        String sql = "DROP TABLE AnimovaneFilmy";
        try{
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        }
        catch (SQLException e) {
        }

        sql = "DROP TABLE HraneFilmy";
        try{
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        }
        catch (SQLException e) {
        }
    }
}
