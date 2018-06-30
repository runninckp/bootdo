package com.bootdo.yzjj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.yzjj.dao.GoldDao;
import com.bootdo.yzjj.domain.GoldDO;
import com.bootdo.yzjj.service.GoldService;



@Service
public class GoldServiceImpl implements GoldService {
	@Autowired
	private GoldDao goldDao;
	
	@Override
	public GoldDO get(String time){
		return goldDao.get(time);
	}

	@Override
	public GoldDO getLatest(){
		return goldDao.getLatest();
	}
	
	@Override
	public List<GoldDO> list(Map<String, Object> map){
		return goldDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return goldDao.count(map);
	}
	
	@Override
	public int save(GoldDO gold){
		return goldDao.save(gold);
	}
	
	@Override
	public int update(GoldDO gold){
		return goldDao.update(gold);
	}
	
	@Override
	public int remove(String time){
		return goldDao.remove(time);
	}
	
	@Override
	public int batchRemove(String[] times){
		return goldDao.batchRemove(times);
	}
	
}
