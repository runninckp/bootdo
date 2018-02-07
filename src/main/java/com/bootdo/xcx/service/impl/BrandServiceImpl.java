package com.bootdo.xcx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.xcx.dao.BrandDao;
import com.bootdo.xcx.domain.BrandDO;
import com.bootdo.xcx.service.BrandService;



@Service
public class BrandServiceImpl implements BrandService {
	@Autowired
	private BrandDao brandDao;
	
	@Override
	public BrandDO get(Long id){
		return brandDao.get(id);
	}
	
	@Override
	public List<BrandDO> list(Map<String, Object> map){
		return brandDao.list(map);
	}

	@Override
	public List<BrandDO> listBrand(){
		return brandDao.listBrand();
	}
	@Override
	public List<BrandDO> queryBrand(Map<String, Object> map){
		return brandDao.queryBrand(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return brandDao.count(map);
	}
	
	@Override
	public int save(BrandDO brand){
		return brandDao.save(brand);
	}
	
	@Override
	public int update(BrandDO brand){
		return brandDao.update(brand);
	}
	
	@Override
	public int remove(Long id){
		return brandDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return brandDao.batchRemove(ids);
	}
	
}
