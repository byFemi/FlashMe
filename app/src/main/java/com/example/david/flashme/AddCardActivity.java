package com.example.david.flashme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);


        //close button
        findViewById(R.id.close_sign).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); //closes the activity
                overridePendingTransition(R.anim.left_out, R.anim.right_out);
            }
        });

        //save button
        findViewById(R.id.save_sign).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent(); // create a new Intent, this is where we will put our data
                data.putExtra("newQuestion", ((EditText) findViewById(R.id.edit_question)).getText().toString() ); // puts one string into the Intent, with the key as newQuestion
                data.putExtra("newAnswer", ((EditText) findViewById(R.id.edit_answer)).getText().toString()); // puts another string into the Intent, with the key as newAnswer
                setResult(RESULT_OK, data); // set result code and bundle data for response
                finish(); // closes the activity
            }
        });
    }
}
