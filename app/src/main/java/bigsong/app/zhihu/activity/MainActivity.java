package bigsong.app.zhihu.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import bigsong.app.zhihu.R;
import bigsong.app.zhihu.fragment.MenuFragment;

public class MainActivity extends AppCompatActivity {
    private FrameLayout fl_content;
    private MenuFragment menu_fragment;
    private DrawerLayout mDrawerLayout;
    private SwipeRefreshLayout sr;
    private String curId;
    private Toolbar toolbar;
    private boolean isLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setSwipeRefreshEnable(boolean enable) {
        sr.setEnabled(enable);
    }


}
