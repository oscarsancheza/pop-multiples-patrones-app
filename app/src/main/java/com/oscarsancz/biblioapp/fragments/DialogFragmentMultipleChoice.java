package com.oscarsancz.biblioapp.fragments;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;

import com.oscarsancz.biblioapp.OnDismissDialogListenerMultipleSelection;
import com.oscarsancz.biblioapp.R;
import com.oscarsancz.biblioapp.models.SelectionKey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogFragmentMultipleChoice extends DialogFragment {

  @BindView(R.id.contenedorLista)
  ListView mLista;

  @BindView(R.id.searchViewDialog)
  SearchView mSearchView;

  private List<SelectionKey> mItems;
  private OnDismissDialogListenerMultipleSelection mListener;
  private ListViewAdapter mAdapter;
  private boolean isComercio;
  private static final String ABARROTE = "ABARROTE";

  public DialogFragmentMultipleChoice() {}

  public void setItems(
      List<SelectionKey> items,
      OnDismissDialogListenerMultipleSelection listener,
      boolean isComercio) {

    mListener = listener;
    mItems = items;
    this.isComercio = isComercio;
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.dialog_libros, null);
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
            if (mAdapter != null) {
              mAdapter.getFilter().filter(txt);
            }
            return false;
          }
        });

    return rootView;
  }

  private void initListView() {
    if (getActivity() != null && mItems != null) {
      mLista.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
      mAdapter = new ListViewAdapter(mItems, isComercio);
      mLista.setAdapter(mAdapter);
    }
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    mLista.setAdapter(null);
  }

  @Override
  public void onCancel(DialogInterface dialog) {
    mListener.onItemsSelected(getSelectedItems());
  }

  public List<SelectionKey> getSelectedItems() {
    List<SelectionKey> selectedItems = new ArrayList<>();
    for (SelectionKey item : mItems) {
      if (item.isSelected()) {
        selectedItems.add(item);
      }
    }
    return selectedItems;
  }

  @OnClick(R.id.btnAceptar)
  public void dismissDialog() {
    mListener.onItemsSelected(getSelectedItems());
    dismiss();
  }

  public class ListViewAdapter extends BaseAdapter implements Filterable {

    List<SelectionKey> arrayList;
    List<SelectionKey> mOriginalValues;
    List<SelectionKey> selectedArray;
    boolean isComercio;

    LayoutInflater inflater;

    ListViewAdapter(List<SelectionKey> arrayList, boolean isComercio) {
      this.arrayList = arrayList;
      this.isComercio = isComercio;
      inflater = LayoutInflater.from(getActivity());
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
      CheckBox checkBox;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
      ViewHolder holder;

      if (selectedArray == null) {
        selectedArray = new ArrayList<>(arrayList);
        selectedArray.removeAll(arrayList);
      }

      if (convertView == null) {
        holder = new ViewHolder();
        convertView = inflater.inflate(R.layout.item_listview_multiple, parent, false);
        holder.textView = (TextView) convertView.findViewById(R.id.alertTextView);
        holder.checkBox = (CheckBox) convertView.findViewById(R.id.alertCheckbox);

        convertView.setTag(holder);
      } else {
        holder = (ViewHolder) convertView.getTag();
      }
      if (position < arrayList.size()) {
        final SelectionKey data = arrayList.get(position);

        holder.textView.setText(data.getDescripcion().trim());
        holder.checkBox.setChecked(data.isSelected());
        if (isComercio && data.getDescripcion().equals(ABARROTE)) {
          holder.checkBox.setChecked(true);
          data.setSelected(true);
        }
        convertView.setOnClickListener(
            v -> {
              final ViewHolder temp = (ViewHolder) v.getTag();
              temp.checkBox.setChecked(!temp.checkBox.isChecked());
              data.setSelected(!data.isSelected());

              if (isComercio && data.getDescripcion().equals(ABARROTE)) {
                temp.checkBox.setChecked(true);
                data.setSelected(true);
              }

              notifyDataSetChanged();
            });
        holder.checkBox.setTag(holder);
      }

      return convertView;
    }

    private void orderSelected(SelectionKey data) {
      arrayList.remove(data);

      if (!selectedArray.contains(data)) {
        if (selectedArray.isEmpty()) {
          arrayList.add(0, data);
          selectedArray.add(data);
        } else {
          selectedArray.add(data);
          for (ListIterator<SelectionKey> iter = arrayList.listIterator(); iter.hasNext(); ) {
            SelectionKey a = iter.next();
            if (a.isSelected()) {
              iter.remove();
            }
          }
          arrayList.addAll(0, selectedArray);
          Collections.sort(
              arrayList.subList(0, selectedArray.size() - 1),
              (o1, o2) ->
                  o1.getDescripcion()
                      .replaceAll("\\s+", "")
                      .compareToIgnoreCase(o2.getDescripcion().replaceAll("\\s+", "")));
        }
      }
    }

    private void orderUnselect(SelectionKey data) {
      selectedArray.remove(data);
      arrayList.remove(data);

      arrayList.add(data);
      Collections.sort(
          arrayList.subList(selectedArray.size(), arrayList.size()),
          (o1, o2) ->
              o1.getDescripcion()
                  .replaceAll("\\s+", "")
                  .compareToIgnoreCase(o2.getDescripcion().replaceAll("\\s+", "")));
    }

    @SuppressLint("DefaultLocale")
    @Override
    public Filter getFilter() {
      return new Filter() {

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
          arrayList = (List<SelectionKey>) results.values;
          notifyDataSetChanged();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
          FilterResults results = new FilterResults();
          List<SelectionKey> FilteredArrList = new ArrayList<>();

          if (mOriginalValues == null) {
            mOriginalValues = new ArrayList<>(arrayList);
          }

          if (constraint == null || constraint.length() == 0) {
            results.count = mOriginalValues.size();
            results.values = mOriginalValues;
          } else {
            constraint = constraint.toString().toLowerCase().replaceAll("\\s+", "");
            for (int i = 0; i < mOriginalValues.size(); i++) {
              String data = mOriginalValues.get(i).getDescripcion();
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
}
