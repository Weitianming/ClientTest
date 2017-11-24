package com.example.client.clienttest.main;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.client.clienttest.R;
import com.example.client.clienttest.util.BitmapCircular;
import com.example.client.clienttest.view.HorizontalListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends Activity {

    @BindView(R.id.login_Head)
    ImageView loginHead;
    @BindView(R.id.account)
    EditText account;
    @BindView(R.id.account_Buttom)
    Button accountButtom;
    @BindView(R.id.login_list)
    HorizontalListView loginList;
    @BindView(R.id.list_layout)
    RelativeLayout listLayout;
    @BindView(R.id.list_view)
    View listView;
    @BindView(R.id.Cipher)
    EditText Cipher;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.login_layout)
    LinearLayout loginLayout;
    @BindView(R.id.register)
    TextView register;

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
        InfoSize(); // 重新设置尺寸
        SP(); // 本地存储
        BitmapCircular(); // 头像圆形处理
    }

    // 初始化控件
    private void init() {
        InfoSize(); // 重新设置尺寸
    }

    // 对布局尺寸进行优化
    private void InfoSize() {
        int height;
        // 获取屏幕尺寸
        WindowManager wm = this.getWindowManager();
        int w = wm.getDefaultDisplay().getWidth();
        int h = wm.getDefaultDisplay().getHeight();
        if (h > 1000) {
            height = 16;
        } else {
            height = 11;
        }
        // 设置控件尺寸
        ViewGroup.LayoutParams lpe1; // 账号编辑框
        lpe1 = (ViewGroup.LayoutParams) account.getLayoutParams();
        lpe1.width = w;
        lpe1.height = h / height;
        account.setLayoutParams(lpe1);

        ViewGroup.LayoutParams lpe2; // 密码编辑框
        lpe2 = (ViewGroup.LayoutParams) Cipher.getLayoutParams();
        lpe2.width = w;
        lpe2.height = h / height;
        Cipher.setLayoutParams(lpe2);

        ViewGroup.LayoutParams lpb; // 登录按钮
        lpb = (ViewGroup.LayoutParams) login.getLayoutParams();
        lpb.width = w - 40;
        lpb.height = h / height;
        login.setLayoutParams(lpb);

        ViewGroup.LayoutParams list_pb; // 列表尺寸
        list_pb = (ViewGroup.LayoutParams) listLayout.getLayoutParams();
        list_pb.width = w;
        list_pb.height = (int) ((h / height) * 2.5);
        listLayout.setLayoutParams(list_pb);

    }

    // 读取已保存的账号密码
    public void SP() {
        sp = getSharedPreferences("info", MODE_PRIVATE);
        if (sp.getBoolean("Remember", false)) {
            account.setText(sp.getString("name", ""));
            Cipher.setText(sp.getString("pwd", ""));
            // 判断自动登录
            if (sp.getBoolean("Automatic", false)) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        }
    }

    // 查找已登录过的账号
    public void InfoAccount() {
//        listAccount = new ArrayList<String>();
//        listCipher = new ArrayList<String>();
//
//        for (int i = 0; i < InfoCursor.getCount(); i++) {
//            InfoCursor.moveToPosition(i);
//
//            listAccount.add(InfoCursor.getString(0));
//            listCipher.add(InfoCursor.getString(1));
//
//        }

    }

    // 头像处理
    public void BitmapCircular() {
        Bitmap Resources = BitmapFactory.decodeResource(getResources(),
                R.drawable.head);
        Bitmap bitmap = new BitmapCircular().setCircular(Resources, 200.0f);
        loginHead.setImageBitmap(bitmap);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        db = new LoginDataBase(LoginActivity.this).getReadableDatabase();
//        InfoCursor = db.query("InfoTable", null, null, null, null, null, null);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        login.setOnClickListener(this);
//        register.setOnClickListener(this);
//        AButton.setOnClickListener(this);
//        layout.setOnClickListener(this);
//        listView.setOnItemClickListener(new ListItemClickListener());
    }

    @Override
    protected void onStop() {
        super.onStop();
//        db.close();
//        InfoCursor.close();
        finish();
    }


    public static final String BASE_URL = "https://api.douban.com/v2/movie/";

    // 登录按钮事件监听
    @OnClick(R.id.login)
    public void login_but(View view) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }




    // 自动登录和记住密码功能
    private void Remember() {
        sp.edit().putBoolean("Automatic", true).commit();
        sp.edit().putBoolean("Remember", true).commit();
        sp.edit().putString("name", account.getText().toString())
                .putString("pwd", Cipher.getText().toString()).commit();
    }



}
