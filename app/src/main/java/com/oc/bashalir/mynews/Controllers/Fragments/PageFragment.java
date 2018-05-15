package com.oc.bashalir.mynews.Controllers.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

<<<<<<< HEAD
import com.oc.bashalir.mynews.Views.Adapters.ListNewsAdapter;
import com.oc.bashalir.mynews.Models.TopStories;
=======
import com.oc.bashalir.mynews.Controllers.Models.TopStories.TopStories;
>>>>>>> parent of 61f2c37... Fix Connexion API NYT
import com.oc.bashalir.mynews.Controllers.Utils.NYTStreams;
import com.oc.bashalir.mynews.R;
import com.oc.bashalir.mynews.Views.Adapters.PageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;
import icepick.State;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment extends Fragment {

    private static final String KEY_POSITION = "position";
    private final String mTag = getClass().getSimpleName();
    private Disposable mDisp;
    private List<TopStories.Result> mTopstories;
    private PageAdapter pageAdapter;

    @BindView(R.id.fragment_page_tv) TextView textView;
    @BindView(R.id.fragment_page_listnews_rv)  RecyclerView recyclerView;
    private ListNewsAdapter adapter;
    //  @State int mPosition;


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.destroyDispose();
    }

    public PageFragment() {
        // Required empty public constructor
    }

    public static PageFragment newInstance(int position) {

        PageFragment frag = new PageFragment();

        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);
        //  mPosition=position;


        return (frag);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View result = inflater.inflate(R.layout.fragment_page, container, false);
        ButterKnife.bind(this, result);
        Icepick.restoreInstanceState(this, savedInstanceState);

        int position = getArguments().getInt(KEY_POSITION, -1);

        textView.setText("Page n° " + position);
        this.configureRecyckerView();
       this.RequestTopStories();

        Log.d(mTag, "Page n° " + position);



        return result;
    }


    private void configureRecyckerView(){

        this.adapter=new ListNewsAdapter(mTopstories);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }




private void RequestTopStories(){
        this.updateUIWhenStartingRequest();
        mDisp= NYTStreams.streamFetchTopStories().subscribeWith(new DisposableObserver<List<TopStories>>() {
            @Override
            public void onNext(List<TopStories> topStories) {
                Log.d(mTag, "NEXT");
                updateUIWithList(topStories);
<<<<<<< HEAD

=======
>>>>>>> parent of 61f2c37... Fix Connexion API NYT
            }


            @Override
            public void onError(Throwable e) {
                Log.e(mTag,"On Error"+Log.getStackTraceString(e));
            }

            @Override
            public void onComplete() {
                Log.e(mTag,"On Complete !!");

            }
        });
}


    /**
     * Dispose subscription
     */
    private void destroyDispose(){
        if(mDisp !=null && !mDisp.isDisposed()) mDisp.dispose();
    }


    private void updateUIWhenStartingRequest(){
        this.textView.setText("Downloading...");
    }

    private void updateUIStop(String response){
        this.textView.setText(response);
    }

<<<<<<< HEAD
    private void updateUIWithList(TopStories topStories){

        mTopstories.addAll(topStories.getResults());
        updateUIStop(topStories.getResults().toString());
        this.adapter.notifyDataSetChanged();
=======
    private void updateUIWithList(List<TopStories> topStories){
        StringBuilder stringBuilder=new StringBuilder();
        for (TopStories stories : topStories){
            stringBuilder.append("-"+stories.getResults()+"\n");
        }
        updateUIStop(stringBuilder.toString());
>>>>>>> parent of 61f2c37... Fix Connexion API NYT
    }
}
