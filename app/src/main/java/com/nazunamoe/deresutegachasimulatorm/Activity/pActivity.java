package com.nazunamoe.deresutegachasimulatorm.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.nazunamoe.deresutegachasimulatorm.R;

public class pActivity extends AppCompatActivity {

    double SRPValue = 0.0;
    double SSRPValue = 0.0;

    public double getConvertedValue(int intVal, int mode){
        double floatVal = 0.0;
        floatVal = .5f * intVal;
        if(mode == 0){
            SRPValue = floatVal;
        }else{
            SSRPValue = floatVal;
        }

        return floatVal;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_p);

        final SharedPreferences Shared = getSharedPreferences("Shared", 0);
        final SharedPreferences.Editor editor = Shared.edit();

        final SeekBar SSR = (SeekBar) findViewById(R.id.SSRSeek);
        final SeekBar SR = (SeekBar) findViewById(R.id.SRSeek);

        final TextView SSRP = (TextView) findViewById(R.id.SSRPText);
        final TextView SRP = (TextView) findViewById(R.id.SRPText);

        Button FesButton = (Button) findViewById(R.id.FesPButton);
        Button ApplyButton = (Button) findViewById(R.id.ApplyButton);
        Button OriginalButton = (Button) findViewById(R.id.OriginalButton);

        SSR.setProgress(6);
        SR.setProgress(24);

        SSR.getProgressDrawable().setColorFilter(getResources().getColor(R.color.accentstatus,null), PorterDuff.Mode.SRC_IN);
        SSR.getThumb().setColorFilter(getResources().getColor(R.color.accentstatus,null), PorterDuff.Mode.SRC_IN);

        SR.getProgressDrawable().setColorFilter(getResources().getColor(R.color.accentstatus,null), PorterDuff.Mode.SRC_IN);
        SR.getThumb().setColorFilter(getResources().getColor(R.color.accentstatus,null), PorterDuff.Mode.SRC_IN);

        SSRP.setText("3.0 %");
        SRP.setText("12.0 %");
        SSRPValue = 3.0;
        SRPValue = 12.0;

        FesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SSR.setProgress(12);
                SR.setProgress(24);
                SSRP.setText("6.0 %");
                SRP.setText("12.0 %");
            }
        });

        OriginalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SSR.setProgress(6);
                SR.setProgress(24);
                SSRP.setText("3.0 %");
                SRP.setText("12.0 %");
            }
        });

        SSR.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SSRP.setText (getConvertedValue(progress,1)+" %");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        SR.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SRP.setText (getConvertedValue(progress,0)+" %");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ApplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SSRPValue + SRPValue > 100){
                    AlertDialog alert_confirm = new AlertDialog.Builder(pActivity.this).create();
                    alert_confirm.setTitle(getResources().getString(R.string.FailedTitle));
                    alert_confirm.setMessage(getResources().getString(R.string.PAlert));
                    alert_confirm.setButton(Dialog.BUTTON_NEGATIVE,getResources().getString(R.string.OK), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alert_confirm.show();
                }
                else{
                    AlertDialog alert_confirm = new AlertDialog.Builder(pActivity.this).create();
                    alert_confirm.setTitle(getResources().getString(R.string.SuccessTitle));
                    alert_confirm.setMessage(getResources().getString(R.string.PApplySuccess));
                    editor.putFloat("SSRP",(float)SSRPValue);
                    editor.putFloat("SRP",(float)SRPValue);
                    editor.commit();
                    alert_confirm.setButton(Dialog.BUTTON_POSITIVE,getResources().getString(R.string.OK), new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alert_confirm.show();
                }
            }
        });
    }
}
