### TakePhotoDemo
基于Takephoto库再做的一层封装，使用更加简单
=============
##使用说明
1、 继承 CustomTakePhotoActivity (在Fragment下使用继承相应的Fragment，目前没有实现，你可以自己新建一个CustomTakePhotoFragment)
2、调用以下方法
```java
popup(MainActivity.this, 5, false, true);
3、调用Takephpto提供的以下方法获取选择的图片
```java
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
        // 
    }
