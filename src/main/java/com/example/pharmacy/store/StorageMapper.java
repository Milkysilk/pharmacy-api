package com.example.pharmacy.store;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageMapper {
//    CREATE
    @Insert("insert into storage (medicine_id, repository_id, `limit`) " +
            "values (#{medicineId}, #{repoId}, #{limit});")
    @SelectKey(statement = "select last_insert_id();", keyProperty = "id", before = false, resultType = int.class)
    int create(Storage storage);

//    READ
    @Select("select * from storage;")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "repo", column = "repository_id",
                    one = @One(select = "com.example.pharmacy.basic.RepoMapper.read")),
            @Result(property = "medicine", column = "medicine_id",
                    one = @One(select = "com.example.pharmacy.basic.MedicineMapper.read")),
    })
    List<Storage> readAll();

    @Select("select * from storage where medicine_id = #{medicineId} and repository_id = #{repositoryId};")
    Storage readByMedicineIdAndRepositoryId(Integer medicineId, Integer repositoryId);

//    UPDATE
    @Update("update storage set " +
            "`limit` = #{limit}, " +
            "inventory = #{inventory} " +
            "where id = #{id};")
    int update(Storage storage);

//    DELETE
    @Delete("delete from storage where id = #{id};")
    int delete(Integer id);
}
