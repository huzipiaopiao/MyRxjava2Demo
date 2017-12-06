package com.ssdt.banbury.myrxjava2demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.ssdt.banbury.myrxjava2demo.bean.AddressBean;
import com.ssdt.banbury.myrxjava2demo.bean.NetBean;
import com.ssdt.banbury.myrxjava2demo.bean.WeatherBean;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author banbury
 * @version v1.0
 * @created 2017/12/5_17:23.
 * @description
 */

public class SecondActivity extends AppCompatActivity {

    private TextView textView;
    private static final String TAG = "SecondActivity";
    private String baiSiBuDeJieBase = "http://route.showapi.com/255-1";
    private String baiSiBuDeJieurl = "?type=10&showapi_appid=50676&showapi_sign=3b68385c2a7a411a83c4cf79552e7966";
    private String base_url = "http://route.showapi.com/{path}";
    private String idByAddress_url = "9-3";
    private String weatherByAddressid_url = "9-7";

    private HashMap<String, String> pathParamter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = (TextView) findViewById(R.id.tv);
        pathParamter = new HashMap<>();
        pathParamter.put("showapi_appid", "50676");
        pathParamter.put("showapi_sign", "3b68385c2a7a411a83c4cf79552e7966");
//        getSimpleNetRequest();
//        getSimpleNetRequest2();
//        getDataFromCacheAndNet();
//        getDataAndAfter();
        getAllMsg();
    }

    private void getSimpleNetRequest() {
        Observable
                .create(new ObservableOnSubscribe<Response>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<Response> e) throws Exception {
                        Log.e(TAG, "create: " + Thread.currentThread().getName());
                        Request.Builder builder = new Request.Builder()
                                .url(baiSiBuDeJieBase + baiSiBuDeJieurl)
                                .get();
                        Request request = builder.build();
                        Response response = new OkHttpClient().newCall(request).execute();
                        e.onNext(response);
                    }
                })
                .map(new Function<Response, NetBean>() {
                    @Override
                    public NetBean apply(@NonNull Response response) throws Exception {
                        Log.e(TAG, "map: " + Thread.currentThread().getName());
                        if (response.isSuccessful()) {
                            ResponseBody responseBody = response.body();
                            if (responseBody != null) {
                                Log.e(TAG, "map:转换前:" + responseBody);
                                String string = responseBody.string();
                                Log.e(TAG, "map:转换前:" + string);
                                return new Gson().fromJson(string, NetBean.class);
                            }
                        }
                        return null;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<NetBean>() {
                    @Override
                    public void accept(NetBean netBean) throws Exception {
                        Log.e(TAG, "doOnNext: " + Thread.currentThread().getName());
                        Log.e(TAG, "doOnNext: 保存成功：" + netBean.toString() + "\n");
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NetBean>() {
                    @Override
                    public void accept(NetBean netBean) throws Exception {
                        Log.e(TAG, "subscribe: " + Thread.currentThread().getName());
                        Log.e(TAG, "成功:" + netBean.toString() + "\n");
                        textView.setText(netBean.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "subscribe: " + Thread.currentThread().getName());
                        Log.e(TAG, "失败：" + throwable.getMessage() + "\n");
                        textView.setText(throwable.getMessage());
                    }
                });
    }

    private void getSimpleNetRequest2() {
        Rx2AndroidNetworking.get(baiSiBuDeJieBase)
                .addQueryParameter(pathParamter)
                .setTag("1")
                .build()
                .getObjectObservable(NetBean.class)
                .map(new Function<NetBean, List<NetBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean>>() {
                    @Override
                    public List<NetBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> apply(@NonNull NetBean netBean) throws Exception {
                        Log.e(TAG, "map: " + "处理====" + Thread.currentThread().getName());
                        return netBean.getShowapi_res_body().getPagebean().getContentlist();
                    }
                })
                .doOnNext(new Consumer<List<NetBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean>>() {
                    @Override
                    public void accept(List<NetBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlistBeen) throws Exception {
                        Log.e(TAG, "doOnNext: " + "保存====" + Thread.currentThread().getName());
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<NetBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean>>() {
                    @Override
                    public void accept(List<NetBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlistBeen) throws Exception {
                        Log.e(TAG, "subscribe: 成功====" + Thread.currentThread().getName());
                        textView.setText(contentlistBeen.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "subscribe: 失败====" + Thread.currentThread().getName());
                        textView.setText(throwable.getMessage());
                    }
                });
    }

    private boolean isFromNet = false;

    /**
     * 利用：操作符 concat 中，只有调用 onComplete 之后才会执行下一个 Observable的性质，可以先获取本地缓存，再进行网络请求
     */
    private void getDataFromCacheAndNet() {
        Observable<List<NetBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean>> cacheObservable = Observable.create(new ObservableOnSubscribe<List<NetBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<NetBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean>> e) throws Exception {
                String data = Helper.getData("data", "");
                // 在操作符 concat 中，只有调用 onComplete 之后才会执行下一个 Observable
                if (!TextUtils.isEmpty(data)) {
                    isFromNet = false;
                    Log.e(TAG, "subscribe: 有缓存");
                    e.onNext((List<NetBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean>) (new Gson().fromJson(data, new TypeToken<List<NetBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean>>() {
                    }.getType())));
                } else {
                    isFromNet = true;
                    Log.e(TAG, "subscribe: 无缓存");
                    e.onComplete();
                }
            }
        });

        Observable<List<NetBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean>> netObservable = Rx2AndroidNetworking.get(baiSiBuDeJieBase)
                .addQueryParameter(pathParamter)
                .build()
                .getObjectObservable(NetBean.class)
                .map(new Function<NetBean, List<NetBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean>>() {
                    @Override
                    public List<NetBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> apply(@NonNull NetBean netBean) throws Exception {
                        Log.e(TAG, "apply: 获得请求网络了的数据");
                        List<NetBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlist = netBean.getShowapi_res_body().getPagebean().getContentlist();
                        Helper.putData("data", new Gson().toJson(contentlist));
                        return contentlist;
                    }
                });

        Observable.concat(cacheObservable, netObservable)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<NetBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean>>() {
                    @Override
                    public void accept(List<NetBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlistBeen) throws Exception {
                        Log.e(TAG, "subscribe: 成功====" + Thread.currentThread().getName());
                        textView.setText(contentlistBeen.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "subscribe: 失败====" + Thread.currentThread().getName());
                        textView.setText(throwable.getMessage());
                    }
                });
    }


    /**
     * 先通过城市名称获取城市ID，再通过id获取对应数据
     */
    private void getDataAndAfter() {
        Rx2AndroidNetworking.get(base_url)
                .addPathParameter("path", idByAddress_url)
                .addQueryParameter(pathParamter)
                .addQueryParameter("area", "深圳")
                .build()
                .getObjectObservable(AddressBean.class)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<AddressBean, ObservableSource<WeatherBean>>() {
                    @Override
                    public ObservableSource<WeatherBean> apply(@NonNull AddressBean addressBean) throws Exception {
                        textView.setText(addressBean.toString());
                        //获取到地名的id
                        String areaid = addressBean.getShowapi_res_body().getList().get(0).getAreaid();
                        return Rx2AndroidNetworking.get(base_url)
                                .addPathParameter("path", weatherByAddressid_url)
                                .addQueryParameter(pathParamter)
                                .addQueryParameter("month", "201701")
                                .addQueryParameter("areaid", areaid)
                                .build()
                                .getObjectObservable(WeatherBean.class)
                                .subscribeOn(Schedulers.newThread());

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherBean>() {
                               @Override
                               public void accept(WeatherBean weatherBean) throws Exception {
                                   Log.e(TAG, "subscribe: 成功====" + Thread.currentThread().getName());
                                   textView.setText(weatherBean.toString());
                               }
                           }
                        , new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.e(TAG, "subscribe: 失败====" + Thread.currentThread().getName());
                                textView.setText(throwable.getMessage());
                            }
                        });

    }


    private void getAllMsg() {
        Observable<List<NetBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean>> bsbdjData = Rx2AndroidNetworking.get(baiSiBuDeJieBase)
                .addQueryParameter(pathParamter)
                .build()
                .getObjectObservable(NetBean.class)
                .map(new Function<NetBean, List<NetBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean>>() {
                    @Override
                    public List<NetBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> apply(@NonNull NetBean netBean) throws Exception {
                        Log.e(TAG, "apply: 获得请求网络了的数据");
                        List<NetBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlist = netBean.getShowapi_res_body().getPagebean().getContentlist();
                        return contentlist;
                    }
                });
        Observable<List<AddressBean.ShowapiResBodyBean.ListBean>> listObservable = Rx2AndroidNetworking.get(base_url)
                .addPathParameter("path", idByAddress_url)
                .addQueryParameter(pathParamter)
                .addQueryParameter("area", "深圳")
                .build()
                .getObjectObservable(AddressBean.class)
                .map(new Function<AddressBean, List<AddressBean.ShowapiResBodyBean.ListBean>>() {
                    @Override
                    public List<AddressBean.ShowapiResBodyBean.ListBean> apply(@NonNull AddressBean addressBean) throws Exception {
                        return addressBean.getShowapi_res_body().getList();
                    }
                });

        Observable
                .zip(bsbdjData, listObservable, new BiFunction<List<NetBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean>, List<AddressBean.ShowapiResBodyBean.ListBean>, String>() {
                    @Override
                    public String apply(@NonNull List<NetBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlistBeen, @NonNull List<AddressBean.ShowapiResBodyBean.ListBean> listBeen) throws Exception {
                        return "合并后数据：" + contentlistBeen.toString() + "/n" + listBeen.toString();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e(TAG, "subscribe: 成功====" + Thread.currentThread().getName());
                        textView.setText(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "subscribe: 失败====" + Thread.currentThread().getName());
                        textView.setText(throwable.getMessage());
                    }
                });
    }


}
