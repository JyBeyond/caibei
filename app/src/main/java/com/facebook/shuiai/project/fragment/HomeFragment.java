package com.facebook.shuiai.project.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.facebook.shuiai.project.R;
import com.facebook.shuiai.project.adapter.LoanNumberAdapter;
import com.facebook.shuiai.project.enitity.HomeInfoAtom;
import com.facebook.shuiai.project.enitity.LoanNumber;
import com.facebook.shuiai.project.enitity.ResultDto;
import com.facebook.shuiai.project.task.RequestTaskBiz;
import com.facebook.shuiai.project.util.GlideImageLoader;
import com.facebook.shuiai.project.util.GsonUtil;
import com.facebook.shuiai.project.util.StringUtil;
import com.facebook.shuiai.project.util.netUtil.RequestConstantUtil;
import com.google.gson.reflect.TypeToken;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.Pivot;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;
import com.yolanda.nohttp.rest.Response;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

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

public class HomeFragment extends BaseFragment implements PtrHandler {
    private List<Integer> images = new ArrayList<>();
    private Banner banner;
    private PtrClassicFrameLayout ptrClassicFrameLayout;
    private DiscreteScrollView picker;
    private List<LoanNumber> loanNumbers = new ArrayList<>();
    private TextView txtDate, txtPhone, txtCount, txtTotalPerson, txtTotalLoans;

    @Override
    public View initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_home, null);
        txtDate = (TextView) view.findViewById(R.id.txt_date);
        txtPhone = (TextView) view.findViewById(R.id.txt_phone);
        txtCount = (TextView) view.findViewById(R.id.txt_count);
        txtTotalPerson = (TextView) view.findViewById(R.id.txt_total_person);
        txtTotalLoans = (TextView) view.findViewById(R.id.txt_total_loans);
        banner = (Banner) view.findViewById(R.id.banner);
        ptrClassicFrameLayout = (PtrClassicFrameLayout) view.findViewById(R.id.ptrClassicFrameLayout);
        ptrClassicFrameLayout.disableWhenHorizontalMove(true);
        ptrClassicFrameLayout.setPtrHandler(this);
        picker = (DiscreteScrollView) view.findViewById(R.id.picker);
        return view;
    }

    @Override
    public void initData() {
        initPicker();
        initBanner();
        startTask();
    }

    private void startTask() {
        RequestTaskBiz.getNewOrderInfoTask(mContext, RequestConstantUtil.FIRST_TASK_WHAT, this);
    }

    private void initPicker() {
        for (int i = 0; i < 10; i++) {
            loanNumbers.add(new LoanNumber());
        }
        LoanNumberAdapter adapter = new LoanNumberAdapter(mContext, loanNumbers);
        picker.setAdapter(adapter);
        picker.setOffscreenItems(5);
        picker.setItemTransformer(new ScaleTransformer.Builder()
                .setMaxScale(1.05f)
                .setMinScale(0.8f)
                .setPivotX(Pivot.X.CENTER)
                .setPivotY(Pivot.Y.CENTER)
                .build());
    }

    private void initBanner() {
        images.add(R.mipmap.home_baner);
        images.add(R.mipmap.home_baner);
        images.add(R.mipmap.home_baner);
        images.add(R.mipmap.home_baner);
        banner.setBannerAnimation(Transformer.Default);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(images);
        banner.isAutoPlay(false);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.start();
    }

    @Override
    public void onTaskFinished(int what, Response result) {
        super.onTaskFinished(what, result);
        ptrClassicFrameLayout.refreshComplete();
        switch (what) {
            case RequestConstantUtil.FIRST_TASK_WHAT:
                ResultDto<List<HomeInfoAtom>> resultDto = GsonUtil.getInstance().fromJson((String) result.get(), new TypeToken<ResultDto<List<HomeInfoAtom>>>() {
                }.getType());
                if (resultDto == null)
                    return;
                List<HomeInfoAtom> homeInfoAtoms = resultDto.getProperties();
                HomeInfoAtom homeInfoAtom = homeInfoAtoms.get(0);
                if (!StringUtil.isNull(homeInfoAtom.getCreateDate())) {
                    txtDate.setText(StringUtil.dateToCustomStr(Long.valueOf(homeInfoAtom.getCreateDate()), "yyyy:MM:dd HH:mm"));
                } else {
                    txtDate.setText("--");
                }
                txtPhone.setText(homeInfoAtom.getPhone());
                if (StringUtil.isNull(homeInfoAtom.getLendMoney())) {
                    txtCount.setText("--");
                } else {
                    txtCount.setText(homeInfoAtom.getLendMoney());
                }
                txtTotalLoans.setText(homeInfoAtom.getTotalLendMoney());
                txtTotalPerson.setText(homeInfoAtom.getServicePersonTime());
                break;
        }
    }

    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        startTask();
    }
}
