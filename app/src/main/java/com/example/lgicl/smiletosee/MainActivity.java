package com.example.lgicl.smiletosee;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private Camera camera; // 相机对象
    private boolean isPreview = false; // 是否为预览模式

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置全屏显示
        setContentView(R.layout.main);

        SurfaceView sv = (SurfaceView) findViewById(R.id.surfaceView1); // 获取SurfaceView组件，用于显示相机预览
        final SurfaceHolder sh = sv.getHolder();
        sh.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); // 设置该SurfaceHolder自己不维护缓冲

        Button preview = (Button) findViewById(R.id.preview); // 获取“预览”按钮
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 如果相机为非预览模式，则打开相机
                if (!isPreview) {
                    camera=Camera.open(); // 打开相机
                }
                try {
                    camera.setPreviewDisplay(sh); // 设置用于显示预览的SurfaceView
                    Camera.Parameters parameters = camera.getParameters();	//获取相机参数
                    parameters.setPictureSize(640, 480);	//设置预览画面的尺寸
                    parameters.setPictureFormat(PixelFormat.JPEG);	//指定图片为JPEG图片
                    parameters.set("jpeg-quality", 80);	//设置图片的质量
                    parameters.setPictureSize(640, 480); 	//设置拍摄图片的尺寸
                    parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
                    camera.setParameters(parameters);	//重新设置相机参数
                    camera.startPreview();	//开始预览
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        Button takePhoto = (Button) findViewById(R.id.takephoto); // 获取“拍照”按钮
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(camera!=null){
                    camera.takePicture(null, null, jpeg); // 进行拍照
                }
            }
        });
    }
    //实现拍照的回调接口
    final PictureCallback jpeg = new PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            // 根据拍照所得的数据创建位图
            final Bitmap bm = BitmapFactory.decodeByteArray(data, 0,
                    data.length);
            // 加载layout/save.xml文件对应的布局资源
            View saveView = getLayoutInflater().inflate(R.layout.save, null);
            final EditText photoName = (EditText) saveView
                    .findViewById(R.id.phone_name);
            // 获取对话框上的ImageView组件
            ImageView show = (ImageView) saveView.findViewById(R.id.show);
            show.setImageBitmap(bm);			// 显示刚刚拍得的照片
            camera.stopPreview();		//停止预览
            isPreview = false;

            // 使用对话框显示saveDialog组件
            new AlertDialog.Builder(MainActivity.this).setView(saveView)
                    .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            File file = new File("/sdcard/pictures/" + photoName
                                    .getText().toString() + ".jpg");//创建文件对象
                            try {
                                file.createNewFile();								//创建一个新文件
                                FileOutputStream fileOS = new FileOutputStream(file);	//创建一个文件输出流对象
                                //将图片内容压缩为JPEG格式输出到输出流对象中
                                bm.compress(Bitmap.CompressFormat.JPEG, 100, fileOS);
                                fileOS.flush();									//将缓冲区中的数据全部写出到输出流中
                                fileOS.close();									//关闭文件输出流对象
                                isPreview = true;
                                resetCamera();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    isPreview = true;
                    resetCamera();	//重新预览

                }
            }).show();
        }
    };
    //重新预览
    private void resetCamera(){
        if(isPreview){
            camera.startPreview();	//开启预览
        }
    }
    //停止预览并释放资源
    @Override
    protected void onPause() {
        if(camera!=null){
            camera.stopPreview();	//停止预览
            camera.release();	//释放资源
        }
        super.onPause();
    }
}
