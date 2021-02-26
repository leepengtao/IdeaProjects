var userObj;

//用户管理页面上点击删除按钮弹出删除框(userlist.jsp)
function deleteUser(obj){
	$.ajax({
		type:"GET",
		url:path+"/user/del/"+obj.attr("userid"),
		//data:{method:"deluser",uid:obj.attr("userid")},
		dataType:"json",
		success:function(data){
			if(data.ret == "true"){//删除成功：移除删除行
				cancleBtn();
				obj.parents("tr").remove();
			}else if(data.ret == "false"){//删除失败
				//alert("对不起，删除用户【"+obj.attr("username")+"】失败");
				changeDLGContent("对不起，删除用户【"+obj.attr("username")+"】失败");
			}else if(data.ret == "notexist"){
				//alert("对不起，用户【"+obj.attr("username")+"】不存在");
				changeDLGContent("对不起，用户【"+obj.attr("username")+"】不存在");
			}
		},
		error:function(data){
			//alert("对不起，删除失败");
			changeDLGContent("对不起，删除失败");
		}
	});
}

function openYesOrNoDLG(){
	$('.zhezhao').css('display', 'block');
	$('#removeUse').fadeIn();
}

function cancleBtn(){
	$('.zhezhao').css('display', 'none');
	$('#removeUse').fadeOut();
}
function changeDLGContent(contentStr){
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}

$(function(){
	//通过jquery的class选择器（数组）
	//对每个class为viewUser的元素进行动作绑定（click）
	/**
	 * bind、live、delegate
	 * on
	 */
	$(".viewUser").on("click",function(){
		/*//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
		var obj = $(this);
		window.location.href=path+"/jsp/user.do?method=view&uid="+ obj.attr("userid");*/
		var obj = $(this);
		//alert(obj.attr("userid"));
		$.ajax({
			url: path+"/user/showUser/"+ obj.attr("userid"),
			type:"get",
			dataType:"json",
			success:function(ret){
				//alert(ret);
				$("#v_userCode").val(ret.userCode);
				$("#v_userName").val(ret.userName);
				//$("#v_gender").val(ret.gender);
				if(ret.gender=="1"){
					$("#v_gender").val("男");
				}else{
					$("#v_gender").val("女");
				}
				$("#v_birthday").val(ret.birthday);
				$("#v_phone").val(ret.phone);
				$("#v_userRoleName").val(ret.userRoleName);
				$("#v_address").val(ret.address);
			},
			error:function(){
				alert("error!");
			}
		});
	});
	//修改
	$(".modifyUser").on("click",function(){
		var obj = $(this);
		var pa = path+"/user/view/"+ obj.attr("userid");
		//alert(pa);
		window.location.href=pa;
	});

	$('#no').click(function () {
		cancleBtn();
	});
	
	$('#yes').click(function () {
		deleteUser(userObj);
	});

	$(".deleteUser").on("click",function(){
		userObj = $(this);
		changeDLGContent("你确定要删除用户【"+userObj.attr("username")+"】吗？");
		openYesOrNoDLG();
	});
	
	/*$(".deleteUser").on("click",function(){
		var obj = $(this);
		if(confirm("你确定要删除用户【"+obj.attr("username")+"】吗？")){
			$.ajax({
				type:"GET",
				url:path+"/jsp/user.do",
				data:{method:"deluser",uid:obj.attr("userid")},
				dataType:"json",
				success:function(data){
					if(data.delResult == "true"){//删除成功：移除删除行
						alert("删除成功");
						obj.parents("tr").remove();
					}else if(data.delResult == "false"){//删除失败
						alert("对不起，删除用户【"+obj.attr("username")+"】失败");
					}else if(data.delResult == "notexist"){
						alert("对不起，用户【"+obj.attr("username")+"】不存在");
					}
				},
				error:function(data){
					alert("对不起，删除失败");
				}
			});
		}
	});*/
});