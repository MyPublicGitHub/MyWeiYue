package com.example.myweiyue.net;

/**
 * Created by 冯涛 on 2018/1/22.
 * E-mail:716774214@qq.com
 */

public class JanDanApi {

    public static JanDanApi sInstance;
    private static JanDanApiService mService;

    public JanDanApi(JanDanApiService janDanApiService) {
        mService = janDanApiService;
    }

    public static JanDanApi getInstance(JanDanApiService janDanApiService) {
        if (sInstance == null) {
            sInstance = new JanDanApi(janDanApiService);
        }
        return sInstance;
    }
}
