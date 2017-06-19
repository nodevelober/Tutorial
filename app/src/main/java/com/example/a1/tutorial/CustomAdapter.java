package com.example.a1.tutorial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by a1 on 17.03.17.
 */

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private String[] stringValues;
    LayoutInflater lInflater;

    public CustomAdapter(Context context, String[] stringValues)
    {
        this.context = context;
        this.stringValues = stringValues;
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item, parent, false);
        }
        TextView textView = (TextView) view.findViewById(R.id.colors);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.llColors);
        textView.setText(stringValues[position]);
        return view;
    }
}
