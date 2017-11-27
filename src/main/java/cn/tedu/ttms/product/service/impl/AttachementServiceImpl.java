package cn.tedu.ttms.product.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;

import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.product.dao.AttachmentDao;
import cn.tedu.ttms.product.entity.Attachment;
import cn.tedu.ttms.product.service.AttachmentService;
//����Ļع�������ע��ķ�ʽ��������Ŀ��ƣ�
@Transactional(rollbackFor=ServiceException.class)
@Service
public class AttachementServiceImpl implements AttachmentService{
	
	@Autowired
	private AttachmentDao attachmentDao;
	
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	@Override
	public void uploadObject(String title, MultipartFile mFile) {
		//�ж�������Ч��
		if(StringUtils.isEmpty(title)){
			throw new ServiceException("���ⲻ��Ϊ��");
		}
		if(mFile==null){
			throw new ServiceException("����ѡ���ļ�");
		}
		if(mFile.isEmpty()){
			throw new ServiceException("�ļ����ݲ���Ϊ��");
		}
		//�ж��ļ��Ƿ����ϴ������ݿ��Ƿ��ж�Ӧ��¼��
		//��ȡ�ļ��е��ֽ�
		String digest=null;
		try {
			byte bytes[] = mFile.getBytes();
			//�����ļ�ժҪ(ת����16����)
			digest = DigestUtils.md5DigestAsHex(bytes);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServiceException("�ļ�ժҪ����ʧ��");
			
		}
		//������Ҫ��ѯ��¼
		Attachment attach = attachmentDao.findObjectByDigest(digest);
		if(attach!=null){
			throw new ServiceException("�ļ��Ѵ���");
		}
		
		//�ļ������ڣ���д���ݿ�
		Attachment a = new Attachment();
		a.setTitle(title);
		a.setFileName(mFile.getOriginalFilename());
		a.setFileDisgest(digest);
		a.setContentType(mFile.getContentType());
		String path = "e:/"+mFile.getOriginalFilename();
		a.setFilePath(path);
		attachmentDao.insertObject(a);
		//�ϴ��ļ�
		try {
			mFile.transferTo(new File(path));
		} catch (IOException e) {
			throw new ServiceException("�ϴ�ʧ��");
		}
	}


	
	
	
	
	@Override
	public Attachment findObjectByDigest(String digest) {
		
		return attachmentDao.findObjectByDigest(digest);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Attachment> findObjects() {
		return attachmentDao.findObjects();
	}
	
}
