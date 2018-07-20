package com.mick.example.vips.utilities;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattServer;
import android.bluetooth.BluetoothGattServerCallback;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import static android.bluetooth.BluetoothGatt.GATT_SUCCESS;
import static android.bluetooth.BluetoothGattCharacteristic.PROPERTY_NOTIFY;
import static android.bluetooth.BluetoothGattCharacteristic.PROPERTY_READ;
import static android.bluetooth.BluetoothGattDescriptor.PERMISSION_READ;
import static android.bluetooth.BluetoothGattDescriptor.PERMISSION_WRITE;
import static android.bluetooth.BluetoothGattService.SERVICE_TYPE_PRIMARY;

public class GattServer {
    private BluetoothManager mBluetoothManager;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothLeAdvertiser mBluetoothLeAdvertiser;
    private BluetoothGattServer mGattServer;
    private BluetoothGattServerCallback mGattServerCallback;
    private ArrayList<BluetoothDevice> devices = new ArrayList<>();
    private AppManager mAppManager;

    public Context mContext;

    public GattServer(Context context) {
        mContext = context;
        mAppManager = new AppManager(mContext);
        mBluetoothManager = (BluetoothManager) mContext.getSystemService(mContext.BLUETOOTH_SERVICE);
        bluetoothAdapter = mBluetoothManager.getAdapter();
    }

    public void startAdvertising() {
// Some advertising settings. We don't set an advertising timeout
// since our device is always connected to AC power.
        AdvertiseSettings settings = new AdvertiseSettings.Builder()
                .setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_BALANCED)
                .setConnectable(true)
                .setTimeout(0)
                .setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_MEDIUM)
                .build();

// Defines which service to advertise.
        AdvertiseData data = new AdvertiseData.Builder()
                .setIncludeDeviceName(true)
                .setIncludeTxPowerLevel(false)
                .build();

