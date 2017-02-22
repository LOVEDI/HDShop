package com.zd.hdshop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zd.hdshop.R;
import com.zd.hdshop.entity.HomeCampain;
import com.zd.hdshop.entity.base.Campaign;

import java.util.List;

/**
 * Created by Administrator on 2017/2/15 0015.
 */
public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder>{
    private static final int VIEW_TYPE_R = 0;
    private static final int VIEW_TYPE_L = 1 ;
    private LayoutInflater mInflater;
    private List<HomeCampain> mDatas;
    private Context mContext;
    public OnCampaignClickListener mlistener;
    public void setOnCampaignClickListener (OnCampaignClickListener listener){
        this.mlistener = listener;
    }

    public HomeCategoryAdapter(List<HomeCampain> mDatas,Context context) {

        this.mDatas = mDatas;
        this.mContext = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mInflater  = LayoutInflater.from(parent.getContext());
    if(viewType == VIEW_TYPE_R) {
        return new ViewHolder(mInflater.inflate(R.layout.item_home_cardview2,parent,false));
    }
            return new ViewHolder(mInflater.inflate(
                    R.layout.item_home_cardview,parent,false));
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HomeCampain homeCampaign = mDatas.get(position);
        holder.textTitle.setText(homeCampaign.getTitle());
            Picasso.with(mContext)
                .load(homeCampaign.getCpOne().getImgUrl())
                 .into(holder.imageViewBig);
        Picasso.with(mContext)
                .load(homeCampaign.getCpTwo().getImgUrl())
                .into(holder.imageViewSmallTop);
        Picasso.with(mContext)
                .load(homeCampaign.getCpThree().getImgUrl())
                .into(holder.imageViewSmallBottom);
    }
    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if( position % 2 == 0) {
            return VIEW_TYPE_R;
        } else {
            return VIEW_TYPE_L;
        }
    }

    //holder  内部类
    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        TextView textTitle;
        ImageView imageViewBig;
        ImageView imageViewSmallTop;
        ImageView imageViewSmallBottom;
    //ViewHolder分装的构造方法在这里面 进行 控件初始化
        public ViewHolder(View itemView) {
            super(itemView);
            //构造方法中fbi
            textTitle = (TextView) itemView.findViewById(R.id.title_tv);
            imageViewBig = (ImageView) itemView.findViewById(R.id.big_iv);
            imageViewSmallTop = (ImageView) itemView.findViewById(R.id.small_top_iv);
            imageViewSmallBottom = (ImageView) itemView.findViewById(R.id.small_bottom_iv);

            imageViewBig.setOnClickListener(this);
            imageViewSmallTop.setOnClickListener(this);
            imageViewSmallBottom.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            HomeCampain homeCampaign = mDatas.get(getLayoutPosition());
            if(mlistener != null) {
                switch (v.getId()) {
                    case  R.id.big_iv:
                            mlistener.onClick(v,homeCampaign.getCpOne());
                        break;
                    case  R.id.small_top_iv:
                        mlistener.onClick(v,homeCampaign.getCpTwo());
                        break;
                    case  R.id.small_bottom_iv:
                        mlistener.onClick(v,homeCampaign.getCpThree());
                        break;
                }

            }


        }
    }
    public interface OnCampaignClickListener{
        void onClick(View veiw,Campaign campaign);
    }

}
