package controller;

import dao.CategoryDao;
import dao.ProductDao;
import model.Category;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/admin/products")
public class ProductServlet extends HttpServlet {
    private ProductDao productDao;
    private CategoryDao categoryDao;
    private HttpSession session;

    @Override
    public void init() {
        productDao = new ProductDao();
        categoryDao = new CategoryDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) {
        session = req.getSession();
        try {
            String action = (req.getParameter("act") != null) ? req.getParameter("act") : "";
            switch (action) {
                case "create":
                    createProduct(req, resp);
                    break;
                case "store":
                    storeProduct(req, resp);
                    break;
                case "edit":
                    editProduct(req, resp);
                    break;
                case "update":
                    updateProduct(req, resp);
                    break;
                case "delete":
                    deleteProduct(req, resp);
                    break;
                case "search":
                    searchProduct(req, resp);
                    break;

                default:
                    showAllProducts(req, resp);
            }
        } catch (SQLException | ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = categoryDao.getRecords();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/product/create.jsp").forward(req, resp);
    }

    private void storeProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        session = req.getSession();
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int qty = Integer.parseInt(req.getParameter("qty"));
        String color = req.getParameter("color");
        String desc = req.getParameter("desc");
        int category = Integer.parseInt(req.getParameter("category"));

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setQty(qty);
        product.setColor(color);
        product.setDesc(desc);
        product.setCategoryId(category);

        try {
            if (productDao.add(product)) {
                session.setAttribute("msg", "<div class=\"alert alert alert-success\">New product created successfully.</div>");
            } else {
                session.setAttribute("msg", "<div class=\"alert alert alert-danger\">INSERT product fails.</div>");
            }
        } catch (SQLException ex) {
            session.setAttribute("msg", "<div class=\"alert alert alert-danger\">INSERT product fails: " + ex.getMessage() + "</div>");
        }
        resp.sendRedirect("/admin/products");
    }

    private void editProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session = req.getSession();
        int id = Integer.parseInt(req.getParameter("id"));
        Product product;
        try {
            product = productDao.findById(id);
            if (product == null) {
                session.setAttribute("msg", "<div class=\"alert alert alert-danger\">Product not found.</div>");
                resp.sendRedirect("/admin/products");
            } else {
                req.setAttribute("product", product);

                List<Category> categories = categoryDao.getRecords();
                req.setAttribute("categories", categories);
                req.getRequestDispatcher("/product/edit.jsp").forward(req, resp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        session = req.getSession();

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int qty = Integer.parseInt(req.getParameter("qty"));
        String color = req.getParameter("color");
        String desc = req.getParameter("desc");
        int category = Integer.parseInt(req.getParameter("category"));

        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setQty(qty);
        product.setColor(color);
        product.setDesc(desc);
        product.setCategoryId(category);

        if (productDao.edit(product)) {
            session.setAttribute("msg", "<div class=\"alert alert alert-success\">Record updated successfully.</div>");
        } else {
            session.setAttribute("msg", "<div class=\"alert alert alert-danger\">UPDATE fails.</div>");
        }

        resp.sendRedirect("/admin/products");
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        session = req.getSession();
        int id = Integer.parseInt(req.getParameter("id"));
        Product product;
        try {
            product = productDao.findById(id);
            if (product == null) {
                session.setAttribute("msg", "<div class=\"alert alert alert-danger\">Product not found.</div>");
            } else {
                try {
                    if (productDao.remove(id)) {
                        session.setAttribute("msg", "<div class=\"alert alert alert-success\">Record deleted successfully.</div>");
                    } else {
                        session.setAttribute("msg", "<div class=\"alert alert alert-danger\">Cannot delete or update a parent row: a foreign key constraint fails.</div>");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            resp.sendRedirect("/admin/products");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void showAllProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Product> products = productDao.getRecords();
        req.setAttribute("products", products);

        req.getRequestDispatcher("/product/index.jsp").forward(req, resp);
    }

    private void searchProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("query");
        List<Product> products = productDao.findByName(search);
        req.setAttribute("products", products);
        req.getRequestDispatcher("/product/index.jsp").forward(req, resp);
    }
}
