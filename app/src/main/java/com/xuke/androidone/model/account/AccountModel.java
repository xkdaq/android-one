package com.xuke.androidone.model.account;

import android.support.annotation.NonNull;

import com.xuke.androidone.contract.account.AccountContract;

/**
 * Created by xuke on 2018/4/27.
 */

public class AccountModel implements AccountContract.IAccountModel {

    @NonNull
    public static AccountModel newInstance(){
        return new AccountModel();
    }
}
