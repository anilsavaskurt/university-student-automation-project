package Otomasyon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ders extends Not{
    
    private int dersId;

    Ders() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void setId(int a){
        this.dersId = a;
    }
    public int getId(){
        return this.dersId;
    }
    String dersAdi;
    int devamsizlikSayisi;
    String dersGunu;
    String dersBaslamaSaati;
    String dersBitisSaati;
    public void setDevamsizlik(int d){
        this.devamsizlikSayisi = d;
    }
    public int getDevamsizlik(){
        return this.devamsizlikSayisi;
    }
    public final void bilgiAktar(Connection myConnection) {
        
         try {
             String sql = "select * from dersler where did = ? ";
             PreparedStatement info = myConnection.prepareStatement(sql);
             info.setInt(1,this.getId());
             ResultSet row = info.executeQuery();
             while(row.next()){
 
                this.dersAdi = row.getString("dadi");
                this.dersGunu = row.getString("dgunu");
                this.dersBaslamaSaati = row.getString("dbaslamasaati");
                this.dersBitisSaati = row.getString("dbitissaati");
             }
         } catch (SQLException ex) {
             Logger.getLogger(Ogrenci.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    public void atama(String dersAdi,String dersGunu,String dersBaslamaSaati,String dersBitisSaati){
        this.dersAdi = dersAdi;
        this.dersBaslamaSaati = dersBaslamaSaati;
        this.dersBitisSaati = dersBitisSaati;
        this.dersGunu = dersGunu;
    } 
    Ders(int id,Connection myConnection){
       this.setId(id);
       bilgiAktar(myConnection);
    }
}
