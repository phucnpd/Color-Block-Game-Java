/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainmenujava;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyen Pham Dinh Phuc
 */
public class ScoresManagement {
    private Connection conn;
    PreparedStatement pst;
    private ResultSet rs;
    private String sql;
    ArrayList<Scores> scores;

    /**
     * Type model
     *
     * @param conn Connection use for all
     */
    public ScoresManagement(Connection conn) {
        this.conn = conn;
        scores = new ArrayList<Scores>();
        pst = null;
        rs = null;
        sql = "";

    }

    public ArrayList<Scores> getSocre(){
        return this.scores;
    }
    /**
     * Load from database
     *
     * @return a String to know is Load succeed?
     */
    public String load() {
        try {
            DBManagement db = new DBManagement();
            Connection conn = db.getConnected();
            Statement st = conn.createStatement();
            sql = "SELECT * FROM `scores` WHERE 1 ORDER BY `s_score` DESC";
            ResultSet rs = st.executeQuery(sql);
            if(rs.first()){
                addAccount(rs, scores);
                int i=0;
                while(rs.next()){
                    addAccount(rs, scores); 
                    System.out.println(scores.get(i).sScore);
                    i++;
                }
            }
            return "succeed";
        } catch (SQLException ex) {
            return ex.getMessage();
        }

    }
    private static void addAccount(ResultSet rs, ArrayList<Scores> al) throws SQLException{
        String acUser;
        int acScore;
        acUser=rs.getString("s_username");
        acScore=rs.getInt("s_score");
        al.add(new Scores(acUser, Integer.toString(acScore)));
    }

    /**
     * Insert to database
     *
     * @param tText info to insert to database
     * @return a String to know is insert succeed?
     */
    public String insert(String string, int score) {
        try {
            sql = "INSERT INTO `Scores`(`s_username`, `s_score`) VALUES (?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, string);
            pst.setInt(2, score);
            pst.execute();

            return "succes";
        } catch (SQLException ex) {
            return ex.getMessage();
        }
    }

    /**
     * Delete info at database
     *
     * @param tId id to delete
     * @return a String to know is delete succeed?
     */
    public String delete(int tId) {
        try {
            sql = "DELETE FROM `type` WHERE `tId`=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, tId);
            pst.execute();

            return "succes";
        } catch (SQLException ex) {
            return ex.getMessage();
        }
    }

    /**
     * Update info at database
     *
     * @param tId id which one need to update
     * @param new_tText new info update to database
     * @return a String to know is update succeed?
     */
    public String update(int tId, String new_tText) {
        try {
            sql = "UPDATE `type` SET `tText`=? WHERE `tId`=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, new_tText);
            pst.setInt(2, tId);
            pst.execute();

            return "succes";
        } catch (SQLException ex) {
            return ex.getMessage();
        }
    }
     @Override
    public String toString() {
        String str = "";
        int no = 0;
        for (Scores score : scores) {
            no++;
            str += "#" + no + ". " + score.toString() + "\n";
        }
        return str;
    }
    public String saveBXH(){
        load();
        String save = "";
        for(Scores s : scores){
            save =save+ s.toString();
        }
        
        return save;
    }
}
