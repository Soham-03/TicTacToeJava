package com.group7.tictactoe;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flatdialoglibrary.dialog.FlatDialog;

public class GameActivity extends AppCompatActivity {
    ImageView button0,button1,button2,button3,button4,button5,button6,button7,button8;
    MediaPlayer winnerMp,drawMp,tapXMp,tapYMp;
    int activePlayer,gameState = 2,count = 0;
    int[] winCheck = new int[]{2,2,2,2,2,2,2,2,2};
    TextView txtPlayersTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        button0 = findViewById(R.id.button1);
        button1 = findViewById(R.id.button2);
        button2 = findViewById(R.id.button3);
        button3 = findViewById(R.id.button4);
        button4 = findViewById(R.id.button5);
        button5 = findViewById(R.id.button6);
        button6 = findViewById(R.id.button7);
        button7 = findViewById(R.id.button8);
        button8 = findViewById(R.id.button9);
        txtPlayersTurn = findViewById(R.id.txtPlayersTurn);

        activePlayer = getIntent().getIntExtra("activePlayer",0);

        button0.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                tapCheck(button0,0);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                tapCheck(button1,1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                tapCheck(button2,2);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                tapCheck(button3,3);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                tapCheck(button4,4);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                tapCheck(button5,5);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                tapCheck(button6,6);
            }
        });


        button7.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                tapCheck(button7,7);
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                tapCheck(button8,8);
            }
        });

        winnerMp = MediaPlayer.create(this,R.raw.winner);
        drawMp = MediaPlayer.create(this,R.raw.draw);
        tapXMp = MediaPlayer.create(this,R.raw.tap);
        tapYMp = MediaPlayer.create(this,R.raw.tap);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    void tapCheck(ImageView button, int i){
        if(gameState == 2){
            if(activePlayer == 0){
                int resourceId = getResources().getIdentifier("img_x","drawable",getPackageName());
                button.setImageResource(resourceId);
                activePlayer = 1;
                winCheck[i] = 0;
                count++;
                tapXMp.start();
                button.setClickable(false);
                txtPlayersTurn.setText("Player 2's turn");
            }
            else{
                int resourceId = getResources().getIdentifier("img_o","drawable",getPackageName());
                button.setImageResource(resourceId);
                activePlayer = 0;
                winCheck[i] = 1;
                count++;
                tapYMp.start();
                button.setClickable(false);
                txtPlayersTurn.setText("Player 1's turn");
            }
        }
        checkWin(button);
        if(count == 9){
            drawMp.start();
            Toast.makeText(this,"Its a Draw", Toast.LENGTH_SHORT).show();
            final FlatDialog flatDialog = new FlatDialog(GameActivity.this);
            flatDialog.setTitle("Draw")
                    .setFirstButtonColor(R.color.white)
                    .setSecondButtonColor(R.color.white)
                    .setBackgroundColor(getColor(R.color.main))
                    .setFirstButtonText("Try Again")
                    .setSecondButtonText("Exit")
                    .setIcon(R.drawable.award)
                    .withFirstButtonListner(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            recreate();
                        }
                    })
                    .withSecondButtonListner(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finish();
                        }
                    })
                    .isCancelable(false)
                    .show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    void checkWin(ImageView button){
        if((winCheck[0]==0 && winCheck[1]==0 && winCheck[2]==0) ||
                (winCheck[3]==0 && winCheck[4]==0 && winCheck[5]==0) ||
                (winCheck[6]==0 && winCheck[7]==0 && winCheck[8]==0) ||
                (winCheck[0]==0 && winCheck[4]==0 && winCheck[8]==0) ||
                (winCheck[2]==0 && winCheck[4]==0 && winCheck[6]==0) ||
                (winCheck[0]==0 && winCheck[3]==0 && winCheck[6]==0) ||
                (winCheck[1]==0 && winCheck[4]==0 && winCheck[7]==0) ||
                (winCheck[2]==0 && winCheck[5]==0 && winCheck[8]==0)){
            winnerMp.start();

            final FlatDialog flatDialog = new FlatDialog(GameActivity.this);
            flatDialog.setTitle("Congrats Player 1")
                    .setBackgroundColor(getColor(R.color.main))
                    .setFirstButtonColor(R.color.white)
                    .setSecondButtonColor(R.color.white)
                    .setFirstButtonText("Try Again")
                    .setSecondButtonText("Exit")
                    .setIcon(R.drawable.award)
                    .withFirstButtonListner(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            recreate();
                        }
                    })
                    .withSecondButtonListner(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finish();
                        }
                    })
                    .isCancelable(false)
                    .show();

            Toast.makeText(this,"Congratulations",Toast.LENGTH_SHORT).show();

        }
        if((winCheck[0]==1 && winCheck[1]==1 && winCheck[2]==1) ||
                (winCheck[3]==1 && winCheck[4]==1 && winCheck[5]==1) ||
                (winCheck[6]==1 && winCheck[7]==1 && winCheck[8]==1) ||
                (winCheck[0]==1 && winCheck[4]==1 && winCheck[8]==1) ||
                (winCheck[2]==1 && winCheck[4]==1 && winCheck[6]==1) ||
                (winCheck[0]==1 && winCheck[3]==1 && winCheck[6]==1) ||
                (winCheck[1]==1 && winCheck[4]==1 && winCheck[7]==1) ||
                (winCheck[2]==1 && winCheck[5]==1 && winCheck[8]==1)){
            winnerMp.start();

            Toast.makeText(this,"Congratulations",Toast.LENGTH_SHORT).show();

            final FlatDialog flatDialog = new FlatDialog(GameActivity.this);
            flatDialog.setBackgroundColor(getColor(R.color.main))
                    .setFirstButtonColor(R.color.white)
                    .setSecondButtonColor(R.color.white)
                    .setTitle("Congrats Player 2")
                    .setFirstButtonText("Try Again")
                    .setSecondButtonText("Exit")
                    .setIcon(R.drawable.award)
                    .withFirstButtonListner(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            recreate();
                        }
                    })
                    .withSecondButtonListner(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finish();
                        }
                    })
                    .isCancelable(false)
                    .show();

        }
    }

}