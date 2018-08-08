package com.gang.store.storesystemmanager.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2018/6/25.
 */

public class SQLHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "StoreManager.db";// 数据库名称
    public static final int VERSION = 1;
    public static final String TABLE_STORE = "store_order";// 数据表
    public static final String ORDER_ID = "order_id";//
    public static final String NAME = "name";
    public static final String CATEGORY = "category";
    public static final String SIZE = "size";
    public static final String ORDER_COUNT= "order_count";
    public static final String ORDER_PRICE = "order_price";
    private Context context;

    public SQLHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    public Context getContext() {
        return context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO 创建数据库后，对数据库的操作
        String sql = "create table if not exists " + TABLE_STORE +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ORDER_ID + " INTEGER NOT NULL , " +
                NAME + " VACHAR(128) NOT NULL , " +
                CATEGORY + " TEXT , " +
                SIZE + " INTEGER , " +
                ORDER_COUNT + " INTEGER NOT NULL, " +
                ORDER_PRICE + " FLOAT NOT NULL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }


}
