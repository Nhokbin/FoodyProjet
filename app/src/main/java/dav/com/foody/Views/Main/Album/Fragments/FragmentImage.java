package dav.com.foody.Views.Main.Album.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dav.com.foody.R;

/**
 * Created by binhb on 07/04/2017.
 */

public class FragmentImage extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_album_content, container, false);

        return view;
    }
}
