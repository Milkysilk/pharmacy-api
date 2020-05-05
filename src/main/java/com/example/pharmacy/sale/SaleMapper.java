package com.example.pharmacy.sale;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleMapper {
//    CREATE
    @Insert("insert into sale (medicine_id, recorder_id, type, date, count, total_price) " +
            "values (#{medicineId}, #{recorderId}, #{type}, #{date}, #{count}, #{totalPrice});")
    @SelectKey(statement = "select last_insert_id();", keyProperty = "id", before = false, resultType = int.class)
    int create(Sale sale);

//    READ
    @Select("select * from sale;")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "recorder", column = "recorder_id",
                    one = @One(select = "com.example.pharmacy.user.UserMapper.read")),
            @Result(property = "medicine", column = "medicine_id",
                    one = @One(select = "com.example.pharmacy.basic.MedicineMapper.read")),
    })
    List<Sale> readAll();

//    DELETE
    @Delete("delete from sale where id = #{id};")
    int delete(Integer id);
}
