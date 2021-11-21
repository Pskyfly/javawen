package com.example.demo.controller;

import com.example.demo.bean.Doc;
import com.example.demo.bean.UserT;
import com.example.demo.bean.Userwrites;
import com.example.demo.service.UserService;
import com.example.demo.tools.Tools;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

@Controller
public class DocManageController {
    @Resource
    UserService userService;//所有的service都在这里，并没有分开

    protected Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    @RequestMapping(value="/writerlist",method= RequestMethod.GET)
    @ResponseBody
    public Object getWriterList(int page, int limit ,String name)
    {
        resultMap.clear();
        resultMap.put("status", 200);
        resultMap.put("code", 0);
        if(Objects.equals(name, "")) {

            List<Userwrites> writers = userService.getWriterList();
            Object some = Tools.writerBuildPage(writers, limit, page);
            resultMap.put("data", some);
            resultMap.put("count", writers.size());
            resultMap.put("msg", "用户列表");
            return resultMap;
        }
        else
        {
            if(userService.getWriterbyname(name)==null)
            {
                resultMap.put("msg", "用户不存在");
                resultMap.put("count",0);
                resultMap.put("data",null);
                return resultMap;
            }
            else
            {
                List<Userwrites> list = new ArrayList<>();
                list.add(userService.getWriterbyname(name));
                resultMap.put("data",list);
                resultMap.put("count", 1);
                resultMap.put("msg","找到该用户");
                return resultMap;
            }
        }
    }

    @RequestMapping(value="/setOperatingWriter",method= RequestMethod.GET)
    @ResponseBody
    public Object setOperating(String name)
    {
        resultMap.clear();
        Userwrites writer=userService.getWriterbyname(name);
        if(writer==null)
        {
            resultMap.put("status",500);
            resultMap.put("message","作者不存在");
        }
        else
        {
            Tools.operatingwriter.copyWriter(writer);
            resultMap.put("status",200);
            resultMap.put("message","找到该作者");
        }
        return resultMap;
    }
    @RequestMapping(value="/getOperatingWriter",method= RequestMethod.GET)
    @ResponseBody
    public Object getOperating()
    {
        resultMap.clear();

        if(Tools.operatingUser==null)
        {
            resultMap.put("status",500);
            resultMap.put("message","未获取当前作者");
        }
        else
        {
            resultMap.put("status",200);
            resultMap.put("message","获取当前作者");
            String name=Tools.operatingwriter.getUsername();
            resultMap.put("data",userService.findUserbynme(name));
        }
        return resultMap;
    }

    @RequestMapping(value="/getdocs",method= RequestMethod.GET)
    @ResponseBody
    public Object getUserList(int page, int limit,String title)
    {
        resultMap.clear();
        resultMap.put("status", 200);
        resultMap.put("code", 0);
//        String name=Tools.lastquery;
        List<Doc> docs = userService.getDocListbyname(Tools.operatingwriter.getUsername());
        if(Objects.equals(title, "")) {
            Object some = Tools.docBuildPage(docs, limit, page);
            resultMap.put("data", some);
            resultMap.put("count", docs.size());
            resultMap.put("msg", "用户列表");
            return resultMap;
        }
        else
        {
            List<Doc> some=new ArrayList<Doc>();
            for (int i=0;i<docs.size();i++)
            {
                if(Objects.equals(docs.get(i).getTitle(), title)||title==null)
                {
                    some.add(docs.get(i));
                }
            }
            resultMap.put("count",some.size());
            resultMap.put("data",Tools.docBuildPage(some,limit,page));
            resultMap.put("message","找到了记录");
            return resultMap;
        }
    }
    @RequestMapping(value="/deletedoc",method= RequestMethod.DELETE)
    @ResponseBody
    public Object deleteDoc(int id)
    {
        resultMap.clear();
        if(userService.findDocbyid(id)!=null)
        {
            userService.deleteDocbyid(id);
            resultMap.put("status",200);
            resultMap.put("message","删除成功");
            int lcount=Tools.operatingwriter.getCount();
            Tools.operatingwriter.setCount(lcount-1);
            userService.updateWriter(Tools.operatingwriter);
        }
        else
        {
            resultMap.put("status",500);
            resultMap.put("message","文档不存在");
        }
        return resultMap;
    }
    @RequestMapping(value="/addDoc",method= RequestMethod.POST)
    @ResponseBody
    public Object addDoc(String title,String content)
    {
        resultMap.clear();
        Doc doc=new Doc();
        doc.setTitle(title);
        doc.setContent(content);
        doc.setUsername(Tools.operatingwriter.getUsername());//给文章赋予作者
        Tools.operatingwriter.setCount(Tools.operatingwriter.getCount()+1);//当前作者文章数量加一
        userService.updateWriter(Tools.operatingwriter);//修改数据库
        userService.addDoc(doc);
        resultMap.put("status",200);
        resultMap.put("message","添加成功");
        return resultMap;
    }
    @RequestMapping(value="/updateDoc",method= RequestMethod.POST)
    @ResponseBody
    public Object updateDoc(String title,String content)
    {
        resultMap.clear();
        Doc doc=new Doc();
        doc.setTitle(title);
        doc.setContent(content);
        doc.setUsername(Tools.operatingwriter.getUsername());//给文章赋予作者
        doc.setId(Tools.operatingDoc.getId());
        userService.updateDoc(doc);
        resultMap.put("data",doc);
        resultMap.put("status",200);
        resultMap.put("message","修改成功");
        return resultMap;
    }
    @RequestMapping(value="/prechoosedoc",method= RequestMethod.POST)
    @ResponseBody
    public Object prechooseDoc(int id)
    {
        Doc doc=userService.findDocbyid(id);
        Tools.operatingDoc.setContent(doc.getContent());
        Tools.operatingDoc.setTitle(doc.getTitle());
        Tools.operatingDoc.setUsername(Tools.operatingwriter.getUsername());
        Tools.operatingDoc.setId(id);
        resultMap.put("status",200);
        resultMap.put("message","已经确认当前操作的文档");
        return resultMap;
    }
    @RequestMapping(value="/getwriterdata",method= RequestMethod.GET)
    @ResponseBody
    public Object getWriters()
    {
        resultMap.clear();
        List<Userwrites> writers=userService.getWriterList();
        List<String> categories=new ArrayList<String>();
        List<Integer> values=new ArrayList<Integer>();
        for(int i=0;i<writers.size();i++)
        {
            Userwrites temp=writers.get(i);
            categories.add(temp.getUsername());
            values.add(temp.getCount());
        }
        resultMap.put("status",200);
        resultMap.put("message","已经获取作者信息");
        resultMap.put("categories",categories);
        resultMap.put("values",values);
        return resultMap;
    }
}
