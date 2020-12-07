package mhj.Grp10_AppProject.ViewModels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import mhj.Grp10_AppProject.Model.PrivateMessage;
import mhj.Grp10_AppProject.Model.Repository;

public class ViewMessageViewModel extends ViewModel {

    private Repository repo;
    public ViewMessageViewModel(Context context) {
        repo = Repository.getInstance(context);
    }
    public LiveData<PrivateMessage> returnSelected()
    {
        return repo.getSelectedMessage();
    }

    public void reply(PrivateMessage privateMessage) {
        repo.sendMessage(privateMessage);
    }
}
