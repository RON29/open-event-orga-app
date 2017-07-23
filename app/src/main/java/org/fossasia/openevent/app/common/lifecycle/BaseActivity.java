package org.fossasia.openevent.app.common.lifecycle;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.fossasia.openevent.app.common.contract.presenter.IBasePresenter;
import org.fossasia.openevent.app.common.lifecycle.loader.IPresenterProvider;

public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity implements IPresenterProvider<P> {

    private LoaderHandler<P> loaderHandler = new LoaderHandler<>();

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loaderHandler.load(this, getSupportLoaderManager(), getLoaderId(), getPresenterProvider());
    }

    @Override
    @CallSuper
    protected void onStop() {
        super.onStop();
        loaderHandler.presenter.detach();
    }

    @Override
    public P getPresenter() {
        return loaderHandler.getPresenter();
    }
}