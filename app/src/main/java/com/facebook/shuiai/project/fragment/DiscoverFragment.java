package com.facebook.shuiai.project.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.shuiai.project.R;
import com.facebook.shuiai.project.adapter.InsuranceAdapter;
import com.facebook.shuiai.project.enitity.CreditAtom;
import com.facebook.shuiai.project.enitity.DiscoverAtom;
import com.facebook.shuiai.project.enitity.InsuranceAtom;
import com.facebook.shuiai.project.enitity.ResultDto;
import com.facebook.shuiai.project.task.RequestTaskBiz;
import com.facebook.shuiai.project.util.CollectionUtil;
import com.facebook.shuiai.project.util.GsonUtil;
import com.facebook.shuiai.project.util.netUtil.RequestConstantUtil;
import com.google.gson.reflect.TypeToken;
import com.yolanda.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2017/8/21
 */

public class DiscoverFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private InsuranceAdapter insuranceAdapter;
    private int pageNum = 1;
    private TextView txtDiscoverHeader, txtCreditHeader;
    private List<DiscoverAtom> discoverAtoms = new ArrayList<>();
    private boolean CREDITTASK, INSURANCETASK;

    @Override
    public View initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_dicover, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
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
        insuranceAdapter = new InsuranceAdapter(discoverAtoms);
        insuranceAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return discoverAtoms.get(position).getSpanSize();
            }
        });

        recyclerView.setAdapter(insuranceAdapter);
    }

    @Override
    public void onTaskFinished(int what, Response result) {
        super.onTaskFinished(what, result);
        switch (what) {
            case RequestConstantUtil.FIRST_TASK_WHAT:
                INSURANCETASK = true;
                ResultDto<Object, List<InsuranceAtom>> resultDto = GsonUtil.getInstance().fromJson((String) result.get(), new TypeToken<ResultDto<Object, List<InsuranceAtom>>>() {
                }.getType());
                if (resultDto == null)
                    return;
                List<InsuranceAtom> InsuranceAtoms = resultDto.getListData();
                if (!CollectionUtil.isEmpity(InsuranceAtoms)) {
                    DiscoverAtom discoverAtomHeader = new DiscoverAtom(DiscoverAtom.INSURANCETYPE_HEADER, DiscoverAtom.INSURANCE_SPAN_SIZE);
                    discoverAtomHeader.setProductName("保险");
                    discoverAtomHeader.setDataTYpe(0);
                    discoverAtoms.add(discoverAtomHeader);
                    for (InsuranceAtom insuranceAtom : InsuranceAtoms) {
                        DiscoverAtom discoverAtom = new DiscoverAtom(DiscoverAtom.INSURANCETYPE, DiscoverAtom.INSURANCE_SPAN_SIZE);
                        discoverAtom.setIdentifier(insuranceAtom.getIdentifier());
                        discoverAtom.setDataTYpe(0);
                        discoverAtom.setProductName(insuranceAtom.getSafeName());
                        discoverAtom.setProductPic(insuranceAtom.getSafePicUrl());
                        discoverAtom.setProductUrl(insuranceAtom.getSafeUrl());
                        discoverAtom.setProductSpecial(insuranceAtom.getSafeSpecial());
                        discoverAtoms.add(discoverAtom);
                    }
                }
                setAdaperData(discoverAtoms);
                break;
            case RequestConstantUtil.TWO_TASK_WHAT:
                CREDITTASK = true;
                ResultDto<Object, List<CreditAtom>> CreditAtomDto = GsonUtil.getInstance().fromJson((String) result.get(), new TypeToken<ResultDto<Object, List<CreditAtom>>>() {
                }.getType());
                if (CreditAtomDto == null)
                    return;
                List<CreditAtom> creditAtoms = CreditAtomDto.getListData();
                if (!CollectionUtil.isEmpity(creditAtoms)) {
                    DiscoverAtom discoverAtomHeader = new DiscoverAtom(DiscoverAtom.CREDITTYPE_HEADER, DiscoverAtom.INSURANCE_SPAN_SIZE);
                    discoverAtomHeader.setProductName("办理信用卡");
                    discoverAtomHeader.setDataTYpe(1);
                    discoverAtoms.add(discoverAtomHeader);
                    for (CreditAtom creditAtom : creditAtoms) {
                        DiscoverAtom discoverAtom = new DiscoverAtom(DiscoverAtom.CREDITTYPE, DiscoverAtom.CREDIT_SPAN_SIZE);
                        discoverAtom.setIdentifier(creditAtom.getIdentifier());
                        discoverAtom.setDataTYpe(1);
                        discoverAtom.setProductName(creditAtom.getCriditName());
                        discoverAtom.setProductPic(creditAtom.getCriditPicUrl());
                        discoverAtom.setProductUrl(creditAtom.getCriditUrl());
                        discoverAtom.setProductSpecial(creditAtom.getCriditSpecial());
                        discoverAtoms.add(discoverAtom);
                    }
                }
                setAdaperData(discoverAtoms);
                break;
        }
    }

    private void setAdaperData(List<DiscoverAtom> discoverAtomList) {
        if (CREDITTASK && INSURANCETASK) {
            sort();
            insuranceAdapter.setNewData(discoverAtomList);
        }
    }

    public void sort() {
        Comparator<DiscoverAtom> comparator = new Comparator<DiscoverAtom>() {

            @Override
            public int compare(DiscoverAtom o1, DiscoverAtom o2) {
                Integer sort1 = o1.getDataTYpe();
                Integer sort2 = o2.getDataTYpe();
                return sort2.compareTo(sort1);
            }
        };
        Collections.sort(discoverAtoms, comparator);
    }
}
