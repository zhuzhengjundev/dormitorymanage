package cn.dormitorymanage.homeActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.android.volley.Response;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.dormitorymanage.R;
import cn.dormitorymanage.function.HttpRequest;
import cn.dormitorymanage.function.MyApplication;
import cn.dormitorymanage.function.UploadImg;
import cn.dormitorymanage.function.User;

public class ShiwuzhaolingAddActivity extends AppCompatActivity {

    //图片选择所用到的
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    protected Uri titleImgUrl;//图片路径
    private ProgressDialog dialog=null;
    private String result="";
    private String resultImg = "";
    private String userTitleImgUrl = "";
    private User user;
    //图片选择所用到的

    private ImageView imageView_img;
    private EditText editText_title,editText_content,editText_phoneNum;
    private String lostarticle_stat = "";
    private Spinner spinner;
    private List<String> stringList = new ArrayList<>();
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shiwuzhaoling_add);
        user = new User(ShiwuzhaolingAddActivity.this);
        findViewById(R.id.shiwuzhaolingAddActivity_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initView();
    }

    private void initView() {
        imageView_img = findViewById(R.id.shiwuzhaolingAddActivity_img);
        imageView_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChoosePicDialog(view);
            }
        });
        editText_title = findViewById(R.id.shiwuzhaolingAddActivity_title);
        editText_content = findViewById(R.id.shiwuzhaolingAddActivity_content);
        editText_phoneNum = findViewById(R.id.shiwuzhaolingAddActivity_phonenumber);
        spinner = findViewById(R.id.shiwuzhaolingAddActivity_stat);
        stringList.add("认领");
        stringList.add("丢失");
        arrayAdapter = new ArrayAdapter(ShiwuzhaolingAddActivity.this, android.R.layout.simple_spinner_item, stringList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lostarticle_stat = stringList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        findViewById(R.id.shiwuzhaolingAddActivity_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resultImg.equals("")){
                    Toast.makeText(ShiwuzhaolingAddActivity.this,"请添加失物的图片",Toast.LENGTH_SHORT).show();
                }else{
                    dialog = new ProgressDialog(ShiwuzhaolingAddActivity.this);
                    dialog.setTitle("请稍后...");
                    dialog.show();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            result = UploadImg.formUpload("http://" + MyApplication.ipLocation + "/dormitoryManage/uploadShiwuImg", resultImg);
                            userTitleImgUrl = "http://" + MyApplication.ipLocation + "/dormitoryManage/upload/shiwuImg/" + result;
                            handler.sendEmptyMessage(1);
                        }
                    }).start();
                }
            }
        });
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Log.d("上传的照片有", userTitleImgUrl);
                    upDate();
                    dialog.dismiss();
                    break;
                case 2:
                    dialog.dismiss();
                    break;
                default:
                    break;
            }

        }
    };

    private void upDate() {
        String title = editText_title.getText().toString().trim();
        String content = editText_content.getText().toString().trim();
        String phoneNum = editText_phoneNum.getText().toString().trim();
        try {
            HttpRequest.postJSONObject("addLostarticle", new JSONObject("{\"lostarticle_title\":\"" + title + "\",\"lostarticle_content\":\"" + content + "\",\"lostarticle_img\":\"" + userTitleImgUrl + "\",\"lostarticle_phonenumber\":\"" + phoneNum + "\",\"lostarticle_stat\":\"" + lostarticle_stat + "\",\"lostarticle_time\":\"" + new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date(System.currentTimeMillis())) + "\"}"), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    try {
                        if (jsonObject.getString("isOk").equals("true")) {
                            Toast.makeText(ShiwuzhaolingAddActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void showChoosePicDialog(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择图片");
        String[] items = { "选择本地照片", "拍照" };
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent openAlbumIntent = new Intent();
                        if (Build.VERSION.SDK_INT < 19) {
                            openAlbumIntent.setAction(Intent.ACTION_GET_CONTENT);
                            openAlbumIntent.setType("image/*");
                        } else {
                            openAlbumIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            openAlbumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                        }
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE: // 拍照
                        takePicture();
                        break;
                }
            }
        });
        builder.create().show();
    }

    private void takePicture() {
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (Build.VERSION.SDK_INT >= 23) {
            // 需要申请动态权限
            int check = ContextCompat.checkSelfPermission(this, permissions[0]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (check != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory(), user.getStu_id() + "_" + System.currentTimeMillis()+".jpg");
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= 24) {
            openCameraIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            titleImgUrl = FileProvider.getUriForFile(ShiwuzhaolingAddActivity.this, "cn.dormitorymanage.fileProvider", file);
        } else {
            titleImgUrl = Uri.fromFile(new File(Environment
                    .getExternalStorageDirectory()+"/idleCampusImg", user.getStu_id() + "_" + System.currentTimeMillis()+".jpg"));
        }
        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, titleImgUrl);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    if (Build.VERSION.SDK_INT >= 24) {
                        Log.d("图片选择", "相机：" + getFPUriToPath(ShiwuzhaolingAddActivity.this,titleImgUrl));
                        resultImg = getFPUriToPath(ShiwuzhaolingAddActivity.this,titleImgUrl);
                        Glide.with(ShiwuzhaolingAddActivity.this)// 为当前Activity 加载图片
                                .load(resultImg)// 从URL 中加载
                                .placeholder(R.drawable.titleimg)
                                .error(R.drawable.titleimg)
                                .into(imageView_img);
                    }else{
                        Log.d("图片选择", "相机：" + getPath(ShiwuzhaolingAddActivity.this, titleImgUrl));
                        resultImg = getPath(ShiwuzhaolingAddActivity.this, titleImgUrl);
                        Glide.with(ShiwuzhaolingAddActivity.this)// 为当前Activity 加载图片
                                .load(resultImg)// 从URL 中加载
                                .placeholder(R.drawable.titleimg)
                                .error(R.drawable.titleimg)
                                .into(imageView_img);
                    }
                    break;
                case CHOOSE_PICTURE:
                    Log.d("图片选择", "相册：" + getPath(ShiwuzhaolingAddActivity.this, data.getData()));
                    resultImg = getPath(ShiwuzhaolingAddActivity.this,  data.getData());
                    Glide.with(ShiwuzhaolingAddActivity.this)// 为当前Activity 加载图片
                            .load(resultImg)// 从URL 中加载
                            .placeholder(R.drawable.titleimg)
                            .error(R.drawable.titleimg)
                            .into(imageView_img);
                    break;
                default:
                    break;
            }
        }
    }

    private static String getFPUriToPath(Context context, Uri uri) {
        try {
            List<PackageInfo> packs = context.getPackageManager().getInstalledPackages(PackageManager.GET_PROVIDERS);
            if (packs != null) {
                String fileProviderClassName = FileProvider.class.getName();
                for (PackageInfo pack : packs) {
                    ProviderInfo[] providers = pack.providers;
                    if (providers != null) {
                        for (ProviderInfo provider : providers) {
                            if (uri.getAuthority().equals(provider.authority)) {
                                if (provider.name.equalsIgnoreCase(fileProviderClassName)) {
                                    Class<FileProvider> fileProviderClass = FileProvider.class;
                                    try {
                                        Method getPathStrategy = fileProviderClass.getDeclaredMethod("getPathStrategy", Context.class, String.class);
                                        getPathStrategy.setAccessible(true);
                                        Object invoke = getPathStrategy.invoke(null, context, uri.getAuthority());
                                        if (invoke != null) {
                                            String PathStrategyStringClass = FileProvider.class.getName() + "$PathStrategy";
                                            Class<?> PathStrategy = Class.forName(PathStrategyStringClass);
                                            Method getFileForUri = PathStrategy.getDeclaredMethod("getFileForUri", Uri.class);
                                            getFileForUri.setAccessible(true);
                                            Object invoke1 = getFileForUri.invoke(invoke, uri);
                                            if (invoke1 instanceof File) {
                                                String filePath = ((File) invoke1).getAbsolutePath();
                                                return filePath;
                                            }
                                        }
                                    } catch (NoSuchMethodException e) {
                                        e.printStackTrace();
                                    } catch (InvocationTargetException e) {
                                        e.printStackTrace();
                                    } catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getPath(final Context context, final Uri uri) {
        boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri))
            {
                String docId = DocumentsContract.getDocumentId(uri);
                String[] split = docId.split(":");
                String type = split[0];

                if ("primary".equalsIgnoreCase(type))
                {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];

                }

            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri))
            {

                String id = DocumentsContract.getDocumentId(uri);
                Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);

            }
            // MediaProvider
            else if (isMediaDocument(uri))
            {
                String docId = DocumentsContract.getDocumentId(uri);
                String[] split = docId.split(":");
                String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type))
                {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

                } else if ("video".equals(type))
                {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

                } else if ("audio".equals(type))
                {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

                }

                String selection = "_id=?";
                String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }

        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme()))
        {
            return getDataColumn(context, uri, null, null);

        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme()))
        {
            return uri.getPath();

        }
        return null;
    }

    public String getDataColumn(Context context, Uri uri, String selection,String[] selectionArgs) {

        Cursor cursor = null;
        String column = "_data";
        String[] projection = {column};

        try
        {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst())
            {
                int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        }
        finally
        {
            if (cursor != null)
            {
                cursor.close();
            }

        }
        return null;

    }

    public boolean isExternalStorageDocument(Uri uri)
    {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());

    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public boolean isDownloadsDocument(Uri uri)
    {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());

    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public boolean isMediaDocument(Uri uri){
        return "com.android.providers.media.documents".equals(uri.getAuthority());

    }
}
