/* *
   * Copyright (C) 2017 BaoliYota Tech. Co., Ltd, LLC - All Rights Reserved.
   *
   * Confidential and Proprietary.
   * Unauthorized copying of this file, via any medium is strictly prohibited.
   * */

package com.android.systemui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.baoliyota.contract.IEpdScreenContract;
import com.android.baoliyota.imageloader.IBaoliYotaImageLoader;
import com.android.baoliyota.model.ApiConstants;
import com.android.baoliyota.model.bean.EpdScreenBean;
import com.android.baoliyota.network.BaoliYotaHttpManager;
import com.android.baoliyota.network.callback.StringCallback;
import com.android.baoliyota.network.model.HttpParams;
import com.android.baoliyota.presenter.EpdScreenPresenter;
import com.android.systemuib.R;
import com.coolyota.analysis.CYAnalysis;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IEpdScreenContract.IView {

    @Override
    protected void onResume() {
        super.onResume();
        CYAnalysis.onResume(this, "SPage");

    }

    @Override
    protected void onPause() {
        super.onPause();
        CYAnalysis.onPause(this);
    }

    public static final String SERVER = "http://server.jeasonlzy.com/OkHttpUtils/";
    //    public static final String SERVER = "http://192.168.1.121:8080/OkHttpUtils/";
    public static final String URL_METHOD = SERVER + "method";
    public static final String SHARED_PREFERENCES_NAME = "baoliyota_spf";
    private static final String TAG = "MainAct";
    TextView tv;
    Button btn;
    OkHttpClient client = new OkHttpClient();
    int count = 0;
    private FrameLayout mPanelHolder;
    private Context mContext;
    View.OnClickListener onStartIRListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            startBookAppToShowDetail(ApiConstants.IR_PACKAGE_NAME, ApiConstants.IR_CLASS_NAME);
            Toast.makeText(mContext, "IR阅读器不存在", Toast.LENGTH_LONG).show();
        }
    };
    private IEpdScreenContract.IPresenter mPresenter;
    private Button setWallpaperBtn;
    /**
     * 默认壁纸的资源ID集合
     */
    private int[] mWallpaperIds = {R.drawable.ic_bs_wallpaper_01,
            R.drawable.ic_bs_wallpaper_02,
            R.drawable.ic_bs_wallpaper_03,
            R.drawable.ic_bs_wallpaper_04,
            R.drawable.ic_bs_wallpaper_05,
            R.drawable.ic_bs_wallpaper_06};
    private int mWillWallpaperIndex;//will Show Index
    private SharedPreferences mSharedPreferences;// 共享参数,判断是否第一次打开SystemUI,记录当前第几张壁纸
    private TextView mShowDetailTextView;
    IBaoliYotaImageLoader.LoaderListener<Drawable> loaderListener = new IBaoliYotaImageLoader.LoaderListener<Drawable>() {
        @Override
        public void onResourceReady(Drawable drawable) {
            mPanelHolder.setBackground(drawable);
            mPanelHolder.setVisibility(View.VISIBLE);

        }

        @Override
        public void onLoadFailed(Exception e, Drawable errorDrawable) {
            setEpdWallpaperFromLocal();
        }
    };
    private EpdScreenBean.DataBean mBookData;
    View.OnClickListener onStartBRListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startBookAppToShowDetail(ApiConstants.BR_PACKAGE_NAME, ApiConstants.BR_CLASS_NAME);
        }
    };

/*    @Override
    public void setEpdWallpaperFromNet(EpdScreenBean epdScreenBean) {
        List<EpdScreenBean.DataBean> datas = epdScreenBean.getData();
        int size = datas.size();
        if (count >= size) {
            count = 0;
        }
        if (count <= size - 1) {
            EpdScreenBean.DataBean data = datas.get(count);
            Toast.makeText(this, "count = " + count + ", picURL = " + data.getPicUrl(), Toast.LENGTH_SHORT).show();
            Glide.with(getApplicationContext())
                    .load(data.getPicUrl())
                    .into(simpleTarget);
            data.getResourceType();
            count++;
        }

    }*/

    /**
     * BaoliYota begin, add
     * what(reason) 背屏的锁屏壁纸逻辑
     * liuwenrong@coolpad.com, 1.0, 2017/3/21 */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = SystemUIApplication.getContext();
        setContentView(R.layout.activity_test_get);
        initView();



        mWillWallpaperIndex = getWallPaperIndexFromSP(); //
        mPresenter = new EpdScreenPresenter(this);
