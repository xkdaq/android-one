package com.xuke.andoirdone.model.bean.one;

import java.io.Serializable;

public class OneBean implements Serializable {

    /**
     * res : 0
     * data : {"hpcontent_id":"2000","hp_title":"VOL.1970","author_id":"-1","hp_img_url":"http://image.wufazhuce.com/FgW-iZpSb4g0vuWLvFAiC5mgIRPq","hp_img_original_url":"http://image.wufazhuce.com/FgW-iZpSb4g0vuWLvFAiC5mgIRPq","hp_author":"摄影＆Matteo Catanese 作品","ipad_url":"http://image.wufazhuce.com/FgW-iZpSb4g0vuWLvFAiC5mgIRPq","hp_content":"对于远方的思念、空虚感、期待，这些思想本身可以绵延不断，比生命更长久。 by 伊塔洛·卡尔维诺","hp_makettime":"2018-02-27 06:00:00","last_update_date":"2018-02-26 23:38:38","web_url":"http://m.wufazhuce.com/one/2000","wb_img_url":"","image_authors":"Matteo Catanese","text_authors":"伊塔洛·卡尔维诺","image_from":"","text_from":"","content_bgcolor":"#FFFFFF","template_category":"0","maketime":"2018-02-27 06:00:00","share_list":{"wx":{"title":"","desc":"","link":"http://m.wufazhuce.com/one/2000?channel=singlemessage","imgUrl":"","audio":""},"wx_timeline":{"title":"","desc":"","link":"http://m.wufazhuce.com/one/2000?channel=timeline","imgUrl":"","audio":""},"weibo":{"title":"ONE一个 对于远方的思念、空虚感、期待，这些思想本身可以绵延不断，比生命更长久。 by 伊塔洛·卡尔维诺\u2014\u2014伊塔洛·卡尔维诺 下载ONE一个APP:http://weibo.com/p/100404157874","desc":"","link":"http://m.wufazhuce.com/one/2000?channel=weibo","imgUrl":"","audio":""},"qq":{"title":"","desc":"","link":"http://m.wufazhuce.com/one/2000?channel=qq","imgUrl":"","audio":""}},"praisenum":2914,"sharenum":657,"commentnum":0}
     */
    @Override
    public String toString() {
        return "OneBean{" +
                "res=" + res +
                ", data=" + data +
                '}';
    }

    private int res;
    private DataBean data;

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        /**
         * hpcontent_id : 2000
         * hp_title : VOL.1970
         * author_id : -1
         * hp_img_url : http://image.wufazhuce.com/FgW-iZpSb4g0vuWLvFAiC5mgIRPq
         * hp_img_original_url : http://image.wufazhuce.com/FgW-iZpSb4g0vuWLvFAiC5mgIRPq
         * hp_author : 摄影＆Matteo Catanese 作品
         * ipad_url : http://image.wufazhuce.com/FgW-iZpSb4g0vuWLvFAiC5mgIRPq
         * hp_content : 对于远方的思念、空虚感、期待，这些思想本身可以绵延不断，比生命更长久。 by 伊塔洛·卡尔维诺
         * hp_makettime : 2018-02-27 06:00:00
         * last_update_date : 2018-02-26 23:38:38
         * web_url : http://m.wufazhuce.com/one/2000
         * wb_img_url :
         * image_authors : Matteo Catanese
         * text_authors : 伊塔洛·卡尔维诺
         * image_from :
         * text_from :
         * content_bgcolor : #FFFFFF
         * template_category : 0
         * maketime : 2018-02-27 06:00:00
         * share_list : {"wx":{"title":"","desc":"","link":"http://m.wufazhuce.com/one/2000?channel=singlemessage","imgUrl":"","audio":""},"wx_timeline":{"title":"","desc":"","link":"http://m.wufazhuce.com/one/2000?channel=timeline","imgUrl":"","audio":""},"weibo":{"title":"ONE一个 对于远方的思念、空虚感、期待，这些思想本身可以绵延不断，比生命更长久。 by 伊塔洛·卡尔维诺\u2014\u2014伊塔洛·卡尔维诺 下载ONE一个APP:http://weibo.com/p/100404157874","desc":"","link":"http://m.wufazhuce.com/one/2000?channel=weibo","imgUrl":"","audio":""},"qq":{"title":"","desc":"","link":"http://m.wufazhuce.com/one/2000?channel=qq","imgUrl":"","audio":""}}
         * praisenum : 2914
         * sharenum : 657
         * commentnum : 0
         */

