package org.xielipeng.takephotodemo.takephoto;

import android.net.Uri;
import android.os.Environment;
import android.view.View;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;

import org.xielipeng.takephotodemo.R;

import java.io.File;

/**
 * 选择图片的一些配置
 * - 支持通过相机拍照获取图片
 * - 支持从相册选择图片
 * - 支持从文件选择图片
 * - 支持多图选择
 * - 支持批量图片裁切
 * - 支持批量图片压缩
 * - 支持对图片进行压缩
 * - 支持对图片进行裁剪
 * - 支持对裁剪及压缩参数自定义
 * Created by xielipeng on 2016/10/23.
 */

public class TakePhotoHelper {
    private static final String TAG = "TakePhotoHelper";

    private View rootView;

    public static TakePhotoHelper of(View rootView) {
        return new TakePhotoHelper(rootView);
    }

    private TakePhotoHelper(View rootView) {
        this.rootView = rootView;
    }

    public void onClick(View view, TakePhoto takePhoto, int limit, boolean isCrop, boolean isCompress) {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        Uri imageUri = Uri.fromFile(file);

        configCompress(takePhoto, isCompress);

        switch (view.getId()) {
            case R.id.btnCapture: // 拍照
                if (isCrop) {
                    takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions());
                } else {
                    takePhoto.onPickFromCapture(imageUri);
                }
                break;
            case R.id.btnPhoto: // 相册
                if (limit > 1) {
                    if (isCrop) {
                        // 从相册中选择多张图片并裁剪
                        takePhoto.onPickMultipleWithCrop(limit, getCropOptions());
                    } else {
                        // 从相册中选择多张图片不裁剪
                        takePhoto.onPickMultiple(limit);
                    }
                    return;
                }
                if (isCrop) {
                    // 从相册中选择单张图片并裁剪
                    takePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions());
                } else {
                    // 从相册中选择单张图片不裁剪
                    takePhoto.onPickFromGallery();
                }
                break;

            default:
                break;
        }
    }

    /**
     * 压缩配置
     *
     * @param takePhoto
     * @param isCompress 是否压缩
     */
    private void configCompress(TakePhoto takePhoto, boolean isCompress) {
        if (!isCompress) {
            takePhoto.onEnableCompress(null, false);
            return;
        }
        int maxSize = 102400; // B
        int maxPixel = 700; // px
        CompressConfig config = new CompressConfig.Builder().setMaxPixel(maxSize).setMaxPixel(maxPixel).create();
        takePhoto.onEnableCompress(config, false);
    }

    /**
     * 裁剪配置
     *
     * @return
     */
    private CropOptions getCropOptions() {
        int height = 700;
        int width = 700;

        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setOutputX(width).setOutputY(height);
        builder.setWithOwnCrop(false);
        return builder.create();
    }

}
