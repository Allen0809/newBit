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
//事务的回滚（基于注解的方式进行事务的控制）
@Transactional(rollbackFor=ServiceException.class)
@Service
public class AttachementServiceImpl implements AttachmentService{
	
	@Autowired
	private AttachmentDao attachmentDao;
	
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	@Override
	public void uploadObject(String title, MultipartFile mFile) {
		//判定参数有效性
		if(StringUtils.isEmpty(title)){
			throw new ServiceException("标题不能为空");
		}
		if(mFile==null){
			throw new ServiceException("请先选择文件");
		}
		if(mFile.isEmpty()){
			throw new ServiceException("文件内容不能为空");
		}
		//判定文件是否已上传（数据库是否有对应记录）
		//获取文件中的字节
		String digest=null;
		try {
			byte bytes[] = mFile.getBytes();
			//创建文件摘要(转换成16进制)
			digest = DigestUtils.md5DigestAsHex(bytes);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServiceException("文件摘要创建失败");
			
		}
		//根据再要查询记录
		Attachment attach = attachmentDao.findObjectByDigest(digest);
		if(attach!=null){
			throw new ServiceException("文件已存在");
		}
		
		//文件不存在，则写数据库
		Attachment a = new Attachment();
		a.setTitle(title);
		a.setFileName(mFile.getOriginalFilename());
		a.setFileDisgest(digest);
		a.setContentType(mFile.getContentType());
		String path = "e:/"+mFile.getOriginalFilename();
		a.setFilePath(path);
		attachmentDao.insertObject(a);
		//上传文件
		try {
			mFile.transferTo(new File(path));
		} catch (IOException e) {
			throw new ServiceException("上传失败");
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
