package com.mick.example.vips.utilities;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class AppManager {
    private Context app;

    public AppManager(Context app) {
        this.app = app;
    }

    public final UUID SERVICE_UUID = UUID.fromString("795090c7-420d-4048-a24e-18e60180e23c");
    public final UUID CHARACTERISTIC_COUNTER_UUID = UUID.fromString("31517c58-66bf-470c-b662-e352a6c80cba");
    public final UUID DESCRIPTOR_CONFIG_UUID = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
    public final String TAG = "vips";


    public long get_timestamp() {
        return System.currentTimeMillis();
    }

    public void mLog(String msg) {
        Log.i(TAG, msg);
    }

    public void saveFileIntoDevice(String msg, String fileName) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(getOutputMediaFile(fileName), true);
            String message = msg + "*\n";
            byte[] bytesArray = message.getBytes();

            out.write(bytesArray);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private File getOutputMediaFile(String fileName) {

        File fileStorageDir = new File(app.getExternalFilesDir(null)
                + "/Android/data/"
                + app.getApplicationContext().getPackageName()
                + "/Files");

        // Create the storage directory if it does not exist
        if (!fileStorageDir.exists()) {
            if (!fileStorageDir.mkdirs()) {
                return null;
            }
        }

        File textFile;
        String mFilename = fileName + ".txt";
        textFile = new File(fileStorageDir.getPath() + File.separator + mFilename);
        return textFile;
    }
}
