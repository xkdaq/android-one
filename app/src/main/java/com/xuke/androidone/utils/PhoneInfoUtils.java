package com.xuke.androidone.utils;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.net.NetworkInterface;
import java.net.SocketException;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * 读取手机信息
 * 必须在加入各种权限
 */

public class PhoneInfoUtils {

    private static final String TAG = "PhoneInfoUtils";

    /**
     * TelephonyManager提供设备上获取通讯服务信息的入口。 应用程序可以使用这个类方法确定的电信服务商和国家 以及某些类型的用户访问信息。
     * 应用程序也可以注册一个监听器到电话收状态的变化。不需要直接实例化这个类
     * 使用Context.getSystemService(Context.TELEPHONY_SERVICE)来获取这个类的实例。
     */
    private TelephonyManager telephonyManager;

    /**
     * 国际移动用户识别码
     */
    private String IMSI;

    private Context context;

    public PhoneInfoUtils(Context context) {
        this.context = context;
        telephonyManager = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
    }


    /**
     * 获取当前设置的电话号码
     */
    public String getNativePhoneNumber() {

        String NativePhoneNumber = null;
        NativePhoneNumber = telephonyManager.getLine1Number();
        return NativePhoneNumber;
    }

    private String ProvidersName = null;

    /**
     * 获取手机服务商信息
     */
    public String getProvidersName() {
        // 返回唯一的用户ID;就是这张卡的编号神马的
        IMSI = telephonyManager.getSubscriberId();
        if (IMSI != null) {
            // IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
            Log.e(TAG, "IMSI==" + IMSI);
            if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
                ProvidersName = "中国移动";
            } else if (IMSI.startsWith("46001")) {
                ProvidersName = "中国联通";
            } else if (IMSI.startsWith("46003")) {
                ProvidersName = "中国电信";
            }
        } else {
            ProvidersName = "无sim卡";
        }

        return ProvidersName;
    }

    public String getIMSI() {
        String myimsi = null;
        myimsi = telephonyManager.getSubscriberId();

        return myimsi;

    }

    /**
     * 获取设备唯一标识
     *
     * @return
     */
    public String getANDROID_ID() {
        //获取设备的唯一标识
        String ANDROID_ID = Settings.System.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        if (!TextUtils.isEmpty(ANDROID_ID)) {
            return ANDROID_ID;
        } else {
            String szImei = telephonyManager.getDeviceId();
            return szImei;
        }
    }

    /**
     * 获取到设备mac地址
     *
     * @return
     */
    public String getMacAddress() {
        String macAddress = null;
        StringBuffer buf = new StringBuffer();
        NetworkInterface networkInterface = null;
        try {
            networkInterface = NetworkInterface.getByName("eth1");
            if (networkInterface == null) {
                networkInterface = NetworkInterface.getByName("wlan0");
            }
            if (networkInterface == null) {
                return "02:00:00:00:00:02";
            }
            byte[] addr = networkInterface.getHardwareAddress();
            for (byte b : addr) {
                buf.append(String.format("%02X:", b));
            }
            if (buf.length() > 0) {
                buf.deleteCharAt(buf.length() - 1);
            }
            macAddress = buf.toString();
        } catch (SocketException e) {
            e.printStackTrace();
            return "02:00:00:00:00:02";
        }
        return macAddress;
    }

}
