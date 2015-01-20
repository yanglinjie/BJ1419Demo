package com.qianfeng.bj1419demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.qianfeng.bj1419demo.R;
import com.qianfeng.bj1419demo.bean.UpdateImage;

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
    private List<UpdateImage> bitmapList;
    private Context context;
    private LayoutInflater inflater;

    public ShowImageAdapter(List<UpdateImage> bitmapList, Context context) {
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
        if (bitmapList.get(position).getIsCheck()) {
            vh.cb.setChecked(true);
        } else {
            vh.cb.setChecked(false);
        }
        vh.imgIv.setImageBitmap(bitmapList.get(position).getBitmap());
        return convertView;
    }


    public static class ViewHolder {
        public ImageView imgIv;
        public CheckBox cb;

        private ViewHolder(View view) {
            imgIv = (ImageView) view.findViewById(R.id.item_update_img);
            cb = (CheckBox) view.findViewById(R.id.item_update_cb);
        }
    }

}
