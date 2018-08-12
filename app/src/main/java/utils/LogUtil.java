package utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wrjeoung on 2017-06-12.
 */

public class LogUtil {
    static public final String TAG = "Sample";
    static boolean DEBUG = true;
    static boolean LOGFILE = false;

    /** Log Level Error **/
    public static final void e(String message) {
        if (DEBUG) Log.e(TAG, buildLogMsg(message));
    }
    /** Log Level Warning **/
    public static final void w(String message) {
        if (DEBUG) Log.w(TAG, buildLogMsg(message));
    }
    /** Log Level Information **/
    public static final void i(String message) {
        if (DEBUG) Log.i(TAG, buildLogMsg(message));
    }
    /** Log Level Debug **/
    public static final void d(String message) {
        if (DEBUG) Log.d(TAG, buildLogMsg(message));
    }
    /** Log Level Verbose **/
    public static final void v(String message) {
        if (DEBUG) Log.v(TAG, buildLogMsg(message));
    }

    /** Log Level Error **/
    public static final void e() {
        if (DEBUG) Log.e(TAG, buildLogMsg());
    }
    /** Log Level Warning **/
    public static final void w() {
        if (DEBUG) Log.w(TAG, buildLogMsg());
    }
    /** Log Level Information **/
    public static final void i() {
        if (DEBUG) Log.i(TAG, buildLogMsg());
    }
    /** Log Level Debug **/
    public static final void d() {
        if (DEBUG) Log.d(TAG, buildLogMsg());
    }
    /** Log Level Verbose **/
    public static final void v() {
        if (DEBUG) Log.v(TAG, buildLogMsg());
    }

    public static String buildLogMsg(String message) {

        StackTraceElement ste = Thread.currentThread().getStackTrace()[4];

        StringBuilder sb = new StringBuilder();

        sb.append("[");
        sb.append(ste.getFileName().replace(".java", ""));
        sb.append("::");
        sb.append(ste.getMethodName());
        sb.append("]");
        sb.append("->"+ste.getLineNumber()+" ");
        sb.append(message);

        return sb.toString();
    }

    public static String buildLogMsg() {
        StackTraceElement ste = Thread.currentThread().getStackTrace()[4];

        StringBuilder sb = new StringBuilder();

        sb.append("[");
        sb.append(ste.getFileName().replace(".java", ""));
        sb.append("::");
        sb.append(ste.getMethodName());
        sb.append("]");
        sb.append("->"+ste.getLineNumber()+" ");

        return sb.toString();
    }

    /**
     * message내용을 현재시간의 파일명으로 파일 생성, 현재시간 format은 변경 가능.
     * e.g) 2017-10-02.log
     * @param message 파일내용
     */
    public  static void logFileWrite(String message)
    {
        if ( LOGFILE ) {
            Date date = new Date();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String fileName = TAG + sdf1.format(date) + ".log";
            File file = new File(Environment.getExternalStorageDirectory(), fileName);
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(message.getBytes());
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * message내용을 현재시간의 파일명으로 파일 생성, 현재시간 format은 변경 가능.
     * 파일명의 식별자를 추가 할 수 있다
     * e.g) 2017-10-02_{fileIdentifier}.log
     * @param fileIdentifier 파일명 날짜 뒤에 붙는 식별자
     * @param message 파일내용
     */
    public  static void logFileWrite(String fileIdentifier, String message)
    {
        if ( LOGFILE ) {
            Date date = new Date();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String filename = TAG + sdf1.format(date) + "_" + fileIdentifier + ".log";
            File file = new File(Environment.getExternalStorageDirectory(), filename);
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(message.getBytes());
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
