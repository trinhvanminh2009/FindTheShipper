package com.minh.findtheshipper.models.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import com.minh.findtheshipper.R;
import com.minh.findtheshipper.models.ListControl;

import java.util.List;

/**
 * Created by Minh on 5/31/2017.
 */

public class CustomAdapterListView extends ArrayAdapter<ListControl> {
    private Context context;
    private List<ListControl>listControls;

    @NonNull
    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<ListControl> getListControls() {
        return listControls;
    }

    public void setListControls(List<ListControl> listControls) {
        this.listControls = listControls;
    }

    public CustomAdapterListView(@NonNull Context context, @NonNull List<ListControl> objects) {
        super(context, 0, objects);
        this.context = context;
        this.listControls = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item,parent,false);
        ImageView imageView = (ImageView)view.findViewById(R.id.imgIcon);
        EditText editText = (EditText)view.findViewById(R.id.txtControl);
        ListControl control = listControls.get(position);
        imageView.setImageResource(control.getIdIcon());
        editText.setText(control.getContent());
        return view;

    }
}
