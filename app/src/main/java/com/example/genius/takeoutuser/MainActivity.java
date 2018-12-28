package com.example.genius.takeoutuser;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private Handler handler;
    private static final String TAG = "MainActivity";
    public static String userId = "5";
    private ShopFragment shopFragment;
    private OrderFragment orderFragment;
    private UserCenterFragment userCenterFragment;
    private OrderInfo orderInfo;
    private AllShop allShop;
    private UserInfo userInfo;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getDataOnNewThread();
                    jumpToFragment(shopFragment);
                    handler.sendEmptyMessage(1);
                    return true;
                case R.id.navigation_dashboard:
                    getDataOnNewThread();
                    jumpToFragment(orderFragment);
                    handler.sendEmptyMessage(2);
                    return true;
                case R.id.navigation_notifications:
                    getDataOnNewThread();
                    jumpToFragment(userCenterFragment);
                    handler.sendEmptyMessage(3);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shopFragment = new ShopFragment();
        orderFragment = new OrderFragment();
        userCenterFragment = new UserCenterFragment();
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                        if(allShop!=null){
                            shopFragment.updateData(allShop.getShopArr());
                        }
                        return true;
                    case 2:
                        if(orderInfo!=null){
                            orderFragment.updateData(orderInfo.getAllOrder());
                        }
                        return true;

                    case 3:
                        if(userInfo!=null){
                            userCenterFragment.updateData(userInfo);
                        }
                       return true;

                }
                return false;
            }
        });
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        jumpToFragment(shopFragment);
        getDataOnNewThread();

    }

    private void jumpToFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    private void getDataOnNewThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                getOrderInfo();
                getAllShop();
                getUserInfo();
            }
        }).start();
    }

    private void getOrderInfo() {
        String url = Services.getMyOrder;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .callTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build();
        FormBody formBody = new FormBody.Builder().add("userId", userId).build();
        Log.e(TAG, "getMyMenu: userId" + userId);
        Request request = new Request.Builder().url(url).post(formBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.e(TAG, "onResponse: " + result);
                orderInfo = new Gson().fromJson(result, OrderInfo.class);
                handler.sendEmptyMessage(2);
            }
        });
    }
    private void getAllShop() {
        String url = Services.getAllShop;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .callTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build();
        FormBody formBody = new FormBody.Builder().add("userId", userId).build();
        Log.e(TAG, "getMyMenu: userId" + userId);
        Request request = new Request.Builder().url(url).post(formBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.e(TAG, "onResponse: " + result);
                allShop = new Gson().fromJson(result, AllShop.class);
                handler.sendEmptyMessage(1);
            }
        });
    }

    private void getUserInfo() {
        String url = Services.getMyInfo;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .callTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build();
        FormBody formBody = new FormBody.Builder().add("userId", userId).build();
        Log.e(TAG, "getMyMenu: userId" + userId);
        Request request = new Request.Builder().url(url).post(formBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.e(TAG, "onResponse: " + result);
                userInfo = new Gson().fromJson(result, UserInfo.class);
                handler.sendEmptyMessage(3);
            }
        });
    }

}
