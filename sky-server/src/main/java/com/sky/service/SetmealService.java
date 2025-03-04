package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;

import java.util.List;

public interface SetmealService {


    /**
     * 新增套餐
     *
     * @param setmealDTO the setmeal dto
     */
    public void saveWithDish(SetmealDTO setmealDTO);

    /**
     * 套餐分页查询
     *
     * @param setmealPageQueryDTO the setmeal page query dto
     * @return page result
     */
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);


    /**
     * 删除套餐
     *
     * @param ids
     */
    void delete(List<Long> ids);

    /**
     * 修改套餐
     *
     * @param setmealDTO the setmeal dto
     */
    void update(SetmealDTO setmealDTO);


    /**
     * 出售/禁售套餐
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);
}
