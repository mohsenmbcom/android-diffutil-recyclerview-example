package com.mohsenmb.diffutilexample;

import android.support.v7.util.DiffUtil;
import android.support.v7.util.ListUpdateCallback;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WebsitesRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static int TYPE_SECTION_TITLE = 1, TYPE_WEBSITE_ITEM = 2;
    private List<ListItem> allWebsites;
    private List<ListItem> bookmarkedWebsites;

    public WebsitesRecyclerViewAdapter(List<ListItem> allWebsites) {
        this.allWebsites = allWebsites;
        bookmarkedWebsites = new ArrayList<>();
        for (ListItem item : allWebsites)
            if (item.isBookmarked())
                bookmarkedWebsites.add(item);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (TYPE_SECTION_TITLE == viewType)
            return new SectionTitleHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_section_item, parent, false));
        else
            return new WebsiteItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_website_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (position == 0 || position == bookmarkedWebsites.size() + 1) {
            SectionTitleHolder titleHolder = (SectionTitleHolder) holder;
            if (position == 0)
                titleHolder.tvTitle.setText(R.string.bookmarked_websites);
            else
                titleHolder.tvTitle.setText(R.string.all_websites);
        } else {
            WebsiteItemHolder websiteHolder = (WebsiteItemHolder) holder;
            ListItem item = position <= bookmarkedWebsites.size() ? bookmarkedWebsites.get(position - 1) : allWebsites.get(position - (bookmarkedWebsites.size() + 2));
            websiteHolder.tvTitle.setText(item.getTitle());
            websiteHolder.tvDescription.setText(item.getDescription());
            websiteHolder.cbBookmarked.setOnCheckedChangeListener(null);
            websiteHolder.cbBookmarked.setChecked(item.isBookmarked());
            websiteHolder.cbBookmarked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int position = holder.getAdapterPosition();
                    if (position > 0) {
                        ListItem item = position <= bookmarkedWebsites.size() ? bookmarkedWebsites.get(position - 1) : allWebsites.get(position - (bookmarkedWebsites.size() + 2));
                        item.setBookmarked(isChecked);

                        // to know unchecked from bookmarked section
                        if (!isChecked && position <= bookmarkedWebsites.size()) {
                            int aPos = allWebsites.indexOf(item) + 2 + bookmarkedWebsites.size();
                            notifyItemChanged(aPos);
                        }
                        syncBookmarkedItems();
                    }
                }
            });
        }
    }

    private void syncBookmarkedItems() {
        List<ListItem> newBookmarks = new ArrayList<>();
        for (ListItem item : allWebsites)
            if (item.isBookmarked())
                newBookmarks.add(item);
        ListItemDiffCallback callback = new ListItemDiffCallback(bookmarkedWebsites, newBookmarks);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(callback);
        bookmarkedWebsites.clear();
        bookmarkedWebsites.addAll(newBookmarks);
        diffResult.dispatchUpdatesTo(new ListUpdateCallback() {
            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position + 1, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position + 1, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition + 1, toPosition + 1);
            }

            @Override
            public void onChanged(int position, int count, Object payload) {
                notifyItemRangeChanged(position + 1, count, payload);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allWebsites.size() + bookmarkedWebsites.size() + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == bookmarkedWebsites.size() + 1)
            return TYPE_SECTION_TITLE;
        else
            return TYPE_WEBSITE_ITEM;
    }

    private static class SectionTitleHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        SectionTitleHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView;
        }
    }

    private static class WebsiteItemHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription;
        CheckBox cbBookmarked;

        WebsiteItemHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            cbBookmarked = (CheckBox) itemView.findViewById(R.id.checkbox);
        }
    }
}
