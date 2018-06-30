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

import com.bootdo.yzjj.domain.RemindDO;
import com.bootdo.yzjj.service.RemindService;
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
@RequestMapping("/yzjj/remind")
public class RemindController {
	@Autowired
	private RemindService remindService;
	
	@GetMapping()
	@RequiresPermissions("yzjj:remind:remind")
	String Remind(){
	    return "yzjj/remind/remind";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("yzjj:remind:remind")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RemindDO> remindList = remindService.list(query);
		int total = remindService.count(query);
		PageUtils pageUtils = new PageUtils(remindList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("yzjj:remind:add")
	String add(){
	    return "yzjj/remind/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("yzjj:remind:edit")
	String edit(@PathVariable("id") Long id,Model model){
		RemindDO remind = remindService.get(id);
		model.addAttribute("remind", remind);
	    return "yzjj/remind/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("yzjj:remind:add")
	public R save( RemindDO remind){
		if(remindService.save(remind)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("yzjj:remind:edit")
	public R update( RemindDO remind){
		remindService.update(remind);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("yzjj:remind:remove")
	public R remove( Long id){
		if(remindService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("yzjj:remind:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		remindService.batchRemove(ids);
		return R.ok();
	}
	
}
