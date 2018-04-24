package com.xuke.androidone.model.bean.login;

/**
 * <pre>
 * —————————————神兽出没——————————————
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
 */
public class TokenUserEntity {

    private String accountNum;
    private String password;
    private String meid;
    private String macid;
    private String sysid;
    private String fromid;

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMeid() {
        return meid;
    }

    public void setMeid(String meid) {
        this.meid = meid;
    }

    public String getMacid() {
        return macid;
    }

    public void setMacid(String macid) {
        this.macid = macid;
    }

    public String getSysid() {
        return sysid;
    }

    public void setSysid(String sysid) {
        this.sysid = sysid;
    }

    public String getFromid() {
        return fromid;
    }

    public void setFromid(String fromid) {
        this.fromid = fromid;
    }
}
