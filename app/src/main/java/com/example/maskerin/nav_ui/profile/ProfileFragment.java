package com.example.maskerin.nav_ui.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.maskerin.LoginActivity;
import com.example.maskerin.MainActivity;
import com.example.maskerin.Pengguna;
import com.example.maskerin.R;
import com.example.maskerin.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    private ProfileViewModel notificationsViewModel;
    private DatabaseReference databaseReference;
    private TextView tvnama,tvemail,tvnik;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private Button btnkeluar;
    private ImageView btnEditNama,btnEditEmail,btnEditPassword;
    private String GetUserID;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        notificationsViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        final TextView textView = root.findViewById(R.id.tv_profile);


        tvnama=root.findViewById(R.id.tvNama);
        tvnik=root.findViewById(R.id.tvNik);
        tvemail=root.findViewById(R.id.tvEmail);
        btnkeluar=root.findViewById(R.id.button_logout);
        btnEditEmail=root.findViewById(R.id.btnEditEmail);
        btnEditNama=root.findViewById(R.id.btnEditNama);
        btnEditPassword=root.findViewById(R.id.btnEditPassword);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        GetUserID = user.getUid();
        databaseReference = firebaseDatabase.getReference("Pengguna").child(firebaseAuth.getUid());

        
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                Pengguna userProfile = dataSnapshot.getValue(Pengguna.class);
                tvemail.setText(userProfile.getEmail());
                tvnik.setText(userProfile.getNik());
                tvnama.setText(userProfile.getNama());

            }
            @Override
            public void onCancelled( DatabaseError databaseError) {
                // Toast.makeText(ProfileActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        btnkeluar.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }

        });

        btnEditNama.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.dialog_edit_nama, null);
                final EditText etBaru = alertLayout.findViewById(R.id.etBaru);
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

                alert.setTitle("Edit Nama");
                alert.setView(alertLayout);
                // disallow cancel of AlertDialog on click of back button and outside touch
                alert.setCancelable(false);
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nama = etBaru.getText().toString();
                        String email = tvemail.getText().toString();
                        String nik = tvnik.getText().toString();
                        Pengguna pengguna = new Pengguna(email,nama,nik);
                        FirebaseDatabase.getInstance().getReference("Pengguna")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(pengguna);
                        etBaru.onEditorAction(EditorInfo.IME_ACTION_DONE);
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });

        btnEditEmail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.dialog_edit_email, null);
                final EditText etBaru = alertLayout.findViewById(R.id.etBaru);
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

                alert.setTitle("Edit Email");
                alert.setView(alertLayout);
                // disallow cancel of AlertDialog on click of back button and outside touch
                alert.setCancelable(false);
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String email = etBaru.getText().toString();
                        String nama = tvnama.getText().toString();
                        String nik = tvnik.getText().toString();
                        Pengguna pengguna = new Pengguna(email,nama,nik);
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        user.updateEmail(email);
                        FirebaseDatabase.getInstance().getReference("Pengguna")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(pengguna);
                        etBaru.onEditorAction(EditorInfo.IME_ACTION_DONE);
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });
        btnEditPassword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.dialog_edit_password, null);
                final EditText etBaru = alertLayout.findViewById(R.id.etBaru);
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

                alert.setTitle("Edit Kata Sandi");
                alert.setView(alertLayout);
                // disallow cancel of AlertDialog on click of back button and outside touch
                alert.setCancelable(false);
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String password = etBaru.getText().toString();
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        user.updatePassword(password);
                        etBaru.onEditorAction(EditorInfo.IME_ACTION_DONE);
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });
        return root;
    }
}