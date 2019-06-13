package com.oscarsancz.biblioapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oscarsancz.biblioapp.R;
import com.oscarsancz.biblioapp.contracts.DevolverLibroContract;

import butterknife.ButterKnife;

public class DevolverLibroFragment extends Fragment implements DevolverLibroContract.View {
    private DevolverLibroContract.Presenter presenter;

    public DevolverLibroFragment() {

    }

    public View onCreateView(
            LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.devovler_libro_view, container, false);
        getActivity().setTitle(R.string.pantalla_devovler);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setPresenter(DevolverLibroContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
