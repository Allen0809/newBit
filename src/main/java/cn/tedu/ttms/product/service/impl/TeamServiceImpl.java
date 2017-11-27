package cn.tedu.ttms.product.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.product.dao.TeamDao;
import cn.tedu.ttms.product.service.TeamService;

@Service("teamService")
public class TeamServiceImpl implements TeamService{
	@Autowired
	@Qualifier("TeamDao")
	private TeamDao dao;
	
	@Override
	public Map<String, Object>findPageObjects
	(String projectName,Integer pageCurrent) {
		if(pageCurrent==null||pageCurrent<=0){
			throw new ServiceException("当前页面值无效");
		}
		//每页多少条数据
		int pageSize = 2;
		//从第几页开始
		int startIndex = (pageCurrent-1)*pageSize;
		int rowCount = dao.getRowCount(projectName);
		List<Map<String,Object>> list = dao.findPageObjects
				(projectName, startIndex, pageSize);
		if(list==null){
			throw new ServiceException("没有找到");
		}
		PageObject pageObject = new PageObject();
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setStartIndex(startIndex);
		pageObject.setRowCount(rowCount);
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("list", list);
		map.put("pageObject", pageObject);
		return map;
	}

	

}
