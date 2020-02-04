package Otomasyon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Ogrenci extends Kullanici implements BilgiCek{
    
     @Override
    public final void bilgiAktar(Connection myConnection) {
        
         try {
             String sql = "select * from ogrenci where id = ? ";
             PreparedStatement info = myConnection.prepareStatement(sql);
             info.setInt(1,this.getId());
             ResultSet row = info.executeQuery();
             while(row.next()){
                this.ad = row.getString("ad");
                this.soyad = row.getString("soyad");
                this.bolum = row.getString("bolum");
                this.bid = row.getInt("bid");
             }
         } catch (SQLException ex) {
             Logger.getLogger(Ogrenci.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    public Ogrenci(int id,String password,Connection myConnection){
        this.setId(id);
        this.setPassword(password);
        this.bilgiAktar(myConnection);
    }
    public String bolum;
    public int bid;

    public void notGorme(Connection myConnection){
        
        try {
            String sql = "Select * from ogrenci_ders where id = ?";
            PreparedStatement not = myConnection.prepareStatement(sql);
            not.setInt(1,this.getId());
            ResultSet rows = not.executeQuery();
            Ders ders = null;
            while(rows.next()){
                
                int did = rows.getInt("did");
                int dnotu = rows.getInt("dnotu");
                ders = new Ders(did,myConnection);
                ders.setNot(dnotu);
                if(ders.getNot()!= -1)
                System.out.println(this.getId()+" Numaralı Öğrenci "+this.ad+" "+this.soyad+" "+ders.getId()+" Ders Id 'li "+ders.dersAdi+" Dersi Notu : "+ders.getNot()+" ||  Ders Harf Notu : "+Not.harfNotuHesaplama(ders.getNot()));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Ogrenci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void devamsizlikGorme(Connection myConnection){
        
        try {
            String sql = "select dersler.bid,dersler.dadi,ogrenci_ders.id,ogrenci_ders.did,ogrenci_ders.devamsizlik from dersler left join ogrenci_ders on dersler.did = ogrenci_ders.did union select dersler.bid,dersler.dadi,ogrenci_ders.id,ogrenci_ders.did,ogrenci_ders.devamsizlik from dersler right join ogrenci_ders on dersler.did = ogrenci_ders.did where ogrenci_ders.id = ?";
            PreparedStatement devamsizlik = myConnection.prepareStatement(sql);
            devamsizlik.setInt(1,this.getId());
            ResultSet rows = devamsizlik.executeQuery();
            Ders ders = null;
            while(rows.next()){
               
                int id = rows.getInt("id");
                int did = rows.getInt("did");
                int bolumid = rows.getInt("bid");
                ders = new Ders(did,myConnection);
                int devam = rows.getInt("devamsizlik");
                ders.setDevamsizlik(devam);
                if(this.bid ==bolumid  && this.getId() == id )
                System.out.println(this.getId()+" Numaralı Öğrenci "+this.ad+" "+this.soyad+" "+ders.getId()+" Ders Id 'li "+ders.dersAdi+" Dersi Devamsızlık Sayısı :  "+ders.devamsizlikSayisi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ogrenci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void secilenDersListele(Connection myConnection){
       
        try {
            String sql = "select dersler.dadi,ogrenci_ders.id,ogrenci_ders.did from dersler left join ogrenci_ders on dersler.did = ogrenci_ders.did union select dersler.dadi,ogrenci_ders.id,ogrenci_ders.did from dersler right join ogrenci_ders on dersler.did = ogrenci_ders.did where ogrenci_ders.id = ?";
            PreparedStatement secilenler = myConnection.prepareStatement(sql);
            secilenler.setInt(1,this.getId());
            ResultSet rows = secilenler.executeQuery();
            System.out.println(this.ad+" "+this.soyad+" Seçtiğiniz Dersler :");
            while(rows.next()){
                
                int id = rows.getInt("id");
                int did = rows.getInt("did");
                String ders = rows.getString("dadi");
                if(id ==this.getId()){
                    System.out.println(did+" Ders Id 'li "+ders+" Dersini Alıyorsunuz");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ogrenci.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void dersSecme(Connection myConnection){
        //düzenlenmeli
        try {
           // String sql = "select dersler.dadi,dersler.did,ogrenci_ders.did from dersler left join ogrenci_ders on dersler.did != ogrenci_ders.did union select dersler.dadi,dersler.did,ogrenci_ders.did from dersler right join ogrenci_ders on dersler.did != ogrenci_ders.did where dersler.bid = ? And Not ogrenci_ders.id = ? ";
            String sql = "select did,dadi from dersler where bid = ? and did not in(select did  from ogrenci_ders where id = ?)";
            PreparedStatement sders = myConnection.prepareStatement(sql);
            sders.setInt(1,this.bid);            
            sders.setInt(2,this.getId());
            int flag = 0;
            ResultSet rows = sders.executeQuery();
            while(rows.next()){
                
                flag++;
                int did = rows.getInt("did");
                String dadi = rows.getString("dadi");
                System.out.println(did+" "+dadi);
            }
            if(flag != 0){
            System.out.println("Seçmek istediğiniz dersin id sini giriniz");
            Scanner scan = new Scanner(System.in);
            int dersid = scan.nextInt();
            String sql1 ="insert into ogrenci_ders(id,did) values(?,?)";
            PreparedStatement eklenecek = myConnection.prepareStatement(sql1);
            eklenecek.setInt(1,this.getId());
            eklenecek.setInt(2,dersid);
            eklenecek.execute();
            }else
                System.out.println("Alabileceğiniz Tüm Dersleri Seçtiğiniz İçin Seçilecek Dersiniz Yok");
        } catch (SQLException ex) {
            Logger.getLogger(Ogrenci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void dersProgrami(Connection myConnection){
        
         try {
             String sql = "select ogrenci_ders.id,ogrenci_ders.did,dersler.did,dersler.dadi,dersler.dgunu,dersler.dbaslamasaati,dersler.dbitissaati from ogrenci_ders left join dersler on dersler.did  = ogrenci_ders.did union select ogrenci_ders.id,ogrenci_ders.did,dersler.did,dersler.dadi,dersler.dgunu,dersler.dbaslamasaati,dersler.dbitissaati from ogrenci_ders right join dersler on dersler.did  = ogrenci_ders.did where ogrenci_ders.id = ?";
             PreparedStatement view = myConnection.prepareStatement(sql);
             view.setInt(1,this.getId());
             ResultSet rows = view.executeQuery();
             Ders ders = null;
             while(rows.next()){
                 
                int rid = rows.getInt("id");
                int did = rows.getInt("did");
                ders = new Ders(did,myConnection);
                if(rid == this.getId()){
                   
                    ders.atama(rows.getString("dadi"),rows.getString("dgunu"),rows.getString("dbaslamasaati"),rows.getString("dbitissaati"));
                    if(rows.getString("dgunu")!= null)
                    System.out.println(ders.getId()+" "+ders.dersAdi+" Ders, "+ders.dersGunu+" Günü "+ders.dersBaslamaSaati+" Saatinde Başlayıp "+ders.dersBitisSaati+" Saatinde Bitiyor");
                }
            }
         } catch (SQLException ex) {
             Logger.getLogger(Ogrenci.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
}
