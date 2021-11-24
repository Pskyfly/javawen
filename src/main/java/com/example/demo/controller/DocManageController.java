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
import javax.servlet.http.HttpSession;
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
    public Object setOperatingWriter(String name, HttpSession session)
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
            session.setAttribute("writername",name);
            resultMap.put("status",200);
            resultMap.put("message","找到该作者");
        }
        return resultMap;
    }
    @RequestMapping(value="/getOperatingWriter",method= RequestMethod.GET)
    @ResponseBody
    public Object getOperatingWriter(HttpSession session)
    {
        resultMap.clear();

        if(session.getAttribute("writername")==null)
        {
            resultMap.put("status",500);
            resultMap.put("message","未获取当前作者");
        }
        else
        {
            resultMap.put("status",200);
            resultMap.put("message","获取当前作者");
            String name=(String)session.getAttribute("writername");
            resultMap.put("data",userService.findUserbynme(name));
        }
        return resultMap;
    }

    @RequestMapping(value="/getdocs",method= RequestMethod.GET)
    @ResponseBody
    public Object getUserList(int page, int limit,String title,HttpSession session)
    {
        resultMap.clear();
        resultMap.put("status", 200);
        resultMap.put("code", 0);
        String writer=(String)session.getAttribute("writername");
        List<Doc> docs = userService.getDocListbyname(writer);
        if(Objects.equals(title, "")) {
            Object some = Tools.docBuildPage(docs, limit, page);
            resultMap.put("data", some);
            resultMap.put("count", docs.size());
            resultMap.put("msg", "用户列表");
            return resultMap;
        }
        else
        {
            List<Doc> some=userService.getsimilarDocList(title);
            resultMap.put("count",some.size());
            resultMap.put("data",Tools.docBuildPage(some,limit,page));
            resultMap.put("message","找到了记录");
            return resultMap;
        }
    }
    @RequestMapping(value="/deletedoc",method= RequestMethod.DELETE)
    @ResponseBody
    public Object deleteDoc(int id,HttpSession session)
    {
        resultMap.clear();
        if(userService.findDocbyid(id)!=null)
        {
            userService.deleteDocbyid(id);
            resultMap.put("status",200);
            resultMap.put("message","删除成功");
            Userwrites writer=userService.getWriterbyname((String)session.getAttribute("writername"));
            int lcount=writer.getCount();
            writer.setCount(lcount-1);
            userService.updateWriter(writer);
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
    public Object addDoc(String title,String content,HttpSession session)
    {
        resultMap.clear();
        Doc doc=new Doc();
        doc.setTitle(title);
        doc.setContent(content);
        String writername=(String)session.getAttribute("writername");
        doc.setUsername(writername);//给文章赋予作者
        Userwrites userwrites=userService.getWriterbyname(writername);
        userwrites.setCount(userwrites.getCount()+1);//当前作者文章数量加一
        userService.updateWriter(userwrites);//修改数据库
        userService.addDoc(doc);
        resultMap.put("status",200);
        resultMap.put("message","添加成功");
        return resultMap;
    }
    @RequestMapping(value="/updateDoc",method= RequestMethod.POST)
    @ResponseBody
    public Object updateDoc(String title,String content,HttpSession session)
    {
        resultMap.clear();
        Doc doc=new Doc();
        doc.setTitle(title);
        doc.setContent(content);
        doc.setUsername((String)session.getAttribute("writername"));//给文章赋予作者
        doc.setId((int)session.getAttribute("docid"));
        userService.updateDoc(doc);
        resultMap.put("data",doc);
        resultMap.put("status",200);
        resultMap.put("message","修改成功");
        return resultMap;
    }
    @RequestMapping(value="/prechoosedoc",method= RequestMethod.POST)
    @ResponseBody
    public Object prechooseDoc(int id,HttpSession session)
    {
        session.setAttribute("docid",id);
        resultMap.put("status",200);
        resultMap.put("message","已经确认当前操作的文档");
        return resultMap;
    }
    @RequestMapping(value="/getoperatingdoc",method= RequestMethod.GET)
    @ResponseBody
    public Object getOperatingDoc(HttpSession session)
    {
        int docid=(int)session.getAttribute("docid");
        resultMap.put("data",userService.findDocbyid(docid));
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
        for (Userwrites temp : writers) {
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
