package libai.aliyun.fileroom.mapper;

import libai.aliyun.fileroom.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UserEntityMapper extends Mapper<UserEntity> {

    UserEntity getUserByNameAndPd(@Param("username") String username,@Param("password") String password);

}