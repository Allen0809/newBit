package cn.tedu.ttms.product.service;

import java.util.List;
import java.util.Map;


public interface TeamService {
	Map<String,Object> findPageObjects
	(String projectName,Integer pageCurrent);
}
