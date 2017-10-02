/*
 * Copyright (c) YuntianZhang(dingding) CMPUT 301 University of Alberta. All Rights Reserved. you may
 * use,distribute or modify this code under term and conditions of Code of Students Behavior
 * at University of Alberta.
 */

package com.example.countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class counter extends AppCompatActivity {
    private EditText nameText;
    private EditText currentValue;
    private EditText commentText;
    private EditText initialValue;
    private TextView dateText;

    protected String dateStr;
    protected String nameStr[];
    protected String currentValueStr[];
    protected String initialValueStr[] ;
    protected String commentStr[];
    protected int position;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        nameText = (EditText) findViewById(R.id.name);
        initialValue = (EditText) findViewById(R.id.initialValue);
        commentText = (EditText) findViewById(R.id.comment);
        currentValue = (EditText) findViewById(R.id.currentValue);
        dateText = (TextView) findViewById(R.id.date);

        Button incrementButton = (Button) findViewById(R.id.increment);
        Button decrementButton = (Button) findViewById(R.id.decrement);
        Button resetButton = (Button) findViewById(R.id.reset);
        Button saveButton = (Button) findViewById(R.id.save);
        Button deleteButton = (Button) findViewById(R.id.deleteCounter);

        Bundle bundle = getIntent().getExtras();
        position = bundle.getInt("p");
        String temp = bundle.getString("item");
        String segments[] = temp.split("  ");

        dateStr = segments[0];
        nameStr= segments[1].split(":");
        currentValueStr = segments[2].split(":");
        initialValueStr= segments[2].split(":");
        commentStr = segments[3].split(":");

        dateText.setText(dateStr, TextView.BufferType.EDITABLE);
        nameText.setText(nameStr[1], TextView.BufferType.EDITABLE);
        currentValue.setText(currentValueStr[1], TextView.BufferType.EDITABLE);
        initialValue.setText(initialValueStr[1], TextView.BufferType.EDITABLE);
        commentText.setText(commentStr[1], TextView.BufferType.EDITABLE);

        incrementButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int counter = Integer.valueOf(currentValueStr[1]);
                counter ++;
                currentValueStr[1] = (Integer.toString(counter));
                currentValue.setText(currentValueStr[1], TextView.BufferType.EDITABLE);
            }
        });
        decrementButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int counter = Integer.valueOf(currentValueStr[1]);
                if(counter > 0){
                    counter --;
                    currentValueStr[1] = (Integer.toString(counter));
                    currentValue.setText(currentValueStr[1], TextView.BufferType.EDITABLE);
                }
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
               currentValueStr[1] = initialValueStr[1];
                currentValue.setText(currentValueStr[1], TextView.BufferType.EDITABLE);
            }
        });
    }
    public void passData(View view) {

        String nameString = nameText.getText().toString();
        String commentString = commentText.getText().toString();
        String currentValueInt = currentValue.getText().toString();
        String dateString = dateText.getText().toString();

        if(currentValueInt.matches("[0-9]+")) {
            if (Integer.valueOf(currentValueInt) >= 0) {
                Intent passDataIntent = new Intent(this, MainActivity.class);

                passDataIntent.putExtra("firstArgument", currentValueInt);
                passDataIntent.putExtra("secondArgument", nameString);
                passDataIntent.putExtra("thirdArgument", currentValueInt);
                passDataIntent.putExtra("forthArgument", commentString);
                passDataIntent.putExtra("p", position);

                setResult(RESULT_OK, passDataIntent);
                finish();
            }
        }
        else{
            Toast.makeText(this,"Invalid value entered", Toast.LENGTH_LONG).show();
        }
    }
    public void delete(View view){
        Intent delete = new Intent(this, MainActivity.class);

        delete.putExtra("p", position);
        setResult(-999, delete);
        finish();
    }
}

