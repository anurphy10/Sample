package com.example.sample;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    public static final String KEY_ITEM_TEXT = "item_text";
    public static final String KEY_ITEM_POSITION = "item position";
    public static final int EDIT_TEXT_CODE = 20;
    List<String> items;

    Button BtnAdd;
    EditText editem;
    RecyclerView rbitems;
    ItemsAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BtnAdd = findViewById(R.id.BtnAdd);
        editem = findViewById(R.id.editem);
        rbitems = findViewById(R.id.rbitems);

        loadItems();


        ItemsAdapter.onLongClickListener onLongClickListener = new ItemsAdapter.onLongClickListener(){
           @Override
           public void onItemLongClicked(int position) {
               items.remove(position);
               itemsAdapter.notifyItemRemoved(position);
               Toast.makeText(getApplicationContext(), "item was removed", Toast.LENGTH_SHORT).show();
               saveItems();
           }
        };

        itemsAdapter = new ItemsAdapter(items, onLongClickListener);
        rbitems.setAdapter(itemsAdapter);
        rbitems.setLayoutManager(new LinearLayoutManager(  this));

        BtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String todoItem= editem.getText().toString();
                items.add(todoItem);

                itemsAdapter.notifyItemInserted(  items.size() - 1);
                editem.setText("");
                Toast.makeText(getApplicationContext(),"item was added", Toast.LENGTH_SHORT).show();
                saveItems();
            }
        });
    }


    private File getDataFile() {
        return new File(getFilesDir(), "data.txt");
    }
    private void loadItems() {
        try {
            items = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
        } catch (IOException e) {
            Log.e(   "MainActivity", "error reading items", e);
            items = new ArrayList<>();
        }
    }
    private void saveItems() {
        try {
            FileUtils.writeLines(getDataFile(), items);
        } catch (IOException e) {
            Log.e("MainActivity", "error reading items", e);
        }
    }

}
