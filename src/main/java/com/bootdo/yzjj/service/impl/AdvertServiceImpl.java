package com.bootdo.yzjj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.yzjj.dao.AdvertDao;
import com.bootdo.yzjj.domain.AdvertDO;
import com.bootdo.yzjj.service.AdvertService;



@Service
public class AdvertServiceImpl implements AdvertService {
	@Autowired
	private AdvertDao advertDao;
	
	@Override
	public AdvertDO get(Long id){
		return advertDao.get(id);
	}
	
	@Override
	public List<AdvertDO> list(Map<String, Object> map){
		return advertDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return advertDao.count(map);
	}
	
	@Override
	public int save(AdvertDO advert){
		return advertDao.save(advert);
	}
	
	@Override
	public int update(AdvertDO advert){
		return advertDao.update(advert);
	}
	
	@Override
	public int remove(Long id){
		return advertDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return advertDao.batchRemove(ids);
	}
	
}
