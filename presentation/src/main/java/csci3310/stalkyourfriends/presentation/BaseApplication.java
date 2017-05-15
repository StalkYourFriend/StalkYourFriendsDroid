package csci3310.stalkyourfriends.presentation;

import android.app.Application;

import csci3310.stalkyourfriends.presentation.dependency.component.ApplicationComponent;
import csci3310.stalkyourfriends.presentation.dependency.component.DaggerActivityComponent;
import csci3310.stalkyourfriends.presentation.dependency.component.DaggerApplicationComponent;
import csci3310.stalkyourfriends.presentation.dependency.component.FragmentInjector;
import csci3310.stalkyourfriends.presentation.dependency.module.ApplicationModule;

public class BaseApplication extends Application {

    protected ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    protected void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                                        .applicationModule(new ApplicationModule(this))
                                        .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    public FragmentInjector getFragmentInjector() {
        return DaggerActivityComponent.builder()
                .applicationComponent(this.applicationComponent).build();
    }

}
