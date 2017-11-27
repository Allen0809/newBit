package cn.tedu.ttms.product.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.tedu.ttms.product.entity.Attachment;

public interface AttachmentService {
	//用于实现文件上传
	void uploadObject(String title,MultipartFile mFile);
	
	Attachment findObjectByDigest(String digest);
	List<Attachment> findObjects();
}
