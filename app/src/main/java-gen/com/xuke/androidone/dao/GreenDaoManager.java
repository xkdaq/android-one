package com.xuke.androidone.dao;


import com.xuke.androidone.MyApplication;
import com.xuke.androidone.dao.gen.DaoMaster;
import com.xuke.androidone.dao.gen.DaoSession;
import com.xuke.androidone.dao.gen.UserBeanDao;
import com.xuke.androidone.model.bean.login.UserBean;

import java.util.List;

public class GreenDaoManager {
    private static GreenDaoManager mInstance;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private DaoMaster.OpenHelper mySQLiteOpenHelper;


    public GreenDaoManager() {
        //创建一个数据库
        mySQLiteOpenHelper = new DaoMaster.DevOpenHelper(MyApplication.getInstance(), "xuke_db", null);
        //该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(mySQLiteOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public static GreenDaoManager getInstance() {
        if (mInstance == null) {
            mInstance = new GreenDaoManager();
        }
        return mInstance;
    }

    /**
     * 获取DaoMaster
     * <p>
     * 判断是否存在数据库，如果没有则创建数据库
     */
    public DaoMaster getMaster() {
        if (null == mDaoMaster) {
            synchronized (GreenDaoManager.class) {
                if (null == mDaoMaster) {
                    DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(MyApplication.getInstance(), "xuke_db", null);
                    mDaoMaster = new DaoMaster(helper.getWritableDatabase());
                }
            }
        }
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }

    /**
     * 关闭数据连接
     */
    public void close() {
        if (mySQLiteOpenHelper != null) {
            mySQLiteOpenHelper.close();
        }
    }

    /**
     * 用户是否登录
     *
     * @param accountNum 用户id
     */
    public boolean isLoginUserExist(String accountNum) {
        UserBeanDao loginUserDao = mDaoSession.getUserBeanDao();
        UserBean findUser = loginUserDao.queryBuilder().where(UserBeanDao.Properties.AccountNum.eq(accountNum)).build().unique();
        return findUser != null;
    }


    /**
     * 插入或更新登录用户
     *
     * @param loginUser 登录用户
     */
    public void saveLoginUser(UserBean loginUser) {
        UserBeanDao loginUserDao = mDaoSession.getUserBeanDao();
        if (isLoginUserExist(loginUser.getAccountNum())) {
            loginUserDao.update(loginUser);
        } else {
            loginUserDao.insert(loginUser);
        }

    }


    /**
     * 获取登录用户
     *
     * @param accountNum 用户id
     * @return LoginUser 可能为null
     */
    public UserBean getLoginUser(String accountNum) {
        UserBeanDao loginUserDao = mDaoSession.getUserBeanDao();
        UserBean findUser = loginUserDao.queryBuilder().where(UserBeanDao.Properties.AccountNum.eq(accountNum)).build().unique();
        return findUser;
    }

    /**
     * 根据用户id删除某用户
     *
     * @param accountNum 用户id
     */
    public void deleteLoginUser(String accountNum) {
        UserBeanDao loginUserDao = mDaoSession.getUserBeanDao();
        List<UserBean> userList = loginUserDao.queryBuilder().where(UserBeanDao.Properties.AccountNum.eq(accountNum)).build().list();
        for (UserBean user : userList) {
            loginUserDao.delete(user);
        }
    }

}

