package com.bootdo.xcx.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.xcx.domain.GoodsDO;
import com.bootdo.xcx.service.GoodsService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 商品
 * 
 * @author runningckp
 * @email 756495742@qq.com
 * @date 2018-01-24 22:16:10
 */
 
@Controller
@RequestMapping("/xcx/goods")
public class GoodsController extends BaseController {
	@Autowired
	private GoodsService goodsService;
	
	@GetMapping()
	@RequiresPermissions("xcx:goods:goods")
	String Goods(){
	    return "xcx/goods/goods";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("xcx:goods:goods")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<GoodsDO> goodsList = goodsService.list(query);
		int total = goodsService.count(query);
		PageUtils pageUtils = new PageUtils(goodsList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("xcx:goods:add")
	String add(){
	    return "xcx/goods/add";
	}

	@GetMapping("/edit/{uuid}")
	@RequiresPermissions("xcx:goods:edit")
	String edit(@PathVariable("uuid") String uuid,Model model){
		GoodsDO goods = goodsService.get(uuid);
		model.addAttribute("goods", goods);
	    return "xcx/goods/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("xcx:goods:add")
	public R save( GoodsDO goods){
		goods.setGtmCreate(new Date());
		goods.setGtmModified(new Date());
		goods.setCreated(getUserId());
		goods.setModified(getUserId());
		goods.setStatus(1);
		if(goodsService.save(goods)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("xcx:goods:edit")
	public R update( GoodsDO goods){
		goods.setModified(getUserId());
		goods.setGtmModified(new Date());
		goodsService.update(goods);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("xcx:goods:remove")
	public R remove( String id){
		/*if(goodsService.remove(id)>0){
		return R.ok();
		}*/
		if(goodsService.updateStatus(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("xcx:goods:batchRemove")
	public R remove(@RequestParam("uuids[]") String[] ids){
		//goodsService.batchRemove(ids);
		goodsService.batchUpdateStatus(ids);
		return R.ok();
	}
	
}
