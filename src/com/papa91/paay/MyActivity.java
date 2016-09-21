package com.papa91.paay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

public class MyActivity extends Activity {

    PaayReceiver paayReceiver;
    String uid = "3913852899";
    String token = "5wnrKqSDPBCvHcWp";
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        paayReceiver = new PaayReceiver(){

            @Override
            public void onReceive(Context context, Intent intent) {
                //继续调用查询接口
                uid = intent.getExtras().getString("uid");
                token = intent.getExtras().getString("token");
                Log.d("TTTTTTTTTTTT","uid="+uid+";token="+token);
                lobbyPlayCheck();
//                startLobbyGame();
//                inquiry();
            }
        };
        registerReceiver(paayReceiver,new IntentFilter(PayCenter.ACTION_LOGIN_SUCCESS));
        setContentView(R.layout.main);
//        inquiry();
        startLobbyGame();
    }

    public void inquiry(){

        PayCenter.inquiryCheat(this, uid, token, "123", new PayListener() {

            @Override
            public void onResult(PayResponse payResponse) {
                if (payResponse != null) {
                    Log.d("TTTTTTTTTTTT","payResponse="+payResponse.getError());
                }
            }
        });
    }


    public void startLobbyGame(){
        PayCenter.startLobby(this, uid, token, "3913852899", "client", "20040032", new PayListener() {
            @Override
            public void onResult(PayResponse payResponse) {
                if (payResponse != null) {
                    Log.d("TTTTTTTTTTTT","payResponse="+payResponse.getError());
                }
            }
        });
    }

    public void lobbyPlayCheck(){
        PayCenter.lobbyPlayCheck(this, uid, token, "3913852899", "client", "20040032", new PayListener() {
            @Override
            public void onResult(PayResponse payResponse) {
                if (payResponse != null) {
                    Log.d("TTTTTTTTTTTT","payResponse="+payResponse.getError());
                }
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(paayReceiver);
    }
}
