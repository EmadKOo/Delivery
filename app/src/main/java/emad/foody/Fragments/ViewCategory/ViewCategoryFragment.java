package emad.foody.Fragments.ViewCategory;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import emad.foody.Adapters.AllCategoriesAdapter;
import emad.foody.Adapters.DisplayAllCategoryAdapter;
import emad.foody.Model.Category;
import emad.foody.Model.Item;
import emad.foody.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewCategoryFragment extends Fragment {

    ViewCategoryController categoryController;
    RecyclerView viewCategoriesRecycler;
    DisplayAllCategoryAdapter allCategoriesAdapter;
    ArrayList<Item> items = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_view_category, container, false);
        categoryController = new ViewCategoryController(getActivity());
        initRecyclerView(view);

        Category category = (Category) getArguments().getSerializable("category");
        categoryController.getAllItemsOfCategory(category.getCategoryName(),allCategoriesAdapter,items);
        Log.d("TAG", "onCreateView: "  + category.getCategoryName());
        return view;
    }

    private void initRecyclerView(View view){
        viewCategoriesRecycler = view.findViewById(R.id.viewCategoriesRecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        viewCategoriesRecycler.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(viewCategoriesRecycler.getContext(),
                ((LinearLayoutManager) layoutManager).getOrientation());
        viewCategoriesRecycler.addItemDecoration(dividerItemDecoration);
        allCategoriesAdapter = new DisplayAllCategoryAdapter(items, getActivity().getApplicationContext());
        viewCategoriesRecycler.setAdapter(allCategoriesAdapter);
    }
}