package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA, ansB;
    Button submitBtn;

    int score = 0;
    int Science_score=0;
    int Commerce_score=0;
    int Arts_score=0;
    int totalQuestion = QuestionAnswers.Questions.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionsTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        submitBtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalQuestionsTextView.setText("Total questions : " + totalQuestion);

        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {

        ansA.setBackgroundColor(Color.rgb(232,192,125));
        ansB.setBackgroundColor(Color.rgb(232,192,125));

        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.submit_btn){

            if (currentQuestionIndex == 0) {
                if (selectedAnswer.equals(QuestionAnswers.correctAnswers[0])) {
                    Science_score++;
                    Arts_score = Arts_score + 2;
                } else
                    Commerce_score++;
            }

            if (currentQuestionIndex == 1) {
                if (selectedAnswer.equals(QuestionAnswers.correctAnswers[1])) {
                    Commerce_score = Commerce_score + 2;
                    Science_score++;
                } else {

                    Arts_score++;
                }
            }

            if (currentQuestionIndex == 2) {
                if (selectedAnswer.equals(QuestionAnswers.correctAnswers[2])) {
                    Science_score+=2;
                } else {
                    Commerce_score++;
                    Arts_score++;
                }
            }

            if (currentQuestionIndex == 3) {
                if (selectedAnswer.equals(QuestionAnswers.correctAnswers[3])) {
                    Arts_score+=2;
                } else {
                    Commerce_score++;
                    Science_score++;
                }
            }

            if (currentQuestionIndex == 4) {
                if (selectedAnswer.equals(QuestionAnswers.correctAnswers[4])) {
                    Commerce_score+=2;
                    Science_score+=3;
                } else {
                    Arts_score++;
                }
            }

            currentQuestionIndex++;
            loadNewQuestion();
        }
        else{
            //choices button clicked
            selectedAnswer  = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.rgb(46,176,134));
        }
    }

    void loadNewQuestion(){

        if(currentQuestionIndex == totalQuestion ){
            finishQuiz();
            return;
        }

        questionTextView.setText(QuestionAnswers.Questions[currentQuestionIndex]);
        ansA.setText(QuestionAnswers.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswers.choices[currentQuestionIndex][1]);

    }

    void finishQuiz(){

        if (Science_score > Commerce_score && Science_score > Arts_score)
        {
            new AlertDialog.Builder(this)
                    .setTitle("Result")
                    .setMessage("You should opt for Science")
                    .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz() )
                    .setCancelable(false)
                    .show();
        }

        else if (Commerce_score > Science_score && Commerce_score > Arts_score)
        {
            new AlertDialog.Builder(this)
                    .setTitle("Result")
                    .setMessage("You should opt for Commerce")
                    .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz() )
                    .setCancelable(false)
                    .show();
        }

        else if (Arts_score > Commerce_score && Arts_score > Science_score)
        {
            new AlertDialog.Builder(this)
                    .setTitle("Result")
                    .setMessage("You should opt for Arts")
                    .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz() )
                    .setCancelable(false)
                    .show();
        }

        else if (Science_score == Commerce_score)
        {
            new AlertDialog.Builder(this)
                    .setTitle("Result")
                    .setMessage("You can opt for either Science or Commerce")
                    .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz() )
                    .setCancelable(false)
                    .show();
        }

        else if (Science_score == Arts_score)
        {
            new AlertDialog.Builder(this)
                    .setTitle("Result")
                    .setMessage("You can opt for either Science or Arts")
                    .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz() )
                    .setCancelable(false)
                    .show();
        }

        else if (Commerce_score == Arts_score)
        {
            new AlertDialog.Builder(this)
                    .setTitle("Result")
                    .setMessage("You can opt for either Commerce or Arts")
                    .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz() )
                    .setCancelable(false)
                    .show();
        }
    }

    void restartQuiz(){
        Science_score = 0;
        Commerce_score = 0;
        Arts_score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }
}