//        mPresenter.start();
//        setEpdWallpaperFromLocal();
    }

    void getOk() {
//        String url = String.format("http://suggest.taobao.com/sug?code=%s&q=%s&callback=%s", "utf-8", "衣服", "cb");
        String url = "http://suggest.taobao.com/sug";
        Log.d(TAG, "get: url = " + url);
        HttpParams params = new HttpParams();
        params.put("code", "utf-8");
        params.put("q", "衣服");
        params.put("callback", "cb");

        BaoliYotaHttpManager.getInstance().get(url)
                .tag(this)
                .params(params)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Toast.makeText(MainActivity.this, "success result = " + s, Toast.LENGTH_SHORT).show();
                    }
                });


    }

    public void getOriOk() {
        FormBody formBody = new FormBody.Builder()
                .add("code", "utf-8")
                .add("q", "衣服")
                .add("callback", "cb")
                .build();

        String url = String.format("http://suggest.taobao.com/sug?code=%s&q=%s&callback=%s", "utf-8", "衣服", "cb");
//        String url = "http://suggest.taobao.com/sug";
        Log.d(TAG, "get: url = " + url);
        final Request req = new Request.Builder()
                .url(url)
                .get()
//                .method ("get",formBody)
//                .post(formBody)
                .build();

        //异步
        client.newCall(req).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse: response = " + response.body().string());
            }
        });

        /*new Thread(new Runnable(){
            public void run(){
                Response response = null;
                try{
                    response = client.newCall(req).execute();

                    if(response.isSuccessful()){
                        String result = response.body().string();
                        Log.d(TAG, "run: result = " + result);

                    }else {
                        throw new IOException("unexpected code " + response);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();*/

    }

    void postOriOk() {
        FormBody formBody = new FormBody.Builder()
                .add("type", "" + 1)
                .add("id", "100")
                .add("money", "" + 100)
                .build();

//        String url = "http://app/operation/app/getUpdateList";
        String url = "http://test.tianpingpai.com/api/cash/apply";
        Log.d(TAG, "get: url = " + url);
        final Request req = new Request.Builder()
                .url(URL_METHOD)
                .post(formBody)
                .build();

        //异步
        client.newCall(req).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse: response = " + response.body().string());
            }
        });


    }

    void postOk() {


        HttpParams params = new HttpParams();
        params.put("code", "utf-8");
        params.put("q", "衣服");
        params.put("callback", "cb");

        BaoliYotaHttpManager.getInstance().post(URL_METHOD)
                .tag(this)//取消时候用
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        //UI线程
                        Toast.makeText(MainActivity.this, "success result = " + s, Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void initView() {
        Button btn_download = (Button) findViewById(R.id.btn_download);
        Button ori_get_btn = (Button) findViewById(R.id.ori_get_btn);
        FrameLayout activity_main = (FrameLayout) findViewById(R.id.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        setWallpaperBtn = (Button) findViewById(R.id.set_wallpaper_btn);
        mPanelHolder = (FrameLayout) findViewById(R.id.panel_holder);
        mShowDetailTextView = (TextView) findViewById(R.id.show_detail_text_view);

        DisplayManager dm = (DisplayManager) mContext.getSystemService(Context.DISPLAY_SERVICE);
        dm.getDisplays();
        Display display = dm.getDisplay(0);
        DisplayMetrics dm2 = new DisplayMetrics();
        display.getMetrics(dm2);

        float size = 21 * dm2.density;
//        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 21);
//        display.get


        setWallpaperBtn.setOnClickListener(this);
        mPanelHolder.setOnClickListener(this);
        mShowDetailTextView.setOnClickListener(this);
        btn_download.setOnClickListener(this);
        ori_get_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.panel_holder:
//                mPresenter.start();
                break;
            case R.id.show_detail_text_view:

//                startBookAppToShowDetail();

                break;

            case R.id.btn_download:
//                Intent intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
                break;
            case R.id.ori_get_btn:
//                getOriOk();
//                getOk();
//                postOriOk();
/*                postOk();*/
                mPresenter.start();
                break;
            case R.id.set_wallpaper_btn:

                Context c = this;
                String packageName = c.getPackageName();
                String p2 = mContext.getPackageName();

                WindowManager windowManager = getWindowManager();
                Display display = windowManager.getDefaultDisplay();
                int screenWidth = display.getWidth();
                int screenHeight = display.getHeight();

                // 方法2
                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);
//        dm.widthPixels * dm.density;
//        float height = dm.heightPixels * dm.density;
                float f1 = tv.getTextSize();
                float f2 = setWallpaperBtn.getTextSize();
                float f3 = setWallpaperBtn.getTextSize();

               /* print();
                WallpaperManager wpm = WallpaperManager.getInstance(getApplicationContext());
                Bitmap bm = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);*/
                /*try {
//                    wpm.setBitmap(bm);

//                    wpm.setBitmap(bm, null, true, WallpaperManager.FLAG_LOCK | WallpaperManager.FLAG_SYSTEM);
//                    wpm.setBitmap(bm, null, true, WallpaperManager.FLAG_LOCK);

                } catch (IOException e) {
                    e.printStackTrace();
                }*/


                break;
        }
    }

    void print() {


        Log.w("xx", "1<<0 = " + (1 << 0) + ", 1<<1 = " + (1 << 1) + ", 1 | 2 = " + (1 | 2) + ", 7 | 7 = " + (7 | 7));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaoliYotaHttpManager.getInstance().cancelTag(this);
    }

    /**
     * 下一个要显示的壁纸的索引,用于在壁纸集合中取将要显示的图片资源ID
     */
    public void setWillWallpaperIndex() {
        mWillWallpaperIndex++;
        if (mWillWallpaperIndex > mWallpaperIds.length - 1) {
            mWillWallpaperIndex = mWillWallpaperIndex % (mWallpaperIds.length);
        }
        setWallpaperIndexToSharedPreferences(mWillWallpaperIndex);
    }

    /**
     * 将要显示的壁纸的资源ID
     *
     * @return 壁纸资源ID
     */
    public int getWillShowWallpaperId() {
        if (mWillWallpaperIndex > mWallpaperIds.length - 1) {
            mWillWallpaperIndex = 0;
        }
        return mWallpaperIds[mWillWallpaperIndex];
    }

    /**
     * setWallpaper on BackScreen 设置背屏的锁屏壁纸 从本地默认的壁纸中
     * liuwenrong@coolpad.com 17/03/16
     */
    @Override
    public void setEpdWallpaperFromLocal() {
        mPanelHolder.setBackgroundResource(getWillShowWallpaperId());
        mShowDetailTextView.setVisibility(View.GONE);
        DisplayManager displayManager = (DisplayManager) mContext.getSystemService(Context.DISPLAY_SERVICE);
        Display[] displays = displayManager.getDisplays();
        android.widget.Toast.makeText(mContext.createDisplayContext(displays[0]), "背屏已上锁", Toast.LENGTH_SHORT).show();
        mPanelHolder.setVisibility(View.VISIBLE);
        setWillWallpaperIndex();

    }

    /**
     * @return SharedPreferences对象
     */
    public SharedPreferences getSharedPreferences() {
        if (mSharedPreferences == null) {
            mSharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        }
        return mSharedPreferences;
    }

    /**
     * 从共享参数中读取,一般第一次启动时调用
     *
     * @return 将要显示的壁纸的索引
     */
    public int getWallPaperIndexFromSP() {
        if (mSharedPreferences == null) {
            mSharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        }
        return mSharedPreferences.getInt("wallpaperIndex", 0);

    }

    /**
     * 设置壁纸索引存储到共享参数中
     *
     * @param index 将要显示的壁纸的索引
     */
    public void setWallpaperIndexToSharedPreferences(int index) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt("wallpaperIndex", index);
        editor.commit();
    }

    /**
     * Mvp中的回调方法,从设置中读取状态后去设置壁纸
     *
     * @param isOpen
     */
    @Override
    public void setEpdWallpaper(boolean isOpen) {

        if (isOpen) { //BLauncher中设置为显示壁纸
            mPresenter.start();//先从网络或者内存中获取背屏数据
        } else { //BLauncher中设置为显示today页,即隐藏壁纸和图书推荐
            mPanelHolder.setBackgroundResource(0);//设置为透明的壁纸
//            mPanelHolder.setVisibility(View.INVISIBLE);
//            mBackdrop.setVisibility(View.INVISIBLE);
//            mBackdrop.setBackgroundResource(0);
//            mBackdropBack.setVisibility(View.INVISIBLE);
//            mCustomLockScreenCover.setVisibility(View.INVISIBLE);

        }
    }

    /**
     * 设置图书推荐
     *
     * @param data 图书推荐一本书的的数据
     * @param type 类型:指定哪个阅读器,如BR,IReader掌阅
     */
    @Override
    public void setEpdBookRecommend(EpdScreenBean.DataBean data, int type) {

        mShowDetailTextView.setVisibility(View.VISIBLE);
        mBookData = data;
        switch (type) {
            case ApiConstants.RES_TYPE_BACK_READER:
                if (mBookData.getBookId() != 0 && mBookData.getDownUrl() != null && mBookData.getFileFormat() != 0) {
                    mShowDetailTextView.setOnClickListener(onStartBRListener);
                } else { //有空数据时,不显示跳转BR的按钮
                    mShowDetailTextView.setVisibility(View.GONE);
                }
                break;
            case ApiConstants.RES_TYPE_IREADER:
                mShowDetailTextView.setOnClickListener(onStartIRListener);
                break;
        }

        // 从内存或磁盘缓存中读取并显示图片
        mPresenter.loadImage(data.getPicUrl(), loaderListener);

    }

    /**
     * Mvp中的回调方法,从网络数据中设置壁纸
     *
     * @param data 单个壁纸的数据
     */
    @Override
    public void setEpdWallpaperFromNet(EpdScreenBean.DataBean data) {

        mShowDetailTextView.setVisibility(View.GONE);
        // 从内存或磁盘缓存中读取并显示图片
        mPresenter.loadImage(data.getPicUrl(), loaderListener);
    }

    @Override
    public void setPresenter(@NonNull IEpdScreenContract.IPresenter presenter) {
//        mPresenter = checkNotNull(presenter);
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    private void startBookAppToShowDetail(String packageName, String className) {

        if (mBookData == null) {
            return;
        }

        try {
            // 指定包名和类名
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName cn = new ComponentName(packageName, className);
            intent.setComponent(cn);
            // 书籍的数据封装成json传给BR
            String json = new GsonBuilder().create().toJson(mBookData);
            intent.putExtra(ApiConstants.INTENT_FLAG_TYPE, ApiConstants.FLAG_TYPE_EPD_SCREEN);
            intent.putExtra(ApiConstants.INTENT_FLAG_STR, json);
            intent.putExtra(ApiConstants.INTENT_BOOK_ID, mBookData.getBookId());
            // TODO: 2017/3/30  由于BR还没做完图书推荐的处理,
            mContext.startActivity(intent);
//            Toast.makeText(mContext, "当前阅读器不存在", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(mContext, "当前阅读器不存在", Toast.LENGTH_LONG).show();
        } finally {
        }

    }

/**
 * BaoliYota end
 */

}
