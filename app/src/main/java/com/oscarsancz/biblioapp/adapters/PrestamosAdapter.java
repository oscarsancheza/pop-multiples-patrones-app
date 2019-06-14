package com.oscarsancz.biblioapp.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oscarsancz.biblioapp.R;
import com.oscarsancz.biblioapp.models.Libro.Libro;
import com.oscarsancz.biblioapp.models.Usuarios.Usuario;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class PrestamosAdapter
    extends RealmRecyclerViewAdapter<Usuario, PrestamosAdapter.PrestamoHolder> {

  private Context context;
  private OrderedRealmCollection<Usuario> data;

  public PrestamosAdapter(Activity activity, OrderedRealmCollection<Usuario> data) {
    super(data, true, true);
    this.context = activity;
    this.data = data;
  }

  @Override
  public PrestamoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView =
        LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_prestamo_view, parent, false);
    return new PrestamoHolder(itemView);
  }

  @Override
  public void onBindViewHolder(PrestamoHolder holder, int position) {
    Usuario obj = getData().get(position);
    holder.nombrePersona.setText(obj.getNombreCompleto());
    holder.tipoPersona.setText(obj.getTipo().toString());
    holder.totalLibros.setText("Total Libros Prestados:" + obj.getLibros().size());
    holder.data = obj;
  }

  class PrestamoHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.nombre_persona)
    TextView nombrePersona;

    @BindView(R.id.tipo_persona)
    TextView tipoPersona;

    @BindView(R.id.total_libros)
    TextView totalLibros;

    Usuario data;

    public PrestamoHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
      view.setOnLongClickListener(
          view1 -> {
            StringBuilder libros = new StringBuilder();
            if (data != null && data.getLibros() != null) {
              int x = 1;
              for (Libro item : data.getLibros()) {
                libros.append(x + ". " + item.getTitulo());
                libros.append("\n");
                x++;
              }
            }

            new AlertDialog.Builder(view1.getContext())
                .setTitle("Atención")
                .setPositiveButton("Aceptar", (dialogInterface, i) -> dialogInterface.dismiss())
                .setMessage(libros.toString())
                .setCancelable(false)
                .show();

            return false;
          });
    }
  }
}
