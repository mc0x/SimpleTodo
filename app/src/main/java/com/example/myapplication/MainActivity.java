package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.FileUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> items;

    Button btnAdd;
    EditText etItem;
    RecyclerView rvItems;
    ItemsAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.buttonAdd);
        etItem = findViewById(R.id.editText);
        rvItems = findViewById(R.id.recyclerView);

        //etItem.setText("I'm doing this from Java");


        items = new ArrayList<>();
        //loadItem();
        items.add("Buy milk");
        items.add("Go to the gym");
        items.add("have fun!");

        ItemsAdapter.OnLongClickListener onLongClickListener = new ItemsAdapter.OnLongClickListener(){
            @Override
            public void onItemLongCLicked(int position) {
                //Delete the item from the model
                items.remove(position);
                //notify the adapter
                itemsAdapter.notifyItemRemoved(position);
                Toast.makeText(getApplicationContext(), "Item was removed", Toast.LENGTH_SHORT).show();
                //saveItem();
            }
        };
        final ItemsAdapter itemsAdapter = new ItemsAdapter(items);
        rvItems.setAdapter(itemsAdapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoItem = etItem.getText().toString();
                items.add(todoItem);
                itemsAdapter.notifyItemInserted(items.size()-1);
                etItem.setText("");
                Toast.makeText(getApplicationContext(), "Item was added", Toast.LENGTH_SHORT).show();
                //saveItem();
            }
        });
    }
    private File getDataFile(){
        return new File(getFilesDir(), "data.txt");

    }
//    private void loadItem(){
//        try{
//            items = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
//        } catch (Exception e) {
//            Log.e("MainAcivity", "Error reading items", e);
//            items = new ArrayList<>();
//        }
//
//    }
//    private void saveItem(){
//        try {
//            FileUtils.writeLines(getDataFile(), items);
//        } catch (Exception e) {
//            Log.e("MainAcivity", "Error reading items", e);
//        }
//
//    }
}
