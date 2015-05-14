package org.javaprotrepticon.android.teddemo.storage;

import java.io.File;
import java.sql.SQLException;

import org.javaprotrepticon.android.androidutils.Apps;
import org.javaprotrepticon.android.teddemo.storage.model.Channel;
import org.javaprotrepticon.android.teddemo.storage.model.ChannelCategory;
import org.javaprotrepticon.android.teddemo.storage.model.ChannelCloud;
import org.javaprotrepticon.android.teddemo.storage.model.ItemEnclosure;
import org.javaprotrepticon.android.teddemo.storage.model.ItemGuid;
import org.javaprotrepticon.android.teddemo.storage.model.ChannelImage;
import org.javaprotrepticon.android.teddemo.storage.model.ChannelItem;
import org.javaprotrepticon.android.teddemo.storage.model.ItemCategory;
import org.javaprotrepticon.android.teddemo.storage.model.ItemSource;
import org.javaprotrepticon.android.teddemo.storage.model.ChannelTextInput;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class Storage {
	
	static {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private JdbcPooledConnectionSource mConnection;
	
	public static void dropStorage(Context context) {
		File file = new File(context.getDir("data", Context.MODE_PRIVATE).getPath() + "/" + context.getPackageName() + "-" + Apps.getVersionName(context) + ".h2.db");
		
		file.delete();
	}
	
	public static void initialize(Context context) {
		Storage storage = new Storage(context);
		
		try {
			TableUtils.createTableIfNotExists(storage.getConnection(), ChannelTextInput.class);
			TableUtils.createTableIfNotExists(storage.getConnection(), ChannelCategory.class);
			TableUtils.createTableIfNotExists(storage.getConnection(), ChannelCloud.class);
			TableUtils.createTableIfNotExists(storage.getConnection(), ItemEnclosure.class);
			TableUtils.createTableIfNotExists(storage.getConnection(), ItemGuid.class);
			TableUtils.createTableIfNotExists(storage.getConnection(), ChannelImage.class);
			TableUtils.createTableIfNotExists(storage.getConnection(), ItemCategory.class);
			TableUtils.createTableIfNotExists(storage.getConnection(), ItemSource.class);
			TableUtils.createTableIfNotExists(storage.getConnection(), ChannelItem.class);
			TableUtils.createTableIfNotExists(storage.getConnection(), Channel.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		storage.closeConnection();
	}
	
	public Storage(Context context) {
		String storageFolder = context.getDir("data", Context.MODE_PRIVATE).getPath() + "/" + context.getPackageName() + "-" + Apps.getVersionName(context);
		
		try {
			mConnection = new JdbcPooledConnectionSource("jdbc:h2:" + storageFolder + ";AUTO_SERVER=TRUE;IGNORECASE=TRUE;"); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public JdbcPooledConnectionSource getConnection() {
		return mConnection;
	}
	
	public void closeConnection() {
		try {
			mConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Dao<?, Integer> createDao(Class<?> type) {
		Dao<?, Integer> result = null;
		
		try {
			result = (Dao<?, Integer>) DaoManager.createDao(mConnection, type);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
