package org.javaprotrepticon.android.teddemo.storage.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author petronic
 *
 */
@DatabaseTable
public class ChannelImage {
	
	@DatabaseField(generatedId = true)
	private Integer id;
	
	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private Channel channel;
	
	@DatabaseField(canBeNull = false)
	private String url;
	
	@DatabaseField(canBeNull = false)
	private String title;
	
	@DatabaseField(canBeNull = false)
	private String link;
	
	@DatabaseField
	private Integer width;
	
	@DatabaseField
	private Integer height;
	
	@DatabaseField(dataType = DataType.LONG_STRING)
	private String description;
	
	public ChannelImage() {
		
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
