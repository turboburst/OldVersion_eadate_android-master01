package com.somoplay.eadate.view.tabme;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.somoplay.eadate.R;
import com.somoplay.eadate.view.Interfaces.WebRequestActivityInterface;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by work on 2016-02-21.
 */
public class MePersonalInfoActivity extends Activity implements WebRequestActivityInterface {

    private static final String TAG = MePersonalInfoActivity.class.getName();
    private MeDynamicGridView gridView;
    private ListView infoListView;
//    AlertDialog levelDialog;
//
//    // Strings to Show In Dialog with Radio Buttons
//    final CharSequence[] gender = {"男", "女"};
//    final CharSequence[] marriage = {"保密", "单身", "恋爱中", "已婚", "同性"};
//
//    private int day;
//    private int month;
//    private int year;

    /* 头像文件 */
    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";

    /* 请求识别码 */
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;

    // 裁剪后图片的宽(X)和高(Y),480 X 480的正方形。
    private static int output_X = 480;
    private static int output_Y = 480;

    private ImageView image1 = null;
    private ImageView image2 = null;
    private ImageView image3 = null;
    private ImageView image4 = null;
    private String imageNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_activity_personal_info);

        gridView = (MeDynamicGridView) findViewById(R.id.dynamic_grid);
        gridView.setAdapter(new MePersonalPhotoGridAdapter(MePersonalInfoActivity.this,
                new ArrayList<String>(Arrays.asList(MeGridItem.photos)),4));

        gridView.setOnDragListener(new MeDynamicGridView.OnDragListener() {
            @Override
            public void onDragStarted(int position) {
                Log.d(TAG, "drag started at position " + position);
            }

            @Override
            public void onDragPositionsChanged(int oldPosition, int newPosition) {
                Log.d(TAG, String.format("drag item position changed from %d to %d", oldPosition, newPosition));
            }
        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                gridView.startEditMode(position);
                return true;
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MePersonalInfoActivity.this, parent.getAdapter().getItem(position).toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });


        infoListView = (ListView) findViewById(R.id.listview);
//        image1 = (ImageView) findViewById(R.id.photo1);
//        image2 = (ImageView) findViewById(R.id.photo2);
//        image3 = (ImageView) findViewById(R.id.photo3);
//        image4 = (ImageView) findViewById(R.id.photo4);

//        DragLinearLayout dragLinearLayout = (DragLinearLayout) findViewById(R.id.dragLayout);
        // set all children draggable except the first (the header)
