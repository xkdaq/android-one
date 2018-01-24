package com.xuke.andoirdone.cache;

import com.xuke.andoirdone.model.bean.douban.child.SubjectsBean;
import com.zyw.horrarndoo.sdk.utils.SpUtils;

import java.util.List;


/**
 * Created by xuke on 2018/1/24.
 * 电影列表的缓存
 */

public class Cache {
    /**
     * 获取豆瓣电影hot cache
     *
     * @return 豆瓣电影hot cache
     */
    public static List<SubjectsBean> getHotMovieCache() {
        return SpUtils.getDataList("hot_movie_cache", SubjectsBean.class);
    }

    /**
     * 保存豆瓣电影hot cache
     */
    public static void saveHotMovieCache(List<SubjectsBean> list) {
        SpUtils.setDataList("hot_movie_cache", list);
    }
}
