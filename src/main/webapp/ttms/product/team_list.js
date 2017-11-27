$(document).ready(function(){
	doGetObjects1();
	$('#queryFormId')
	.on('click','.btn-search',doQueryObjects1)
	.on('click','.btn-add,.btn-update',doShowEditDialog);
});

function doShowEditDialog(){
	var title;
	if($(this).hasClass('btn-add')){
		title = '添加信息';
	}
	if($(this).hasClass('btn-update')){
		var id = $(this).parent().parent().data("id");
		$('#modal-dialog').data('id',id);
		title = '修改信息,id='+id ;
	}
	var url = "team/editUI.do";
	$('#modal-dialog .modal-body').load(url,function(){
		$('#myModalLabel').html(title);
		//显示模态框
		$('#modal-dialog').modal('show');
	})
}






function doQueryObjects1(){
	$('#pageId').data('pageCurrent',1);
	doGetObjects1();
}


function doGetObjects1(){
	var url="team/doFindPageObjects.do";
	var params={
			"projectName":$('#searchNameId').val()
	}
	var pageCurrent = $('#pageId').data("#pageCurrent");
	if(!pageCurrent){
		pageCurrent=1;
	}
	params.pageCurrent= pageCurrent;
	$.getJSON(url,params,function(result){
		if(result.state==1){
			setTableBodyRows1(result.data.list);
			setPagination(result.data.pageObject);
		}else{
			alert(result.message);
		}
	});
	
}


function setTableBodyRows1(data){
	var tb = $('#tbodyId');
	tb.empty();
	for(var i in data){//for(var i=0;i<data.length;i++)  类似
		//构建tr对象
		var tr = $("<tr></tr>");
		tr.data('id',data[i].id);
		//构建td对象
		var tds = 
		"<td><input type='checkbox' name='check' value='"+data[i].id+"' /></td>"+
		"<td>"+data[i].name+"</td>"+
		"<td>"+data[i].projectName+"</td>"+
		"<td>"+(data[i].valid?"启用":"禁用")+"</td>"+
		"<td><button type='button' class='btn btn-warning btn-update'>修改</button></td>";
		//将td对象添加到tr中
		tr.append(tds);
		//将tr对象添加到tbodyId中
		tb.append(tr);
	}
	
}