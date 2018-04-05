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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/api")
public class OpenApiController  extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(OpenApiController.class);
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



  /*  public void ckpckpckp2(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("status","1");
        List<GoodsDO> DBGoods = goodsService.list(map);

        for(GoodsDO goodsDO:DBGoods){

        }

    }

    public void ckpckpckp(){
        List<BrandDO> brandlist = null;

        List<SeriesDO> serieslist = null;
        List<GoodsDO> goodslist = null;
        FileInputStream brandfis = null;
        FileInputStream seriesfis = null;
        FileInputStream goodsfis = null;
        try {

            //品牌
            brandfis = new FileInputStream("F:\\data\\brandDO3.xls");
            ExcelUtil<BrandDO> util1 = new ExcelUtil<BrandDO>(BrandDO.class);// 创建excel工具类
            brandlist = util1.importExcel("品牌管理", brandfis);// 导入
            //系列
            seriesfis = new FileInputStream("F:\\data\\series2.xls");
            ExcelUtil<SeriesDO> util2 = new ExcelUtil<SeriesDO >(SeriesDO.class);// 创建excel工具类
            serieslist = util2.importExcel("Sheet1", seriesfis);// 导入
            //商品
            goodsfis = new FileInputStream("F:\\data\\goods2.xls");
            ExcelUtil<GoodsDO> util3 = new ExcelUtil<GoodsDO>(GoodsDO.class);// 创建excel工具类
            goodslist = util3.importExcel("Sheet1", goodsfis);// 导入

           *//* for (BrandDO brandDO:brandlist){
                brandDO.setGtmCreate(new Date());
                brandDO.setGtmModified(new Date());
                brandDO.setCreated(new Long(1));
                brandDO.setModified(new Long(1));
                brandDO.setStatus(1);
                brandService.save(brandDO);
            }*//*
            HashMap<String,Object> map = new HashMap<>();
            map.put("status","1");
            List<BrandDO> DBBrand = brandService.list(map);

           // System.out.println("所有的品牌"+JSON.toJSONString(DBBrand));

            for (SeriesDO seriesDO:serieslist){
                for (BrandDO dbB:DBBrand){
                    if (seriesDO.getBrandId().equals(dbB.getCname())){
                        seriesDO.setBrandId(dbB.getUuid());
                        seriesDO.setGtmCreate(new Date());
                        seriesDO.setGtmModified(new Date());
                        seriesDO.setCreated(new Long(1));
                        seriesDO.setModified(new Long(1));
                        seriesDO.setStatus(1);
                        seriesService.save(seriesDO);
                        continue;
                    }
                }

            }

            HashMap<String,Object> map33 = new HashMap<>();
            map33.put("status","1");
            List<SeriesDO> DBSeries = seriesService.list(map33);

         *//*   System.out.println("所有的系列"+JSON.toJSONString(DBSeries));*//*

            int num =0;
            for (GoodsDO good:goodslist ) {
               for (BrandDO brandDO:DBBrand){
                    if (brandDO.getCname().equals(good.getBrand())){
                        good.setBrandUuid(brandDO.getUuid());
                        good.setBrand(brandDO.getCname());
                    }
                }
                for (SeriesDO seriesDO:DBSeries){
                    if (seriesDO.getCname().equals(good.getSeries())&&good.getBrandUuid().equals(seriesDO.getBrandId())){
                        good.setSeriesUuid(seriesDO.getUuid());
                        good.setSeries(seriesDO.getCname());

                    }
                }

                if (good.getSeriesUuid()!=null&&good.getBrandUuid()!=null){
                    good.setGtmCreate(new Date());
                    good.setGtmModified(new Date());
                    good.setCreated(new Long(1));
                    good.setModified(new Long(1));
                    good.setStatus(1);
                    good.setType("10");
                    goodsService.save(good);
                    num++;
                }else {
                    System.out.println(JSON.toJSONString(good));
                }
            }
            System.out.println("一共插入："+num);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public static void main1(String[] args){
        List<BrandDO> brandlist = null;

        List<SeriesDO> serieslist = null;
        List<GoodsDO> goodslist = null;
        FileInputStream brandfis = null;
        FileInputStream seriesfis = null;
        FileInputStream goodsfis = null;
        try {

            //品牌
            brandfis = new FileInputStream("F:\\data\\brandDO3.xls");
            ExcelUtil<BrandDO> util1 = new ExcelUtil<BrandDO>(BrandDO.class);// 创建excel工具类
            brandlist = util1.importExcel("品牌管理", brandfis);// 导入
            //系列
            seriesfis = new FileInputStream("F:\\data\\series.xls");
            ExcelUtil<SeriesDO> util2 = new ExcelUtil<SeriesDO >(SeriesDO.class);// 创建excel工具类
            serieslist = util2.importExcel("Sheet1", seriesfis);// 导入
            //商品
            goodsfis = new FileInputStream("F:\\data\\goods.xls");
            ExcelUtil<GoodsDO> util3 = new ExcelUtil<GoodsDO>(GoodsDO.class);// 创建excel工具类
            goodslist = util3.importExcel("Sheet1", goodsfis);// 导入
*//*
            System.out.println(JSON.toJSONString(brandlist));
            System.out.println(JSON.toJSONString(serieslist));
            System.out.println(JSON.toJSONString(goodslist));*//*




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }
*/
    /**
     *:2：通过商品的uuid获取商品详细信息
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("/goods/getGood")
    Map<String, Object> getGood(@RequestParam Map<String, Object> params){
        logger.trace(" 通过商品的uuid获取商品详细信息："+JSON.toJSONString(params));
        if(params==null||params.get("uuid")==null){
            return R.error();
        }
        String uuid = params.get("uuid").toString();
        GoodsDO goods = goodsService.get(uuid);
        HashMap hashMap = new HashMap();
        hashMap.put("data",goods);
        return R.ok(hashMap);
    }

    /**
     *:2：通过品牌的id获取品牌详细信息
     * @param params
     * @return
     * 完成
     */
    @ResponseBody
    @PostMapping("/brand/getBrand")
    Map<String, Object> getBrand(@RequestParam Map<String, Object> params){
        logger.trace(" 通过品牌的id获取品牌详细信息请求入参："+JSON.toJSONString(params));
        if(params==null||params.get("id")==null){
            return R.error();
        }
        Long id = Long.valueOf(params.get("id").toString()) ;
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
        logger.trace(" 精确查询商品请求入参："+JSON.toJSONString(params));
        //查询列表数据
        if(params==null){
            return R.error();
        }
        params.put("status","1");
        List<GoodsDO> goodsList = goodsService.list(params);
        HashMap hashMap = new HashMap();
        if(params.get("brandUuid")!=null){
            List<SeriesDO> series = new ArrayList<>();
            if ( params.get("seriesUuid")==null){
                series = seriesService.getSeriesByBrandUuid(params.get("brandUuid").toString());
            }else {
                SeriesDO seriesDO = seriesService.getByUUID(params.get("seriesUuid").toString());
                series.add(seriesDO);
            }

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
     * 获取品牌下的系列
     */
    @ResponseBody
    @PostMapping("/series/querySeries")
    public R querySeries(@RequestParam Map<String, Object> params){
        logger.trace(" 获取品牌下的系列请求入参："+JSON.toJSONString(params));
        //查询列表数据
        if(params==null){
            return R.error();
        }
        params.put("status","1");
        HashMap hashMap = new HashMap();
        if(params.get("brandUuid")==null){
            return R.error("品牌UUID为空！");
        }
        List<SeriesDO>  series = seriesService.getSeriesByBrandUuid(params.get("brandUuid").toString());
        hashMap.put("data",series);
        return R.ok(hashMap);
    }


    /**
     * 获取系列下的商品
     */
    @ResponseBody
    @PostMapping("/goods/queryGoodsByBrandAndSeries")
    public R queryGoodsByBrandAndSeries(@RequestParam Map<String, Object> params){
        logger.trace(" 获取系列下的商品请求入参："+JSON.toJSONString(params));
        //查询列表数据
        if(params==null||params.get("brandUuid")==null||params.get("offset")==null||params.get("limit")==null){
            return R.error("品牌UUID不能为空！");
        }
        params.put("status","1");
        Query query = new Query(params);
        List<GoodsDO> goodsList = goodsService.list(query);
        int total = goodsService.count(query);
        PageUtils pageUtils = new PageUtils(goodsList, total);
        HashMap hashMap = new HashMap();
        hashMap.put("data",pageUtils);

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
        logger.trace("页面模糊查询商品请求入参："+JSON.toJSONString(params));
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
        logger.trace("查询所有的品牌请求入参："+JSON.toJSONString(params));
        if(StringUtils.isEmpty(params.get("sort").toString())){
            return R.error();
        }
        List<BrandDO> goodsList = brandService.queryBrand(params);
        LinkedHashMap Map =Sort.sortBrand(goodsList,params.get("sort").toString());

        HashMap hashMap = new HashMap();
        hashMap.put("data",Map);
        return R.ok(hashMap);
    }

}
