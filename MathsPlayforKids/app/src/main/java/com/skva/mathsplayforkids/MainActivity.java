package com.skva.mathsplayforkids;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity {

    private int score=0, total_attempts =0;
    private String question ="";
    private int game_type = 0;
    private int difficulty = 0;
    private int a=0;
    private int[] choiceArray = new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                a = Integer.parseInt(button1.getText().toString());
                validateAnswer();
                Log.d("BUTTON1onClick","a: "+a);
            }
        });
        final Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                a = Integer.parseInt(button2.getText().toString());
                Log.d("BUTTON2onClick","a: "+a);
                validateAnswer();
            }
        });
        final Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                a = Integer.parseInt(button3.getText().toString());
                Log.d("BUTTON3onClick","a: "+a);
                validateAnswer();
            }
        });
        final Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                a = Integer.parseInt(button4.getText().toString());
                Log.d("BUTTON4onClick","a: "+a);
                validateAnswer();
            }
        });
        final Button button5 = (Button) findViewById(R.id.skip);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                generateQuestion();
                Log.d("SKIP","skip ");

            }
        });
        updateScore(0);
        generateQuestion();
    }

    private void updateScore(int tmpScore) {
        Log.d("updateScore", " before : "+score);
        score += tmpScore;
        Log.d("updateScore", "after : "+score);
    }

    private void generateQuestion() {
        Random r = new Random();
        if ((game_type == 0) && (difficulty == 0)) {
            int difficulty_max = 50;
            int difficulty_min = 5;
            int q = r.nextInt(difficulty_max-difficulty_min)+difficulty_min+1;
            if (q==difficulty_max) {
                q=q-2;
            }
            Log.d("generateQuestion", "q : " +q);
            question = "Which number is (greater than) >  " + q;
            choiceArray[0] = r.nextInt(q-difficulty_min)+difficulty_min;
            choiceArray[1] = r.nextInt(q-difficulty_min)+difficulty_min;
            choiceArray[2] = r.nextInt(q-difficulty_min)+difficulty_min;
            choiceArray[3] = r.nextInt(difficulty_max-q-1)+q+1;
            Log.d("generateQuestion", "a1: " +choiceArray[0]+" a2: " +choiceArray[1] + " a3: " +choiceArray[2] + " a4: " +choiceArray[3]);
        }
        if ((game_type == 1) && (difficulty == 0)) {
            int difficulty_max = 50;
            int difficult_min = 5;
            int q = r.nextInt(difficulty_max-difficult_min)+difficult_min+1;
            if (q==difficulty_max) {
                q=q-2;
            }
            Log.d("generateQuestion", "q : " +q);
            question = "Which number is (less than) <  " + q;
            choiceArray[0] = r.nextInt(difficulty_max-q-1)+q+1;
            choiceArray[1] = r.nextInt(difficulty_max-q-1)+q+1;
            choiceArray[2] = r.nextInt(difficulty_max-q-1)+q+1;
            choiceArray[3] = r.nextInt(q-difficult_min)+difficult_min;
            Log.d("generateQuestion", "a1: " +choiceArray[0]+" a2: " +choiceArray[1] + " a3: " +choiceArray[2] + " a4: " +choiceArray[3]);
        }
        if ((game_type == 2) && (difficulty == 0)) {
            int difficulty_max = 50;
            int difficult_min = 5;
            int mid = (int)(difficult_min+difficulty_max)/2;
            int p = r.nextInt(mid-difficult_min)+difficult_min;
            int q = r.nextInt(difficulty_max-mid)+mid;
            Log.d("generateQuestion", "p: "+p+" q : " +q);
            question = "Which number is between " +p + " & " + q;
            choiceArray[0] = r.nextInt(p)+difficult_min;
            choiceArray[1] = r.nextInt(difficulty_max-q-1)+q+1;
            choiceArray[2] = r.nextInt(difficulty_max-q-1)+q+1;
            choiceArray[3] = r.nextInt(q-p)+p;
            Log.d("generateQuestion", "a1: " +choiceArray[0]+" a2: " +choiceArray[1] + " a3: " +choiceArray[2] + " a4: " +choiceArray[3]);
        }
        if ((game_type == 3) && (difficulty == 0)) {
            int difficulty_max = 10;
            int difficult_min = 0;
            int mid = (int)(difficult_min+difficulty_max)/2;
            int p = r.nextInt(mid-difficult_min)+difficult_min;
            int q = r.nextInt(difficulty_max-mid)+mid;
            Log.d("generateQuestion", "p: "+p+" q : " +q);
            question = "What is " +p + " + " + q;
            choiceArray[0] = p+q+r.nextInt(difficulty_max-difficult_min)+1;
            choiceArray[1] = p+q+r.nextInt(difficulty_max)-1;
            choiceArray[2] = (p+q)/2;
            choiceArray[3] = p+q;
            Log.d("generateQuestion", "a1: " +choiceArray[0]+" a2: " +choiceArray[1] + " a3: " +choiceArray[2] + " a4: " +choiceArray[3]);
        }
        if ((game_type == 4) && (difficulty == 0)) {
            int difficulty_max = 10;
            int difficult_min = 0;
            int mid = (int)(difficult_min+difficulty_max)/2;
            int p = r.nextInt(mid-difficult_min)+difficult_min;
            int q = r.nextInt(difficulty_max-mid)+mid;
            Log.d("generateQuestion", "p: "+p+" q : " +q);
            question = "What is " +q + " - " + p;
            choiceArray[0] = q-p+r.nextInt(difficulty_max-difficult_min)+1;
            choiceArray[1] = q-p+r.nextInt(difficulty_max)-1;
            choiceArray[2] = (q-p)/2;
            choiceArray[3] = q-p;
            Log.d("generateQuestion", "a1: " +choiceArray[0]+" a2: " +choiceArray[1] + " a3: " +choiceArray[2] + " a4: " +choiceArray[3]);
        }
        updateView();
        total_attempts += 1;

    }

    private void validateAnswer() {
        if (a==choiceArray[3]) {
            updateScore(1);
            generateQuestion();
        } else {
            Log.d("validateAnswer","validation failed");
//            Toast.makeText(getApplicationContext(),"Wrong Answer Dumbass", Toast.LENGTH_SHORT).show();
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast_success,
                    (ViewGroup) findViewById(R.id.custom_toast_container));

            TextView text = (TextView) layout.findViewById(R.id.text);
            text.setText("Try Again");

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
        }
    }

    private void updateView() {
        int tempArray[] = new int[4];
        int i = 0;
        for (i=0; i<=3;i++ ) {
            int j = (int)Math.floor(Math.random() * (i + 1));
            if (j!=i) {
                tempArray[i] = tempArray[j];
            }
            tempArray[j] = choiceArray[i];
        }
        ((TextView) findViewById(R.id.score)).setText(" "+ score);
        ((TextView) findViewById(R.id.question)).setText(question);
        ((Button) findViewById(R.id.button1)).setText(Integer.toString(tempArray[0]));
        ((Button) findViewById(R.id.button2)).setText(Integer.toString(tempArray[1]));
        ((Button) findViewById(R.id.button3)).setText(Integer.toString(tempArray[2]));
        ((Button) findViewById(R.id.button4)).setText(Integer.toString(tempArray[3]));
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_gt:
                if (checked)
                    game_type = 0;
                    generateQuestion();
                    break;
            case R.id.radio_lt:
                if (checked)
                    game_type = 1;
                    generateQuestion();
                    break;
            case R.id.radio_between:
                if (checked)
                    game_type = 2;
                    generateQuestion();
                break;
            case R.id.radio_add:
                if (checked)
                    game_type = 3;
                    generateQuestion();
                break;
            case R.id.radio_subtract:
                if (checked)
                    game_type = 4;
                    generateQuestion();
                break;
        }
    }
}
