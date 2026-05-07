package com.example.a71;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShowItemsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewItems;
    private ItemsAdapter adapter;
    private DatabaseHelper dbHelper;
    private Spinner spinnerFilter;
    private EditText etSearch;
    private List<LostFoundItem> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_items);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        dbHelper = new DatabaseHelper(this);
        recyclerViewItems = findViewById(R.id.recyclerViewItems);
        spinnerFilter = findViewById(R.id.spinnerFilter);
        etSearch = findViewById(R.id.etSearch);

        recyclerViewItems.setLayoutManager(new LinearLayoutManager(this));
        itemList = new ArrayList<>();
        adapter = new ItemsAdapter(itemList, item -> {
            Intent intent = new Intent(ShowItemsActivity.this, ItemDetailActivity.class);
            intent.putExtra("item", item);
            startActivity(intent);
        });
        recyclerViewItems.setAdapter(adapter);

        setupFilter();
        setupSearch();
    }

    private void setupFilter() {
        String[] categories = {
                getString(R.string.all_categories),
                "Electronics", "Pets", "Wallets", "Documents", "Others"
        };
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFilter.setAdapter(spinnerAdapter);

        spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterAndSearch();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void setupSearch() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterAndSearch();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        filterAndSearch();
    }

    private void filterAndSearch() {
        String category = spinnerFilter.getSelectedItem().toString();
        String query = etSearch.getText().toString().trim();

        if (category.equals(getString(R.string.all_categories))) {
            if (query.isEmpty()) {
                itemList = dbHelper.getAllItems();
            } else {
                itemList = dbHelper.searchItems(query);
            }
        } else {
            List<LostFoundItem> allForCategory = dbHelper.getItemsByCategory(category);
            if (query.isEmpty()) {
                itemList = allForCategory;
            } else {
                itemList = new ArrayList<>();
                for (LostFoundItem item : allForCategory) {
                    if (item.getName().toLowerCase().contains(query.toLowerCase()) ||
                        item.getDescription().toLowerCase().contains(query.toLowerCase())) {
                        itemList.add(item);
                    }
                }
            }
        }
        adapter.updateList(itemList);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
