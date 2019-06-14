package com.oscarsancz.biblioapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oscarsancz.biblioapp.R;
import com.oscarsancz.biblioapp.holders.LibroHolder;
import com.oscarsancz.biblioapp.models.Libro.Libro;

public class LibroAdapter extends DefaultAdapter<Libro> {

  public LibroAdapter(
      Context context, RecyclerView.LayoutManager layoutManager, RecyclerView recyclerView) {
    super(context, layoutManager, recyclerView);
  }

  @Override
  public RecyclerView.ViewHolder makeHolder(ViewGroup parent) {
    View itemView = LayoutInflater.from(context).inflate(R.layout.libro_item, parent, false);
    return new LibroHolder(context, itemView);
  }
}
