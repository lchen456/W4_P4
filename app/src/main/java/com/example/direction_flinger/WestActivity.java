package com.example.direction_flinger;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

public class WestActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private GestureDetectorCompat mDetector;
    float initialX, initialY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_west);

        mDetector=new GestureDetectorCompat(this,this);
        mDetector.setOnDoubleTapListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch (action) {
            case (MotionEvent.ACTION_DOWN):
                initialX = event.getX();
                initialY = event.getY();
                break;
            case (MotionEvent.ACTION_UP):
                Log.d("Msg","lift up");
                float finalX = event.getX();
                float finalY = event.getY();

                //if more change happened in x direction than y direction
                if(Math.abs(finalX - initialX) > Math.abs(finalY - initialY)){
                    //Horizontal directions
                    if(finalX > initialX){
                        Intent eIntent = new Intent(this, EastActivity.class);
                        this.startActivity(eIntent);
                        overridePendingTransition(R.anim.fade_in, 0);
                    }
                    if(finalX < initialX){
                        //remain on west activity
                        break;
                    }
                }
                //else more changed happened in y direction
                else{
                    //Vertical Directions
                    if(finalY > initialY){
                        //Log.d("direction", "direction is down");
                        Intent sIntent = new Intent(this, SouthActivity.class);
                        this.startActivity(sIntent);
                        overridePendingTransition(R.anim.fade_in, 0);
                    }
                    if(finalY < initialY){
                        //Log.d("direction", "direction is up");
                        Intent nIntent = new Intent(this, NorthActivity.class);
                        this.startActivity(nIntent);
                        overridePendingTransition(R.anim.fade_in, 0);
                    }
                }
                break;
        }

        if (this.mDetector.onTouchEvent(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float velocityX, float velocityY) {
        //Log.d("direction", String.valueOf(velocityY));
        return false;
    }
}
