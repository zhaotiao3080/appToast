package com.android.zt.apptoastutil.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.zt.apptoastutil.R;
import com.android.zt.openlibrary.AppToast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tv_show_toast)
    TextView tvShowToast;

//    https://github.com/zhaotiao3080/AppToast.git

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.tv_show_toast)
    public void onViewClicked() {
        AppToast.show("测试 AppToast success... o(*￣︶￣*)o 1");
//        AppToast.show(R.string.test_str);
//        AppToast.show("测试 AppToast success... o(*￣︶￣*)o 3",2000);
//        AppToast.show(R.string.test_str,2000);
    }
}
