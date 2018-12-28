package com.example.genius.takeoutuser;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class ShopMenuActivity extends AppCompatActivity {
    private static final String TAG = "ShopMenuActivity";
    @BindView(R.id.shop_menu_recycler_view)
    RecyclerView shopMenuRecyclerView;
    @BindView(R.id.submit_order)
    Button submitOrder;
    String id;
    private List<OneShopMenu.MenuBean> list;
    ShopMenuAdapter adapter;
    Handler handler;
    private OneShopMenu shopMenu;
    private Map<Integer,Integer> result;
    private String addMenuItemSql="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_menu);
        ButterKnife.bind(this);
        id = getIntent().getStringExtra("id");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        shopMenuRecyclerView.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        adapter = new ShopMenuAdapter(list,this);
        shopMenuRecyclerView.setAdapter(adapter);
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(msg.what==2){
                    Log.e(TAG, "handleMessage: "+msg.obj );
                    list.addAll(shopMenu.getMenu());
                    adapter.notifyDataSetChanged();
                    return true;
                }
                else if(msg.what == 3){
                    String  temp = (String)msg.obj;
                    Log.e(TAG, "handleMessage: "+temp);
                    AlertDialog.Builder builder = new AlertDialog.Builder(ShopMenuActivity.this).setTitle("下单成功")
                            .setMessage("正在等待骑手接单,请耐心等待")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
                    builder.show();
                    if(!temp.equals("Failed")&&!temp.equals("Failed2")){
                        String orderId = temp;
                        for(int i=0;i<list.size();i++){
                            if(result.containsKey(i)&&result.get(i)!=0){
                                String sql = "insert into 订单物品(O_num,OI_price,OI_count,M_num) values('"
                                        +orderId+"','"
                                        +Float.parseFloat(list.get(i).getPrice())*Float.parseFloat(list.get(i).getDiscount())+"','"
                                        +result.get(i)+"','"
                                        +list.get(i).getNum()+"');";
                                addMenuItemSql+=sql;
                                Log.e(TAG, "handleMessage: "+sql );
                            }
                        }
                        update(addMenuItemSql,ShopMenuActivity.this,4);
                    }
                    return true;
                }
                else if(msg.what == 4){
                    Log.e(TAG, "handleMessage: "+(String)msg.obj );
                    return true;
                }
                return false;
            }
        });
        runOnNewThread();
    }
    public void runOnNewThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                getMenuData();
            }
        }).start();

    }
    @OnClick(R.id.submit_order)
    public void onViewClicked() {
        result = adapter.getData();
        showPopWindow();
    }
    private void showPopWindow(){
        final List<SubmitMenuItem> list2 = new ArrayList<>();
        float sum = 0;
        for(int i=0;i<list.size();i++){
            if(result.containsKey(i)&&result.get(i)!=0){
                String temp = list.get(i).getPrice()+"X"+list.get(i).getDiscount()+"X"+result.get(i);
                SubmitMenuItem item = new SubmitMenuItem(list.get(i).getName(),temp);
                list2.add(item);
                sum += (Float.parseFloat(list.get(i).getPrice())*Float.parseFloat(list.get(i).getDiscount())*result.get(i));
            }
        }
        SubmitMenuItem item = new SubmitMenuItem("配送费","2");
        SubmitMenuItem item2 = new SubmitMenuItem("餐盒费",String.valueOf(result.size()));
        sum+=2;
        sum+=result.size();
        list2.add(item);
        list2.add(item2);
        View view = LayoutInflater.from(this).inflate(R.layout.pop_window_layout,null);
        RecyclerView recyclerView = view.findViewById(R.id.pop_windows_recycler_view);
        PopWindowRecyclerViewAdapter popWindowRecyclerViewAdapter = new PopWindowRecyclerViewAdapter(list2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(popWindowRecyclerViewAdapter);
        Button button = view.findViewById(R.id.submit_order);
        TextView textView = view.findViewById(R.id.pop_window_all_sum);
        DecimalFormat decimalFormat = new DecimalFormat(".00");
        textView.setText(decimalFormat.format(sum)+"元");
        final String O_sum = decimalFormat.format(sum);
        final String Delivery_fee = "2";
        final String Meal_box_fee =String.valueOf(result.size());
        final String C_num = id;
        final String U_num = MainActivity.userId;
        final PopupWindow popupWindow = new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.pop_window_bg));
        popupWindow.showAtLocation(submitOrder,Gravity.BOTTOM,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String O_sum,String Delivery_fee,String Meal_box_fee,String C_num,String U_num
                addOrder(O_sum,Delivery_fee,Meal_box_fee,C_num,U_num);
                popupWindow.dismiss();
            }
        });
    }
    private void getMenuData(){
        String url = Services.getShopMenu;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .callTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build();
        FormBody formBody = new FormBody.Builder().add("id", id).build();
        Log.e(TAG, "getMyMenu: id" + id);
        Request request = new Request.Builder().url(url).post(formBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.e(TAG, "onResponse: " + result);
                shopMenu = new Gson().fromJson(result, OneShopMenu.class);
                handler.sendEmptyMessage(2);
            }
        });
    }
    void  addOrder(String O_sum,String Delivery_fee,String Meal_box_fee,String C_num,String U_num){
        String url = Services.addOrder;
        OkHttpClient client = new OkHttpClient();
        MultipartBody.Builder  requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        requestBody.addFormDataPart("O_sum",O_sum)
        .addFormDataPart("Delivery_fee",Delivery_fee)
        .addFormDataPart("Meal_box_fee",Meal_box_fee)
        .addFormDataPart("C_num",C_num)
        .addFormDataPart("U_num",U_num);
        final Request request = new Request.Builder().url(url).post(requestBody.build()).build();
        client.newBuilder().readTimeout(10,TimeUnit.MINUTES).connectTimeout(10,TimeUnit.MINUTES).writeTimeout(10,TimeUnit.MINUTES)
                .build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: "+e.getMessage() );
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Log.e(TAG, "onResponse: "+response.body().string() );
                Message message = new Message();
                message.obj =  response.body().string();
                message.what = 3;
                handler.sendMessage(message);
            }
        });
    }
    void  update(String sql, final Context context, final int what){
        String url = Services.updateInfo;
        OkHttpClient client = new OkHttpClient();
        MultipartBody.Builder  requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        requestBody.addFormDataPart("sql",sql);
        final Request request = new Request.Builder().url(url).post(requestBody.build()).tag(context).build();
        client.newBuilder().readTimeout(10,TimeUnit.MINUTES).connectTimeout(10,TimeUnit.MINUTES).writeTimeout(10,TimeUnit.MINUTES)
                .build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: "+e.getMessage() );
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Log.e(TAG, "onResponse: "+response.body().string() );
                Message message = new Message();
                message.obj =  response.body().string();
                message.what = what;
                handler.sendMessage(message);
            }
        });
    }
}
