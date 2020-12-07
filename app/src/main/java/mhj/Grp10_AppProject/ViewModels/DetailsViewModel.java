package mhj.Grp10_AppProject.ViewModels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import mhj.Grp10_AppProject.Model.Repository;
import mhj.Grp10_AppProject.Model.SalesItem;


public class DetailsViewModel extends ViewModel {

    private Repository repo;
    public DetailsViewModel(Context context) {
        repo = Repository.getInstance(context);
    }

    public LiveData<SalesItem> returnSelected()
    {
        return repo.getSelectedItem();
    }

}
