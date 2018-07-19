package com.ccore.Activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ccore.Fragment.NFCInfoFragment;
import com.ccore.nfcjni.NFCJni;

public class MainActivity extends AppCompatActivity {
    public    NFCJni        mnfcjni        = null;
    protected NfcAdapter    mNfcAdapter    = null;
    protected PendingIntent mPendingIntent = null;
    protected Tag           mTag           = null;
    protected int           NfcType        = 0;//标签类型
    protected String        NfcAction      = "";//标签类型
    private IntentFilter[]  intentFilters;


    @Override
    protected void onPause() {
        super.onPause();
        /*if(mNfcAdapter!=null){
            mNfcAdapter.disableForegroundDispatch(this);//关闭前台发布系统
        }*/
        mnfcjni.disableDispatch();
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*if (mNfcAdapter!=null){
            //            mNfcAdapter.enableForegroundDispatch(this,mPendingIntent,null,null);
            mNfcAdapter.enableForegroundDispatch(this,mPendingIntent,null,null);//打开前台发布系统，使页面优于其它nfc处理.当检测到一个Tag标签就会执行mPendingItent
        }*/
        mnfcjni.enableDispatch();
    }

    public NFCJni getNfc(){
        return mnfcjni;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mnfcjni.recvNewTag(intent);
        if(mnfcjni.recvIsoDep == true){
            replaceFragment(new NFCInfoFragment());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mnfcjni = new NFCJni(this);
        mNfcAdapter = mnfcjni.getNfcAdapter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.action_info){
            replaceFragment(new NFCInfoFragment());
        }

        return super.onOptionsItemSelected(item);
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.info_nfc,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
