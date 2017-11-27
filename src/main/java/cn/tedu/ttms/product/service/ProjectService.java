package cn.tedu.ttms.product.service;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.product.entity.Project;

/**ҵ���ӿڶ���(����ҵ��Ĵ���)
 * 1)ҵ�����ݵ���֤
 * 2)����Ĵ���
 * 3)��־�Ĵ���
 * 4)����Ĵ���
 * 5)Ȩ�޵Ĵ���
 * 6).....
 * */
public interface ProjectService {
	/**
	 * pageCurrent : 当前页数的值
	 * 
	 * @return 返回当前页数据以及分页信息
	 */
	  List<Project> findObjects();
	  Map<String,Object> findPageObjects(String name,Integer valid,Integer pageCurrent);
	  
	  /**禁用启用项目信息*/
	  void validById(String checkedIds,Integer valid);
	  
	  /**增加信息*/
	  void saveObject(Project project);
	  
	  
	  /**
	   * 根据id查找Project
	   * @param id
	   * @return
	   */
	  Project findObjectById(Integer id);
	  
	  /**
	   * 更新项目信息
	   * @param p
	   * @return
	   */
	  void updateObject(Project p);
}





