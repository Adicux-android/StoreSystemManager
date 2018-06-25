package com.gang.store.storesystemmanager.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gang.store.storesystemmanager.helper.SQLHelper;

/**
 * Created by Administrator on 2018/6/25.
 */

public class DBUtils {
    private static DBUtils mInstance;
    private SQLHelper mSQLHelp;
    private SQLiteDatabase mSQLiteDatabase;
    public DBUtils(Context context) {
        mSQLHelp = new SQLHelper(context);
        mSQLiteDatabase = mSQLHelp.getWritableDatabase();
    }


    public static DBUtils getInstance(Context context){
        if (mInstance == null){
            mInstance = new DBUtils(context);
        }
        return mInstance;
    }


    public void close() {
        mSQLHelp.close();
        mSQLHelp = null;
        mSQLiteDatabase.close();
        mSQLiteDatabase = null;
        mInstance = null;
    }
    /**
     * 插入数据
     */
    public void insertData(ContentValues values){
        mSQLiteDatabase.insert(SQLHelper.TABLE_STORE, null, values);
    }
    /**
     * 修改数据
     */
    public void updateData(ContentValues values, String whereClause,
                           String[] whereArgs){
        mSQLiteDatabase.update(SQLHelper.TABLE_STORE, values, whereClause,
                whereArgs);
    }
    /**
     * 删除数据
     */
    public void deleteData(String whereClause, String[] whereArgs){
        mSQLiteDatabase.delete(SQLHelper.TABLE_STORE, whereClause, whereArgs);
    }
    /**
     * 按搜索条件查询数据
     */
    public Cursor selectData(String[] columns, String selection,
                             String[] selectionArgs, String groupBy, String having,
                             String orderBy){
        Cursor cursor = mSQLiteDatabase.query(SQLHelper.TABLE_STORE, columns, selection,
                selectionArgs, groupBy, having, orderBy);
        return cursor;
    }
}
