$().ready(function() {
	validateRule();
    getBrandList();
    getTypeList();
    querySeries();

});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
    $('#brand').val("");
    $('#series').val("");
    $('#brand').val($('#brandUuid').find("option:selected").text());
    $('#series').val($('#seriesUuid').find("option:selected").text());
/*
    //被删除图片的名字数组
    var delUrls = parseUrls($('#moduleGallery').editableGallery('getDeletedUrls'));

    //被删除图片的Url
    var delUrls1=$('#moduleGallery').editableGallery('getDeletedUrls');*/
  /* var json = $("#imgurl").val();
   for(var a=0;a<json.length;a++){
        for (var b=0;b<delUrls1.length;b++){
            if(json[a].fileUrl ==delUrls1[b].fileUrl){
                json.remove(a);
            }
        }
    }*/
    var josn = JSON.stringify(imgURLArray);;
    $("#imgurl").val(josn);
    $.ajax({
		cache : true,
		type : "POST",
		url : "/xcx/goods/update",
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
/*//对图片的路径进行处理（我的数据库里只存了图片的名字）
function parseUrls(fileUrls) {
    var result = [];
    if (fileUrls) {
        $.each(fileUrls, function(index, value) {
            result.push(value.substring(value.lastIndexOf("/") + 1));
        });
    }
    return result;
}*/

$("#brandUuid").bind("change",function(){
    querySeries();
});
function querySeries() {
    var brandUuid = $("#brandUuid").val();
    if (typeof(brandUuid) == "undefined"){return;}
    var url = "/xcx/series/getSeries/"+brandUuid;
    $.get(url,function(data){
        if(data.code==0){
            var json = data.data;
            $("#seriesUuid").empty();
            for ( var i = 0; i < json.length; i++){
                var Cname = !json[i].cname?"":json[i].cname;
                var Ename = !json[i].ename?"":json[i].ename;
                if ($("#seriesUuid").attr('data-val')==json[i].uuid){
                    $("#seriesUuid").prepend('<option selected value="' + json[i].uuid + '">' + Cname+Ename + '</option>')
                }else {
                    $("#seriesUuid").prepend('<option value="' + json[i].uuid + '">' + Cname+Ename + '</option>')
                }

            };
        }else{
            parent.layer.alert(data.msg)
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
                $("#brandUuid").empty();
                for ( var i = 0; i < json.length; i++){
                    if($("#brandUuid").attr('data-val') ==json[i].uuid ){
                        $("#brandUuid").prepend('<option selected value="' + json[i].uuid + '">' + json[i].cname+','+json[i].ename + '</option>')
                    }else {
                        $("#brandUuid").prepend('<option value="' + json[i].uuid + '">' + json[i].cname+','+json[i].ename + '</option>')
                    }

                };
            } else {
                parent.layer.alert(data.msg)
            }

        }
    });
}

function getTypeList() {
    $.ajax({
        cache : true,
        type : "GET",
        url : "/api/dict/getAllDict",
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            if (data.code == 0) {
                var json = data.data.zb_type;
                $("#type").empty();
                for ( var i = 0; i < json.length; i++){
                    if($("#type").attr('data-val') ==json[i].value ){
                        $("#type").prepend('<option selected value="' + json[i].value + '">' + json[i].name + '</option>')
                    }else {
                        $("#type").prepend('<option value="' + json[i].value + '">' + json[i].name + '</option>')
                    }

                };
                $("#type").prepend('<option value="0">请选择</option>')
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
            cname : "required",
            price : "required",
            quality : "required",
            type : "required",
            seriesUuid : "required",
            brandUuid : "required",
            number : "required"
        },
        messages : {
            cname : "请填写商品标题",
            price : "请填写商品价格",
            quality : "请填写商品材质",
            type : "请填写商品类型",
            seriesUuid : "请填写商品系列",
            brandUuid : "请填写商品品牌",
            number : "请填写商品编号"
        }
	})
}


function returnList() {
    var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
    parent.layer.close(index);
}