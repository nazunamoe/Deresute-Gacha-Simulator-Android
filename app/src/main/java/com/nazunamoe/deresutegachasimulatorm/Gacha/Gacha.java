package com.nazunamoe.deresutegachasimulatorm.Gacha;

import java.util.Random;


public class Gacha {

    int MAX = 999;
    Random random = new Random();

    public Gacha(){

    }

    public String GachaExecute(float SSRp, float SRp){
        String result = "";
        int temp;
        temp=random.nextInt(MAX);
        if(temp<MAX*(SSRp*0.01)){
            result = "7";
        }else if(temp<MAX*(SRp*0.01)){
            result = "5";
        }else {
            result = "3";
        }
        return result;
    }

    public String rensyaSR(float SSRp){
        String result = "";

        int temp;
        while(true){
            temp=random.nextInt(MAX);

            if(temp<MAX*(SSRp*0.01)){
                result = "7";
                break;
            }else
                {result ="5";
                break;}
        }
        return result;
    }

}
