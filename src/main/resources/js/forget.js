layui.use('laydate', function () {
    var laydate = layui.laydate;

    //执行一个laydate实例
    laydate.render({
        elem: '#date' //指定元素
    });
});
layui.use(['form'], function () {
    var form = layui.form;
    //var layer=layui.layer;
    //监听提交
    form.on('submit(formDemo)', function (data) {
        var dat=document.getElementById("in");
        if(dat.password.value!=dat.firm.value)
        {
            alert("两次密码不一致！");
            return false;
        }
        var name=dat.name.value;
        var last=dat.last.value;
        var password=dat.password.value;
        $.ajax({
            url:"/forget",
            type:"post",
            dataType:"json",
            data:{
                name:name,
                last:last,
                password:password,
            },
            success:function (result) {
                alert(result.message);
                //layer.msg(result.message);
            },
            error:function (){
                alert("未知错误");
            }
        });
    });
});

function CheckPassword(){
    var dat=document.getElementById("in");
    if(dat.password.value!=dat.firm.value)
    {
        alert("两次密码不一致！");
        return false;
    }
}
function returntologin(){
    window.location.assign("login.html");
}