package cn.tedu.ttms.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 分页获取团目信息(例如环球游30,40日游)
 * 要求查询结果中包含项目名称
 * projectName: 页面上的查询条件，根据项目进行查询
 * startIndex ： 查询当前业数据的起始位置
 * pageSize ： 当前页面的 大小（每页最多显示多少条数据）
 * @author Administrator
 *
 */
@Repository("TeamDao")
public interface TeamDao {
	List<Map<String,Object>> findPageObjects(
			@Param("projectName")String projectName,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
	int getRowCount(
			@Param("projectName")String projectName);
}
