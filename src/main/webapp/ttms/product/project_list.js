$(document).ready(function(){
	doGetObjects();
	$('#queryFormId')
	.on('click','.btn-search',doQueryObject)
	.on('click','.btn-invalid,.btn-valid',doValidById)
	.on('click','.btn-add,.btn-update',doShowEditDialog);
});

function doShowEditDialog(){
	var title;
	if($(this).hasClass('btn-add')){
		title = '添加项目信息';
	}
	if($(this).hasClass('btn-update')){
		//获取button所在行的id值
		var id = $(this).parent().parent().data('id');
		//将id值绑定到模态框上
		$('#modal-dialog').data('id',id);
		title = '修改项目信息,id='+id;
	}
	var url = "project/editUI.do";
	$('#modal-dialog .modal-body').load(url,function(){
		$('#myModalLabel').html(title);
		//显示模态框
		$('#modal-dialog').modal('show');
	})
}



/*禁用或启用项目信息*/
function doValidById(){
	//1.设置状态信息
	//2.根据操作（禁用，启动）设置状态信息   hasClass:判断是否 这个.class
	var valid;
	if($(this).hasClass('btn-valid')){
		valid =1;
	}
	if($(this).hasClass('btn-invalid')){
		valid =0;
	}
	var checkedIds = getCheckedIds();
	if(checkedIds==""){
		alert('请至少选择一个');
		return;
	}
	//2.发起异步请求，禁用或启用项目信息?
	var url = 'project/doValidById.do';
	var params={"checkedIds":checkedIds,"valid":valid};
	$.post(url,params,function(result){
		if(result.state==1){
			doQueryObject();
		}else{
			alert(result.message);
		}
	});
	
}

function getCheckedIds(){
	//3.获取选中的Id的值
	var checkedIds="";
	$('#tbodyId input[name=check]').each(function(){
		//下面 this 代表的是input对象,(选中的！！！)
		if($(this).prop('checked')){//checked==true表示选中了
			if(checkedIds==""){
				checkedIds+=$(this).val();
			}else{
				checkedIds+=','+$(this).val();
			}
		}
	});
	
	return checkedIds;
}



function doQueryObject(){
	//1.初始化当前页面信息
	$('#pageId').data('pageCurrent',1);
	//2.执行查询操作
	
	//2.1 获得表单数据
	//2.2 提交表单数据执行查询
	doGetObjects();
	
}
//获取表单数据
function getQueryFormData(){
	var params={
			"name":$("#searchNameId").val(),
			"valid":$('#searchValidId').val()
	}
	return params;
}


function doGetObjects(){
	//定义访问项目信息的url
	var url = "project/doFindPageObjects.do";
	//获取表单数据（查询时用）
	var params = getQueryFormData();
	//发起异步ajax请求
	var pageCurrent = $('#pageId').data("pageCurrent");
	if(!pageCurrent){
		pageCurrent=1;
	}
	params.pageCurrent=pageCurrent;
// 	var params={"pageCurrent":pageCurrent};
	$.getJSON(url,params,function(result){
	//	console.log(data);
		if(result.state==1){
		//设置当前页数据
		setTableBodyRows(result.data.list);
		//设置分页信息
		setPagination(result.data.pageObject);
		}else{
			alert(result.message);
		}
	});
}
//将服务端返回的json对象数据显示在页面上
function setTableBodyRows(data){
	//debugger
	//获得具体dom对象（显示数据位置的那个dom）
	var tb = $("#tbodyId");
	tb.empty();
	for(var i in data){//for(var i=0;i<data.length;i++)  类似
		//构建tr对象
		var tr = $("<tr></tr>");
		tr.data('id',data[i].id);
		//构建td对象
		var tds = 
		"<td><input type='checkbox' name='check' value='"+data[i].id+"' /></td>"+
		"<td>"+data[i].code+"</td>"+
		"<td>"+data[i].name+"</td>"+
		"<td>"+data[i].beginDate+"</td>"+
		"<td>"+data[i].endDate+"</td>"+
		"<td>"+(data[i].valid?"启用":"禁用")+"</td>"+
		"<td><button type='button' class='btn btn-warning btn-update'>修改</button></td>";
		//将td对象添加到tr中
		tr.append(tds);
		//将tr对象添加到tbodyId中
		tb.append(tr);
	}
}
