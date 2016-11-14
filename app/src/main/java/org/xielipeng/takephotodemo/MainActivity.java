package org.xielipeng.takephotodemo;

import android.os.Bundle;
import android.view.View;

import org.xielipeng.takephotodemo.takephoto.CustomTakePhotoActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends CustomTakePhotoActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_selectimg})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_selectimg:

                break;

            default:
                break;
        }
    }
}
