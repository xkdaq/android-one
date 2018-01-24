package com.xuke.andoirdone.model.bean.douban.child;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xuke on 2018/1/24.
 */

public class SubjectsBean implements Serializable{
    @SerializedName("rating")
    private RatingBean rating;
    @SerializedName("title")
    private String title;
    @SerializedName("collect_count")
    private int collect_count;
    @SerializedName("original_title")
    private String original_title;
    @SerializedName("subtype")
    private String subtype;
    @SerializedName("year")
    private String year;
    @SerializedName("images")
    private ImagesBean images;
    @SerializedName("alt")
    private String alt;
    @SerializedName("id")
    private String id;
    @SerializedName("genres")
    private List<String> genres;
    @SerializedName("casts")
    private List<PersonBean> casts;
    @SerializedName("directors")
    private List<PersonBean> directors;

    public RatingBean getRating() {
        return rating;
    }

    public String getTitle() {
        return this.title;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getSubtype() {
        return subtype;
    }

    public String getYear() {
        return year;
    }

    public ImagesBean getImages() {
        return images;
    }

    public String getAlt() {
        return alt;
    }

    public String getId() {
        return id;
    }

    public List<String> getGenres() {
        return genres;
    }

    public List<PersonBean> getCasts() {
        return casts;
    }

    public List<PersonBean> getDirectors() {
        return directors;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public void setCasts(List<PersonBean> casts) {
        this.casts = casts;
    }

    public void setDirectors(List<PersonBean> directors) {
        this.directors = directors;
    }

    @Override
    public String toString() {
        return "SubjectsBean{" +
                "directors=" + directors +
                ", casts=" + casts +
                ", genres=" + genres +
                ", id='" + id + '\'' +
                ", alt='" + alt + '\'' +
                ", images=" + images +
                ", year='" + year + '\'' +
                ", subtype='" + subtype + '\'' +
                ", original_title='" + original_title + '\'' +
                ", collect_count=" + collect_count +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                '}';
    }


    /**
     * 获取导演字符串
     *
     * @return 导演字符串 A/B/C..
     */
    public String getDirectorsString() {
        return pListToString(getDirectors());
    }

    /**
     * 获取演员字符串
     *
     * @return 演员字符串 A/B/C..
     */
    public String getActorsString() {
        return pListToString(getCasts());
    }

    /**
     * 获取类型字符串
     *
     * @return 类型字符串 A/B/C..
     */
    public String getGenresString() {
        return sListToString(getGenres());
    }


    /**
     * 格式化list为字符串
     *
     * @param list 类型list
     * @return 字符串 A/B/C..
     */
    private String sListToString(List<String> list) {
        String str = "";
        if (list.size() == 0)
            return str;
        for (int i = 0; i < list.size(); i++) {
            str = str + list.get(i);
            if (i < list.size() - 1)
                str += " / ";
        }
        return str;
    }

    /**
     * 格式化list为字符串
     *
     * @param list 类型list
     * @return 字符串 A/B/C..
     */
    private String pListToString(List<PersonBean> list) {
        String str = "";
        if (list.size() == 0)
            return str;
        for (int i = 0; i < list.size(); i++) {
            str = str + list.get(i).getName();
            if (i < list.size() - 1)
                str += " / ";
        }
        return str;
    }
}
