package com.zd.hdshop.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zd.hdshop.R;
import com.zd.hdshop.entity.Wares;
import com.zd.hdshop.httprequest.Contants;

import java.util.List;

/**
 * Created by Administrator on 2017/2/20.
 */
public class HotWaresAdapter extends RecyclerView.Adapter<HotWaresAdapter.ViewHolder>{

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Wares> mDatas;
    public HotWaresAdapter(List<Wares> datas){
        this.mDatas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.item_hot_wares,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Wares wares = mDatas.get(position);
        holder.sdv.setImageURI(Uri.parse(Contants.API.BASE_URL+wares.getImgUrl()));
       // Log.e("TAG",""+Uri.parse(Contants.API.BASE_URL+wares.getImgUrl()));
        holder.titletv.setText(wares.getName());
        holder.messagetv.setText("￥"+wares.getPrice());
    }

    @Override
    public int getItemCount() {
        if(mDatas!=null&mDatas.size()>0){
            return mDatas.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView sdv;
        TextView titletv;
        TextView messagetv;
        public ViewHolder(View itemView) {
            super(itemView);
            sdv = (SimpleDraweeView)itemView.findViewById(R.id.hot_wares_img_sdv);
            titletv = (TextView)itemView.findViewById(R.id.hot_wares_title_tv);
            messagetv = (TextView)itemView.findViewById(R.id.hot_wares_price_tv);
        }
    }

}