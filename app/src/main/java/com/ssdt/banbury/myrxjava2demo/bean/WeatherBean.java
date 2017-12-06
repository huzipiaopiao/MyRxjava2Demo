package com.ssdt.banbury.myrxjava2demo.bean;

import java.util.List;

/**
 * @author banbury
 * @version v1.0
 * @created 2017/12/6_14:44.
 * @description
 */

public class WeatherBean {

    @Override
    public String toString() {
        return "WeatherBean{" +
                "showapi_res_code=" + showapi_res_code +
                ", showapi_res_error='" + showapi_res_error + '\'' +
                ", showapi_res_body=" + showapi_res_body +
                '}';
    }

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"area":"北京","areaid":"101010100","list":[{"max_temperature":"2℃","min_temperature":"-7℃","time":"20160113","weather":"晴-多云","wind_direction":"无持续风向","wind_power":"微风"},{"max_temperature":"0℃","min_temperature":"-8℃","time":"20160112","weather":"晴","wind_direction":"无持续风向","wind_power":"微风"},{"max_temperature":"-1℃","min_temperature":"-9℃","time":"20160111","weather":"晴","wind_direction":"无持续风向","wind_power":"微风"},{"max_temperature":"1℃","min_temperature":"-8℃","time":"20160110","weather":"多云-晴","wind_direction":"无持续风向","wind_power":"微风"},{"max_temperature":"3℃","min_temperature":"-6℃","time":"20160109","weather":"晴-多云","wind_direction":"无持续风向","wind_power":"微风"},{"max_temperature":"2℃","min_temperature":"-8℃","time":"20160108","weather":"晴","wind_direction":"北风~无持续风向","wind_power":"3-4级~微风"},{"max_temperature":"2℃","min_temperature":"-7℃","time":"20160107","weather":"晴","wind_direction":"北风~无持续风向","wind_power":"3-4级~微风"},{"max_temperature":"3℃","min_temperature":"-6℃","time":"20160106","weather":"晴","wind_direction":"北风","wind_power":"3-4级"},{"max_temperature":"1℃","min_temperature":"-7℃","time":"20160105","weather":"晴","wind_direction":"北风~无持续风向","wind_power":"3-4级~微风"},{"max_temperature":"2℃","min_temperature":"-6℃","time":"20160104","weather":"多云-晴","wind_direction":"北风~无持续风向","wind_power":"3-4级~微风"},{"max_temperature":"3℃","min_temperature":"-4℃","time":"20160103","weather":"霾-多云","wind_direction":"无持续风向~北风","wind_power":"微风~3-4级"},{"max_temperature":"6℃","min_temperature":"-4℃","time":"20160102","weather":"霾-雾","wind_direction":"无持续风向","wind_power":"微风"},{"max_temperature":"5℃","min_temperature":"-4℃","time":"20160101","weather":"霾","wind_direction":"无持续风向","wind_power":"微风"}],"month":"201601","ret_code":0}
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
                    "area='" + area + '\'' +
                    ", areaid='" + areaid + '\'' +
                    ", month='" + month + '\'' +
                    ", ret_code=" + ret_code +
                    ", list=" + list +
                    '}';
        }

        /**
         * area : 北京
         * areaid : 101010100
         * list : [{"max_temperature":"2℃","min_temperature":"-7℃","time":"20160113","weather":"晴-多云","wind_direction":"无持续风向","wind_power":"微风"},{"max_temperature":"0℃","min_temperature":"-8℃","time":"20160112","weather":"晴","wind_direction":"无持续风向","wind_power":"微风"},{"max_temperature":"-1℃","min_temperature":"-9℃","time":"20160111","weather":"晴","wind_direction":"无持续风向","wind_power":"微风"},{"max_temperature":"1℃","min_temperature":"-8℃","time":"20160110","weather":"多云-晴","wind_direction":"无持续风向","wind_power":"微风"},{"max_temperature":"3℃","min_temperature":"-6℃","time":"20160109","weather":"晴-多云","wind_direction":"无持续风向","wind_power":"微风"},{"max_temperature":"2℃","min_temperature":"-8℃","time":"20160108","weather":"晴","wind_direction":"北风~无持续风向","wind_power":"3-4级~微风"},{"max_temperature":"2℃","min_temperature":"-7℃","time":"20160107","weather":"晴","wind_direction":"北风~无持续风向","wind_power":"3-4级~微风"},{"max_temperature":"3℃","min_temperature":"-6℃","time":"20160106","weather":"晴","wind_direction":"北风","wind_power":"3-4级"},{"max_temperature":"1℃","min_temperature":"-7℃","time":"20160105","weather":"晴","wind_direction":"北风~无持续风向","wind_power":"3-4级~微风"},{"max_temperature":"2℃","min_temperature":"-6℃","time":"20160104","weather":"多云-晴","wind_direction":"北风~无持续风向","wind_power":"3-4级~微风"},{"max_temperature":"3℃","min_temperature":"-4℃","time":"20160103","weather":"霾-多云","wind_direction":"无持续风向~北风","wind_power":"微风~3-4级"},{"max_temperature":"6℃","min_temperature":"-4℃","time":"20160102","weather":"霾-雾","wind_direction":"无持续风向","wind_power":"微风"},{"max_temperature":"5℃","min_temperature":"-4℃","time":"20160101","weather":"霾","wind_direction":"无持续风向","wind_power":"微风"}]
         * month : 201601
         * ret_code : 0
         */

        private String area;
        private String areaid;
        private String month;
        private int ret_code;
        private List<ListBean> list;

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

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

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
                        "max_temperature='" + max_temperature + '\'' +
                        ", min_temperature='" + min_temperature + '\'' +
                        ", time='" + time + '\'' +
                        ", weather='" + weather + '\'' +
                        ", wind_direction='" + wind_direction + '\'' +
                        ", wind_power='" + wind_power + '\'' +
                        '}';
            }

            /**
             * max_temperature : 2℃
             * min_temperature : -7℃
             * time : 20160113
             * weather : 晴-多云
             * wind_direction : 无持续风向
             * wind_power : 微风
             */

            private String max_temperature;
            private String min_temperature;
            private String time;
            private String weather;
            private String wind_direction;
            private String wind_power;

            public String getMax_temperature() {
                return max_temperature;
            }

            public void setMax_temperature(String max_temperature) {
                this.max_temperature = max_temperature;
            }

            public String getMin_temperature() {
                return min_temperature;
            }

            public void setMin_temperature(String min_temperature) {
                this.min_temperature = min_temperature;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public String getWind_direction() {
                return wind_direction;
            }

            public void setWind_direction(String wind_direction) {
                this.wind_direction = wind_direction;
            }

            public String getWind_power() {
                return wind_power;
            }

            public void setWind_power(String wind_power) {
                this.wind_power = wind_power;
            }
        }
    }
}
