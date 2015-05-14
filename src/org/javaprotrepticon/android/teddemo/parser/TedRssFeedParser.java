package org.javaprotrepticon.android.teddemo.parser;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.javaprotrepticon.android.teddemo.storage.Storage;
import org.javaprotrepticon.android.teddemo.storage.model.Channel;
import org.javaprotrepticon.android.teddemo.storage.model.ChannelCategory;
import org.javaprotrepticon.android.teddemo.storage.model.ChannelCloud;
import org.javaprotrepticon.android.teddemo.storage.model.ChannelImage;
import org.javaprotrepticon.android.teddemo.storage.model.ChannelItem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ForeignCollection;

public class TedRssFeedParser {

	private static final String TAG_SOURCE = "source";
	private static final String TAG_GUID = "guid";
	private static final String TAG_ENCLOSURE = "enclosure";
	private static final String TAG_COMMENTS = "comments";
	private static final String TAG_AUTHOR = "author";
	private static final String TAG_CLOUD = "cloud";
	private static final String TAG_IMAGE = "image";
	private static final String TAG_REGISTER_PROCEDURE = "registerProcedure";
	private static final String TAG_PATH = "path";
	private static final String TAG_PORT = "port";
	private static final String TAG_TTL = "ttl";
	private static final String TAG_DOMAIN = "domain";
	private static final String TAG_DOCS = "docs";
	private static final String TAG_PUB_DATE = "pubDate";
	private static final String TAG_WEB_MASTER = "webMaster";
	private static final String TAG_MANAGING_EDITOR = "managingEditor";
	private static final String TAG_COPYRIGHT = "copyright";
	private static final String TAG_TEXT_INPUT = "textInput";
	private static final String TAG_LASTBUILDDATE = "lastBuildDate";
	private static final String TAG_CATEGORY = "category";
	private static final String TAG_LANGUAGE = "language";
	private static final String TAG_GENERATOR = "generator";
	private static final String TAG_PROTOCOL = "protocol";
	private static final String TAG_HEIGHT = "height";
	private static final String TAG_DESCRIPTION = "description";
	private static final String TAG_WIDTH = "width";
	private static final String TAG_SKIP_DAYS = "skipDays";
	private static final String TAG_SKIP_HOURS = "skipHours";
	private static final String TAG_RATING = "rating";
	private static final String TAG_CHANNEL = "channel";
	private static final String TAG_ITEM = "item";
	private static final String TAG_URL = "url";
	private static final String TAG_TITLE = "title";
	private static final String TAG_LINK = "link";
	
	private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	
	private Context mContext;
	
	public TedRssFeedParser(Context context) {
		mContext = context;
	}
	
