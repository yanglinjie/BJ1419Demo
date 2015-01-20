package com.qianfeng.bj1419demo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.qianfeng.bj1419demo.R;

import java.util.List;


/**
 * @Package com.qianfeng.bj1419demo.adapter
 * @作 用:
 * @创 建 人: zhangwei
 * @日 期: 15/1/20
 * @修 改 人:
 * @日 期:
 */
public class ShowImageAdapter extends BaseAdapter {
    private List<Bitmap> bitmapList;
    private Context context;
    private LayoutInflater inflater;

    public ShowImageAdapter(List<Bitmap> bitmapList, Context context) {
        this.bitmapList = bitmapList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return bitmapList.size();
    }

    @Override
    public Object getItem(int position) {
        return bitmapList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_update_layout, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.imgIv.setImageBitmap(bitmapList.get(position));
        return convertView;
    }


    private static class ViewHolder {
        ImageView imgIv;


        private ViewHolder(View view) {
            imgIv = (ImageView) view.findViewById(R.id.item_update_img);
        }
    }

}