package org.javaprotrepticon.android.teddemo.storage.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author petronic
 *
 */
@DatabaseTable
public class ChannelTextInput {

	@DatabaseField(generatedId = true)
	private Integer id;
	
	@DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
	private Channel channel;
	
	@DatabaseField(canBeNull = false)
	private String title;
	
	@DatabaseField(canBeNull = false, dataType = DataType.LONG_STRING)
	private String description;
	
	@DatabaseField(canBeNull = false)
	private String name;
	
	@DatabaseField(canBeNull = false)
	private String link;
	
	public ChannelTextInput() {
		
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
}
