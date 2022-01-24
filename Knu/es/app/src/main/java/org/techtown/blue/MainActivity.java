package org.techtown.blue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    BluetoothAdapter btAdapter;
    private final static int REQUSET_ENABLE_BT = 1;

    TextView textSatus;
    Button btnPaired, btnSearch, btnSend1, btnSend2;
    ListView listView;
    Set<BluetoothDevice> pairedDevices;
    ArrayAdapter<String> btArrayAdapter;
    ArrayList<String> deviceAddressArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] permissin_list = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};
        ActivityCompat.requestPermissions(MainActivity.this, permissin_list, 1);

        btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!btAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUSET_ENABLE_BT);
        }

        textSatus = (TextView) findViewById(R.id.text_status);
        btnPaired = (Button) findViewById(R.id.btn_paired);
        btnSearch = (Button) findViewById(R.id.search);
        btnSend1 = (Button) findViewById(R.id.btn_send1);
        btnSend2 = (Button) findViewById(R.id.btn_send2);
        listView = (ListView) findViewById(R.id.listview);

        btArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        deviceAddressArray = new ArrayList<>();
        listView.setAdapter(btArrayAdapter);

        btnPaired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btArrayAdapter.clear();
                if (deviceAddressArray != null && !deviceAddressArray.isEmpty()) {
                    deviceAddressArray.clear();
                }
                pairedDevices = btAdapter.getBondedDevices();
                if (pairedDevices.size() > 0) {
                    for (BluetoothDevice device : pairedDevices) {
                        String deviceName = device.getName();
                        String deviceHardwareAddress = device.getAddress();
                        btArrayAdapter.add(deviceName);
                        deviceAddressArray.add(deviceHardwareAddress);
                    }
                }
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btAdapter.isDiscovering()) {
                    btAdapter.cancelDiscovery();
                } else {
                    if (btAdapter.isEnabled()) {
                        btAdapter.startDiscovery();
                        if (deviceAddressArray != null && !deviceAddressArray.isEmpty()) {
                            deviceAddressArray.clear();
                        }
                        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                        registerReceiver(receiver, filter);
                    } else {
                        Toast.makeText(getApplicationContext(), "Bluetooth not on", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        listView.setOnItemClickListener(new myOnItemClickListner());
    }
    public class myOnItemClickListner implements AdapterView.OnItemClickListener{
        BluetoothSocket btSocket = null;

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(getApplicationContext(), btArrayAdapter.getItem(i), Toast.LENGTH_SHORT).show();
            textSatus.setText("try...");

            final String name = btArrayAdapter.getItem(i);
            final String address = deviceAddressArray.get(i);
            boolean flag = true;

            BluetoothDevice device = btAdapter.getRemoteDevice(address);

            try{
                btSocket.connect();
            }catch (IOException e) {
                flag = false;
                textSatus.setText("connection failed");
                e.printStackTrace();
            }

            if(flag) {
                textSatus.setText("connected to "+ name);
                ConnectedThread connetedThread = new ConnectedThread(btSocket);
                connetedThread.start();
                btnSend1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(connetedThread!=null) {
                            connetedThread.write("1");
                        }
                    }
                });
                btnSend2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(connetedThread!=null) {
                            connetedThread.write("2");
                        }
                    }
                });
            }
        }
    }

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                String deviceHardwardAddress = device.getAddress();
                btArrayAdapter.add(deviceName);
                deviceAddressArray.add(deviceHardwardAddress);
                btArrayAdapter.notifyDataSetChanged();
            }
        }
    };

    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}