package com.minh.findtheshipper.helpers;

import com.minh.findtheshipper.models.CommentTemp;

import java.util.Comparator;

/**
 * Created by trinh on 7/5/2017.
 */

public class SortCommentTempHelpers implements Comparator<CommentTemp> {
    @Override
    public int compare(CommentTemp o1, CommentTemp o2) {
        return o2.getDateTime().compareTo(o1.getDateTime());
    }
}
