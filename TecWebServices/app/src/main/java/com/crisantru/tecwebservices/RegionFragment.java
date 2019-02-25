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
public class RegionFragment extends Fragment implements View.OnClickListener {

    private Region region;
    private TextView edt_id;
    private TextView edt_name;
    private TextView txt_res;

    private ImageButton btn_clean;
    private ImageButton btn_search;
    private ImageButton btn_save;
    private ImageButton btn_delete;


    public RegionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).setActionBarTitle("Regions");
        View view = inflater.inflate(R.layout.fragment_region, container, false);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        edt_id = (TextView) view.findViewById(R.id.edt_id);
        edt_name = (TextView) view.findViewById(R.id.edt_name);
        txt_res = (TextView) view.findViewById(R.id.txt_res);

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
        edt_name.setText("");
        txt_res.setText("");
    }

    private void search(){
        int res = 0;

        if(!edt_id.getText().toString().isEmpty()){
            res = 1;
        }

        if(!edt_name.getText().toString().isEmpty()){
            res = 2;
        }


        switch (res){
            case 1: getRegionId();
                break;
            case 2: getRegionN();
                break;
            default: System.out.println("mori cara");
        }

    }

    private void getRegionId(){
        int id = Integer.valueOf(edt_id.getText().toString());
        region = Stub.getRegion(id);
        if (region != null){
            edt_name.setText(region.getRegionName());
            txt_res.setText(region.getRegionName());
        } else {
            Toast.makeText(getActivity().getApplicationContext(), "No se encontraron resultados", Toast.LENGTH_SHORT).show();
        }
    }

    private void getRegionN(){
        Log.d("path","Entra a bucar por nombre");
        String name = edt_name.getText().toString();
        Log.d("name", name);
        region = Stub.getRegionN(name);
        if (region != null){
            edt_id.setText(Integer.toString(region.getIdRegion()));
            txt_res.setText(Integer.toString(region.getIdRegion()));
        } else {
            Toast.makeText(getActivity().getApplicationContext(), "No se encontraron resultados", Toast.LENGTH_SHORT).show();
        }
    }

    private void save() {

        int id = Integer.valueOf(edt_id.getText().toString());
        Log.d("name", edt_name.getText().toString());
        boolean ok = Stub.setRegion(id, edt_name.getText().toString());
        if (ok) {
            Toast.makeText(getActivity().getApplicationContext(), "Registro guardado exitosamente", Toast.LENGTH_SHORT).show();
        }
    }

    private void delete() {
        int id = Integer.valueOf(edt_id.getText().toString());
        boolean ok = Stub.deleteRegion(id);
        if (ok) {
            clean();
            Toast.makeText(getActivity().getApplicationContext(), "Registro borrado exitosamente", Toast.LENGTH_SHORT).show();
        }
    }
}
