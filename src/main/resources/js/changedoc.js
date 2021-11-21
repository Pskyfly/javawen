//Demo
layui.use('form', function(){
    var form = layui.form;

    //监听提交
    form.on('submit(formDemo)', function(data){
        //layer.msg(JSON.stringify(data.field));
        var dat=document.getElementById("in");
        $.ajax({
            url:"/updateDoc",
            dataType:"json",
            method:"post",
            data:{
                title:dat.title.value,
                content:dat.content.value
            },
            success:function (result){
                alert("修改成功");
                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                parent.layer.close(index); //再执行关闭
            },
            error:function (result) {
                alert("未知错误");
            }
        });
        return false;
    });
});