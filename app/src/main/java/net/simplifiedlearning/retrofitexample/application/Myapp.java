package net.simplifiedlearning.retrofitexample.application;

import android.app.Application;

import net.simplifiedlearning.retrofitexample.component.DaggerInjector;
import net.simplifiedlearning.retrofitexample.component.Injector;
import net.simplifiedlearning.retrofitexample.database.RoomModule;
import net.simplifiedlearning.retrofitexample.modules.AppModule;
import net.simplifiedlearning.retrofitexample.modules.ApiModule;

public class Myapp extends Application {
    public Injector getAppComponents() {
        return appComponents;
    }

    Injector appComponents;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponents = DaggerInjector.builder()
                .apiModule(new ApiModule("https://jsonplaceholder.typicode.com/"))
                .roomModule(new RoomModule(this))
                .build();
    }
}