//        for(int i = 0; i < dragLinearLayout.getChildCount(); i++){
//            View child = dragLinearLayout.getChildAt(i);
//            dragLinearLayout.setViewDraggable(child, child); // the child is its own drag handle
//        }

        infoListView.setAdapter(new MePersonalInfoAdapter(this));
        infoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // 修改名字
                if (i == 0) {
                    Intent intent = new Intent(MePersonalInfoActivity.this, MeEditTextActivity.class);
                    intent.putExtra("ACTIVITYID", 0);
                    startActivity(intent);
                }
                // 显示ID
                else if (i == 1) {
                }
                // 显示二维码
                else if (i == 2) {
                }
                // 修改性别
                else if (i == 3) {
                    Intent intent = new Intent(MePersonalInfoActivity.this, MeListViewActivity.class);
                    intent.putExtra("ACTIVITYID", 3);
                    startActivity(intent);
                }
                // 修改地址
                else if (i == 4) {
                    Intent intent = new Intent(MePersonalInfoActivity.this, MeEditTextActivity.class);
                    intent.putExtra("ACTIVITYID", 4);
                    startActivity(intent);}
                // 修改出生日期
                else if (i == 5) {
                    Intent intent = new Intent(MePersonalInfoActivity.this, MeBirthDateActivity.class);
                    intent.putExtra("ACTIVITYID", 5);
                    startActivity(intent);
                }
                // 修改感情生活
                else if (i == 6) {

                    Intent intent = new Intent(MePersonalInfoActivity.this, MeListViewActivity.class);
                    intent.putExtra("ACTIVITYID", 6);
                    startActivity(intent);
                }
                // 修改个性签名
                else if(i == 7) {
                    Intent intent = new Intent(MePersonalInfoActivity.this, MeEditTextActivity.class);
                    intent.putExtra("ACTIVITYID", 7);
                    startActivity(intent);
                }
                // 修改地区
                else if (i == 8) {
                    Intent intent = new Intent(MePersonalInfoActivity.this, MeListViewActivity.class);
                    intent.putExtra("ACTIVITYID", 8);
                    startActivity(intent);}
                // 修改学校
                else if (i == 9) {
                    Intent intent = new Intent(MePersonalInfoActivity.this, MeSchoolActivity.class);
//                    intent.putExtra("ACTIVITYID", 9);
                    startActivity(intent);
                }
                // 修改工作
                else if (i == 10) {
                    Intent intent = new Intent(MePersonalInfoActivity.this, MeJobActivity.class);
                    intent.putExtra("ACTIVITYID", 10);
                    startActivity(intent);
                }
            }
        });

    }

    // set the profile photo
    public void editPhoto(View view) {

//        imageNum = view.getTag().toString();

        AlertDialog.Builder photoDialog = new AlertDialog.Builder(this);
        photoDialog.setTitle("Camera");
        photoDialog.setMessage("Where ?");

        photoDialog.setPositiveButton("Gallery", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                Intent intentFromGallery = new Intent();
                // 设置文件类型
                intentFromGallery.setType("image/*");
                intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
            }
        });// end Positive Button

        photoDialog.setNegativeButton("Take Photo", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Does your device have a camera?
                if (hasCamera()) {
                    Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    // 判断存储卡是否可用，存储照片文件
                    if (hasSdcard()) {
                        intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                                .fromFile(new File(Environment
                                        .getExternalStorageDirectory(), IMAGE_FILE_NAME)));
                    }

                    startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);
                } else {
                    messageCamera();
                }
            }
        }); // end Negative Button
        photoDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {

        // 用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getApplication(), "取消", Toast.LENGTH_LONG).show();
            return;
        }

        switch (requestCode) {
            case CODE_GALLERY_REQUEST:
                cropRawPhoto(intent.getData());
                break;

            case CODE_CAMERA_REQUEST:
                if (hasSdcard()) {
                    File tempFile = new File(
                            Environment.getExternalStorageDirectory(),
                            IMAGE_FILE_NAME);
                    cropRawPhoto(Uri.fromFile(tempFile));
                } else {
                    Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_LONG).show();
                }

                break;

            case CODE_RESULT_REQUEST:
                if (intent != null) {
                    setImageToHeadView(intent);
                }

                break;
        }

        super.onActivityResult(requestCode, resultCode, intent);
    }

    /**
     * 裁剪原始的图片
     */
    public void cropRawPhoto(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        // 设置裁剪
        intent.putExtra("crop", "true");

        // aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", output_X);
        intent.putExtra("outputY", output_Y);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, CODE_RESULT_REQUEST);
    }

    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */
    private void setImageToHeadView(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            switch (imageNum) {
                case "image1":
                    image1.setImageBitmap(photo);
                    break;
                case "image2":
                    image2.setImageBitmap(photo);
                    break;
                case "image3":
                    image3.setImageBitmap(photo);
                    break;
                case "image4":
                    image4.setImageBitmap(photo);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }

    // method to check you have a Camera
    private boolean hasCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    private void messageCamera(){
        AlertDialog.Builder cameraDialog = new AlertDialog.Builder(this);
        cameraDialog.setTitle("No camera");
        cameraDialog.setMessage("Your phone do not have a Camera!!");
        cameraDialog.setPositiveButton("sure", null);
        cameraDialog.show();
    }

    public void moveImage() {

    }

    public static void setupDragSort(View view) {
        view.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent event) {
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                View dragView = (View) event.getLocalState();
                switch (event.getAction()) {
                    case DragEvent.ACTION_DROP:
                        if (view != dragView) {
                            swapViewGroupChildren(viewGroup, view, dragView);
                        }
                        break;
                }
                return true;
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                v.startDrag(null, new View.DragShadowBuilder(v), v, 0);
                return true;
            }
        });
    }

    public static void swapViewGroupChildren(ViewGroup viewGroup, View firstView, View secondView) {
        int firstIndex = viewGroup.indexOfChild(firstView);
        int secondIndex = viewGroup.indexOfChild(secondView);

        if (firstIndex < secondIndex) {
            viewGroup.removeViewAt(secondIndex);
            viewGroup.removeViewAt(firstIndex);
            viewGroup.addView(secondView, firstIndex);
            viewGroup.addView(firstView, secondIndex);
        }
        else {
            viewGroup.removeViewAt(firstIndex);
            viewGroup.removeViewAt(secondIndex);
            viewGroup.addView(firstView, secondIndex);
            viewGroup.addView(secondView, firstIndex);
        }

    }

    @Override
    public void onBackPressed() {
        if (gridView.isEditMode()) {
            gridView.stopEditMode();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public JSONObject webRequestCallBack(JSONObject jsonObject) {
        return null;
    }
}
