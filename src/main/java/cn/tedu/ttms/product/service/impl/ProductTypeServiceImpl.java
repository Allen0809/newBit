package cn.tedu.ttms.product.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.common.vo.Node;
import cn.tedu.ttms.product.dao.ProductTypeDao;
import cn.tedu.ttms.product.entity.ProductType;
import cn.tedu.ttms.product.service.ProductTypeService;

@Service("product")
public class ProductTypeServiceImpl implements ProductTypeService{
	
	@Resource(name="productTypeDao")
	private ProductTypeDao dao;
	
	@Override
	public List<Map<String, Object>> findTreeGridNodes() {
		return dao.findTreeGridNodes();
	}

	@Override
	public void deleteById(Integer id) {
		//判断id的有效性
		if(id==null||id<1){
			throw new ServiceException("id值无效");
		}
		//统计此id对应的记录的子元素
		int i = dao.hasChilds(id);
		if(i>0){
			throw new ServiceException("此记录有子元素，不允许删除");
		}
		int rows = dao.deleteById(id);
		if(rows <= 0){
			throw new ServiceException("此元素已经不存在");
		}
		
	}
	@Override
	public List<Node> findZtreeNodes() {
		return dao.findZtreeNodes();
	}
	@Override
	public void saveObject(ProductType p) {
		if(p==null){
			throw new ServiceException("名字不能为空");
		}
		int rows = dao.insertObject(p);
		if(rows<=0){
			throw new ServiceException("保存失败");
		}
		
		
	}

}
