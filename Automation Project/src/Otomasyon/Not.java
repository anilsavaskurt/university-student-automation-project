package Otomasyon;

public abstract class Not{
    private int not;
    private static String harfNotu;
    public int getNot(){
        return not;
    }
    public void setNot(int not){
        this.not = not;
    }
    public static String harfNotuHesaplama(int not){
      
        if(not>= 90){
            harfNotu = "AA";
        }else if(not >=85){
            harfNotu = "BA";
        }else if(not >=80){
            harfNotu = "BB";
        }else if(not >= 75){
            harfNotu = "CB";
        }else if(not>= 65){
            harfNotu = "CC";
        }else if(not>= 58){
            harfNotu = "DC";
        }else if(not>= 50){
            harfNotu = "DD";
        }else if(not>= 40){
            harfNotu = "FD";
        }else {
            harfNotu = "FF";
        }
        
        return harfNotu;
    }
}
