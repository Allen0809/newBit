package beans.product;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.ttms.product.entity.Project;
import cn.tedu.ttms.product.service.ProjectService;

public class TestProjectService {
     ClassPathXmlApplicationContext ctx;
	 @Before
	 public void init(){
		 ctx=new ClassPathXmlApplicationContext(
		"spring-mvc.xml","spring-mybatis.xml");
	 }
	 
	 @Test
	 public void testFindObjects(){
		 //1.��ȡҵ�����
		 ProjectService projectService=
		 ctx.getBean("projectServiceImpl",
				 ProjectService.class);
		 //2.����ҵ����󷽷�
		 List<Project> list=
		 projectService.findObjects();
		 //3.���Խ���Ƿ���ȷ
		 //���Ե�ǰlist�����ǲ��ǲ�����null
		 Assert.assertNotEquals(null, list);
		 //��ǰ���������ܼ���3��,��������Ƿ�ȡ����
		 Assert.assertEquals(3, list.size());
		 System.out.println(list);
	 }
	 
	 
	 @Test
	 public void testFindObjects1(){
		 //1.��ȡҵ�����
		 ProjectService projectService=
		 ctx.getBean("projectServiceImpl",
				 ProjectService.class);
		Project p = new Project();
		p.setName("��ŷ��");
		p.setCode("tt-20171116-CN-BJ-001");
		p.setBeginDate(new Date());
		p.setEndDate(new Date());
		p.setValid(1);
		p.setNote("...");
		projectService.saveObject(p);
		System.out.println("ok");
	 }
	 
	 
	
	 
	 
	 
	 @After
	 public void destroy(){
		 ctx.close();
	 }
}
