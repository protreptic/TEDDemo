package org.javaprotrepticon.android.teddemo.parser;

import java.util.List;

import org.javaprotrepticon.android.teddemo.storage.model.ChannelItem;

public interface FeedParser {
    List<ChannelItem> parse();
}