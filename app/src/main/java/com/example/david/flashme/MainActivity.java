package com.example.david.flashme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //toggle value for showing answer list
    boolean isShowingAnswers = false;

    FlashcardDatabase flashcardDatabase; //create instance of databse to read and write to
    List<Flashcard> allFlashcards;

    int currentCardDisplayedIndex = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();

        if(allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView)findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView)findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(0).getAnswer());
        }
        //On click listener to display next card
        findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //advance the pointer index so we can show the next card
                currentCardDisplayedIndex++;

                //make sure we don't get an IndexOutOfBoundError if we are viewing last indexed card
                if(currentCardDisplayedIndex > allFlashcards.size() -1){
                    currentCardDisplayedIndex = 0; //wrap around to beginning
                }

                ((TextView)findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                ((TextView)findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());



            }
        });

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


        //switch activity when the plus button is pressed, to the add card activity.
        findViewById(R.id.plus_sign).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class); //starts intent for communication between main and addcard
                MainActivity.this.startActivityForResult(intent, 100);  //begins to listen on request code 100
            }
        });



    }//onCreate

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && data != null) { // code 100, and the textfields aren't empty
            String newQuestion = data.getExtras().getString("newQuestion"); // gets string from users question
            String newAnswer = data.getExtras().getString("newAnswer"); // gets string from users answer

            ((TextView) findViewById(R.id.flashcard_question)).setText(newQuestion);
            ((TextView) findViewById(R.id.flashcard_answer)).setText(newAnswer);

            flashcardDatabase.insertCard(new Flashcard(newQuestion, newAnswer));
            allFlashcards = flashcardDatabase.getAllCards();
        }
    }
}
