layui.use('laydate', function () {
    var laydate = layui.laydate;

    //执行一个laydate实例
    laydate.render({
        elem: '#date' //指定元素
    });
});
layui.use('form', function () {
    var form = layui.form;

    //监听提交
    form.on('submit(formDemo)', function (data) {
        dat=document.getElementById("in");
        email=dat.email.value;
        var em = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
        if (em.test(email) == true) {
            //layer.msg(JSON.stringify(data.field));
            var name = dat.name.value;
            var password = dat.password.value;
            var email=dat.email.value;
            var birthday=dat.birthday.value;
            $.ajax({
                url:"/register",
                type:"post",
                dataType:"json",
                data:{
                    name:name,
                    password:password,
                    email:email,
                    birthday:birthday
                },
                success:function (result) {
                    if(result.status==200) {
                        alert("注册成功");
                        window.location.assign("register.html");
                    }
                    else alert("用户名已存在");
                },
                error:function (){
                    alert("未知错误");
                }
            });
            return false;
        }
        else{
            alert("请输入正确的邮箱");
        }
        return false;
    });
});
function returntologin(){
    window.location.assign("login.html");
}