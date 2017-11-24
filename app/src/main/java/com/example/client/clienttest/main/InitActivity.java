package com.example.client.clienttest.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.client.clienttest.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 滑动欢迎页
 */
public class InitActivity extends FragmentActivity {
    @BindView(R.id.welcome_pager)
    ViewPager welcomePager;
    @BindView(R.id.welcome_guide_btn)
    Button welcomeGuideBtn;

    private List<View> list;
    public static SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        ButterKnife.bind(this);

        // 获取本地存储
        sp = getSharedPreferences("info", MODE_PRIVATE);

        sp.edit().putBoolean("info", false).commit();
        sp.edit().putBoolean("Automatic", false).commit();

        if (sp.getBoolean("Automatic", false)) { // 自动登录
            startActivity(new Intent(InitActivity.this, MainActivity.class));
        } else if (sp.getBoolean("init", false)) { // 不可自动登录
            startActivity(new Intent(InitActivity.this, LoginActivity.class));
        } else { // 第一次打开软件
            initView();
            initViewPager();
        }

    }

    // 初始化控件
    private void initView() {
        list = new ArrayList<View>();
        ImageView iv1 = new ImageView(this);
        iv1.setImageResource(R.drawable.a0);
        ImageView iv2 = new ImageView(this);
        iv2.setImageResource(R.drawable.a1);
        ImageView iv3 = new ImageView(this);
        iv3.setImageResource(R.drawable.a2);
        list.add(iv1);
        list.add(iv2);
        list.add(iv3);
    }

    // 跳转至登录页面
    @OnClick(R.id.welcome_guide_btn)
    public void welcome_guide_btn(View view) {
        // 页面跳转
        startActivity(new Intent(InitActivity.this, LoginActivity.class));
    }

    // 初始化ViewPager
    private void initViewPager() {
        welcomePager.setAdapter(new MyPagerAdapter());

        // 监听ViewPager滑动效果
        welcomePager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            // 页卡被选中的方法
            @Override
            public void onPageSelected(int position) {
                // 进入第三个页面
                if (position == 2) {
                    welcomeGuideBtn.setVisibility(View.VISIBLE);
                } else {
                    welcomeGuideBtn.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    // 自定义ViewPager的适配器
    class MyPagerAdapter extends PagerAdapter {
        // 计算需要多少item显示
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        // 初始化item实例方法
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

        // item销毁的方法
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // 注销父类销毁item的方法，因为此方法并不是使用此方法
            container.removeView(list.get(position));
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

}











