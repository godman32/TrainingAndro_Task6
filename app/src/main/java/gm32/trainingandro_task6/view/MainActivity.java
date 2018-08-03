package gm32.trainingandro_task6.view;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import gm32.trainingandro_task6.R;
import gm32.trainingandro_task6.database.DatabaseHelper;
import gm32.trainingandro_task6.model.Expenses;
import gm32.trainingandro_task6.model.Income;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DatabaseHelper myDB;
    DatabaseReference databaseIncome;
    DatabaseReference databaseExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB= new DatabaseHelper(this);

        loadFragmrnt(new Homepage());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_homepage) {
            // Handle the camera action
            loadFragmrnt(new Homepage());
        } else if (id == R.id.nav_transaction) {
            loadFragmrnt(new Trasaction());
        } else if (id == R.id.nav_backup) {
            backUp();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void loadFragmrnt(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.viewFragment, fragment);
        ft.commit();
    }

    public void backUp(){
        databaseExpense= FirebaseDatabase.getInstance().getReference();
        databaseExpense.setValue(null);

        databaseIncome= FirebaseDatabase.getInstance().getReference();
        databaseIncome.setValue(null);

        Log.i("Expense", String.valueOf(getExpense().size()) );
        Log.i("Income", String.valueOf(getIncome().size()) );

        for (Expenses exp: getExpense()){
            databaseExpense = FirebaseDatabase.getInstance().getReference("expense");

            String id = databaseExpense.push().getKey();
            //creating an Author Object
            Expenses author = new Expenses(exp.getLabel(), exp.getBecome());
            //Saving the Author
            databaseExpense.child(id).setValue(author);
        }

        for (Income inco: getIncome()){
            databaseIncome = FirebaseDatabase.getInstance().getReference("income");

            String id = databaseIncome.push().getKey();
            //creating an Author Object
            Income author = new Income(inco.getLabel(),inco.getBecome());
            //Saving the Author
            databaseIncome.child(id).setValue(author);
        }
    }

    public List<Expenses> getExpense(){
        List<Expenses> expense= new ArrayList<>();
        Cursor exp = myDB.get_Expenses();
        if(exp.getCount() == 0){
        }
        else{

            Double total= 0d;
            while(exp.moveToNext()){
                Expenses temp= new Expenses(exp.getString(1), exp.getDouble(2));
                expense.add(temp);
                total= total+exp.getDouble(2);
            }
        }
        return  expense;
    }

    public List<Income> getIncome(){
        List<Income> income= new ArrayList<>();
        Cursor inc = myDB.get_Income();
        if(inc.getCount() == 0){
        }
        else{

            Double total= 0d;
            while(inc.moveToNext()){
                Income temp= new Income(inc.getString(1), inc.getDouble(2));
                income.add(temp);
                total= total+inc.getDouble(2);
            }
        }
        return income;
    }


}
