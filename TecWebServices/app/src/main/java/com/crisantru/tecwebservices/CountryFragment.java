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
public class CountryFragment extends Fragment implements View.OnClickListener {

    private Country country;
    private TextView edt_id;
    private TextView edt_name;
    private TextView edt_idRegion;

    private TextView lbl_idCountry;
    private TextView lbl_countryName;
    private TextView lbl_idRegion;


    private ImageButton btn_clean;
    private ImageButton btn_search;
    private ImageButton btn_save;
    private ImageButton btn_delete;


    public CountryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).setActionBarTitle("Regions");
        View view = inflater.inflate(R.layout.fragment_country, container, false);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        edt_id = (TextView) view.findViewById(R.id.edt_id);
        edt_name = (TextView) view.findViewById(R.id.edt_name);
        edt_idRegion = (TextView) view.findViewById(R.id.edt_idRegion);

        lbl_idCountry = (TextView) view.findViewById(R.id.lbl_idCountry);
        lbl_countryName = (TextView) view.findViewById(R.id.lbl_countryN);
        lbl_idRegion= (TextView) view.findViewById(R.id.lbl_idRegion);

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
        edt_idRegion.setText("");
        lbl_idCountry.setText("");
        lbl_countryName.setText("");
        lbl_idRegion.setText("");

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
            case 1: getCountryId();
                break;
            case 2: getCountryName();
                break;
            default:

                if(!edt_idRegion.getText().toString().isEmpty()){
                    Toast.makeText(getActivity().getApplicationContext(),
                            "No se puede realizar busqueda por RegionID",
                            Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(getActivity().getApplicationContext(),
                        "No se puede realizar busqueda. El campo de ID o Nombre estan Vacio",
                        Toast.LENGTH_SHORT).show();
        }

    }

    private void getCountryId(){

        String id = edt_id.getText().toString();
        country = Stub.getCountryId(id);
        if (country != null){
            edt_name.setText(country.getCountryName());
            edt_idRegion.setText(Integer.toString(country.getIdRegion()));

            lbl_idCountry.setText("ID Country: " + country.getIdCountry());
            lbl_countryName.setText("Nombre: " + country.getCountryName());
            lbl_idRegion.setText("ID Region: " +Integer.toString(country.getIdRegion()));

        } else {
            Toast.makeText(getActivity().getApplicationContext(), "No se encontraron resultados", Toast.LENGTH_SHORT).show();
        }
    }

    private void getCountryName(){

        country = Stub.getCountryName(edt_name.getText().toString());
        if (country != null){

            edt_id.setText(country.getIdCountry());
            edt_idRegion.setText(Integer.toString(country.getIdRegion()));

            lbl_idCountry.setText("ID Country: " + country.getIdCountry());
            lbl_countryName.setText("Nombre: " + country.getCountryName());
            lbl_idRegion.setText("ID Region: " +Integer.toString(country.getIdRegion()));

        } else {
            Toast.makeText(getActivity().getApplicationContext(), "No se encontraron resultados", Toast.LENGTH_SHORT).show();
        }
    }

    private void save(){
        if(edt_id.getText().toString().isEmpty() || edt_name.getText().toString().isEmpty() || edt_idRegion.getText().toString().isEmpty()){
            Toast.makeText(getActivity().getApplicationContext(), "No se puede guardar campos vacios", Toast.LENGTH_SHORT).show();
            return;
        }

        if(edt_id.getText().toString().length() > 2){
            Toast.makeText(getActivity().getApplicationContext(), "El maximo de caracteres para ID son 2", Toast.LENGTH_SHORT).show();
            return;

        }

        int region = Integer.valueOf(edt_idRegion.getText().toString());
        boolean ok = Stub.setCountry(edt_id.getText().toString(), edt_name.getText().toString(), region);
        if (ok) {
            Toast.makeText(getActivity().getApplicationContext(), "Registro guardado exitosamente", Toast.LENGTH_SHORT).show();
        }
    }

    private void delete(){

        if(edt_id.getText().toString().isEmpty()){
            Toast.makeText(getActivity().getApplicationContext(), "No se puede borrar introducir ID", Toast.LENGTH_SHORT).show();
        }

        if(!edt_name.getText().toString().isEmpty() || !edt_idRegion.getText().toString().isEmpty()){
            Toast.makeText(getActivity().getApplicationContext(), "No se puede borrar por Nombre ni por Region ID", Toast.LENGTH_SHORT).show();
        }

        boolean ok = Stub.deleteCountry(edt_id.getText().toString());
        if (ok) {
            clean();
            Toast.makeText(getActivity().getApplicationContext(), "Registro borrado exitosamente", Toast.LENGTH_SHORT).show();
        }
    }


}
