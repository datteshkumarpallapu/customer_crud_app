package com.tap.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tap.dao.CustomerDAO;
import com.tap.model.Customer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class CustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;
    
    public void init() {
        customerDAO = new CustomerDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertCustomer(request, response);
                    break;
                case "/delete":
                    deleteCustomer(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateCustomer(request, response);
                    break;
                case "/sync":
                    syncCustomers(request, response);
                    break;
                default:
                    listCustomer(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Customer> listCustomer = customerDAO.selectAllCustomers();
        request.setAttribute("listCustomer", listCustomer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Customer existingCustomer = customerDAO.selectCustomer(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer-form.jsp");
        request.setAttribute("customer", existingCustomer);
        dispatcher.forward(request, response);
    }

    private void insertCustomer(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String street = request.getParameter("street");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        Customer newCustomer = new Customer(firstName, lastName, street, address, city, state, email, phone);
        customerDAO.insertCustomer(newCustomer);
        response.sendRedirect("list");
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String street = request.getParameter("street");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        Customer customer = new Customer(id, firstName, lastName, street, address, city, state, email, phone);
        customerDAO.updateCustomer(customer);
        response.sendRedirect("list");
    }
    
    private void syncCustomers(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            List<Customer> remoteCustomers = fetchRemoteCustomers();
            for (Customer remoteCustomer : remoteCustomers) {
                Customer existingCustomer = customerDAO.selectCustomer(remoteCustomer.getId());
                if (existingCustomer != null) {
                    customerDAO.updateCustomer(remoteCustomer);
                } else {
                    customerDAO.insertCustomer(remoteCustomer);
                }
            }
            response.sendRedirect("list");
        } catch (SQLException e) {
            throw new ServletException("Error syncing customers", e);
        }
    }


    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        customerDAO.deleteCustomer(id);
        response.sendRedirect("list");
    }
    
    private List<Customer> fetchRemoteCustomers() throws IOException {
        return List.of(); 
    }
}

