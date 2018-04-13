package com.sina.weibo.sdk.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import com.sina.weibo.sdk.ApiUtils;
import com.sina.weibo.sdk.WeiboAppManager.WeiboInfo;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.constant.WBConstants.Base;

public class SecurityHelper {
    public static boolean validateAppSignatureForIntent(Context context, Intent intent) {
        boolean z = false;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            ResolveInfo resolveActivity = packageManager.resolveActivity(intent, z);
            if (resolveActivity != null) {
                try {
                    z = containSign(packageManager.getPackageInfo(resolveActivity.activityInfo.packageName, 64).signatures, WBConstants.WEIBO_SIGN);
                } catch (NameNotFoundException e) {
                    e.printStackTrace();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        return z;
    }

    public static boolean checkResponseAppLegal(Context context, WeiboInfo weiboInfo, Intent intent) {
        if ((weiboInfo != null && weiboInfo.getSupportApi() <= ApiUtils.BUILD_INT_VER_2_3) || weiboInfo == null) {
            return true;
        }
        String stringExtra = intent != null ? intent.getStringExtra(Base.APP_PKG) : null;
        if (stringExtra == null || intent.getStringExtra(WBConstants.TRAN) == null || !ApiUtils.validateWeiboSign(context, stringExtra)) {
            return false;
        }
        return true;
    }

    public static boolean containSign(Signature[] signatureArr, String str) {
        if (signatureArr == null || str == null) {
            return false;
        }
        for (Signature toByteArray : signatureArr) {
            if (str.equals(MD5.hexdigest(toByteArray.toByteArray()))) {
                return true;
            }
        }
        return false;
    }
}
