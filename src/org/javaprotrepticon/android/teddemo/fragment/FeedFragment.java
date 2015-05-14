package org.javaprotrepticon.android.teddemo.fragment;

import org.javaprotrepticon.android.teddemo.parser.TedRssFeedParser;
import org.javaprotrepticon.android.teddemo.storage.model.Channel;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FeedFragment extends Fragment {
	
	private TextView itemsView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		itemsView = new TextView(getActivity());
		itemsView.setTextSize(12); 
		
		return itemsView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		new ReadFeed().execute();
	}
	
	public class ReadFeed extends AsyncTask<Void, Void, Channel> {

        @Override
        protected Channel doInBackground(Void... arg0) {
            return new TedRssFeedParser(getActivity()).parse();
        }

        protected void onPostExecute(Channel channel) {
        	itemsView.setText(
        		"title: " + channel.getTitle() + "\n" + 
        		"link: " + channel.getLink() + "\n" + 
        		"description: " + channel.getDescription() + "\n" +
        		"language: " + channel.getLanguage() + "\n" +
        		"copyright: " + channel.getCopyright() + "\n" +
        		"managing editor: " + channel.getManagingEditor() + "\n" +
        		"web master: " + channel.getWebMaster() + "\n" +
        		"pub date: " + channel.getPubDate() + "\n" + 
        		"last build date: "+ channel.getLastBuildDate() + "\n" +
        		//"categories: " + channel.getCategories().size() + "\n" +
        		"generator: " + channel.getGenerator() + "\n" +
        		"docs: " + channel.getDocs() + "\n" + 
        		"ttl: " + channel.getTtl() + "\n" +
        		"rating: " + channel.getRating() + "\n" +
        		"skip hours: " + channel.getSkipHours() + "\n" + 
        		"skip days: " + channel.getSkipDays() + "\n" + 
        		"image: " + channel.getImage() + "\n" + 
        		"cloud: " + channel.getCloud());    
        }
        
	}
	
}
