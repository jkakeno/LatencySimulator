package com.denso.latencysimulator;

import android.annotation.SuppressLint;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    final int MSG_START_TIMER = 0;
    final int MSG_STOP_TIMER = 1;
    final int MSG_UPDATE_TIMER = 2;
    final int REFRESH_RATE = 100;

    ToggleButton ignition;
    Button unlock;
    Button lock;
    ImageView remoteCmd;
    ImageView postCmd;
    ImageView postAck;
    ImageView specD;
    ImageView sms;
    ImageView smsAck1;
    ImageView smsAck2;
    ImageView accessTSC;
    ImageView downloadCmd;
    ImageView uploadResult;
    ImageView callbackPhase1;
    ImageView callbackPhase2;
    ImageView callbackPhase3;
    ImageView callbackPhase4;
    ImageView remoteCmdResponse;
    TextView statusMsg;

    Stopwatch timer = new Stopwatch();
    Handler mHandler;

    long elapseTime;
    boolean isLocked;
    String lstBtn;

    @SuppressLint("HandlerLeak")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ignition = findViewById(R.id.ig_btn);
        unlock = findViewById(R.id.unlock_btn);
        lock = findViewById(R.id.lock_btn);
        remoteCmd = findViewById(R.id.remote_cmd);
        postCmd = findViewById(R.id.post_cmd);
        postAck = findViewById(R.id.post_ack);
        specD = findViewById(R.id.spec_d);
        sms = findViewById(R.id.sms);
        smsAck1 = findViewById(R.id.sms_ack1);
        smsAck2 = findViewById(R.id.sms_ack2);
        accessTSC = findViewById(R.id.access_to_tsc);
        downloadCmd = findViewById(R.id.download_cmd);
        uploadResult = findViewById(R.id.upload_result);
        callbackPhase1 = findViewById(R.id.callback_phase1);
        callbackPhase2 = findViewById(R.id.callback_phase2);
        callbackPhase3 = findViewById(R.id.callback_phase3);
        callbackPhase4 = findViewById(R.id.callback_phase4);
        remoteCmdResponse = findViewById(R.id.remote_cmd_res);
        statusMsg = findViewById(R.id.status);

        ignition.toggle();

        ignition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!timer.running) {
                    ignition.setClickable(false);
                    unlock.setClickable(false);
                    lock.setClickable(false);
                    lstBtn = "ignition";
                    mHandler.sendEmptyMessage(MSG_START_TIMER);
                    statusMsg.setVisibility(View.VISIBLE);
                }
            }
        });

        unlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!timer.running) {
                    ignition.setClickable(false);
                    unlock.setClickable(false);
                    lock.setClickable(false);
                    isLocked = false;
                    lstBtn = "door";
                    lock.setAlpha(.5f);
                    mHandler.sendEmptyMessage(MSG_START_TIMER);
                    statusMsg.setVisibility(View.VISIBLE);
                }
            }
        });

        lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!timer.running) {
                    ignition.setClickable(false);
                    unlock.setClickable(false);
                    lock.setClickable(false);
                    isLocked = true;
                    lstBtn = "door";
                    unlock.setAlpha(.5f);
                    mHandler.sendEmptyMessage(MSG_START_TIMER);
                    statusMsg.setVisibility(View.VISIBLE);
                }
            }
        });


        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case MSG_START_TIMER:
                        timer.start();
                        mHandler.sendEmptyMessage(MSG_UPDATE_TIMER);
                        break;

                    case MSG_UPDATE_TIMER:
                        Log.d("Time: ", String.valueOf(timer.getElapsedTime()));
                        elapseTime = timer.getElapsedTime();

                        remoteCmd(0,1000);
                        postCmd(1000,1500);
                        postAck(2000,2500);
                        specD(4500,5000);
                        callbackPhase1(4500,5000);
                        sms(10000,15000);
                        smsAck1(15000,17000);
                        smsAck2(17000,17500);
                        callbackPhase2(17500,18000);
                        accessTSC(21000,22000);
                        downloadCmd(22000,25000);
                        callbackPhase3(26000,26500);
                        uploadResult(32000,33000);
                        callbackPhase4(36000,37000);
                        remoteCmdResponse(37000,38000);

                        if(elapseTime>38000){
                            switch (lstBtn){
                                case "ignition":
                                    if(!ignition.isChecked()){
                                        statusMsg.setText("Engine is ON");
                                    }else{
                                        statusMsg.setText("Engine is OFF");
                                    }
                                    break;
                                case "door":
                                    if(!isLocked){
                                        statusMsg.setText("Vehicle Locked");
                                        unlock.setAlpha(1.0f);
                                        lock.setAlpha(1.0f);
                                    }else{
                                        statusMsg.setText("Vehicle Unlocked");
                                        unlock.setAlpha(1.0f);
                                        lock.setAlpha(1.0f);
                                    }
                                    break;
                            }
                        }

                        if(elapseTime>40000){
                            resetDrawables();
                            statusMsg.setVisibility(View.INVISIBLE);
                            mHandler.sendEmptyMessage(MSG_STOP_TIMER);
                        }

                        mHandler.sendEmptyMessageDelayed(MSG_UPDATE_TIMER,REFRESH_RATE); //text view is updated every second,
                        break;

                    case MSG_STOP_TIMER:
                        mHandler.removeMessages(MSG_UPDATE_TIMER);
                        timer.stop();
                        Log.d("Time: ", String.valueOf(timer.getElapsedTime()));
                        ignition.setClickable(true);
                        unlock.setClickable(true);
                        lock.setClickable(true);
                        break;

                    default:
                        break;
                }
            }
        };

    }


    public void remoteCmd(long start, long end) {
        if(start < elapseTime && elapseTime < end) {
            remoteCmd.setBackgroundResource(R.drawable.arrow_right_on);
        }
    }

    public void postCmd(long start, long end){
        if(start < elapseTime && elapseTime < end) {
            postCmd.setBackgroundResource(R.drawable.arrow_right_on);
        }
    }

    public void postAck(long start, long end) {
        if(start< elapseTime && elapseTime <end) {
            postAck.setBackgroundResource(R.drawable.arrow_left_on);
        }
    }

    public void specD(long start, long end) {
        if(start< elapseTime && elapseTime <end) {
            specD.setBackgroundResource(R.drawable.arrow_right_on);
        }
    }

    public void callbackPhase1(long start, long end) {
        if(start< elapseTime && elapseTime <end) {
            callbackPhase1.setBackgroundResource(R.drawable.arrow_left_on);
            statusMsg.setText("Contacting Vehicle");
        }
    }

    public void sms(long start, long end) {
        if(start< elapseTime && elapseTime <end) {
            sms.setBackgroundResource(R.drawable.arrow_right_on);
        }
    }

    public void smsAck1(long start, long end) {
        if(start< elapseTime && elapseTime <end) {
            smsAck1.setBackgroundResource(R.drawable.arrow_left_on);
        }
    }

    public void smsAck2(long start, long end) {
        if(start< elapseTime && elapseTime <end) {
            smsAck2.setBackgroundResource(R.drawable.arrow_left_on);
        }
    }

    public void callbackPhase2(long start, long end) {
        if(start< elapseTime && elapseTime <end) {
            callbackPhase2.setBackgroundResource(R.drawable.arrow_left_on);
            statusMsg.setText("Sending Request");
        }
    }

    public void accessTSC(long start, long end) {
        if(start< elapseTime && elapseTime <end) {
            accessTSC.setBackgroundResource(R.drawable.arrow_left_on);
        }
    }

    public void downloadCmd(long start, long end) {
        if(start< elapseTime && elapseTime <end) {
            downloadCmd.setBackgroundResource(R.drawable.arrow_right_on);
        }
    }

    public void callbackPhase3(long start, long end) {
        if(start< elapseTime && elapseTime <end) {
            callbackPhase3.setBackgroundResource(R.drawable.arrow_left_on);
            statusMsg.setText("Processing Request");
        }
    }

    public void uploadResult(long start, long end) {
        if(start< elapseTime && elapseTime <end) {
            uploadResult.setBackgroundResource(R.drawable.arrow_left_on);
        }
    }

    public void callbackPhase4(long start, long end) {
        if(start< elapseTime && elapseTime <end) {
            callbackPhase4.setBackgroundResource(R.drawable.arrow_left_on);
        }
    }

    public void remoteCmdResponse(long start, long end) {
        if(start< elapseTime && elapseTime <end) {
            remoteCmdResponse.setBackgroundResource(R.drawable.arrow_left_on);
        }
    }


    public void resetDrawables() {
        remoteCmd.setBackgroundResource(R.drawable.arrow_right_off);
        postCmd.setBackgroundResource(R.drawable.arrow_right_off);
        postAck.setBackgroundResource(R.drawable.arrow_left_off);
        specD.setBackgroundResource(R.drawable.arrow_right_off);
        callbackPhase1.setBackgroundResource(R.drawable.arrow_left_off);
        sms.setBackgroundResource(R.drawable.arrow_right_off);
        smsAck1.setBackgroundResource(R.drawable.arrow_left_off);
        smsAck2.setBackgroundResource(R.drawable.arrow_left_off);
        callbackPhase2.setBackgroundResource(R.drawable.arrow_left_off);
        accessTSC.setBackgroundResource(R.drawable.arrow_left_off);
        downloadCmd.setBackgroundResource(R.drawable.arrow_right_off);
        callbackPhase3.setBackgroundResource(R.drawable.arrow_left_off);
        uploadResult.setBackgroundResource(R.drawable.arrow_left_off);
        callbackPhase4.setBackgroundResource(R.drawable.arrow_left_off);
        remoteCmdResponse.setBackgroundResource(R.drawable.arrow_left_off);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.settings){
            Log.d("Menu selected is: ", "Settings");
        }

        return super.onOptionsItemSelected(item);
    }
}
