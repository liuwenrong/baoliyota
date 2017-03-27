package com.android.systemui;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.baoliyota.contract.IEpdScreenContract;
import com.android.baoliyota.model.bean.EpdScreenBean;
import com.android.baoliyota.network.BaoliYotaHttpManager;
import com.android.baoliyota.network.callback.StringCallback;
import com.android.baoliyota.network.model.HttpParams;
import com.android.baoliyota.presenter.EpdScreenPresenter;
import com.android.systemuib.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IEpdScreenContract.IView{

    private static final String TAG = "MainAct";
    private ImageView mPanelView;
    private IEpdScreenContract.IPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_get);
        initView();

        mPresenter = new EpdScreenPresenter(this);
        String json = "{ \"code\": 1, \"data\": [ { \"bookId\": 1, \"picUrl\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490864224&di=e16630f6fd5f0587f9a1bff2fd1392c2&imgtype=jpg&er=1&src=http%3A%2F%2Fpic21.nipic.com%2F20120511%2F8133282_145011003398_2.jpg\", \"reourceId\": 1, \"resourceType\": 1, \"resourceUrl\": \"www.baidu.com\" }, { \"bookId\": 1, \"picUrl\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490269455272&di=7ef929d847836d298dad597acda5994a&imgtype=0&src=http%3A%2F%2Fimg3.redocn.com%2Ftupian%2F20140704%2Fheibaiyutu_2694809.jpg\", \"reourceId\": 1, \"resourceType\": 1, \"resourceUrl\": \"www\" } ], \"msg\": \"success\" } ";
        Log.i(TAG, "onCreate: json = " + json);

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

    public static final String SERVER = "http://server.jeasonlzy.com/OkHttpUtils/";
    //    public static final String SERVER = "http://192.168.1.121:8080/OkHttpUtils/";
    public static final String URL_METHOD = SERVER + "method";

    OkHttpClient client = new OkHttpClient();

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
        LinearLayout activity_main = (LinearLayout) findViewById(R.id.activity_main);
        Button setWallpaperBtn = (Button) findViewById(R.id.set_wallpaper_btn);
        mPanelView = (ImageView) findViewById(R.id.panel_view);
        setWallpaperBtn.setOnClickListener(this);

        new EpdScreenBean();
        btn_download.setOnClickListener(this);
        ori_get_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_download:
//                Intent intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
                break;
            case R.id.ori_get_btn:
//                getOriOk();
//                getOk();
//                postOriOk();
//                postOk();
                mPresenter.start();
                break;
            case R.id.set_wallpaper_btn:

                print();
                WallpaperManager wpm = WallpaperManager.getInstance(getApplicationContext());
                Bitmap bm = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
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

    void print(){


        Log.w("xx", "1<<0 = " + (1<<0) + ", 1<<1 = " + (1<<1) + ", 1 | 2 = " + (1 | 2) + ", 7 | 7 = " + (7 | 7));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaoliYotaHttpManager.getInstance().cancelTag(this);
    }

    @Override
    public void setPresenter(@NonNull IEpdScreenContract.IPresenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    int count = 0;
    @Override
    public void setEpdWallpaperFromNet(EpdScreenBean epdScreenBean) {
        List<EpdScreenBean.DataBean> datas = epdScreenBean.getData();
        int size = datas.size();
        if (count <= size - 1) {
            EpdScreenBean.DataBean data = datas.get(count);
            Glide.with(getApplicationContext())
                    .load(data.getPicUrl())
                    .into(simpleTarget);
            data.getResourceType();
            count++;
        }
        if (count >= size){
            count = 0;
        }

    }

    SimpleTarget<GlideDrawable> simpleTarget = new SimpleTarget<GlideDrawable>() {

            @Override
            public void onResourceReady(GlideDrawable drawable, GlideAnimation<? super GlideDrawable> glideAnimation) {
                mPanelView.setImageDrawable(drawable);
            }

        @Override
        public void onLoadFailed(Exception e, Drawable errorDrawable) {
            super.onLoadFailed(e, errorDrawable);

        }
    };
}
