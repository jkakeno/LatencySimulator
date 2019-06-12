package com.denso.latencysimulator.View;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.denso.latencysimulator.Model.SettingItem;
import com.denso.latencysimulator.R;
import com.denso.latencysimulator.Interface.SettingEditListener;

import java.util.ArrayList;
import java.util.LinkedHashMap;

//https://demonuts.com/android-recyclerview-with-edittext/

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.ViewHolder> {

    LinkedHashMap<String, SettingItem> settings;
    SettingEditListener listener;

    public SettingAdapter(LinkedHashMap<String,SettingItem> settings, SettingEditListener listener) {
        this.settings = settings;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.setting_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SettingItem settingItem = (new ArrayList<SettingItem>(settings.values())).get(position);
        holder.cmd.setText(settingItem.getCommand());
        holder.wait.setText(String.valueOf(settingItem.getWait()));
        holder.duration.setText(String.valueOf(settingItem.getDuration()));
    }


    @Override
    public int getItemCount() {
        if(settings !=null) {
            return settings.size();
        }else{
            return 1;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cmd;
        EditText wait;
        EditText duration;


        public ViewHolder(View itemView) {
            super(itemView);
            cmd =itemView.findViewById(R.id.cmd);
            wait =itemView.findViewById(R.id.wait);
            duration =itemView.findViewById(R.id.duration);


            wait.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    //https://stackoverflow.com/questions/18711896/how-can-i-prevent-java-lang-numberformatexception-for-input-string-n-a
                    long waitValue=0;

                    try{
                        waitValue = Long.valueOf(wait.getText().toString());
                    }catch (NumberFormatException ex){

                    }

                    (new ArrayList<SettingItem>(settings.values())).get(getAdapterPosition()).setWait(waitValue);

                    listener.onSettingsChangeInteraction(settings);
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });


            duration.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    //https://stackoverflow.com/questions/18711896/how-can-i-prevent-java-lang-numberformatexception-for-input-string-n-a
                    long durationValue=0;

                    try{
                        durationValue = Long.valueOf(duration.getText().toString());
                    }catch (NumberFormatException ex){

                    }

                    (new ArrayList<SettingItem>(settings.values())).get(getAdapterPosition()).setDuration(durationValue);

                    listener.onSettingsChangeInteraction(settings);
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });
        }
    }
}



