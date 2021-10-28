package com.example.crudeoperation_activity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.database.core.Context;

import java.util.List;

public class Myadapter extends BaseAdapter {
   Activity activity;
    List<Model> list;
    LayoutInflater inflater;

    public Myadapter(Activity activity, List<Model> list) {
        this.activity = activity;
        this.list = list;
        inflater=activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=inflater.inflate(R.layout.onerowlayout,null,false);
        TextView T1=convertView.findViewById(R.id.name);
        TextView T2=convertView.findViewById(R.id.email);
        TextView T3=convertView.findViewById(R.id.dob);
        TextView T4=convertView.findViewById(R.id.Qalification);

        TextView T5=convertView.findViewById(R.id.pass);
        TextView T6=convertView.findViewById(R.id.Id);

        Model model=list.get(position);

        T1.setText(model.getName());
        T2.setText(model.getEmail());
        T3.setText(model.getDob());
       T4.setText(model.getQualification());
        T5.setText(model.getPassword());
        T6.setText(model.getId());

        return convertView;
    }
}
