package com.org.max.shortnews1.db;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import java.util.List;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;
@Dao
@TypeConverters(DateConverter.class)
public interface NewsDao {

    @Query("select * from news")
    LiveData<List<NewsEntity>> getAllBorrowedItems();

    @Query("select * from news where id = :id")
    NewsEntity getItembyId(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addBorrow(List<NewsEntity> newsEntityList);

    @Delete
    void deleteBorrow(NewsEntity borrowModel);

}
