$(function(){
	//模态框上注册click事件
	$("#modal-dialog").on('click','.ok',doSaveOrUpdate);
	//模态框隐藏时，移除click事件
	$('#modal-dialog').on('hidden.bs.modal',function(){
		$('#modal-dialog').off('click','.ok');
		$('#modal-dialog').removeData('id');
	});
	// 获取模态框上绑定的id值（可能有值也可能没有）
	var id = $('#modal-dialog').data('id');
	//假如id有值，一定是修改，然后根据id查记录，将记录信息初始化到表单中
	if(id)doFindObjectById(id);
		
});

function doFindObjectById(id){
	var url = 'project/doFindObjectById.do';
	var params = {id:id};
	$.getJSON(url,params,function(result){
		if(result.state==1){
			setEditFormDate(result.data);
		}else{
			alert(result.message);
		}
	})
}


function setEditFormDate(data){
	
	$('#nameId').val(data.name);
	$('#codeId').val(data.code);
	$('#beginDateId').val(data.beginDate);
	$('#endDateId').val(data.endDate);
	$('#editFormId input[name="valid"][value="'+data.valid+'"]').prop('checked',true);
	/*$('#editFormId input[name="valid"]').each(function(){
		if($(this).val()==data.valid){
			$(this).prop('checked',true)
		}
	});*/
	$('#noteId').html(data.note);
}


//执行保存或更新操作
function doSaveOrUpdate(){
	//对表单数据进行非空验证
	if(!$('#editFormId').valid())return;
	//1.获取表单数据
	var params = getEditFormDate();
	var url1 = 'project/doSaveObject.do';
	var url2 = 'project/doUpdateObject.do';
	var id=$('#modal-dialog').data('id');
	var url = id?url2:url1;
	if(id)params.id= id;
	//2.异步提交表单数据
	$.post(url,params,function(result){
		if(result.state==1){
			$('#modal-dialog').modal('hide');
			doGetObjects();
		}else{
			alert(result.message);
		}
	});
}

function getEditFormDate(){
	var params = {
			"name":$("#nameId").val(),
			"code":$('#codeId').val(),
			"beginDate":$('#beginDateId').val(),
			"endDate":$('#endDateId').val(),
			"valid":$('#editFormId input[name="valid"]:checked').val(),
			"note":$('#noteId').val()
	};
	return params;
}