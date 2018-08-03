package gm32.trainingandro_task6.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import gm32.trainingandro_task6.R;
import gm32.trainingandro_task6.model.Income;

public class IncomeAdapter  extends RecyclerView.Adapter<IncomeAdapter.IncomeViewHolder> {

    private List<Income> income;
    private int rowLayout;
    private Context context;

    public IncomeAdapter(List<Income> track, int rowLayout, Context context) {
        this.income = track;
        this.rowLayout = rowLayout;
        this.context = context;

    }


    @Override
    public IncomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new IncomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IncomeViewHolder holder, final int position) {
        //Log.d("Judul",albums.get(position).getName());
        Log.i("Income","adada");

        holder.label.setText(income.get(position).getLabel());
        holder.price.setText("Rp. " +String.valueOf(income.get(position).getBecome()));
    }

    @Override
    public int getItemCount() {
        return income.size();
    }

    public static class IncomeViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout list_Layout;
        TextView label;
        TextView price;

        public IncomeViewHolder(View v) {
            super(v);
            list_Layout = v.findViewById(R.id.list_layout);
            label = v.findViewById(R.id.label);
            price = v.findViewById(R.id.price);
        }
    }
}