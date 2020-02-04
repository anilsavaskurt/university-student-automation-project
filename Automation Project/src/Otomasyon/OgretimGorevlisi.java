package Otomasyon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ANIL
 */

public class OgretimGorevlisi extends Kullanici implements NotGormeGirme,Yoklama,BilgiCek{
    
    private String verdigiDers;
    int verdigiDersId;
 
    public void setDers(String verdigiDers){
        this.verdigiDers=verdigiDers;
    }
    public String getDers(){
        return this.verdigiDers;
    }
    public OgretimGorevlisi(int id,String pass,Connection myConnection){
        this.setId(id);
        this.setPassword(pass);
        
        this.bilgiAktar(myConnection);
    }
    @Override
    public void bilgiAktar(Connection myConnection) {
           
        try {
             String sql = "select * from ogretimgorevlisi where id = ? ";
             PreparedStatement info = myConnection.prepareStatement(sql);
             info.setInt(1,this.getId());
             ResultSet row = info.executeQuery();
             while(row.next()){
                this.ad = row.getString("ad");
                this.soyad = row.getString("soyad");
                this.setDers(row.getString("verdigiders"));
                this.verdigiDersId= row.getInt("did");
             }
         } catch (SQLException ex) {
             Logger.getLogger(Ogrenci.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    @Override
    public void notGorme(OgretimGorevlisi user,Connection myConnection) {
        
        try{ 
            String sql = "Select ogrenci_ders.id,ogrenci_ders.did,ogrenci_ders.dnotu,ogrenci.id,ogrenci.ad,ogrenci.soyad from ogrenci_ders left join ogrenci on ogrenci_ders.id = ogrenci.id union Select ogrenci_ders.id,ogrenci_ders.did,ogrenci_ders.dnotu,ogrenci.id,ogrenci.ad,ogrenci.soyad from ogrenci_ders right join ogrenci  on ogrenci_ders.id = ogrenci.id Where ogrenci_ders.did = ?";
            PreparedStatement not = myConnection.prepareStatement(sql);
            not.setInt(1,this.verdigiDersId);
            ResultSet persons = not.executeQuery();
            while(persons.next()){
                int no = persons.getInt("id");
                String ad = persons.getString("ad");
                String soyad = persons.getString("soyad");
                int dnotu = persons.getInt("dnotu");
                int dersidsi = persons.getInt("did");
                if((dnotu >= 0) && this.verdigiDersId == dersidsi){
                   
                     System.out.println(ad+" "+soyad+" "+no+" "+" ,Öğrencinin "+this.verdigiDers+" Dersi Notu : "+dnotu);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OgretimGorevlisi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void notGirme(OgretimGorevlisi user,Connection myConnection) {
       
        try{ 
            String sql = "Select ogrenci_ders.id,ogrenci_ders.did,ogrenci_ders.dnotu,ogrenci.id,ogrenci.ad,ogrenci.soyad from ogrenci_ders left join ogrenci on ogrenci_ders.id = ogrenci.id union Select ogrenci_ders.id,ogrenci_ders.did,ogrenci_ders.dnotu,ogrenci.id,ogrenci.ad,ogrenci.soyad from ogrenci_ders right join ogrenci  on ogrenci_ders.id = ogrenci.id Where ogrenci_ders.did = ?";
            PreparedStatement not = myConnection.prepareStatement(sql);
            not.setInt(1,this.verdigiDersId);
            ResultSet persons = not.executeQuery();
            while(persons.next()){
                int no = persons.getInt("id");
                String ad = persons.getString("ad");
                String soyad = persons.getString("soyad");
                int dnotu = persons.getInt("dnotu");
                int dersidsi = persons.getInt("did");
                if(this.verdigiDersId == dersidsi){
                   System.out.println(ad+" "+soyad+" "+no+" "+" ,Öğrencinin "+this.verdigiDers+" Dersi Notu : "+dnotu);
                }
            }
            System.out.println("Notunu Değiştirmek / Girmek İstediğiniz Öğrencinin Numarasını Giriniz");
            Scanner scan = new Scanner(System.in);
            int ogrNo = scan.nextInt();
            System.out.println("Öğrencinin Notunu Giriniz");
            int girileceknot = scan.nextInt();
            String sql3 ="Update ogrenci_ders set dnotu = ? Where did = ? and id = ?";
            PreparedStatement notgirme = myConnection.prepareStatement(sql3);
            notgirme.setInt(1,girileceknot);
            notgirme.setInt(2,this.verdigiDersId);
            notgirme.setInt(3,ogrNo);
            notgirme.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OgretimGorevlisi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void yoklamaGorme(OgretimGorevlisi user, Connection myConnection) {
        
        try{ 
            String sql = "Select ogrenci_ders.id,ogrenci_ders.did,ogrenci_ders.devamsizlik,ogrenci.id,ogrenci.ad,ogrenci.soyad from ogrenci_ders left join ogrenci on ogrenci_ders.id = ogrenci.id union Select ogrenci_ders.id,ogrenci_ders.did,ogrenci_ders.devamsizlik,ogrenci.id,ogrenci.ad,ogrenci.soyad from ogrenci_ders right join ogrenci  on ogrenci_ders.id = ogrenci.id Where ogrenci_ders.did = ?";
            PreparedStatement devamsizlik = myConnection.prepareStatement(sql);
            devamsizlik.setInt(1,this.verdigiDersId);
            ResultSet persons = devamsizlik.executeQuery();
            while(persons.next()){
                
                int no = persons.getInt("id");
                int devam = persons.getInt("devamsizlik");
                String ad = persons.getString("ad");
                String soyad = persons.getString("soyad");
                int dersidsi = persons.getInt("did");
                    if(this.verdigiDersId == dersidsi){
                        System.out.println(ad+" "+soyad+" "+no+" Öğrencinin Devamsızlık Sayısı : "+devam);
                    }
            }
        }catch (SQLException ex) {
            Logger.getLogger(OgretimGorevlisi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @Override
    public void yoklamaGirme(OgretimGorevlisi user, Connection myConnection) {
        
          try{ 
            String sql = "Select ogrenci_ders.id,ogrenci_ders.did,ogrenci_ders.devamsizlik,ogrenci.id,ogrenci.ad,ogrenci.soyad from ogrenci_ders left join ogrenci on ogrenci_ders.id = ogrenci.id union Select ogrenci_ders.id,ogrenci_ders.did,ogrenci_ders.devamsizlik,ogrenci.id,ogrenci.ad,ogrenci.soyad from ogrenci_ders right join ogrenci  on ogrenci_ders.id = ogrenci.id Where ogrenci_ders.did = ?";
            PreparedStatement not = myConnection.prepareStatement(sql);
            not.setInt(1,this.verdigiDersId);
            ResultSet persons = not.executeQuery();
            while(persons.next()){
                
                int no = persons.getInt("id");
                int devam = persons.getInt("devamsizlik");
                String ad = persons.getString("ad");
                String soyad = persons.getString("soyad");
                int dersidsi = persons.getInt("did");
                    if(this.verdigiDersId == dersidsi){
                        System.out.println(ad+" "+soyad+" "+no+" Öğrencinin Devamsızlık Sayısı : "+devam);
                    }
            }
            System.out.println("Devamsızlık Sayısını Girmek İstediğiniz Öğrencinin Numarasını Giriniz");
            Scanner scan = new Scanner(System.in);
            int ogrNo = scan.nextInt();
            System.out.println("Öğrencinin Devamsızlığını Giriniz");
            int devam = scan.nextInt();
            String sql3 ="Update ogrenci_ders set devamsizlik = ? Where did = ? and id = ?";
            PreparedStatement devamsizlik = myConnection.prepareStatement(sql3);
            devamsizlik.setInt(1,devam);
            devamsizlik.setInt(2,this.verdigiDersId);
            devamsizlik.setInt(3,ogrNo);
            devamsizlik.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OgretimGorevlisi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
}
