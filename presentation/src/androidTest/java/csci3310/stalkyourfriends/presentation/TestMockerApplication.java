package csci3310.stalkyourfriends.presentation;

import csci3310.stalkyourfriends.presentation.dependency.component.FragmentInjector;

public class TestMockerApplication extends BaseApplication {

    @Override
    public FragmentInjector getFragmentInjector() {
        return DaggerTestMockerComponent.builder()
                .applicationComponent(this.applicationComponent)
                .testMockerModule(new TestMockerModule()).build();
    }
}
