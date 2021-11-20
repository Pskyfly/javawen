//Demo
var baseUrl = "http://localhost:8080"
layui.use('form', function (message) {
    var form = layui.form;

    // 监听提交
    form.on('submit(formDemo)', function (data) {
        dat=document.getElementById("post");
        var name = dat.name.value;
        var password = dat.password.value;
        var load = layer.load();
        $.ajax({
            url:"/login",
            type:"post",
            dataType:"json",
            data:{
                name:name,
                password:password,
            },
            success:function (result) {
                if(result.status==200)
                    window.location.assign("main.html");
                else alert("用户名密码错误");
            },
            error:function (){
                alert("未知错误");
            }
        });
        return false;
    });
    return false;
});