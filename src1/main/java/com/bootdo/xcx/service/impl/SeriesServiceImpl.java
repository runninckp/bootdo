package com.bootdo.xcx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.xcx.dao.SeriesDao;
import com.bootdo.xcx.domain.SeriesDO;
import com.bootdo.xcx.service.SeriesService;



@Service
public class SeriesServiceImpl implements SeriesService {
	@Autowired
	private SeriesDao seriesDao;
	
	@Override
	public SeriesDO get(Long id){
		return seriesDao.get(id);
	}
	
	@Override
	public List<SeriesDO> list(Map<String, Object> map){
		return seriesDao.list(map);
	}

	@Override
	public List<SeriesDO> getSeriesByBrandUuid(String brandUuid){
		return seriesDao.getSeriesByBrandUuid(brandUuid);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return seriesDao.count(map);
	}
	
	@Override
	public int save(SeriesDO series){
		return seriesDao.save(series);
	}
	
	@Override
	public int update(SeriesDO series){
		return seriesDao.update(series);
	}
	
	@Override
	public int remove(Long id){
		return seriesDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return seriesDao.batchRemove(ids);
	}
	
}
