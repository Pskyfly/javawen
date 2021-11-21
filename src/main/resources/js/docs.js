//列出所有用户
var table = layui.table;
var str="";
layui.use('table', function () {
    table.render({
        elem: '#demo'
        , height: 250
        ,id:'theusers'
        ,where:{
            name:str
        }
        , url: "/writerlist"//数据接口
        , page: true //开启分页
        , cols: [[ //表头
            { field: 'id', title: 'ID', width: 300, sort: true }
            , { field: 'username', title: '学生姓名', width: 300 }
            , { field: 'count', title: '文章数量', width: 300 }
            , { field: '进入文章管理', toolbar: '#barDemo' }
        ]]
    });
    return false;
});
//查找某一个用户
//工具条事件
layui.use('form', function(){
    var form = layui.form;
    //监听提交
    form.on('submit(findname)', function(data){
        dat=document.getElementById("find");
        str= dat.findbyname.value;

        layui.use('table', function () {
            table.render({
                elem: '#demo'
                , height: 250
                ,id:'theusers'
                , url: "/writerlist"//数据接口
                ,where:{
                    name:str
                }
                , page: true //开启分页
                , cols: [[ //表头
                    { field: 'id', title: 'ID', width: 300, sort: true }
                    , { field: 'username', title: '学生姓名', width: 300 }
                    , { field: 'count', title: '文章数量', width: 300 }
                    , { field: '进入文章管理', toolbar: '#barDemo' }
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
    if (layEvent === 'update') { //编辑
        $.ajax({
            url:"setOperatingWriter",
            dataType:"json",
            data:{
                name:data.username
            },
            error:function (result) {
                alert("未知错误");
            }
        });
        window.location.assign("Docmanage.html");
    }
    return false;
});
