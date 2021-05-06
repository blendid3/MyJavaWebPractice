package com.example.HelloWorld;

import db.dao.CustomerDao;
import db.dao.GoodDao;
import db.dao.imp.CustomerDaoImp;
import db.dao.imp.GoodDaoImp;
import db.domain.Customer;
import db.domain.Good;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "controller", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {
    List<String> errors ;
    CustomerDao customerDao = new CustomerDaoImp();
    GoodDao goodDao = new GoodDaoImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        errors = new ArrayList<>();
        if (req.getParameter("action").equals("login")) {
            int id = 0;
            try {
                id = Integer.parseInt(req.getParameter("id"));
            } catch (Exception e) {
                errors.add("id format is wrong!!! Must be Integer");
                req.setAttribute("errors", errors);
                req.getRequestDispatcher("login.jsp").forward(req, resp);
                return;
            }

            String password = req.getParameter("password");

            try {
                if (customerDao.findAll(id, password)) {
                    Customer customer = customerDao.getById(id);
                    req.setAttribute("name", customer.getName());
                    req.getRequestDispatcher("mainPlatform.jsp").forward(req, resp);
                } else {
                    errors.add("Doesn't find your account, please retype id and password");
                    req.setAttribute("errors", errors);
                    req.getRequestDispatcher("login.jsp").forward(req, resp);
                    return;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                req.setAttribute("error", "findAll: SQLException!!!");
                req.getRequestDispatcher("404.jsp").forward(req, resp);
                return;
            }

        } else if (req.getParameter("action").equals("reg")) {
            int id = 0;
            String name = req.getParameter("name");
            String password = req.getParameter("password");
            String password2 = req.getParameter("password2");
            String address = req.getParameter("address");
            String phone = req.getParameter("phone");
            Date birthday = null;
            try {
                id = Integer.parseInt(req.getParameter("id"));
            } catch (Exception e) {
                errors.add("id is wrong!!!");
            }

            try {
                String bir_str = req.getParameter("birthday");
                birthday =new SimpleDateFormat("MM/dd/yyyy").parse( req.getParameter("birthday"));
            } catch (ParseException e) {
                e.printStackTrace();
                errors.add("birthday format is wrong");
            }

            if (!password.equals(password2)) {
                errors.add("password is not same");
            }

            Pattern pattern = Pattern.compile("^\\d{10}$");
            Matcher matcher = pattern.matcher(phone);
            if (!matcher.matches()) {
                errors.add("phone number format is wrong!!!");
            }
            if (errors.size() > 0 ) {
                req.setAttribute("errors", errors);
                req.getRequestDispatcher("register.jsp").forward(req, resp);
                return;
            }
            Customer c1 = new Customer(id, name, password, address, phone,  birthday);
            try {
                if (customerDao.findAll(c1)) {
                    errors.add("userName and id already exist, please use different one");
                    req.setAttribute("errors", errors);
                    req.getRequestDispatcher("register.jsp").forward(req, resp);
                    return;
                }
                customerDao.insert(c1);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                req.setAttribute("error", "checkIfExist: Error!!!! SQL Exception, Database format is not right");
                req.getRequestDispatcher("404.jsp").forward(req, resp);
            }

            req.getRequestDispatcher("mainPlatforms.jsp").forward(req, resp);

        } else if (req.getParameter("action").equals("list")) {
            Map<String, Integer> goodNumMap = (Map<String, Integer>) req.getSession().getAttribute("cart");
            List<Good> goods = null;
            if (goodNumMap == null) {
                goodNumMap = new HashMap<>();
                req.getSession().setAttribute("cart", goodNumMap);
                try {
                    goods = goodDao.getGoodList();

                    for (Good good: goods) {
                        goodNumMap.put(good.getNAME(), 0);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    req.setAttribute("error", "Controller: action: list: goodDao.getGoodList Error!!!");
                    req.getRequestDispatcher("404.jsp").forward(req, resp);
                }

            }
            try {
                goods = goodDao.getGoodList();
                req.setAttribute("goods", goods);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            req.getRequestDispatcher("list.jsp").forward(req, resp);


        } else if(req.getParameter("action").equals("cart")) {

            Map<String, Integer> cartInfo = (Map<String, Integer>) req.getSession().getAttribute("cart");
            if (cartInfo == null) {
                cartInfo = new HashMap<>();
            }
            List<String> productList = new ArrayList<>();
            int total_price = 0;
            for(Map.Entry<String, Integer> s1: cartInfo.entrySet()) {
                String productName = s1.getKey();
                int num = s1.getValue();
                if (num == 0) {
                    continue;
                }
                productList.add(productName);
                try {
                    total_price += goodDao.getGoodByName(productName).getPrice() * num;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    req.setAttribute("error", "Controller: action: cart: goodDao: getGoodByName: error!!!");
                    req.getRequestDispatcher("404.jsp").forward(req, resp);
                }
            }
            req.setAttribute("total_price", total_price);
            req.setAttribute("productList", productList);
            req.getRequestDispatcher("cart.jsp").forward(req, resp);

        }  else if (req.getParameter("action").equals("submit")) {
            Map<String, Integer> goodNumMap = (Map<String, Integer>) req.getSession().getAttribute("cart");
            for(Map.Entry<String, Integer> entry: goodNumMap.entrySet()) {
                String goodName = entry.getKey();
                try {
                    Good good = goodDao.getGoodByName(goodName);
                    String goodId = String.valueOf(good.getId());
                    int num = Integer.parseInt(req.getParameter(goodId));
                    goodNumMap.put(goodName, goodNumMap.get(goodName) + num);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            req.getRequestDispatcher("mainPlatform.jsp").forward(req, resp);
        } else if (req.getParameter("action").equals("clear_cart")) {
            Map<String, Integer> goodNumMap = (Map<String, Integer>) req.getSession().getAttribute("cart");
            for (Map.Entry<String, Integer> entry : goodNumMap.entrySet()) {
                entry.setValue(0);
            }
            req.getRequestDispatcher("cart.jsp").forward(req, resp);
        } else if (req.getParameter("action").equals("good_detail")) {
            String goodName = req.getParameter("goodName");
            try {
                Good good1 = goodDao.getGoodByName(goodName);
                req.setAttribute("good", good1);
                req.getRequestDispatcher("good_detail.jsp").forward(req, resp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
