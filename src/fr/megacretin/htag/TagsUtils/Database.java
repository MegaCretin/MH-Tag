package fr.megacretin.htag.TagsUtils;

        import org.bukkit.entity.Player;

        import java.sql.*;
        import java.util.UUID;

public class Database {
    public Database() {
    }

    public static Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://164.132.233.54/s15_HighSky";
            String user = "u15_O6NOQIf3ZB";
            String passwd = "TGY^fXWIxxt5NbCDve.om.4F";
            Connection conn = DriverManager.getConnection(url, user, passwd);
            return conn;
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static int RecupId(String tag) throws SQLException {
        int resultat = 0;
        Connection cn = Database.connect();
        String query = "SELECT * FROM Tag where libelle = ?";
        PreparedStatement ps = cn.prepareStatement(query);
        ps.setString(1, tag);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            resultat = rs.getInt("id");
        }
        rs.close();
        ps.close();
        cn.close();

        return resultat;
    }



    public static Boolean VerifTag(Player p, int tag) throws SQLException {

        boolean resultat = false;
        Connection cn = Database.connect();
        String query = "SELECT * FROM User WHERE UUID = ? AND idTags = ?";
        PreparedStatement ps = cn.prepareStatement(query);
        ps.setString(1, p.getUniqueId().toString());
        ps.setInt(2, tag);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            String bddPseudo = rs.getString("UUID");
            int bddTag = rs.getInt("idTags");
            String currentPseudo = p.getUniqueId().toString();
            int currentTag = tag;
            if ((bddPseudo.equals(currentPseudo)) && (currentTag == bddTag)) {
                resultat = true;
            }
        }
        rs.close();
        ps.close();
        cn.close();
        return resultat;
    }

    public static String RecupTag(int tag) throws SQLException {

        String resultat = "";

        Connection cn = Database.connect();
        String query = "SELECT * FROM Tag WHERE id = ?";
        PreparedStatement ps = cn.prepareStatement(query);
        ps.setInt(1, tag);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            resultat = rs.getString("libelle");
        }

        rs.close();
        ps.close();
        cn.close();
        return resultat;
    }
}
