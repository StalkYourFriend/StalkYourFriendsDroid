package csci3310.stalkyourfriends.presentation.dependency.component;

import csci3310.stalkyourfriends.presentation.dependency.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class)
public interface ActivityComponent extends FragmentInjector {}
