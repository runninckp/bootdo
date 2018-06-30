package com.bootdo.yzjj.service;

import com.bootdo.yzjj.domain.RemindDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author ckp
 * @email 756495742@qq.com
 * @date 2018-06-09 16:11:02
 */
public interface RemindService {
	
	RemindDO get(Long id);

	List<RemindDO> getByOpenid(String openid);
	
	List<RemindDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RemindDO remind);


	
	int update(RemindDO remind);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
