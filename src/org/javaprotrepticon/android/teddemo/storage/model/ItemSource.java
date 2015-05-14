package org.javaprotrepticon.android.teddemo.storage.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Its value is the name of the RSS channel that the item came from, derived
 * from its <title>. It has one required attribute, url, which links to the
 * XMLization of the source.
 * 
 * @author petronic
 *
 */
@DatabaseTable
public class ItemSource {

	@DatabaseField(generatedId = true)
	private Integer id;

	@DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
	private ChannelItem item;

	@DatabaseField
	private String title;

	@DatabaseField(canBeNull = false)
	private String url;

	public ItemSource() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ChannelItem getItem() {
		return item;
	}

	public void setItem(ChannelItem item) {
		this.item = item;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
