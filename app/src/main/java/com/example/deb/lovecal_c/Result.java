package com.example.deb.lovecal_c;

import android.app.Activity;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.spark.submitbutton.SubmitButton;

/**
 * Created by dEb on 28-11-2017.
 */

public class Result extends AppCompatActivity {

    TextView fname,sname,percen,con;
    ImageView image;
    SubmitButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        initView();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        fname.setText(bundle.getString("fname"));
        sname.setText(bundle.getString("sname"));
        String per =(bundle.getString("percentage"));
        Integer percentage = Integer.parseInt(per);

        if(percentage<=50)
             image.setImageResource(R.drawable.nocouple);
         if(percentage>=51 && percentage<=70)
             image.setImageResource(R.drawable.couple);
        if(percentage>=71)
            image.setImageResource(R.drawable.wedding);
        String setPer = per + "%";
        percen.setText(setPer);
        con.setText(bundle.getString("result"));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Result.this.finish();
            }
        });

    }

    void initView()
    {
        fname = (TextView) findViewById(R.id.prince);
        sname = (TextView) findViewById(R.id.princess);
        percen = (TextView) findViewById(R.id.percentage);
        con = (TextView) findViewById(R.id.con);
        image = (ImageView) findViewById(R.id.f_image);
        back = (SubmitButton) findViewById(R.id.back);

    }


}
