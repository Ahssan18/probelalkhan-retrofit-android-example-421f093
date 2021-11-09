package net.simplifiedlearning.retrofitexample.modules;

import android.app.Application;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    @Singleton
    @Provides
    public Application getApplication() {
        return application;
    }
    Application application;

    public AppModule(Application application) {
        this.application = application;
    }
}
