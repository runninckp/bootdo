package com.bootdo.yzjj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.yzjj.dao.SubscribeDao;
import com.bootdo.yzjj.domain.SubscribeDO;
import com.bootdo.yzjj.service.SubscribeService;



@Service
public class SubscribeServiceImpl implements SubscribeService {
	@Autowired
	private SubscribeDao subscribeDao;
	
	@Override
	public SubscribeDO get(Long id){
		return subscribeDao.get(id);
	}

	@Override
	public List<SubscribeDO> getByOpenid(String id){
		return subscribeDao.getByOpenid(id);
	}
	
	@Override
	public List<SubscribeDO> list(Map<String, Object> map){
		return subscribeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return subscribeDao.count(map);
	}
	
	@Override
	public int save(SubscribeDO subscribe){
		return subscribeDao.save(subscribe);
	}
	
	@Override
	public int update(SubscribeDO subscribe){
		return subscribeDao.update(subscribe);
	}
	
	@Override
	public int remove(Long id){
		return subscribeDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return subscribeDao.batchRemove(ids);
	}
	
}
