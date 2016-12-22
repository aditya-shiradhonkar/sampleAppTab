
package framework.global;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.ImageView;

import framework.network.communication.NetworkCommunication;
import framework.ui.BaseFragment;

/**
 * Created by aniruddhatr on 6/13/2016. This class will contain the application framework specific
 * utility and to be extended by Applicatio specifc UtilsApp
 */
public class Utils {
    private static final String TAG = "UtilsApp";

    public static void loadImage(BaseFragment uiHandler, String url, int placeHolderImageDrawable,
            ImageView imageView, NetworkCommunication networkCommunication) {
        Logger.i(TAG, "loadImage :" + url);
        if (networkCommunication != null) {
            networkCommunication.getPicasso().load(url).placeholder(placeHolderImageDrawable)
                    .resize(92, 92)
                    .into(imageView);
        }
    }

    public static ProgressDialog showProgressDialog(Context context, String title, String message) {

        ProgressDialog progressDialog = ProgressDialog.show(context, title, message);
        progressDialog.setCancelable(false);
        return progressDialog;
    }

    public static void cancelProgressDialog(ProgressDialog progressDialog) {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
    }
}
