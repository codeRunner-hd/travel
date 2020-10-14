package com.hand.travel.web.servlet;

import com.hand.travel.domain.Category;
import com.hand.travel.service.CategoryService;
import com.hand.travel.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: Admin-han
 * @DateTime: 2020/4/28 21:14
 */
@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    private final CategoryService categoryService = new CategoryServiceImpl();

    /**
     * 查询商品目录
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、调用service查询所有商品目录
        List<Category> list = categoryService.findAll();
        // 2、将list序列化为json，并返回
        writeValue(list, response);
    }
}