        @Override
        public String toString() {
            return "DataBean{" +
                    "hpcontent_id='" + hpcontent_id + '\'' +
                    ", hp_title='" + hp_title + '\'' +
                    ", author_id='" + author_id + '\'' +
                    ", hp_img_url='" + hp_img_url + '\'' +
                    ", hp_img_original_url='" + hp_img_original_url + '\'' +
                    ", hp_author='" + hp_author + '\'' +
                    ", ipad_url='" + ipad_url + '\'' +
                    ", hp_content='" + hp_content + '\'' +
                    ", hp_makettime='" + hp_makettime + '\'' +
                    ", last_update_date='" + last_update_date + '\'' +
                    ", web_url='" + web_url + '\'' +
                    ", wb_img_url='" + wb_img_url + '\'' +
                    ", image_authors='" + image_authors + '\'' +
                    ", text_authors='" + text_authors + '\'' +
                    ", image_from='" + image_from + '\'' +
                    ", text_from='" + text_from + '\'' +
                    ", content_bgcolor='" + content_bgcolor + '\'' +
                    ", template_category='" + template_category + '\'' +
                    ", maketime='" + maketime + '\'' +
                    ", share_list=" + share_list +
                    ", praisenum=" + praisenum +
                    ", sharenum=" + sharenum +
                    ", commentnum=" + commentnum +
                    '}';
        }

        private String hpcontent_id;
        private String hp_title;
        private String author_id;
        private String hp_img_url;
        private String hp_img_original_url;
        private String hp_author;
        private String ipad_url;
        private String hp_content;
        private String hp_makettime;
        private String last_update_date;
        private String web_url;
        private String wb_img_url;
        private String image_authors;
        private String text_authors;
        private String image_from;
        private String text_from;
        private String content_bgcolor;
        private String template_category;
        private String maketime;
        private ShareListBean share_list;
        private int praisenum;
        private int sharenum;
        private int commentnum;

        public String getHpcontent_id() {
            return hpcontent_id;
        }

        public void setHpcontent_id(String hpcontent_id) {
            this.hpcontent_id = hpcontent_id;
        }

        public String getHp_title() {
            return hp_title;
        }

        public void setHp_title(String hp_title) {
            this.hp_title = hp_title;
        }

        public String getAuthor_id() {
            return author_id;
        }

        public void setAuthor_id(String author_id) {
            this.author_id = author_id;
        }

        public String getHp_img_url() {
            return hp_img_url;
        }

        public void setHp_img_url(String hp_img_url) {
            this.hp_img_url = hp_img_url;
        }

        public String getHp_img_original_url() {
            return hp_img_original_url;
        }

        public void setHp_img_original_url(String hp_img_original_url) {
            this.hp_img_original_url = hp_img_original_url;
        }

        public String getHp_author() {
            return hp_author;
        }

        public void setHp_author(String hp_author) {
            this.hp_author = hp_author;
        }

        public String getIpad_url() {
            return ipad_url;
        }

        public void setIpad_url(String ipad_url) {
            this.ipad_url = ipad_url;
        }

        public String getHp_content() {
            return hp_content;
        }

        public void setHp_content(String hp_content) {
            this.hp_content = hp_content;
        }

        public String getHp_makettime() {
            return hp_makettime;
        }

        public void setHp_makettime(String hp_makettime) {
            this.hp_makettime = hp_makettime;
        }

        public String getLast_update_date() {
            return last_update_date;
        }

        public void setLast_update_date(String last_update_date) {
            this.last_update_date = last_update_date;
        }

        public String getWeb_url() {
            return web_url;
        }

        public void setWeb_url(String web_url) {
            this.web_url = web_url;
        }

        public String getWb_img_url() {
            return wb_img_url;
        }

        public void setWb_img_url(String wb_img_url) {
            this.wb_img_url = wb_img_url;
        }

        public String getImage_authors() {
            return image_authors;
        }

        public void setImage_authors(String image_authors) {
            this.image_authors = image_authors;
        }

        public String getText_authors() {
            return text_authors;
        }

        public void setText_authors(String text_authors) {
            this.text_authors = text_authors;
        }

        public String getImage_from() {
            return image_from;
        }

        public void setImage_from(String image_from) {
            this.image_from = image_from;
        }

        public String getText_from() {
            return text_from;
        }

        public void setText_from(String text_from) {
            this.text_from = text_from;
        }

        public String getContent_bgcolor() {
            return content_bgcolor;
        }

        public void setContent_bgcolor(String content_bgcolor) {
            this.content_bgcolor = content_bgcolor;
        }

        public String getTemplate_category() {
            return template_category;
        }

        public void setTemplate_category(String template_category) {
            this.template_category = template_category;
        }

        public String getMaketime() {
            return maketime;
        }

        public void setMaketime(String maketime) {
            this.maketime = maketime;
        }

        public ShareListBean getShare_list() {
            return share_list;
        }

        public void setShare_list(ShareListBean share_list) {
            this.share_list = share_list;
        }

        public int getPraisenum() {
            return praisenum;
        }

        public void setPraisenum(int praisenum) {
            this.praisenum = praisenum;
        }

