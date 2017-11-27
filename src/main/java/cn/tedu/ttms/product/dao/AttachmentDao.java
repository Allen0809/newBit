package cn.tedu.ttms.product.dao;

import java.util.List;

import cn.tedu.ttms.product.entity.Attachment;

public interface AttachmentDao {
	//负责将附件信息写到数据库
	int insertObject(Attachment entity);
	//根据摘要信息查询数据库
	Attachment findObjectByDigest(String digest);
	//查询
	List<Attachment> findObjects();
}
