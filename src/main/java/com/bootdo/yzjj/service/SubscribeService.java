package com.bootdo.yzjj.service;

import com.bootdo.yzjj.domain.SubscribeDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author ckp
 * @email 756495742@qq.com
 * @date 2018-06-09 16:11:02
 */
public interface SubscribeService {
	
	SubscribeDO get(Long id);
	
	List<SubscribeDO> getByOpenid(String openid);

	List<SubscribeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SubscribeDO subscribe);
	
	int update(SubscribeDO subscribe);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
