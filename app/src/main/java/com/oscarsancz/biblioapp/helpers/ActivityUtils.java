package com.oscarsancz.biblioapp.helpers;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class ActivityUtils {

  public static void addFragmentToActivity(
      @NonNull FragmentManager fragmentManager,
      @NonNull Fragment fragment,
      int frameId,
      @NonNull String tag) {

    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.add(frameId, fragment, tag);
    transaction.commit();
  }

  public static void replaceFragment(
      @NonNull FragmentManager fragmentManager,
      @NonNull Fragment fragment,
      int frameId,
      @NonNull String tag) {

    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.replace(frameId, fragment, tag);
    transaction.commit();
  }

  public static <T> void createIntent(
      Context context, Class<T> clase, String tituloExtra, String extra) {
    Intent intent = new Intent(context, clase);
    intent.putExtra(tituloExtra, extra);
    context.startActivity(intent);
  }
}
