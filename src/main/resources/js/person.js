function logout() {
    $.ajax({
        url:"/logout",
        error:function (result) {
            alert("退出失败");
        }
    });
}
$(document).ready(function () {
    $.ajax({
        url:"/loginuser",
        type: "get",
        dataType: "json",
        success:function (result) {
            var ret=result.data;
            if(ret.name==null) return false;
            var name,password,email,money,birthday;
            name=ret.name;
            password=ret.password;
            birthday=ret.birthday;
            email=ret.email;
            money=ret.money;
            document.getElementById("name").value=name;
            document.getElementById("email").value=email;
            document.getElementById("date").value=birthday;
            document.getElementById("amoney").value=money;
        }
    });
});

layui.use('laydate', function () {
    var laydate = layui.laydate;

    //执行一个laydate实例
    laydate.render({
        elem: '#date' //指定元素
    });
});
layui.use('form', function () {
    var form = layui.form;

    //修改信息的事件--------------------------------------
    form.on('submit(formDemo)', function (data) {
        //alert("开始");
        dat=document.getElementById("in");
        var name,password,email,money,birthday;
        email=dat.email.value;
        var em = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
        if (em.test(email) == true) {
            //layer.msg(JSON.stringify(data.field));
            name = dat.name.value;
            email=dat.email.value;
            birthday=dat.date.value;
            money=dat.amoney.value;
            $.ajax({
                url:"/selfupdate",
                type:"post",
                dataType:"json",
                data:{
                    name:name,
                    email:email,
                    birthday:birthday,
                    money:money
                },
                success:function (result) {
                    alert(result.message);
                    //window.location.reload();
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

    //修改密码的事件--------------------------------
    form.on('submit(passwordsubmit)', function (data) {
        dat=document.getElementById("pwd");
        var lastpassword=dat.lastpassword.value;
        var newpassword=dat.newpassword.value;
        var comfirmpassword=dat.comfirmpassword.value;
        if (newpassword==comfirmpassword) {
            //layer.msg(JSON.stringify(data.field));
            $.ajax({
                url:"/updatepassword",
                type:"post",
                dataType:"json",
                data:{
                    lastpassword:lastpassword,
                    newpassword:newpassword
                },
                success:function (result) {
                    alert(result.message);
                },
                error:function (){
                    alert("未知错误");
                }
            });
            return false;
        }
        else{
            alert("两次密码不一致");
        }
        return false;
    });
    return false;
});