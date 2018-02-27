package com.xuke.androidone.model.bean.one;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xuke on 2018/1/24.
 * 首页内容的id
 */

public class OneIdBean implements Serializable {

    /**
     * res : 0
     * data : ["1998","1999","1997","1996","1995","1994","1993","1992","1991","1990"]
     */

    private int res;
    private List<String> data;

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "OneIdBean{" +
                "res=" + res +
                ", data=" + data +
                '}';
    }
}
