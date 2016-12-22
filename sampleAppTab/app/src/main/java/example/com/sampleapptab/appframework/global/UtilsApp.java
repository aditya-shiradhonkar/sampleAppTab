package example.com.sampleapptab.appframework.global;

import java.math.BigDecimal;
import java.math.RoundingMode;


import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import example.com.sampleapptab.R;

/**
 * Created by aniruddhatr on 10/18/2016.
 * Class is the subclass of framework.global.UtilsApp, and will implement the application specific
 *  utility
 */

public class UtilsApp extends framework.global.Utils{
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static void showCustomDialog(final Context context, String title, String message,
                                        String btnLabel, final int drawable) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.common_custom_dialog);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(false);

        ((TextView) dialog.findViewById(R.id.tvDialogTitle)).setText(title);
        ((TextView) dialog.findViewById(R.id.tvDialogMessage)).setText(message);
        TextView closeButton = (TextView) dialog.findViewById(R.id.tvDialogClose);
        closeButton.setText(btnLabel);
        ((ImageView) dialog.findViewById(R.id.ivDialogImage)).setImageResource(drawable);

        (dialog.findViewById(R.id.tvDialogClose)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
