package com.xuke.androidone.model.rxbus;

import android.net.Uri;

/**
 * Created by xuke on 2018/4/27.
 * RxBus传递头像uri
 */

public class RxEventHeadBean {
    private Uri uri;

    public RxEventHeadBean(Uri uri) {
        this.uri = uri;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
