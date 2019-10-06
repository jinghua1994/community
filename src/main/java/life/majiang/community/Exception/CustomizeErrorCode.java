package life.majiang.community.Exception;

public enum CustomizeErrorCode implements  IcustomizeErrorCode {

       QUESTION_NOT_FOUND("寻找的问题不存在") ;
        private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
