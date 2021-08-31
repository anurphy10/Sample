package com.example.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    EditText etitem;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit2);

        etitem = findViewById(R.id.etitem);
        btnSave = findViewById(R.id.btnSave);

        getSupportActionBar("Edit item");
    }
}