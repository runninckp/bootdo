package com.bootdo.xcx.controller;

import com.alibaba.fastjson.JSON;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.*;
import com.bootdo.xcx.domain.BrandDO;
import com.bootdo.xcx.domain.GoodsDO;
import com.bootdo.xcx.domain.SeriesDO;
import com.bootdo.xcx.service.BrandService;
import com.bootdo.xcx.service.GoodsService;
import com.bootdo.xcx.service.SeriesService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class OpenApiController  extends BaseController {
    @Autowired
    private DictService sysDictService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private SeriesService seriesService;
    /**
     * 1：获取所有基础数据
     * 完成
     * @return
     */
    @ResponseBody
    @GetMapping("/dict/getAllDict")
    Map getAllDict(){
        HashMap<String,Object> dictList= sysDictService.getAllDict();
        HashMap hashMap = new HashMap();
        hashMap.put("data",dictList);
        return R.ok(hashMap);
    }

    /**
     *:2：通过商品的uuid获取商品详细信息
     * @param uuid
     * @return
     */
    @ResponseBody
    @GetMapping("/goods/{uuid}")
    Map<String, Object> edit(@PathVariable("uuid") String uuid){
        GoodsDO goods = goodsService.get(uuid);
        HashMap hashMap = new HashMap();
        hashMap.put("data",goods);
        return R.ok(hashMap);
    }

    /**
     *:2：通过商品的id获取商品详细信息
     * @param id
     * @return
     * 完成
     */
    @ResponseBody
    @GetMapping("/brand/{id}")
    Map<String, Object> edit(@PathVariable("id") Long id){
        BrandDO brand = brandService.get(id);
        HashMap hashMap = new HashMap();
        hashMap.put("data",brand);
        return R.ok(hashMap);
    }

   /* *//**
     * 通过品牌id查询所有系列和系列下的类型
     * 品牌
     * 系列
     * 类型
     * 都需要传id
     *//*
    @ResponseBody
    @GetMapping("/goods/choiceGoods")
    public PageUtils choiceGoods(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<GoodsDO> goodsList = goodsService.list(query);
        int total = goodsService.count(query);
        PageUtils pageUtils = new PageUtils(goodsList, total);

        return pageUtils;
    }*/

    /**
     *:5：通过品牌，系列，类型，商品uuid 精确查询商品
     * 品牌
     * 系列
     * 类型
     * 都需要传id
     * 完成
     */
    @ResponseBody
    @PostMapping("/goods/choiceGoods")
    public R choiceGoods(@RequestParam Map<String, Object> params){
        //查询列表数据
        List<GoodsDO> goodsList = goodsService.list(params);
        HashMap hashMap = new HashMap();
        if(StringUtils.isNotEmpty(params.get("brandUuid").toString())&&
                StringUtils.isEmpty(params.get("type").toString())&&
                StringUtils.isEmpty(params.get("uuid").toString())&&
                StringUtils.isEmpty(params.get("seriesUuid").toString())){
            List<SeriesDO> series = seriesService.getSeriesByBrandUuid(params.get("brandUuid").toString());
          for(SeriesDO seriesDO:series){
              seriesDO.setGoodList(new ArrayList<>());
              for(GoodsDO goodsDO:goodsList){
                  if(seriesDO.getUuid().equals(goodsDO.getSeriesUuid())){
                      seriesDO.getGoodList().add(goodsDO);
                  }
              }
          }
            hashMap.put("data",series);
            return R.ok(hashMap);
        }
        hashMap.put("data",goodsList);
        return R.ok(hashMap);
    }





    /**
     *:3：页面模糊查询商品
     * 品牌
     * 系列
     * 类型
     * 都需要传id
     * 完成
     */
    @ResponseBody
    @PostMapping("/goods/searchGoods")
    public R searchGoods(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<GoodsDO> goodsList = goodsService.searchGoods(query);
        int total = goodsService.countSearch(query);
        PageUtils pageUtils = new PageUtils(goodsList, total);
        HashMap hashMap = new HashMap();
        hashMap.put("data",pageUtils);

        return R.ok(hashMap);
    }

    /**
     * 4：通过英语/拼音查询所有的品牌
     * 完成
     */
    @ResponseBody
    @PostMapping("/brand/query")
    public Map queryBrand(@RequestParam Map<String, Object> params){
        //查询列表数据
        if(StringUtils.isEmpty(params.get("sort").toString())){
            return R.error();
        }
        List<BrandDO> goodsList = brandService.queryBrand(params);
        Map Map =Sort.sortBrand(goodsList,params.get("sort").toString());
        return R.ok(Map);
    }

}
