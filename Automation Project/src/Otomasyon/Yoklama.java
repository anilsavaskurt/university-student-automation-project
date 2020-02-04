package Otomasyon;

import java.sql.Connection;

/**
 *
 * @author ANIL
 */
public interface Yoklama {
    
    public void yoklamaGirme(OgretimGorevlisi user,Connection myConnection);
    public void yoklamaGorme(OgretimGorevlisi user,Connection myConnection);
}
