package luolc.project.thecourses.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import luolc.project.thecourses.R;
import luolc.project.thecourses.config.Constant;
import luolc.project.thecourses.util.DataUtil;


/**
 * Created by Luo Liangchen on 2015/7/25.
 */
public class SplashActivity extends Activity {
    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setUpVersionNote();
        final SplashDelayTask task = new SplashDelayTask();
        task.execute("");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //set delay
                    task.get(Constant.SPLASH_TIME_DELAY, TimeUnit.MILLISECONDS);
                } catch (TimeoutException e) {
                    task.cancel(true);
                    Log.i(TAG, "time out");
                } catch (Exception e) {
                    task.cancel(true);
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    private void setUpVersionNote() {
        TextView tvVersionLicence = (TextView) findViewById(R.id.tv_version_licence);
        String content = getString(R.string.splash_licence)
                .replace("1.0.0", DataUtil.getVersionName(this));
        tvVersionLicence.setText(content);
    }

    //disable the back button
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME ||
                super.onKeyDown(keyCode, event);
    }

    class SplashDelayTask extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(String... params) {
            while (!isCancelled()) {}
            return null;
        }
        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.i(TAG, "-->in canceled");
            Intent intent = new Intent(SplashActivity.this, CourseActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            SplashActivity.this.finish();
        }
    }
}
