package in.itechvalley.apicallretrofit.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import butterknife.ButterKnife;
import in.itechvalley.apicallretrofit.R;
import in.itechvalley.apicallretrofit.viewmodel.MainViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment
{
    private MainViewModel mainViewModel;

    public BlankFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);

        /*
        * Bind ButterKnife to this Fragment
        * */
        ButterKnife.bind(this, rootView);

        /*
        * Init ViewModel
        * */
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);



        return rootView;
    }

}
