package com.bootdo.xcx.dao;

import com.bootdo.xcx.domain.SeriesDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 系列
 * @author runningckp
 * @email 756495742@qq.com
 * @date 2018-01-27 18:42:13
 */
@Mapper
public interface SeriesDao {

	SeriesDO get(Long id);
	
	List<SeriesDO> list(Map<String,Object> map);

	List<SeriesDO> getSeriesByBrandUuid(String brandUuid);

	int count(Map<String,Object> map);
	
	int save(SeriesDO series);
	
	int update(SeriesDO series);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
