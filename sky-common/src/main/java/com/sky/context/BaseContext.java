package com.sky.context;


/**
 * 存储和管理当前线程的用户 ID
 */
public class BaseContext {

    /*
    使用 ThreadLocal 存储当前线程的用户 ID,
    为每个线程提供一个独立的变量副本，确保线程安全。
     */
    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * 设置当前线程的用户 ID
     * @param id 用户 ID
     */
    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    /**
     * 获取当前线程的用户 ID
     * @return 用户 ID
     */
    public static Long getCurrentId() {
        return threadLocal.get();
    }

    /**
     * 移除当前线程的用户 ID
     */
    public static void removeCurrentId() {
        threadLocal.remove();
    }

}