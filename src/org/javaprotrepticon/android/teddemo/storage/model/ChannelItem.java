package org.javaprotrepticon.android.teddemo.storage.model;

import java.sql.Date;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author petronic
 *
 */
@DatabaseTable
public class ChannelItem {

	@DatabaseField(generatedId = true)
	private Integer id;

	/**
	 * @see Channel
	 */
	@DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
	private Channel channel;

	/**
	 * The title of the item.
	 */
	@DatabaseField(canBeNull = false)
	private String title;

	/**
	 * The URL of the item.
	 */
	@DatabaseField(canBeNull = false)
	private String link;

	/**
	 * The item synopsis.
	 */
	@DatabaseField(canBeNull = false, dataType = DataType.LONG_STRING)
	private String description;

	/**
	 * It's the email address of the author of the item. For newspapers and
	 * magazines syndicating via RSS, the author is the person who wrote the
	 * article that the <item> describes. For collaborative weblogs, the author
	 * of the item might be different from the managing editor or webmaster. For
	 * a weblog authored by a single individual it would make sense to omit the
	 * <author> element. <a href=
	 * "http://cyber.law.harvard.edu/rss/rss.html#ltauthorgtSubelementOfLtitemgt"
	 * >More</a>.
	 */
	@DatabaseField
	private String author;

	/**
	 * Includes the item in one or more categories. <a href=
	 * "http://cyber.law.harvard.edu/rss/rss.html#ltcategorygtSubelementOfLtitemgt"
	 * >More</a>.
	 * 
	 * @see ItemCategory
	 */
	@ForeignCollectionField(eager = false)
	private ForeignCollection<ItemCategory> categories;

	/**
	 * If present, it is the url of the comments page for the item. <a href=
	 * "http://cyber.law.harvard.edu/rss/rss.html#ltcommentsgtSubelementOfLtitemgt"
	 * >More</a>.
	 */
	@DatabaseField
	private String comments;

	/**
	 * Describes a media object that is attached to the item. <a href=
	 * "http://cyber.law.harvard.edu/rss/rss.html#ltenclosuregtSubelementOfLtitemgt"
	 * >More</a>.
	 * 
	 * @see ItemEnclosure
	 */
	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private ItemEnclosure enclosure;

	/**
	 * A string that uniquely identifies the item. <a href=
	 * "http://cyber.law.harvard.edu/rss/rss.html#ltguidgtSubelementOfLtitemgt"
	 * >More</a>.
	 * 
	 * @see ItemGuid
	 */
	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private ItemGuid guid;
	
	/**
	 * Indicates when the item was published. <a href=
	 * "http://cyber.law.harvard.edu/rss/rss.html#ltpubdategtSubelementOfLtitemgt"
	 * >More</a>.
	 */
	@DatabaseField
	private Date pubDate;

	/**
	 * The RSS channel that the item came from. <a href=
	 * "http://cyber.law.harvard.edu/rss/rss.html#ltsourcegtSubelementOfLtitemgt"
	 * >More</a>.
	 * 
	 * @see ItemSource
	 */
	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private ItemSource source;

	public ChannelItem() {
		
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public ForeignCollection<ItemCategory> getCategories() {
		return categories;
	}

	public void setCategories(ForeignCollection<ItemCategory> categories) {
		this.categories = categories;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public ItemEnclosure getEnclosure() {
		return enclosure;
	}

	public void setEnclosure(ItemEnclosure enclosure) {
		this.enclosure = enclosure;
	}

	public ItemGuid getGuid() {
		return guid;
	}

	public void setGuid(ItemGuid guid) {
		this.guid = guid;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public ItemSource getSource() {
		return source;
	}

	public void setSource(ItemSource source) {
		this.source = source;
	}

}
