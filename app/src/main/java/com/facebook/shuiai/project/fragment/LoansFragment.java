package com.facebook.shuiai.project.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.shuiai.project.R;
import com.facebook.shuiai.project.activity.LoansDetailActivity_;
import com.facebook.shuiai.project.adapter.LoansListAdapter;
import com.facebook.shuiai.project.enitity.LoansAtom;
import com.facebook.shuiai.project.enitity.ResultDto;
import com.facebook.shuiai.project.task.RequestTaskBiz;
import com.facebook.shuiai.project.util.GsonUtil;
import com.facebook.shuiai.project.util.netUtil.RequestConstantUtil;
import com.google.gson.reflect.TypeToken;
import com.yolanda.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2017/8/21
 */

public class LoansFragment extends BaseFragment implements PtrHandler, View.OnClickListener, BaseQuickAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private int pageNum = 1;
    private PtrClassicFrameLayout ptrClassicFrameLayout;
    private List<LoansAtom> loansAtoms = new ArrayList<>();
    private boolean loadMore = false;
    private LoansListAdapter loansListAdapter;
    private LinearLayout throughRateLayout, throughPutLayout, monthRateLayout;
    private TextView txtLimit, txtThroughPut, txtMonthhRate;
    private ImageView imgThroughRateUp, imgThroughRateDown, imgThroughPutUp, imgThroughPutDown, imgMonthRateUp, imgMonthRateDown;
    private String type = "-1";//1:通过率2:放宽速度3:月利率
    private int sortType = -1;//0:降序1:升序

    @Override
    public View initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_loans, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        ptrClassicFrameLayout = (PtrClassicFrameLayout) view.findViewById(R.id.ptrClassicFrameLayout);
        ptrClassicFrameLayout.setPtrHandler(this);
        initScreenView(view);
        return view;
    }

    private void initScreenView(View view) {
        throughRateLayout = (LinearLayout) view.findViewById(R.id.through_rate_layout);
        txtLimit = (TextView) view.findViewById(R.id.txt_limit);
        imgThroughRateUp = (ImageView) view.findViewById(R.id.img_through_rate_up);
        imgThroughRateDown = (ImageView) view.findViewById(R.id.img_through_rate_down);
        throughPutLayout = (LinearLayout) view.findViewById(R.id.through_put_layout);
        txtThroughPut = (TextView) view.findViewById(R.id.txt_through_put);
        imgThroughPutUp = (ImageView) view.findViewById(R.id.img_through_put_up);
        imgThroughPutDown = (ImageView) view.findViewById(R.id.img_through_put_down);
        monthRateLayout = (LinearLayout) view.findViewById(R.id.month_rate_layout);
        txtMonthhRate = (TextView) view.findViewById(R.id.txt_month_rate);
        imgMonthRateUp = (ImageView) view.findViewById(R.id.img_month_rate_up);
        imgMonthRateDown = (ImageView) view.findViewById(R.id.img_month_rate_down);
        throughRateLayout.setOnClickListener(this);
        throughPutLayout.setOnClickListener(this);
        monthRateLayout.setOnClickListener(this);
    }

    @Override
    public void initData() {
        loansListAdapter = new LoansListAdapter(mContext, loansAtoms);
        recyclerView.setAdapter(loansListAdapter);
        loansListAdapter.setOnItemClickListener(this);
        refreshTask();
    }

    private void refreshTask() {
        pageNum = 1;
        startTask();
    }

    private void startTask() {
        RequestTaskBiz.getLendPage(mContext, type, sortType, pageNum, RequestConstantUtil.FIRST_TASK_WHAT, this);
    }

    @Override
    public void onTaskFinished(int what, Response result) {
        super.onTaskFinished(what, result);
        ptrClassicFrameLayout.refreshComplete();
        switch (what) {
            case RequestConstantUtil.FIRST_TASK_WHAT:
                ResultDto<Object, List<LoansAtom>> resultDto = GsonUtil.getInstance().fromJson((String) result.get(), new TypeToken<ResultDto<Object, List<LoansAtom>>>() {
                }.getType());
                if (resultDto == null)
                    return;
                List<LoansAtom> loansAtoms = resultDto.getListData();
                if (!loadMore) {
                    loansListAdapter.setNewData(loansAtoms);
                } else {
                    loansListAdapter.addData(loansAtoms);
                    loansListAdapter.loadMoreComplete();
                }
                break;
        }
    }

    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        refreshTask();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.through_rate_layout:
                if (!"1".equals(type)) {
                    sortType = -1;
                }
                type = "1";
                break;
            case R.id.through_put_layout:
                if (!"2".equals(type)) {
                    sortType = -1;
                }
                type = "2";
                break;
            case R.id.month_rate_layout:
                if (!"3".equals(type)) {
                    sortType = -1;
                }
                type = "3";
                break;
        }
        if (sortType == 0) {
            sortType = 1;
        } else {
            sortType = 0;
        }
        changeView(type);
        refreshTask();
    }


    private void changeView(String type) {
        switch (type) {
            case "1":
                setTextColor(txtLimit, txtThroughPut, txtMonthhRate);
                setImageDown(imgThroughRateDown, imgThroughPutDown, imgMonthRateDown);
                setImageUp(imgThroughRateUp, imgThroughPutUp, imgMonthRateUp);
                break;
            case "2":
                setTextColor(txtThroughPut, txtLimit, txtMonthhRate);
                setImageDown(imgThroughPutDown, imgThroughRateDown, imgMonthRateDown);
                setImageUp(imgThroughPutUp, imgThroughRateUp, imgMonthRateUp);
                break;
            case "3":
                setTextColor(txtMonthhRate, txtThroughPut, txtLimit);
                setImageDown(imgMonthRateDown, imgThroughPutDown, imgThroughRateDown);
                setImageUp(imgMonthRateUp, imgThroughPutUp, imgThroughRateUp);
                break;
        }
    }

    private void setImageUp(ImageView imageView1, ImageView imageView2, ImageView imageView3) {
        if (sortType == 0 || sortType == -1) {
            imageView1.setImageResource(R.mipmap.screen_up_unselect);
        } else if (sortType == 1) {
            imageView1.setImageResource(R.mipmap.screen_up_select);
        }
        imageView2.setImageResource(R.mipmap.screen_up_unselect);
        imageView3.setImageResource(R.mipmap.screen_up_unselect);
    }

    private void setImageDown(ImageView imageView1, ImageView imageView2, ImageView imageView3) {
        if (sortType == 0) {
            imageView1.setImageResource(R.mipmap.screen_down_select);
        } else if (sortType == 1) {
            imageView1.setImageResource(R.mipmap.screen_down_unselect);
        }
        imageView2.setImageResource(R.mipmap.screen_down_unselect);
        imageView3.setImageResource(R.mipmap.screen_down_unselect);
    }

    private void setTextColor(TextView textView1, TextView textView2, TextView textView3) {
        textView1.setTextColor(getResources().getColor(R.color.themeClr));
        textView2.setTextColor(getResources().getColor(R.color.textClr));
        textView3.setTextColor(getResources().getColor(R.color.textClr));
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        List<LoansAtom> loansAtoms = adapter.getData();
        LoansAtom loansAtom = loansAtoms.get(position);
        Intent intent = new Intent(mContext, LoansDetailActivity_.class);
        intent.putExtra("id", loansAtom.getIdentifier());
        startActivity(intent);
    }
}
