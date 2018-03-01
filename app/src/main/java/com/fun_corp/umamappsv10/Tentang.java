package com.fun_corp.umamappsv10;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.media.MediaMetadataCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tentang extends Fragment{
Button btn_panggil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_tentang, container, false);

        return view;
    }



    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        btn_panggil.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //EditText edt_tlp = (EditText) view.findViewById(R.id.editText_telp);
//                //Intent call = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+edt_tlp.getText()));
//                //startActivity(call);
//                call();
//            }
//        });

    }
//    private void call() {
//        try {
//            Intent callIntent = new Intent(Intent.ACTION_CALL);
//            callIntent.setData(Uri.parse("tel:08992255445"));
//            startActivity(callIntent);
//        } catch (ActivityNotFoundException e) {
//
//        }
//    }
}
