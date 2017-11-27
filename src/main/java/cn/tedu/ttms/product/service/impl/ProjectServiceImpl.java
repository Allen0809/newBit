package cn.tedu.ttms.product.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.product.dao.ProjectDao;
import cn.tedu.ttms.product.entity.Project;
import cn.tedu.ttms.product.service.ProjectService;
@Service //假如没有指定名字默认id为projectServiceImpl
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private ProjectDao projectDao;
	@Override
	public List<Project> findObjects() {
		return projectDao.findObjects();
	}
	@Override
	public Map<String, Object> findPageObjects(String name,Integer valid,Integer pageCurrent) {
		
		
		
		//1.验证参数的有效性
		if(pageCurrent==null||pageCurrent<1){
			throw new ServiceException("参数无效");
		}
		int pageSize = 2;
		//计算当前页数的 值
		int startIndex = (pageCurrent-1)*pageSize;
		//获得总页数
		int rowCount = projectDao.getRowCount(name,valid);
		List<Project> list = projectDao.findPageObjects(name,valid,startIndex, pageSize);
		PageObject pageObject = new PageObject();
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setStartIndex(startIndex);
		pageObject.setRowCount(rowCount);
		/*
		 * 封装查询和计算结果到map对象
		 *	hashMap底层结构？（数组+链表+红黑树）
		 *	hashMap是否线程安全？(不安全，多线程并发访问)
		 *	hashMap是否能保证添加元素的有序性?(不能，假如希望保证有序性可以选择LinkeHashMap)
		 *	hashMap在并发场景下如何使用（将其转换成同步集合或者直接使用ConcurrentHashMap）
		 */ 
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("pageObject", pageObject);
		
		return map;
	}
	
	@Override
	public void validById(String checkedIds, Integer valid) {
		//1.验证参数的有效性
		if(StringUtils.isEmpty(checkedIds))
			throw new ServiceException("请先选择");
		if(valid!=0&&valid!=1)
			throw new ServiceException("启用或禁用的状态值不正确");
		//2.执行更新操作
		String[] ids = checkedIds.split(",");
		int rows = projectDao.validById(ids, valid);
		//3.验证更新结果（成功，失败）
		if(rows<=0){
			throw new ServiceException("更新失败："+rows);
		}
	}
	@Override
	public void saveObject(Project project) {
		//验证参数的有效性
		if(project==null){
			throw new ServiceException("保存对象不能为空");
		}
		//验证成功，执行保存操作
		int d = projectDao.insertObject(project);
		//验证结果
		if(d<=0){
			throw new ServiceException("添加信息错误");
		}
		
	}
	@Override
	public Project findObjectById(Integer id) {
		if(id==null||id<1){
			throw new ServiceException("id的值无效");
		}
		//根据id查找数据
		Project p = projectDao.findObjectById(id);
		//验证结果
		if(p==null){
			throw new ServiceException("此记录不存在");
		}
		return p;
	}
	@Override
	public void updateObject(Project p) {
		if(p==null){
			throw new ServiceException("更新数据不能为空");
		}
		int a = projectDao.updateObject(p);
		if(a<=0){
			throw new ServiceException("更新失败");
		}
	}

}
