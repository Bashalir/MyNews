package com.oc.bashalir.mynews.Views;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.oc.bashalir.mynews.Controllers.Utils.Utilities;

import com.oc.bashalir.mynews.Models.TopStories;
import com.oc.bashalir.mynews.R;
import com.squareup.picasso.Picasso;


import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.fragment_page_news_title)
    TextView mTitle;
    @BindView(R.id.fragment_page_news_ariane)
    TextView mAriane;
    @BindView(R.id.fragment_page_news_date)
    TextView mDate;
    @BindView(R.id.fragment_page_news_img)
    ImageView mImg;

    private Utilities utilities;

    public NewsHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }



    public void updateWithNews(TopStories.Result topStories) {


        String ariane;

        if (topStories.getSubsection().isEmpty()) {
            ariane = topStories.getSection();
        } else {
            ariane = topStories.getSection() + " > " + topStories.getSubsection();
        }

        Log.d("TAG",topStories.getUpdatedDate() );

        String newsDate=new Utilities().DateShortFormatter(topStories.getUpdatedDate(),"yyyy-MM-dd'T'HH:mm:ssZZZZZ");

        mTitle.setText(topStories.getTitle());
        mAriane.setText(ariane);
        mDate.setText(newsDate);

        String imgURL="https://www.nytco.com/wp-content/themes/nytco/images/nytco/sidebar-logo.png";

        if (!topStories.getMultimedia().isEmpty()) {

            imgURL =topStories.getMultimedia().get(1).getUrl();
        }

        Picasso.get().load(imgURL).into(mImg);
    }



}