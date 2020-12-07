package mhj.Grp10_AppProject.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mhj.Grp10_AppProject.Adapter.MarketAdapter;
import mhj.Grp10_AppProject.Model.SalesItem;
import mhj.Grp10_AppProject.R;
import mhj.Grp10_AppProject.ViewModels.MarketsViewModel;
import mhj.Grp10_AppProject.ViewModels.MarketsViewModelFactory;


public class MarketsActivity extends BaseActivity implements MarketAdapter.IItemClickedListener {

    MarketsActivity context;
    MarketsViewModel viewModel;
    private RecyclerView itemList;
    private MarketAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markets);
        context = this;

        itemList = findViewById(R.id.rcvItems);

        viewModel = new ViewModelProvider(context, new MarketsViewModelFactory(this.getApplicationContext())).get(MarketsViewModel.class);
        viewModel.getItems().observe(this, updateObserver);
    }


    //Tilføjet for at kunne starte på firebase - Hvis noget tilføjes til listen bliver det opdaget her
    //Inspired from https://medium.com/@atifmukhtar/recycler-view-with-mvvm-livedata-a1fd062d2280
    Observer<List<SalesItem>> updateObserver = new Observer<List<SalesItem>>() {
        @Override
        public void onChanged(List<SalesItem> UpdatedItems) {
            adapter = new MarketAdapter(context);
            itemList.setLayoutManager(new LinearLayoutManager(context));
            itemList.setAdapter(adapter);
            adapter.updateSalesItemList(UpdatedItems);
        }
    };

    @Override
    public void OnItemClicked(int index) {
        viewModel.SetSelected(index);
        startActivity(new Intent(this, DetailsActivity.class));
    }
}