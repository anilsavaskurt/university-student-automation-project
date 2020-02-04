package Otomasyon;
import static Otomasyon.Giris.girisGorevliSorgula;
import static Otomasyon.Giris.girisMemurSorgula;
import static Otomasyon.Giris.girisOgrSorgula;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;


public class Sistem {

    static final String JDBC_DRIVER ="com.myssql.jdbc.Driver";
    static final String DOMAIN_NAME ="localhost";
    static final String DB_NAME = "proje";
    static final String DB_URL = "jdbc:mysql://" + DOMAIN_NAME +"/"+DB_NAME;
    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        
        Connection myConnection = null;
        try{
           myConnection = DriverManager.getConnection(DB_URL,"root","");
        }catch (Exception e) {
            e.printStackTrace(); 
        }
       
        int giris;
        boolean out = true;
        boolean ogrout = true;
        boolean gorevliout = true;
        boolean memurout = true;
        do{
            System.out.println("----------------------------");
            System.out.println("KULLANICI GİRİŞ EKRANI");
            System.out.println("1) ÖĞRENCİ GİRİŞİ");  
            System.out.println("2) ÖĞRETİM GÖREVLİSİ GİRİŞİ");  
            System.out.println("3) İDARİ MEMUR GİRİŞİ");
            System.out.println("4) ÇIKIŞ");            
            System.out.println("----------------------------");
            Scanner scan = new Scanner(System.in);
            giris = scan.nextInt();
            switch(giris){
            case 1 :
            System.out.println("----------------------------");
            System.out.println("ÖĞRENCİ GİRİŞ EKRANI"); 
            System.out.println("----------------------------");
            System.out.print("ÖĞRENCİ ID GİRİNİZ : ");
            int ogrid = scan.nextInt();
            System.out.print("ÖĞRENCİ ŞİFRE GİRİNİZ : ");
            String ogrsifre = scan.next();
            Ogrenci ogrenci = new Ogrenci(ogrid,ogrsifre,myConnection);
            boolean ogrsorgu = girisOgrSorgula(ogrenci, myConnection);
            if(ogrsorgu){ 
                do{
                System.out.println("----------------------------");
                System.out.println("1) NOTLARI GÖRÜNTÜLEME");
                System.out.println("2) DEVAMSIZLIK GÖRÜNTÜLEME");
                System.out.println("3) ALDIĞIN DERSLERİ GÖRÜNTÜLEME");
                System.out.println("4) DERS PROGRAMI GÖRÜNTÜLEME");
                System.out.println("5) DERS SEÇİM EKRANI");
                System.out.println("6) ÇIKIŞ");
                System.out.println("----------------------------");
                int gorevligiris = scan.nextInt();
                switch(gorevligiris){
                    
                    case 1 :
                        ogrenci.notGorme(myConnection);
                        break;
                    case 2 :
                        ogrenci.devamsizlikGorme(myConnection);
                        break;
                    case 3 : 
                        ogrenci.secilenDersListele(myConnection);
                        break;
                    case 4 :
                        ogrenci.dersProgrami(myConnection);
                        break;
                    case 5 : 
                        ogrenci.dersSecme(myConnection);
                        break;
                    case 6 :
                        ogrout = false;
                }
                }while(ogrout);
            }else{
                System.out.println("----------------------------");
                System.out.println("ID VEYA ŞİFRE YANLIŞ");
                System.out.println("GİRİŞ BAŞARISIZ");
                System.out.println("----------------------------");
            }
            break;
            case 2 :
            System.out.println("----------------------------");
            System.out.println("ÖĞRETİM GÖREVLİSİ GİRİŞ EKRANI"); 
            System.out.println("----------------------------");
            System.out.print("ÖĞRETİM GÖREVLİSİ ID GİRİNİZ : ");
            int gorevliid = scan.nextInt();
            System.out.print("ÖĞRETİM GÖREVLİSİ ŞİFRE GİRİNİZ : ");
            String gorevlisifre = scan.next();
            OgretimGorevlisi gorevli = new OgretimGorevlisi(gorevliid,gorevlisifre,myConnection);
            boolean gsorgu = girisGorevliSorgula(gorevli,myConnection);
            if(gsorgu){
                
                do{
                    System.out.println("----------------------------");
                    System.out.println("1) DERSİ ALAN ÖĞRENCİLERİN NOTLARINI GÖRME");
                    System.out.println("2) OGRENCİLERE NOT GİRME");
                    System.out.println("3) DERSİ ALAN ÖĞRENCİLERİN DEVAMSIZLIKLARINI GÖRME");
                    System.out.println("4) OGRENCİLERE DEVAMSIZLIK GİRME");
                    System.out.println("5) ÇIKIŞ");
                    System.out.println("----------------------------");
                    int gorevligiris = scan.nextInt();
                    
                    switch(gorevligiris){

                        case 1 :
                            gorevli.notGorme(gorevli, myConnection);
                            break;
                        case 2 :
                            gorevli.notGirme(gorevli, myConnection);
                            break;
                        case 3 :
                            gorevli.yoklamaGorme(gorevli, myConnection);
                            break;
                        case 4 :
                            gorevli.yoklamaGirme(gorevli, myConnection);
                            break;
                        case 5 :
                            gorevliout = false;
                    }
                }while(gorevliout);
            }else{
                System.out.println("----------------------------");
                System.out.println("ID VEYA ŞİFRE YANLIŞ");
                System.out.println("GİRİŞ BAŞARISIZ");
                System.out.println("----------------------------");
            }
            break;
            case 3 :
                System.out.println("----------------------------");
            System.out.println("İDARİ MEMUR GİRİŞ EKRANI");
            System.out.println("----------------------------");
            System.out.print("İDARİ MEMUR ID GİRİNİZ : ");
            int memurid = scan.nextInt();
            System.out.print("İDARİ MEMUR ŞİFRE GİRİNİZ : ");
            String memursifre = scan.next();
            IdariMemur memur = new IdariMemur(memurid,memursifre);
            boolean memursorgu = girisMemurSorgula(memur,myConnection);
            if(memursorgu){
                
                do{
                    System.out.println("----------------------------");
                    System.out.println("1) OGRENCİ LİSTELE");
                    System.out.println("2) OGRENCİ KAYDI EKLE");
                    System.out.println("3) OGRENCİ KAYDI SİL");
                    System.out.println("4) OGRENCİ KAYDI GUNCELLE");
                    System.out.println("5) OGRETİM GOREVLİSİ LİSTELE");
                    System.out.println("6) OGRETİM GÖREVLİSİ KAYDI EKLE");
                    System.out.println("7) OGRETİM GÖREVLİSİ KAYDI SİL");
                    System.out.println("8) OGRETİM GÖREVLİSİ KAYDI GUNCELLE");
                    System.out.println("9) DERS PROGRAMI OLUŞTUR");
                    System.out.println("0) ÇIKIŞ");
                    System.out.println("----------------------------");
                    int memurgiris = scan.nextInt();
                    
                    switch(memurgiris){
                        case 1 :
                            memur.ogrenciListele(myConnection);
                            break;
                        case 2 : 
                            memur.ogrKayitEkle(myConnection);
                            break;
                        case 3 : 
                            memur.ogrKayitSil(myConnection);
                            break;
                        case 4 : 
                            memur.ogrKayitGuncelle(myConnection);
                            break;
                        case 5 :
                            memur.gorevliListele(myConnection);
                            break;
                        case 6 :
                            memur.ogrGorevlisiKayitEkle(myConnection);
                            break;
                        case 7 : 
                            memur.ogrGorevlisiKayitSil(myConnection);
                            break;
                        case 8 : 
                            memur.ogrGorevlisiKayitGuncelle(myConnection);
                            break;
                        case 9 : 
                            memur.dersProgramiOlustur(myConnection);
                            break;
                        case 0 : 
                            memurout = false;
                    }
                }while(memurout);
            }else{
                System.out.println("----------------------------");
                System.out.println("ID VEYA ŞİFRE YANLIŞ");
                System.out.println("GİRİŞ BAŞARISIZ");
                System.out.println("----------------------------");
            }
            break;
            case 4 : 
            out = false;
            break;
            }
        }while(out);
    }
}
 


