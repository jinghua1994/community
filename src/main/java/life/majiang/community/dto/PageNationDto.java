package life.majiang.community.dto;

import life.majiang.community.model.Question;
import lombok.Data;

import java.util.List;
@Data
public class PageNationDto {
    private List<Question> questionList;
    private boolean showPrevious;
    private  boolean showFirstPage;
    private boolean showNext;
    private boolean nextPage;
    private Integer page;
    private List<Integer> pages;


    private void  setPageNationDto(Integer totalCount , Integer page, Integer size){
            Integer totalpage =0;
            if(totalCount%size==0){
                showPrevious=false;
            }else {
                showPrevious=true;
            }
            if(page==totalpage){
                showNext=false;
            }else{
                showNext=true;
            }



    }





 }
