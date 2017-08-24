package com.facebook.shuiai.project.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.facebook.shuiai.project.R;
import com.facebook.shuiai.project.application.MyApplication;
import com.facebook.shuiai.project.fragment.BaseFragment;
import com.facebook.shuiai.project.fragment.DiscoverFragment;
import com.facebook.shuiai.project.fragment.HomeFragment;
import com.facebook.shuiai.project.fragment.LoansFragment;
import com.facebook.shuiai.project.fragment.PersonFragment;
import com.facebook.shuiai.project.util.ActivityUtil;
import com.facebook.shuiai.project.util.StatusBarUtil;
import com.facebook.shuiai.project.util.ToastUtils;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity {

    FrameLayout frameLayout;
    RadioGroup radioGroup;

    //装fragment的实例集合
    private ArrayList<BaseFragment> fragments;
    private int position = 0;
    //缓存Fragment或上次显示的Fragment
    private Fragment tempFragment;
    private boolean isExit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setStatusBar(this);
        setContentView(R.layout.activity_main);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        initFragment();
        initListener();
    }


    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new LoansFragment());
        fragments.add(new DiscoverFragment());
        fragments.add(new PersonFragment());
    }

    private void initListener() {
        radioGroup.check(R.id.home_radio_button);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.home_radio_button: //首页
                        position = 0;
                        break;
                    case R.id.loans_radio_button: //贷款
                        position = 1;
                        break;
                    case R.id.discover_radio_button: //发现
                        position = 2;
                        break;
                    case R.id.me_radio_button: //我的
                        position = 3;
                        break;
                    default:
                        position = 0;
                        break;
                }
                BaseFragment baseFragment = getFragment(position);
                switchFragment(tempFragment, baseFragment);
            }
        });
        BaseFragment baseFragment = getFragment(position);
        switchFragment(tempFragment, baseFragment);
    }

    private void switchFragment(Fragment fragment, BaseFragment nextFragment) {

        if (tempFragment != nextFragment) {
            tempFragment = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加成功
                if (!nextFragment.isAdded()) {
                    //隐藏当前的Fragment
                    if (fragment != null) {
                        transaction.hide(fragment);
                    }
                    //添加Fragment
                    transaction.add(R.id.frameLayout, nextFragment).commit();
                } else {
                    //隐藏当前Fragment
                    if (fragment != null) {
                        transaction.hide(fragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }

    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exitBy2Click() {
        Timer tExit;
        if (!isExit) {
            isExit = true; // 准备退出
            ToastUtils.showToast(this, "再按一次退出程序");
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000);

        } else {
            ActivityUtil.exitApp(MyApplication.activitys);
        }
    }
}
