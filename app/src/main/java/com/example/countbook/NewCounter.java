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
import android.widget.Toast;

import java.lang.reflect.Array;

public class NewCounter extends AppCompatActivity {
    private EditText nameText;
    private EditText value;
    private EditText commentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_counter);

        nameText = (EditText) findViewById(R.id.newName);
        value = (EditText) findViewById(R.id.initialValue);
        commentText = (EditText) findViewById(R.id.newComment);

    }
    public void passData(View view) {

        String nameString = nameText.getText().toString();
        String commentString = commentText.getText().toString();
        String valueInt = value.getText().toString();

        if(valueInt.matches("[0-9]+")) {
            if (Integer.valueOf(valueInt) >= 0) {
                Intent passDataIntent = new Intent(this, MainActivity.class);

                passDataIntent.putExtra("firstArgument", valueInt);
                passDataIntent.putExtra("secondArgument", nameString);
                passDataIntent.putExtra("thirdArgument", commentString);

                setResult(RESULT_OK, passDataIntent);
                finish();
            }
        }
        else{
            Toast.makeText(this,"Invalid value entered", Toast.LENGTH_LONG).show();
        }
    }
}
