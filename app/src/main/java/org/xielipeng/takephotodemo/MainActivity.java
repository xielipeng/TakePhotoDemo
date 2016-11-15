package org.xielipeng.takephotodemo;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;

import org.xielipeng.takephotodemo.takephoto.CustomTakePhotoActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.widget.GridLayout.VERTICAL;

public class MainActivity extends CustomTakePhotoActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    ArrayList<TImage> mImgs = new ArrayList<TImage>();
    ImgAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2, VERTICAL, false));
    }

    @OnClick({R.id.btn_selectimg})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_selectimg:
                popup(MainActivity.this, 5, false, true);
                break;

            default:
                break;
        }
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        mImgs = result.getImages();

        mAdapter = new ImgAdapter(R.layout.recyclerview_item_image, mImgs);
        mRecyclerView.setAdapter(mAdapter);
    }


    private class ImgAdapter extends BaseQuickAdapter<TImage> {

        public ImgAdapter(int layoutResId, List<TImage> data) {
            super(layoutResId, data);
        }

        @Override
        protected void startAnim(Animator anim, int index) {
            super.startAnim(anim, index);
            if (index < 5) anim.setStartDelay(index * 150);
        }

        @Override
        protected void convert(BaseViewHolder holder, TImage item) {
            ImageView imageView = holder.getView(R.id.iv_image);
            if (imageView != null) {
                Glide.with(MyAppliction.getInstance())
                        .load(item.getPath())
                        .error(R.mipmap.ic_launcher)
                        .into(imageView);
            }
        }
    }

}
