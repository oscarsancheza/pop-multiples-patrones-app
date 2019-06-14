package com.oscarsancz.biblioapp.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
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

  @BindView(R.id.estudiante_radio)
  RadioButton estudianteRadio;

  @BindView(R.id.publico_radio)
  RadioButton publicoRadio;

  @BindView(R.id.profesor_radio)
  RadioButton maestroRadio;

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
  public void cambiarTipoUsuario() {
    if (usuario != null && tipoUsuario != null) {
      this.presenter.cambioUsuario(usuario, tipoUsuario);
      new AlertDialog.Builder(getContext())
          .setTitle("Atención")
          .setPositiveButton("Aceptar", (dialogInterface, i) -> getActivity().finish())
          .setMessage("Se realizó el cambio a " + usuario.getTipo().toString() + " con exito.")
          .setCancelable(false)
          .show();
    } else {
      mostrarMensaje("Es necesario seleccionar los campos usuario y tipo usuario.");
    }
  }

  private void mostrarMensaje(String mensaje) {
    new AlertDialog.Builder(getContext())
        .setTitle("Atención")
        .setPositiveButton("Aceptar", (dialogInterface, i) -> dialogInterface.dismiss())
        .setMessage(mensaje)
        .show();
  }

  @OnCheckedChanged({R.id.estudiante_radio, R.id.publico_radio, R.id.profesor_radio})
  public void onRadioButtonCheckChanged(CompoundButton button, boolean checked) {
    if (checked) {
      switch (button.getId()) {
        case R.id.profesor_radio:
          tipoUsuario = TipoUsuario.PROFESOR;
          break;
        case R.id.publico_radio:
          tipoUsuario = TipoUsuario.PUBLICO_GENERAL;
          break;
        case R.id.estudiante_radio:
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

          switch (usuario.getTipo()) {
            case PROFESOR:
              maestroRadio.setEnabled(false);
              maestroRadio.setSelected(false);
              maestroRadio.setChecked(false);
              estudianteRadio.setEnabled(false);
              estudianteRadio.setSelected(false);
              estudianteRadio.setChecked(false);
              break;
            case ESTUDIANTE:
              estudianteRadio.setEnabled(false);
              estudianteRadio.setSelected(false);
              estudianteRadio.setChecked(false);
              break;
            case PUBLICO_GENERAL:
              publicoRadio.setEnabled(false);
              publicoRadio.setSelected(false);
              publicoRadio.setChecked(false);
              break;
          }
          tipoUsuario = null;
          usuarioComponent.setText(usuario.getNombreCompleto());
        });
    dialog.show(getActivity().getFragmentManager(), "usuarioDialog");
  }

  @Override
  public void setPresenter(CambioTipoUsuarioContract.Presenter presenter) {
    this.presenter = presenter;
  }
}
