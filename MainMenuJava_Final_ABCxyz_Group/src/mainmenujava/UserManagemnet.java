package mainmenujava;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyen Pham Dinh Phuc Ce140024
 */
public class UserManagemnet {
    public static ArrayList<Account> al = new ArrayList<>();
    /**
     * Upload to database
     */
    public static void Update() {
        try {
            DBManagement db = new DBManagement();
            Connection conn = db.getConnected();
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM `account` WHERE 1";
            ResultSet rs = st.executeQuery(sql);
            if(rs.first()){
                AddAccount(rs, al);
                while(rs.next()){
                    AddAccount(rs, al); 
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserManagemnet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * add account form database
     * @param rs
     * @param al
     * @throws SQLException 
     */
    private static void AddAccount(ResultSet rs, ArrayList<Account> al) throws SQLException {
        String acUser;
        String acPass;
        acUser=rs.getString("ac_username");
        acPass=rs.getString("ac_password");
        al.add(new Account(acUser, acPass));
    }
    /**
     * check duplicate user
     * @param user
     * @return 
     */
    public boolean isDuplicateUser(String user){
        for(Account account : al){
            if (account.getAcUser().equals(user))
                return true;
        }
        return false;
    }
    
    public boolean correctPin(String user, String pass){
        pass= encryptMD5(pass);
        for(Account account : al){
            if (account.getAcUser().equals(user))
                if(account.getAcPass().equals(pass))
                    return true;
        }
        return false;
    }

    /**
     * MD5
     * @param input
     * @return 
     */
    public static String encryptMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
}
