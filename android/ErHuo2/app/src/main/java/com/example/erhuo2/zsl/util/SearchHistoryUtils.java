package com.example.erhuo2.zsl.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SearchHistoryUtils extends SQLiteOpenHelper {
    private Context context;
    public SearchHistoryUtils(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table historykeywords(id Integer primary key autoincrement,keyword varchar(15), time varchar(15))";
        db.execSQL(sql);
        Toast.makeText(
                context,
                "创建数据库表成功",
                Toast.LENGTH_SHORT
        ).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
