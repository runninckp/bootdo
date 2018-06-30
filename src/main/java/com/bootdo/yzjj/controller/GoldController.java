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

import com.bootdo.yzjj.domain.GoldDO;
import com.bootdo.yzjj.service.GoldService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author ckp
 * @email 756495742@qq.com
 * @date 2018-06-10 12:08:46
 */
 
@Controller
@RequestMapping("/yzjj/gold")
public class GoldController {
	@Autowired
	private GoldService goldService;
	
	@GetMapping()
	@RequiresPermissions("yzjj:gold:gold")
	String Gold(){
	    return "yzjj/gold/gold";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("yzjj:gold:gold")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<GoldDO> goldList = goldService.list(query);
		int total = goldService.count(query);
		PageUtils pageUtils = new PageUtils(goldList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("yzjj:gold:add")
	String add(){
	    return "yzjj/gold/add";
	}

	@GetMapping("/edit/{time}")
	@RequiresPermissions("yzjj:gold:edit")
	String edit(@PathVariable("time") String time,Model model){
		GoldDO gold = goldService.get(time);
		model.addAttribute("gold", gold);
	    return "yzjj/gold/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("yzjj:gold:add")
	public R save( GoldDO gold){
		if(goldService.save(gold)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("yzjj:gold:edit")
	public R update( GoldDO gold){
		goldService.update(gold);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("yzjj:gold:remove")
	public R remove( String time){
		if(goldService.remove(time)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("yzjj:gold:batchRemove")
	public R remove(@RequestParam("ids[]") String[] times){
		goldService.batchRemove(times);
		return R.ok();
	}
	
}
