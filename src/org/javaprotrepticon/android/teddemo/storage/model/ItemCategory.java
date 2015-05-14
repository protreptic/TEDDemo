package org.javaprotrepticon.android.teddemo.storage.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author petronic
 *
 */
@DatabaseTable
public class ItemCategory {
	
	@DatabaseField(generatedId = true)
	private Integer id;
	
	@DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
	private ChannelItem item;
	
	@DatabaseField(canBeNull = false)
	private String name;
	
	public ItemCategory() {
		
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
