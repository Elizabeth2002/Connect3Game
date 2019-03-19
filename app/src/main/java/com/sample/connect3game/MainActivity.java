package com.sample.connect3game;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int activePlayer = 0;
    int [][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    LinearLayout linearLayout;
    int timesTapped = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.linearLayout);
        linearLayout.setTranslationY(-1000);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("info","clicked button");
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
    }

    public void dropIn(View view){
        String winner = "";
        timesTapped ++;
        ImageView imageView = (ImageView)view;
        int tappedCounter = Integer.parseInt(imageView.getTag().toString());
        if(gameState[tappedCounter]==2){
            gameState[tappedCounter] = activePlayer;
            imageView.setTranslationY(-1500);
            if(activePlayer==0){
                imageView.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            }else{
                imageView.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            imageView.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for(int[] winningPosition : winningPositions){
                if(gameState[winningPosition[0]]==gameState[winningPosition[1]]&&gameState[winningPosition[1]]==gameState[winningPosition[2]]&&gameState[winningPosition[0]]!=2){

                    if(activePlayer==1){
                        winner = "Congrats!! Yellow player has won the game!";
                    }else
                        winner = "Congrats!! Red player has won the game!";
                    GridLayout gridLayout = findViewById(R.id.gridLayout);
                    TextView textView = findViewById(R.id.textView);
                    gridLayout.animate().scaleX(0.5f).scaleY(.5f).alpha(0).setDuration(1000);
                    textView.setText(winner);
                    linearLayout.animate().translationYBy(1000).setDuration(2000);
                }else{
                    if(timesTapped==9) {
                        winner = "No winners! :( ";
                        GridLayout gridLayout = findViewById(R.id.gridLayout);
                        TextView textView = findViewById(R.id.textView);
                        gridLayout.animate().scaleX(0.5f).scaleY(.5f).alpha(0).setDuration(1000);
                        textView.setText(winner);
                        linearLayout.animate().translationYBy(1000).setDuration(2000);
                    }

                }
            }

        }

    }

}
