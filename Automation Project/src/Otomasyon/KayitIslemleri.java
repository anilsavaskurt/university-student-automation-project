package Otomasyon;
import java.sql.Connection;
import java.sql.Statement;

public interface KayitIslemleri{

    public void ogrKayitEkle(Connection myConnection);
    public void ogrKayitSil(Connection myConnection);
    public void ogrKayitGuncelle(Connection myConnection);   
    public void ogrGorevlisiKayitEkle(Connection myConnection);
    public void ogrGorevlisiKayitSil(Connection myConnection);
    public void ogrGorevlisiKayitGuncelle(Connection myConnection);
}