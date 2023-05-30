package mvcBookStore;

public class Book {
    private int id;
    private String nameBook;
    private String nameAuthor;
    private double price;
    private int quantity;
    private String img;

    public Book(int id, String nameBook, String nameAuthor, double price, int quantity, String img) {
        super();
        this.id = id;
        this.nameBook = nameBook;
        this.nameAuthor = nameAuthor;
        this.price = price;
        this.quantity = quantity;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public void setNameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
}

