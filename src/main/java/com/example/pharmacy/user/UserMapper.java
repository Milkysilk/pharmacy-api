package com.example.pharmacy.user;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

//    CREATE
    @Insert("insert into user (username, password, name, gender, phone, email, enable) " +
            "values(#{username}, #{password}, #{name}, #{gender}, #{phone}, #{email}, #{enable})")
    @SelectKey(statement = "select last_insert_id();", keyProperty = "id", before = false, resultType = int.class)
    int create(User user);

//    @Insert("insert into user_role(user_id, role_id) values(#{userId}, #{roleId});")
//    @SelectKey(statement = "select last_insert_id();", keyProperty = "id", before = false, resultType = int.class)
    @Insert({"<script>" +
            "insert into user_role (user_id, role_id) values " +
            "<foreach collection='roles' item='role' index='index' open='(' separator='), (' close=')'>"+
            "#{userId}, #{role.id}" +
            "</foreach>" +
            "</script>"})
    int addRole(Integer userId, @Param("roles") List<Role> roles);

//    READ
    @Select("select * from user where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles", column = "id", javaType = List.class,
                    many = @Many(select = "com.example.pharmacy.user.RoleMapper.readByUserId"))
    })
    User read(Integer id);

    @Select("select * from user where id = #{id};")
    User readWithoutRoles(Integer id);

    @Select("select * from user")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles", column = "id", javaType = List.class,
                    many = @Many(select = "com.example.pharmacy.user.RoleMapper.readByUserId"))
    })
    List<User> readAll();

    @Select("select * from user where username = #{username};")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles", column = "id", javaType = List.class,
                    many = @Many(select = "com.example.pharmacy.user.RoleMapper.readByUserId"))
    })
    User readByName(String username);

//    UPDATE
//    @Update("update user set username = #{username}, password = #{password}, name = #{name}, gender = #{gender}, phone = #{phone}, email = #{email} where id = #{id};")
    @Update("<script>" +
            "update user set name = #{name}, gender = #{gender}, phone = #{phone}, email = #{email}, enable = #{enable}" +
            "<if test='username != null and username != \"\"'>" +
            ", username = #{username}" +
            "</if>" +
            "<if test='password != null and password != \"\"'>" +
            ", password = #{password}" +
            "</if>" +
            "<if test='enable != null'>" +
            ", enable = #{enable}" +
            "</if>" +
            "where id = #{id};" +
            "</script>")
    int update(User user);

//    DELETE
    @Delete("delete from user where id = #{id}")
    int delete(Integer id);

}
