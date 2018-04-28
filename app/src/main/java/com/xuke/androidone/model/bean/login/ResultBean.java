package com.xuke.androidone.model.bean.login;

import java.io.Serializable;

/**
 * Created by xuke on 2018/1/26.
 * <P></P>
 */

public class ResultBean implements Serializable {

    /**
     * msg : 更新成功!
     * success : true
     */

    private String msg;
    private boolean success;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
