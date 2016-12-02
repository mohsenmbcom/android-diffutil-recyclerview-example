package com.mohsenmb.diffutilexample;

/**
 * Created by Mohsen on 12/2/2016.
 */

public class ListItem {
    private String title, description;
    private boolean bookmarked;

    public ListItem(String title, String description, boolean bookmarked) {
        this.title = title;
        this.description = description;
        this.bookmarked = bookmarked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        this.bookmarked = bookmarked;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof ListItem))
            return false;
        return title.matches(((ListItem) obj).title);
    }
}
