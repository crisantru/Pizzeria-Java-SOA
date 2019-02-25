package com.crisantru.tecwebservices;


import android.os.Bundle;
import android.app.Fragment;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmployeeFragment extends Fragment implements View.OnClickListener {

    private Employee employee;
    private TextView edt_idEmployee;
    private TextView edt_firstName;
    private TextView edt_lastName;
    private TextView edt_email;
    private TextView edt_phoneNumber;
    private TextView edt_hireDate;
    private TextView edt_idJob;
    private TextView edt_salary;
    private TextView edt_commissionPCT;
    private TextView edt_idManager;
    private TextView edt_idDepatment;

    private ImageButton btn_clean;
    private ImageButton btn_search;
    private ImageButton btn_save;
    private ImageButton btn_delete;

    public EmployeeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((MainActivity) getActivity()).setActionBarTitle("Employees");
        View view = inflater.inflate(R.layout.fragment_employee, container, false);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        edt_idEmployee = (TextView) view.findViewById(R.id.edt_idEmployee);
        edt_firstName = (TextView) view.findViewById(R.id.edt_firstName);
        edt_lastName = (TextView) view.findViewById(R.id.edt_lastName);
        edt_email = (TextView) view.findViewById(R.id.edt_email);
        edt_phoneNumber = (TextView) view.findViewById(R.id.edt_phoneNumber);
        edt_hireDate = (TextView) view.findViewById(R.id.edt_hireDate);
        edt_idJob = (TextView) view.findViewById(R.id.edt_idJob);
        edt_salary = (TextView) view.findViewById(R.id.edt_salary);
        edt_commissionPCT = (TextView) view.findViewById(R.id.edt_commissionPCT);
        edt_idManager = (TextView) view.findViewById(R.id.edt_idManager);
        edt_idDepatment = (TextView) view.findViewById(R.id.edt_idDepartment);

        btn_clean = (ImageButton) view.findViewById(R.id.btn_clean);
        btn_search = (ImageButton) view.findViewById(R.id.btn_search);
        btn_save = (ImageButton) view.findViewById(R.id.btn_save);
        btn_delete = (ImageButton) view.findViewById(R.id.btn_delete);

        btn_clean.setOnClickListener(this);
        btn_search.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        btn_delete.setOnClickListener(this);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_clean:
                clean();
                break;
            case R.id.btn_search:
                search();
                break;
            case R.id.btn_save:
                save();
                break;
            case R.id.btn_delete:
                delete();
                break;
        }

    }

    private void clean() {
        Log.d("clean", "entra a clean");
        edt_idEmployee.setText("");
        edt_firstName.setText("");
        edt_lastName.setText("");
        edt_email.setText("");
        edt_phoneNumber.setText("");
        edt_hireDate.setText("");
        edt_idJob.setText("");
        edt_salary.setText("");
        edt_commissionPCT.setText("");
        edt_idManager.setText("");
        edt_idDepatment.setText("");

    }

    private void search() {
        int res = 0;

        if (!edt_idEmployee.getText().toString().isEmpty()) {
            res = 1;
        }

        if (!edt_firstName.getText().toString().isEmpty()) {
            res = 2;
        }


        switch (res) {
            case 1:
                getEmployeeId();
                break;
            case 2: //getCountryName();
                break;
            default:

                Toast.makeText(getActivity().getApplicationContext(),
                        "No se puede realizar busqueda. El campo de ID o Nombre estan Vacio",
                        Toast.LENGTH_SHORT).show();
        }

    }

    private void getEmployeeId() {
        Log.d("path", "Entra a getEmployeeID");
        int idEmployee = Integer.valueOf(edt_idEmployee.getText().toString());
        employee = Stub.getEmployeeId(idEmployee);
        String cPCT;
        if (employee != null) {
            edt_firstName.setText(employee.getFirstName());
            edt_lastName.setText(employee.getLastName());
            edt_email.setText(employee.getEmail());
            edt_phoneNumber.setText(employee.getPhoneNumber());
            edt_hireDate.setText(employee.getHireDate());
            edt_idJob.setText(employee.getIdJob());
            edt_salary.setText(Float.toString(employee.getSalary()));
            edt_commissionPCT.setText(employee.getCommissionPCT());
            edt_idManager.setText(Integer.toString(employee.getIdManager()));
            edt_idDepatment.setText(Integer.toString(employee.getIdDepartment()));
        } else {
            Toast.makeText(getActivity().getApplicationContext(), "No se encontraron resultados", Toast.LENGTH_SHORT).show();
        }

    }

    public void save(){
        int idEmployee = Integer.valueOf(edt_idEmployee.getText().toString());
        float salary = Float.valueOf(edt_salary.getText().toString());
        float comPCT = Float.valueOf(edt_commissionPCT.getText().toString());
        int idManager = Integer.valueOf(edt_idManager.getText().toString());
        int idDepartment = Integer.valueOf(edt_idDepatment.getText().toString());

        boolean ok = Stub.setEmployee(idEmployee, edt_firstName.getText().toString(), edt_lastName.getText().toString(),
                edt_email.getText().toString(), edt_phoneNumber.getText().toString(), edt_hireDate.getText().toString(),
                edt_idJob.getText().toString(), salary, comPCT, idManager, idDepartment );

        if (ok) {
            clean();
            Toast.makeText(getActivity().getApplicationContext(), "Registro Guardado exitosamente", Toast.LENGTH_SHORT).show();
        }
    }

    private void delete() {
        int idEmployee = Integer.valueOf(edt_idEmployee.getText().toString());
        boolean ok = Stub.deleteEmployee(idEmployee);
        if (ok) {
            clean();
            Toast.makeText(getActivity().getApplicationContext(), "Registro borrado exitosamente", Toast.LENGTH_SHORT).show();
        }
    }
}
