package com.bootdo.yzjj.dao;

import com.bootdo.yzjj.domain.RemindDO;

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
public interface RemindDao {

	RemindDO get(Long id);
	
	List<RemindDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(RemindDO remind);
	
	int update(RemindDO remind);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
