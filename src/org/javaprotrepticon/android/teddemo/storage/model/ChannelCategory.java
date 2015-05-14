package org.javaprotrepticon.android.teddemo.storage.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author petronic
 *
 */
@DatabaseTable
public class ChannelCategory {
	
	@DatabaseField(generatedId = true)
	private Integer id;
	
	@DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
	private Channel channel;
	
	@DatabaseField(canBeNull = false)
	private String name;
	
	public ChannelCategory() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
