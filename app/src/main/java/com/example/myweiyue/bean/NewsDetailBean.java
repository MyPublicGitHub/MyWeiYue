package com.example.myweiyue.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 冯涛 on 2018/1/24.
 * E-mail:716774214@qq.com
 */

public class NewsDetailBean implements Serializable {
    @Override
    public String toString() {
        return "NewsDetailBean{" +
                "listId='" + listId + '\'' +
                ", type='" + type + '\'' +
                ", expiredTime=" + expiredTime +
                ", currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", topsize=" + topsize +
                ", item=" + item +
                '}';
    }

    /**
     * http://api.iclient.ifeng.com/ClientNews?id=SYLB10,SYDT10&action=default&pullNum=1
     * **************************************************************************************************
     * listId : SYLB10NEW
     * type : list
     * expiredTime : 180000
     * currentPage : 1
     * totalPage : 27
     * topsize : 0
     * item : [{"type":"doc","thumbnail":"http://d.ifengimg.com/w230_h152_q100/p0.ifengimg.com/pmop/2017/1229/4A85A81B2EAE11DD7220F135626220A1005D99B8_size700_w560_h314.gif","title":"能上天可下海 这个大家伙将成中国南海维权利器","showType":"0","updateTime":"2018/01/03 10:28:52","id":"http://api.3g.ifeng.com/ipadtestdoc?aid=cmpp_030010054646328&channelKey=Y21wcF8xNzAwN183MTlfNzU=&channelid=SYLB10NEW","documentId":"cmpp_030010054646328","staticId":"cmpp_030010054646328","style":{"backreason":["不感兴趣","内容质量差","看过了","不感兴趣"],"view":"titleimg"},"hasVideo":true,"commentsUrl":"sub_43011572","comments":"53","commentsall":"123","link":{"type":"doc","url":"http://api.3g.ifeng.com/ipadtestdoc?aid=cmpp_030010054646328&channelKey=Y21wcF8xNzAwN183MTlfNzU=&channelid=SYLB10NEW","weburl":"http://m.ifeng.com/sharenews.f?ch=qd_sdk_dl1&aid=030010054646328"},"reftype":"editor||"},{"type":"doc","thumbnail":"http://d.ifengimg.com/w230_h152_q100/p0.ifengimg.com/pmop/2017/1228/9B853975AE702553D779C7224A7112CDBBBFF336_size47_w640_h574.jpeg","title":"德国大使在南京大屠杀纪念日还干过这么恶心人的事","showType":"0","source":"环球时报","subscribe":{"cateid":"环球时报","type":"source","catename":"环球时报","description":""},"updateTime":"2017/12/29 12:45:57","id":"http://api.3g.ifeng.com/ipadtestdoc?aid=cmpp_034470054617408&channelKey=Y21wcF8xNzAwN183MTlfNzU=&channelid=SYLB10NEW","documentId":"cmpp_034470054617408","staticId":"cmpp_034470054617408","style":{"backreason":["不感兴趣","不想看:环球时报","内容质量差","看过了","不感兴趣"],"view":"titleimg"},"hasVideo":true,"commentsUrl":"sub_42900331","comments":"3308","commentsall":"30217","link":{"type":"doc","url":"http://api.3g.ifeng.com/ipadtestdoc?aid=cmpp_034470054617408&channelKey=Y21wcF8xNzAwN183MTlfNzU=&channelid=SYLB10NEW","weburl":"http://m.ifeng.com/sharenews.f?ch=qd_sdk_dl1&aid=034470054617408"},"reftype":"editor||"},{"type":"doc","thumbnail":"http://d.ifengimg.com/w230_h152_q100/p2.ifengimg.com/cmpp/2017/12/29/fc7b5c7284a440ec08369759eaffe25c_size65_w230_h152.png","title":"余光中告别式今日举行 马英九吴敦义送别","showType":"0","source":"中国台湾网","subscribe":{"cateid":"中国台湾网","type":"source","catename":"中国台湾网","description":""},"updateTime":"2017/12/29 15:52:50","id":"http://api.3g.ifeng.com/ipadtestdoc?aid=cmpp_030220054647378&channelKey=Y21wcF8xNzAwN183MTlfNzU=&channelid=SYLB10NEW","documentId":"cmpp_030220054647378","staticId":"cmpp_030220054647378","style":{"backreason":["不感兴趣","不想看:中国台湾网","内容质量差","看过了","不感兴趣"],"view":"titleimg"},"commentsUrl":"http://news.ifeng.com/a/20171229/54647378_0.shtml","comments":"68","commentsall":"434","link":{"type":"doc","url":"http://api.3g.ifeng.com/ipadtestdoc?aid=cmpp_030220054647378&channelKey=Y21wcF8xNzAwN183MTlfNzU=&channelid=SYLB10NEW","weburl":"http://m.ifeng.com/sharenews.f?ch=qd_sdk_dl1&aid=030220054647378"},"reftype":"editor||"},{"type":"slide","thumbnail":"http://d.ifengimg.com/w230_h152_q100/p0.ifengimg.com/pmop/2017/1229/A6C441BD3F4BB07123E5BA2E5AE52FE0FDB42FD0_size72_w641_h344.jpeg","title":"农民乔迁新屋大手笔 连吃2天还能打包","showType":"0","updateTime":"2017/12/29 14:01:47","id":"http://api.3g.ifeng.com/ipadtestdoc?aid=cmpp_030240054640371&channelid=SYLB10NEW","documentId":"cmpp_030240054640371","staticId":"cmpp_030240054640371","style":{"backreason":["不感兴趣","内容质量差","看过了","不感兴趣"],"type":"slides","images":["http://d.ifengimg.com/w230_h152_q100/p0.ifengimg.com/pmop/2017/1229/A6C441BD3F4BB07123E5BA2E5AE52FE0FDB42FD0_size72_w641_h344.jpeg","http://d.ifengimg.com/w230_h152_q100/p0.ifengimg.com/pmop/2017/1229/8B72D42B7EC922DD3610341003F331D3C0BF2D3D_size78_w641_h481.jpeg","http://d.ifengimg.com/w230_h152_q100/p0.ifengimg.com/pmop/2017/1229/4DBD1EA9C4369B6322DE5BFE2111C702D97E54F3_size67_w641_h481.jpeg"],"slideCount":9,"view":"slideimg"},"hasSlide":true,"commentsUrl":"sub_42991935","comments":"96","commentsall":"265","link":{"type":"slide","url":"http://api.3g.ifeng.com/ipadtestdoc?aid=cmpp_030240054640371&channelid=SYLB10NEW","weburl":"http://m.ifeng.com/sharenews.f?ch=qd_sdk_dl1&aid=030240054640371"},"reftype":"editor||"},{"type":"doc","thumbnail":"http://d.ifengimg.com/w230_h152_q100/p1.ifengimg.com/cmpp/2017/12/29/a8facb4af83ca1902b32bfba4ef67ea8_size60_w230_h152.webp","title":"尼泊尔不跟印度一起测珠峰，怎么又成了中国的错？","showType":"0","source":"环球网","subscribe":{"cateid":"环球网","type":"source","catename":"环球网","description":""},"updateTime":"2017/12/29 10:48:05","id":"http://api.3g.ifeng.com/ipadtestdoc?aid=cmpp_030180054630928&channelKey=Y21wcF8xNzAwN183MTlfNzU=&channelid=SYLB10NEW","documentId":"cmpp_030180054630928","staticId":"cmpp_030180054630928","style":{"backreason":["不感兴趣","不想看:环球网","内容质量差","看过了","不感兴趣"],"view":"titleimg"},"commentsUrl":"http://news.ifeng.com/a/20171229/54630928_0.shtml","comments":"400","commentsall":"1421","link":{"type":"doc","url":"http://api.3g.ifeng.com/ipadtestdoc?aid=cmpp_030180054630928&channelKey=Y21wcF8xNzAwN183MTlfNzU=&channelid=SYLB10NEW","weburl":"http://m.ifeng.com/sharenews.f?ch=qd_sdk_dl1&aid=030180054630928"},"reftype":"editor||"},{"type":"doc","thumbnail":"http://d.ifengimg.com/w230_h152_q100/p0.ifengimg.com/cmpp/2017/12/28/ed83fadd623c6e1aba5cb31d99c99e9e_size48_w230_h152.jpg","title":"知名地产商李贵斌病逝 央视女主播百亿家产惨遭清零","showType":"0","source":"等深线综合","subscribe":{"cateid":"等深线综合","type":"source","catename":"等深线综合","description":""},"updateTime":"2017/12/29 10:01:33","id":"http://api.3g.ifeng.com/ipadtestdoc?aid=cmpp_034470054619779&channelKey=Y21wcF8xNzAwN183MTlfNzU=&channelid=SYLB10NEW","documentId":"cmpp_034470054619779","staticId":"cmpp_034470054619779","style":{"backreason":["不感兴趣","不想看:等深线综合","内容质量差","看过了","不感兴趣"],"view":"titleimg"},"commentsUrl":"http://news.ifeng.com/a/20171228/54619779_0.shtml","comments":"1044","commentsall":"6306","link":{"type":"doc","url":"http://api.3g.ifeng.com/ipadtestdoc?aid=cmpp_034470054619779&channelKey=Y21wcF8xNzAwN183MTlfNzU=&channelid=SYLB10NEW","weburl":"http://m.ifeng.com/sharenews.f?ch=qd_sdk_dl1&aid=034470054619779"},"reftype":"editor||"},{"type":"doc","thumbnail":"http://d.ifengimg.com/w230_h152_q100/img1.ugc.ifeng.com/newugc/20171228/18/wemedia/070188ffdef91252bcb08d169502cfd83e3c65c5_size145_w750_h320.jpg","title":"感觉嘴苦都是因为啥？","showType":"0","updateTime":"2017/12/29 08:59:37","id":"http://api.3g.ifeng.com/ipadtestdoc?aid=cmpp_034470054629786&channelKey=Y21wcF8xNzAwN183MTlfNzU=&channelid=SYLB10NEW","documentId":"cmpp_034470054629786","staticId":"cmpp_034470054629786","style":{"backreason":["不感兴趣","内容质量差","看过了","不感兴趣"],"view":"titleimg"},"commentsUrl":"sub_42894756","comments":"69","commentsall":"149","link":{"type":"doc","url":"http://api.3g.ifeng.com/ipadtestdoc?aid=cmpp_034470054629786&channelKey=Y21wcF8xNzAwN183MTlfNzU=&channelid=SYLB10NEW","weburl":"http://m.ifeng.com/sharenews.f?ch=qd_sdk_dl1&aid=034470054629786"},"reftype":"editor||"},{"type":"slide","thumbnail":"http://d.ifengimg.com/w696_h392_q100/p2.ifengimg.com/cmpp/2017/12/29/c1bd6cf57d8e432f307ed20be7da758c_size634_w750_h376.jpg","title":"俄美女主持成立喀山总统竞选办公室","showType":1,"source":"凤凰图片","subscribe":{"cateid":"凤凰图片","type":"source","catename":"凤凰图片","description":""},"updateTime":"2017/12/29 07:40:08","id":"http://api.3g.ifeng.com/ipadtestdoc?aid=cmpp_030240054629810&channelid=SYLB10NEW","documentId":"cmpp_030240054629810","staticId":"cmpp_030240054629810","style":{"view":"bigimg","backreason":["不感兴趣","不想看:凤凰图片","内容质量差","旧闻、重复","标题党"],"type":"slides","images":["http://d.ifengimg.com/w230_h152_q100/p1.ifengimg.com/cmpp/2017_52/d92fddbc6e3e0b0_w1024_h683.jpg","http://d.ifengimg.com/w230_h152_q100/p1.ifengimg.com/cmpp/2017_52/fe53cf6ba6b7047_w1024_h683.jpg","http://d.ifengimg.com/w230_h152_q100/p1.ifengimg.com/cmpp/2017_52/0efd1e7d4709f10_w1024_h683.jpg"],"slideCount":3},"hasSlide":true,"commentsUrl":"http://news.ifeng.com/a/20171229/54629810_0.shtml","comments":"24","commentsall":"69","link":{"type":"slide","url":"http://api.3g.ifeng.com/ipadtestdoc?aid=cmpp_030240054629810&channelid=SYLB10NEW","weburl":"http://m.ifeng.com/sharenews.f?ch=qd_sdk_dl1&aid=030240054629810"},"reftype":"editor||"}]
     */
    public String listId;
    public String type;
    public int expiredTime;
    public int currentPage;
    public int totalPage;
    public int topsize;
    public List<ItemBean> item;

