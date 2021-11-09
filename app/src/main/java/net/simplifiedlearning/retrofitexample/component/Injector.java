package net.simplifiedlearning.retrofitexample.component;

import net.simplifiedlearning.retrofitexample.activities.MainActivity;
import net.simplifiedlearning.retrofitexample.modules.ApiModule;
import net.simplifiedlearning.retrofitexample.modules.AppModule;
import net.simplifiedlearning.retrofitexample.database.RoomModule;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component(modules = {AppModule.class, ApiModule.class, RoomModule.class})
public interface Injector {
    void InjectMainActivity(MainActivity activity);
}
