package net.simplifiedlearning.retrofitexample.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.simplifiedlearning.retrofitexample.Api;
import net.simplifiedlearning.retrofitexample.R;
import net.simplifiedlearning.retrofitexample.application.Myapp;
import net.simplifiedlearning.retrofitexample.database.MyDatabase;
import net.simplifiedlearning.retrofitexample.database.Post;
import net.simplifiedlearning.retrofitexample.models.Posts;
import net.simplifiedlearning.retrofitexample.threads.Executor;

import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    @Inject
    Retrofit retrofitClient;
    @Inject
    MyDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Myapp) getApplication()).getAppComponents().InjectMainActivity(this);
        listView = findViewById(R.id.listViewHeroes);

        //calling the method to display the heroes
        getHeroes();
        getDataBaseData();
    }

    private void getDataBaseData() {
        Executor.IoThread(() -> {
            database.getDao().addPost(new Post("Ahssan","hy this is ahssan"));
            String data=database.getDao().getPosts().toString();
           showToastonMainThread(data);
        });


    }

    private void showToastonMainThread(String msg) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, ""+msg, Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void getHeroes() {
        Call<List<Posts>> call = retrofitClient.create(Api.class).getHeroes();
        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                List<Posts> heroList = response.body();

                //Creating an String array for the ListView
                String[] heroes = new String[heroList.size()];

                //looping through all the heroes and inserting the names inside the string array
                for (int i = 0; i < heroList.size(); i++) {
                    heroes[i] = heroList.get(i).getTitle();
                }

                //displaying the string array into listview
                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, heroes));
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
