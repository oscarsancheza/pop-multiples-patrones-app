package com.oscarsancz.biblioapp.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.oscarsancz.biblioapp.R;
import com.oscarsancz.biblioapp.adapters.DefaultAdapter;
import com.oscarsancz.biblioapp.models.Usuarios.Usuario;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PrestamoHolder extends RecyclerView.ViewHolder
    implements DefaultAdapter.BindHolder<Usuario> {

  private Context context;
  private View itemView;
  private Usuario model;

  @BindView(R.id.nombre_persona)
  TextView nombrePersona;

  @BindView(R.id.tipo_persona)
  TextView tipoPersona;

  @BindView(R.id.total_libros)
  TextView totalLibros;

  public PrestamoHolder(Context context, View itemView) {
    super(itemView);
    this.context = context;
    this.itemView = itemView;
    ButterKnife.bind(this, itemView);
  }

  @Override
  public void bind(final Usuario model, final DefaultAdapter.OnItemClickListener listener) {
    this.model = model;

    nombrePersona.setText(model.getNombreCompleto());
    tipoPersona.setText(model.getTipo().toString());
    totalLibros.setText("Total Libros Prestados:" + model.getLibros().size());

    itemView.setOnClickListener(
        v -> listener.onItemClick(PrestamoHolder.this.getAdapterPosition(), model));

    itemView.setOnLongClickListener(
        v -> listener.onItemLongClick(PrestamoHolder.this.getAdapterPosition(), model));
  }
}
