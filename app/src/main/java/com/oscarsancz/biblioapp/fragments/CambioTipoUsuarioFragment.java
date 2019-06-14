package com.oscarsancz.biblioapp.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.oscarsancz.biblioapp.R;
import com.oscarsancz.biblioapp.contracts.CambioTipoUsuarioContract;
import com.oscarsancz.biblioapp.models.Usuarios.TipoUsuario;
import com.oscarsancz.biblioapp.models.Usuarios.Usuario;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class CambioTipoUsuarioFragment extends Fragment implements CambioTipoUsuarioContract.View {
  private CambioTipoUsuarioContract.Presenter presenter;
  private Usuario usuario;
  private TipoUsuario tipoUsuario;

  @BindView(R.id.cambiar_tipo_btn)
  Button cambiarBtn;

  @BindView(R.id.usuario_component)
  EditText usuarioComponent;

  @BindView(R.id.radio_group)
  RadioGroup radioGroup;

  @SuppressLint("ValidFragment")
  public CambioTipoUsuarioFragment() {}

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.cambio_tipo_usuario_view, container, false);
    getActivity().setTitle(R.string.pantalla_cambio);
    ButterKnife.bind(this, view);

    usuarioComponent.setFocusable(false);
    usuarioComponent.setOnFocusChangeListener(
        (v, hasFocus) -> {
          if (v.isFocused()) {
            usuarioComponent();
          }
        });

    return view;
  }

  @OnClick(R.id.cambiar_tipo_btn)
  public void cambiarTipoUsuario() {}

  @OnCheckedChanged({R.id.estudiante_check, R.id.publico_check, R.id.maestro_check})
  public void onRadioButtonCheckChanged(CompoundButton button, boolean checked) {
    if (checked) {
      switch (button.getId()) {
        case R.id.maestro_check:
          tipoUsuario = TipoUsuario.PROFESOR;
          break;
        case R.id.publico_check:
          tipoUsuario = TipoUsuario.PUBLICO_GENERAL;
          break;
        case R.id.estudiante_check:
          tipoUsuario = TipoUsuario.ESTUDIANTE;
          break;
      }
    }
  }

  @OnClick(R.id.usuario_component)
  public void usuarioComponent() {
    List<Usuario> usuarios = presenter.getUsuarios();
    UsuarioDialogFragment dialog = new UsuarioDialogFragment();
    dialog.setItems(
        getContext(),
        usuarios,
        usuario -> {
          this.usuario = usuario;
          usuarioComponent.setText(usuario.getNombreCompleto());
        });
    dialog.show(getActivity().getFragmentManager(), "usuarioDialog");
  }

  @Override
  public void setPresenter(CambioTipoUsuarioContract.Presenter presenter) {
    this.presenter = presenter;
  }

  private void cambioTipoUsuario() {
    Usuario usuario = new Usuario();
    TipoUsuario nuevoTipo = TipoUsuario.ESTUDIANTE;
    this.presenter.cambioUsuario(usuario, nuevoTipo);
  }
}
