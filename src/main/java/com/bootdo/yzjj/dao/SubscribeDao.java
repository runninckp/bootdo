package com.bootdo.yzjj.dao;

import com.bootdo.yzjj.domain.SubscribeDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author ckp
 * @email 756495742@qq.com
 * @date 2018-06-09 16:11:02
 */
@Mapper
public interface SubscribeDao {

	SubscribeDO get(Long id);
	
	List<SubscribeDO> getByOpenid(String openid);

	List<SubscribeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SubscribeDO subscribe);
	
	int update(SubscribeDO subscribe);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
