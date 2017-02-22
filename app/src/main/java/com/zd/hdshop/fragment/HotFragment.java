package com.zd.hdshop.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjj.MaterialRefreshLayout;
import com.zd.hdshop.R;
import com.zd.hdshop.adapter.HotWaresAdapter;
import com.zd.hdshop.adapter.decoration.DividerItemDecoration;
import com.zd.hdshop.entity.PageResult;
import com.zd.hdshop.entity.Wares;
import com.zd.hdshop.httprequest.Contants;
import com.zd.hdshop.httprequest.OkHttpHelper;
import com.zd.hdshop.httprequest.SpotsCallBack;

import java.util.List;

import okhttp3.Response;

/**
 * Created by Administrator on 2017/1/12 0012.
 */
public class HotFragment extends Fragment{

    private static final int STATE_NORMAL = 0;//正常状态
    private static final int STATE_REFRESH = 1;//刷新
    private static final int STATE_MORE = 1;//加载更多
    private int state =STATE_NORMAL;//默认状态是正常状态

    private OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
    private int currentPage = 1;
    private int totalPage = 1;
    private int PageSize = 10;

    private View view;
    private HotWaresAdapter adapter;
    private MaterialRefreshLayout mlRefreshLayout;
    private RecyclerView rv;
    private List<Wares> datas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot, null);
        getData();
        return view;

    }
    private void getData(){
        String url = Contants.API.WARES_HOT+"?curPage="+currentPage+"&pageSize="+PageSize;
        okHttpHelper.get(url, new SpotsCallBack<PageResult<Wares>>(getContext()) {

            @Override
            public void onSuccess(Response response, PageResult<Wares> waresPageResult) {
                datas = waresPageResult.getList();
                currentPage = waresPageResult.getCurrentPage();
                totalPage = waresPageResult.getTotalPage();
                showData();
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

    }
    private void showData(){
        adapter = new HotWaresAdapter(datas);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.addItemDecoration(new DividerItemDecoration(
                getContext(),DividerItemDecoration.VERTICAL_LIST));
    }
}
