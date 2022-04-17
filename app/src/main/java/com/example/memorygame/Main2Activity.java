package com.example.memorygame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Main2Activity extends Activity {
    Button btn1,btn2;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);










    }

    public void Score(View target)

    {


    }


    public void normal_mod(View target)

    {

        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
        Main2Activity.this.startActivity(intent);
        Main2Activity.this.finish();

    }



    public void zor_mod(View target)

    {

        Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
        Main2Activity.this.startActivity(intent);
        Main2Activity.this.finish();

    }






}
