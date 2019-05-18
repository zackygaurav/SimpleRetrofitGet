package in.itechvalley.apicallretrofit.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import in.itechvalley.apicallretrofit.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment
{


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

        return rootView;
    }

}
