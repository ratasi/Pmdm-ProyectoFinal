package com.example.rafatarrega.navdrawer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rafatarrega.navdrawer.model.Usuarios;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentProfile.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FragmentProfile extends Fragment {

    private OnFragmentInteractionListener mListener;
    EditText text_nickname;
    EditText text_name;
    Button btnCrearUsuario;
    DatabaseReference bbdd;
    CheckBox checkBox;


    public FragmentProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_profile, container, false);

        checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        text_name = (EditText) view.findViewById(R.id.nombre);
        text_nickname = (EditText) view.findViewById(R.id.nickname);
        btnCrearUsuario = (Button) view.findViewById(R.id.crearUsuario);

        bbdd = FirebaseDatabase.getInstance().getReference("usuarios");

        /*
        bbdd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayAdapter<String> adaptador;
                ArrayList<String> listado = new ArrayList<>();

                for (DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                    Usuarios usuarios = datasnapshot.getValue(Usuarios.class);



                   String nickname = usuarios.getNickname();
                    listado.add(nickname);*/
                //}

                //adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listado);
                //lista.setAdapter(adaptador);
            //}


           /* @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/


        btnCrearUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nickname = text_nickname.getText().toString();
                String name = text_name.getText().toString();
                CheckBox check = checkBox;
                if (!TextUtils.isEmpty(nickname)){
                    if (!TextUtils.isEmpty(name)) {
                            Usuarios u = new Usuarios(nickname, name);
                            String clave = bbdd.push().getKey();
                            bbdd.child(clave).setValue(u);

                            Toast.makeText(getActivity(), "User add", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "Introduce un name", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(getActivity(), "Introduce un nickname", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
