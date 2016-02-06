package bigsong.app.zhihu.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import bigsong.app.zhihu.R;
import bigsong.app.zhihu.model.NewsListItem;
import bigsong.app.zhihu.util.Constant;
import bigsong.app.zhihu.util.HttpUtils;
import cz.msebera.android.httpclient.Header;

public class MenuFragment extends Fragment implements View.OnClickListener {

    private ListView lv_item;
    private TextView tv_main;

    private List<NewsListItem> items;
    private Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        tv_main = (TextView) view.findViewById(R.id.iv_main);
        tv_main.setOnClickListener(this);
        lv_item = (ListView) view.findViewById(R.id.lv_item);
        getItems();
        return view;
    }

    private void getItems() {
        items = new ArrayList<NewsListItem>();
        HttpUtils.get(Constant.THEMES, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONArray itemsArray = response.getJSONArray("others");
                    for (int i = 0; i < itemsArray.length();i++){
                        NewsListItem newsListItem = new NewsListItem();
                        JSONObject itemObject = itemsArray.getJSONObject(i);
                        newsListItem.setTitle(itemObject.getString("name"));
                        newsListItem.setId(itemObject.getString("id"));
                        items.add(newsListItem);
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            lv_item.setAdapter(new NewsTypeAdapter());
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    public class NewsTypeAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.menu_item,parent,false);
            }
            TextView tv_item = (TextView) convertView.findViewById(R.id.tv_item);
            tv_item.setText(items.get(position).getTitle());
            return convertView;
        }
    }
}