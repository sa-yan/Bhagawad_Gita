package com.example.bhagawadgita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ChapterMeaningActivity extends AppCompatActivity {

    TextView summaryChap, chapNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_meaning);
        summaryChap=findViewById(R.id.summary);
        chapNumber=findViewById(R.id.chapNumber);
        Intent intent = getIntent();
        String summary = intent.getStringExtra("com.example.bhagawadgita.EXTRA_NAME_1");
        int chapterNumber = intent.getIntExtra("com.example.bhagawadgita.EXTRA_NAME_2", 0)+1;
        chapNumber.setText("Chapter-"+chapterNumber);

        summaryChap.setText(summary);
    }
}