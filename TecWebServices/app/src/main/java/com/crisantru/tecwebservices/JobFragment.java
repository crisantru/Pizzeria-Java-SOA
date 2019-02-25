package com.crisantru.tecwebservices;


import android.os.Bundle;
import android.os.StrictMode;
import android.app.Fragment;
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
public class JobFragment extends Fragment implements View.OnClickListener {

    private Job job;
    private TextView edt_id;
    private TextView edt_jobTitle;
    private TextView edt_minSalary;
    private TextView edt_maxSalary;

    private TextView lbl_idJob;
    private TextView lbl_jobTitle;
    private TextView lbl_minSalary;
    private TextView lbl_maxSalary;

    private ImageButton btn_clean;
    private ImageButton btn_search;
    private ImageButton btn_save;
    private ImageButton btn_delete;

    public JobFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).setActionBarTitle("Jobs");
        View view = inflater.inflate(R.layout.fragment_job, container, false);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        edt_id = (TextView) view.findViewById(R.id.edt_id);
        edt_jobTitle = (TextView) view.findViewById(R.id.edt_jobTitle);
        edt_minSalary = (TextView) view.findViewById(R.id.edt_minSalary);
        edt_maxSalary = (TextView) view.findViewById(R.id.edt_maxSalary);

        lbl_idJob = (TextView) view.findViewById(R.id.lbl_idJob);
        lbl_jobTitle = (TextView) view.findViewById(R.id.lbl_jobTitle);
        lbl_minSalary = (TextView) view.findViewById(R.id.lbl_minSalary);
        lbl_maxSalary = (TextView) view.findViewById(R.id.lbl_maxSalary);


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
        Log.d("clean","entra a clean");
        edt_id.setText("");
        edt_jobTitle.setText("");
        edt_minSalary.setText("");
        edt_maxSalary.setText("");
        lbl_idJob.setText("");
        lbl_jobTitle.setText("");
        lbl_minSalary.setText("");
        lbl_maxSalary.setText("");
    }

    private void search(){

        int res = 0;

        if(!edt_id.getText().toString().isEmpty()){
            res = 1;
        }

        if(!edt_jobTitle.getText().toString().isEmpty()){
            res = 2;
        }


        switch (res){
            case 1: getJobId();
                break;
            case 2: getJobName();
                break;
            default:

                if(!edt_minSalary.getText().toString().isEmpty() || !edt_maxSalary.getText().toString().isEmpty()){
                    Toast.makeText(getActivity().getApplicationContext(),
                            "No se puede realizar busqueda por estos campos",
                            Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(getActivity().getApplicationContext(),
                        "No se puede realizar busqueda. El campo de ID o Nombre estan Vacios",
                        Toast.LENGTH_SHORT).show();
        }


    }

    private void getJobId(){

        String id = edt_id.getText().toString();
        job = Stub.getJobId(id);
        if (job != null){
            edt_jobTitle.setText(job.getJobTitle());
            edt_minSalary.setText(job.getMinSalary());
            edt_maxSalary.setText(job.getMaxSalary());

            lbl_idJob.setText("ID Job: " + job.getIdJob());
            lbl_jobTitle.setText("Nombre: " + job.getJobTitle());
            lbl_minSalary.setText("Min Salary: " +Integer.toString(job.getMinSalary()));
            lbl_maxSalary.setText("Max Salary: " +Integer.toString(job.getMaxSalary()));

        } else {
            Toast.makeText(getActivity().getApplicationContext(), "No se encontraron resultados", Toast.LENGTH_SHORT).show();
        }
    }

    private void getJobName(){

        String jobTitle = edt_jobTitle.getText().toString();
        job = Stub.getJobName(jobTitle);
        if (job != null){
            edt_id.setText(job.getIdJob());
            edt_minSalary.setText(Integer.toString(job.getMinSalary()));
            edt_maxSalary.setText(Integer.toString(job.getMaxSalary()));

            lbl_idJob.setText("ID Job: " + job.getIdJob());
            lbl_jobTitle.setText("Nombre: " + job.getJobTitle());
            lbl_minSalary.setText("Min Salary: " +Integer.toString(job.getMinSalary()));
            lbl_maxSalary.setText("Max Salary: " +Integer.toString(job.getMaxSalary()));

        } else {
            Toast.makeText(getActivity().getApplicationContext(), "No se encontraron resultados", Toast.LENGTH_SHORT).show();
        }
    }

    private void save(){

        if(edt_id.getText().toString().isEmpty() || edt_jobTitle.getText().toString().isEmpty()
                || edt_minSalary.getText().toString().isEmpty() || edt_maxSalary.getText().toString().isEmpty()){
            Toast.makeText(getActivity().getApplicationContext(), "No se puede guardar campos vacios", Toast.LENGTH_SHORT).show();
            return;
        }

        int minSalary = Integer.valueOf(edt_minSalary.getText().toString());
        int maxSalary = Integer.valueOf(edt_maxSalary.getText().toString());

        boolean ok = Stub.setJob(edt_id.getText().toString(), edt_jobTitle.getText().toString(), minSalary, maxSalary);
        if (ok) {
            Toast.makeText(getActivity().getApplicationContext(), "Registro guardado exitosamente", Toast.LENGTH_SHORT).show();
        }
    }

    private void delete(){

        if(edt_id.getText().toString().isEmpty()){
            Toast.makeText(getActivity().getApplicationContext(), "Campo vacio Introducir ID", Toast.LENGTH_SHORT).show();
        }

        boolean ok = Stub.deleteJob(edt_id.getText().toString());
        if (ok) {
            clean();
            Toast.makeText(getActivity().getApplicationContext(), "Registro borrado exitosamente", Toast.LENGTH_SHORT).show();
        }
    }
}
