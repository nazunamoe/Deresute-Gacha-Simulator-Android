package com.nazunamoe.deresutegachasimulatorm.Gacha;

import java.util.Random;

public class Gacha {

    int MAX = 999;
    Random random = new Random();

    public int GachaExecute(float SSRp, float SRp){
        int result = 0;
        int temp;
        temp=random.nextInt(MAX);
        if(temp<MAX*(SSRp*0.01)){
            result = 7;
        }else if(temp<MAX*(SRp*0.01)){
            result = 5;
        }else {
            result = 3;
        }
        return result;
    }

    public int rensyaSR(float SSRp){
        int result = 0;
        int temp;
        while(true){
            temp=random.nextInt(MAX);
            if(temp<MAX*(SSRp*0.01)){
                result = 7;
                break;
            }else {result = 5;
                break;}
        }
        return result;
    }
}
