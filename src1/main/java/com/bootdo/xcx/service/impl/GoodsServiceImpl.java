package com.bootdo.xcx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.xcx.dao.GoodsDao;
import com.bootdo.xcx.domain.GoodsDO;
import com.bootdo.xcx.service.GoodsService;



@Service
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsDao goodsDao;
	
	@Override
	public GoodsDO get(String uuid){
		return goodsDao.get(uuid);
	}
	
	@Override
	public List<GoodsDO> list(Map<String, Object> map){
		return goodsDao.list(map);
	}
	@Override
	public List<GoodsDO> searchGoods(Map<String, Object> map){
		return goodsDao.searchGoods(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return goodsDao.count(map);
	}

	@Override
	public int countSearch(Map<String, Object> map){
		return goodsDao.countSearch(map);
	}
	@Override
	public int save(GoodsDO goods){
		return goodsDao.save(goods);
	}
	
	@Override
	public int update(GoodsDO goods){
		return goodsDao.update(goods);
	}
	
	@Override
	public int remove(Long id){
		return goodsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return goodsDao.batchRemove(ids);
	}

	@Override
	public int updateStatus(String id){
		return goodsDao.updateStatus(id);
	}

	@Override
	public int batchUpdateStatus(String[] ids){
		return goodsDao.batchUpdateStatus(ids);
	}
	
}
