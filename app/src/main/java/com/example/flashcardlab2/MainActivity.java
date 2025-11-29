package com.example.flashcardlab2;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    private int currentQuestionNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button saveButton = findViewById(R.id.saveButton);
        EditText questionInput = findViewById(R.id.questionInput);
        EditText answer1Input = findViewById(R.id.answer1Input);

        EditText answer2Input = findViewById(R.id.answer2Input);
        EditText answer3Input = findViewById(R.id.answer3Input);
        answer2Input.setVisibility(View.GONE);
        answer3Input.setVisibility(View.GONE);


        questionInput.setText("Quelle est la capitale de la France ?");
        questionInput.setEnabled(false);
        answer1Input.setText("");

        saveButton.setOnClickListener(v -> {
            String userAnswer = answer1Input.getText().toString().trim();


            if (currentQuestionNumber == 1) {
                if (userAnswer.equalsIgnoreCase("Paris")) {

                    answer1Input.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
                    Toast.makeText(MainActivity.this, "Good! Prêt pour la suite ?", Toast.LENGTH_SHORT).show();

                    currentQuestionNumber = 2;


                    new Handler().postDelayed(() -> {
                        answer1Input.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                        questionInput.setText("Quel est le plus grand pays du monde ?");
                        answer1Input.setText("");
                    }, 1000);

                } else {

                    handleWrongAnswer(answer1Input);
                }
            }

            else if (currentQuestionNumber == 2) {
                if (userAnswer.equalsIgnoreCase("Russie")) {
                    // --- BON ---
                    answer1Input.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
                    Toast.makeText(MainActivity.this, "Good! Tout est correct !", Toast.LENGTH_SHORT).show();

                    Intent data = new Intent();
                    data.putExtra("ANSWER_KEY", userAnswer);
                    setResult(RESULT_OK, data);
                    finish();

                } else {

                    handleWrongAnswer(answer1Input);
                }
            }
        });
    }

    private void handleWrongAnswer(EditText answerInput) {
        answerInput.setBackgroundTintList(ColorStateList.valueOf(Color.RED));

        Toast.makeText(MainActivity.this, "Echec", Toast.LENGTH_SHORT).show();

        TranslateAnimation shake = new TranslateAnimation(0, 10, 0, 0);
        shake.setDuration(500);
        shake.setInterpolator(new CycleInterpolator(7));
        answerInput.startAnimation(shake);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                answerInput.setText("");
                answerInput.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
            }
        }, 1000);
    }
}
