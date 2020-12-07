package mhj.Grp10_AppProject.ViewModels;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

//Inspired from: https://stackoverflow.com/questions/46283981/android-viewmodel-additional-arguments
public class DetailsViewModelFactory implements ViewModelProvider.Factory {
    private final Context con;

    public DetailsViewModelFactory(Context context) {
        con = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new DetailsViewModel(con);
    }
}
