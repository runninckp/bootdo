package com.bootdo.yzjj.controller;

import java.util.List;
import java.util.Map;

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

import com.bootdo.yzjj.domain.AdvertDO;
import com.bootdo.yzjj.service.AdvertService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author ckp
 * @email 756495742@qq.com
 * @date 2018-06-09 16:11:02
 */
 
@Controller
@RequestMapping("/yzjj/advert")
public class AdvertController {
	@Autowired
	private AdvertService advertService;
	
	@GetMapping()
	@RequiresPermissions("yzjj:advert:advert")
	String Advert(){
	    return "yzjj/advert/advert";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("yzjj:advert:advert")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AdvertDO> advertList = advertService.list(query);
		int total = advertService.count(query);
		PageUtils pageUtils = new PageUtils(advertList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("yzjj:advert:add")
	String add(){
	    return "yzjj/advert/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("yzjj:advert:edit")
	String edit(@PathVariable("id") Long id,Model model){
		AdvertDO advert = advertService.get(id);
		model.addAttribute("advert", advert);
	    return "yzjj/advert/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("yzjj:advert:add")
	public R save( AdvertDO advert){
		if(advertService.save(advert)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("yzjj:advert:edit")
	public R update( AdvertDO advert){
		advertService.update(advert);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("yzjj:advert:remove")
	public R remove( Long id){
		if(advertService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("yzjj:advert:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		advertService.batchRemove(ids);
		return R.ok();
	}
	
}
