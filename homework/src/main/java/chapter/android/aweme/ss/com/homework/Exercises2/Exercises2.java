package chapter.android.aweme.ss.com.homework.Exercises2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import chapter.android.aweme.ss.com.homework.R;

/**
 * 作业2：一个抖音笔试题：统计页面所有view的个数
 * Tips：ViewGroup有两个API
 * {@link android.view.ViewGroup #getChildAt(int) #getChildCount()}
 * 用一个TextView展示出来
 */
public class Exercises2 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex2);

        TextView viewNum = findViewById(R.id.viewNum);
        TextView tViewNum = findViewById(R.id.tViewNum);
        View father = findViewById(R.id.rootView);
        View root = viewNum.getRootView();
        viewNum.setText(Integer.toString(getAllChildViewCount(father)));
        tViewNum.setText(Integer.toString(getAllChildViewCount(root)));
    }

    public int getAllChildViewCount(View view) {
        //todo 补全你的代码
        int num = 1;
        if (view == null) return 0;
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                num += getAllChildViewCount(((ViewGroup) view).getChildAt(i));
            }
        } else {
            return 1;
        }
        return num;
    }
}
