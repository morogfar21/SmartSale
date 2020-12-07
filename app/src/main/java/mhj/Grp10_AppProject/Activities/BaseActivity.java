package mhj.Grp10_AppProject.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import mhj.Grp10_AppProject.R;

//Inspired from: https://stackoverflow.com/questions/17889240/reuse-the-action-bar-in-all-the-activities-of-app
public class BaseActivity extends AppCompatActivity {
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
    }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.messagesTxt:
                OpenMessages();
                return true;
            case R.id.logoutTxt:
                LogOut();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem logoutItem = menu.findItem(R.id.logoutTxt);
        MenuItem messagesItem = menu.findItem(R.id.messagesTxt);
        MenuItem userItem = menu.findItem(R.id.userTxt);

        if(auth.getCurrentUser() != null)
        {
            logoutItem.setVisible(true);
            messagesItem.setVisible(true);
            userItem.setVisible(true);
            userItem.setTitle( getString(R.string.menu_user) + ": " + auth.getCurrentUser().getEmail());
        }
        return super.onPrepareOptionsMenu(menu);
    }

    private void OpenMessages() {
        Intent intent = new Intent(this, InboxActivity.class);
        startActivity(intent);
    }

    public void LogOut() {
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(this, getString(R.string.logged_out), Toast.LENGTH_SHORT).show();
            invalidateOptionsMenu();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }
}

