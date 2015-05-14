package org.javaprotrepticon.android.teddemo.storage.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class ItemGuid {

	@DatabaseField(generatedId = true)
	private Integer id;

	@DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
	private ChannelItem item;
	
	@DatabaseField
	private Boolean isPermaLink;
	
	public ItemGuid() {
		
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

	public Boolean getIsPermaLink() {
		return isPermaLink;
	}

	public void setIsPermaLink(Boolean isPermaLink) {
		this.isPermaLink = isPermaLink;
	}

}
