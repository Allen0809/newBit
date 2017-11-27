package cn.tedu.ttms.product.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.product.entity.Project;
import cn.tedu.ttms.product.service.ProjectService;

@RequestMapping("/project/")
@Controller
public class ProjectController {
	@Autowired
	@Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
	@RequestMapping("listUI")
	public String listUI(){
		return "product/project_list";
	}
	@RequestMapping("editUI")
	public String editUI(){
		return "product/project_edit";
	}
	
	
	
	/**spring发现方法上有此注解修饰时,底层会启动
	 * 第三方法API将方法返回的数据转换为JSON格式的字符串
	 * 例如借助jackson (此api底层会调用对象的getxxx方法获取数据)
	 * "[
	 *   {"id":1,"name":"环球游",....},
	 *   {"id":2,"name":"月球游",....},
	 *  ]"
	 *  http://localhost:8080/ttms1.01/
	 *  project/doFindObjects.do
	 * */
	@RequestMapping("doFindObjects")
	@ResponseBody
	public List<Project> doFindObjects(){//ModelAndView
		List<Project> list=
		projectService.findObjects();
		return list;
	}
	
	/**
	 *  spring mvc 自动将请求中的参数数据按照指定类型，
	 *  进行类型转换，然后赋值给对应参数(名字相同的参数,一般是通过反射)
	 * @param pageCurrent
	 * @return
	 */
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(
			String name,
			Integer valid,
			Integer pageCurrent){
		Map<String,Object> map = projectService.findPageObjects(name,valid,pageCurrent);
		return new JsonResult(map);
	}
	
	@RequestMapping("doValidById")
	@ResponseBody
	public JsonResult doValidById(String checkedIds,Integer valid){
		projectService.validById(checkedIds, valid);
		return new JsonResult();
	}
	
	//增加
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(Project p){
		/**
		 * 当执行这个数据保存方法时，假如出现了异常，如何处理？
		 * 先检测当前控制层对象内容是否定义了这个异常的处理函数
		 * 假如定义了就直接调用异常处理函数处理
		 * 假如没有定义要检测是否有全局异常处理类@ControllerAdvice
		 * 然后再全局异常处理类中查找对应的异常处理函数处理异常
		 */
		projectService.saveObject(p);
		return new JsonResult();
		
	}
	
	
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult findObjectById(Integer id){
		Project p = projectService.findObjectById(id);
		return new JsonResult(p);
		
	}
	
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(Project p){
		projectService.updateObject(p);
		return new JsonResult();
	}
	
	
	
}






