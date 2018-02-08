package com.bootdo.xcx.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.xcx.domain.BrandDO;
import com.bootdo.xcx.service.BrandService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 名牌
 * 
 * @author runningckp
 * @email 756495742@qq.com
 * @date 2018-01-24 22:16:10
 */
 
@Controller
@RequestMapping("/xcx/brand")
public class BrandController extends BaseController {
	@Autowired
	private BrandService brandService;
	
	@GetMapping()
	@RequiresPermissions("xcx:brand:brand")
	String Brand(){
	    return "xcx/brand/brand";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("xcx:brand:brand")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<BrandDO> brandList = brandService.list(query);
		int total = brandService.count(query);
		PageUtils pageUtils = new PageUtils(brandList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("xcx:brand:add")
	String add(){
	    return "xcx/brand/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("xcx:brand:edit")
	String edit(@PathVariable("id") Long id,Model model){
		BrandDO brand = brandService.get(id);
		model.addAttribute("brand", brand);
	    return "xcx/brand/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("xcx:brand:add")
	public R save( BrandDO brand){
		brand.setCreated(getUserId());
		brand.setModified(getUserId());
		brand.setGtmCreate(new Date());
		brand.setGtmModified(new Date());
		brand.setStatus(1);
		if(brandService.save(brand)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("xcx:brand:edit")
	public R update( BrandDO brand){
		brand.setGtmModified(new Date());
		brand.setModified(getUserId());
		brandService.update(brand);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("xcx:brand:remove")
	public R remove( Long id){

		if(brandService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("xcx:brand:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		brandService.batchRemove(ids);
		return R.ok();
	}

	/**
	 * 获取所有的品牌
	 * @return
	 */
	@GetMapping("/type")
	@ResponseBody
	public R listType() {
		HashMap<String,Object> map = new HashMap<>();
		List<BrandDO> resultList = brandService.listBrand();
		map.put("data",resultList);
		return R.ok(map);
	}



	
}
