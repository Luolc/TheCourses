package luolc.project.thecourses;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

import luolc.project.thecourses.ui.CourseActivity;

/**
 * Created by Luo Liangchen on 2015/11/2.
 */
public class SplashActivity extends BaseActivity {

    private static final String TAG = "SplashActivity";
    private static final long SPLASH_DELAY_IN_MILLIS = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), CourseActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DELAY_IN_MILLIS);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME ||
                super.onKeyDown(keyCode, event);
    }
}
