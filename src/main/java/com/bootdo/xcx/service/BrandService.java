package com.bootdo.xcx.service;

import com.bootdo.xcx.domain.BrandDO;

import java.util.List;
import java.util.Map;

/**
 * 名牌
 * 
 * @author runningckp
 * @email 756495742@qq.com
 * @date 2018-01-24 22:16:10
 */
public interface BrandService {
	
	BrandDO get(Long id);
	
	List<BrandDO> list(Map<String, Object> map);

	List<BrandDO> listBrand();

	List<BrandDO> queryBrand(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(BrandDO brand);
	
	int update(BrandDO brand);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
