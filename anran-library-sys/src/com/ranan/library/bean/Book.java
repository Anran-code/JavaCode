package com.ranan.library.bean;

public class Book {
    private String name;
    private String ISBN;
    private int count;
    private int available;

    /** 根据图书信息创建馆藏图书对象。 */
    public Book(String name, String ISBN, int count, int available) {
        this.name = name;
        this.ISBN = ISBN;
        this.count = count;
        this.available = available;
    }

    /** 根据已有图书复制一份字段相同的新对象。 */
    public Book(Book book) {
        this.name = book.name;
        this.ISBN = book.ISBN;
        this.count = book.count;
        this.available = book.available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}
