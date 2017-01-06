
package example.com.sampleapptab.tv.channels.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import example.com.sampleapptab.R;
import framework.global.Logger;
import framework.ui.BaseActivity;

public class ChannelActivity extends BaseActivity {

    public static final String TAG = "ChannelActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channels);

        ChannelsFragment channelsFragment = new ChannelsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.activity_channel, channelsFragment, "ChannelsFragment");
        fragmentTransaction.commit();
        Logger.i(TAG, "onCreate");
    }
}
