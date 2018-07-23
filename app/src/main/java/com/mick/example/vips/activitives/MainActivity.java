package com.mick.example.vips.activitives;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.wear.widget.drawer.WearableDrawerLayout;
import android.support.wear.widget.drawer.WearableNavigationDrawerView;
import android.view.View;

import com.mick.example.vips.R;
import com.mick.example.vips.adapters.NavigationDrawerAdapter;
import com.mick.example.vips.domains.Gesture;
import com.mick.example.vips.domains.SensorData;
import com.mick.example.vips.fragments.DjembeFragment;
import com.mick.example.vips.fragments.SettingFragment;
import com.mick.example.vips.utilities.AppManager;
import com.mick.example.vips.utilities.GattServer;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private AppManager mAppManager;
    FragmentTransaction ft;

    private WearableDrawerLayout mWearableDrawerLayout;
    private WearableNavigationDrawerView mWearableNavigationDrawer;
    int DrawerCount = 2;

    public static final int sensorRate = 10000; //100Hz

    private final double sensorThreshold = 240;

    private double lastX, lastY, lastZ;
    private SensorManager mSensorManager;
    private Sensor accelerometer;
    private Sensor gyroscope;
    private RecordStatus mRecordStatus;

    private long recordStartTimeStamp;
    private double listeningTime = 100;
    private double suspendTime = 200;
    private Gesture currentGesture;

    private GattServer mGattServer;
    private int currentCounterValue;
    Context mContext;
    private int nextPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAppManager = new AppManager(this);
        mContext = this;

        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_fragment_placeholder, new DjembeFragment());
        ft.commit();

        mWearableDrawerLayout = findViewById(R.id.main_drawer_layout);
        // Top navigation drawer
        mWearableNavigationDrawer = findViewById(R.id.top_navigation_drawer);
        mWearableNavigationDrawer.setAdapter(new NavigationDrawerAdapter(this, DrawerCount));
        // Peeks navigation drawer on the top.
        mWearableNavigationDrawer.getController().peekDrawer();
        mWearableNavigationDrawer.addOnItemSelectedListener(new WearableNavigationDrawerView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int pos) {
                nextPosition = pos;
                if (nextPosition == 0) {
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.main_fragment_placeholder, new DjembeFragment());
                    ft.commit();
                } else if (nextPosition == 1) {
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.main_fragment_placeholder, new SettingFragment());
                    ft.commit();
                }
                mAppManager.mLog("Drawer pos: " + pos);
            }
        });

        mGattServer = new GattServer(this);
        mGattServer.startServer();
        currentCounterValue = 0;

        mRecordStatus = new RecordStatus();
        currentGesture = new Gesture(this);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            mSensorManager.registerListener(this, accelerometer, sensorRate);
        } else {
            mAppManager.mLog("We don't do accelerometer here");
        }
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null) {
            gyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            mSensorManager.registerListener(this, gyroscope, sensorRate);
        } else {
            mAppManager.mLog("We don't do gyroscope here");
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_ImageView_djembe:
                mGattServer.updateCharacteristicValue("Click");
                mGattServer.notifyAllDevices();
                break;
            case R.id.setting_quit:
                mGattServer.releaseResource();
                mSensorManager.unregisterListener(this);
                finish();
                break;
            case R.id.setting_reinit:
                mGattServer.reinit();
                break;
            default:
                break;
        }
    }

    private void recordDataByThreshold(android.hardware.SensorEvent sensorEvent) {
        SensorData data = new SensorData("default", 0, 0, 0);
        long time = mAppManager.get_timestamp();
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            data = new SensorData(
                    "Accelerometer",
                    sensorEvent.values[0],
                    sensorEvent.values[1],
                    sensorEvent.values[2]);
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            data = new SensorData(
                    "Gyroscope",
                    sensorEvent.values[0],
                    sensorEvent.values[1],
                    sensorEvent.values[2]);
        }
        switch (mRecordStatus.getStatus()) {
            case 0:
                //sensor recorder in idle status
                if (data.getSensorName().equalsIgnoreCase("Accelerometer")
                        && data.getAxisEuclideanDestance() > sensorThreshold) {
                    mRecordStatus.setStatus(1);
                    recordStartTimeStamp = time;
                    currentGesture = new Gesture(this);
                }
                break;

            case 1:
                //sensor recorder in listening status
                if ((time - recordStartTimeStamp) >= listeningTime) {
                    mRecordStatus.setStatus(2);

                    //Data segmentation
                    currentGesture.calculateFeatures();

//                    Classification
                    String ret = currentGesture.modelClassification();

//                    boardcast gesture recognition result
                    if (ret.equalsIgnoreCase("idle")) {
                    } else {
                        mGattServer.updateCharacteristicValue(ret);
                        mGattServer.notifyAllDevices();
                    }
//                    currentCounterValue++;
                } else {
                    if (data.getSensorName().equalsIgnoreCase("Accelerometer")) {
                        currentGesture.accDataList.add(data);
                    } else {
                        currentGesture.gyroDataList.add(data);
                    }
                }
                break;

            case 2:
                //sensor recorder in suspend status
                if ((time - recordStartTimeStamp - listeningTime) >= suspendTime) {
                    mRecordStatus.setStatus(0);
                    recordStartTimeStamp = 0;
                }
                break;
            default:
                mAppManager.mLog("Default out");
                break;
        }
    }

    @Override
    public void onSensorChanged(android.hardware.SensorEvent sensorEvent) {
        recordDataByThreshold(sensorEvent);
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onDestroy() {
        mSensorManager.unregisterListener(this);
        mGattServer.releaseResource();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, accelerometer, sensorRate);
        mSensorManager.registerListener(this, gyroscope, sensorRate);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    public class RecordStatus {
        private int status;

        public RecordStatus() {
            status = 0;
        }


        public int getStatus() {
            return status;
        }

        public void setStatus(int value) {
            status = value;
        }

    }
}
