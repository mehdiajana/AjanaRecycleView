package com.example.recycleviewajana;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.recycleviewajana.adapter.StarAdapter;
import com.example.recycleviewajana.beans.Star;
import com.example.recycleviewajana.service.StarService;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private List<Star> stars;
    private RecyclerView recyclerView;
    private StarAdapter starAdapter = null;
    private StarService service =null;
    private static final String TAG = "StarAdapter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        stars = new ArrayList<>();
        service = StarService.getInstance();
        init();
        recyclerView = findViewById(R.id.recycle_view);
        //insérer le code
        starAdapter = new StarAdapter(this, service.findAll());
        recyclerView.setAdapter(starAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    public void init(){
        service.create(new Star("Mehdi Ajana", "https://img.freepik.com/photos-gratuite/homme-affaires-prospere-garde-mains-croisees-expression-satisfaite_273609-16711.jpg?t=st=1647550454~exp=1647551054~hmac=2747ff1c608e98b66fc64afad95fa15c6f015084559ad43c525a3055891aa944&w=740", 3.5f));
        service.create(new Star("Andrea Summers", "https://img.freepik.com/photos-gratuite/assez-souriant-joyeusement-femme-aux-cheveux-blonds-habille-desinvolture-regardant-satisfaction_176420-15187.jpg?t=st=1647550454~exp=1647551054~hmac=078d62058cdf621ec762f4a70d9e7d443e4c471e77974f9ffe777fea567f53fd&w=1380", 3));
        service.create(new Star("Karima Barkox",
                "https://img.freepik.com/photos-gratuite/belle-femme-peau-sombre-longs-cheveux-bruns-large-sourire-heureux-portant-chemise-jean-beneficiant-bonnes-nouvelles-positives-concernant-sa-promotion-au-travail-posant-isole-contre-mur-blanc-blanc-b_176420-10367.jpg?w=1380", 5));
        service.create(new Star("Achraf Benjava", "https://img.freepik.com/photos-gratuite/portrait-homme-blanc-isole_53876-40306.jpg?t=st=1647550454~exp=1647551054~hmac=6b18461d2d29ed834c42cccc8ba1fdba0dc891c269a81a5d6950ae18674cba60&w=1060", 1));
        service.create(new Star("Karim Nouini", "https://img.freepik.com/photos-gratuite/portrait-visage-homme-affaires-souriant-vetu-costume_53876-148135.jpg?w=1380", 5));
        service.create(new Star("Wiam Bounhar", "https://img.freepik.com/photos-gratuite/charmante-jeune-femme-detendue-douce-faisant-procedure-cosmetologique-appliquant-creme-pour-visage-visage-doigts-souriant-largement-se-sentir-parfait-prenant-soin-peau_176420-24010.jpg?t=st=1647550454~exp=1647551054~hmac=761179ecfa858736b3b798a9993e793936e477710987110312540cd93c81728a&w=1380", 1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView)
                MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new
                                                  SearchView.OnQueryTextListener() {
                                                      @Override
                                                      public boolean onQueryTextSubmit(String query) {
                                                          return true;
                                                      }
                                                      @Override
                                                      public boolean onQueryTextChange(String newText) {
                                                          if (starAdapter != null){
                                                              starAdapter.getFilter().filter(newText);
                                                          }
                                                          return true;
                                                      }
                                                  });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.share){
            String txt = "Stars";
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle("Stars")
                    .setText(txt)
                    .startChooser();
        }
        return super.onOptionsItemSelected(item);
    }


}