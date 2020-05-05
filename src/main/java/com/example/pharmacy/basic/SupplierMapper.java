package com.example.pharmacy.basic;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierMapper {

//    CREATE
    @Insert("insert into supplier (name, address, postcode, telephone, email) " +
            "values (#{name}, #{address}, #{postcode}, #{telephone}, #{email});")
    @SelectKey(statement = "select last_insert_id();", keyProperty = "id", before = false, resultType = int.class)
    int create(Supplier supplier);

//    READ
    @Select("select * from supplier;")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "medicines", column = "id", javaType = List.class,
                    many = @Many(select = "com.example.pharmacy.basic.MedicineMapper.readBySupplierId"))
    })
    List<Supplier> readAll();

    @Select("select * from supplier where id = #{id};")
    Supplier read(Integer id);

//    UPDATE
    @Update("update supplier " +
            "set name = #{name}," +
            "address = #{address}," +
            "postcode = #{postcode}," +
            "telephone = #{telephone}," +
            "email = #{email}" +
            "where id = #{id};")
    int update(Supplier supplier);


//    DELETE
    @Delete("delete from supplier where id = #{id};")
    int delete(Integer id);
}
