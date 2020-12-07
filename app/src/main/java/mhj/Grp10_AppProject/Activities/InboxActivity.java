package mhj.Grp10_AppProject.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mhj.Grp10_AppProject.Adapter.InboxAdapter;
import mhj.Grp10_AppProject.Model.PrivateMessage;
import mhj.Grp10_AppProject.R;
import mhj.Grp10_AppProject.ViewModels.InboxViewModel;
import mhj.Grp10_AppProject.ViewModels.InboxViewModelFactory;


public class InboxActivity extends BaseActivity implements InboxAdapter.IMessageClickedListener {

    InboxActivity context;
    private InboxViewModel viewModel;
    private RecyclerView recyclerView;
    private InboxAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        context = this;

        recyclerView = findViewById(R.id.inboxMessages);

        viewModel = new ViewModelProvider(this, new InboxViewModelFactory(this.getApplicationContext()))
                .get(InboxViewModel.class);
        viewModel.getMessages().observe(this, updateObserver);
    }

    Observer<List<PrivateMessage>> updateObserver = new Observer<List<PrivateMessage>>() {
        @Override
        public void onChanged(List<PrivateMessage> UpdatedItems) {
            adapter = new InboxAdapter(context);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(adapter);
            adapter.updateMessageList(UpdatedItems);
        }
    };

    @Override
    public void onMessageClicked(int index) {

        viewModel.SetSelectedMessage(index);
        viewModel.setRead(index);
        Intent ViewMessage = new Intent(this, ViewMessageActivity.class);
        startActivity(ViewMessage);
    }
}