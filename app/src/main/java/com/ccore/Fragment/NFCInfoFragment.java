package com.ccore.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ccore.Activity.R;
import com.ccore.ConstDef.MsgConstDef;
import com.ccore.ConstDef.NetConstDef;
import com.ccore.Until.OkHttpUntil.CallBackUtil;
import com.ccore.Until.OkHttpUntil.OkhttpUtil;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

public class NFCInfoFragment extends Fragment {

    private TextView        mshow;
    private ProgressDialog progressDialog;
    private byte[]          msn;
    private static final String TAG = NFCInfoFragment.class.getSimpleName();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info,container,false);
        mshow = (TextView)view.findViewById(R.id.info_show);
        return view;
    }

    /**
     * @Function: 初始化加载资源
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Message msg = handler.obtainMessage();
        msg.what = MsgConstDef.MSG_GETRANDOM;
        msg.sendToTarget();
    }

    /**
     * @param sn
     * @return
     */
    public boolean getrandom(byte[] sn){
        OkhttpUtil.okHttpGet("http:/guolin.tech/api/china", 1, new CallBackUtil.CallBackString() {
                    @Override
                    public void onFailure(Call call, Exception e) {
                        closeProgressDialog();
                        Message msg = handler.obtainMessage();
                        msg.what = MsgConstDef.MSG_GETRAND_FAIL;
                        msg.sendToTarget();
                    }

                    @Override
                    public void onResponse(final String response) {
                        closeProgressDialog();
                        new Thread(){
                            @Override
                            public void run() {
                                ParseResponse(MsgConstDef.MSG_GETRANDOM,response);
                                super.run();
                            }
                        }.start();
                        mshow.setText(response);
                        Message msg = handler.obtainMessage();
                        msg.what = MsgConstDef.MSG_GETRAND_OK;
                        msg.sendToTarget();
                    }
                }
        );
        return false;
    }

    /**
     * @Function:
     * @param cert
     * @return
     */
    public boolean getcert(byte[] cert){
        return false;
    }

    /**
     * @Function: 销毁当前Activity的资源
     *
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks(goRunnable);
    }

    /**
     * @Function: 显示加载资源对话框
    **/
    private void showProgressDialog(){
        if(progressDialog == null){
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("正在请求数据...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    private void closeProgressDialog(){
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
    }

    private Runnable goRunnable = new Runnable() {
        @Override
        public void run() {
        }
    };

    /**
     * @Function: 处理服务器传回的数据
    **/
    private void ParseResponse(int type,String resp){
        Message msg = handler.obtainMessage();
        msg.what = MsgConstDef.MSG_DEF;
        switch(type){
            default:
                msg.what = MsgConstDef.MSG_HTTP_FAIL;
                break;
        }
        msg.sendToTarget();
    }

    /**
     * @Function:消息接收
    **/
    Handler handler = new Handler(){
        /**
         * @param msg
         */
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case MsgConstDef.MSG_GETRANDOM:
                    showProgressDialog();
                    getrandom(msn);
                    break;
                case MsgConstDef.MSG_GETRAND_OK:
                    Toast.makeText(getContext(),"获取随机数成功",Toast.LENGTH_SHORT).show();
                    break;
                case MsgConstDef.MSG_GETRAND_FAIL:
                    Toast.makeText(getContext(),"获取随机数失败",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }
        }
    };


}
