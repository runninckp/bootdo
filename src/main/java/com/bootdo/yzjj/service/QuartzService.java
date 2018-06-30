package com.bootdo.yzjj.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateMessage;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.yzjj.domain.AdvertDO;
import com.bootdo.yzjj.domain.GoldDO;
import com.bootdo.yzjj.domain.RemindDO;
import com.bootdo.yzjj.domain.SubscribeDO;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class QuartzService {

    @Autowired
    private GoldService goldService;
    @Autowired
    private SubscribeService subscribeService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Inject
    private WxMaService wxService;
    @Autowired
    private AdvertService advertService;
    @Autowired
    private RemindService remindService;

    /***
     * 1:一周时间提醒的模板：Wpfnw1-y5UDeJv5Jkl2f-7Nql5B_C3LCcxZBymMmEtU
     2：涨跌模板：Wpfnw1-y5UDeJv5Jkl2f-xXo81IpBiFgdsoRf8ki268
     */
    private final static String TEMPLE_WEEK = "Wpfnw1-y5UDeJv5Jkl2f-7Nql5B_C3LCcxZBymMmEtU";
    private final static String TEMPLE_UPANDDOWN = "Wpfnw1-y5UDeJv5Jkl2f-xXo81IpBiFgdsoRf8ki268";

    private static ArrayList<String> gold = new ArrayList<String>(Arrays.asList("gold", "byGold", "bjGold", "hkGold", "londonGold"));

    /**
     * 0 * * * * ? 每1分钟触发一次
     * 0 0 * * * ? 每天每1小时触发一次
     * 0 0 10 * * ? 每天10点触发一次
     * 0 * 14 * * ? 在每天下午2点到下午2:59期间的每1分钟触发
     * 0 30 9 1 * ? 每月1号上午9点半
     * 0 15 10 15 * ? 每月15日上午10:15触发
     */

//    每分钟启动
    //@Scheduled(cron = "*/2 * * * * ?")
    public void timerToNow() {
        System.out.println("now time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        messagingTemplate.convertAndSend("/topic/subscribeLatest", R.ok(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
    }

    //更新最新 每3秒执行一次
   // @Scheduled(cron = "*/6 * * * * ?")
    public void updateJob() throws Exception {
        System.out.println("now time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        GoldDO goldDO = goldService.getLatest();
        subscribeLatest(goldDO);
        subscribeJob(goldDO);
    }


    //客户端只要订阅了/topic/subscribeTest主题，调用这个方法即可
    public void subscribeLatest(GoldDO goldDO) {
        HashMap hashMap = new HashMap();
        hashMap.put("data", goldDO);
        messagingTemplate.convertAndSend("/topic/subscribeLatest", R.ok(hashMap));
    }

    //订阅任务 每天10:30执行
    @Scheduled(cron = "0 30 10 * * ?")
    public void subscribeWeekJob() throws Exception {
        HashMap map = new HashMap();
        map.put("subtime", DateUtils.format(new Date()));
        map.put("status", "1");
        GoldDO goldDO = goldService.getLatest();
        AdvertDO advertDO = advertService.getAd("2");
        List<SubscribeDO> subscribeDOS = subscribeService.list(map);
        for (SubscribeDO subDO : subscribeDOS) {
            WxMaTemplateMessage templateMessage = WxMaTemplateMessage.builder()
                    .toUser(subDO.getOpenid())
                    .formId(subDO.getPushcode())
                    .page("index")
                    .data(Lists.newArrayList(
                            new WxMaTemplateMessage.Data("keyword1", goldDO.getGoldSale(), "#173177"),
                            new WxMaTemplateMessage.Data("keyword2", goldDO.getByGoldSale(), "#173177"),
                            new WxMaTemplateMessage.Data("keyword3", goldDO.getBjGoldSale(), "#173177"),
                            new WxMaTemplateMessage.Data("keyword4", goldDO.getHkGoldSale(), "#173177"),
                            new WxMaTemplateMessage.Data("keyword5", goldDO.getLondonGoldSale(), "#173177"),
                            new WxMaTemplateMessage.Data("keyword6", DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"), "#173177"),
                            new WxMaTemplateMessage.Data("keyword7", advertDO.getTitle(), "#173177")
                    ))
                    .templateId(TEMPLE_WEEK)
                    .emphasisKeyword("keyword1.DATA")
                    .build();
            //templateMessage.addData( new WxMaTemplateMessage.Data("keyword1", "339208499", "#173177"));
            this.wxService.getMsgService().sendTemplateMsg(templateMessage);
            subDO.setStatus("0");
            subscribeService.update(subDO);
        }
    }

    //订阅任务
    public void subscribeJob(GoldDO goldDO) throws Exception {
        HashMap map = new HashMap();
        map.put("subtime", DateUtils.format(new Date()));
        map.put("status", "1");
        List<RemindDO> remindDOS = remindService.list(map);
        AdvertDO advertDO = advertService.getAd("2");
        Class<?> cl = goldDO.getClass();
        for (RemindDO remindDO : remindDOS) {

            if (!gold.contains(remindDO.getType())) {
                continue;
            }

            if ("1".equals(remindDO.getChange())) {
                Method goldHighMethod = cl.getDeclaredMethod("get" + remindDO.getType() + "High");
                double valueHigh = getDoubleByString(goldHighMethod, goldDO);
                if (!(NumberUtils.compare(valueHigh, Double.parseDouble(remindDO.getPrice())) == 1)) {
                    continue;
                }
            }
            if ("-1".equals(remindDO.getChange())) {
                Method goldLowMethod = cl.getDeclaredMethod("get" + remindDO.getType() + "Low");
                double valueLow = getDoubleByString(goldLowMethod, goldDO);
                if (!(NumberUtils.compare(valueLow, Double.parseDouble(remindDO.getPrice())) == -1)) {
                    continue;
                }
            }

            Method goldSaleMethod = cl.getDeclaredMethod("get" + remindDO.getType() + "Sale");
            String valueSale = (String) goldSaleMethod.invoke(goldDO);

            WxMaTemplateMessage templateMessage = WxMaTemplateMessage.builder()
                    .toUser(remindDO.getOpenid())
                    .formId(remindDO.getPushcode())
                    .page("index")
                    .data(Lists.newArrayList(
                            new WxMaTemplateMessage.Data("keyword1", remindDO.getType(), "#173177"),
                            new WxMaTemplateMessage.Data("keyword2", valueSale, "#173177"),
                            new WxMaTemplateMessage.Data("keyword3", DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"), "#173177"),
                            new WxMaTemplateMessage.Data("keyword4", advertDO.getTitle(), "#173177")
                    ))
                    .templateId(TEMPLE_UPANDDOWN)
                    .emphasisKeyword("keyword1.DATA")
                    .build();
            //templateMessage.addData( new WxMaTemplateMessage.Data("keyword1", "339208499", "#173177"));
            this.wxService.getMsgService().sendTemplateMsg(templateMessage);


        }
    }


    private double getDoubleByString(Method method, GoldDO goldDO) throws Exception {
        String value = (String) method.invoke(goldDO);
        if (StringUtils.isNotEmpty(value)) {
            return Double.parseDouble(value);
        }
        return 0.0;
    }


    //客户端只要订阅了/topic/subscribeTest主题，调用这个方法即可
   /* public void templateTest() {
        messagingTemplate.convertAndSend("/topic/subscribeTest", "服务器主动推的数据");
    }*/

}