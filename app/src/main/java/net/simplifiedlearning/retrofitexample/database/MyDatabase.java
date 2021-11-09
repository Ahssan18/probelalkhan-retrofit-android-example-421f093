package net.simplifiedlearning.retrofitexample.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = Post.class,exportSchema = false,version = 1)
public abstract class MyDatabase extends androidx.room.RoomDatabase {
    public static final String DB_NAME = "room";
    public static MyDatabase database;

    public static MyDatabase getInstance (Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(), MyDatabase.class, DB_NAME)
                   /* .allowMainThreadQueries()*/
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    public abstract Dao getDao();


}
