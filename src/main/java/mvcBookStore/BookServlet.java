package mvcBookStore;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import mvcBookStore.Book;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/books")
@MultipartConfig
public class BookServlet extends HttpServlet {
    private List<Book> bookList;
    @Override
    public void init() throws ServletException {
        super.init();
        bookList = new ArrayList<>();
        bookList.add(new Book(1, "Dế mèn phiêu lưu kí", "Tô Hoài",48000, 20, "img/img_2.png" ));
        bookList.add(new Book(2, "Làng", "Kim Lân",23000, 21, "img/img_3.png" ));
        bookList.add(new Book(3, "Vợ nhặt", "Kim Lân",30000, 50, "img/img_1.png" ));
        bookList.add(new Book(4, "Giết con chim nhại", "Harper Lee",45000, 100, "img/img.png" ));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null) {
            action = "list";
        }

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "create":
                createBook(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "update":
                updateBook(request, response);
                break;
            case "delete":
                deleteBook(request, response);
                break;
            case "searchByName":
                String searchTerm = request.getParameter("searchTerm");
                List<Book> searchResult = searchByName(searchTerm);
                request.setAttribute("bookList", searchResult);
                RequestDispatcher dispatcher = request.getRequestDispatcher("book-list.jsp");
                dispatcher.forward(request, response);
                break;
            default:
                listBooks(request, response);
                break;
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    private void listBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("bookList", bookList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("book-list.jsp");
        dispatcher.forward(request, response);
    }
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("book-form.jsp");
        dispatcher.forward(request, response);
    }
    private void createBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameBook = request.getParameter("nameBook");
        String nameAuthor = request.getParameter("nameAuthor");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int id = bookList.size() + 1;

        Part filePart = request.getPart("img");

        String fileName = getFileName(filePart);

        String uploadDirectory = getServletContext().getRealPath("/img");

        String filePath = uploadFile(filePart, fileName, uploadDirectory);
        String fileURL = "img/" + fileName;


        Book newBook = new Book(id, nameBook, nameAuthor, price, quantity, fileURL);
        bookList.add(newBook);

        response.sendRedirect("books");


    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book book = getBookById(id);

        request.setAttribute("book", book);
        RequestDispatcher dispatcher = request.getRequestDispatcher("book-form.jsp");
        dispatcher.forward(request, response);
    }
    private void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nameBook = request.getParameter("nameBook");
        String nameAuthor = request.getParameter("nameAuthor");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Part filePart = request.getPart("img");

        String fileName = getFileName(filePart);

        String uploadDirectory = getServletContext().getRealPath("/img");

        String filePath = uploadFile(filePart, fileName, uploadDirectory);
        String fileURL = "img/" + fileName;

        Book book = getBookById(id);
        book.setNameBook(nameBook);
        book.setNameAuthor(nameAuthor);
        book.setPrice(price);
        book.setQuantity(quantity);
        book.setImg(fileURL);

        response.sendRedirect("books");
    }
    private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book book = getBookById(id);
        bookList.remove(book);
        response.sendRedirect("books");
    }

    private List<Book> searchByName(String searchTerm){
        List<Book> searchResult = new ArrayList<>();
        for (Book book : bookList){
            if(book.getNameBook().toLowerCase().contains(searchTerm.toLowerCase())){
                searchResult.add(book);
            }
        }
        return searchResult;
    }
    private Book getBookById(int id) {
        for (Book book : bookList) {
            if(book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    private String uploadFile(Part filePart, String fileName, String uploadDirectory) throws IOException {
        String filePath = uploadDirectory + File.separator + fileName;

        try(InputStream inputStream = filePart.getInputStream(); FileOutputStream outputStream = new FileOutputStream(filePath)){
            byte[] buffer = new byte[8192];
            int byteRead;

            while ((byteRead = inputStream.read(buffer)) != -1){
                outputStream.write(buffer, 0, byteRead);
            }
        }

        return filePath;
    }

    private String getFileName(Part part){
        String contentDisposition = part.getHeader("content-disposition");
        String[] elements = contentDisposition.split(";");

        for(String element : elements){
            if(element.trim().startsWith("filename")){
                return element.substring(element.indexOf("=") + 1).trim().replace("\"","");
            }
        }

        return "";
    }

}