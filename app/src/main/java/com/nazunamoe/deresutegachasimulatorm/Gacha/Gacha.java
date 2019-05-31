package com.nazunamoe.deresutegachasimulatorm.Gacha;

import java.util.Random;

public class Gacha {
    private double SSRp;
    private double SRp;
    private double Rp;
    private int mode;
    Random random = new Random();

    public Gacha(int mode){
        this.mode = mode;
        initalize(mode);
    }

    public void Fes(boolean set){
        if(set){
            initalize(1);
        }
        else{
            initalize(0);
        }
    }

    private void initalize(int mode){
        if(mode == 0){
            SSRp = 3.0;
            SRp = 12.0;
        }else if (mode == 1){
            SSRp = 6.0;
            SRp = 12.0;
        }
        Rp = 100 - (SSRp+SRp);
    }

    public String GachaExecute(){
        String result = "";
        int temp;
        temp=random.nextInt(999);
        if(temp<999*(SSRp*0.01)){
            result = "7";
        }else if(temp<999*(SRp*0.01)){
            result = "5";
        }else {
            result = "3";
        }
        return result;
    }

    public String rensyaSR(){
        String result = "";
        int temp=random.nextInt(999);
        if(mode == 0){
            SSRp = 15.0;
        }else if(mode == 1){
            SSRp = 20.0;;
        }
        if(temp<999*(SSRp*0.01)) {
            result = "7";
        }else{
            result = "5";
        }
        initalize(mode);
        return result;
    }

}
