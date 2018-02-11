package com.example.rafatarrega.navdrawer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentCamera.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FragmentCamera extends Fragment {

    private ImageButton btnImagen;
    public final static int RESP_TOMAR_FOTO = 1000;
    private Uri mImageUri;

    ImageView imageView;

    private OnFragmentInteractionListener mListener;

    public FragmentCamera() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_fragment_camera, container, false);

        imageView = (ImageView) v.findViewById(R.id.imageView);
        Button btnCamera = (Button) v.findViewById(R.id.btnCamera);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });

        //btnImagen = (ImageButton) v.findViewById(R.id.imgButton);





       /* btnImagen.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.FROYO)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);

                //Temporaly file to save the photo
                File photo = null;
                try{
                    //place to store the photo
                    photo = FragmentCamera.createTemporaryFile("picture",".jpg", getContext());

                    photo.delete();
                } catch (Exception e){
                    Log.v(getClass().getSimpleName(),
                            "Can´ create file to take picture");
                }
                // mImageUri = Uri.fromFile(photo);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
                startActivityForResult(intent, RESP_TOMAR_FOTO);

            }
        });*/
        return v;
    }


  /*  @RequiresApi(api = Build.VERSION_CODES.FROYO)
    public static File createTemporaryFile(String part, String ext, Context myContext) throws Exception{
        File tempDir = myContext.getExternalCacheDir();
        tempDir = new File(tempDir.getAbsolutePath() + "/temp");
        if (!tempDir.exists()){
            tempDir.mkdir();
        }
        return File.createTempFile(part, ext, tempDir);
    }*/


    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == RESP_TOMAR_FOTO && resultCode == RESULT_OK){
           //Ejemplo1
            // btnImagen.setImageURI(mImageUri);


        }
    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);
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
