package luolc.project.thecourses.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;

import luolc.project.thecourses.R;
import luolc.project.thecourses.bean.VersionBean;
import luolc.project.thecourses.biz.AppBiz;
import luolc.project.thecourses.util.BizUtil;
import luolc.project.thecourses.util.DataUtil;
import luolc.project.thecourses.util.ResourceUtil;

/**
 * Created by Luo Liangchen on 2015/8/3.
 */
public class SettingsActivity extends Activity {
    private static final String TAG = "SettingsActivity";

    private Context mContext;
    private VersionBean versionBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_settings);
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

    public void onClickBackButton(View view) {
        this.finish();
    }

    public void onClickMonologue(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage(R.string.settings_monologue_content)
                .setPositiveButton(R.string.settings_confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .create().show();
    }

    public void onClickCheckVersion(View view) {
        new CheckVersionTask().execute();
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
               if (versionBean.getVersion_code() <= DataUtil.getVersionCode(mContext)) {
                   Toast.makeText(mContext, R.string.settings_no_more_latest_version, Toast.LENGTH_SHORT).show();
               } else {
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
            } else {
                Toast.makeText(mContext, status, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
