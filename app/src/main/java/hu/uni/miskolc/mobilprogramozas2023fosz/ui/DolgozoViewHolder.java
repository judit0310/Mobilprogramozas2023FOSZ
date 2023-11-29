package hu.uni.miskolc.mobilprogramozas2023fosz.ui;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import hu.uni.miskolc.mobilprogramozas2023fosz.R;

public class DolgozoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView id;
    TextView keresztNev;
    TextView vezetekNev;

    DolgozoKattintas listener;

    public DolgozoViewHolder(@NonNull LinearLayout v, DolgozoKattintas listener) {
        super(v);
        id = v.findViewById(R.id.idMezo);
        keresztNev = v.findViewById(R.id.keresztNevMezo);
        vezetekNev = v.findViewById(R.id.vezetekNevMezo);
        this.listener = listener;
        v.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        listener.onDolgozoClick(getAdapterPosition(), view);
    }




}
