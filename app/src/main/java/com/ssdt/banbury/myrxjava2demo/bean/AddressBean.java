package com.ssdt.banbury.myrxjava2demo.bean;

import java.util.List;

/**
 * @author banbury
 * @version v1.0
 * @created 2017/12/6_14:40.
 * @description
 */

public class AddressBean {
    @Override
    public String toString() {
        return "AddressBean{" +
                "showapi_res_code=" + showapi_res_code +
                ", showapi_res_error='" + showapi_res_error + '\'' +
                ", showapi_res_body=" + showapi_res_body +
                '}';
    }

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"list":[{"area":"丽江","areaid":"101291401","cityInfo":{"c1":"101291401","c10":"2","c11":"0888","c12":"674100","c15":"2394","c16":"AZ9888","c17":"+8","c2":"lijiang","c3":"丽江","c4":"lijiang","c5":"丽江","c6":"yunnan","c7":"云南","c8":"china","c9":"中国","latitude":26.903,"longitude":100.222},"distric":"丽江","prov":"云南"}],"ret_code":0}
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
                    "ret_code=" + ret_code +
                    ", list=" + list +
                    '}';
        }

        /**
         * list : [{"area":"丽江","areaid":"101291401","cityInfo":{"c1":"101291401","c10":"2","c11":"0888","c12":"674100","c15":"2394","c16":"AZ9888","c17":"+8","c2":"lijiang","c3":"丽江","c4":"lijiang","c5":"丽江","c6":"yunnan","c7":"云南","c8":"china","c9":"中国","latitude":26.903,"longitude":100.222},"distric":"丽江","prov":"云南"}]
         * ret_code : 0
         */

        private int ret_code;
        private List<ListBean> list;

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {

            @Override
            public String toString() {
                return "ListBean{" +
                        "area='" + area + '\'' +
                        ", areaid='" + areaid + '\'' +
                        ", cityInfo=" + cityInfo +
                        ", distric='" + distric + '\'' +
                        ", prov='" + prov + '\'' +
                        '}';
            }

            /**
             * area : 丽江
             * areaid : 101291401
             * cityInfo : {"c1":"101291401","c10":"2","c11":"0888","c12":"674100","c15":"2394","c16":"AZ9888","c17":"+8","c2":"lijiang","c3":"丽江","c4":"lijiang","c5":"丽江","c6":"yunnan","c7":"云南","c8":"china","c9":"中国","latitude":26.903,"longitude":100.222}
             * distric : 丽江
             * prov : 云南
             */

            private String area;
            private String areaid;
            private CityInfoBean cityInfo;
            private String distric;
            private String prov;

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getAreaid() {
                return areaid;
            }

            public void setAreaid(String areaid) {
                this.areaid = areaid;
            }

            public CityInfoBean getCityInfo() {
                return cityInfo;
            }

            public void setCityInfo(CityInfoBean cityInfo) {
                this.cityInfo = cityInfo;
            }

            public String getDistric() {
                return distric;
            }

            public void setDistric(String distric) {
                this.distric = distric;
            }

            public String getProv() {
                return prov;
            }

            public void setProv(String prov) {
                this.prov = prov;
            }

            public static class CityInfoBean {
                @Override
                public String toString() {
                    return "CityInfoBean{" +
                            "c1='" + c1 + '\'' +
                            ", c10='" + c10 + '\'' +
                            ", c11='" + c11 + '\'' +
                            ", c12='" + c12 + '\'' +
                            ", c15='" + c15 + '\'' +
                            ", c16='" + c16 + '\'' +
                            ", c17='" + c17 + '\'' +
                            ", c2='" + c2 + '\'' +
                            ", c3='" + c3 + '\'' +
                            ", c4='" + c4 + '\'' +
                            ", c5='" + c5 + '\'' +
                            ", c6='" + c6 + '\'' +
                            ", c7='" + c7 + '\'' +
                            ", c8='" + c8 + '\'' +
                            ", c9='" + c9 + '\'' +
                            ", latitude=" + latitude +
                            ", longitude=" + longitude +
                            '}';
                }

                /**
                 * c1 : 101291401
                 * c10 : 2
                 * c11 : 0888
                 * c12 : 674100
                 * c15 : 2394
                 * c16 : AZ9888
                 * c17 : +8
                 * c2 : lijiang
                 * c3 : 丽江
                 * c4 : lijiang
                 * c5 : 丽江
                 * c6 : yunnan
                 * c7 : 云南
                 * c8 : china
                 * c9 : 中国
                 * latitude : 26.903
                 * longitude : 100.222
                 */

                private String c1;
                private String c10;
                private String c11;
                private String c12;
                private String c15;
                private String c16;
                private String c17;
                private String c2;
                private String c3;
                private String c4;
                private String c5;
                private String c6;
                private String c7;
                private String c8;
                private String c9;
                private double latitude;
                private double longitude;

                public String getC1() {
                    return c1;
                }

                public void setC1(String c1) {
                    this.c1 = c1;
                }

                public String getC10() {
                    return c10;
                }

                public void setC10(String c10) {
                    this.c10 = c10;
                }

                public String getC11() {
                    return c11;
                }

                public void setC11(String c11) {
                    this.c11 = c11;
                }

                public String getC12() {
                    return c12;
                }

                public void setC12(String c12) {
                    this.c12 = c12;
                }

                public String getC15() {
                    return c15;
                }

                public void setC15(String c15) {
                    this.c15 = c15;
                }

                public String getC16() {
                    return c16;
                }

                public void setC16(String c16) {
                    this.c16 = c16;
                }

                public String getC17() {
                    return c17;
                }

                public void setC17(String c17) {
                    this.c17 = c17;
                }

                public String getC2() {
                    return c2;
                }

                public void setC2(String c2) {
                    this.c2 = c2;
                }

                public String getC3() {
                    return c3;
                }

                public void setC3(String c3) {
                    this.c3 = c3;
                }

                public String getC4() {
                    return c4;
                }

                public void setC4(String c4) {
                    this.c4 = c4;
                }

                public String getC5() {
                    return c5;
                }

                public void setC5(String c5) {
                    this.c5 = c5;
                }

                public String getC6() {
                    return c6;
                }

                public void setC6(String c6) {
                    this.c6 = c6;
                }

                public String getC7() {
                    return c7;
                }

                public void setC7(String c7) {
                    this.c7 = c7;
                }

                public String getC8() {
                    return c8;
                }

                public void setC8(String c8) {
                    this.c8 = c8;
                }

                public String getC9() {
                    return c9;
                }

                public void setC9(String c9) {
                    this.c9 = c9;
                }

                public double getLatitude() {
                    return latitude;
                }

                public void setLatitude(double latitude) {
                    this.latitude = latitude;
                }

                public double getLongitude() {
                    return longitude;
                }

                public void setLongitude(double longitude) {
                    this.longitude = longitude;
                }
            }
        }
    }
}
