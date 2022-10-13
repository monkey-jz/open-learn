package com.boyaa.binder.demo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

/**
 * @Auther: JerryZhu
 * @datetime: 2020/12/16
 */
public class RemoteService extends Service {

    private static final String TAG = "RemoteService";
    private List<Book> bookList = new ArrayList<Book>();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"RemoteService : onCreate " + ",pid = " + android.os.Process.myPid() + ",thread name = " + Thread.currentThread().getName());
        initData();
    }

    private void initData() {
        Book book1 = new Book("活着");
        Book book2 = new Book("或者");
        Book book3 = new Book("叶应是叶");
        Book book4 = new Book("https://github.com/leavesC");
        Book book5 = new Book("http://www.jianshu.com/u/9df45b87cfdf");
        Book book6 = new Book("http://blog.csdn.net/new_one_object");
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookList.add(book4);
        bookList.add(book5);
        bookList.add(book6);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"RemoteService : onBind");
        return mBookManager;
    }

    private BookManager.Stub mBookManager = new BookManager.Stub() {

        @Override
        public void addBook(Book book) throws RemoteException {

            bookList.add(book);
            Log.d(TAG,"mBookManager : addBook " + ",pid = " + android.os.Process.myPid() + ",thread name = " + Thread.currentThread().getName());
            for (Book book1 : bookList) {
                Log.d(TAG,"Book" + book1.getName());
            }
        }
    };
}
