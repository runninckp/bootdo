package com.bootdo.xcx.service;

import com.bootdo.xcx.domain.GoodsDO;

import java.util.List;
import java.util.Map;

/**
 * 商品
 * 
 * @author runningckp
 * @email 756495742@qq.com
 * @date 2018-01-24 22:16:10
 */
public interface GoodsService {
	
	GoodsDO get(String uuid);
	
	List<GoodsDO> list(Map<String, Object> map);

	List<GoodsDO> searchGoods(Map<String, Object> map);
	
	int count(Map<String, Object> map);

	int countSearch(Map<String, Object> map);

	int save(GoodsDO goods);
	
	int update(GoodsDO goods);
	
	int remove(Long id);

	int updateStatus(String id);
	
	int batchRemove(Long[] ids);

	int batchUpdateStatus(String[] ids);
}
