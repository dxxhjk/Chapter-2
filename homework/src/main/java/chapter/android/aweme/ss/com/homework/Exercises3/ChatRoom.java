package chapter.android.aweme.ss.com.homework.Exercises3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import chapter.android.aweme.ss.com.homework.R;

public class ChatRoom extends AppCompatActivity {

    public static final String RETURN_INFO = "ChatRoom";
    private String log = new String();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
        ((TextView)findViewById(R.id.tv_with_name)).setText(getIntent().getStringExtra(RETURN_INFO));
        EditText say = (EditText)findViewById(R.id.ed_say);
    }

    public void send(View view){
        String msg = ((EditText)findViewById(R.id.ed_say)).getText().toString();
        if (msg.length() != 0) {
            ((TextView)findViewById(R.id.tv_content_info)).append(msg + "\n" + msg + "\n");
            ((EditText)findViewById(R.id.ed_say)).setText("");
        }
    }
}