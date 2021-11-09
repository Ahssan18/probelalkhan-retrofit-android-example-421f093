package net.simplifiedlearning.retrofitexample.database;

import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Query("SELECT * FROM post")
    List<Post> getPosts();

    @Insert
    void addPost(Post post);
}
