package Otomasyon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ANIL
 */
public class Giris {
    
    static boolean girisOgrSorgula(Ogrenci user,Connection myConnection){
        try {
            String sql = "Select * from ogrenci where id = ? and sifre = ?";
            PreparedStatement giris = myConnection.prepareStatement(sql);
            giris.setInt(1,user.getId());
            giris.setString(2,user.getPassword());
            ResultSet rows = giris.executeQuery();
            while(rows.next()){
              int id = rows.getInt("id");
              if(id == user.getId()){
                  System.out.println("----------------------------");
                  System.out.println("GİRİŞ BAŞARILI");
                  System.out.println("----------------------------");
                  return true;
              }
        }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

     static boolean girisGorevliSorgula(OgretimGorevlisi user,Connection myConnection){
        try {
            String sql = "Select * from ogretimgorevlisi where id = ? and sifre = ?";
            PreparedStatement giris = myConnection.prepareStatement(sql);
            giris.setInt(1,user.getId());
            giris.setString(2,user.getPassword());
            ResultSet rows = giris.executeQuery();
            while(rows.next()){
              int id = rows.getInt("id");
              if(id == user.getId()){
                  System.out.println("----------------------------");
                  System.out.println("GİRİŞ BAŞARILI");
                  System.out.println("----------------------------");
                  return true;
              }
        }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
     static boolean girisMemurSorgula(IdariMemur user,Connection myConnection){
        try {
            String sql = "Select * from idarimemur where id = ? and sifre = ?";
            PreparedStatement giris = myConnection.prepareStatement(sql);
            giris.setInt(1,user.getId());
            giris.setString(2,user.getPassword());
            ResultSet rows = giris.executeQuery();
            while(rows.next()){
              int id = rows.getInt("id");
              if(id == user.getId()){
                  System.out.println("----------------------------");
                  System.out.println("GİRİŞ BAŞARILI");
                  System.out.println("----------------------------");
                  return true;
              }
        }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
