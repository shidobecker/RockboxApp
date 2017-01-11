package br.com.rockbox.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.rockbox.R;

/**
 * Classe responsavel por listar as bandas que o usuário gosta como bandas dele para que depois venham informações das mesmas
 */
public class BandListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_listbands, container, false);
        return v;
    }
}
