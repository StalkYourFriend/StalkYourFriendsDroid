package csci3310.stalkyourfriends.presentation;

import csci3310.stalkyourfriends.presentation.dependency.ActivityScope;
import csci3310.stalkyourfriends.presentation.dependency.component.ApplicationComponent;
import csci3310.stalkyourfriends.presentation.dependency.component.FragmentInjector;

import dagger.Component;

@ActivityScope
@Component(modules = TestMockerModule.class, dependencies = ApplicationComponent.class)
public interface TestMockerComponent extends FragmentInjector {}
