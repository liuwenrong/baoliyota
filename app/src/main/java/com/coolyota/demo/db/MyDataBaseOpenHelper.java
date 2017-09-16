package com.coolyota.demo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


/**
 * des:
 *
 * @author liuwenrong
 * @version 1.0, 2017/8/22
 */
public class MyDataBaseOpenHelper extends SQLiteOpenHelper {
    private static final String ASSET_DB_PATH = "databases";
    public static final String Database_Name = "baoliyota";
    private static final String Sql_Create_Friend_Table = "CREATE TABLE " +
            FriendTable.Table_Name + "(" +
            FriendTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FriendTable.Column_Friend_Name + " TEXT, " +
            FriendTable.Column_IsOpen + " INTEGER" + ")";
    public static MyDataBaseOpenHelper mInstance;

    public class FriendTable implements BaseColumns {
        public static final String Table_Name = "dynamic_toggle";
        public static final String Column_Friend_Name = "name";
        public static final String Column_IsOpen = "isOpen";
    }

    private final Context mContext;
    private final String mName;
    private final int mNewVersion;
    private String mDatabasePath;
    private String mAssetPath;

    private MyDataBaseOpenHelper(Context context) {

        super(context, Database_Name, null, 1);
        mContext = context;
        mName = FriendTable.Table_Name;
        mNewVersion = 1;

    }

    public static MyDataBaseOpenHelper getInstance(Context ctx) {
        if (mInstance == null) {
            synchronized (MyDataBaseOpenHelper.class) {
                if (mInstance == null) {
                    mInstance = new MyDataBaseOpenHelper(ctx);
                }
            }
        }
        return mInstance;
    }

    public MyDataBaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

        mContext = context;
        mName = name;
        mNewVersion = version;
        mAssetPath = ASSET_DB_PATH + "/" + name;
        mDatabasePath = context.getApplicationInfo().dataDir + "/databases";

    }

    /*public MyDataBaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }*/

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Sql_Create_Friend_Table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
