package com.bootdo.yzjj.service;

import com.bootdo.yzjj.domain.AdvertDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author ckp
 * @email 756495742@qq.com
 * @date 2018-06-09 16:11:02
 */
public interface AdvertService {
	
	AdvertDO get(Long id);

	AdvertDO getAd(String type);
	
	List<AdvertDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AdvertDO advert);
	
	int update(AdvertDO advert);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
