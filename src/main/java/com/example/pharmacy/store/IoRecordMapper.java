package com.example.pharmacy.store;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IoRecordMapper {
//    CREATE
    @Insert("insert into iorecord (medicine_id, repository_id, recorder_id, type, quantity) " +
            "values (#{medicineId}, #{repoId}, #{recorderId}, #{type}, #{quantity});")
    @SelectKey(statement = "select last_insert_id();", keyProperty = "id", before = false, resultType = int.class)
    int create(IoRecord ioRecord);

//    READ
    @Select("select * from iorecord;")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "repo", column = "repository_id",
                    one = @One(select = "com.example.pharmacy.basic.RepoMapper.read")),
            @Result(property = "medicine", column = "medicine_id",
                    one = @One(select = "com.example.pharmacy.basic.MedicineMapper.read")),
            @Result(property = "recorder", column = "recorder_id",
                    one = @One(select = "com.example.pharmacy.user.UserMapper.read"))
    })
    List<IoRecord> readAll();

//    UPDATE

//    DELETE
    @Delete("delete from iorecord where id = #{id};")
    int delete(Integer id);
}
