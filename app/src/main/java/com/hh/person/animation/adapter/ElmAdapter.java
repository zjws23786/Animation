package com.hh.person.animation.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hh.person.animation.R;
import com.hh.person.animation.custom.AnimShopButton;
import com.hh.person.animation.inter.IOnAddDelListener;

import java.util.HashMap;
import java.util.List;

/**
 * Created by hjz on 2017/3/6.
 */

public class ElmAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> list;
    private IOnAddDelListener listener;
    HashMap<Integer, View> viewMap = new HashMap<Integer, View>();

    public void setListener(IOnAddDelListener listener) {
        this.listener = listener;
    }

    public ElmAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list != null ? list.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (viewMap.get(position) == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_elm_item,null);
            holder = new ViewHolder();
            holder.contentTv = (TextView) convertView.findViewById(R.id.content_tv);
            holder.animShopButton = (AnimShopButton) convertView.findViewById(R.id.btnEle);
            convertView.setTag(holder);
            viewMap.put(position, convertView);
        }else{
            convertView = viewMap.get(position);
            holder = (ViewHolder) convertView.getTag();
        }
        holder.contentTv.setText(list.get(position));
        holder.animShopButton.setShowHintText(true);
        holder.animShopButton.setOnAddDelListener(listener);
        return convertView;
    }

    class ViewHolder{
        TextView contentTv;
        AnimShopButton animShopButton;
    }
}
