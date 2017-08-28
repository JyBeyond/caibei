package com.facebook.shuiai.project.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.facebook.shuiai.project.R;
import com.facebook.shuiai.project.adapter.CreditAdapter;
import com.facebook.shuiai.project.adapter.InsuranceAdapter;
import com.facebook.shuiai.project.enitity.CreditAtom;
import com.facebook.shuiai.project.enitity.InsuranceAtom;
import com.facebook.shuiai.project.enitity.ResultDto;
import com.facebook.shuiai.project.task.RequestTaskBiz;
import com.facebook.shuiai.project.util.CollectionUtil;
import com.facebook.shuiai.project.util.GsonUtil;
import com.facebook.shuiai.project.util.netUtil.RequestConstantUtil;
import com.google.gson.reflect.TypeToken;
import com.yolanda.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2017/8/21
 */

public class DiscoverFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private List<InsuranceAtom> insuranceAtomList = new ArrayList<>();
    private InsuranceAdapter insuranceAdapter;
    private int pageNum = 1;
    private TextView txtDiscoverHeader, txtCreditHeader;
    private RecyclerView CreditRecyclerView;

    @Override
    public View initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_dicover, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        return view;
    }

    @Override
    public void initData() {
        setAdapter();
        startInsuranceListTask();
        startCreditListTask();
    }

    private void startCreditListTask() {
        pageNum = 1;
        RequestTaskBiz.getCreditList(mContext, pageNum, RequestConstantUtil.TWO_TASK_WHAT, this);
    }

    private void startInsuranceListTask() {
        pageNum = 1;
        RequestTaskBiz.getInsuranceList(mContext, pageNum, RequestConstantUtil.FIRST_TASK_WHAT, this);
    }

    private void setAdapter() {
        insuranceAdapter = new InsuranceAdapter(mContext, insuranceAtomList);
        View creditView = LayoutInflater.from(mContext).inflate(R.layout.item_discover_selection, null);
        CreditRecyclerView = (RecyclerView) creditView.findViewById(R.id.recyclerView);
        CreditRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        View headerView = LayoutInflater.from(mContext).inflate(R.layout.item_discover_list_header, null);
        txtCreditHeader = (TextView) headerView.findViewById(R.id.txt_discover_header);
        txtCreditHeader.setText("办理信用卡");
        insuranceAdapter.addHeaderView(headerView);
        insuranceAdapter.addHeaderView(creditView);
        View insuranceView = LayoutInflater.from(mContext).inflate(R.layout.item_discover_list_header, null);
        txtDiscoverHeader = (TextView) insuranceView.findViewById(R.id.txt_discover_header);
        txtDiscoverHeader.setText("保险");
        insuranceAdapter.addHeaderView(insuranceView);
        recyclerView.setAdapter(insuranceAdapter);
    }

    @Override
    public void onTaskFinished(int what, Response result) {
        super.onTaskFinished(what, result);
        switch (what) {
            case RequestConstantUtil.FIRST_TASK_WHAT:
                ResultDto<Object, List<InsuranceAtom>> resultDto = GsonUtil.getInstance().fromJson((String) result.get(), new TypeToken<ResultDto<Object, List<InsuranceAtom>>>() {
                }.getType());
                if (resultDto == null)
                    return;
                List<InsuranceAtom> InsuranceAtoms = resultDto.getListData();
                insuranceAdapter.setNewData(InsuranceAtoms);
                break;
            case RequestConstantUtil.TWO_TASK_WHAT:
                ResultDto<Object, List<CreditAtom>> CreditAtomDto = GsonUtil.getInstance().fromJson((String) result.get(), new TypeToken<ResultDto<Object, List<CreditAtom>>>() {
                }.getType());
                if (CreditAtomDto == null)
                    return;
                List<CreditAtom> creditAtoms = CreditAtomDto.getListData();
                if (!CollectionUtil.isEmpity(creditAtoms)) {
                    CreditAdapter creditAdapter = new CreditAdapter(mContext, creditAtoms);
                    CreditRecyclerView.setAdapter(creditAdapter);
                }
                break;
        }
    }
}