// Starts advertising.
        mBluetoothLeAdvertiser = bluetoothAdapter.getBluetoothLeAdvertiser();
        mBluetoothLeAdvertiser.startAdvertising(settings, data, mAdvertiseCallback);
    }

    public BluetoothGattService createService() {
        BluetoothGattService service = new BluetoothGattService(mAppManager.SERVICE_UUID, SERVICE_TYPE_PRIMARY);

        // Counter characteristic (read-only, supports subscriptions)
        BluetoothGattCharacteristic counter = new BluetoothGattCharacteristic(mAppManager.CHARACTERISTIC_COUNTER_UUID, PROPERTY_READ | PROPERTY_NOTIFY, PERMISSION_READ);
        BluetoothGattDescriptor counterConfig = new BluetoothGattDescriptor(mAppManager.DESCRIPTOR_CONFIG_UUID, PERMISSION_READ | PERMISSION_WRITE);
        counter.addDescriptor(counterConfig);

        service.addCharacteristic(counter);
        mGattServerCallback = new BluetoothGattServerCallback() {
            @Override
            public void onConnectionStateChange(BluetoothDevice device, int status, int newState) {
                super.onConnectionStateChange(device, status, newState);
                Log.i(mAppManager.TAG, "onConnectionStateChange: " + newState);
                devices.add(device);
            }

            @Override
            public void onServiceAdded(int status, BluetoothGattService service) {
                super.onServiceAdded(status, service);
                Log.i(mAppManager.TAG, "onServiceAdded");
            }

            @Override
            public void onCharacteristicReadRequest(BluetoothDevice device, int requestId, int offset, BluetoothGattCharacteristic characteristic) {
                super.onCharacteristicReadRequest(device, requestId, offset, characteristic);
                Log.i(mAppManager.TAG, "onCharacteristicReadRequest");
                if (mAppManager.CHARACTERISTIC_COUNTER_UUID.equals(characteristic.getUuid())) {
                    mGattServer.notifyCharacteristicChanged(device, characteristic, false);
                    mGattServer.sendResponse(device, requestId, GATT_SUCCESS, 0, characteristic.getValue());
                }
            }

            @Override
            public void onCharacteristicWriteRequest(BluetoothDevice device, int requestId, BluetoothGattCharacteristic characteristic, boolean preparedWrite, boolean responseNeeded, int offset, byte[] value) {
                super.onCharacteristicWriteRequest(device, requestId, characteristic, preparedWrite, responseNeeded, offset, value);
                Log.i(mAppManager.TAG, "onCharacteristicWriteRequest");
            }

            @Override
            public void onDescriptorReadRequest(BluetoothDevice device, int requestId, int offset, BluetoothGattDescriptor descriptor) {
                super.onDescriptorReadRequest(device, requestId, offset, descriptor);
                Log.i(mAppManager.TAG, "onDescriptorReadRequest");
            }

            @Override
            public void onDescriptorWriteRequest(BluetoothDevice device, int requestId, BluetoothGattDescriptor descriptor, boolean preparedWrite, boolean responseNeeded, int offset, byte[] value) {
                super.onDescriptorWriteRequest(device, requestId, descriptor, preparedWrite, responseNeeded, offset, value);
                Log.i(mAppManager.TAG, "onDescriptorWriteRequest");
                if (mAppManager.DESCRIPTOR_CONFIG_UUID.equals(descriptor.getUuid())) {
                    if (responseNeeded) {
                        mGattServer.sendResponse(device, requestId, GATT_SUCCESS, 0, null);
                    }
                }
            }

            @Override
            public void onExecuteWrite(BluetoothDevice device, int requestId, boolean execute) {
                super.onExecuteWrite(device, requestId, execute);
                Log.i(mAppManager.TAG, "onExecuteWrite");
            }

            @Override
            public void onNotificationSent(BluetoothDevice device, int status) {
                super.onNotificationSent(device, status);
                Log.i(mAppManager.TAG, "onNotificationSent");
            }

            @Override
            public void onMtuChanged(BluetoothDevice device, int mtu) {
                super.onMtuChanged(device, mtu);
                Log.i(mAppManager.TAG, "onMtuChanged");
            }

            @Override
            public void onPhyUpdate(BluetoothDevice device, int txPhy, int rxPhy, int status) {
                super.onPhyUpdate(device, txPhy, rxPhy, status);
                Log.i(mAppManager.TAG, "onPhyUpdate");
            }

            @Override
            public void onPhyRead(BluetoothDevice device, int txPhy, int rxPhy, int status) {
                super.onPhyRead(device, txPhy, rxPhy, status);
                Log.i(mAppManager.TAG, "onPhyRead");
            }
        };
        return service;
    }

    public void startServer() {
        startAdvertising();
        BluetoothGattService services = createService();
        mGattServer = mBluetoothManager.openGattServer(mContext, mGattServerCallback);
        mGattServer.addService(services);
    }

    public void updateCharacteristicValue(String value) {
        BluetoothGattCharacteristic characteristic = mGattServer.
                getService(mAppManager.SERVICE_UUID).
                getCharacteristic(mAppManager.CHARACTERISTIC_COUNTER_UUID);
        characteristic.setValue(value);
    }

    public void notifyAllDevices() {
        BluetoothGattCharacteristic characteristic = mGattServer.
                getService(mAppManager.SERVICE_UUID).
                getCharacteristic(mAppManager.CHARACTERISTIC_COUNTER_UUID);
        if (devices.size() != 0) {
            for (int i = 0; i < devices.size(); i++) {
                mGattServer.notifyCharacteristicChanged(devices.get(i), characteristic, false);
            }
        }
    }

    public void releaseResource() {
        mBluetoothLeAdvertiser.stopAdvertising(mAdvertiseCallback);
        mGattServer.clearServices();
        mGattServer.close();
    }

    private AdvertiseCallback mAdvertiseCallback = new AdvertiseCallback() {
        @Override
        public void onStartSuccess(AdvertiseSettings settingsInEffect) {
            Log.i(mAppManager.TAG, "LE Advertise Started.");
        }

        @Override
        public void onStartFailure(int errorCode) {
            Log.w(mAppManager.TAG, "LE Advertise Failed: " + errorCode);
        }
    };
}
