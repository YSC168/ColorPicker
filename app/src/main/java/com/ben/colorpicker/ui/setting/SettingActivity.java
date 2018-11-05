package com.ben.colorpicker.ui.setting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ben.colorpicker.BuildConfig;
import com.ben.colorpicker.R;
import com.ben.colorpicker.db.DataStore;
import com.ben.colorpicker.provider.ColorProvider;
import com.ben.colorpicker.ui.common.ToolbarActivity;
import com.ben.colorpicker.ui.license.LicenseActivity;
import com.gl.toast.Toastg;

public class SettingActivity extends ToolbarActivity implements View.OnClickListener {
    private static final String ALIPAY_PACKAGE_NAME = "com.eg.android.AlipayGphone";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    private void initView() {
        findViewById(R.id.clear_list).setOnClickListener(this);
        findViewById(R.id.compile).setOnClickListener(this);
        findViewById(R.id.git_hub).setOnClickListener(this);
        findViewById(R.id.licenses).setOnClickListener(this);
        findViewById(R.id.donation).setOnClickListener(this);
        TextView version = (TextView) findViewById(R.id.version);
        version.setText(String.valueOf(BuildConfig.VERSION_NAME));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clear_list:
                new AlertDialog.Builder(this).setTitle(android.R.string.dialog_alert_title)
                        .setMessage(R.string.clear_list_yes_or_no)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DataStore.delete().clear(SettingActivity.this, ColorProvider.uriColor());
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, null)
                        .show();
                break;
            case R.id.git_hub:
                github();
                break;
            case R.id.compile:
                Toastg.success(this, "QQ:2290797143", Toast.LENGTH_LONG).show();
                break;
            case R.id.licenses:
                licenseActivity();
                break;
            case R.id.donation:
                if (hasInstalledAlipayClient(SettingActivity.this)) {
                    OpenUtil.alipayDonate(this);
                } else {
                    Toastg.error(this, "你还没有支付宝！", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }

    public void github() {
        Uri uri = Uri.parse("https://github.com/ysc168/ColorPicker");
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(uri);
        startActivity(intent);
    }

    public void licenseActivity() {
        Intent intent = new Intent(this, LicenseActivity.class);
        startActivity(intent);
    }
    public static boolean hasInstalledAlipayClient(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(ALIPAY_PACKAGE_NAME, 0);
            return info != null;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
