package org.xielipeng.takephotodemo.takephoto;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.jph.takephoto.app.TakePhotoActivity;

import org.xielipeng.takephotodemo.R;

/**
 * Created by xielipeng on 2016/10/23.
 */
public class CustomTakePhotoActivity extends TakePhotoActivity {
    private static final String TAG = "CustomTakePhotoActivity";

    // 是否允许全屏
    private boolean mAllowFullScreen = true;

    public void setAllowFullScreen(boolean allowFullScreen) {
        this.mAllowFullScreen = allowFullScreen;
    }

    private View rootView;
    protected Dialog dialog;

    private TakePhotoHelper takePhotoHelper;

    protected int limit; // 最多可以选择几张
    protected boolean isCrop; // 是否压缩
    protected boolean isCompress; // 是否裁剪

    protected void popup(Context context, int limit, boolean isCrop, boolean isCompress) {
        this.limit = limit;
        this.isCrop = isCrop;
        this.isCompress = isCompress;
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rootView = inflater.inflate(R.layout.view_popup_button, null, false);

        takePhotoHelper = TakePhotoHelper.of(rootView);
        dialog = PopupUtil.makePopup(context, rootView);
        dialog.show();
    }

    public void onClickTake(View view) {
        if (dialog != null) {
            dialog.dismiss();
        }
        takePhotoHelper.onClick(view, getTakePhoto(), limit, isCrop, isCompress);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mAllowFullScreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }

        /**
         * 如想让你的app在android 6.0系统上也能运行的话，需要动态获取权限，没有权限的话分享sdk会出错，
         * 参考以下代码做动态获取权限,适配安卓6.0系统你需要最新的android.support.v4包，或者v13的包可也以
         */
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP,
                    Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS,
                    Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 100);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
