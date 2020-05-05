package com.example.pharmacy.basic;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoMapper {
//    CREATE
    @Insert("insert into repository (name, address, note) values (#{name}, #{address}, #{note});")
    @SelectKey(statement = "select last_insert_id();", keyProperty = "id", before = false, resultType = int.class)
    int create(Repo repo);

//    READ
    @Select("select * from repository;")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "medicines", column = "id", javaType = List.class,
                    many = @Many(select = "com.example.pharmacy.basic.MedicineMapper.readByRepoId"))
    })
    List<Repo> readAll();

    @Select("select * " +
            "from repository r, storage s " +
            "where r.id = s.repository_id and s.medicine_id = #{medicineId};")
    List<Repo> readByMedicineId(Integer medicineId);

    @Select("select * from repository where id = #{id};")
    Repo read(Integer id);

//    UPDATE
    @Update("update repository set name = #{name}, address = #{address}, note = #{note} where id = #{id};")
    int update(Repo repo);

//    DELETE
    @Delete("delete from repository where id = #{id};")
    int delete(Integer id);
}
