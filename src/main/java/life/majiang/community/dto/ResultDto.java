package life.majiang.community.dto;

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
}
