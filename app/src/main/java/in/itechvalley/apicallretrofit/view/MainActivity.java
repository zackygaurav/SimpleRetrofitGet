package in.itechvalley.apicallretrofit.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.itechvalley.apicallretrofit.R;
import in.itechvalley.apicallretrofit.model.SingleMovieModel;
import in.itechvalley.apicallretrofit.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity
{
    /*
     * https://api.github.com/users/zackygaurav
     * */

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    /*
    * Init ViewModel
    * */
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        * Init ButterKnife to this Activity
        * */
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        /*
        * Init RecyclerView
        * */
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /*
        * Init ViewModel
        * */
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        /*
        * Observe the LiveData
        * */
        viewModel.getApiObserver().observe(this, new Observer<String>()
        {
            @Override
            public void onChanged(String message)
            {
                // Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        /*
        * Observe List
        * */
        viewModel.getListObserver().observe(this, new Observer<List<SingleMovieModel>>()
        {
            @Override
            public void onChanged(List<SingleMovieModel> movieList)
            {
                if (movieList != null)
                {
                    if (movieList.size() > 0)
                    {
                        // TODO Set Adapter
                        Toast.makeText(MainActivity.this, "Size: " + movieList.size(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @OnClick({R.id.btnFetch, R.id.fab})
    void fetchData()
    {
        viewModel.fetchMoviesList();
    }
}
