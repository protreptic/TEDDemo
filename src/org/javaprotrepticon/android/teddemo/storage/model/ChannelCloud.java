package org.javaprotrepticon.android.teddemo.storage.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class ChannelCloud {
	
	@DatabaseField(generatedId = true)
	private Integer id;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private Channel channel;
	
	@DatabaseField(canBeNull = false)
	private String domain;
	
	@DatabaseField(canBeNull = false)
	private Integer port;
	
	@DatabaseField(canBeNull = false)
	private String path;
	
	@DatabaseField(canBeNull = false)
	private String registerProcedure;
	
	@DatabaseField(canBeNull = false)
	private String protocol;
	
	public ChannelCloud() {
		
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

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getRegisterProcedure() {
		return registerProcedure;
	}

	public void setRegisterProcedure(String registerProcedure) {
		this.registerProcedure = registerProcedure;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	
}
