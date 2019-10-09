package life.majiang.community.dto;

import life.majiang.community.Exception.CustomizeErrorCode;
import life.majiang.community.Exception.CustomizeException;
import lombok.Data;

@Data
public class ResultDto {
    private  String  message;
    private   Integer  code;
    public static  ResultDto errorOf(String message, Integer code){
        ResultDto resultDto=new ResultDto();
        resultDto.setCode(code);
        resultDto.setMessage(message);
        return resultDto;
    }

    public static ResultDto errorOf(CustomizeErrorCode customizeErrorCode) {
                return  errorOf(customizeErrorCode.getMessage(),customizeErrorCode.getCode());
    }
    public static ResultDto okOf(){
        ResultDto resultDto=new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessage("请求成功");
        return resultDto;
    }

    public static ResultDto errorOf(CustomizeException e) {

        return   errorOf(e.getMessage(),e.getCode());
    }
}
