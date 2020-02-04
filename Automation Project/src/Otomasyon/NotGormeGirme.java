package Otomasyon;

import java.sql.Connection;

/**
 *
 * @author ANIL
 */
public interface NotGormeGirme{
    
    public void notGirme(OgretimGorevlisi user,Connection myConnection);
    public void notGorme(OgretimGorevlisi user,Connection myConnection);
}
