package com.coolyota.demo.provider;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.coolyota.demo.db.MyDataBaseOpenHelper;

/**
 * des: 
 * 
 * @author  liuwenrong
 * @version 1.0,2017/8/22 
 */
public class MyContentProvider extends ContentProvider   {

    MyDataBaseOpenHelper dbHelper;

    private static final int All_Friend = 2;
    private static final int Friend = 3;

    private static final UriMatcher sMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sMatcher.addURI("com.coolyota.demo.provider.MyContentProvider", "friend", All_Friend);
        sMatcher.addURI("com.coolyota.demo.provider.MyContentProvider", "friend/#", Friend);
    }

    @Override
    public boolean onCreate() {
        dbHelper = MyDataBaseOpenHelper.getInstance(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        switch (sMatcher.match(uri)) {
            case All_Friend:
                return db.query(MyDataBaseOpenHelper.Database_Name, projection, selection, selectionArgs, null, null, sortOrder);
            case Friend:
                long friendId = ContentUris.parseId(uri);
                String[] params = new String[]{String.valueOf(friendId)};
                return db.query(MyDataBaseOpenHelper.Database_Name, projection, "id = ?", params, null, null, sortOrder);
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        long id = 0;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (sMatcher.match(uri)) {

            case All_Friend:
                id = db.insert(MyDataBaseOpenHelper.Database_Name, "name", values);
                return ContentUris.withAppendedId(uri, id);
        }


        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
