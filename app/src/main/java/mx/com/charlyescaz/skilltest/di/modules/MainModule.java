package mx.com.charlyescaz.skilltest.di.modules;

import android.content.Context;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import mx.com.charlyescaz.database.DBSkillTest;
import mx.com.charlyescaz.skilltest.ui.home.contract.HomeContract;
import mx.com.charlyescaz.skilltest.ui.home.data.HomeRepository;
import mx.com.charlyescaz.skilltest.ui.home.presenter.HomePresenter;
import mx.com.charlyescaz.skilltest.ui.home.view.HomeActivity;
import mx.com.charlyescaz.web.api.APISkilltest;

@Module
public abstract class MainModule {

    @Binds
    public abstract HomeContract.View view(HomeActivity activity);

    @Binds
    public abstract Context context(HomeActivity context);

    @Provides
    static HomeContract.Presenter provideHomePresenter(Context context, HomeRepository repository, HomeContract.View view) {

        return new HomePresenter(context,view,repository);
    }


    @Provides
    static HomeRepository provideHomeRepository() {
        return new HomeRepository(APISkilltest.INSTANCE, DBSkillTest.db.skillTestDao());
    }
}
