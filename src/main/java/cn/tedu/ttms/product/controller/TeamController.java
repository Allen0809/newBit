package cn.tedu.ttms.product.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.product.service.TeamService;

@RequestMapping("/team/")
@Controller
public class TeamController {
	@Autowired
	@Qualifier("teamService")
	private TeamService t;
	
	@RequestMapping("editUI")
	public String editUI(){
		return "product/team_edit";
	}
	
	@RequestMapping("listUI")
	public String listUI(){
		return "product/team_list";
	}
	
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String projectName,Integer pageCurrent){
		Map<String,Object> map = t.findPageObjects(projectName, pageCurrent);
		return new JsonResult(map);
	}
	
	
}
