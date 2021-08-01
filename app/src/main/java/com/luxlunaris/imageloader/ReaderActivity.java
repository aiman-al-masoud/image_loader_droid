package com.luxlunaris.imageloader;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.widget.EditText;

import org.xml.sax.XMLReader;

public class ReaderActivity extends AppCompatActivity {

    EditText textArea;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        textArea = findViewById(R.id.textArea);

        String html =  getIntent().getStringExtra("HTML");


        loadHtml(html);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void loadHtml(String html){
        textArea.setText(Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY, new ImageGetter(), null));
    }




}