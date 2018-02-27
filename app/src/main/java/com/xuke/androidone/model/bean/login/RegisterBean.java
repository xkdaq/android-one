package com.xuke.androidone.model.bean.login;

import java.io.Serializable;

/**
 * <pre>
 * —————————————————————神兽出没——————————————————————————
 *          ┌──┐       ┌──┐
 *       ┌──┘  ┴───────┘  ┴──┐
 *       │                   │
 *       │        ───        │
 *       │   ─┬┘       └┬─   │
 *       │                   │
 *       │        ─┴─        │
 *       │                   │
 *       └───┐           ┌───┘
 *           │           │
 *           │           │　        　神兽保佑
 *           │           │　          代码无BUG!
 *           │           │
 *           │           │
 *           │           └──────────────┐
 *           │                          │
 *           │                          ├─┐
 *           │                          ┌─┘
 *           │                          │
 *           └─┐  ┐  ┌─────────┬──┐  ┌──┘
 *             │ ─┤ ─┤         │ ─┤ ─┤
 *             └──┴──┘         └──┴──┘
 *
 * —————————————————————感觉萌萌哒————————————————————————
 * </pre>
 *
 * Created by xuke on 2018/1/25.
 * 注册请求的bean
 */
public class RegisterBean implements Serializable {
    public String username;
    public String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}





















