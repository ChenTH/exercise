package database.mybatis.mapper;

import database.mybatis.dao.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {

    UserInfo getUserById(@Param("userId") Long id);
}
