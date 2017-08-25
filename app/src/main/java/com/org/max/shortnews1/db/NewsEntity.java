package com.org.max.shortnews1.db;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import java.util.Date;
@Entity(tableName = "news")
public class NewsEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    private String newsTitle;
    private String newsDescription;
    @TypeConverters(DateConverter.class)
    private Date newsDate;


    public NewsEntity(int id, String newsTitle, String newsDescription, Date newsDate) {
        this.id = id;
        this.newsTitle = newsTitle;
        this.newsDescription = newsDescription;
        this.newsDate = newsDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }

    public Date getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(Date newsDate) {
        this.newsDate = newsDate;
    }
}
