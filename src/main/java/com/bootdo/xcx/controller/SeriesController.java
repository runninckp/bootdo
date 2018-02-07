package com.bootdo.xcx.controller;

import java.util.Date;
import java.util.HashMap;
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

import com.bootdo.xcx.domain.SeriesDO;
import com.bootdo.xcx.service.SeriesService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 系列
 * 
 * @author runningckp
 * @email 756495742@qq.com
 * @date 2018-01-27 18:42:13
 */
 
@Controller
@RequestMapping("/xcx/series")
public class SeriesController extends BaseController {
	@Autowired
	private SeriesService seriesService;
	
	@GetMapping()
	@RequiresPermissions("xcx:series:series")
	String Series(){
	    return "xcx/series/series";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("xcx:series:series")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SeriesDO> seriesList = seriesService.list(query);
		int total = seriesService.count(query);
		PageUtils pageUtils = new PageUtils(seriesList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("xcx:series:add")
	String add(){
	    return "xcx/series/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("xcx:series:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SeriesDO series = seriesService.get(id);
		model.addAttribute("series", series);
	    return "xcx/series/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("xcx:series:add")
	public R save( SeriesDO series){
		series.setStatus(1);
		series.setGtmCreate(new Date());
		series.setGtmModified(new Date());
		series.setCreated(getUserId());
		series.setModified(getUserId());
		if(seriesService.save(series)>0){
			return R.ok();
		}
		return R.error();
	}


	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("xcx:series:edit")
	public R update( SeriesDO series){
		series.setGtmModified(new Date());
		series.setModified(getUserId());
		seriesService.update(series);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("xcx:series:remove")
	public R remove( Long id){
		if(seriesService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("xcx:series:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		seriesService.batchRemove(ids);
		return R.ok();
	}

	@ResponseBody
	@GetMapping("/getSeries/{brandUuid}")
	R getSeriesByBrandUuid(@PathVariable("brandUuid") String brandUuid){
		List<SeriesDO> series = seriesService.getSeriesByBrandUuid(brandUuid);
		HashMap<String,Object> map = new HashMap<>();
		map.put("data",series);
		return R.ok(map);
	}
	
}
