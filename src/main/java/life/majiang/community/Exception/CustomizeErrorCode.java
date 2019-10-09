package life.majiang.community.Exception;

public enum CustomizeErrorCode implements  IcustomizeErrorCode {

       QUESTION_NOT_FOUND(2001,"寻找的问题不存在") ,
       TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),
       NO_LOGIN(2003,"抱歉，未登录不能进行评论"),
        SYS_ERROR(2004,"系统错误"),
        TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
        COMMENT_NOT_FOUND(2006,"评论不存在"),
    COMMENT_IS_EMPTY(2007,"输入的评论不能为空");
        private String message;
        private Integer code;

    CustomizeErrorCode(Integer code,String message) {
        this.code=code;
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

}
