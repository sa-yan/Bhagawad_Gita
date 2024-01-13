package com.example.bhagawadgita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_NAME_1 = "com.example.unitconverter.EXTRA_NAME_1";
    public static final String EXTRA_NAME_2 = "com.example.unitconverter.EXTRA_NAME_2";
    EditText chapter, verse;
    Button btn;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        chapter=findViewById(R.id.chapterEditText);
        verse=findViewById(R.id.verseEditText);
        queue= Volley.newRequestQueue(getApplicationContext());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SlokActivity.class);
                try{
                    if(chapter.getText().toString().isEmpty() || verse.getText().toString().isEmpty()){
                        Toast.makeText(MainActivity.this, "Fields Can't be empty", Toast.LENGTH_SHORT).show();
                    }else {
                        int i = Integer.parseInt(chapter.getText().toString());
                        int j = Integer.parseInt(verse.getText().toString());
                        intent.putExtra(EXTRA_NAME_1, i);
                        intent.putExtra(EXTRA_NAME_2, j);
                        if (i <= 18 && i>0) {
                            if(isCorrectVerseNumber(i, j)){
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this, "Enter Correct Verse NUmber", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Chapter number should be in between 1 to 18", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch(NumberFormatException ex){ // handle your exception
                    Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });





    }

    boolean isCorrectVerseNumber(int chapNumber, int VerseNumber){
        int[] maxVerseNumber = {47, 72, 43, 42, 29, 47, 30, 28, 34, 42, 55, 20, 34, 27, 20, 24, 28, 78};
        if(VerseNumber>maxVerseNumber[chapNumber-1]){
            return false;
        }else{
            return true;
        }
    }


}