package com.papa91.paay;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.google.gson.Gson;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lala on 15/11/13.
 */
public class PayCenter {
    private final static String root = "http://payv1.papa91.com";
    public final static String ACTION_LOGIN_SUCCESS = "com.join.android.app.mgsim.broadcast.action_login_success";
    public final static String ACTION_UPDATE_USER_PURCHASE_INFO = "com.join.android.app.mgsim.broadcast.action_update_user_purchase_info";
    private static final String TAG = "PayCenter";
    private static PayListener payListener;
    private static Gson mGson = new Gson();
    private static PayResponse payResponse;
    private static Context mContext;

    private static Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(payResponse!=null){
                if(payResponse.getError() == 701){//去登录
                    redirectLogin();
                }else
                    payListener.onResult(payResponse);
            }
        }
    };

    public static void redirectLogin(){
        Log.d(TAG,"method redirectLogin() called.");
        payResponse = new PayResponse();
        payResponse.setError(701);
        payListener.onResult(payResponse);
        Intent mIntent = new Intent();
        mIntent.putExtra("intentFrom",5);
        ComponentName comp = new ComponentName("com.join.android.app.mgsim", "com.join.mgps.activity.MyAccountCenterActivity_");
        mIntent.setComponent(comp);
        mIntent.setAction(Intent.ACTION_VIEW);
        mIntent.setAction("android.intent.action.VIEW");
        mContext.startActivity(mIntent);
    }

    public static void inquiryCheat(Context context,final String uid,final String token,final String gameId,final PayListener listener){
        payListener  = listener;
        mContext = context;
        if(uid==null||uid.equals("")||uid.equals("0")){
            redirectLogin();
            return;
        }
        new Thread(){
            @Override
            public void run() {
                super.run();
                List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>(0);
                params.add(new BasicNameValuePair("uid",uid));
                params.add(new BasicNameValuePair("token",token));
                params.add(new BasicNameValuePair("game_id",gameId));

                NetUtils.postRequest(root+"/pay/emulator/cheat_permission_info", params, new NetWorkListener() {
                    @Override
                    public void onSuccess(String str) {
                        payResponse = mGson.fromJson(str, PayResponse.class);
                        mHandler.sendEmptyMessage(0);
                    }

                    @Override
                    public void onFailed(String error) {
                        PayResponse response = new PayResponse();
                        PayResponseData data = new PayResponseData();
                        data.setBuy_error_message(error);
                        response.setData(data);
                        response.setError(-1);
                        payResponse = response;
                        mHandler.sendEmptyMessage(1);
                    }
                });
            }
        }.start();
    };

    public static void inquirySP(Context context,final String uid,final String token,final String gameId,final PayListener listener){
        payListener  = listener;
        mContext = context;
        if(uid==null||uid.equals("")||uid.equals("0")){
            redirectLogin();
            return;
        }
        new Thread(){
            @Override
            public void run() {
                super.run();
                List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>(0);
                params.add(new BasicNameValuePair("uid",uid));
                params.add(new BasicNameValuePair("token",token));
                params.add(new BasicNameValuePair("game_id",gameId));

                NetUtils.postRequest(root+"/pay/emulator/sp_permission_info", params, new NetWorkListener() {
                    @Override
                    public void onSuccess(String str) {
                        payResponse = mGson.fromJson(str, PayResponse.class);
                        mHandler.sendEmptyMessage(0);
                    }

                    @Override
                    public void onFailed(String error) {
                        PayResponse response = new PayResponse();
                        PayResponseData data = new PayResponseData();
                        data.setBuy_error_message(error);
                        response.setData(data);
                        response.setError(-1);
                        payResponse = response;
                        mHandler.sendEmptyMessage(1);
                    }
                });
            }
        }.start();
    };

    public static void inquiryCoinInfo(Context context,final String uid,final String token,final String gameId,final PayListener listener){
        payListener  = listener;
        mContext = context;
        if(uid==null||uid.equals("")||uid.equals("0")){
            redirectLogin();
            return;
        }
        new Thread(){
            @Override
            public void run() {
                super.run();
                List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>(0);
                params.add(new BasicNameValuePair("uid",uid));
                params.add(new BasicNameValuePair("token",token));
                params.add(new BasicNameValuePair("game_id",gameId));

                NetUtils.postRequest(root+"/pay/emulator/coin_permission_info", params, new NetWorkListener() {
                    @Override
                    public void onSuccess(String str) {
                        payResponse = mGson.fromJson(str, PayResponse.class);
                        mHandler.sendEmptyMessage(0);
                    }

                    @Override
                    public void onFailed(String error) {
                        PayResponse response = new PayResponse();
                        PayResponseData data = new PayResponseData();
                        data.setBuy_error_message(error);
                        response.setData(data);
                        response.setError(-1);
                        payResponse = response;
                        mHandler.sendEmptyMessage(1);
                    }
                });
            }
        }.start();
    };

    public static void buyCheat(Context context,final String uid,final String token,final String gameId,final PayListener listener){
        payListener  = listener;
        mContext = context;
        if(uid==null||uid.equals("")||uid.equals("0")){
            redirectLogin();
            return;
        }
        new Thread(){
            @Override
            public void run() {
                super.run();
                List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>(0);
                params.add(new BasicNameValuePair("uid",uid));
                params.add(new BasicNameValuePair("token",token));
                params.add(new BasicNameValuePair("game_id",gameId));

                NetUtils.postRequest(root+"/pay/emulator/open_cheat_permission", params, new NetWorkListener() {
                    @Override
                    public void onSuccess(String str) {
                        payResponse = mGson.fromJson(str, PayResponse.class);
                        mHandler.sendEmptyMessage(0);
                        mContext.sendBroadcast(new Intent(ACTION_UPDATE_USER_PURCHASE_INFO));
                    }

                    @Override
                    public void onFailed(String error) {
                        PayResponse response = new PayResponse();
                        PayResponseData data = new PayResponseData();
                        data.setBuy_error_message(error);
                        response.setData(data);
                        response.setError(-1);
                        payResponse = response;
                        mHandler.sendEmptyMessage(1);
                    }
                });
            }
        }.start();
    };

    public static void buySP(Context context,final String uid,final String token,final String gameId,final PayListener listener){
        payListener  = listener;
        mContext = context;
        if(uid==null||uid.equals("")||uid.equals("0")){
            redirectLogin();
            return;
        }
        new Thread(){
            @Override
            public void run() {
                super.run();
                List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>(0);
                params.add(new BasicNameValuePair("uid",uid));
                params.add(new BasicNameValuePair("token",token));
                params.add(new BasicNameValuePair("game_id",gameId));

                NetUtils.postRequest(root+"/pay/emulator/open_sp_permission", params, new NetWorkListener() {
                    @Override
                    public void onSuccess(String str) {
                        payResponse = mGson.fromJson(str, PayResponse.class);
                        mHandler.sendEmptyMessage(0);
                        mContext.sendBroadcast(new Intent(ACTION_UPDATE_USER_PURCHASE_INFO));
                    }

                    @Override
                    public void onFailed(String error) {
                        PayResponse response = new PayResponse();
                        PayResponseData data = new PayResponseData();
                        data.setBuy_error_message(error);
                        response.setData(data);
                        response.setError(-1);
                        payResponse = response;
                        mHandler.sendEmptyMessage(1);
                    }
                });
            }
        }.start();
    };

    public static void buyCoin(Context context,final String uid,final String token,final String gameId,final PayListener listener){
        payListener  = listener;
        mContext = context;
        if(uid==null||uid.equals("")||uid.equals("0")){
            redirectLogin();
            return;
        }
        new Thread(){
            @Override
            public void run() {
                super.run();
                List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>(0);
                params.add(new BasicNameValuePair("uid",uid));
                params.add(new BasicNameValuePair("token",token));
                params.add(new BasicNameValuePair("game_id",gameId));

                NetUtils.postRequest(root+"/pay/emulator/buy_game_coin", params, new NetWorkListener() {
                    @Override
                    public void onSuccess(String str) {
                        payResponse = mGson.fromJson(str, PayResponse.class);
                        mHandler.sendEmptyMessage(0);
                        mContext.sendBroadcast(new Intent(ACTION_UPDATE_USER_PURCHASE_INFO));
                    }

                    @Override
                    public void onFailed(String error) {
                        PayResponse response = new PayResponse();
                        PayResponseData data = new PayResponseData();
                        data.setBuy_error_message(error);
                        response.setData(data);
                        response.setError(-1);
                        payResponse = response;
                        mHandler.sendEmptyMessage(1);
                    }
                });
            }
        }.start();
    };

    /**
     * 开始游戏,坐下扣啪币
     * @param context
     * @param uid
     * @param token
     * @param gameId
     * @param role master 主机, client 加入， spectator 观战者
     */
    public static void startLobby(Context context, final String uid, final String token, final String gameId, final String role,final String roomId,final PayListener listener){
        mContext = context;
        payListener  = listener;
        if(uid==null||uid.equals("")||uid.equals("0")){
            redirectLogin();
            return;
        }
        new Thread(){
            @Override
            public void run() {
                super.run();
                Map params = new HashMap(0);
                params.put("uid",uid);
                params.put("game_id",gameId);
                params.put("token",token);
                params.put("role",role);
                params.put("room_id",roomId);
                String rs = NetUtils.getRequest("http://anv3btapi.papa91.com/netbattle/start_lobby_game", params);
//                String rs = NetUtils.getRequest("http://192.168.78.5:10001/netbattle/start_lobby_game", params);
                if(rs==null){
                    PayResponse response = new PayResponse();
                    PayResponseData data = new PayResponseData();
                    data.setBuy_error_message("网络异常");
                    response.setData(data);
                    response.setError(-1);
                    payResponse = response;
                    mHandler.sendEmptyMessage(1);
                }else{
                    payResponse = mGson.fromJson(rs, PayResponse.class);
                    mHandler.sendEmptyMessage(0);
                }
            }
        }.start();
    }


    public static void lobbyPlayCheck(Context context, final String uid, final String token, final String gameId, final String role,final String roomId,final PayListener listener){
        mContext = context;
        payListener  = listener;
        if(uid==null||uid.equals("")||uid.equals("0")){
            redirectLogin();
            return;
        }
        new Thread(){
            @Override
            public void run() {
                super.run();
                Map params = new HashMap(0);
                params.put("uid",uid);
                params.put("game_id",gameId);
                params.put("token",token);
                params.put("role",role);
                params.put("room_id",roomId);
                String rs = NetUtils.getRequest("http://anv3btapi.papa91.com/netbattle/lobby_play_check", params);
//                String rs = NetUtils.getRequest("http://192.168.78.5:10001/netbattle/start_lobby_game", params);
                if(rs==null){
                    PayResponse response = new PayResponse();
                    PayResponseData data = new PayResponseData();
                    data.setBuy_error_message("网络异常");
                    response.setData(data);
                    response.setError(-1);
                    payResponse = response;
                    mHandler.sendEmptyMessage(1);
                }else{
                    payResponse = mGson.fromJson(rs, PayResponse.class);
                    mHandler.sendEmptyMessage(0);
                }
            }
        }.start();
    }



}
