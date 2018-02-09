$().ready(function() {
    getBrandList()
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/xcx/series/update",
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


function getBrandList() {
    $.ajax({
        cache : true,
        type : "GET",
        url : "/xcx/brand/type",
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            if (data.code == 0) {
                var json = data.data;
                $("#brandId").empty();
                for ( var i = 0; i < json.length; i++){
                    $("#brandId").prepend('<option value="' + json[i].uuid + '">' + json[i].cname+','+json[i].ename + '</option>')
                };
                $("#brandId").prepend('<option value="0">请选择</option>')
            } else {
                parent.layer.alert(data.msg)
            }

        }
    });

}