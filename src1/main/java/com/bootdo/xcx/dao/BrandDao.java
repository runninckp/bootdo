package com.bootdo.xcx.dao;

import com.bootdo.xcx.domain.BrandDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 名牌
 * @author runningckp
 * @email 756495742@qq.com
 * @date 2018-01-24 22:16:10
 */
@Mapper
public interface BrandDao {

	BrandDO get(Long id);
	
	List<BrandDO> list(Map<String,Object> map);

	List<BrandDO> listBrand();

	List<BrandDO> queryBrand(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(BrandDO brand);
	
	int update(BrandDO brand);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

}
