package com.denso.latencysimulator;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SettingFragment extends Fragment {

    private static final String TAG = SettingFragment.class.getSimpleName();
    private static final String SETTINGS = "settings";

    View view;
    EditText remoteCmd;
    EditText postCmdWait;
    EditText postCmd;
    EditText specDWait;
    EditText specD;
    EditText smsWait;
    EditText sms;
    EditText tscAccessWait;
    EditText tscAccess;
    EditText downloadCmdWait;
    EditText downloadCmd;
    EditText uploadResultWait;
    EditText uploadResult;
    EditText callback4Wait;
    EditText callback4;
    EditText remoteCmdResWait;
    EditText remoteCmdRes;

    Button okBtn;

    InputMethodManager imm;

    InteractionListener listener;
    Settings settings;


    public SettingFragment() {
        // Required empty public constructor
    }

    public static SettingFragment newInstance(Settings settings) {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putParcelable(SETTINGS, settings);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        if (getArguments() != null) {
            settings = getArguments().getParcelable(SETTINGS);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");

        view = inflater.inflate(R.layout.setting_fragment, container, false);
        remoteCmd = view.findViewById(R.id.remote_cmd_et);
        postCmdWait = view.findViewById(R.id.post_cmd_wait);
        postCmd = view.findViewById(R.id.post_cmd_et);
        specDWait = view.findViewById(R.id.spec_d_wait);
        specD = view.findViewById(R.id.spec_d_et);
        smsWait = view.findViewById(R.id.sms_wait);
        sms = view.findViewById(R.id.sms_et);
        tscAccessWait = view.findViewById(R.id.access_to_tsc_wait);
        tscAccess = view.findViewById(R.id.access_to_tsc_et);
        downloadCmdWait = view.findViewById(R.id.download_cmd_wait);
        downloadCmd = view.findViewById(R.id.download_cmd_et);
        uploadResultWait = view.findViewById(R.id.upload_result_wait);
        uploadResult = view.findViewById(R.id.upload_result_et);
        callback4Wait = view.findViewById(R.id.callback_phase4_wait);
        callback4 = view.findViewById(R.id.callback_phase4_et);
        remoteCmdResWait = view.findViewById(R.id.remote_cmd_wait);
        remoteCmdRes = view.findViewById(R.id.remote_cmd_res_et);
        okBtn = view.findViewById(R.id.ok_btn);

        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
        listener = (InteractionListener) context;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        if (settings!=null){
            remoteCmd.setText(String.valueOf(settings.getRemoteCmdDuration()));
            postCmdWait.setText(String.valueOf(settings.getPostCmdWait()));
            postCmd.setText(String.valueOf(settings.getPostCmdDuration()));
            specDWait.setText(String.valueOf(settings.getSpecDWait()));
            specD.setText(String.valueOf(settings.getSpecDDuration()));
            smsWait.setText(String.valueOf(settings.getSmsWait()));
            sms.setText(String.valueOf(settings.getSmsDuration()));
            tscAccessWait.setText(String.valueOf(settings.getAccessTSCWait()));
            tscAccess.setText(String.valueOf(settings.getAccessTSCDuration()));
            downloadCmdWait.setText(String.valueOf(settings.getDownloadCmdWait()));
            downloadCmd.setText(String.valueOf(settings.getDownloadCmdDuration()));
            uploadResultWait.setText(String.valueOf(settings.getUploadResultWait()));
            uploadResult.setText(String.valueOf(settings.getUploadResultDuration()));
            uploadResult.setText(String.valueOf(settings.getUploadResultDuration()));
            callback4Wait.setText(String.valueOf(settings.getCallbackPhase4Wait()));
            callback4.setText(String.valueOf(settings.getCallbackPhase4Duration()));
            remoteCmdResWait.setText(String.valueOf(settings.getRemoteCmdResponseWait()));
            remoteCmdRes.setText(String.valueOf(settings.getRemoteCmdResponseDuration()));
        }

        remoteCmd.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    settings.setRemoteCmdDuration(Long.valueOf(remoteCmd.getText().toString()));
                    imm.hideSoftInputFromWindow(remoteCmd.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        postCmdWait.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    settings.setPostCmdWait(Long.valueOf(postCmdWait.getText().toString()));
                    imm.hideSoftInputFromWindow(postCmdWait.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        postCmd.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    settings.setPostCmdDuration(Long.valueOf(postCmd.getText().toString()));
                    imm.hideSoftInputFromWindow(postCmd.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        specDWait.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    settings.setSpecDWait(Long.valueOf(specDWait.getText().toString()));
                    imm.hideSoftInputFromWindow(specDWait.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        specD.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    settings.setSpecDDuration(Long.valueOf(specD.getText().toString()));
                    imm.hideSoftInputFromWindow(specD.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSettingsChangeInteraction(settings);
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG,"onDetach");
        listener=null;
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.d(TAG,"onCreatOptionsMenu");
        menu.clear();
    }
}
