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
		//�ж�id����Ч��
		if(id==null||id<1){
			throw new ServiceException("idֵ��Ч");
		}
		//ͳ�ƴ�id��Ӧ�ļ�¼����Ԫ��
		int i = dao.hasChilds(id);
		if(i>0){
			throw new ServiceException("�˼�¼����Ԫ�أ�������ɾ��");
		}
		int rows = dao.deleteById(id);
		if(rows <= 0){
			throw new ServiceException("��Ԫ���Ѿ�������");
		}
		
	}
	@Override
	public List<Node> findZtreeNodes() {
		return dao.findZtreeNodes();
	}
	@Override
	public void saveObject(ProductType p) {
		if(p==null){
			throw new ServiceException("���ֲ���Ϊ��");
		}
		int rows = dao.insertObject(p);
		if(rows<=0){
			throw new ServiceException("����ʧ��");
		}
		
		
	}

}
