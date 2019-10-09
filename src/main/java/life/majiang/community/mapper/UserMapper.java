package life.majiang.community.mapper;

import life.majiang.community.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User findByToken(@Param("token")String token);


    public void insert(User user);

    User findByAccountId(String accountId);

    Integer findIdByToken(String token);

    User findById(Long id);
}
