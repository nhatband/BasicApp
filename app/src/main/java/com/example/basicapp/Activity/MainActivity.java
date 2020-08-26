package com.example.basicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basicapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnNumber0,
            btnNumber1,
            btnNumber2,
            btnNumber3,
            btnNumber4,
            btnNumber5,
            btnNumber6,
            btnNumber7,
            btnNumber8,
            btnNumber9;
    ImageView imglock;
    TextView tvCode1,
            tvCode2,
            tvCode3,
            tvCode4;
    String edString, edtString;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWinget();
        edString = "";
        count = 0;
    }

    private void initWinget() {
        btnNumber0 = findViewById(R.id.btnNumber0);
        btnNumber1 = findViewById(R.id.btnNumber1);
        btnNumber2 = findViewById(R.id.btnNumber2);
        btnNumber3 = findViewById(R.id.btnNumber3);
        btnNumber4 = findViewById(R.id.btnNumber4);
        btnNumber5 = findViewById(R.id.btnNumber5);
        btnNumber6 = findViewById(R.id.btnNumber6);
        btnNumber7 = findViewById(R.id.btnNumber7);
        btnNumber8 = findViewById(R.id.btnNumber8);
        btnNumber9 = findViewById(R.id.btnNumber9);

        tvCode1 = findViewById(R.id.tvcode1);
        tvCode2 = findViewById(R.id.tvcode2);
        tvCode3 = findViewById(R.id.tvcode3);
        tvCode4 = findViewById(R.id.tvcode4);
        imglock = findViewById(R.id.imglock);
    }

    @Override
    public void onClick(View view) {
        count++;
        switch (count) {
            case 1:
                tvCode1.setVisibility(View.VISIBLE);
                break;
            case 2:
                tvCode2.setVisibility(View.VISIBLE);
                break;
            case 3:
                tvCode3.setVisibility(View.VISIBLE);
                break;
            case 4:
                tvCode4.setVisibility(View.VISIBLE);
                break;
        }
        if (count == 4) {
            count = 0;
        }
        edtString = edString;
        Button button = (Button) view;
        edString += ((Button) view).getText();
        edtString += ((Button) view).getText();

            Log.d("edString", String.valueOf(edString));
            Log.d("edString", String.valueOf(edtString));

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (edtString.equals("1234") && edtString.length() == 4) {
                        Intent intent = new Intent(MainActivity.this, SecondScreen.class);
                        final Toast t = Toast.makeText(getApplicationContext(), R.string.success, Toast.LENGTH_SHORT);

                        t.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                t.cancel();
                            }
                        }, 500);
                        t.cancel();
                        startActivity(intent);
                        edtString = "";
                        edString="";
                        imglock.setImageDrawable((getResources().getDrawable(R.drawable.unlock)));
                        tvCode1.setVisibility(View.INVISIBLE);
                        tvCode2.setVisibility(View.INVISIBLE);
                        tvCode3.setVisibility(View.INVISIBLE);
                        tvCode4.setVisibility(View.INVISIBLE);
                        finish();
                    } else if (edtString != "1234" && edtString.length() == 4) {
                        imglock.setImageDrawable((getResources().getDrawable(R.drawable.wrong_password)));
                        final Toast t = Toast.makeText(getApplicationContext(), R.string.fale, Toast.LENGTH_SHORT);
                        t.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                t.cancel();
                            }
                        }, 500);
                        edtString = "";
                        edString="";
                        tvCode1.setVisibility(View.INVISIBLE);
                        tvCode2.setVisibility(View.INVISIBLE);
                        tvCode3.setVisibility(View.INVISIBLE);
                        tvCode4.setVisibility(View.INVISIBLE);
                    } else if (edtString.length() > 4) {
                        edtString="";
                        edString="";
                        count=0;
                    }
                }
            }, 100);

        }
    }


