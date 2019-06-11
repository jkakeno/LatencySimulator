package com.denso.latencysimulator;

import android.annotation.SuppressLint;
import android.os.Message;
import android.support.v4.app.FragmentManager;
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

import java.util.LinkedHashMap;

public class MainActivity extends AppCompatActivity implements SettingSaveListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String ROOT = "root";
    private static final String SETTING_FRAGMENT = "setting_fragment";

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
    TextView timeMsg;

    Stopwatch timer = new Stopwatch();
    Handler handler;
    FragmentManager fragmentManager;
    LinkedHashMap<String,SettingItem> settings;

    long elapseTime;
    boolean isLocked;
    String lstBtn;

    @SuppressLint("HandlerLeak")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        settings = new Settings().getDefaultSetting();

        //TODO: Add database to store settings

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
        timeMsg = findViewById(R.id.time);

        ignition.toggle();

        ignition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!timer.running) {
                    ignition.setClickable(false);
                    unlock.setClickable(false);
                    lock.setClickable(false);
                    lstBtn = getResources().getString(R.string.ignition);
                    handler.sendEmptyMessage(MSG_START_TIMER);
                    statusMsg.setText("");
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
                    lstBtn = getResources().getString(R.string.door);
                    lock.setAlpha(.5f);
                    handler.sendEmptyMessage(MSG_START_TIMER);
                    statusMsg.setText("");
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
                    lstBtn = getResources().getString(R.string.door);
                    unlock.setAlpha(.5f);
                    handler.sendEmptyMessage(MSG_START_TIMER);
                    statusMsg.setText("");
                    statusMsg.setVisibility(View.VISIBLE);
                }
            }
        });


        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case MSG_START_TIMER:
                        timer.start();
                        handler.sendEmptyMessage(MSG_UPDATE_TIMER);
                        break;

                    case MSG_UPDATE_TIMER:
                        Log.d("Time: ", String.valueOf(timer.getElapsedTimeSecs()));
                        elapseTime = timer.getElapsedTime();
                        timeMsg.setText(String.valueOf(timer.getElapsedTimeSecs()) + " " + getResources().getString(R.string.sec_unit));

                        setSequence(settings);

                        handler.sendEmptyMessageDelayed(MSG_UPDATE_TIMER,REFRESH_RATE); //text view is updated every second,
                        break;

                    case MSG_STOP_TIMER:
                        handler.removeMessages(MSG_UPDATE_TIMER);
                        timer.stop();
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

    public void setSequence(LinkedHashMap<String,SettingItem> settings){
        SettingItem remoteCmd = settings.get(getResources().getString(R.string.remote_cmd));
        SettingItem postCmd = settings.get(getResources().getString(R.string.post_cmd));
        SettingItem spedD = settings.get(getResources().getString(R.string.spec_d));
        SettingItem sms = settings.get(getResources().getString(R.string.sms));
        SettingItem accessTSC = settings.get(getResources().getString(R.string.access_tsc));
        SettingItem downloadCmd = settings.get(getResources().getString(R.string.download_cmd));
        SettingItem uploadResult = settings.get(getResources().getString(R.string.upload_result));
        SettingItem callback4 = settings.get(getResources().getString(R.string.callback4));
        SettingItem remoteCmdRes = settings.get(getResources().getString(R.string.remote_cmd_res));

        long remoteCmdWait = remoteCmd.getWait();
        long remoteCmdDuration = remoteCmd.getDuration();
        long postCmdWait = postCmd.getWait();
        long postCmdDuration = postCmd.getDuration();
        long specDWait = spedD.getWait();
        long specDDuration = spedD.getDuration();
        long smsWait = sms.getWait();
        long smsDuration = sms.getDuration();
        long accessTSCWait = accessTSC.getWait();
        long accessTSCDuration = accessTSC.getDuration();
        long downloadCmdWait = downloadCmd.getWait();
        long downloadCmdDuration = downloadCmd.getDuration();
        long uploadResultWait = uploadResult.getWait();
        long uploadResultDuration = uploadResult.getDuration();
        long callback4Wait = callback4.getWait();
        long callback4Duration = callback4.getDuration();
        long remoteCmdResWait = remoteCmdRes.getWait();
        long remoteCmdResDuration = remoteCmdRes.getDuration();

        //Default values
        long postAckWait = 0;
        long smsAck1Wait = 0;
        long smsAck1Duration = 0;
        long smsAck2Wait = 0;
        long smsAck2Duration = 0;
        long callback1Wait = 0;
        long callback2Wait = 0;
        long callback3Wait = 0;

        long remoteCmdStart = remoteCmdWait;
        long postCmdStart = remoteCmdStart + remoteCmdDuration + postCmdWait;
        long postAckStart = postCmdStart + postCmdDuration + postAckWait;
        long specDStart = postCmdStart + postCmdDuration + specDWait;
        long callback1Start = specDStart + callback1Wait;
        long smsStart = specDStart + specDDuration + smsWait;
        long smsAck1Start = smsStart + smsDuration + smsAck1Wait;
        long smsAck2Start = smsAck1Start + smsAck1Duration + smsAck2Wait;
        long callback2Start = smsAck2Start + smsAck2Duration + callback2Wait;
        long accessTSCStart = smsAck1Start + accessTSCWait;
        long downloadCmdStart = accessTSCStart + accessTSCDuration + downloadCmdWait;
        long callback3Start = downloadCmdStart + callback3Wait;
        long uploadResultStart = downloadCmdStart + downloadCmdDuration + uploadResultWait;
        long callback4Start = uploadResultStart + uploadResultDuration + callback4Wait;
        long remoteCmdResStart = callback4Start + callback4Duration + remoteCmdResWait;
        long endSequence = remoteCmdResStart + remoteCmdResDuration;
        long resetDrawables = endSequence + 2000; //Arbritrary 2 sec delay


        if(remoteCmdWait < elapseTime){
            this.remoteCmd.setBackgroundResource(R.drawable.arrow_right_on);
        }
        if(postCmdStart < elapseTime){
            this.postCmd.setBackgroundResource(R.drawable.arrow_right_on);
        }
        if(postAckStart < elapseTime){
            this.postAck.setBackgroundResource(R.drawable.arrow_left_on);
        }
        if(specDStart < elapseTime){
            this.specD.setBackgroundResource(R.drawable.arrow_right_on);
        }
        if(callback1Start < elapseTime){
            callbackPhase1.setBackgroundResource(R.drawable.arrow_left_on);
            statusMsg.setText(getResources().getString(R.string.contacting_vehicle));
        }
        if(smsStart < elapseTime){
            this.sms.setBackgroundResource(R.drawable.arrow_right_on);
        }
        if(smsAck1Start < elapseTime){
            smsAck1.setBackgroundResource(R.drawable.arrow_left_on);
        }
        if(smsAck2Start < elapseTime){
            smsAck2.setBackgroundResource(R.drawable.arrow_left_on);
        }
        if(callback2Start < elapseTime){
            callbackPhase2.setBackgroundResource(R.drawable.arrow_left_on);
            statusMsg.setText(getResources().getString(R.string.sending_request));
        }
        if(accessTSCStart < elapseTime){
            this.accessTSC.setBackgroundResource(R.drawable.arrow_left_on);
        }
        if(downloadCmdStart < elapseTime){
            this.downloadCmd.setBackgroundResource(R.drawable.arrow_right_on);
        }
        if(callback3Start < elapseTime){
            callbackPhase3.setBackgroundResource(R.drawable.arrow_left_on);
            statusMsg.setText(getResources().getString(R.string.processing_request));
        }
        if(uploadResultStart < elapseTime){
            this.uploadResult.setBackgroundResource(R.drawable.arrow_left_on);
        }
        if(callback4Start < elapseTime){
            this.callbackPhase4.setBackgroundResource(R.drawable.arrow_left_on);
        }
        if(remoteCmdResStart < elapseTime){
            remoteCmdResponse.setBackgroundResource(R.drawable.arrow_left_on);
        }
        if(endSequence < elapseTime){
            switch (lstBtn){
                case "ignition":
                    if(!ignition.isChecked()){
                        statusMsg.setText(getResources().getString(R.string.engine_is_on));
                    }else{
                        statusMsg.setText(getResources().getString(R.string.engine_is_off));
                    }
                    break;
                case "door":
                    if(!isLocked){
                        statusMsg.setText(getResources().getString(R.string.vehicle_locked));
                        unlock.setAlpha(1.0f);
                        lock.setAlpha(1.0f);
                    }else{
                        statusMsg.setText(getResources().getString(R.string.vehicle_unlocked));
                        unlock.setAlpha(1.0f);
                        lock.setAlpha(1.0f);
                    }
                    break;
            }
        }
        if(resetDrawables < elapseTime){
            resetDrawables();
            statusMsg.setVisibility(View.INVISIBLE);
            handler.sendEmptyMessage(MSG_STOP_TIMER);
        }
    }


    public void resetDrawables() {
        timeMsg.setText("");
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

        if (item.getItemId() == R.id.settings){
            Log.d("Menu selected is: ", "Settings");

            SettingFragment settingFragment = SettingFragment.newInstance(settings);
            fragmentManager.beginTransaction().add(R.id.root,settingFragment,SETTING_FRAGMENT).addToBackStack(ROOT).commit();

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {

    }


    @Override
    public void onSettingsSaveInteraction(LinkedHashMap<String,SettingItem> settings) {
        this.settings = settings;
        fragmentManager.popBackStackImmediate();
    }
}
