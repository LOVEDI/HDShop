package com.zd.hdshop.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.gson.Gson;
import com.zd.hdshop.R;
import com.zd.hdshop.adapter.HomeCategoryAdapter;
import com.zd.hdshop.adapter.decoration.DividerItemDecoration;
import com.zd.hdshop.entity.Banner;
import com.zd.hdshop.entity.HomeCampain;
import com.zd.hdshop.entity.base.Campaign;
import com.zd.hdshop.httprequest.BaseCallBack;
import com.zd.hdshop.httprequest.Contants;
import com.zd.hdshop.httprequest.OkHttpHelper;
import com.zd.hdshop.httprequest.SpotsCallBack;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/1/12 0012.
 */
public class HomeFragment extends Fragment{
    private SliderLayout mSliderLayout;
    private PagerIndicator mIndicator;
    private RecyclerView mRecyclerView;
    private HomeCategoryAdapter mAdapter;
    private Gson mGson = new Gson();
    private List<Banner> mBanners;
    OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container,false);
        mSliderLayout = (SliderLayout) view.findViewById(R.id.slider);
        mIndicator = (PagerIndicator) view.findViewById(R.id.custom_indicator);
        requestImages();
        initRecyclerView(view);
        return view;
    }
//    OKhttop
    private  void requestImages(){
       //geturl
        String url = Contants.API.BANNER+"?type=1";
        okHttpHelper.get(url, new SpotsCallBack<List<Banner>>(getContext()) {
            @Override
            public void onSuccess(Response response, List<Banner> banners) {
                Log.e("TAG", "");
                mBanners = banners;
                initSlider();
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

      /*//  String url = "http://101.200.167.75:8080/phoenixshop/banner/query";
        OkHttpClient client = new OkHttpClient();
        //post请求
//        RequestBody body = new FormBody.Builder()
//                .add("type","1")
//                .build();
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
        //Get请求
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
           client.newCall(request).enqueue(new Callback() {
               //请求网络时出现不可恢复的错误时 调用该方法
                @Override
                public void onFailure(Call call, IOException e) {

                }
                //请求网络成功时调用该方法
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    //判断是否成功
                    if(response.isSuccessful()) {
                        String json = response.body().string();
                        Log.e("TAG", "json====" + json);
                        Type type = new TypeToken<List<Banner>>(){}.getType();
                        // mBanners = mGson.fromJson(json,new TypeToken<List<Banner>>(){}.getType());
                        mBanners = mGson.fromJson(json,type);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                             initSlider();
                            }
                        });

                    }
                }
            });//异步的
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
    private void initRecyclerView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.home_rv);
        okHttpHelper.get(Contants.API.CAMPAIGN_HOME,
                new BaseCallBack<List<HomeCampain>>() {
            @Override
            public void onRequestBefore(Request request) {

            }

            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("TAG","onFailure"+e);
            }

            @Override
            public void onSuccess(Response response, List<HomeCampain> homeCampains) {
                 Log.e("TAG","homeCampains==="+homeCampains.size());
                 Log.e("TAG","到这里了么饿？");
                 initData(homeCampains);
             }
            @Override
            public void onError(Response response, int code, Exception e) {
                Log.e("TAG","onError"+e);
            }

            @Override
            public void onResponse(Response response) {

            }
        });
    }

    private void initData(List<HomeCampain> homeCampains) {
        mAdapter = new HomeCategoryAdapter(homeCampains,getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration());
        mAdapter.setOnCampaignClickListener(new HomeCategoryAdapter.OnCampaignClickListener() {
            @Override
            public void onClick(View veiw, Campaign campaign) {
                Toast.makeText(getContext(), "title"+
                        campaign.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initSlider(){
        if(mBanners != null) {
            for(Banner banner : mBanners) {
                TextSliderView textSliderView = new TextSliderView(getActivity());
                textSliderView
                        .description(banner.getDescription())
                        .image(banner.getImgUrl())
                        .setScaleType(BaseSliderView.ScaleType.Fit);//设置图片的宽和高
                mSliderLayout.addSlider(textSliderView);
                //设置指示器位置(默认)
                mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                //设置转场效果
                mSliderLayout.setPresetTransformer(SliderLayout.Transformer.RotateUp);
                //设置自定义的知识器
                mSliderLayout.setCustomIndicator(mIndicator);
                //设置自定义动画
                mSliderLayout.setCustomAnimation(new DescriptionAnimation());
                //设置时间
                mSliderLayout.setDuration(3000);
                //给SliderLayout设置监听事件
                mSliderLayout.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        //    Log.e("TAG", "onPageScrolled----------->1");//滚动中调用
                    }
                    @Override
                    public void onPageSelected(int position) {
                        // Log.e("TAG", "onPageSelected----------->2");//新叶卡 被选中的时候调用
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                        // Log.e("TAG", "onPageScrollStateChanged----------->3");//滚动状态改变时时候调用
                    }
                });

            }
        }
        /*TextSliderView textSliderView = new TextSliderView(getActivity());
        textSliderView
                .description("新品推荐")
                .image("http://101.200.167.75:8080/phoenixshop/img/banner/582d4f6eN1d3c912c.jpg")
                .setScaleType(BaseSliderView.ScaleType.Fit);//设置图片的宽和高
        textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView slider) {
                Toast.makeText(HomeFragment.this.getActivity(), "新品推荐", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),TextActivity.class);
                startActivity(intent);
            }
        });
        TextSliderView textSliderView2 = new TextSliderView(getActivity());
        textSliderView2
                .description("时尚男装")
                .image("http://101.200.167.75:8080/phoenixshop/img/banner/57ff4039N709820d2.jpg")
                .setScaleType(BaseSliderView.ScaleType.Fit);//设置图片的宽和高
        textSliderView2.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView slider) {
                Toast.makeText(HomeFragment.this.getActivity(), "时尚男装", Toast.LENGTH_SHORT).show();
            }
        });
        TextSliderView textSliderView3 = new TextSliderView(getActivity());
        textSliderView3
                .description("家电秒杀")
                .image("http://101.200.167.75:8080/phoenixshop/img/banner/582ae45dNa57e1349.jpg")
                .setScaleType(BaseSliderView.ScaleType.Fit);//设置图片的宽和高
        textSliderView3.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView slider) {
                Toast.makeText(HomeFragment.this.getActivity(), "家电秒杀", Toast.LENGTH_SHORT).show();
            }
        });
        mSliderLayout.addSlider(textSliderView);
        mSliderLayout.addSlider(textSliderView2);
        mSliderLayout.addSlider(textSliderView3);*/

    }
    //再次进来的时候继续滚动
    @Override
    public void onStart() {
        mSliderLayout.startAutoCycle();
        super.onStart();
    }
    //绑定生命周期 结束同步
    @Override
    public void onStop() {
        mSliderLayout.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if(hidden) {
            mSliderLayout.stopAutoCycle();
        }else {
            mSliderLayout.startAutoCycle();
        }
        super.onHiddenChanged(hidden);
    }
}
