package com.skva.mathsplayforkids;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private int score=0, total_attempts =0;
    private String question ="";
    private int game_type = 0;
    private int difficulty = 0;
    private int a=0;
    private int[] choiceArray = new int[4];
    private Spinner spinner1;


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
                total_attempts += 1;
                generateQuestion();

                Log.d("SKIP","skip ");

            }
        });
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.game_list, R.layout.custom_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(this);


        updateScore(0);
        generateQuestion();
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        game_type = parent.getSelectedItemPosition();
        Log.d("spinner ",""+game_type);
        generateQuestion();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    private void updateScore(int tmpScore) {
        Log.d("updateScore", " before : "+score);
        score += tmpScore;
        Log.d("updateScore", "after : "+score);
    }

    private void generateQuestion() {
        Random r = new Random();
        Log.d("generateQuestion",""+game_type);
        if ((game_type == 0) && (difficulty == 0)) {
            int difficulty_max = 50;
            int difficulty_min = 5;
            int q = r.nextInt(difficulty_max-difficulty_min)+difficulty_min+1;
            if (q==difficulty_max) {
                q=q-2;
            }
            Log.d("generateQuestion", "q : " +q);
            question = " >  " + q + " ? ";
            choiceArray[0] = r.nextInt(q-difficulty_min)+difficulty_min;
            choiceArray[1] = r.nextInt(q-difficulty_min)+difficulty_min;
            choiceArray[2] = r.nextInt(q-difficulty_min)+difficulty_min;
            choiceArray[3] = r.nextInt(difficulty_max-q)+q+1;
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
            question = " < " + q + " ? ";
            choiceArray[0] = r.nextInt(difficulty_max-q)+q+1;
            choiceArray[1] = r.nextInt(difficulty_max-q)+q+1;
            choiceArray[2] = r.nextInt(difficulty_max-q)+q+1;
            choiceArray[3] = r.nextInt(q-difficult_min)+difficult_min;
            Log.d("generateQuestion", "a1: " +choiceArray[0]+" a2: " +choiceArray[1] + " a3: " +choiceArray[2] + " a4: " +choiceArray[3]);
        }
        if ((game_type == 2) && (difficulty == 0)) {
            int difficulty_max = 50;
            int difficulty_min = 5;
            int q = r.nextInt(difficulty_max-difficulty_min)+difficulty_min+1;
            if (q==difficulty_max) {
                q=q-2;
            }
            int p = q+2;
            Log.d("generateQuestion", "p: "+p+" q : " +q);
            question = " " +q + " ___ " + p + " ?";
            choiceArray[0] = r.nextInt(p)+difficulty_min;
            choiceArray[1] = r.nextInt(q)+difficulty_min;
            choiceArray[2] = r.nextInt(q)+p;
            choiceArray[3] = q+1;
            Log.d("generateQuestion", "a1: " +choiceArray[0]+" a2: " +choiceArray[1] + " a3: " +choiceArray[2] + " a4: " +choiceArray[3]);
        }
        if ((game_type == 3) && (difficulty == 0)) {
            int difficulty_max = 10;
            int difficult_min = 1;
            int mid = (int)(difficult_min+difficulty_max)/2;
            int p = r.nextInt(mid-difficult_min)+difficult_min;
            int q = r.nextInt(difficulty_max-mid)+mid;
            Log.d("generateQuestion", "p: "+p+" q : " +q);
            question = p + " + " + q+" ?";
            choiceArray[0] = p+q+r.nextInt(difficulty_max-difficult_min)+1;
            choiceArray[1] = q-p+r.nextInt(difficulty_max)-1;
            choiceArray[2] = (p+q)/2;
            choiceArray[3] = p+q;
            Log.d("generateQuestion", "a1: " +choiceArray[0]+" a2: " +choiceArray[1] + " a3: " +choiceArray[2] + " a4: " +choiceArray[3]);
        }
        if ((game_type == 4) && (difficulty == 0)) {
            int difficulty_max = 10;
            int difficult_min = 1;
            int mid = (int)(difficult_min+difficulty_max)/2;
            int p = r.nextInt(mid-difficult_min)+difficult_min;
            int q = r.nextInt(difficulty_max-mid)+mid;
            Log.d("generateQuestion", "p: "+p+" q : " +q);
            question = q + " - " + p+" ?";
            choiceArray[0] = q-p+r.nextInt(difficulty_max-difficult_min)+1;
            choiceArray[1] = p+q;
            choiceArray[2] = (q-p)/2;
            choiceArray[3] = q-p;
            Log.d("generateQuestion", "a1: " +choiceArray[0]+" a2: " +choiceArray[1] + " a3: " +choiceArray[2] + " a4: " +choiceArray[3]);
        }
        updateView();


    }

    private void validateAnswer() {
        boolean success = false;
        total_attempts += 1;
        if (a==choiceArray[3]) {
            Log.d("validateAnswer","validation failed");
            success=true;
            showSucessFailure(success);
            updateScore(1);
            generateQuestion();
        } else {
            Log.d("validateAnswer","validation failed");
            success = false;
            showSucessFailure(success);
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
        ((TextView) findViewById(R.id.score)).setText(" Score: "+ score + "/" + total_attempts);
        ((TextView) findViewById(R.id.question)).setText(question);
        ((Button) findViewById(R.id.button1)).setText(Integer.toString(tempArray[0]));
        ((Button) findViewById(R.id.button2)).setText(Integer.toString(tempArray[1]));
        ((Button) findViewById(R.id.button3)).setText(Integer.toString(tempArray[2]));
        ((Button) findViewById(R.id.button4)).setText(Integer.toString(tempArray[3]));
    }

/*    public void onRadioButtonClicked(View view) {
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
    }*/

    private void showSucessFailure(boolean success) {
        int sound, image, time;
        String text;
        if (success) {
            sound = R.raw.rightapplause;
            image = R.drawable.happysmiley;
            text = "You Are Right!";
            time = 1000;

        } else {
            sound = R.raw.wrongbuzzer;
            image = R.drawable.worriedsmiley;
            text = "Try Again";
            time = 500;
        }
        final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), sound);

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        ImageView toast_image = (ImageView) layout.findViewById(R.id.toast_image);
        toast_image.setImageResource(image);

        TextView toast_message = (TextView) layout.findViewById(R.id.toast_message);
        toast_message.setText(text);

        final Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        mp.start();
        toast.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
                mp.stop();
            }
        }, time);
    }
}
