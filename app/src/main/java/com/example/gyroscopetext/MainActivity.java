package com.example.gyroscopetext;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private TextView gyro;
    private SensorManager mSensorManager;
    private Sensor mGyroscope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gyro = (TextView)findViewById(R.id.gyroscope);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    @Override
    protected void onResume(){
        super.onResume();
        mSensorManager.registerListener(this, mGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        String values = "X-axis:"+String.valueOf(sensorEvent.values[0])+"\n"
                + "Y-axis:"+String.valueOf(sensorEvent.values[1])+"\n"
                + "Z-axis:"+String.valueOf(sensorEvent.values[2])+"\n";
        gyro.setText(values);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}