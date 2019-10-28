package com.colin.anbet.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.colin.anbet.MyApp;
import com.colin.anbet.R;


public class UIHelper {
    private static final int DEFAULT_TIME = 2000;
    private static long lastTime;
    private static String showingText;
    private static Toast toast;

    public static void cancelToast() {
        Toast localToast = toast;
        if (localToast == null)
            return;
        localToast.cancel();
    }

    public static void copySuccess(int paramInt) {
        errorToast(MyApp.getContext().getResources().getString(paramInt), R.drawable.ic_toast_success);
    }

    public static void copySuccess(String paramString) {
        errorToast(paramString, R.drawable.ic_toast_success);
    }

    public static void errorToast(int paramInt) {
        errorToast(MyApp.getContext().getResources().getString(paramInt), R.drawable.ic_alert_error);
    }

    private static void errorToast(String paramString, int paramInt) {
        if ((TextUtils.isEmpty(paramString)) || (paramString.equals(showingText)))
            return;
        toast = Toast.makeText(MyApp.getContext(), paramString, Toast.LENGTH_SHORT);
        toast.setGravity(17, 0, 0);
        View localView = View.inflate(MyApp.getContext(), R.layout.toast_custom_view, null);
        LinearLayout localLinearLayout = localView.findViewById(R.id.llToastView);
        if (paramString.length() <= 6) {
            RelativeLayout.LayoutParams localLayoutParams = (RelativeLayout.LayoutParams) localLinearLayout.getLayoutParams();
            localLayoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT;
            localLayoutParams.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
            localLinearLayout.setLayoutParams(localLayoutParams);
        }
        ((TextView) localView.findViewById(R.id.tvToastText)).setText(paramString);
        ((ImageView) localView.findViewById(R.id.ivToastIcon)).setImageResource(paramInt);
        toast.setView(localView);
        toast.show();
        showingText = paramString;
        newCountDownTimer(2000);
    }
    public static void errorToastString(String paramString) {
        errorToast(paramString,  R.drawable.ic_alert_error);
    }

    public static void goBrowser(Context paramContext, String paramString) {
        try {
            paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    public static void goGameActivity(Context paramContext, List<GameList> paramList, String paramString1, String paramString2) {
//        Intent localIntent = new Intent(paramContext, GameActivity.class);
//        localIntent.putExtra("GAME", (Serializable) paramList);
//        localIntent.putExtra("TYPE", paramString1);
//        localIntent.putExtra("gamingType", paramString2);
//        paramContext.startActivity(localIntent);
//    }
//
//    public static void goHuoDongActivity(Context paramContext, String paramString) {
//        Intent localIntent = new Intent(paramContext, HuoDongActivity.class);
//        localIntent.putExtra("userName", paramString);
//        paramContext.startActivity(localIntent);
//    }
//
//
//    public static void goQiPaiActivity(Context paramContext, List<GameList> paramList, int paramInt) {
//        Intent localIntent = new Intent(paramContext, QiPaiActivity.class);
//        localIntent.putExtra("qipaiGame", (Serializable) paramList);
//        localIntent.putExtra("type", paramInt);
//        paramContext.startActivity(localIntent);
//    }
//
//
//    public static void goServiceActivity(Context paramContext, String paramString) {
//        Intent localIntent = new Intent(paramContext, OnLineServiceActivity.class);
//        localIntent.putExtra("serviceUrl", paramString);
//        paramContext.startActivity(localIntent);
//    }
//
//
//    public static void goWebviewGameActivity(Context paramContext, String paramString) {
//        Intent localIntent = new Intent(paramContext, WebviewGameActivity.class);
//        localIntent.putExtra("gameUrl", paramString);
//        paramContext.startActivity(localIntent);
//    }

    public static void goWifiSettings(Activity paramActivity) {
        paramActivity.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
    }
    static String a(String s)
    {
        showingText = s;
        return s;
    }

    private static void newCountDownTimer(int paramInt) {
        long lcount = paramInt;
        new CountDownTimer(lcount, lcount) {
            public void onFinish() {
                UIHelper.a(null);
            }

            public void onTick(long paramLong) {
            }
        }.start();
    }

    public static void okToast(int paramInt) {
        okToast(MyApp.getContext().getResources().getString(paramInt));
    }

    public static void okToast(String paramString) {
        if ((TextUtils.isEmpty(paramString)) || (paramString.equals(showingText)))
            return;
        toast = Toast.makeText(MyApp.getContext(), paramString, Toast.LENGTH_SHORT);
        toast.setGravity(17, 0, 0);
        View localView = View.inflate(MyApp.getContext(), R.layout.toast_custom_view, null);
        LinearLayout localLinearLayout = localView.findViewById(R.id.llToastView);
        RelativeLayout.LayoutParams localLayoutParams;
        if (paramString.length() <= 6) {
            localLayoutParams = (RelativeLayout.LayoutParams) localLinearLayout.getLayoutParams();
            localLayoutParams.width = -1;
            localLayoutParams.height = -1;
        } else {
            localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
        }
        localLinearLayout.setLayoutParams(localLayoutParams);
        ((TextView) localView.findViewById(R.id.tvToastText)).setText(paramString);
        ((ImageView) localView.findViewById(R.id.ivToastIcon)).setImageResource(R.drawable.ic_success);
        toast.setView(localView);
        toast.show();
        showingText = paramString;
        newCountDownTimer(2000);
    }

    public static void showToast(Context paramContext, int paramInt1, int paramInt2) {
        Toast.makeText(paramContext, paramInt2, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Context paramContext, String paramString) {
        Toast.makeText(paramContext, paramString, Toast.LENGTH_SHORT).show();
    }

    private static void showToast(Context paramContext, String paramString, int paramInt) {
        if ((paramString == null) || (paramString.equals(showingText)))
            return;
        toast = Toast.makeText(paramContext, paramString, Toast.LENGTH_SHORT);
        toast.show();
        showingText = paramString;
        newCountDownTimer(paramInt);
//        if (paramInt == 0) ;
//        for (paramInt = 2000; ; paramInt = 3500) {
//            newCountDownTimer(paramInt);
//            return;
//            if (paramInt != 1)
//                return;
//        }
    }

    public static void showToastLong(Context paramContext, int paramInt) {
        showToast(paramContext, paramContext.getResources().getString(paramInt), 1);
    }

    public static void showToastLong(Context paramContext, String paramString) {
        showToast(paramContext, paramString, 1);
    }

    public static void showToastShort(Context paramContext, int paramInt) {
        showToast(paramContext, paramContext.getResources().getString(paramInt), 0);
    }

    public static void showToastShort(Context paramContext, String paramString) {
        showToast(paramContext, paramString, 0);
    }

    public static void showWebview(Context paramContext, String paramString) {
        try {
            paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void systemAlert(String paramString) {
        errorToast(paramString, R.drawable.ic_fix_alert);
    }
}
