package com.example.pharmacy.basic;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineMapper {
//    CREATE
    @Insert("insert into medicine (code, name, supplier_id, manufacture_date, buying_price, " +
            "price, shelf_life, specifications, manufacturer) " +
            "values (#{code}, #{name}, #{supplierId}, #{manufactureDate}, #{buyingPrice}, " +
            "#{price}, #{shelfLife}, #{specifications}, #{manufacturer});")
    @SelectKey(statement = "select last_insert_id();", keyProperty = "id", before = false, resultType = int.class)
    int create(Medicine medicine);

//    READ
    @Select("select * from medicine;")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "repos", column = "id", javaType = List.class,
                    many = @Many(select = "com.example.pharmacy.basic.RepoMapper.readByMedicineId")),
            @Result(property = "supplier", column = "supplier_id",
                    one = @One(select = "com.example.pharmacy.basic.SupplierMapper.read"))
    })
    List<Medicine> readAll();

    @Select("select * from medicine where id = #{id};")
    Medicine read(Integer id);

    @Select("select * from medicine where supplier_id = #{supplierId};")
    Medicine readBySupplierId(Integer supplierId);

    @Select("select * " +
            "from medicine m, storage s " +
            "where m.id = s.medicine_id and s.repository_id = #{repoId};")
    List<Medicine> readByRepoId(Integer repoId);

//    UPDATE
//    @Update("update medicine " +
//            "set number = #{number}, " +
//            "name = #{name}, " +
//            "manufacture_date = #{manufactureDate}, " +
//            "shelf_life = #{shelfLife}, " +
//            "barcode = #{barcode}, " +
//            "specifications = #{specifications}, " +
//            "manufacturer = #{manufacturer}, " +
//            "provider = #{provider}, " +
//            "price = #{price}, " +
//            "buying_price = #{buyingPrice}" +
//            "where id = #{id};")
//    int update(Medicine medicine);

//    DELETE
    @Delete("delete from medicine where id = #{id};")
    int delete(Integer id);
}
