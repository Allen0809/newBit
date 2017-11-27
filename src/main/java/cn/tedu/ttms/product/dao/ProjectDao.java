package cn.tedu.ttms.product.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.product.entity.Project;
/**持久层对象:负责数据的持久化操作*/
public interface ProjectDao {
     /**
      * 从数据库查询表中所有数据(项目信息)
      * 1)一行记录封装为一个project对象
      * 2)多行记录对应的多个project对象再封装到list集合
      */
	List<Project> findObjects();
	/**
	 * 分页查询函数
	 * startIndex: 对应limit语句中offset,表示从哪开始拿数据
	 * pageSize ：每页最多显示多少条记录
	 */
	List<Project> findPageObjects(
			@Param("name")String name,
			@Param("valid")Integer valid,
			@Param("startIndex")int startIndex,
			@Param("pageSize")int pageSize);
	
	
	//获得总记录数
	int getRowCount(
			@Param("name")String name,
			@Param("valid")Integer valid);
	
	//执行禁用和启用的操作   ids:要禁用和启动的项目对象的id   valid:禁用或启用状态值
	int validById(
			@Param("ids")String[] ids,
			@Param("valid")Integer valid);
	
	
	int insertObject(Project project);
	
	
	
	Project findObjectById(Integer id);
	
	int updateObject(Project p);
	
}


