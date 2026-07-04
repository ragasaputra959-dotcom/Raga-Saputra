package com.example.siakad_ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.siakad_ui.R;
import com.example.siakad_ui.adapters.MenuAdapter;
import com.example.siakad_ui.helper.SessionManager;
import com.example.siakad_ui.models.MenuItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends Fragment {

    private SessionManager sessionManager;
    private TextView tvName, tvNim;
    private ImageView ivProfile, ivBanner;
    private RecyclerView rvMenu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        sessionManager = new SessionManager(requireContext());
        HashMap<String, String> user = sessionManager.getUserDetails();

        tvName = view.findViewById(R.id.tvName);
        tvNim = view.findViewById(R.id.tvNim);
        ivProfile = view.findViewById(R.id.ivProfile);
        ivBanner = view.findViewById(R.id.ivBanner);
        rvMenu = view.findViewById(R.id.rvMenu);

        // Update sesuai permintaan user
        tvName.setText("Raga Saputra");
        tvNim.setText("230201027");
        
        Glide.with(this)
                .load(user.get(SessionManager.KEY_FOTO))
                .placeholder(R.drawable.ic_launcher_background)
                .into(ivProfile);

        // Banner Slider (Static Demo)
        Glide.with(this)
                .load("https://via.placeholder.com/800x400/1A73E8/FFFFFF?text=Welcome+to+SIAKAD")
                .into(ivBanner);

        setupMenu();

        return view;
    }

    private void setupMenu() {
        List<MenuItem> menuList = new ArrayList<>();
        menuList.add(new MenuItem("Jadwal", android.R.drawable.ic_menu_month, R.id.navigation_jadwal));
        menuList.add(new MenuItem("KRS", android.R.drawable.ic_menu_edit, R.id.navigation_krs));
        menuList.add(new MenuItem("KHS", android.R.drawable.ic_menu_sort_by_size, R.id.navigation_khs));
        menuList.add(new MenuItem("Informasi", android.R.drawable.ic_menu_info_details, R.id.navigation_info));
        menuList.add(new MenuItem("Pengumuman", android.R.drawable.ic_menu_agenda, R.id.navigation_pengumuman));
        menuList.add(new MenuItem("Dosen", android.R.drawable.ic_menu_myplaces, R.id.navigation_dosen));
        menuList.add(new MenuItem("Profile", android.R.drawable.ic_menu_gallery, R.id.navigation_profile));
        menuList.add(new MenuItem("Logout", android.R.drawable.ic_menu_close_clear_cancel, -1));

        MenuAdapter adapter = new MenuAdapter(menuList, item -> {
            if (item.getActionId() != -1) {
                Navigation.findNavController(requireView()).navigate(item.getActionId());
            } else {
                sessionManager.logoutUser();
                requireActivity().finish();
            }
        });

        rvMenu.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvMenu.setAdapter(adapter);
    }
}
