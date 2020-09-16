package com.nazunamoe.deresutegachasimulatorm.Gacha;

        import java.util.Random;

public class Gacha {

    int MAX = 999;
    Random random = new Random();

    public int GachaExecute(float SSRp, float SRp, Boolean rensya){
        int temp;
        temp=random.nextInt(MAX);
        if(temp<MAX*(SSRp*0.01)) return 7;
        else if(temp<MAX*(SRp*0.01)) return 5;
        else {
            if(rensya) return GachaExecute(SSRp, SRp, rensya);
            else return 3;
        }
    }
}
