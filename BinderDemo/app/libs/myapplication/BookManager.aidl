// BookManager.aidl
package com.boyaa.godsdk.myapplication;

import com.boyaa.godsdk.myapplication.Book;

interface BookManager {
    void addBook(inout Book book);
}