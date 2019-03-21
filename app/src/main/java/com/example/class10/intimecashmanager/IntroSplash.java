package com.example.class10.intimecashmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class IntroSplash extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            Thread.sleep(1500);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
