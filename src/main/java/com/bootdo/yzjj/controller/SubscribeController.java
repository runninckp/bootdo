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

import com.bootdo.yzjj.domain.SubscribeDO;
import com.bootdo.yzjj.service.SubscribeService;
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
@RequestMapping("/yzjj/subscribe")
public class SubscribeController {
	@Autowired
	private SubscribeService subscribeService;
	
	@GetMapping()
	@RequiresPermissions("yzjj:subscribe:subscribe")
	String Subscribe(){
	    return "yzjj/subscribe/subscribe";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("yzjj:subscribe:subscribe")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SubscribeDO> subscribeList = subscribeService.list(query);
		int total = subscribeService.count(query);
		PageUtils pageUtils = new PageUtils(subscribeList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("yzjj:subscribe:add")
	String add(){
	    return "yzjj/subscribe/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("yzjj:subscribe:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SubscribeDO subscribe = subscribeService.get(id);
		model.addAttribute("subscribe", subscribe);
	    return "yzjj/subscribe/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("yzjj:subscribe:add")
	public R save( SubscribeDO subscribe){
		if(subscribeService.save(subscribe)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("yzjj:subscribe:edit")
	public R update( SubscribeDO subscribe){
		subscribeService.update(subscribe);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("yzjj:subscribe:remove")
	public R remove( Long id){
		if(subscribeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("yzjj:subscribe:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		subscribeService.batchRemove(ids);
		return R.ok();
	}
	
}
