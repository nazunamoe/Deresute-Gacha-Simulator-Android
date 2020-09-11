package com.nazunamoe.deresutegachasimulatorm.Gacha;

import java.util.Random;

public class Gacha {

    int MAX = 999;
    Random random = new Random();

    public int GachaExecute(float SSRp, float SRp, Boolean rensya){
        int result;
        int temp;
        temp=random.nextInt(MAX);
        if(temp<MAX*(SSRp*0.01)) result = 7;
        else if(temp<MAX*(SRp*0.01)) result = 5;
        else {
            if(rensya) return GachaExecute(SSRp, SRp, rensya);
            else result = 3;
        }
        return result;
    }
}
