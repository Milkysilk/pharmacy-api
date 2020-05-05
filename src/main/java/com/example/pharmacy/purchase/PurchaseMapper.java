package com.example.pharmacy.purchase;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseMapper {
//    CREATE
    @Insert("insert into purchase (status, medicine_id, maker_id, plan_date, planned_quantity) " +
            "values (#{status}, #{medicineId}, #{makerId}, #{planDate}, #{plannedQuantity});")
    @SelectKey(statement = "select last_insert_id();", keyProperty = "id", before = false, resultType = int.class)
    int create(Purchase purchase);

//    READ
    @Select("select * from purchase;")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "medicine", column = "medicine_id",
                    one = @One(select = "com.example.pharmacy.basic.MedicineMapper.read", fetchType = FetchType.EAGER)),
            @Result(property = "maker", column = "maker_id",
                    one = @One(select = "com.example.pharmacy.user.UserMapper.readWithoutRoles", fetchType = FetchType.EAGER)),
            @Result(property = "executor", column = "executor_id",
                    one = @One(select = "com.example.pharmacy.user.UserMapper.readWithoutRoles", fetchType = FetchType.EAGER))
    })
    List<Purchase> readAll();

//    UPDATE
    @Update("update purchase set " +
            "status = #{status}, " +
            "executor_id = #{executorId}, " +
            "actual_quantity = #{actualQuantity}, " +
            "total_price = #{totalPrice}, " +
            "start_date = #{startDate}, " +
            "complete_date = #{completeDate} " +
            "where id = #{id};")
    int update(Purchase purchase);

//    DELETE
    @Delete("delete from purchase where id = #{id};")
    int delete(Integer id);
}
