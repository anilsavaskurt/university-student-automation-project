 package Otomasyon;
 
public abstract class Kullanici{
    
    private int id;
    String ad;
    String soyad;
    private String password;
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }
   public void setId(int id){
       this.id = id;
   }
   public int getId(){
       return this.id;
   }
}