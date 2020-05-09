package com.example.maskerin;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PemesananActivity extends AppCompatActivity {
    private TextView nama_apotik,jumlah_stock_dewasa,jumlah_stock_anak,harga_masker_anak, harga_masker_dewasa,total_harga;
    private EditText jumlah_dewasa,jumlah_anak;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buatpesanan);
        nama_apotik=findViewById(R.id.tvNamaApotik);
        jumlah_stock_dewasa=findViewById(R.id.tv_jumlah_stock_dewasa);
        jumlah_stock_anak=findViewById(R.id.tv_jumlah_stock_anak);
        harga_masker_dewasa=findViewById(R.id.tv_harga_dewasa);
        harga_masker_anak=findViewById(R.id.tv_harga_anak);

        total_harga=findViewById(R.id.tv_total_harga);
        jumlah_dewasa=findViewById(R.id.et_jumlah_dewasa);
        jumlah_anak=findViewById(R.id.et_jumlah_anak);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        getData();
        //dbHelper = new DataHelper(this);
    }

    private void getData(){
        final String getNama = getIntent().getExtras().getString("nama");
        final int getJumlahDewasa = getIntent().getExtras().getInt("stock_dewasa");
        final int getJumlahAnak = getIntent().getExtras().getInt("stock_anak");
        final int getHargaDewasa = getIntent().getExtras().getInt("harga_dewasa");
        final int getHargaAnak = getIntent().getExtras().getInt("harga_anak");

        nama_apotik.setText(getNama);
        jumlah_stock_dewasa.setText(" Tersedia " + String.valueOf(getJumlahDewasa) +" masker ");
        jumlah_stock_anak.setText(" Tersedia " + String.valueOf(getJumlahAnak) +" masker ");
        harga_masker_dewasa.setText(" - Rp." + String.valueOf(getHargaDewasa) + "/masker");
        harga_masker_anak.setText(" - Rp." + String.valueOf(getHargaAnak) + "/masker");
    }
}