        public int getSharenum() {
            return sharenum;
        }

        public void setSharenum(int sharenum) {
            this.sharenum = sharenum;
        }

        public int getCommentnum() {
            return commentnum;
        }

        public void setCommentnum(int commentnum) {
            this.commentnum = commentnum;
        }

        public static class ShareListBean {

            /**
             * wx : {"title":"","desc":"","link":"http://m.wufazhuce.com/one/2000?channel=singlemessage","imgUrl":"","audio":""}
             * wx_timeline : {"title":"","desc":"","link":"http://m.wufazhuce.com/one/2000?channel=timeline","imgUrl":"","audio":""}
             * weibo : {"title":"ONE一个 对于远方的思念、空虚感、期待，这些思想本身可以绵延不断，比生命更长久。 by 伊塔洛·卡尔维诺\u2014\u2014伊塔洛·卡尔维诺 下载ONE一个APP:http://weibo.com/p/100404157874","desc":"","link":"http://m.wufazhuce.com/one/2000?channel=weibo","imgUrl":"","audio":""}
             * qq : {"title":"","desc":"","link":"http://m.wufazhuce.com/one/2000?channel=qq","imgUrl":"","audio":""}
             */

            @Override
            public String toString() {
                return "ShareListBean{" +
                        "wx=" + wx +
                        ", wx_timeline=" + wx_timeline +
                        ", weibo=" + weibo +
                        ", qq=" + qq +
                        '}';
            }

            private WxBean wx;
            private WxTimelineBean wx_timeline;
            private WeiboBean weibo;
            private QqBean qq;

            public WxBean getWx() {
                return wx;
            }

            public void setWx(WxBean wx) {
                this.wx = wx;
            }

            public WxTimelineBean getWx_timeline() {
                return wx_timeline;
            }

            public void setWx_timeline(WxTimelineBean wx_timeline) {
                this.wx_timeline = wx_timeline;
            }

            public WeiboBean getWeibo() {
                return weibo;
            }

            public void setWeibo(WeiboBean weibo) {
                this.weibo = weibo;
            }

            public QqBean getQq() {
                return qq;
            }

            public void setQq(QqBean qq) {
                this.qq = qq;
            }

            public static class WxBean {
                /**
                 * title :
                 * desc :
                 * link : http://m.wufazhuce.com/one/2000?channel=singlemessage
                 * imgUrl :
                 * audio :
                 */

                private String title;
                private String desc;
                private String link;
                private String imgUrl;
                private String audio;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public String getImgUrl() {
                    return imgUrl;
                }

                public void setImgUrl(String imgUrl) {
                    this.imgUrl = imgUrl;
                }

                public String getAudio() {
                    return audio;
                }

                public void setAudio(String audio) {
                    this.audio = audio;
                }
            }

            public static class WxTimelineBean {
                /**
                 * title :
                 * desc :
                 * link : http://m.wufazhuce.com/one/2000?channel=timeline
                 * imgUrl :
                 * audio :
                 */

                private String title;
                private String desc;
                private String link;
                private String imgUrl;
                private String audio;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public String getImgUrl() {
                    return imgUrl;
                }

                public void setImgUrl(String imgUrl) {
                    this.imgUrl = imgUrl;
                }

                public String getAudio() {
                    return audio;
                }

                public void setAudio(String audio) {
                    this.audio = audio;
                }
            }

            public static class WeiboBean {
                /**
                 * title : ONE一个 对于远方的思念、空虚感、期待，这些思想本身可以绵延不断，比生命更长久。 by 伊塔洛·卡尔维诺——伊塔洛·卡尔维诺 下载ONE一个APP:http://weibo.com/p/100404157874
                 * desc :
                 * link : http://m.wufazhuce.com/one/2000?channel=weibo
                 * imgUrl :
                 * audio :
                 */

                private String title;
                private String desc;
                private String link;
                private String imgUrl;
                private String audio;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public String getImgUrl() {
                    return imgUrl;
                }

                public void setImgUrl(String imgUrl) {
                    this.imgUrl = imgUrl;
                }

                public String getAudio() {
                    return audio;
                }

                public void setAudio(String audio) {
                    this.audio = audio;
                }
            }

            public static class QqBean {
                /**
                 * title :
                 * desc :
                 * link : http://m.wufazhuce.com/one/2000?channel=qq
                 * imgUrl :
                 * audio :
                 */

                private String title;
                private String desc;
                private String link;
                private String imgUrl;
                private String audio;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public String getImgUrl() {
                    return imgUrl;
                }

                public void setImgUrl(String imgUrl) {
                    this.imgUrl = imgUrl;
                }

                public String getAudio() {
                    return audio;
                }

                public void setAudio(String audio) {
                    this.audio = audio;
                }
            }
        }
    }
}
