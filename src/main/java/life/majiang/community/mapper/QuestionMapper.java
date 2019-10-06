package life.majiang.community.mapper;

import life.majiang.community.dto.QuestionDto;
import life.majiang.community.model.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper {
     List<QuestionDto> list() ;

    void create(Question question);

    List<QuestionDto> findQuestionByCreator(Integer temp_id);

    void updateQuestion(Question question);

    QuestionDto findQuestionDtoById(Integer id);

    Question findQuestionById(Integer id);

    void addView(Integer id);
}
