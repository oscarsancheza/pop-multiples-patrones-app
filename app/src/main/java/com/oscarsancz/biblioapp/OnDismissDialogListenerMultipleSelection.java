package com.oscarsancz.biblioapp;

import com.oscarsancz.biblioapp.models.SelectionKey;

import java.util.List;

public interface OnDismissDialogListenerMultipleSelection {
  void onItemsSelected(List<SelectionKey> items);
}
