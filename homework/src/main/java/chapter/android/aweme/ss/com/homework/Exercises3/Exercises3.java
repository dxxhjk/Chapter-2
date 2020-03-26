package chapter.android.aweme.ss.com.homework.Exercises3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.InputStream;
import java.util.List;
import chapter.android.aweme.ss.com.homework.R;
import chapter.android.aweme.ss.com.homework.model.Message;
import chapter.android.aweme.ss.com.homework.model.PullParser;

/**
 * 大作业:实现一个抖音消息页面,
 * 1、所需的data数据放在assets下面的data.xml这里，使用PullParser这个工具类进行xml解析即可
 * <p>如何读取assets目录下的资源，可以参考如下代码</p>
 * <pre class="prettyprint">
 *
 *         @Override
 *     protected void onCreate(@Nullable Bundle savedInstanceState) {
 *         super.onCreate(savedInstanceState);
 *         setContentView(R.layout.activity_xml);
 *         //load data from assets/data.xml
 *         try {
 *             InputStream assetInput = getAssets().open("data.xml");
 *             List<Message> messages = PullParser.pull2xml(assetInput);
 *             for (Message message : messages) {
 *
 *             }
 *         } catch (Exception exception) {
 *             exception.printStackTrace();
 *         }
 *     }
 * </pre>
 * 2、所需UI资源已放在res/drawable-xxhdpi下面
 *
 * 3、作业中的会用到圆形的ImageView,可以参考 widget/CircleImageView.java
 */
public class Exercises3 extends AppCompatActivity implements GreenAdapter.ListItemClickListener {

    private GreenAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private Toast mToast;
    private RecyclerView.ViewHolder tempHolder;
    public List<Message> messages;

    public void showDialog (View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher_round);
        builder.setTitle("提示");
        builder.setMessage("该功能尚未开放，敬请期待");
        builder.setPositiveButton("好",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void initColor() {
        LinearLayout BGColor = (LinearLayout)findViewById(R.id.BGColor);
        BGColor.setBackgroundColor(getResources().getColor(R.color.colorBackground));
        TextView xiaoxi = (TextView)findViewById(R.id.xiaoxi);
        xiaoxi.setTextColor(getResources().getColor(R.color.white));
        Button btn0 = (Button)findViewById(R.id.btn0);
        btn0.setBackgroundColor(getResources().getColor(R.color.colorBackground));
        Button btn1 = (Button)findViewById(R.id.btn1);
        btn1.setBackgroundColor(getResources().getColor(R.color.colorBackground));
        Button btn2 = (Button)findViewById(R.id.btn2);
        btn2.setBackground(getResources().getDrawable(R.mipmap.jia));
        Button btn3 = (Button)findViewById(R.id.btn3);
        btn3.setBackgroundColor(getResources().getColor(R.color.colorBackground));
        Button btn4 = (Button)findViewById(R.id.btn4);
        btn4.setBackgroundColor(getResources().getColor(R.color.colorBackground));
    }

    private void initListItem() {
        //获取数据
        try {
            InputStream assetInput = getAssets().open("data.xml");
            messages = PullParser.pull2xml(assetInput);
            for (int i = 0; i < messages.size(); i++) {
                Log.d("msg" + Integer.toString(i), messages.get(i).toString());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        //获取recyclerView控件
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        //为recyclerView设置布局管理器
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //为recycler设置数据适配器
        mAdapter = new GreenAdapter(messages.size(), this, messages);
        mRecyclerView.setAdapter(mAdapter);

        //滑动效果
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            // 最后一个完全可见项的位置
            private int lastCompletelyVisibleItemPosition;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (visibleItemCount > 0 && lastCompletelyVisibleItemPosition >= totalItemCount - 1) {
                        Toast.makeText(Exercises3.this, "已滑动到底部!,触发loadMore", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    lastCompletelyVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
                }
                Log.d("ex3", "onScrolled: lastVisiblePosition=" + lastCompletelyVisibleItemPosition);
            }
        });

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex3);
        initColor();
        initListItem();
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Log.d("ex3", "onListItemClick: ");
        //((TextView)findViewById(R.id.tv_with_name)).setText("???");
        //setContentView(R.layout.activity_chatroom);
        Intent startIntent = new Intent(this, ChatRoom.class);
        startIntent.putExtra(ChatRoom.RETURN_INFO, messages.get(clickedItemIndex).getTitle());
        startActivity(startIntent);
    }
}
