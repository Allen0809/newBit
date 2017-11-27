package cn.tedu.ttms.product.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.product.entity.Project;
/**�־ò����:�������ݵĳ־û�����*/
public interface ProjectDao {
     /**
      * �����ݿ��ѯ������������(��Ŀ��Ϣ)
      * 1)һ�м�¼��װΪһ��project����
      * 2)���м�¼��Ӧ�Ķ��project�����ٷ�װ��list����
      */
	List<Project> findObjects();
	/**
	 * ��ҳ��ѯ����
	 * startIndex: ��Ӧlimit�����offset,��ʾ���Ŀ�ʼ������
	 * pageSize ��ÿҳ�����ʾ��������¼
	 */
	List<Project> findPageObjects(
			@Param("name")String name,
			@Param("valid")Integer valid,
			@Param("startIndex")int startIndex,
			@Param("pageSize")int pageSize);
	
	
	//����ܼ�¼��
	int getRowCount(
			@Param("name")String name,
			@Param("valid")Integer valid);
	
	//ִ�н��ú����õĲ���   ids:Ҫ���ú���������Ŀ�����id   valid:���û�����״ֵ̬
	int validById(
			@Param("ids")String[] ids,
			@Param("valid")Integer valid);
	
	
	int insertObject(Project project);
	
	
	
	Project findObjectById(Integer id);
	
	int updateObject(Project p);
	
}


