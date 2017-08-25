package com.org.max.shortnews1.db;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
@Database(entities = {NewsEntity.class}, version = 2,exportSchema = false)
public abstract class NewsDatabase extends RoomDatabase {
    //https://developer.android.com/reference/android/arch/persistence/room/Embedded.html
    private static NewsDatabase INSTANCE;
    public static NewsDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), NewsDatabase.class, "news_db")
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public abstract NewsDao itemAndPersonModel();

}
