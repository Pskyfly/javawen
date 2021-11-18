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
        var dat=document.getElementById("in");
        if(dat.password.value!=dat.firm.value)
        {
            alert("两次密码不一致！");
            return false;
        }
        // email = prompt("请输入邮箱：");
        // var em = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
        // if (em.test(email) == true) {
        //     alert('密码修改为:'+dat.password.value);
        //     return false;
        // }
        return false;
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