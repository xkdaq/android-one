package com.xuke.androidone.utils;

import android.text.TextUtils;

import com.xuke.androidone.api.APIService;

public class RelativePath {
    public static String toAbs(String absPath) {
        String url = APIService.HOST;
        return TextUtils.isEmpty(absPath) ? ""
                : absPath.startsWith("http") ? absPath : absPath.startsWith("/") ? url + absPath
                : url + "/" + absPath;
    }
}
