package luolc.project.thecourses.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import luolc.project.thecourses.R;
import luolc.project.thecourses.bean.CommentBean;

/**
 * Created by Luo Liangchen on 2015/7/30.
 */
public class CommentListAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<CommentBean> comments;
    private Context context;

    public CommentListAdapter(Context context, List<CommentBean> comments) {
        this.layoutInflater = LayoutInflater.from(context);
        this.comments = comments;
        this.context = context;
    }

    public void reset(List<CommentBean> comments) {
        this.comments = comments;
    }

    @Override
    public int getCount() {
        if (comments == null) {
            return 0;
        }
        return comments.size();
    }

    @Override
    public Object getItem(int position) {
        if (comments == null || position >= comments.size()) {
            return null;
        }
        return comments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_comment_list, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.tvAuthor = (TextView) convertView.findViewById(R.id.tv_author);
            viewHolder.tvDate = (TextView) convertView.findViewById(R.id.tv_date);
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CommentBean commentBean = comments.get(position);
        if (commentBean.getAuthor() != null) {
            viewHolder.tvAuthor.setText(commentBean.getAuthor());
        } else {
            viewHolder.tvAuthor.setText(R.string.course_detail_comment_default_author);
        }
        viewHolder.tvDate.setText(commentBean.getDate());
        viewHolder.tvContent.setText(commentBean.getContent());

        return convertView;
    }

    static class ViewHolder {
        TextView tvAuthor;
        TextView tvDate;
        TextView tvContent;
    }
}
