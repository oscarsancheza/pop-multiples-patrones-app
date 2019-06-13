package com.oscarsancz.biblioapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oscarsancz.biblioapp.R;
import com.oscarsancz.biblioapp.contracts.CambioTipoUsuarioContract;
import com.oscarsancz.biblioapp.models.Usuarios.TipoUsuario;
import com.oscarsancz.biblioapp.models.Usuarios.Usuario;

import butterknife.ButterKnife;

public class CambioTipoUsuarioFragment extends Fragment implements CambioTipoUsuarioContract.View {
    private CambioTipoUsuarioContract.Presenter presenter;

    public CambioTipoUsuarioFragment(){}

    public View onCreateView(
            LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cambio_tipo_usuario_view, container, false);
        getActivity().setTitle(R.string.pantalla_cambio);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setPresenter(CambioTipoUsuarioContract.Presenter presenter) {
        this.presenter = presenter;
    }

    private void cambioTipoUsuario(){
        Usuario usuario = new Usuario();
        TipoUsuario nuevoTipo = TipoUsuario.ESTUDIANTE;
        this.presenter.cambioUsuario(usuario, nuevoTipo);
    }
}
