package com.ben.colorpicker.ui.setting;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public final class OpenUtil {
    public static void alipayDonate(Context context) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        String payUrl = "HTTPS://QR.ALIPAY.COM/FKX02468JOE5DKCFGPL919";
        intent.setData(Uri.parse("alipayqr://platformapi/startapp?saId=10000007&clientVersion=3.7.0.0718&qrcode=" + payUrl));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        } else {
            intent.setData(Uri.parse(payUrl));
            context.startActivity(intent);
        }
    }
}

