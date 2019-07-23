package com.lvzhu.dal.dao;

import com.lvzhu.dal.dataobject.UserDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import java.util.Map;


public interface UserMapper {

	int save(UserDO userDO);

	int saveSelective(UserDO userDO);

    int deleteById(@Param("id")Integer id);

    int deleteBatchById(@Param("ids")List<Integer> ids);

    int updateById(UserDO userDO);

    UserDO getById(@Param("id")Integer id);

    List<UserDO> listById(@Param("ids")List<Integer> ids);

    List<UserDO> listByParam(Map params);

    Integer countByParam(Map params);
}
