package com.facebook.shuiai.project.activity;

import android.widget.TextView;

import com.facebook.shuiai.project.R;
import com.facebook.shuiai.project.enitity.LoansDetailAtom;
import com.facebook.shuiai.project.enitity.ResultDto;
import com.facebook.shuiai.project.task.RequestTaskBiz;
import com.facebook.shuiai.project.util.GsonUtil;
import com.facebook.shuiai.project.util.netUtil.RequestConstantUtil;
import com.google.gson.reflect.TypeToken;
import com.yolanda.nohttp.rest.Response;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2017/9/9
 */
@EActivity(R.layout.activity_loans_detail)
public class LoansDetailActivity extends BaseActivity {


    @ViewById(R.id.naviBar_title)
    TextView naviBarTitle;
    @ViewById(R.id.txt_limit)
    TextView txtLimit;
    @ViewById(R.id.txt_interest_rate)
    TextView txtInterestRate;
    @ViewById(R.id.txt_total_apply)
    TextView txtTotalApply;
    @ViewById(R.id.txt_through_rate)
    TextView txtThroughRate;
    @ViewById(R.id.txt_requirement)
    TextView txtRequirement;
    @ViewById(R.id.txt_development)
    TextView txtDevelopment;
    @ViewById(R.id.txt_suggestions)
    TextView txtSuggestions;
    @ViewById(R.id.txt_limit_range)
    TextView txtLimitRange;
    @ViewById(R.id.txt_punishment)
    TextView txtPunishment;
    @ViewById(R.id.txt_lending_time)
    TextView txtLendingTime;
    private String id;

    @AfterViews
    public void initView() {
        naviBarTitle.setText("贷款详情");
        id = getIntent().getStringExtra("id");
        getData();
    }

    /**
     * 请求接口
     */
    private void getData() {

        RequestTaskBiz.getLoansDetail(this, id, RequestConstantUtil.FIRST_TASK_WHAT, this);

    }

    @Override
    public void onTaskFinished(int what, Response result) {
        super.onTaskFinished(what, result);
        switch (what) {
            case RequestConstantUtil.FIRST_TASK_WHAT:
                ResultDto<List<LoansDetailAtom>, Object> resultDto = GsonUtil.getInstance().fromJson((String) result.get(), new TypeToken<ResultDto<List<LoansDetailAtom>, Object>>() {
                }.getType());
                if (resultDto == null)
                    return;
                List<LoansDetailAtom> loansDetailAtoms = resultDto.getProperties();
                LoansDetailAtom loansDetailAtom = loansDetailAtoms.get(0);
                setData(loansDetailAtom);
                break;
        }
    }

    private void setData(LoansDetailAtom loansDetailAtom) {
        naviBarTitle.setText(loansDetailAtom.getLendName());
        txtInterestRate.setText(loansDetailAtom.getMonthlyInterestRate());
        txtLendingTime.setText(loansDetailAtom.getLoanTime());
        txtTotalApply.setText(loansDetailAtom.getTotalApply());
        txtLendingTime.setText(loansDetailAtom.getLoanTime());
        txtThroughRate.setText(loansDetailAtom.getThroughputRate());
        txtRequirement.setText(loansDetailAtom.getRequirements());
        txtDevelopment.setText(loansDetailAtom.getCertificationMaterials());
        txtSuggestions.setText(loansDetailAtom.getApplyForAdvice());
        txtPunishment.setText(loansDetailAtom.getOverduePunishment());
    }
}
