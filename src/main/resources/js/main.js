$(document).ready(function () {
    $.ajax({
        url:"/getlogstatus",
        success:function (result) {
            if(result.logstatus==0)
                window.location.assign("404.html");
            else {
                if(document.body.clientWidth<900)
                    window.location.assign("person.html");
                layer.msg("欢迎");
            }
        },
        error:function (result) {
            alert("未知错误");
        }
    })
    return false;
});
function logout() {
    $.ajax({
        url:"/logout",
        error:function (result) {
            alert("退出失败");
        }
    });
}