package com.lexue.base.domain;


import com.lexue.base.annotation.mybatis.MyColumn;
import com.lexue.base.annotation.mybatis.MyId;
import com.lexue.base.annotation.mybatis.MyTable;
import lombok.Data;

import java.util.List;

/**
 * author lilong
 */
@Data
@MyTable("video")
public class Video {

    @MyId("video_id")
    private String id;
    //视频标题
    @MyColumn("video_title")
    private String title;
    //视频标题 n保持前端自动填充
    @MyColumn("teacher_name")
    private String teacherName;
    @MyColumn("teacher_id")
    private String teacherId;
    //视频封面图片
    @MyColumn("video_cover")
    private String cover;
    //视频封面图片url 页面显示使用不存库
    private String coverJson;
    @MyColumn("user_collect")
    private int collect;
    @MyColumn("user_praise")
    private int praise;
    @MyColumn("user_critical")
    private int critical;
    //起始热度
    @MyColumn("watcher_count")
    private int watcherCount;
    //视频时长
    @MyColumn("video_duration")
    private long duration;
    //视频介绍
    @MyColumn("video_description")
    private String description;
    //视频评级 1星
    @MyColumn("video_rate")
    private int rate;

    //默认大招
    private String videoType="1";

    //交谈价格
    @Deprecated
    private int chatPrice;
    //视频名称
    private String realId;
    //预览视频名称
    private String previewId;
    //教学科目
    private String subject;
    /**
     * 默认出的例题的数目是5，不是习题的数目
     */
    private int questionNum=5;
    private int isBanner = 0;
       //临时助教id
    private String assistantId;
    //正文
    private String content;

    private String editorMark="0";

    /**
     * 开始这个值是放在sub_video.video_id的，表示父视频
     * 从20151223起，这个值会放在video_relation表里，type为1，代表父子视频关系
     */
    private String parentId;
    private String parentTitle;

    private long updateTime;
    //乐学币 原价
    private String origPrice = "0";
    //乐学币 折后价， 折后价为0在discount>0的情况下，才代表折后价为0，否则当discountStart=0的情况下，代表无折扣即原价
    private String price="0";

    //乐砖 原价 orig_price
    private String originalCost = "0";
    //乐砖 折后价 video_price  折后价为0在discount>0的情况下，才代表折后价为0，否则当discountStart=0的情况下，代表无折扣即原价
    private String currentPrice="0";

    // 折扣开始时间
    private String discountStartTime;
    // 折扣结束时间
    private String discountEndTime;

    //折扣进行的三种状态(还未开始，已经结束，促销进行中)，需要根据当前时间进行判断的值，不保存到数据库中
    private String discountState;

    // 支付方式 0币1钻2免费，要用原价判断
    private String paymentType;

    private int knowledgeExpectRate;
    private String knowledgeQuestionsJson;

    private String forward;
    private String forwardNote;
    private String forwardType;

    private long createTime;

    private int boughtCount;
    private int finishCount;

    private String tagStr;
    //	update by pengsong 2016.06.16
//	视频的有效期，单位是秒，默认值是购买日起90天即7776000秒
    private int expiration;
    //	视频下载地址
    private String downloadUrl;
    //  是否是加密视频
    private int encodeFlag;
    private String flvRealId;
    //下载讲义名称
    private String downloadName;

    //	年卡增加 20161102 by pengsong
    private String mallIds;
    private List mallList;

    private String client;

    private String schema;

    private String downloadFlag="1";

    //	版本2.2.3新增
    private int phase;
    private String pointId;
    private String subPointId;

    private String pointIdCopy;
    private String subPointIdCopy;
    private int videoType2=1;

    private String onlineTime=0+"";
    private long editorTime;
}