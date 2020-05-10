package com.example.maskerin.nav_ui.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.maskerin.EditProfileActivity;
import com.example.maskerin.LoginActivity;
import com.example.maskerin.Pengguna;
import com.example.maskerin.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
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
    private FirebaseUser user;
    private String GetUserID;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        notificationsViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        tvnama=root.findViewById(R.id.tvNama);
        tvnik=root.findViewById(R.id.tvNik);
        tvemail=root.findViewById(R.id.tvEmail);

        auth();
        updatingDataInTheView();
        return root;
    }

    public void auth(){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        GetUserID = user.getUid();
        databaseReference = firebaseDatabase.getReference("Pengguna").child(firebaseAuth.getUid());
    }

    public void updatingDataInTheView(){
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
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.appbar_menu_profile, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.app_bar_edit){
            Intent intent = new Intent(getActivity(), EditProfileActivity.class);
            startActivityForResult(intent, 1);
        } else if(id == R.id.app_bar_logout){
            dialogLogOut();
        }

        return super.onOptionsItemSelected(item);
    }

    public void dialogLogOut(){
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        builder.setTitle("Keluar");
        builder.setMessage("Yakin nih mau keluar dari aplikasi?");
        builder.setIcon(R.drawable.ic_warning_black_24dp);
        builder.setPositiveButton("YA", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //do
            }
        });

        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            Toast.makeText(getActivity(), "Data updated", Toast.LENGTH_LONG).show();
            //getActivity().recreate();
        }
    }
}