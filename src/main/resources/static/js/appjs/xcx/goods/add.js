$().ready(function() {
    getBrandList();

    getTypeList();
    validateRule();
  /*  $( "#market" ).datepicker({
        minViewMode: 1,
        keyboardNavigation: false,
        forceParse: false,
        autoclose: true,
        todayHighlight: true
    });*/
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
    $('#brand').val("");
    $('#series').val("");
    $('#brand').val($('#brandUuid').find("option:selected").text());
    $('#series').val($('#seriesUuid').find("option:selected").text());
	$.ajax({
		cache : true,
		type : "POST",
		url : "/xcx/goods/save",
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

$("#brandUuid").bind("change",function(){
    var brandUuid = $(this).val();
    if (typeof(brandUuid) == "undefined"){return;}
    console.log(brandUuid);
    var url = "/xcx/series/getSeries/"+brandUuid;
    $.get(url,function(data){
        if(data.code==0){
            var json = data.data;
            $("#seriesUuid").empty();
            for ( var i = 0; i < json.length; i++){
                var Cname = !json[i].cname?"":json[i].cname;
                var Ename = !json[i].ename?"":json[i].ename;

                $("#seriesUuid").prepend('<option value="' + json[i].uuid + '">' + Cname+Ename + '</option>')
            };
            $("#seriesUuid").prepend('<option value="0">请选择</option>')
        }else{
            parent.layer.alert(data.msg)
        }
    })
});


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
                    var Cname = !json[i].cname?"":json[i].cname;
                    var Ename = !json[i].ename?"":json[i].ename;
                    $("#brandUuid").prepend('<option value="' + json[i].uuid + '">' + Cname+Ename + '</option>')

                };
                $("#brandUuid").prepend('<option value="0">请选择</option>')
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
                    $("#type").prepend('<option value="' + json[i].id + '">' + json[i].name + '</option>')
                };
                $("#type").prepend('<option value="0">请选择</option>')
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