
package framework.global;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.os.Environment;
import android.util.Log;

public class Logger {

    private static String mName = "";

    private static final String PREFIX_TAG = "MPOSIntegration_";
    private static final SimpleDateFormat stSDFLoggerMessageTime = new SimpleDateFormat(
            "MM/dd/yyyy hh:mm:ss:SS aa",
            Locale.getDefault());
    private static final SimpleDateFormat stSDFFileWithDateIdentifier = new SimpleDateFormat(
            "MM/dd/yyyy",
            Locale.getDefault());
    private static File root;
    private static File stLoggerFile;
    private static FileWriter stLoggerFileWriter;
    private static BufferedWriter stBufferedWriter;

    private static boolean LOGGING = false;
    private static boolean stIsFileInitialized = false;
    private static final String stLogDirectory = "/MPOSLog_Report";

    static {
        // Create directory on SDCARD to save log files.
        File file = new File(Environment.getExternalStorageDirectory(), stLogDirectory);
        if (!file.mkdir()) {
            if (LOGGING)
                Log.e(PREFIX_TAG, "Failed to create log directory");
        }

    }

    public static void setLoggerFileName(String fileName) {
        mName = fileName;
        // deleteOldFile();
        initializeFile();
    }

    private static void initializeFile() {

        try {
            root = new File(Environment.getExternalStorageDirectory(), stLogDirectory);
            stLoggerFile = new File(root, getFileName());
            stLoggerFileWriter = new FileWriter(stLoggerFile, true);
            stBufferedWriter = new BufferedWriter(stLoggerFileWriter);
            stIsFileInitialized = true;
        } catch (IOException e) {
            if (LOGGING)
                Log.e(PREFIX_TAG,
                        "Failed to create file on the sdcard. PLease check whether SDcard is mounted. Message:"
                                + e.getMessage());
        }

    }

    public static void d(String tag, String message) {

        if (LOGGING) {
            Log.d(PREFIX_TAG + tag, message);
            writeToLogcatAndSDCARD(PREFIX_TAG + tag, message);
        }
    }

    public static void e(String tag, String message) {

        if (LOGGING) {
            Log.e(PREFIX_TAG + tag, message);
            writeToLogcatAndSDCARD(PREFIX_TAG + tag, message);
        }
    }

    public static void v(String tag, String message) {

        if (LOGGING) {
            Log.v(PREFIX_TAG + tag, message);
            writeToLogcatAndSDCARD(PREFIX_TAG + tag, message);
        }
    }

    public static void i(String tag, String message) {

        if (LOGGING) {
            Log.i(PREFIX_TAG + tag, message);
            writeToLogcatAndSDCARD(PREFIX_TAG + tag, message);
        }
    }

    @SuppressWarnings("unused")
    public static void w(String tag, String message) {

        if (LOGGING) {
            Log.w(PREFIX_TAG + tag, message);
            writeToLogcatAndSDCARD(PREFIX_TAG + tag, message);
        }
    }

    /**
     * Turn on logging to logcat and sdcard
     */
    public static void setLogging() {
        LOGGING = true;
    }

    private static void writeToLogcatAndSDCARD(String tag, String message) {

        if (!stIsFileInitialized) {
            initializeFile();
        }

        try {

            if (root.canWrite()) {
                // checkDate();
                String line_separator = "\n";
                String text = getCurrentDateTime() + " " + tag + " " + message + line_separator;
                if (stBufferedWriter == null) {
                    stLoggerFileWriter = new FileWriter(stLoggerFile, true);
                    stBufferedWriter = new BufferedWriter(stLoggerFileWriter);
                }
                stBufferedWriter.write(text);
                stBufferedWriter.flush();

            } else {
                if (LOGGING)
                    Log.e(PREFIX_TAG, "Application does not have permission to write log file");
            }

        } catch (IOException e) {
            if (LOGGING)
                Log.e(PREFIX_TAG, "Could not write file " + e.getMessage());
        } catch (Exception e) {
            Log.e(PREFIX_TAG,
                    "Exception Failed to write to file : Message:->"
                            + e.getMessage());
        }
    }

    public static void deleteOldFile() {
        // delete the old file
        root = new File(Environment.getExternalStorageDirectory(), stLogDirectory);
        if (root.listFiles() != null) {
            for (File file : root.listFiles()) {
                if (file.getName().startsWith(mName)) {
                    if (file.getName().equals(getFileName())) {
                        boolean result = file.delete();
                        Log.v(PREFIX_TAG, "result:->" + result);
                    }
                }
            }
        }
    }

    private static String getCurrentDateTime() {
        return stSDFLoggerMessageTime.format(new Date());
    }

    private static String getFileName() {
        String szCurrentDate = getFormatedDateString().replaceAll("/", "");

        return mName + "_Logreport" + szCurrentDate + ".txt";
    }

    private static String getFormatedDateString() {
        return stSDFFileWithDateIdentifier.format(new Date());
    }

}
