package com.oscarsancz.biblioapp.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.oscarsancz.biblioapp.R;

import java.util.ArrayList;
import java.util.List;

public abstract class DefaultAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  protected Context context;
  protected List<T> listItems;
  protected OnItemClickListener itemClickListener;
  protected RecyclerView.LayoutManager layoutManager;
  protected RecyclerView recyclerView;
  protected RecyclerView.ViewHolder mLastViewHolder;

  public DefaultAdapter(
      Context context, RecyclerView.LayoutManager layoutManager, RecyclerView recyclerView) {
    this.context = context;
    this.layoutManager = layoutManager;
    this.recyclerView = recyclerView;
    listItems = new ArrayList<>();
  }

  public abstract RecyclerView.ViewHolder makeHolder(ViewGroup parent);

  public void addOnItemClickListener(OnItemClickListener itemClickListener) {
    this.itemClickListener = itemClickListener;
  }

  public T getItem(int position) {
    return listItems.get(position);
  }

  public List<T> getItems() {
    return listItems;
  }

  public void addItem(T item) {
    listItems.add(item);
    notifyItemInserted(getItemCount());
  }

  public void updateItem(T item, int position) {
    listItems.set(position, item);
    notifyItemChanged(position);
  }

  public void addItems(T... items) {
    int startPosition = getItemCount();
    for (T item : items) {
      listItems.add(item);
    }
    notifyItemRangeInserted(startPosition, items.length);
    layoutManager.scrollToPosition(0);
  }

  public void removeItem(T model) {
    removeItem(listItems.indexOf(model));
  }

  public void removeItem(int position) {
    listItems.remove(position);
    notifyItemRemoved(position);
  }

  public void removeAll() {
    int count = listItems.size();
    listItems.clear();
    notifyItemRangeRemoved(0, count);
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return makeHolder(parent);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
    BindHolder holder = (BindHolder) viewHolder;
    holder.bind((T) listItems.get(position), itemClickListener);
  }

  @Override
  public int getItemCount() {
    return listItems == null ? 0 : listItems.size();
  }

  public interface OnItemClickListener<T> {
    void onItemClick(int position, T model);

    boolean onItemLongClick(int position, T model);
  }

  private void unmark() {
    if (mLastViewHolder != null) {
      int[] attrs = new int[] {R.attr.selectableItemBackground};
      TypedArray typedArray = context.obtainStyledAttributes(attrs);
      int backgroundResource = typedArray.getResourceId(0, 0);

      mLastViewHolder.itemView.setBackgroundResource(backgroundResource);
      notifyItemChanged(mLastViewHolder.getAdapterPosition());

      typedArray.recycle();
    }
  }

  public interface BindHolder<T> {
    void bind(final T model, final OnItemClickListener listener);
  }
}
