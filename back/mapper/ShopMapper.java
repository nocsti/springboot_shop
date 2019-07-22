package com.tfotu.shop.mapper;

import com.tfotu.shop.dataobject.GoodsDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ShopMapper {

    @Select("SELECT * FROM goods")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "thumb", column = "thumb"),
            @Result(property = "imgs", column = "imgs"),
            @Result(property = "price", column = "price"),
            @Result(property = "status", column = "status"),
            @Result(property = "ctime", column = "ctime")
    })
    List<GoodsDO> getAll();

    @Select("SELECT * FROM goods WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "thumb", column = "thumb"),
            @Result(property = "imgs", column = "imgs"),
            @Result(property = "price", column = "price"),
            @Result(property = "status", column = "status"),
            @Result(property = "ctime", column = "ctime")
    })
    GoodsDO getOne(Integer id);

    @Insert("INSERT INTO goods(name,thumb,imgs,price,status,ctime) " +
            "VALUES(#{name},#{thumb},#{imgs},#{price},#{status},#{ctime})")
    void insert(GoodsDO goods);
}
