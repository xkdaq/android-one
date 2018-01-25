package com.xuke.andoirdone.model.bean.login;

import java.io.Serializable;

/**
 * Created by xuke on 2018/1/26.
 */

public class ResultBean<T> implements Serializable {

    /**
     * meta : {"success":true,"message":"ok"}
     * data : {"id":null,"userName":"xke","password":"123456","age":null}
     */

    private MetaBean meta;
    private T data;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static class MetaBean {
        /**
         * success : true
         * message : ok
         */

        private boolean success;
        private String message;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
