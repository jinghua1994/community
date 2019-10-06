package life.majiang.community.Enums;

public enum  ContentTypeEnum {
    Question(1),
    Comment(2);
    private   Integer  type;
    ContentTypeEnum(Integer type){
        this.type=type;
    }
}
