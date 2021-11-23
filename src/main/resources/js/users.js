//列出所有用户
var table = layui.table;
var str="";
layui.use('table', function () {
    table.render({
        elem: '#demo'
        ,id:'theusers'
        ,where:{
            name:str
        }
        , url: "/list"//数据接口
        , page: true //开启分页
        , cols: [[ //表头
            { field: 'id', title: 'ID', width: 100, sort: true }
            , { field: 'name', title: '学生姓名', width: 100 }
            , { field: 'password', title: '学生密码', width: 100 }
            , { field: 'email', title: '学生邮箱', width: 200 }
            , { field: 'birthday', title: '学生生日', width: 110 }
            , { field: 'money', title: '学生余额', width: 110 }
            , { field: '编辑', toolbar: '#barDemo' }
        ]]
    });
    return false;
});
//查找某一个用户
layui.use('form', function(){
    var form = layui.form;
    form.on('submit(findname)', function(data){
        dat=document.getElementById("find");
        str= dat.findbyname.value;
        layui.use('table', function () {
            table.render({
                elem: '#demo'
                ,id:'theusers'
                , url: "/list"//数据接口
                ,method:'post'
                ,where:{
                    name:str
                }
                , page: true //开启分页
                , cols: [[ //表头
                    { field: 'id', title: 'ID', width: 100, sort: true }
                    , { field: 'name', title: '学生姓名', width: 100 }
                    , { field: 'password', title: '学生密码', width: 100 }
                    , { field: 'email', title: '学生邮箱', width: 200 }
                    , { field: 'birthday', title: '学生生日', width: 110 }
                    , { field: 'money', title: '学生余额', width: 110 }
                    , { field: '编辑', toolbar: '#barDemo' }
                ]]
            });
        });
        return false;
    });
});

table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
    var data = obj.data; //获得当前行数据
    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
    var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

    if (layEvent === 'detail') { //查看
        //do somehing
    } else if (layEvent === 'delete') { //删除
        layer.confirm('真的删除该用户吗', function (index) {
            //alert(data.name);
            $.ajax({
                url:"/delete",
                type:"delete",
                dataType:"json",
                data:{
                    name:data.name
                },
                success:function (result) {
                    if(result.status==0)
                        layer.msg("删除成功");
                    else layer.msg("用户不存在");
                },
                error:function (){
                    layer.msg("未知错误");
                }
            });
            obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
            layer.close(index);
            //向服务端发送删除指令
        });
    } else if (layEvent === 'update') { //编辑
        //同步更新缓存对应的值
        $.ajax({
            url:"prechoose",
            method: "post",
            dataType: "json",
            data:{
                name:data.name
            },
            error:function (){
                layer.msg("未知错误");
            }
        });
        layer.open({
            type:2,
            content:["changeuser.html","no"],
            title:"修改用户",
            area:["700px","600px"],
            end:function () {
                window.location.reload();
            }
        });
    }
});
