package cn.tedu.ttms.product.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.tedu.ttms.common.vo.Node;
import cn.tedu.ttms.product.entity.ProductType;

/**产品分类管理持久成对象*/
@Repository("productTypeDao")
public interface ProductTypeDao {
	/**
	 * 查询所有分类及这个分类的父类信息（只要获取父类名称即可)
	 * 说明：此模块不做分页查询
	 * @return
	 */
	
	List<Map<String,Object>> findTreeGridNodes();
	
	/*
	 * 根据id删除对应的记录  
	 * 假如此分类下还有子元素不允许删除
	 * 假如此分类下有产品也不允许删除
	 */
	int deleteById(Integer id);
	
	
	/*
	 * 统计此id对应的子分类，返回值为分类的个数
	 */
	int hasChilds(Integer id);
	
	
	/**
	 * 获取所有分类信息，这些信息在页面上
	 * 要以zTree结构显示，使用一个Node对象代表
	 * 树结构中的一个节点信息
	 * @return
	 */
	List<Node> findZtreeNodes();
	
	
	
	int insertObject(ProductType p);
	
}
