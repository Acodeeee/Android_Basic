package com.android.wrf_mac.activity_deliver;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wrf_mac on 2017/7/7.
 */

public class Book implements Parcelable {

    private String bookName;
    private String author;
    private int price;

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public int getPrice() {
        return price;
    }



    // 1.必须实现Parcelable.Creator接口,否则在获取Person数据的时候，会报错，如下：
    // android.os.BadParcelableException:
    // Parcelable protocol requires a Parcelable.Creator object called  CREATOR on class com.um.demo.Person
    // 2.这个接口实现了从Parcel容器读取Book数据，并返回Book对象给逻辑层使用
    // 3.实现Parcelable.Creator接口对象名必须为CREATOR，不如同样会报错上面所提到的错；
    // 4.在读取Parcel容器里的数据时，必须按成员变量声明的顺序读取数据，不然会出现获取数据出错
    // 5.反序列化对象
    public static final Creator<Book> CREATOR = new Creator<Book>() {
        /**
         * TODO:安装成员变量的声明顺序读取数据，然后存入Book对象，返回Book对象给逻辑层用
         * @param in
         * @return
         */
        @Override
        public Book createFromParcel(Parcel in) {
            Book book = new Book();
            book.setBookName(in.readString());
            book.setAuthor(in.readString());
            book.setPrice(in.readInt());
            return book;
        }

        //供外部类反序列化本类数组使用
        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };


    //内容接口描述，默认返回0即刻
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * TODO:按照声明顺序打包数据到Parcel对象中，既将数据打包到Parcel容器中
     * TODO:使用write***方法打包数据
     * @param parcel
     * @param i
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(bookName);
        parcel.writeString(author);
        parcel.writeInt(price);
    }
}
