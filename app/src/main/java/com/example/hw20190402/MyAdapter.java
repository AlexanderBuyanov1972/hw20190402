package com.example.hw20190402;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private List<Profile> list;

    public MyAdapter() {
        list = StoreProvider.getInstance().getListProfiles();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
            TextView name = convertView.findViewById(R.id.row_name);
            TextView lastName = convertView.findViewById(R.id.row_last_name);
            Profile profile = list.get(position);
            name.setText(profile.name);
            lastName.setText(profile.lastName);
            return convertView;
        }
        return null;
    }
}
