package net.simplifiedlearning.retrofitexample.database;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {
    private Context context;

    public RoomModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public MyDatabase getDatabase() {
        return MyDatabase.getInstance(context);
    }
}
