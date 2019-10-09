package life.majiang.community.Exception;

public class CustomizeException extends  RuntimeException {


    private String message;
    private Integer code;

    public CustomizeException(IcustomizeErrorCode icustomizeErrorCode){

        this.code=icustomizeErrorCode.getCode();
        this.message=icustomizeErrorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
