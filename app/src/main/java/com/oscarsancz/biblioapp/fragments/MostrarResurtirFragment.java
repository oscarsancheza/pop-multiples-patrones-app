package com.oscarsancz.biblioapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oscarsancz.biblioapp.R;
import com.oscarsancz.biblioapp.contracts.MostrarResurtirContract;

import butterknife.ButterKnife;

public class MostrarResurtirFragment extends Fragment implements MostrarResurtirContract.View {
    private MostrarResurtirContract.Presenter presenter;

    public MostrarResurtirFragment(){

    }

    public View onCreateView(
            LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mostrar_resurtir_view, container, false);
        getActivity().setTitle(R.string.pantalla_resurtir);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setPresenter(MostrarResurtirContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
