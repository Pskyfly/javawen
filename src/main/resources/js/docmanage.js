$(document).ready(function () {
    $.ajax({
        url:"/getOperatingWriter",
        type: "get",
        dataType: "json",
        success:function (result) {
            var ret=result.data;
            var name,password,email,money,birthday;
            name=ret.name;
            birthday=ret.birthday;
            email=ret.email;
            money=ret.money;
            document.getElementById("name").innerHTML=name;
            document.getElementById("email").innerHTML=email;
            document.getElementById("birthday").innerHTML=birthday;
            document.getElementById("money").innerHTML=money;
        }
    });
});
//-----------------------------数据表格------------------------------------
var str="";
var table = layui.table;
layui.use('table', function(){
    //第一个实例
    table.render({
        elem: '#demo'
        ,height: 220
        ,url: '/getdocs' //数据接口
        ,page: true //开启分页
        ,dataType:"json"
        ,where:{
            title:str
        }
        ,cols: [[ //表头
            {field: 'id', title: '序号', width:80, sort: true, fixed: 'left'}
            ,{field: 'title', title: '标题', width:200}
            ,{field: 'content', title: '内容', width:500}
            , { field: '编辑', toolbar: '#barDemo' }
        ]]
    });

});


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
                , height: 220
                ,id:'theusers'
                , url: "/getdocs"//数据接口
                ,where:{
                    title:str
                }
                , page: true //开启分页
                , cols: [[ //表头
                    {field: 'id', title: '序号', width:80, sort: true, fixed: 'left'}
                    ,{field: 'title', title: '标题', width:200}
                    ,{field: 'content', title: '内容', width:500}
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
        layer.confirm('真的删除该文章吗', function (index) {
            //alert(data.name);
            $.ajax({
                url:"/deletedoc",
                type:"delete",
                dataType:"json",
                data:{
                    id:data.id
                },
                success:function (result) {
                    if(result.status==200)
                        layer.msg("删除成功");
                    else layer.msg("文章不存在");
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
            url:"prechoosedoc",
            method: "post",
            dataType: "json",
            data:{
                id:data.id
            },
            error:function (){
                layer.msg("未知错误");
            }
        });
        layer.open({
            type:2,
            content:["changedoc.html","no"],
            title:"修改用户",
            area:["700px","600px"],
            end:function () {
                window.location.reload();
            }
        });
    }
});