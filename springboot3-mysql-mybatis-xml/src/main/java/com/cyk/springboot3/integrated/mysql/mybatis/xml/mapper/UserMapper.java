package com.cyk.springboot3.integrated.mysql.mybatis.xml.mapper;

import com.cyk.springboot3.integrated.mysql.mybatis.xml.entity.User;
import com.cyk.springboot3.integrated.mysql.mybatis.xml.service.dto.UserPageDTO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cyk
 * @date 2023/10/25 14:27
 */
@Mapper
public interface UserMapper {


    int deleteByPrimaryKey(Long id);


    int insert(User record);


    int insertSelective(User record);


    User selectByPrimaryKey(Long id);


    int updateByPrimaryKeySelective(User record);

    List<User> selectAll();


    Page<User> queryByPage(@Param("userPageDTO") UserPageDTO userPageDTO);





}
