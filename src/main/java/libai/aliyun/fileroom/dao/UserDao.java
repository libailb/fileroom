package libai.aliyun.fileroom.dao;

import libai.aliyun.fileroom.entity.UserEntity;
import libai.aliyun.fileroom.mapper.UserEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDao {
    @Autowired
    private UserEntityMapper mapper;

    public UserEntity getUser(String name,String password){
        return mapper.getUserByNameAndPd(name,password);
    }



}