    public static class ItemBean implements Serializable, MultiItemEntity {
        //广告类型
        public static final int TYPE_ADVERT_TITLEIMG = 1;

        public static final int TYPE_ADVERT_SLIDEIMG = 2;

        public static final int TYPE_ADVERT_LONGIMG = 3;
        //图片类型
        public static final int TYPE_SLIDE = 4;
        //视频类型
        public static final int TYPE_PHVIDEO = 5;
        //显示形式单图
        public static final int TYPE_DOC_TITLEIMG = 6;
        //显示形式多图
        public static final int TYPE_DOC_SLIDEIMG = 7;
        @Override
        public String toString() {
            return "ItemBean{" +
                    "type='" + type + '\'' +
                    ", thumbnail='" + thumbnail + '\'' +
                    ", title='" + title + '\'' +
                    ", showType='" + showType + '\'' +
                    ", source='" + source + '\'' +
                    ", subscribe=" + subscribe +
                    ", updateTime='" + updateTime + '\'' +
                    ", id='" + id + '\'' +
                    ", documentId='" + documentId + '\'' +
                    ", staticId='" + staticId + '\'' +
                    ", style=" + style +
                    ", commentsUrl='" + commentsUrl + '\'' +
                    ", comments='" + comments + '\'' +
                    ", commentsall='" + commentsall + '\'' +
                    ", link=" + link +
                    ", reftype='" + reftype + '\'' +
                    ", hasSlide=" + hasSlide +
                    '}';
        }
        public int itemType;//为了辨别item自己加的属性
        public String type;
        public String thumbnail;
        public String title;
        public String showType;
        public String updateTime;
        public String id;
        public String documentId;
        public String staticId;
        public StyleBean style;
        public boolean hasVideo;
        public String commentsUrl;
        public String comments;
        public String commentsall;
        public LinkBean link;
        public String reftype;
        public String source;
        public SubscribeBean subscribe;
        public boolean hasSlide;

        @Override
        public int getItemType() {
            return itemType;
        }

        public static class StyleBean {
            public List<String> images;
            public String view;
            public List<String> backreason;
        }

        public static class LinkBean {

            public String type;
            public String url;
            public String weburl;
        }

        public static class SubscribeBean {

            public String cateid;
            public String type;
            public String catename;
            public String description;
        }
    }
}
