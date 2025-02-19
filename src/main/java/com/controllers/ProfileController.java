package com.controllers;

import com.entities.store.data.Cart;
import com.entities.store.data.EditPage;
import com.entities.store.data.User;
import com.services.AuthService;
import com.services.BasketService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

@WebServlet({
    "/profile",
})
public class ProfileController extends HttpServlet {
    private AuthService authService;
    private BasketService basketService;
    @Override
    public void init() throws ServletException {
        super.init();
        authService = new AuthService();
        basketService = new BasketService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getServletPath();
        User user;
        try {
            user = authService.getUserInfo(req);
            if (!Objects.equals(user.getRole(), "admin")) {
                RequestDispatcher view = req.getRequestDispatcher("WEB-INF/modules/error/pages/error.html.jsp");
                view.forward(req, resp);
                return;
            }

            if ("/profile".equals(uri)) {
                Cart cart = basketService.getCart(user.getUserId());
                req.setAttribute("edit", new EditPage());
                req.setAttribute("user", user);
                req.setAttribute("cart", cart);
                RequestDispatcher view = req.getRequestDispatcher("WEB-INF/modules/profile/pages/profile.html.jsp");
                view.forward(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
