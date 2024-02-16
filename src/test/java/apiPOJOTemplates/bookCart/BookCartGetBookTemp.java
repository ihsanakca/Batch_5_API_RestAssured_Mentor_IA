package apiPOJOTemplates.bookCart;

public class BookCartGetBookTemp {
    /**
     *  {
     *          "bookId": 2,
     *          "title": "HP2",
     *          "author": "JKR",
     *          "category": "Mystery",
     *          "price": 235.00,
     *          "coverFileName": "9d8f4978-0ef8-42d0-873a-4eb583439237HP2.jpg"
     *          }
     */

    private int bookId;
    private String title;
    private String author;
    private String category;
    private String coverFileName;
    private double price;

    public BookCartGetBookTemp() {
    }

    public BookCartGetBookTemp(int bookId, String title, String author, String category, String coverFileName, double price) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.coverFileName = coverFileName;
        this.price = price;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCoverFileName() {
        return coverFileName;
    }

    public void setCoverFileName(String coverFileName) {
        this.coverFileName = coverFileName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BookCartGetBookTemp{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", coverFileName='" + coverFileName + '\'' +
                ", price=" + price +
                '}';
    }
}
