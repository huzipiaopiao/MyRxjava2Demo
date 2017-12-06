package com.ssdt.banbury.myrxjava2demo.bean;

import java.util.List;

/**
 * @author banbury
 * @version v1.0
 * @created 2017/12/5_17:21.
 * @description
 */

public class NetBean {
    @Override
    public String toString() {
        return "NetBean{" +
                "showapi_res_code=" + showapi_res_code +
                ", showapi_res_error='" + showapi_res_error + '\'' +
                ", showapi_res_body=" + showapi_res_body +
                '}';
    }

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"pagebean":{"allNum":3793,"allPages":190,"contentlist":[{"create_time":"2015-07-02 13:00:02","hate":"111","height":"800","id":"14710774","image0":"http://ww2.sinaimg.cn/mw240/005OPYkojw1etn14eqlj8j30go0m8ab3.jpg","image1":"http://ww2.sinaimg.cn/bmiddle/005OPYkojw1etn14eqlj8j30go0m8ab3.jpg","image2":"http://ww2.sinaimg.cn/large/005OPYkojw1etn14eqlj8j30go0m8ab3.jpg","image3":"http://wimg.spriteapp.cn/ugc/2015/07/01/5592ca3b28f6b.jpg","is_gif":"0","love":"303","text":"哎尼玛！抢我五杀","type":"10","videotime":"0","video_uri":"","voicelength":"0","voicetime":"0","voiceuri":"","weixin_url":"http://14710774.f.budejie.com/budejie/land.php?pid=14710774&wx.qq.com&appname=","width":"600"}],"currentPage":1,"maxResult":20},"ret_code":0}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    private ShowapiResBodyBean showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {
        @Override
        public String toString() {
            return "ShowapiResBodyBean{" +
                    "pagebean=" + pagebean +
                    ", ret_code=" + ret_code +
                    '}';
        }

        /**
         * pagebean : {"allNum":3793,"allPages":190,"contentlist":[{"create_time":"2015-07-02 13:00:02","hate":"111","height":"800","id":"14710774","image0":"http://ww2.sinaimg.cn/mw240/005OPYkojw1etn14eqlj8j30go0m8ab3.jpg","image1":"http://ww2.sinaimg.cn/bmiddle/005OPYkojw1etn14eqlj8j30go0m8ab3.jpg","image2":"http://ww2.sinaimg.cn/large/005OPYkojw1etn14eqlj8j30go0m8ab3.jpg","image3":"http://wimg.spriteapp.cn/ugc/2015/07/01/5592ca3b28f6b.jpg","is_gif":"0","love":"303","text":"哎尼玛！抢我五杀","type":"10","videotime":"0","video_uri":"","voicelength":"0","voicetime":"0","voiceuri":"","weixin_url":"http://14710774.f.budejie.com/budejie/land.php?pid=14710774&wx.qq.com&appname=","width":"600"}],"currentPage":1,"maxResult":20}
         * ret_code : 0
         */

        private PagebeanBean pagebean;
        private int ret_code;

        public PagebeanBean getPagebean() {
            return pagebean;
        }

        public void setPagebean(PagebeanBean pagebean) {
            this.pagebean = pagebean;
        }

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public static class PagebeanBean {

            @Override
            public String toString() {
                return "PagebeanBean{" +
                        "allNum=" + allNum +
                        ", allPages=" + allPages +
                        ", currentPage=" + currentPage +
                        ", maxResult=" + maxResult +
                        ", contentlist=" + contentlist +
                        '}';
            }

            /**
             * allNum : 3793
             * allPages : 190
             * contentlist : [{"create_time":"2015-07-02 13:00:02","hate":"111","height":"800","id":"14710774","image0":"http://ww2.sinaimg.cn/mw240/005OPYkojw1etn14eqlj8j30go0m8ab3.jpg","image1":"http://ww2.sinaimg.cn/bmiddle/005OPYkojw1etn14eqlj8j30go0m8ab3.jpg","image2":"http://ww2.sinaimg.cn/large/005OPYkojw1etn14eqlj8j30go0m8ab3.jpg","image3":"http://wimg.spriteapp.cn/ugc/2015/07/01/5592ca3b28f6b.jpg","is_gif":"0","love":"303","text":"哎尼玛！抢我五杀","type":"10","videotime":"0","video_uri":"","voicelength":"0","voicetime":"0","voiceuri":"","weixin_url":"http://14710774.f.budejie.com/budejie/land.php?pid=14710774&wx.qq.com&appname=","width":"600"}]
             * currentPage : 1
             * maxResult : 20
             */

            private int allNum;
            private int allPages;
            private int currentPage;
            private int maxResult;
            private List<ContentlistBean> contentlist;

            public int getAllNum() {
                return allNum;
            }

            public void setAllNum(int allNum) {
                this.allNum = allNum;
            }

            public int getAllPages() {
                return allPages;
            }

            public void setAllPages(int allPages) {
                this.allPages = allPages;
            }

            public int getCurrentPage() {
                return currentPage;
            }

            public void setCurrentPage(int currentPage) {
                this.currentPage = currentPage;
            }

            public int getMaxResult() {
                return maxResult;
            }

            public void setMaxResult(int maxResult) {
                this.maxResult = maxResult;
            }

            public List<ContentlistBean> getContentlist() {
                return contentlist;
            }

            public void setContentlist(List<ContentlistBean> contentlist) {
                this.contentlist = contentlist;
            }

            public static class ContentlistBean {

                @Override
                public String toString() {
                    return "ContentlistBean{" +
                            "create_time='" + create_time + '\'' +
                            ", hate='" + hate + '\'' +
                            ", height='" + height + '\'' +
                            ", id='" + id + '\'' +
                            ", image0='" + image0 + '\'' +
                            ", image1='" + image1 + '\'' +
                            ", image2='" + image2 + '\'' +
                            ", image3='" + image3 + '\'' +
                            ", is_gif='" + is_gif + '\'' +
                            ", love='" + love + '\'' +
                            ", text='" + text + '\'' +
                            ", type='" + type + '\'' +
                            ", videotime='" + videotime + '\'' +
                            ", video_uri='" + video_uri + '\'' +
                            ", voicelength='" + voicelength + '\'' +
                            ", voicetime='" + voicetime + '\'' +
                            ", voiceuri='" + voiceuri + '\'' +
                            ", weixin_url='" + weixin_url + '\'' +
                            ", width='" + width + '\'' +
                            '}';
                }

                /**
                 * create_time : 2015-07-02 13:00:02
                 * hate : 111
                 * height : 800
                 * id : 14710774
                 * image0 : http://ww2.sinaimg.cn/mw240/005OPYkojw1etn14eqlj8j30go0m8ab3.jpg
                 * image1 : http://ww2.sinaimg.cn/bmiddle/005OPYkojw1etn14eqlj8j30go0m8ab3.jpg
                 * image2 : http://ww2.sinaimg.cn/large/005OPYkojw1etn14eqlj8j30go0m8ab3.jpg
                 * image3 : http://wimg.spriteapp.cn/ugc/2015/07/01/5592ca3b28f6b.jpg
                 * is_gif : 0
                 * love : 303
                 * text : 哎尼玛！抢我五杀
                 * type : 10
                 * videotime : 0
                 * video_uri :
                 * voicelength : 0
                 * voicetime : 0
                 * voiceuri :
                 * weixin_url : http://14710774.f.budejie.com/budejie/land.php?pid=14710774&wx.qq.com&appname=
                 * width : 600
                 */

                private String create_time;
                private String hate;
                private String height;
                private String id;
                private String image0;
                private String image1;
                private String image2;
                private String image3;
                private String is_gif;
                private String love;
                private String text;
                private String type;
                private String videotime;
                private String video_uri;
                private String voicelength;
                private String voicetime;
                private String voiceuri;
                private String weixin_url;
                private String width;

                public String getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(String create_time) {
                    this.create_time = create_time;
                }

                public String getHate() {
                    return hate;
                }

                public void setHate(String hate) {
                    this.hate = hate;
                }

                public String getHeight() {
                    return height;
                }

                public void setHeight(String height) {
                    this.height = height;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getImage0() {
                    return image0;
                }

                public void setImage0(String image0) {
                    this.image0 = image0;
                }

                public String getImage1() {
                    return image1;
                }

                public void setImage1(String image1) {
                    this.image1 = image1;
                }

                public String getImage2() {
                    return image2;
                }

                public void setImage2(String image2) {
                    this.image2 = image2;
                }

                public String getImage3() {
                    return image3;
                }

                public void setImage3(String image3) {
                    this.image3 = image3;
                }

                public String getIs_gif() {
                    return is_gif;
                }

                public void setIs_gif(String is_gif) {
                    this.is_gif = is_gif;
                }

                public String getLove() {
                    return love;
                }

                public void setLove(String love) {
                    this.love = love;
                }

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getVideotime() {
                    return videotime;
                }

                public void setVideotime(String videotime) {
                    this.videotime = videotime;
                }

                public String getVideo_uri() {
                    return video_uri;
                }

                public void setVideo_uri(String video_uri) {
                    this.video_uri = video_uri;
                }

                public String getVoicelength() {
                    return voicelength;
                }

                public void setVoicelength(String voicelength) {
                    this.voicelength = voicelength;
                }

                public String getVoicetime() {
                    return voicetime;
                }

                public void setVoicetime(String voicetime) {
                    this.voicetime = voicetime;
                }

                public String getVoiceuri() {
                    return voiceuri;
                }

                public void setVoiceuri(String voiceuri) {
                    this.voiceuri = voiceuri;
                }

                public String getWeixin_url() {
                    return weixin_url;
                }

                public void setWeixin_url(String weixin_url) {
                    this.weixin_url = weixin_url;
                }

                public String getWidth() {
                    return width;
                }

                public void setWidth(String width) {
                    this.width = width;
                }
            }
        }
    }
}
