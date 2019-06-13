package com.oscarsancz.biblioapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oscarsancz.biblioapp.R;
import com.oscarsancz.biblioapp.contracts.PrestamoLibroContract;

import butterknife.ButterKnife;

public class PrestamoLibroFragment extends Fragment implements PrestamoLibroContract.View {
    private PrestamoLibroContract.Presenter presenter;

    public View onCreateView(
            LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.prestamo_libro_view, container, false);
        getActivity().setTitle(R.string.pantalla_prestamo);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void setPresenter(PrestamoLibroContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
