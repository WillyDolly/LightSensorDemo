package com.popland.pop.lightsensordemo;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
SensorManager sensorManager;
Sensor light;
TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.tv);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null)//detect light sensor at runtime without manifest declaration
           light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float illuminance = sensorEvent.values[0];//light return 1 value range from 2.0 to > 1000(dark->bright)
        tv.setText(illuminance+"");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,light,SensorManager.SENSOR_DELAY_NORMAL);//onSensorChanged called every 200 ms
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
