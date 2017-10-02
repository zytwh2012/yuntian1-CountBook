/*
 * Copyright (c) YuntianZhang(dingding) CMPUT 301 University of Alberta. All Rights Reserved. you may
 * use,distribute or modify this code under term and conditions of Code of Students Behavior
 * at University of Alberta.
 */

package com.example.countbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

        currentCounterList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mainToCounter = new Intent(MainActivity.this, counter.class);
                mainToCounter.putExtra("item",currentCounterList.getItemAtPosition
                        (position).toString());
                mainToCounter.putExtra("p",position);
                startActivityForResult(mainToCounter, 2);
            }
        });


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
                android.R.layout.simple_list_item_1, counterList);
        currentCounterList.setAdapter(adapter);
    }

    protected void addNewCounter(View view) {
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

                counterList.add(new counterClass(initialValue,initialValue, name, comment));
                adapter.notifyDataSetChanged();
                saveInFile();
            }
        }
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {

                String initialValue = data.getStringExtra("firstArgument");
                String value = data.getStringExtra("secondArgument");
                String name = data.getStringExtra("thirdArgument");
                String comment = data.getStringExtra("forthArgument");
                int position  = data.getIntExtra("p",0);

                counterList.set(position,new counterClass(initialValue,value,name,comment));
                adapter.notifyDataSetChanged();
                saveInFile();
            }
            else if(resultCode == -999){
                int position  = data.getIntExtra("p",0);

                counterList.remove(position);
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

            Type listType = new TypeToken<ArrayList<counterClass>>() {
            }.getType();
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