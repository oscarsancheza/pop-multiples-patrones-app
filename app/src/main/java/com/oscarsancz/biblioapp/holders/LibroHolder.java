package com.oscarsancz.biblioapp.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.oscarsancz.biblioapp.R;
import com.oscarsancz.biblioapp.adapters.DefaultAdapter;
import com.oscarsancz.biblioapp.models.Libro.Libro;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LibroHolder extends RecyclerView.ViewHolder
    implements DefaultAdapter.BindHolder<Libro> {

  private Context context;
  private View itemView;
  private Libro model;

  @BindView(R.id.nombre_libro)
  TextView nombreLibro;

  @BindView(R.id.estado_libro)
  TextView estadoLibro;

  @BindView(R.id.id_libro)
  TextView idLibro;

  public LibroHolder(Context context, View itemView) {
    super(itemView);
    this.context = context;
    this.itemView = itemView;
    ButterKnife.bind(this, itemView);
  }

  @Override
  public void bind(final Libro model, final DefaultAdapter.OnItemClickListener listener) {
    this.model = model;

    nombreLibro.setText(model.getTitulo());
    idLibro.setText(""+ model.getId());
    estadoLibro.setText(model.getEstado().toString());

    itemView.setOnClickListener(
        v -> listener.onItemClick(LibroHolder.this.getAdapterPosition(), model));

    itemView.setOnLongClickListener(
        v -> listener.onItemLongClick(LibroHolder.this.getAdapterPosition(), model));
  }
}
