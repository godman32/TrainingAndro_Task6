package gm32.trainingandro_task6.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import gm32.trainingandro_task6.R;
import gm32.trainingandro_task6.model.Expenses;
import gm32.trainingandro_task6.model.Income;

public class ExpenseAdapter  extends RecyclerView.Adapter<ExpenseAdapter.ExpensesViewHolder> {

    private List<Expenses> expense;
    private int rowLayout;
    private Context context;

    public ExpenseAdapter(List<Expenses> track, int rowLayout, Context context) {
        this.expense = track;
        this.rowLayout = rowLayout;
        this.context = context;
    }


    @Override
    public ExpensesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ExpensesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExpensesViewHolder holder, final int position) {
        //Log.d("Judul",albums.get(position).getName());
        holder.label.setText(expense.get(position).getLabel());
        holder.price.setText("Rp. "+String.valueOf(expense.get(position).getBecome()));
    }

    @Override
    public int getItemCount() {
        return expense.size();
    }

    public static class ExpensesViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout list_Layout;
        TextView label;
        TextView price;

        public ExpensesViewHolder(View v) {
            super(v);
            list_Layout = v.findViewById(R.id.list_layout);
            label = v.findViewById(R.id.label);
            price = v.findViewById(R.id.price);
        }
    }
}
