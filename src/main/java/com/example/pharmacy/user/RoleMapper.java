package com.example.pharmacy.user;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {

    @Select("select * from role;")
    List<Role> readAll();

    @Select("select * " +
            "from role r, user_role ur " +
            "where r.id = ur.role_id and ur.user_id = #{userId};")
    List<Role> readByUserId(Integer userId);

//    @Insert("insert into role(name, name_zh, description) values(#{name}, #{nameZh}, #{description});")
//    @SelectKey(statement = "select last_insert_id();", keyProperty = "id", before = false, resultType = int.class)
//    public int addRole(Role role);
}
