
package com.boyaa.binder.demo;

import android.util.Log;

public interface BookManager extends android.os.IInterface
{
  /** Default implementation for BookManager. */
  public static class Default implements BookManager
  {
    @Override public void addBook(Book book) throws android.os.RemoteException
    {
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements BookManager
  {
    private static final String DESCRIPTOR = "com.boyaa.godsdk.myapplication.BookManager";
    /** Construct the stub at attach it to the interface. */
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an com.boyaa.godsdk.myapplication.BookManager interface,
     * generating a proxy if needed.
     */
    public static BookManager asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof BookManager))) {
        return ((BookManager)iin);
      }
      return new Proxy(obj);
    }
    @Override public android.os.IBinder asBinder()
    {
      return this;
    }
    @Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
    {

      Log.d("RemoteService","BookManager : onTransact " + ",pid = " + android.os.Process.myPid() + ",thread name = " + Thread.currentThread().getName());

      String descriptor = DESCRIPTOR;
      switch (code)
      {
        case INTERFACE_TRANSACTION:
        {
          reply.writeString(descriptor);
          return true;
        }
        case TRANSACTION_addBook:
        {
          data.enforceInterface(descriptor);
          Book _arg0;
          if ((0!=data.readInt())) {
            _arg0 = Book.CREATOR.createFromParcel(data);
          }
          else {
            _arg0 = null;
          }
          this.addBook(_arg0);
          reply.writeNoException();
          if ((_arg0!=null)) {
            reply.writeInt(1);
            _arg0.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
          }
          else {
            reply.writeInt(0);
          }
          return true;
        }
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
    }
    private static class Proxy implements BookManager
    {
      private android.os.IBinder mRemote;
      Proxy(android.os.IBinder remote)
      {
        mRemote = remote;
      }
      @Override public android.os.IBinder asBinder()
      {
        return mRemote;
      }
      public String getInterfaceDescriptor()
      {
        return DESCRIPTOR;
      }
      @Override public void addBook(Book book) throws android.os.RemoteException
      {
        Log.d("RemoteService","Proxy : addBook" + ",pid = " + android.os.Process.myPid() + ",thread name = " + Thread.currentThread().getName());
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          if ((book!=null)) {
            _data.writeInt(1);
            book.writeToParcel(_data, 0);
          }
          else {
            _data.writeInt(0);
          }
          boolean _status = mRemote.transact(Stub.TRANSACTION_addBook, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().addBook(book);
            return;
          }
          _reply.readException();
          if ((0!=_reply.readInt())) {
            book.readFromParcel(_reply);
          }
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      public static BookManager sDefaultImpl;
    }
    static final int TRANSACTION_addBook = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    public static boolean setDefaultImpl(BookManager impl) {
      // Only one user of this interface can use this function
      // at a time. This is a heuristic to detect if two different
      // users in the same process use this function.
      if (Proxy.sDefaultImpl != null) {
        throw new IllegalStateException("setDefaultImpl() called twice");
      }
      if (impl != null) {
        Proxy.sDefaultImpl = impl;
        return true;
      }
      return false;
    }
    public static BookManager getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
  }
  public void addBook(Book book) throws android.os.RemoteException;
}
