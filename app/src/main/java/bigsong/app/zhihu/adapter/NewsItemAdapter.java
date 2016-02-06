package bigsong.app.zhihu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import bigsong.app.zhihu.R;
import bigsong.app.zhihu.model.Latest;

/**
 * Created by Jesse on 2015/12/1.
 */
public class NewsItemAdapter extends BaseAdapter {
    private List<Latest.StoriesEntity> entities;
    private Context context;
    private ImageLoader mImageLoader;
    private DisplayImageOptions options;

    public NewsItemAdapter(Context context, List<Latest.StoriesEntity> items) {
        this.context = context;
        entities = items;
        mImageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();
    }

    @Override
    public int getCount() {
        return entities.size();
    }

    @Override
    public Object getItem(int position) {
        return entities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.iv_title = (ImageView) convertView.findViewById(R.id.iv_title);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

//        String readSeq = PreUtils.getStringFromDefault(context,"read","");
        viewHolder.tv_title.getParent();
        return convertView;
    }

    public static class ViewHolder {
        TextView tv_title;
        ImageView iv_title;
    }
}
