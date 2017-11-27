package cn.tedu.ttms.product.controller;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.product.entity.ProductType;
import cn.tedu.ttms.product.service.ProductTypeService;
@RequestMapping("/type/")
@Controller
public class ProductController {
	
	@Resource(name="product")
	private ProductTypeService product;
	
	@RequestMapping("listUI")
	public String listUi(){
		return "product/type_list";
	}
	
	@RequestMapping("editUI")
	public String editUI(){
		return "product/type_edit";
	}
	
	
	@RequestMapping("doFindTreeGridNodes")
	@ResponseBody
	public JsonResult doFindTreeGridNodes(){
		List<Map<String,Object>> list = product.findTreeGridNodes();
		return new JsonResult(list);
		
	}
	
	@RequestMapping("doDeleteById")
	@ResponseBody
	public JsonResult doDeleteById(Integer id){
		product.deleteById(id);
		return new JsonResult();
	}
	
	@RequestMapping("doFindZtreeNodes")
	@ResponseBody
	public JsonResult doFindZtreeNodes(){
		return new JsonResult(product.findZtreeNodes());
	}
	
	
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(ProductType p){
		product.saveObject(p);
		return new JsonResult();
	}
	
	
}	
