package com.example.countbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;


public class MainActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private int counter;
    private ListView currentCounterList;

    private ArrayList<counterClass> counterList;
    private ArrayAdapter<counterClass> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //receive data form NewCounter activity

        Button nukeButton = (Button) findViewById(R.id.deleteAll);
        currentCounterList = (ListView) findViewById(R.id.currentCounterList);

        nukeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                counterList.clear();
                adapter.notifyDataSetChanged();
                saveInFile();
            }
        });
    }
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<counterClass>(this,
                R.layout.list_item, counterList);
        currentCounterList.setAdapter(adapter);
    }

    public void addNewCounter(View view) {
        Intent mainToNew = new Intent(this, NewCounter.class);
        startActivityForResult(mainToNew, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String initialValue = data.getStringExtra("firstArgument");
                String name = data.getStringExtra("secondArgument");
                String comment = data.getStringExtra("thirdArgument");

                counterList.add(new counterClass(initialValue,name,comment));
                adapter.notifyDataSetChanged();
                saveInFile();
            }
        }
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<counterClass>>(){}.getType();
            counterList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
             //TODO Auto-generated catch block
            counterList = new ArrayList<counterClass>();
        } /*catch (IOException e) {
            // TODO Auto-generated catch block
           throw new RuntimeException();
        }*/
    }
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(counterList, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}