package com.mohsenmb.diffutilexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<ListItem> items = new ArrayList<>();
        items.add(new ListItem("Google", "Enables users to search the world's information, including webpages, images, and videos. Offers…More unique features and search technology.", false));
        items.add(new ListItem("YouTube", "User-submitted videos with rating, comments, and contests.", false));
        items.add(new ListItem("Facebook", "A social utility that connects people, to keep up with friends, upload photos, share links and videos.", false));
        items.add(new ListItem("Baidu", "The leading Chinese language search engine.", false));
        items.add(new ListItem("Yahoo", "A major internet portal and service provider offering search results, customizable content, cha…Moretrooms, free e-mail, clubs, and pager.", false));
        items.add(new ListItem("Wikipedia", "A free encyclopedia built collaboratively using wiki software. (Creative Commons Attribution-Sh…MoreareAlike License).", false));
        items.add(new ListItem("Amazon", "Amazon.com seeks to be Earth's most customer-centric company.", false));
        items.add(new ListItem("Qq", "China's largest and most used Internet service portal owned by Tencent.", false));
        items.add(new ListItem("Toobao", "Launched in May 2003, Taobao Marketplace (www.taobao.com) is the online shopping destination of…More choice for Chinese consumers looking for wide selection, value and convenience.", false));
        items.add(new ListItem("Live", "Search engine from Microsoft.", false));
        WebsitesRecyclerViewAdapter adapter = new WebsitesRecyclerViewAdapter(items);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvWebsites);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
