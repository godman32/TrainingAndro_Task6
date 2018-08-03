package gm32.trainingandro_task6.view;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gm32.trainingandro_task6.database.DatabaseHelper;
import gm32.trainingandro_task6.R;
import gm32.trainingandro_task6.model.Expenses;
import gm32.trainingandro_task6.model.Income;
import gm32.trainingandro_task6.view.adapter.ExpenseAdapter;
import gm32.trainingandro_task6.view.adapter.IncomeAdapter;


public class Homepage extends Fragment {

    DatabaseHelper myDB;
    RecyclerView recyclerViewExpense, recyclerViewIncome;
    View view;
    TextView income_total, expense_total, balance;
    Double valueBalance;

    public Homepage() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_homepage, container, false);

        // Inflate the layout for this fragment
        myDB= new DatabaseHelper(getContext());
        recyclerViewExpense= view.findViewById(R.id.list_item_expense);
        recyclerViewExpense.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerViewIncome= view.findViewById(R.id.list_item_income);
        recyclerViewIncome.setLayoutManager(new LinearLayoutManager(view.getContext()));
        income_total= view.findViewById(R.id.income_total);
        expense_total= view.findViewById(R.id.expense_total);
        balance= view.findViewById(R.id.balance);

        valueBalance= 0d;

        setIncomeViewList();
        setExpenseViewList();


        return view;
    }

    public void setExpenseViewList(){

        Cursor exp = myDB.get_Expenses();
        if(exp.getCount() == 0){
            Toast.makeText(getContext(), "No DATA...!!!", Toast.LENGTH_SHORT).show();
            Log.i("Expense","No DATA");
        }
        else{
            List<Expenses> expense= new ArrayList<>();
            Double total= 0d;
            while(exp.moveToNext()){
                Expenses temp= new Expenses(exp.getString(1), exp.getDouble(2));
                expense.add(temp);
                total= total+exp.getDouble(2);
            }

            recyclerViewExpense.setAdapter(new ExpenseAdapter(expense, R.layout.item_list, getContext()));
            expense_total.setText("Rp. "+String.valueOf(total));

            valueBalance= valueBalance-total;
            Log.i("Expense","View Succes");
        }

        balance.setText("Balance = Rp. "+String.valueOf(valueBalance));
    }

    public void setIncomeViewList(){
        Cursor inc = myDB.get_Income();
        if(inc.getCount() == 0){
            Toast.makeText(getContext(), "No DATA...!!!", Toast.LENGTH_SHORT).show();
            Log.i("Income","No DATA");
        }
        else{
            List<Income> income= new ArrayList<>();
            Double total= 0d;
            while(inc.moveToNext()){
                Income temp= new Income(inc.getString(1), inc.getDouble(2));
                income.add(temp);
                total= total+inc.getDouble(2);
            }

            IncomeAdapter adapter= new IncomeAdapter(income, R.layout.item_list, view.getContext());
            recyclerViewIncome.setAdapter(adapter);

            income_total.setText("Rp. "+String.valueOf(total));
            valueBalance= valueBalance+total;
            Log.i("Income","View Succes");
        }
    }
}
