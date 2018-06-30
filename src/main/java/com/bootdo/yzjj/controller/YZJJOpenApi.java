package com.bootdo.yzjj.controller;

import com.alibaba.fastjson.JSON;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.redis.JedisUtil;
import com.bootdo.common.utils.R;
import com.bootdo.yzjj.domain.AdvertDO;
import com.bootdo.yzjj.domain.GoldDO;
import com.bootdo.yzjj.domain.RemindDO;
import com.bootdo.yzjj.domain.SubscribeDO;
import com.bootdo.yzjj.service.AdvertService;
import com.bootdo.yzjj.service.GoldService;
import com.bootdo.yzjj.service.RemindService;
import com.bootdo.yzjj.service.SubscribeService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/yzjj/api")
public class YZJJOpenApi extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(YZJJOpenApi.class);
    @Autowired
    private GoldService goldService;
    @Autowired
    private AdvertService advertService;
    @Autowired
    private RemindService remindService;
    @Autowired
    private SubscribeService subscribeService;
    @Autowired
    private JedisUtil jedisUtil;
    /**
     * 获取最新金价数据
     */
    @ResponseBody
    @GetMapping("/getLatest")
    public R getLatest(){

        GoldDO goldDO = goldService.getLatest();
        HashMap hashMap = new HashMap();
        hashMap.put("data",goldDO);
        return R.ok(hashMap);
    }

    /**
     * 广告查询
     * 1是图片
     * 2是文字广告
     */
    @ResponseBody
    @PostMapping("/getAd")
    public R getAd(String type){
        AdvertDO advertDO = advertService.getAd(type);
        HashMap hashMap = new HashMap();
        hashMap.put("data",advertDO);
        return R.ok(hashMap);
    }

    /**
     * 保存贵金属提醒
     * 金gold  白金bjGold   白银byGold   香港金hkGold   伦敦金londonGold
     * @param map
     * @return
     */
    @ResponseBody
    @PostMapping("/saveRemind")
    @Transactional
    public R saveRemind(@RequestParam Map<String, Object> map)throws Exception {
        logger.trace(" 保存贵金属提醒："+ JSON.toJSONString(map));
        String thirdSessionKey = (String) map.get("thirdSessionKey");
        String openid = getOpenidByThirdSessionKey(thirdSessionKey);
        List<RemindDO> list = (List<RemindDO>) map.get("list");
        for (RemindDO remindDO : list) {
            remindDO.setOpenid(openid);
            remindDO.setStatus("1");
            remindDO.setCreated(getUserId());
            remindDO.setModified(getUserId());
            remindDO.setGtmCreate(new Date());
            remindDO.setGtmModified(new Date());
            if (remindService.save(remindDO) <= 0) {
                return R.error("操作失败");
            }
        }
        return R.ok();
    }

    /**
     * 获取贵金属提醒
     * @param map
     * @return
     */
    @ResponseBody
    @PostMapping("/getRemind")
    @Transactional
    public R getRemind(@RequestParam Map<String, Object> map)throws Exception {
        logger.trace("获取贵金属提醒："+ JSON.toJSONString(map));
        String thirdSessionKey = (String) map.get("thirdSessionKey");
        String openid =thirdSessionKey;// getOpenidByThirdSessionKey(thirdSessionKey);
        List<RemindDO> remindDOS = remindService.getByOpenid(openid);
        HashMap hashMap = new HashMap();
        hashMap.put("data",remindDOS);
        return R.ok(hashMap);
    }

    /**
     * 获取一周订阅时间信息
     * @param map
     * @return
     */
    @ResponseBody
    @PostMapping("/getSubscribe")
    @Transactional
    public R getSubscribe(@RequestParam Map<String, Object> map)throws Exception {
        logger.trace("获取一周订阅时间信息："+ JSON.toJSONString(map));
        String thirdSessionKey = (String) map.get("thirdSessionKey");
        String openid = thirdSessionKey;//getOpenidByThirdSessionKey(thirdSessionKey);
        List<SubscribeDO> subscribeDOS = subscribeService.getByOpenid(openid);
        HashMap hashMap = new HashMap();
        hashMap.put("data",subscribeDOS);
        return R.ok(hashMap);
    }




    /**
     *
     * 保存订阅信息
     * @param map
     * @return
     */
    @ResponseBody
    @PostMapping("/saveSubscribe")
    @Transactional
   public R save( @RequestParam Map<String, Object> map)throws Exception{
        logger.trace("保存订阅信息ss："+ JSON.toJSONString(map));
        String thirdSessionKey = (String) map.get("thirdSessionKey");
        String openid = getOpenidByThirdSessionKey(thirdSessionKey);
        List<SubscribeDO> list = (List<SubscribeDO>) map.get("list");
        for (SubscribeDO subscribe:list){
            subscribe.setOpenid(openid);
            subscribe.setCreated(getUserId());
            subscribe.setStatus("1");
            subscribe.setModified(getUserId());
            subscribe.setGtmCreate(new Date());
            subscribe.setGtmModified(new Date());
            if(subscribeService.save(subscribe)<=0){
                return R.error("操作失败");
            }
        }

        return R.ok();
    }
    /**
     * 通过thirdSessionKey 获取缓存获取openid
     */
    private String getOpenidByThirdSessionKey(String thirdSessionKey) throws Exception{
        String sb = jedisUtil.getByKey(thirdSessionKey);
        if (StringUtils.isNotEmpty(sb)){
            String[] sbArray = sb.split("#");
            return  sbArray[1];
        }
        throw new Exception("请重新登陆！");
    }



}
