package mhj.Grp10_AppProject.Activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import mhj.Grp10_AppProject.Model.PrivateMessage;
import mhj.Grp10_AppProject.R;
import mhj.Grp10_AppProject.ViewModels.ViewMessageViewModel;
import mhj.Grp10_AppProject.ViewModels.ViewMessageViewModelFactory;

public class ViewMessageActivity extends AppCompatActivity {
    private ViewMessageViewModel viewModel;
    private PrivateMessage _privatemessage;

    // widgets
    private TextView textSender, textRegarding, textMessage, textReply;
    private Button btnReply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_message);
        setupUI();
        viewModel = new ViewModelProvider(this, new ViewMessageViewModelFactory(this.getApplicationContext()))
                .get(ViewMessageViewModel.class);
        viewModel.returnSelected().observe(this, updateObserver );
    }

    private void setupUI() {
        textSender = findViewById(R.id.viewMessageTextFrom);
        textRegarding = findViewById(R.id.viewMessageTextRegarding);
        textMessage = findViewById(R.id.viewMessageTextMessage);
        btnReply = findViewById(R.id.viewMessageBtnReply);
        textReply = findViewById(R.id.viewMessageReply);
        btnReply.setOnClickListener(view -> reply());
    }

    private void reply() {
        String replyMessage = textReply.getText().toString();
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss", Locale.getDefault()).format(new Date());

        PrivateMessage privateMessage = new PrivateMessage();
        privateMessage.setMessageDate(timeStamp);
        privateMessage.setSender(_privatemessage.getReceiver());
        privateMessage.setReceiver(_privatemessage.getSender());
        privateMessage.setMessageBody(replyMessage);
        privateMessage.setRegarding(_privatemessage.getRegarding());

        viewModel.reply(privateMessage);
        Toast.makeText(this, getString(R.string.reply_sent), Toast.LENGTH_SHORT).show();
        finish();
    }

    Observer<PrivateMessage> updateObserver = new Observer<PrivateMessage>() {
        @Override
        public void onChanged(PrivateMessage message) {
            if(message != null)
            {
                _privatemessage = message;
                textMessage.setText(message.getMessageBody());
                textRegarding.setText(message.getRegarding());
                textSender.setText(message.getSender());
            }
            Log.d("PrivateMessage", "ViewMessage:failed. Message = null ");
        }
    };
}