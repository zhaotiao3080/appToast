package com.android.zt.openlibrary;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

public class AppToast {
    /**
     * 自定义吐司工具
     */
    private final static int DURATION = 2500;
    private static Toast _toast;
    private static Context mContext;

    public static void init(Context context) {
        mContext=context.getApplicationContext();
        if (_toast == null) {
            _toast = Toast.makeText(mContext, null, Toast.LENGTH_SHORT);
        }
    }

    public synchronized static void show(final String content, int delay) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                show(content);
            }
        }, delay);
    }

    public synchronized static void show(String content) {
        String text = String.format(" %1$s ", content);

        _toast.setText(text);
        _toast.setDuration(Toast.LENGTH_SHORT);

        _toast.show();
    }

    public synchronized static void show(int strResId) {
        String content = mContext.getResources().getString(strResId);
        show(content);
    }

}
