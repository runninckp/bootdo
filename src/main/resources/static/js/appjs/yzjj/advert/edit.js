$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
    var josn = JSON.stringify(imgURLArray);;
    $("#imgurl").val(josn);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/yzjj/advert/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}

// 现有图片
$('#moduleGallery').editableGallery({

    //此处可根据自己的需要进行修改参数，我这里第一个参数：一条数据的ID，第二个参数：这条数据的类型（不需要，可不要），第三个参数：这条数据对应的多张图片的路径集合
    urls: handleFileUrl(),    //2表示模块:文件类型
    canDelete: true        //这个参数定义，鼠标移动到图片上时，显示删除按钮 （查看时，不需要编辑图片，可不要这个属性）
});
function handleFileUrl() {
    var result = [];
    /*if (paths) {
        $.each(paths, function(index, value) {
            result.push("seeModuleOrFunctionFile/" + ownerID + "/" +fileType+ "/" + value);    //seeModuleOrFunctionFile为后台请求图片的方法
        });
    }*/
    if($("#imgurl").val()){
        var json =JSON.parse( $("#imgurl").val());
        $.each(json, function(index, value) {
            console.log("图片链接："+value.fileUrl);
            result.push(value.fileUrl);    //seeModuleOrFunctionFile为后台请求图片的方法
        });
    }
    return result;
}


function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入名字"
			}
		}
	})
}