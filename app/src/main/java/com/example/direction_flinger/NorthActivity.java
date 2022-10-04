package com.example.direction_flinger;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

public class NorthActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener, SensorEventListener {
    private GestureDetectorCompat mDetector;
    float initialX, initialY;
    private SensorManager sensorManager;

    private float lastX;
    private float lastY;
    private float lastZ;
    private float geoX;
    private float geoY;
    private float geoZ;

    private ImageView imageShake;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_north);

        mDetector=new GestureDetectorCompat(this,this);
        mDetector.setOnDoubleTapListener(this);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
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
                //Log.d("Msg","lift up");
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
                        Intent wIntent = new Intent(this, WestActivity.class);
                        this.startActivity(wIntent);
                        overridePendingTransition(R.anim.fade_in, 0);
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
                        //remain in north activity
                        break;
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


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if(sensorEvent.sensor.getType()  == Sensor.TYPE_ACCELEROMETER) {
            lastX = sensorEvent.values[0];
            lastY = sensorEvent.values[1];
            lastZ = sensorEvent.values[2];
        }else if(sensorEvent.sensor.getType()  == Sensor.TYPE_GRAVITY) {
            geoX = sensorEvent.values[0];
            geoY = sensorEvent.values[1];
            geoZ = sensorEvent.values[2];
        }
        float shakeX = Math.abs(Math.abs(lastX)-Math.abs(geoX));
        float shakeY = Math.abs(Math.abs(lastY)-Math.abs(geoY));
        float shakeZ = Math.abs(Math.abs(lastY)-Math.abs(geoY));

        //if "significant" shake in x or y or z direction
        if( shakeX > 11 || shakeY > 11 || shakeZ> 11){
            final Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake);
            imageShake = (ImageView) findViewById(R.id.image);
            imageShake.startAnimation(animShake);
            }
        }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}
