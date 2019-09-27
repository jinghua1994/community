package life.majiang.community.mapper;

import life.majiang.community.model.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper {
     List<Question> list() ;

    void create(Question question);

    List<Question> findQuestionByCreator(Integer temp_id);

}
