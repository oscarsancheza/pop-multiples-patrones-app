package com.oscarsancz.biblioapp.fragments;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;

import com.oscarsancz.biblioapp.R;
import com.oscarsancz.biblioapp.models.Usuarios.Usuario;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UsuarioDialogFragment extends DialogFragment {
  @BindView(R.id.contenedorLista)
  ListView mLista;

  @BindView(R.id.buscador)
  SearchView mSearchView;

  private Context mContext;
  private OnDismissDialogListener mListener;
  private List<Usuario> mItems;
  private SingleChoiceAdapter mAdapter;

  public UsuarioDialogFragment() {}

  public void setItems(Context context, List<Usuario> items, OnDismissDialogListener listener) {
    mContext = context;
    mListener = listener;
    mItems = items;
  }

  public void onSuccess(Usuario usuario) {
    mListener.onItemSelected(usuario);
    dismiss();
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.single_choice_dialog_view, null);
    ButterKnife.bind(this, rootView);
    initListView();

    mSearchView.setOnQueryTextListener(
        new SearchView.OnQueryTextListener() {
          @Override
          public boolean onQueryTextSubmit(String txt) {
            return false;
          }

          @Override
          public boolean onQueryTextChange(String txt) {
            mAdapter.getFilter().filter(txt);
            return false;
          }
        });

    return rootView;
  }

  private void initListView() {
    mLista.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    mAdapter = new SingleChoiceAdapter(mContext, mItems);
    mLista.setAdapter(mAdapter);
  }

  public class SingleChoiceAdapter extends BaseAdapter implements Filterable {

    List<Usuario> arrayList;
    List<Usuario> mOriginalValues;
    LayoutInflater inflater;

    public SingleChoiceAdapter(Context context, List<Usuario> arrayList) {
      this.arrayList = arrayList;
      inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
      return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
      return position;
    }

    @Override
    public long getItemId(int position) {
      return position;
    }

    private class ViewHolder {
      TextView textView;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
      ViewHolder holder = new ViewHolder();
      convertView = inflater.inflate(R.layout.item_single_choice, parent, false);
      holder.textView = (TextView) convertView.findViewById(R.id.itemTxt);
      convertView.setTag(holder);

      final Usuario data = arrayList.get(position);

      holder.textView.setText(data.getNombreCompleto());

      convertView.setOnClickListener(
          v -> {
            int longitudAdapter = arrayList.size();
            int longitud = mItems.size();

            if (longitudAdapter != longitud) {
              for (int i = 0; i < longitud; i++) {
                mItems.get(i).setSelected(false);
              }
            }
            Usuario colonia = null;
            for (int i = 0; i < longitudAdapter; i++) {
              arrayList.get(i).setSelected(false);
              if (i == position) {
                arrayList.get(i).setSelected(true);
                colonia = arrayList.get(i);
              }
            }
            UsuarioDialogFragment.this.onSuccess(colonia);
          });

      if (data.isSelected()) {
        convertView.setBackgroundColor(
            ContextCompat.getColor(mContext, R.color.colorBackgroundLight));
      }
      return convertView;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public Filter getFilter() {
      return new Filter() {

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

          arrayList = (List<Usuario>) results.values;
          notifyDataSetChanged();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
          FilterResults results = new FilterResults();
          List<Usuario> FilteredArrList = new ArrayList<>();

          if (mOriginalValues == null) {
            mOriginalValues = new ArrayList<>(arrayList);
          }

          if (constraint == null || constraint.length() == 0) {
            results.count = mOriginalValues.size();
            results.values = mOriginalValues;
          } else {
            constraint = constraint.toString().toLowerCase();
            for (int i = 0; i < mOriginalValues.size(); i++) {
              String data = mOriginalValues.get(i).getNombre();
              if (data.toLowerCase().contains(constraint.toString())) {
                FilteredArrList.add(mOriginalValues.get(i));
              }
            }
            results.count = FilteredArrList.size();
            results.values = FilteredArrList;
          }
          return results;
        }
      };
    }
  }

  public interface OnDismissDialogListener {
    void onItemSelected(Usuario usuario);
  }
}
