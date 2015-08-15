package luolc.project.thecourses.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.umeng.analytics.MobclickAgent;
import com.viewpagerindicator.TabPageIndicator;

import luolc.project.thecourses.R;
import luolc.project.thecourses.adapter.TabAdapter;
import luolc.project.thecourses.bean.VersionBean;
import luolc.project.thecourses.biz.AppBiz;
import luolc.project.thecourses.util.BizUtil;
import luolc.project.thecourses.util.DataUtil;
import luolc.project.thecourses.util.ResourceUtil;

/**
 * Created by Luo Liangchen on 2015/7/25.
 */
public class CourseActivity extends FragmentActivity {
    private static final String TAG = "CourseActivity";

    private Context mContext;

    private VersionBean versionBean;
    private TabPageIndicator mTabPageIndicator;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mTabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_course);
        setUpViewPager();
        new CheckVersionTask().execute();
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

    private void setUpViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mTabPageIndicator = (TabPageIndicator) findViewById(R.id.indicator);
        mTabAdapter = new TabAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mTabAdapter);
        mTabPageIndicator.setViewPager(mViewPager, 0);
    }

    public void onClickSettingsButton(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    class CheckVersionTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            StringBuffer status = new StringBuffer("");
            versionBean = BizUtil.get(mContext, new AppBiz(AppBiz.VERSION), status);
            return status.toString();
        }
        @Override
        protected void onPostExecute(String status) {
            super.onPostExecute(status);
            if (status.equals(getString(R.string.common_exception_none))) {
                if (versionBean.getVersion_code() > DataUtil.getVersionCode(mContext)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle(R.string.settings_new_version_exist)
                            .setMessage(versionBean.getNote().replace("[sp]", "\n"))
                            .setPositiveButton(R.string.settings_update, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String url = ResourceUtil
                                            .getApkDownloadUrl(versionBean.getVersion_name());
                                    Uri uri = Uri.parse(url);
                                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                    mContext.startActivity(intent);
                                }
                            })
                            .setNegativeButton(R.string.settings_cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {}
                            })
                            .create().show();
                }
            }
        }
    }
}
