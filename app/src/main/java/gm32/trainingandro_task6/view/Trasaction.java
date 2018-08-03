package gm32.trainingandro_task6.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import gm32.trainingandro_task6.database.DatabaseHelper;
import gm32.trainingandro_task6.R;


public class Trasaction extends Fragment {

    DatabaseHelper myDB;
    EditText label_Income, label_Expenses, become_Income, become_Expenses;
    ImageView addIncome, addExpense;

    public Trasaction() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_trasaction, container, false);

        // Inflate the layout for this fragment
        myDB= new DatabaseHelper(getContext());

        label_Income= view.findViewById(R.id.label_Income);
        label_Expenses= view.findViewById(R.id.label_Expenses);
        become_Income = view.findViewById(R.id.newBecomeIncome);
        become_Expenses= view.findViewById(R.id.newBecomeExpense);
        addIncome= view.findViewById(R.id.but_AddIncome);
        addExpense= view.findViewById(R.id.but_AddExpense);

        addIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // cek if income null
                if(!label_Income.getText().toString().trim().equals("") ||
                        become_Income.getText().toString().trim().equals("")){

                    // add income to database
                    myDB.add_Income(label_Income.getText().toString().trim(),
                            Double.valueOf(become_Income.getText().toString().trim()));

                    Toast.makeText(getContext(), "Succes Income", Toast.LENGTH_SHORT).show();
                    Log.i("Income","Succes");

                    label_Income.setText("");
                    become_Income.setText("");
                }
                else{
                    Toast.makeText(getContext(), "Silahkan Isi Field Dengan Benar...!!!", Toast.LENGTH_SHORT).show();
                    Log.i("Income","Field kosong");
                }
            }
        });

        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!label_Expenses.getText().toString().trim().equals("") ||
                        become_Expenses.getText().toString().trim().equals("")){
                    myDB.add_Expenses(label_Expenses.getText().toString().trim(),
                            Double.valueOf(become_Expenses.getText().toString().trim()));

                    Toast.makeText(getContext(), "Succes Expense", Toast.LENGTH_SHORT).show();
                    Log.i("Expense","Succes");

                    label_Expenses.setText("");
                    become_Expenses.setText("");
                }
                else{
                    Toast.makeText(getContext(), "Silahkan Isi Field Dengan Benar...!!!", Toast.LENGTH_SHORT).show();
                    Log.i("Expense","Field kosong");
                }
            }
        });


        return view;
    }

//    public void addIncome(View view){
//
//
//    }

//    public void addExpense(View view){
//
//    }
}
