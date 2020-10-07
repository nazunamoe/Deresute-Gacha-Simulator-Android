package com.nazunamoe.deresutegachasimulatorm.Gacha;

import java.util.Random;

public class Gacha {

    public int GachaExecute(float SSRp, float SRp, Boolean rensya){
        int temp = new Random().nextInt(999);
        if(temp < 999 * (SSRp * 0.01)) return 7;
        else if(temp < 999 * (SRp * 0.01)) return 5;
        else {
            if(rensya) return GachaExecute(SSRp, SRp, rensya);
            else return 3;
        }
    }
}
