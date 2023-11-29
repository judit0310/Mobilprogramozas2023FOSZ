package hu.uni.miskolc.mobilprogramozas2023fosz.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hu.uni.miskolc.mobilprogramozas2023fosz.R;
import hu.uni.miskolc.mobilprogramozas2023fosz.service.DolgozoDTO;

public class DolgozoAdapter extends RecyclerView.Adapter<DolgozoViewHolder> {
    private List<DolgozoDTO> dolgozok;
    private DolgozoKattintas listener;

    public DolgozoAdapter(List<DolgozoDTO> dolgozok) {
        this.dolgozok = dolgozok;
    }

    public void setDolgozok(List<DolgozoDTO> dolgozok) {
        this.dolgozok = dolgozok;
    }

    @NonNull
    @Override
    public DolgozoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout layout = (LinearLayout) LayoutInflater.from(parent.getContext()).
                inflate(R.layout.dolgozo_lista_elem, parent, false);
        DolgozoViewHolder vh = new DolgozoViewHolder(layout, listener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull DolgozoViewHolder holder, int position) {
        DolgozoDTO dolgozo = dolgozok.get(position);
        holder.vezetekNev.setText(dolgozo.getVezetekNev());
        holder.keresztNev.setText(dolgozo.getKeresztNev());
        holder.id.setText(String.valueOf(dolgozo.getId()));
    }

    @Override
    public int getItemCount() {
        return dolgozok.size();
    }

    public void setListener(DolgozoKattintas listener) {
        this.listener = listener;
    }
}
