package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// Mapper类为什么要定义成Interface？
@Mapper
public interface SetmealDishMapper {
    /**
     * 根据菜品id查询对应的套餐id
     *
     * @param dishId
     * @return
     */
    //select setmeal_id from setmeal_dish where dish_id in (1,2,3,4)
    @Select("select count(id) from setmeal_dish where dish_id = #{dishId}")
    int getSetmealCountByDishId(Long dishId);

    @Select("select setmeal_id from setmeal_dish where dish_id in (?,?,?)")
    List<Long>  getSetmealIdsByDishIds(List<Long> dishIds);
}
