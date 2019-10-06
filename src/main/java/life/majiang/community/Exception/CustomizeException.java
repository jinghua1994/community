package life.majiang.community.Exception;

public class CustomizeException extends  RuntimeException {


    private String message;


    public CustomizeException(String message){
        this.message=message;
    }
    public CustomizeException(IcustomizeErrorCode icustomizeErrorCode){
        this.message=icustomizeErrorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
