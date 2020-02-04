package com.example.squareandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
public class MainActivity extends AppCompatActivity {
    View vwsqaure;
    //View v2;
    // DisplayMetrics dm = new DisplayMetrics();
//windowManager.defaultDisplay.getMetrics(displayMetrics)
    TextView txtCounter;
    Timer timer = new Timer();
    //    Display display = getWindowManager().getDefaultDisplay();
    Boolean isClockwise = true;
    Boolean goToTop = false;
    Boolean goToBottom = false;
    Boolean goToLeft = false;
    Boolean goToRight = true;
    //    Integer screenWidth = dm.widthPixels;
//    Integer screenHeight = dm.heightPixels;
    RelativeLayout relativeLayout;
    Integer Count = 0;
    Random rand = new Random();
    int r = rand.nextInt(254)+1;
    int g = rand.nextInt(254)+1;
    int b = rand.nextInt(254)+1;
    int randomColor = Color.rgb(r,g,b);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vwsqaure = findViewById(R.id.vwSq);
        txtCounter = findViewById(R.id.txtCount);
        relativeLayout = findViewById(R.id.relLay);
        //v2 = findViewById(R.id.view2);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run()
            {
                moveView();
            }
        },0,4);
    }
    public  void moveView()
    {
        int screenHeight = relativeLayout.getHeight()-100;
        int screenWidth = relativeLayout.getWidth()-100;
        float xPosition = vwsqaure.getX();
        float yPosition = vwsqaure.getY();
        if (isClockwise) {
            if (xPosition > screenWidth - 55) {
                resetBools();
                goToBottom = true;
            }
            if (yPosition > screenHeight - 55) {
                resetBools();
                goToLeft = true;
            }
            if (goToLeft && xPosition < 10) {
                resetBools();
                goToTop = true;
            }
            if (goToTop && yPosition < 50) {
                resetBools();
                goToBottom = true;
                isClockwise = false;
                vwsqaure.setBackgroundColor(randomColor);
                //viewBox.backgroundColor = .random
                Count += 1;
                txtCounter.setText(String.valueOf(Count));
                // v2.setTextAlignment(Count);
                //viewLbl.text = "\(counter)"
            }
        }else {
            if (goToBottom && yPosition > screenHeight - 55) {
                resetBools();
                goToRight = true;
            }
            if (goToRight && xPosition > screenWidth - 55) {
                resetBools();
                goToTop = true;
            }
            if (goToTop && yPosition < 50) {
                resetBools();
                goToLeft = true;
            }
            if (goToLeft && xPosition < 10) {
                resetBools();
                goToRight = true;
                isClockwise = true;
                vwsqaure.setBackgroundColor(randomColor);
                //viewBox.backgroundColor = .random
                Count += 1;
                //viewLbl.text = "\(counter)"
                txtCounter.setText(String.valueOf(Count));
            }
        }
        if (goToBottom) {
            yPosition += 10;
        } else if (goToTop) {
            yPosition -= 10;
        } else if (goToLeft) {
            xPosition -= 10;
        } else if (goToRight) {
            xPosition += 10;
        }
        //print("x position \(xPosition)")
        //print("y position \(yPosition)")
//        let width = viewBox.frame.size.width
//        let height = viewBox.frame.size.height
        vwsqaure.setX(xPosition);
        vwsqaure.setY(yPosition);
        //self.viewBox.frame = CGRect(x: xPosition, y: yPosition, width: width, height: height)
    }
    private void resetBools() {
        goToTop = false;
        goToBottom = false;
        goToLeft = false;
        goToRight = false;
    }
}