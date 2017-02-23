package mx.edu.utng.orders;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mx.edu.utng.orders.adapters.DepartmentAdapter;
import mx.edu.utng.orders.model.Departments;
import mx.edu.utng.orders.sqlite.DBOperations;

public class DepartmentsActivity extends AppCompatActivity {
    private EditText etDepartmentName;
    private EditText etDepartmentPhone;
    private Button btSaveDepartment;
    private DBOperations operations;
    private Departments departments = new Departments();
    private RecyclerView rvDepartments;
    private List<Departments>departmentsList = new ArrayList<Departments>();
    private DepartmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departments);

        operations  = DBOperations.getDBOperations(getApplicationContext());
        departments.setDeptoNo("");

        initComponents();
    }

    public void initComponents(){
        rvDepartments = (RecyclerView)findViewById(R.id.rv_department_list);
        rvDepartments.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvDepartments.setLayoutManager(layoutManager);

        getDepartments();
        adapter = new DepartmentAdapter(departmentsList);
        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.bt_edit_department:
                        Cursor c = operations.getDepartmentsById(departmentsList.get(rvDepartments.getChildPosition(
                                (View)v.getParent().getParent())).getDeptoNo());
                        if(c!=null){
                            if(c.moveToFirst()){
                                departments = new Departments(c.getString(1), c.getString(2), c.getString(3));
                                etDepartmentName.setText(departments.getDeptoName());
                                etDepartmentPhone.setText(departments.getDeptoPhone());
                            }else{
                                Toast.makeText(DepartmentsActivity.this, "Registro no encontrado!!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        Toast.makeText(getApplicationContext(), "Edit", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.bt_delete_department:
                        operations.deleteDepartment(departmentsList.get(rvDepartments.getChildPosition(
                                (View)v.getParent().getParent())).getDeptoNo());
                        getDepartments();
                        adapter.notifyDataSetChanged();
                    default:
                        break;
                }
            }
        });

        rvDepartments.setAdapter(adapter);

        etDepartmentName = (EditText)findViewById(R.id.et_department_name);
        etDepartmentPhone = (EditText)findViewById(R.id.et_department_phone);
        btSaveDepartment = (Button)findViewById(R.id.bt_save_department);

        btSaveDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!departments.getDeptoNo().equals("")){
                    departments.setDeptoName(etDepartmentName.getText().toString());
                    departments.setDeptoPhone(etDepartmentPhone.getText().toString());

                    operations.updateDepartment(departments);
                }else{
                    departments = new Departments("", etDepartmentName.getText().toString(),
                                                        etDepartmentPhone.getText().toString());

                    operations.insertDepartments(departments);
                }
                clearData();
                getDepartments();
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void getDepartments(){
        Cursor c = operations.getDepartments();
        departmentsList.clear();
        if(c!=null){
            while(c.moveToNext()){
                departmentsList.add(new Departments(c.getString(1), c.getString(2), c.getString(3)));
            }
        }
    }

    private void clearData(){
        etDepartmentName.setText("");
        etDepartmentPhone.setText("");
        departments = null;
        departments = new Departments();
        departments.setDeptoNo("");
    }
}