	public Channel parse() {
		Storage storage = new Storage(mContext); 
		
		@SuppressWarnings("unchecked")
		Dao<Channel, Integer> dao = (Dao<Channel, Integer>) storage.createDao(Channel.class);
		
		Channel channel = new Channel();
		channel.setTitle("");
		channel.setLink(""); 
		channel.setDescription(""); 
		
		try {
			dao.create(channel);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ForeignCollection<ChannelCategory> channelCategories = channel.getCategories();
		ForeignCollection<ChannelItem> channelItems = channel.getItems();
		
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document dom = builder.parse(getInputStream());
			Element root = dom.getDocumentElement();
			NodeList channels = root.getElementsByTagName(TAG_CHANNEL);
			for (int i = 0; i < channels.getLength(); i++) {
				Node channelNode = channels.item(i);
				
				NodeList itemNodes = channelNode.getChildNodes();
				for (int j = 0; j < itemNodes.getLength(); j++) {
					if (itemNodes.item(j).getNodeName().equalsIgnoreCase(TAG_TITLE)) {  
						channel.setTitle(itemNodes.item(j).getFirstChild().getNodeValue()); 
					} else if (itemNodes.item(j).getNodeName().equalsIgnoreCase(TAG_LINK)) {
						channel.setLink(itemNodes.item(j).getFirstChild().getNodeValue()); 
					} else if (itemNodes.item(j).getNodeName().equalsIgnoreCase(TAG_DESCRIPTION)) {
						channel.setDescription(itemNodes.item(j).getFirstChild().getNodeValue()); 
					} else if (itemNodes.item(j).getNodeName().equalsIgnoreCase(TAG_LANGUAGE)) {
						channel.setLanguage(itemNodes.item(j).getFirstChild().getNodeValue()); 
					} else if (itemNodes.item(j).getNodeName().equalsIgnoreCase(TAG_COPYRIGHT)) {
						channel.setCopyright(itemNodes.item(j).getFirstChild().getNodeValue()); 
					} else if (itemNodes.item(j).getNodeName().equalsIgnoreCase(TAG_MANAGING_EDITOR)) {
						channel.setManagingEditor(itemNodes.item(j).getFirstChild().getNodeValue()); 
					} else if (itemNodes.item(j).getNodeName().equalsIgnoreCase(TAG_WEB_MASTER)) {
						channel.setWebMaster(itemNodes.item(j).getFirstChild().getNodeValue()); 
					} else if (itemNodes.item(j).getNodeName().equalsIgnoreCase(TAG_PUB_DATE)) {
						channel.setPubDate(parseRfc822DateString(itemNodes.item(j).getFirstChild().getNodeValue()));  
					} else if (itemNodes.item(j).getNodeName().equalsIgnoreCase(TAG_LASTBUILDDATE)) {
						channel.setLastBuildDate(parseRfc822DateString(itemNodes.item(j).getFirstChild().getNodeValue())); 
					} else if (itemNodes.item(j).getNodeName().equalsIgnoreCase(TAG_CATEGORY)) {
//						ChannelCategory channelCategory = new ChannelCategory();
//						channelCategory.setName(itemNodes.item(j).getFirstChild().getNodeValue()); 
//						
//						channelCategories.add(channelCategory);
					} else if (itemNodes.item(j).getNodeName().equalsIgnoreCase(TAG_GENERATOR)) {
						channel.setGenerator(itemNodes.item(j).getNodeValue()); 
					} else if (itemNodes.item(j).getNodeName().equalsIgnoreCase(TAG_DOCS)) {
						channel.setDocs(itemNodes.item(j).getFirstChild().getNodeValue()); 
					} else if (itemNodes.item(j).getNodeName().equalsIgnoreCase(TAG_CLOUD)) {
						ChannelCloud cloud = new ChannelCloud();
						
						NamedNodeMap cloudAttrs = itemNodes.item(j).getAttributes();
						for (int k = 0; k < cloudAttrs.getLength(); k++) {
							if (cloudAttrs.item(k).getNodeName().equalsIgnoreCase(TAG_DOMAIN)) {  
								cloud.setDomain(cloudAttrs.item(k).getFirstChild().getNodeValue()); 
							} else if (cloudAttrs.item(k).getNodeName().equalsIgnoreCase(TAG_PORT)) {
								cloud.setPort(Integer.valueOf(cloudAttrs.item(k).getFirstChild().getNodeValue())); 
							} else if (cloudAttrs.item(k).getNodeName().equalsIgnoreCase(TAG_PATH)) {
								cloud.setPath(cloudAttrs.item(k).getFirstChild().getNodeValue()); 
							} else if (cloudAttrs.item(k).getNodeName().equalsIgnoreCase(TAG_REGISTER_PROCEDURE)) {
								cloud.setRegisterProcedure(cloudAttrs.item(k).getFirstChild().getNodeValue()); 
							} else if (cloudAttrs.item(k).getNodeName().equalsIgnoreCase(TAG_PROTOCOL)) {
								cloud.setProtocol(cloudAttrs.item(k).getFirstChild().getNodeValue()); 
							}
						}
						
						channel.setCloud(cloud); 
					} else if (itemNodes.item(j).getNodeName().equalsIgnoreCase(TAG_TTL)) {
						channel.setTtl(Integer.valueOf(itemNodes.item(j).getFirstChild().getNodeValue())); 
					} else if (itemNodes.item(j).getNodeName().equalsIgnoreCase(TAG_IMAGE)) {
						ChannelImage image = new ChannelImage();
						NodeList imageNodes = itemNodes.item(j).getChildNodes();
						
						for (int k = 0; k < imageNodes.getLength(); k++) {
							if (imageNodes.item(k).getNodeName().equalsIgnoreCase(TAG_URL)) {  
								image.setUrl(imageNodes.item(k).getFirstChild().getNodeValue()); 
							} else if (imageNodes.item(k).getNodeName().equalsIgnoreCase(TAG_TITLE)) {
								image.setTitle(imageNodes.item(k).getFirstChild().getNodeValue()); 
							} else if (imageNodes.item(k).getNodeName().equalsIgnoreCase(TAG_LINK)) {
								image.setLink(imageNodes.item(k).getFirstChild().getNodeValue()); 
							} else if (imageNodes.item(k).getNodeName().equalsIgnoreCase(TAG_WIDTH)) {
								image.setWidth(Integer.valueOf(imageNodes.item(k).getFirstChild().getNodeValue())); 
							} else if (imageNodes.item(k).getNodeName().equalsIgnoreCase(TAG_HEIGHT)) {
								image.setHeight(Integer.valueOf(imageNodes.item(k).getFirstChild().getNodeValue())); 
							} else if (imageNodes.item(k).getNodeName().equalsIgnoreCase(TAG_DESCRIPTION)) {
								image.setDescription(imageNodes.item(k).getFirstChild().getNodeValue()); 
							}
						}
						
						channel.setImage(image); 
					} else if (itemNodes.item(j).getNodeName().equalsIgnoreCase(TAG_RATING)) {
						channel.setRating(itemNodes.item(j).getFirstChild().getNodeValue()); 
					} else if (itemNodes.item(j).getNodeName().equalsIgnoreCase(TAG_TEXT_INPUT)) {
						
					} else if (itemNodes.item(j).getNodeName().equalsIgnoreCase(TAG_SKIP_HOURS)) {
						channel.setSkipHours(Integer.valueOf(itemNodes.item(j).getFirstChild().getNodeValue())); 
					} else if (itemNodes.item(j).getNodeName().equalsIgnoreCase(TAG_SKIP_DAYS)) {
						channel.setSkipDays(Integer.valueOf(itemNodes.item(j).getFirstChild().getNodeValue())); }
//					} else if (itemNodes.item(j).getNodeName().equalsIgnoreCase(TAG_ITEM)) {
//						ChannelItem channelItem = new ChannelItem();
//						ForeignCollection<ItemCategory> itemCategories = channelItem.getCategories();
//						NodeList imageNodes = itemNodes.item(j).getChildNodes();
//						
//						for (int k = 0; k < imageNodes.getLength(); k++) {
//							if (imageNodes.item(k).getNodeName().equalsIgnoreCase(TAG_TITLE)) {
//								channelItem.setTitle(imageNodes.item(k).getFirstChild().getNodeValue()); 
//							} else if (imageNodes.item(k).getNodeName().equalsIgnoreCase(TAG_LINK)) {
//								channelItem.setLink(imageNodes.item(k).getFirstChild().getNodeValue()); 
//							} else if (imageNodes.item(k).getNodeName().equalsIgnoreCase(TAG_DESCRIPTION)) {
//								channelItem.setDescription(imageNodes.item(k).getFirstChild().getNodeValue()); 
//							} else if (imageNodes.item(k).getNodeName().equalsIgnoreCase(TAG_AUTHOR)) {
//								channelItem.setAuthor(imageNodes.item(k).getFirstChild().getNodeValue()); 
//							} else if (imageNodes.item(k).getNodeName().equalsIgnoreCase(TAG_CATEGORY)) {
//								ItemCategory itemCategory = new ItemCategory();
//								itemCategory.setName(imageNodes.item(k).getFirstChild().getNodeValue()); 
//								
//								itemCategories.add(itemCategory);
//							} else if (imageNodes.item(k).getNodeName().equalsIgnoreCase(TAG_COMMENTS)) {
//								channelItem.setComments(imageNodes.item(k).getFirstChild().getNodeValue());
//							} else if (imageNodes.item(k).getNodeName().equalsIgnoreCase(TAG_ENCLOSURE)) {
//								ItemEnclosure itemEnclosure = new ItemEnclosure();
//								
//								channelItem.setEnclosure(itemEnclosure); 
//							} else if (imageNodes.item(k).getNodeName().equalsIgnoreCase(TAG_GUID)) {
//								ItemGuid itemGuid = new ItemGuid();
//								
//								channelItem.setGuid(itemGuid);
//							} else if (imageNodes.item(k).getNodeName().equalsIgnoreCase(TAG_PUB_DATE)) {
//								channelItem.setPubDate(parseRfc822DateString(imageNodes.item(j).getFirstChild().getNodeValue()));  
//							} else if (imageNodes.item(k).getNodeName().equalsIgnoreCase(TAG_SOURCE)) {
//								ItemSource itemSource = new ItemSource();
//								
//								channelItem.setSource(itemSource);
// 							}
//						}
//						
//						channelItems.add(channelItem);
//					}
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) { 
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		storage.closeConnection();
		
		return channel;
	}
	
	public static Date parseRfc822DateString(String dateString) {
		Date date = null;
		
		try {
			date = new Date(new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US).parse(dateString).getTime()); 
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}
	
	private InputStream getInputStream() {
		try {
			return new URL("http://www.ted.com/talks/rss").openConnection().getInputStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	} 
	
}
