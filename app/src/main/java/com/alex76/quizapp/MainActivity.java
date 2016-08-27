package com.alex76.quizapp;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

//initial round value


    int round = 2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setQuestion(round);
        setAnswers(round);
        setPrize(round);

        setFinalAnswer();
    }

    //sets question text by polling Strings array
    public void setQuestion(int round) {
        Resources res = getResources();

        String[] questionsArray = res.getStringArray(R.array.questions);
        TextView setQuestion = (TextView) findViewById(R.id.question);
        setQuestion.setText(questionsArray[round]);
    }

    //gets answers for the buttons by polling Strings array. Probably, can do a loop or switch or something, I just don't know how.
    public void setAnswers(int round) {
        Resources res = getResources();
        if (round == 0) {
            String[] answersArray = res.getStringArray(R.array.answers0);
        } else if (round == 1) {
            String[] answersArray = res.getStringArray(R.array.answers1);
        } else if (round == 2) {
            String[] answersArray = res.getStringArray(R.array.answers2);
        } else if (round == 3) {
            String[] answersArray = res.getStringArray(R.array.answers3);
        } else if (round == 4) {
            String[] answersArray = res.getStringArray(R.array.answers4);
        }
        setButtons(answersArray);
    }

    //sets Strings values to the buttons. Again, probably can be written more economically.
    public void setButtons(String[] answersArray)

    {
        RadioButton bt1 = (RadioButton) findViewById(R.id.button1);
        bt1.setText(answersArray[0]);
        RadioButton bt2 = (RadioButton) findViewById(R.id.button2);
        bt2.setText(answersArray[1]);
        RadioButton bt3 = (RadioButton) findViewById(R.id.button3);
        bt3.setText(answersArray[2]);
        RadioButton bt4 = (RadioButton) findViewById(R.id.button4);
        bt4.setText(answersArray[3]);
    }

    public void setFinalAnswer() {
        //sets OnClick listener to the button
        Button finalAnswer = (Button) findViewById(R.id.finalAnswer);
        finalAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finds Radiogroup by id
                RadioGroup answersGroup = (RadioGroup) findViewById(R.id.answersGroup);
                //creates Radiobutton object for the right answer determination
                RadioButton selectedAnswer;
                // sets int variable for the selected button position
                int selectedId = answersGroup.getCheckedRadioButtonId();
                //finds selected button by position and assigns it to Radiobutton selectedAnswer
                selectedAnswer = (RadioButton) findViewById(selectedId);
                //gets text from the selected button by casting this as a string
                String answerCheck = (String) selectedAnswer.getText();
                //calls checkAnswer method by passing the String answerCheck
                checkAnswer(answerCheck);


            }
        });
    }
    
    public void setPrize(int round) {
        Resources res = getResources();
        String[] prizes = res.getStringArray(R.array.prizes);
        TextView prizeSet = (TextView) findViewById(R.id.dollars);
        prizeSet.setText("You are playing for " + prizes[round] + "!" + "\nYour total bank is $" + calculateBank(round) + "!");

    }

    public int calculateBank(int round) {

        if (round < 3) {
            int totalBank = round * 100;
            return totalBank;

        } else if (round == 3) {
            int totalBank = 500;
            return totalBank;

        } else {
            int totalBank = 1000;
            return totalBank;
        }
    }

    public void checkAnswer(String answerCheck) {

        Resources res2 = getResources();

        String[] rightAnswers = res2.getStringArray(R.array.rightAnswers);
        if (rightAnswers[round].equals(answerCheck)) {

            showToast("Right Answer! Congrats! You won " + calculateBank(round + 1));


        } else {

            showToast("Wrong answer! Game Over! You lost all your money!");


        }


    }


    public void showToast(String toastText) {
        Context context = getApplicationContext();
        String text = toastText;
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }


}
