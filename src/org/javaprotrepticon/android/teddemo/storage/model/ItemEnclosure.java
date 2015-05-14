package org.javaprotrepticon.android.teddemo.storage.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class ItemEnclosure {
	
	@DatabaseField(generatedId = true)
	private Integer id;
	
	@DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
	private ChannelItem item;
	
	@DatabaseField(canBeNull = false)
	private String url;
	
	@DatabaseField(canBeNull = false)
	private Long length;
	
	@DatabaseField(canBeNull = false)
	private String type;
	
	public ItemEnclosure() {
		
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getLength() {
		return length;
	}

	public void setLength(Long length) {
		this.length = length;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
