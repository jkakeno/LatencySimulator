package com.denso.latencysimulator;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.LinkedHashMap;

public class SettingFragment extends Fragment implements SettingEditListener {

    private static final String TAG = SettingFragment.class.getSimpleName();
    private static final String SETTINGS = "settings";

    View view;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    SettingAdapter adapter;

    Button saveBtn;
    Button defaultBtn;

    SettingSaveListener listener;
    SettingEditListener interfaceListener;
    LinkedHashMap<String,SettingItem> settings;

    public SettingFragment() {
        // Required empty public constructor
    }

    public static SettingFragment newInstance(LinkedHashMap<String,SettingItem> settings) {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putSerializable(SETTINGS, settings);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        if (getArguments() != null) {
            settings = (LinkedHashMap<String, SettingItem>) getArguments().getSerializable(SETTINGS);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");

        view = inflater.inflate(R.layout.setting_fragment, container, false);
        recyclerView = view.findViewById(R.id.parameter_list);
        saveBtn = view.findViewById(R.id.saveBtn);
        defaultBtn = view.findViewById(R.id.defaultBtn);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
        listener = (SettingSaveListener) context;
        interfaceListener = (SettingEditListener) this;
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

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.setting_screen_title));

        adapter = new SettingAdapter(settings,interfaceListener);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSettingsSaveInteraction(settings);
            }
        });

        defaultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.settings = new Settings().getDefaultSetting();
                layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(layoutManager);
            }
        });

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG,"onDetach");
        listener=null;
        interfaceListener=null;
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.d(TAG,"onCreatOptionsMenu");
        menu.clear();
    }


    @Override
    public void onSettingsChangeInteraction(LinkedHashMap<String,SettingItem> settings) {
        this.settings = settings;
    }
}
