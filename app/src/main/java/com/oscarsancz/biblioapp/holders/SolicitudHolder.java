package com.oscarsancz.biblioapp.holders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.oscarsancz.biblioapp.R;
import com.oscarsancz.biblioapp.adapters.DefaultAdapter;
import com.oscarsancz.biblioapp.models.Solicitud.Solicitud;
import com.oscarsancz.biblioapp.models.Usuarios.Usuario;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SolicitudHolder extends RecyclerView.ViewHolder implements DefaultAdapter.BindHolder<Solicitud> {
    private Context context;
    private View itemView;
    private Solicitud model;

    @BindView(R.id.titulo_libro)
    TextView tituloLibro;

    @BindView(R.id.isbn)
    TextView isbn;

    @BindView(R.id.fecha)
    TextView fecha;

    public SolicitudHolder(Context context, @NonNull View itemView) {
        super(itemView);
        this.context = context;
        this.itemView = itemView;
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void bind(Solicitud model, DefaultAdapter.OnItemClickListener listener) {
        this.model = model;
        tituloLibro.setText(model.getTitulo());
        isbn.setText(model.getIsbn().toString());
        fecha.setText(model.getFecha().toString());
        itemView.setOnClickListener(
                v -> listener.onItemClick(SolicitudHolder.this.getAdapterPosition(), model));

        itemView.setOnLongClickListener(
                v -> listener.onItemLongClick(SolicitudHolder.this.getAdapterPosition(), model));

    }
}
