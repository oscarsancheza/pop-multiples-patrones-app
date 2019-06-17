package com.oscarsancz.biblioapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oscarsancz.biblioapp.R;
import com.oscarsancz.biblioapp.holders.SolicitudHolder;
import com.oscarsancz.biblioapp.models.Solicitud.Solicitud;

public class SolicitudesAdapter extends DefaultAdapter<Solicitud> {
    public SolicitudesAdapter(
            Context context, RecyclerView.LayoutManager layoutManager, RecyclerView recyclerView) {
        super(context, layoutManager, recyclerView);
    }

    @Override
    public RecyclerView.ViewHolder makeHolder(ViewGroup parent) {
        View itemView =
                LayoutInflater.from(context).inflate(R.layout.item_solicitud_view, parent, false);
        return new SolicitudHolder(context, itemView);
    }
}
