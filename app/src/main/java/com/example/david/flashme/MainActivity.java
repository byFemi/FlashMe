package com.example.david.flashme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    //toggle value for showing answer list
    boolean isShowingAnswers = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    // First on click listener to toggle between flashcard answer and question
        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_question).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);


            }
        });
        //second on click listener to toggle between flashcard answer and question
        findViewById(R.id.flashcard_answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
                    findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);

            }
        });

        //on click listeners that set correct color, while wrong color goes red.
        findViewById(R.id.answer1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.answer1).setBackgroundColor(getResources().getColor(R.color.rubyRed));
                findViewById(R.id.answer2).setBackgroundColor(getResources().getColor(R.color.deepGreen));
            }
        });
        findViewById(R.id.answer3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.answer3).setBackgroundColor(getResources().getColor(R.color.rubyRed));
                findViewById(R.id.answer2).setBackgroundColor(getResources().getColor(R.color.deepGreen));
            }
        });
        findViewById(R.id.answer2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.answer2).setBackgroundColor(getResources().getColor(R.color.deepGreen));
            }
        });

        findViewById(R.id.toggle_choices_visibility).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //((ImageView) findViewById(R.id.toggle_choices_visibility)).setImageResource(R.drawable.hide_icon_a); will be useful for changing icon later
                //logic for toggling visibility of the answer list
                if (isShowingAnswers) {
                    findViewById(R.id.answer1).setVisibility(View.VISIBLE);
                    findViewById(R.id.answer2).setVisibility(View.VISIBLE);
                    findViewById(R.id.answer3).setVisibility(View.VISIBLE);
                    isShowingAnswers = !isShowingAnswers;
                }
                else {
                    findViewById(R.id.answer1).setVisibility(View.INVISIBLE);
                    findViewById(R.id.answer2).setVisibility(View.INVISIBLE);
                    findViewById(R.id.answer3).setVisibility(View.INVISIBLE);
                    isShowingAnswers = !isShowingAnswers;
                }
            }
        });

    }


}
