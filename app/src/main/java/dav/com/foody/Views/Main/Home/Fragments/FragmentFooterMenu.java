package dav.com.foody.Views.Main.Home.Fragments;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabWidget;
import android.widget.TextView;

import dav.com.foody.R;
import dav.com.foody.Views.AddItem.AddItemActivity;

public class FragmentFooterMenu extends Fragment implements View.OnClickListener{

    TabWidget tabWidget;
    View viewClose;
    TextView txtAddItem;

    public FragmentFooterMenu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.custom_dialog, container, false);
        viewClose = view.findViewById(R.id.frame_close);
        tabWidget = (TabWidget) getActivity().getParent().findViewById(android.R.id.tabs);
        txtAddItem = (TextView) view.findViewById(R.id.itAddAddress);



        viewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabWidget.setVisibility(View.VISIBLE);
                view.setVisibility(View.GONE);
            }
        });

        txtAddItem.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.itAddAddress:
                Intent iAddItem = new Intent(getActivity(), AddItemActivity.class);
                startActivity(iAddItem);
                break;
        }
    }
}
