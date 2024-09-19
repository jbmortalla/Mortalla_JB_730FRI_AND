package com.eldroid.news;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class HeadlineListFragment extends Fragment {

    private List<Headline> headlines = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_headline_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        headlines.add(new Headline("Breaking News: Major Earthquake Hits City",
                "A major earthquake with a magnitude of 7.5 struck the city this morning, causing widespread damage and panic."));
        headlines.add(new Headline("Sports Update: Local Team Wins Championship",
                "The local football team clinched the championship title after a thrilling final match that ended in a dramatic penalty shootout."));
        headlines.add(new Headline("Technology: New Smartphone Released",
                "The latest smartphone model has been released, featuring cutting-edge technology and innovative design improvements."));
        headlines.add(new Headline("Health Alert: New Virus Strain Detected",
                "A new strain of virus has been detected, prompting health authorities to issue a global alert and recommend precautionary measures."));
        headlines.add(new Headline("Entertainment: Upcoming Film Festival Announced",
                "The annual film festival is set to kick off next month, featuring a diverse range of films from around the world and special guest appearances."));

        HeadlineAdapter adapter = new HeadlineAdapter(headlines, this::onHeadlineClick);
        recyclerView.setAdapter(adapter);
    }
    private void onHeadlineClick(Headline headline) {
        NewsContentFragment newsContentFragment = new NewsContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("headline_title", headline.getTitle());
        bundle.putString("headline_content", headline.getContent());
        newsContentFragment.setArguments(bundle);

        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (getResources().getConfiguration().orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE) {
            fragmentTransaction.replace(R.id.newsContentContainer, newsContentFragment, "NEWS_CONTENT_FRAGMENT");
        } else {
            fragmentTransaction.replace(R.id.headlineListContainer, newsContentFragment, "NEWS_CONTENT_FRAGMENT");
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }

}
