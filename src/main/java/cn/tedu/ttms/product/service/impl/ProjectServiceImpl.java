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
@Service //����û��ָ������Ĭ��idΪprojectServiceImpl
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private ProjectDao projectDao;
	@Override
	public List<Project> findObjects() {
		return projectDao.findObjects();
	}
	@Override
	public Map<String, Object> findPageObjects(String name,Integer valid,Integer pageCurrent) {
		
		
		
		//1.��֤��������Ч��
		if(pageCurrent==null||pageCurrent<1){
			throw new ServiceException("������Ч");
		}
		int pageSize = 2;
		//���㵱ǰҳ���� ֵ
		int startIndex = (pageCurrent-1)*pageSize;
		//�����ҳ��
		int rowCount = projectDao.getRowCount(name,valid);
		List<Project> list = projectDao.findPageObjects(name,valid,startIndex, pageSize);
		PageObject pageObject = new PageObject();
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setStartIndex(startIndex);
		pageObject.setRowCount(rowCount);
		/*
		 * ��װ��ѯ�ͼ�������map����
		 *	hashMap�ײ�ṹ��������+����+�������
		 *	hashMap�Ƿ��̰߳�ȫ��(����ȫ�����̲߳�������)
		 *	hashMap�Ƿ��ܱ�֤���Ԫ�ص�������?(���ܣ�����ϣ����֤�����Կ���ѡ��LinkeHashMap)
		 *	hashMap�ڲ������������ʹ�ã�����ת����ͬ�����ϻ���ֱ��ʹ��ConcurrentHashMap��
		 */ 
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("pageObject", pageObject);
		
		return map;
	}
	
	@Override
	public void validById(String checkedIds, Integer valid) {
		//1.��֤��������Ч��
		if(StringUtils.isEmpty(checkedIds))
			throw new ServiceException("����ѡ��");
		if(valid!=0&&valid!=1)
			throw new ServiceException("���û���õ�״ֵ̬����ȷ");
		//2.ִ�и��²���
		String[] ids = checkedIds.split(",");
		int rows = projectDao.validById(ids, valid);
		//3.��֤���½�����ɹ���ʧ�ܣ�
		if(rows<=0){
			throw new ServiceException("����ʧ�ܣ�"+rows);
		}
	}
	@Override
	public void saveObject(Project project) {
		//��֤��������Ч��
		if(project==null){
			throw new ServiceException("���������Ϊ��");
		}
		//��֤�ɹ���ִ�б������
		int d = projectDao.insertObject(project);
		//��֤���
		if(d<=0){
			throw new ServiceException("�����Ϣ����");
		}
		
	}
	@Override
	public Project findObjectById(Integer id) {
		if(id==null||id<1){
			throw new ServiceException("id��ֵ��Ч");
		}
		//����id��������
		Project p = projectDao.findObjectById(id);
		//��֤���
		if(p==null){
			throw new ServiceException("�˼�¼������");
		}
		return p;
	}
	@Override
	public void updateObject(Project p) {
		if(p==null){
			throw new ServiceException("�������ݲ���Ϊ��");
		}
		int a = projectDao.updateObject(p);
		if(a<=0){
			throw new ServiceException("����ʧ��");
		}
	}

}
