package cn.tedu.ttms.product.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.product.entity.Attachment;
import cn.tedu.ttms.product.service.impl.AttachementServiceImpl;
	
@RequestMapping("/attach/")
@Controller
public class AttachmentController {
	@Autowired
	private AttachementServiceImpl attachmentSercvice;
	
	private Logger log = Logger.getLogger("AttachmentController");
	@RequestMapping("attachUI")
	public String attachUi(){
		return "attachment/attachment";
		
	}
	
	@RequestMapping("doUpload")
	@ResponseBody
	public JsonResult doUpload(String title,MultipartFile mFile) throws IOException{
		//打印
		log.log(Level.INFO,title+"/"+mFile.getOriginalFilename());
		//上传文件
//		String fileName = mFile.getOriginalFilename();
//		mFile.transferTo(new File("E:/"+fileName));
		attachmentSercvice.uploadObject(title, mFile);
		return new JsonResult();
	}
	
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult findObjects(){
		return new JsonResult(attachmentSercvice.findObjects());
	}
	
	@ResponseBody
	@RequestMapping("doDownload")
	public byte[] doDownload(String digest,HttpServletResponse response) throws IOException{
		//根据digest查询附件对象，判定附件对象是否存在
		Attachment attach = attachmentSercvice.findObjectByDigest(digest);
		if(attach==null){
			throw new ServiceException("文件已不存在");
		}
		String fileName=URLEncoder.encode(attach.getFileName(),"utf-8");
		//设置下载的响应头
		response.setContentType("appliction/octet-stream");
		response.setHeader("Content-disposition",
				"attachment;filename="+fileName);
		
		//实现附件下载（交给浏览器）
		Path path = Paths.get(attach.getFilePath());
		return Files.readAllBytes(path);
	}
	
}
