package life.majiang.community.Service;

import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;
    public void createOrUpdate(Question question){
        if(question.getId()==null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
        }else{
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.updateQuestion(question);
        }
    }
    //为问题添加评论
    public void addview(Integer id) {
        questionMapper.addView(id);
    }
}
