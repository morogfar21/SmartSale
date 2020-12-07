package mhj.Grp10_AppProject.ViewModels;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import mhj.Grp10_AppProject.Model.Repository;

//The ViewModel is inspired by https://medium.com/@taman.neupane/basic-example-of-livedata-and-viewmodel-14d5af922d0
// And https://medium.com/@atifmukhtar/recycler-view-with-mvvm-livedata-a1fd062d2280
public class LoginViewModel extends ViewModel{
    private Repository repo;
    public LoginViewModel(Context context) {
        repo = Repository.getInstance(context);
    }

    public void InitMessages() {
        repo.initializePrivateMessages();
    }
}
