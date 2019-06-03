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

    public String rensyaSR(float SSRp, float SRp){
        String result = "";

        float RenSSRp = (SSRp/(SSRp+SRp));
        float RenSRp = 1-RenSSRp;

        int temp;
        while(true){
            temp=random.nextInt(MAX);

            if(temp<MAX*(RenSSRp)){
                result = "7";
                break;
            }else if(temp<MAX*(RenSRp)){
                result = "5";
                break;
            }
            else{
                continue;
            }
        }
        return result;
    }

}
