
package framework.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import framework.global.Logger;
import framework.model.IRetrofitResponseCallback;

/**
 * Created by aniruddhatr on 6/7/2016. All the fragments should extend this class
 */
public abstract class BaseFragment extends Fragment implements IRetrofitResponseCallback {

    private static final String TAG = "BaseFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i(TAG, "onCreate");
    }

    @Override
    public Context getApplicationContext() {
        return getActivity().getApplicationContext();
    }
}
