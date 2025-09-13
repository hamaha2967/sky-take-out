package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/dish")
public class DishController {

    @Autowired
    private DishService dishService;

//    /**
//     * 修改菜品
//     *
//     * @param dishDTO
//     * @return
//     */
//    @PutMapping
//    @ApiOperation("修改菜品")
//    public Result<String> updateDish(@RequestBody DishDTO dishDTO) {
//        dishService.updateDish(dishDTO);
//        return Result.success();
//    }


    @PostMapping
    @ApiOperation("新增菜品")
    public Result<String> addDish(@RequestBody DishDTO dishDTO) {
        dishService.addDish(dishDTO);
        return Result.success();
    }
}
