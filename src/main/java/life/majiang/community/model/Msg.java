package life.majiang.community.model;

import lombok.Data;

import java.util.HashMap;
@Data
public class Msg {
    //返回给客户端的状态码 成功为100 失败为200
    private   int  code;
    //返回给客户端的提示信息
    private    String  msg;
    //储存需要显示的json数据
    private HashMap<String,Object> extend=new HashMap<String,Object>();

    public static Msg success(){
        Msg result=new Msg();
        result.setCode(100);
        result.setMsg("处理成功");
        return  result;
    }
    public static Msg fail(){
        Msg result=new Msg();
        result.setCode(200);
        result.setMsg("处理失败");
        return  result;
    }
    public Msg add(String key,Object value){
        this.getExtend().put(key,value);
        return this;
    }





}
