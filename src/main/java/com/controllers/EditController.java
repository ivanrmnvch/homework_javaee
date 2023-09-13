//package com.controllers;
//
//import com.entities.store.data.CreatePage;
//import com.entities.store.data.EditPage;
////import com.state.store.EditPageData;
////import jakarta.ejb.EJB;
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//@WebServlet("/edit")
//public class EditController extends HttpServlet {
//
////    @EJB
////    private EditPageData editPageData;
//
//    @Override
//    public void init() throws ServletException {
//        super.init();
//    }
//
//    // todo сохраняем фильтр
//    // сохраняем старые значения формы
//    // все хуйня
//    // возле кнопки редактировать сделать поиск и сразу кидать на редактирование
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("111");
//        req.setAttribute("edit", new EditPage());
//        req.setAttribute("page", new CreatePage());
//        RequestDispatcher view = req.getRequestDispatcher("WEB-INF/modules/edit/pages/edit-page.html.jsp");
//        view.forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
//    }
//}
