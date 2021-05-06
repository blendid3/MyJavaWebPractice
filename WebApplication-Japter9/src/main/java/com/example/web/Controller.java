package com.example.web;


import com.example.domain.Customer;
import com.example.domain.Good;
import com.example.service.CustomerService;
import com.example.service.GoodService;
import com.example.service.OrderService;
import com.example.service.ServiceException;
import com.example.service.imp.CustomerServiceImp;
import com.example.service.imp.GoodServiceImp;
import com.example.service.imp.OrderServiceImp;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;


@javax.servlet.annotation.WebServlet(name = "Controller", urlPatterns = {"/controller"} )
public class Controller extends javax.servlet.http.HttpServlet{
    private GoodService gs = new GoodServiceImp();
    private OrderService os = new OrderServiceImp();
    private CustomerService cs = new CustomerServiceImp();
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private int totalPageNumber = 0;
    private int pageSize = 10;
    private int currentPage =1;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    protected void  doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse rep) throws ServletException, IOException {
        doGet(req, rep);
    }
    protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse rep) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("reg".equals(action)) {
            String userid = req.getParameter("userid");
            String name = req.getParameter("name");
            String password = req.getParameter("password");
            String password2 = req.getParameter("password2");
            String birthday = req.getParameter("birthday");
            String address = req.getParameter("address");
            String phone = req.getParameter("phone");

            // server verify
            List<String> errors = new ArrayList<>();
            if (userid == null || userid.equals("")) {
                errors.add("userid shouldn't be empty");
            }
            if (name == null || name.equals("")) {
                errors.add("name shouldn't be empty");
            }
            if (password == null || password.equals("") || password2 == null || password2.equals("") || password2.equals(password)) {
                errors.add("password or password2 wrong");
            }
            String pattern = "^((((19|20)(([02468][048])|([13579][26]))-02-29))|((20[0-9][0-9])|(19[0-9][0-9]))-((((0[1-9])|(1[0-2]))-((0[1-9])|(1\\d)|(2[0-8])))|((((0[13578])|(1[02]))-31)|(((0[1,3-9])|(1[0-2]))-(29|30)))))$";
            if (!Pattern.matches(pattern, birthday)) {
                errors.add("birthday pattern is wrong");
            }
            if (errors.size() > 0) {
                req.setAttribute("errors", errors);
                req.getRequestDispatcher("customer_reg.jsp").forward(req, rep);
            } else {
                Customer customer = new Customer();
                customer.setId(userid);
                customer.setPhone(phone);
                customer.setName(name);
                try {
                    Date d = dateFormat.parse(birthday);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // login
                try {
                    cs.register(customer);
                    req.getRequestDispatcher("login.jsp").forward(req, rep);
                } catch (ServiceException e) {
                    errors.add("customer already login in!");
                    req.setAttribute("errors", errors);
                    req.getRequestDispatcher("customer_reg.jsp").forward(req, rep);
                }

            }

        } else if ("login".equals(action)) {
            String userid = req.getParameter("userid");
            String password = req.getParameter("password");

            Customer customer = new Customer();
            customer.setId(userid);
            customer.setPassword(password);

            if (cs.login(customer)) {
                HttpSession session = req.getSession();
                session.setAttribute("customer", customer);
                req.getRequestDispatcher("main.jsp").forward(req, rep);
            } else {
                List<String> errors = new ArrayList<>();
                errors.add("Account or password you enter are wrong");
                req.setAttribute("errors", errors);
                req.getRequestDispatcher("login.jsp").forward(req, rep);
            }

        } else if ("list".equals(action)) {
            List<Good> goodsList = gs.queryAll();
            if (goodsList.size() % pageSize == 0) {
                totalPageNumber = goodsList.size() / pageSize;
            } else {
                totalPageNumber = goodsList.size() / pageSize + 1;
            }

            req.setAttribute("totalPageNumber", totalPageNumber);
            req.setAttribute("currentPage", currentPage);

            int start = (currentPage - 1) * pageSize;
            int end = currentPage * pageSize;
            if (currentPage == totalPageNumber) {
                end = goodsList.size();
            }

            end = 1;
            System.out.println("start: " + start);
            System.out.println("end: " + end);
            System.out.println("goodsList len: " + goodsList.size());
            req.setAttribute("goodsList", goodsList.subList(start, end));
            req.getRequestDispatcher("goods_list.jsp").forward(req, rep);


        } else if ("paging".equals(action)) {
            String page = req.getParameter("page");

            if (page.equals("prev")) {
                currentPage --;
                if (currentPage < 1) {
                    currentPage = 1;
                }
            } else if (page.equals("next")) {
                currentPage ++;
                if (currentPage >= totalPageNumber) {
                    currentPage = totalPageNumber;
                }
            } else {
                currentPage = Integer.valueOf(page); // exception
            }
            int start = (currentPage - 1) * pageSize;
            int end = currentPage * pageSize;

            List<Good> goodsList = gs.queryByStartEnd(start, end);

            req.setAttribute("totalPageNumber", totalPageNumber);
            req.setAttribute("currentPage", currentPage);
            req.setAttribute("goodsList", goodsList);
            req.getRequestDispatcher("goods_list.jsp").forward(req,rep);

        } else if ("detail".equals(action)) {
            String goodsid = req.getParameter("id");
            Good goods = gs.querDetail(new Long(goodsid));

            req.setAttribute("goods", goods);
            req.getRequestDispatcher("goods_detail.jsp").forward(req, rep);


        } else if ("add".equals(action)) {

            Long goodsid = new Long(req.getParameter("id"));
            String goodsname = req.getParameter("name");
            Float price = new Float(req.getParameter("price"));

            List<Map<String, Object>> cart = (List<Map<String, Object>>) req.getSession().getAttribute("cart");
            if (cart == null) {
                cart = new ArrayList<>();
                req.getSession().setAttribute("cart", cart);
            }

            int flag = 0;
            for (Map<String, Object> item : cart) {
                Long goodsid2 = (Long) item.get("goodsid");
                if (goodsid.equals(goodsid2)) {
                    Integer quantity = (Integer) item.get("quantity");
                    quantity ++;
                    item.put("quantity", quantity);

                    flag++;
                }
            }

            if (flag == 0) {
                Map<String, Object> item = new HashMap<>();
                item.put("goodsid", goodsid);
                item.put("goodsname", goodsname);
                item.put("quantity", 1);
                item.put("price", price);
                cart.add(item);
            }

            System.out.println(cart);

            String pagename = req.getParameter("pagename");
            if (pagename.equals("list")) {
                int start = (currentPage - 1) * pageSize;
                int end = currentPage * pageSize;

                List<Good> goodsList = gs.queryByStartEnd(start, end);
                req.setAttribute("currentPage", currentPage);
                req.setAttribute("goodsList", goodsList);
                req.getRequestDispatcher("goods_list.jsp").forward(req, rep);
            } else if (pagename.equals("detail")) {
                Good good = gs.querDetail(new Long(goodsid));
                req.setAttribute("goods", good);
                req.getRequestDispatcher("goods_detail.jsp").forward(req, rep);
            }

        } else if ("cart".equals(action)) {
            List<Map<String, Object>> cart = (List<Map<String, Object>>) req.getSession().getAttribute("cart");
            double total = 0.0;

            if (cart != null) {
                for (Map<String, Object> item : cart) {
                    Integer quantity = (Integer) item.get("quantity");
                    Float price = (Float) item.get("price");
                    double subtotal = price * quantity;
                    total += subtotal;
                }
            }
            req.setAttribute("total", total);
            req.getRequestDispatcher("cart.jsp").forward(req, rep);

        } else if ("sub_ord".equals(action)) {

            List<Map<String, Object>> cart = (List<Map<String, Object>>) req.getSession().getAttribute("cart");
            for (Map<String, Object> item : cart) {
                Long goodsid = (Long) item.get("goodsid");
                String strquantity = req.getParameter("quantity_" + goodsid);
                int quantity = 0;
                try {
                    quantity = new Integer(strquantity);
                } catch (Exception e) {

                }
                item.put("quantity", quantity);
            }
            String ordersid = os.submitOrders(cart);
            req.setAttribute("ordersid", ordersid);
            req.getRequestDispatcher("order_finish.jsp").forward(req, rep);
            req.getSession().removeAttribute("cart");

        } else if ("main".equals(action)) {
            req.getRequestDispatcher("main.jsp").forward(req, rep);

        } else if ("logout".equals(action)) {
            req.getSession().removeAttribute("cart");
            req.getSession().removeAttribute("customer");
            req.getRequestDispatcher("login.jsp").forward(req, rep);
        } else if ("reg_init".equals(action)) {
            req.getRequestDispatcher("customer_reg.jsp").forward(req, rep);
        }
    }

}
