package com.bootdo.yzjj.dao;

import com.bootdo.yzjj.domain.GoldDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author ckp
 * @email 756495742@qq.com
 * @date 2018-06-10 12:08:46
 */
@Mapper
public interface GoldDao {

	GoldDO get(String time);

	GoldDO getLatest();
	
	List<GoldDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(GoldDO gold);
	
	int update(GoldDO gold);
	
	int remove(String time);
	
	int batchRemove(String[] times);
}
