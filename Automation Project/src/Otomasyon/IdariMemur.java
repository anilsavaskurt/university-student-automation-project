package Otomasyon;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class IdariMemur extends Kullanici implements KayitIslemleri,BilgiCek{
    
    private Object LocalTime;
    
    @Override
    public void bilgiAktar(Connection myConnection) {
         
        try {
             String sql = "select * from idarimemur where id = ? ";
             PreparedStatement info = myConnection.prepareStatement(sql);
             info.setInt(1,this.getId());
             ResultSet row = info.executeQuery();
             while(row.next()){
                this.ad = row.getString("ad");
                this.soyad = row.getString("soyad");
             }             
         } catch (SQLException ex) {
             Logger.getLogger(Ogrenci.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    public IdariMemur(int id,String pass){
        this.setId(id);
        this.setPassword(pass);
    }
    @Override
    public void ogrKayitEkle(Connection myConnection) {
        
      
        Scanner scan = new Scanner(System.in);
        System.out.println("eklemek istediğiniz öğrencinin idsini giriniz!");
        int id = scan.nextInt();
        System.out.println("eklemek istediğiniz öğrencinin adını giriniz!");
        String ad= scan.next();
        System.out.println("eklemek istediğiniz öğrencinin soyadını giriniz!");
        String soyad = scan.next();
        System.out.println("girdiğiniz öğrencinin bölüm ismini giriniz");
        String bolum = scan.next();
        System.out.println("girdiğiniz öğrencinin bölüm idsini giriniz");
        int bid = scan.nextInt();
        System.out.println("ogrenciye sifre olusturunuz");
        String sifre = scan.next();
         
        try {
            String sql = "INSERT INTO ogrenci(id,ad,soyad,bolum,bid,sifre) VALUES(?,?,?,?,?,?)";
            PreparedStatement insert = myConnection.prepareStatement(sql);
            insert.setInt(1,id);
            insert.setString(2,ad);
            insert.setString(3,soyad);
            insert.setString(4,bolum);
            insert.setInt(5,bid);
            insert.setString(6,sifre);
            insert.execute();
            }catch (SQLException ex) {
            Logger.getLogger(IdariMemur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ogrKayitSil(Connection myConnection) {
        
        try {
            System.out.println("Ogrenci Listesi");
            String sql1 = "select * from ogrenci";
            Statement listeleme = myConnection.createStatement();
            ResultSet rows = listeleme.executeQuery(sql1);
            while(rows.next()){
                int id = rows.getInt("id");
                String ad = rows.getString("ad");
                String soyad = rows.getString("soyad");
                String bolum = rows.getString("bolum");
                int bid = rows.getInt("bid");
                System.out.printf("%d - %s - %s - %s\n",id,ad,soyad,bolum,bid);
                
            }
            
            System.out.println("Silmek istediginiz ogrencinin ID sini giriniz : ");
            Scanner ss = new Scanner(System.in);
            int no = ss.nextInt();
            String sql2 ="Delete from ogrenci where id = ?";
            PreparedStatement silinecek = myConnection.prepareStatement(sql2);
            silinecek.setInt(1, no);
            silinecek.execute();
        } catch (SQLException ex) {
            Logger.getLogger(IdariMemur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ogrKayitGuncelle(Connection myConnection) {
       
        try {
            System.out.println("Ogrenci Listesi");
            String sql1 = "select * from ogrenci";
            Statement listeleme = myConnection.createStatement();
            ResultSet rows = listeleme.executeQuery(sql1);
            while(rows.next()){
                int id = rows.getInt("id");
                String ad = rows.getString("ad");
                String soyad = rows.getString("soyad");
                String bolum = rows.getString("bolum");
                int bid = rows.getInt("bid");
                System.out.printf("%d - %s - %s - %s %d\n",id,ad,soyad,bolum,bid);         
            }
            
            System.out.println("Guncellemek istediginiz ogrencinin ID sini giriniz : ");
            Scanner ss = new Scanner(System.in);
            int no = ss.nextInt();
            String sql ="Update ogrenci set ad = ?,soyad = ?,bolum = ?,bid = ?  Where id = ?";
            System.out.println(no+"lu ogrencinin yeni adini giriniz");
            String yeniad = ss.next();
            System.out.println(no+"lu ogrencinin yeni soyadini giriniz");
            String yenisoyad = ss.next();
            System.out.println(no+"lu ogrencinin yeni bolumunu giriniz");
            String yenibolum = ss.next();
            System.out.println(no+"lu ogrencinin yeni bolum idsini giriniz");
            int yenibid = ss.nextInt();
            PreparedStatement silinecek = myConnection.prepareStatement(sql);
            silinecek.setString(1,yeniad);
            silinecek.setString(2,yenisoyad); 
            silinecek.setString(3,yenibolum);
            silinecek.setInt(4,yenibid); 
            silinecek.setInt(5, no);
            silinecek.execute();
        } catch (SQLException ex) {
            Logger.getLogger(IdariMemur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ogrGorevlisiKayitEkle(Connection myConnection) {
      
        Scanner scan = new Scanner(System.in);
        System.out.println("eklemek istediğiniz görevlinin idsini giriniz!");
        int id = scan.nextInt();
        System.out.println("eklemek istediğiniz görevlinin adını giriniz!");
        String ad= scan.next();
        System.out.println("eklemek istediğiniz görevlinin soyadını giriniz!");
        String soyad = scan.next();
        System.out.println("girdiğiniz görevlinin verdiği ders ismini giriniz");
        String vders = scan.next();
        System.out.println("girdiğiniz görevlinin verdiği ders idsini giriniz");
        int did = scan.nextInt();
        System.out.println("girdiğiniz görevlinin verdiği dersin bölüm idsini giriniz");
        int bid = scan.nextInt();
        System.out.println("görevliye sifre olusturunuz");
        String sifre = scan.next();
         
        try {
            String sql = "INSERT INTO ogretimgorevlisi(id,ad,soyad,verdigiders,did,sifre) VALUES(?,?,?,?,?,?)";
            PreparedStatement insert = myConnection.prepareStatement(sql);
            insert.setInt(1,id);
            insert.setString(2,ad);
            insert.setString(3,soyad);
            insert.setString(4,vders);
            insert.setInt(5,did);
            insert.setString(6,sifre);
            insert.execute();
            String sql2 = "INSERT INTO dersler(did,dadi,bid) VALUES(?,?,?)";
            PreparedStatement dinsert = myConnection.prepareStatement(sql2);
            dinsert.setInt(1,did);
            dinsert.setString(2,vders);
            dinsert.setInt(3,bid);
            dinsert.execute();
            }catch (SQLException ex) {
            Logger.getLogger(IdariMemur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void ogrGorevlisiKayitSil(Connection myConnection) {
        
        try {
            System.out.println("Ogretim Görevlisi Listesi");
            String sql1 = "select * from ogretimgorevlisi";
            Statement listeleme = myConnection.createStatement();
            ResultSet rows = listeleme.executeQuery(sql1);
            while(rows.next()){
                int id = rows.getInt("id");
                String ad = rows.getString("ad");
                String soyad = rows.getString("soyad");
                String vders = rows.getString("verdigiders");
                int did = rows.getInt("did");
                System.out.printf("%d - %s - %s - %s %d\n",id,ad,soyad,vders,did);
                
            }
            
            System.out.println("Silmek istediginiz Ogretim Görevlisinin ID sini giriniz : ");
            Scanner ss = new Scanner(System.in);
            int no = ss.nextInt();
            String sql2 ="Delete from ogretimgorevlisi where id = ?";
            PreparedStatement silinecek = myConnection.prepareStatement(sql2);
            silinecek.setInt(1, no);
            silinecek.execute();
        } catch (SQLException ex) {
            Logger.getLogger(IdariMemur.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @Override
    public void ogrGorevlisiKayitGuncelle(Connection myConnection) {
         
        try {
            System.out.println("Ogretim GÖrevlisi Listesi");
            String sql1 = "select * from ogretimgorevlisi";
            Statement listeleme = myConnection.createStatement();
            ResultSet rows = listeleme.executeQuery(sql1);
            while(rows.next()){
                int id = rows.getInt("id");
                String ad = rows.getString("ad");
                String soyad = rows.getString("soyad");
                String vders = rows.getString("verdigiders");
                int did = rows.getInt("did");
                System.out.printf("%d - %s - %s - %s %d\n",id,ad,soyad,vders,did);         
            }
            
            System.out.println("Guncellemek istediginiz Ogretim Gorevlisinin ID sini giriniz : ");
            Scanner ss = new Scanner(System.in);
            int no = ss.nextInt();
            String sql ="Update ogretimgorevlisi set ad = ?,soyad = ?,verdigiders = ?,did = ?  Where id = ?";
            System.out.println(no+"lu Ogretim Gorevlisinin yeni adini giriniz");
            String yeniad = ss.next();
            System.out.println(no+"lu Ogretim Gorevlisinin yeni soyadini giriniz");
            String yenisoyad = ss.next();
            System.out.println(no+"lu Ogretim Gorevlisinin yeni verdiği dersi giriniz");
            String yenivders = ss.next();
            System.out.println(no+"lu Ogretim Gorevlisinin yeni verdiği ders idsini giriniz");
            int yenidid = ss.nextInt();
            PreparedStatement update= myConnection.prepareStatement(sql);
            update.setString(1,yeniad);
            update.setString(2,yenisoyad); 
            update.setString(3,yenivders);
            update.setInt(4,yenidid); 
            update.setInt(5, no);
            update.execute();
        } catch (SQLException ex) {
            Logger.getLogger(IdariMemur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void dersProgramiOlustur(Connection myConnection){
        
        try {
            String sql1 = "select * from bolumler";
            Statement bolumler =(Statement) myConnection.createStatement();
            ResultSet rows1 = bolumler.executeQuery(sql1);
            while(rows1.next()){
               int bid = rows1.getInt("bid");
               String badi = rows1.getString("badi");
               System.out.println(bid+" "+badi);
            }
            System.out.println("DERS PROGRAMINI HAZIRLAMAK İSTEDİĞİNİZ BÖLÜM ID GİRİNİZ");
            Scanner scan = new Scanner(System.in);
            int input = scan.nextInt();
            String sql2 = "select did,dadi,bid from dersler where bid = ? order by bid";
            PreparedStatement bolumDersleri = myConnection.prepareStatement(sql2);
            bolumDersleri.setInt(1, input);
            ResultSet rows2 = bolumDersleri.executeQuery();
            System.out.println("SEÇTİĞİNİZ BÖLÜME AİT DERSLER");
            while(rows2.next()){
                int did = rows2.getInt("did");
                String dadi = rows2.getString("dadi");
                int bid = rows2.getInt("bid");
                System.out.println(did+" "+dadi+" "+bid);
            }
            System.out.println("PROGRAMINI DÜZENLEMEK İSTEDİĞİNİZ DERS ID GİRİNİZ");
            int ders = scan.nextInt();
            String sql3 = "update dersler set dgunu = ? , dbaslamasaati = ? ,dbitissaati = ? where did = ?";
            PreparedStatement update = myConnection.prepareStatement(sql3);
            System.out.println("DERSİN HANGİ GÜN OLACAĞINI GİRİNİZ");
            String gun = scan.next();
            update.setString(1, gun);
            System.out.println("DERSIN BASLAMA SAATİNİ SEÇİNİZ");
            String start = scan.next();
            System.out.println("DERSIN BİTİŞ SAATİNİ SEÇİNİZ");
            String finish = scan.next();
            DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            try {
                java.util.Date date1 = sdf.parse(start);
                java.util.Date date2 = sdf.parse(finish);
                Time time1 = new Time(date1.getTime());
                Time time2 = new Time(date2.getTime());
                update.setTime(2, time1);
                update.setTime(3,time2);
            } catch (ParseException ex) {
                Logger.getLogger(IdariMemur.class.getName()).log(Level.SEVERE, null, ex);
            }
            update.setInt(4,ders);
            update.execute();
        } catch (SQLException ex) {
            Logger.getLogger(IdariMemur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gorevliListele(Connection myConnection){
        
        try {
            String sql = "select * from ogretimgorevlisi";
            PreparedStatement komut = myConnection.prepareStatement(sql);
            ResultSet rows = komut.executeQuery();
            OgretimGorevlisi gorevli = null;
            while(rows.next()){
                
                int id = rows.getInt("id");
                String pass = rows.getString("sifre");
                gorevli = new OgretimGorevlisi(id,pass,myConnection);
                System.out.println(gorevli.getId()+" "+gorevli.ad+" "+gorevli.soyad+" "+gorevli.getDers()+" "+gorevli.verdigiDersId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OgretimGorevlisi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ogrenciListele(Connection myConnection){
        
     
        try {
            String sql = "select * from ogrenci";
            PreparedStatement komut = myConnection.prepareStatement(sql);
            ResultSet rows = komut.executeQuery();
            Ogrenci ogr = null;
            while(rows.next()){
               
                int id = rows.getInt("id");
                String pass = rows.getString("sifre");
                ogr = new Ogrenci(id,pass,myConnection);
                System.out.println(ogr.getId()+" "+ogr.ad+" "+ogr.soyad+" "+ogr.bolum+" Mühendisliği");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OgretimGorevlisi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
