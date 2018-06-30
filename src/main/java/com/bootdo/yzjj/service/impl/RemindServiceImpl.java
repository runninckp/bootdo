package com.bootdo.yzjj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.yzjj.dao.RemindDao;
import com.bootdo.yzjj.domain.RemindDO;
import com.bootdo.yzjj.service.RemindService;



@Service
public class RemindServiceImpl implements RemindService {
	@Autowired
	private RemindDao remindDao;
	
	@Override
	public RemindDO get(Long id){
		return remindDao.get(id);
	}
	
	@Override
	public List<RemindDO> list(Map<String, Object> map){
		return remindDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return remindDao.count(map);
	}
	
	@Override
	public int save(RemindDO remind){
		return remindDao.save(remind);
	}
	
	@Override
	public int update(RemindDO remind){
		return remindDao.update(remind);
	}
	
	@Override
	public int remove(Long id){
		return remindDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return remindDao.batchRemove(ids);
	}
	
}
