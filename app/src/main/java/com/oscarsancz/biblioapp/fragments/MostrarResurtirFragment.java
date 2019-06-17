package com.oscarsancz.biblioapp.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oscarsancz.biblioapp.R;
import com.oscarsancz.biblioapp.adapters.DefaultAdapter;
import com.oscarsancz.biblioapp.adapters.PrestamosAdapter;
import com.oscarsancz.biblioapp.adapters.SolicitudesAdapter;
import com.oscarsancz.biblioapp.contracts.ListadoPrestamoContract;
import com.oscarsancz.biblioapp.contracts.MostrarResurtirContract;
import com.oscarsancz.biblioapp.helpers.SimpleDividerItemDecoration;
import com.oscarsancz.biblioapp.models.Solicitud.Solicitud;
import com.oscarsancz.biblioapp.models.Usuarios.Usuario;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MostrarResurtirFragment extends Fragment implements MostrarResurtirContract.View {
    @BindView(R.id.contenedor_solicitud)
    RecyclerView recyclerView;

    @BindView(R.id.empty_view)
    TextView textoEnVacio;


    private MostrarResurtirContract.Presenter presenter;
    private RecyclerView.LayoutManager layoutManager;
    private SolicitudesAdapter adapter;

    @SuppressLint("ValidFragment")
    public MostrarResurtirFragment() {}

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mostrar_resurtir_view, container, false);
        getActivity().setTitle(R.string.pantalla_resurtir);
        ButterKnife.bind(this, view);

        initRecyclerView();
        mostrarDatos();

        return view;
    }

    private void mostrarDatos() {
        List<Solicitud> solicituds = presenter.getData();
        if (solicituds == null || solicituds.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            textoEnVacio.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            textoEnVacio.setVisibility(View.GONE);
            adapter.addItems(solicituds);
        }
    }

    private void initRecyclerView() {
        layoutManager = new LinearLayoutManager(getContext());
        adapter = new SolicitudesAdapter(getContext(), layoutManager, recyclerView);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));

        adapter.addOnItemClickListener(
                new DefaultAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, Object model) {}

                    @Override
                    public boolean onItemLongClick(int position, Object model) {
                        return false;
                    }
                });
    }

    @Override
    public void setPresenter(MostrarResurtirContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
