package com.practice.elanorhoak.practiceappfactions;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Elanor on 8/1/2017.
 */

public class BluetoothActivity extends AppCompatActivity {
    //set up Bluetooth adapter
    BluetoothAdapter mBluetoothAdapter;

    //Create a broadcastReciever for ACTION_FOUND
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action.equals(mBluetoothAdapter.ACTION_STATE_CHANGED)){
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, mBluetoothAdapter.ERROR);

                switch(state){
                    case BluetoothAdapter.STATE_OFF:
                        Toast.makeText(getApplicationContext(),"Bluetooth turned OFF",Toast.LENGTH_SHORT).show();
                        break;
                    case BluetoothAdapter.STATE_ON:
                        Toast.makeText(getApplicationContext(),"Bluetooth turned ON",Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            /*
            //when discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)){
                //get BuetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableArrayExtra(BluetoothDevice.EXTRA_DEVICE);
                //add the name and address to an array adapter to show in a ListView
                mArrayAdapter.add(device.getName() + "/n" + device.getAddress());
            }
            */
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        //create button
        //Button btnONOFF = (Button) findViewById(R.id.btnONOFF);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        /*
        btnONOFF.setOnClickListener(new View.OnClickListener()){
            //@Override
            public void onClick(View view){
                enableDisableBT();
            }
        };
        */
    }

    public void checkBT(View view){
        if(mBluetoothAdapter == null){
            Toast.makeText(getApplicationContext(),"This device does not support Bluetooth",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Tada! Bluetooth works!",Toast.LENGTH_SHORT).show();
        }
    }

    //turn on/off bluetooth
    public void enableDisableBT(View view){

            //if BT not enabled, enable it
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBTIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivity(enableBTIntent);

                IntentFilter BTIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
                registerReceiver(mBroadcastReceiver, BTIntent);
            } else {
                mBluetoothAdapter.disable();

                IntentFilter BTIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
                registerReceiver(mBroadcastReceiver, BTIntent);
            }
    }

    //when button pressed, go to BlinkingAnimals (Factions)
    public void gotoFactions(View view){
        Intent intent = new Intent(this, Factions.class);
        startActivity(intent);
    }

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }
}
