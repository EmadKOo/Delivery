package emad.foody.Fragments.Home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import emad.foody.Adapters.AllCategoriesAdapter;
import emad.foody.Adapters.HomePagerAdapter;
import emad.foody.Model.Category;
import emad.foody.R;

public class HomeFragment extends Fragment {

    RecyclerView categoriesRecyclerView;
    AllCategoriesAdapter allCategoriesAdapter;
    ArrayList<Category> categories = new ArrayList<>();
    HomeController homeController;
    ViewPager homeViewPager;
    HomePagerAdapter pagerAdapter;
    ArrayList<String> pagerImagesList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        homeController = new HomeController();
        initViewPager(view);
        homeController.getPagerImages(pagerAdapter, pagerImagesList);
        initCategoriesRecycler(view);
        homeController.getAllCategories(allCategoriesAdapter, categories);
        return view;
    }

    private void initViewPager(View view){
        homeViewPager = view.findViewById(R.id.homeViewPager);
        pagerAdapter = new HomePagerAdapter(getActivity(), pagerImagesList);
        homeViewPager.setAdapter(pagerAdapter);
    }

    private void initCategoriesRecycler(View view){
        categoriesRecyclerView = view.findViewById(R.id.categoriesRecyclerView);
        categoriesRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),4));
        allCategoriesAdapter = new AllCategoriesAdapter(categories, getActivity());
        categoriesRecyclerView.setAdapter(allCategoriesAdapter);
    }
}