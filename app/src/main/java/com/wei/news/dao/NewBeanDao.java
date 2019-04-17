package com.wei.news.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.wei.news.model.NewBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "NEW_BEAN".
*/
public class NewBeanDao extends AbstractDao<NewBean, String> {

    public static final String TABLENAME = "NEW_BEAN";

    /**
     * Properties of entity NewBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Uniquekey = new Property(0, String.class, "uniquekey", true, "UNIQUEKEY");
        public final static Property Title = new Property(1, String.class, "title", false, "TITLE");
        public final static Property Date = new Property(2, String.class, "date", false, "DATE");
        public final static Property Category = new Property(3, String.class, "category", false, "CATEGORY");
        public final static Property AuthorName = new Property(4, String.class, "authorName", false, "AUTHOR_NAME");
        public final static Property Url = new Property(5, String.class, "url", false, "URL");
        public final static Property ThumbnailPicS = new Property(6, String.class, "thumbnailPicS", false, "THUMBNAIL_PIC_S");
        public final static Property ThumbnailPicS02 = new Property(7, String.class, "thumbnailPicS02", false, "THUMBNAIL_PIC_S02");
        public final static Property ThumbnailPicS03 = new Property(8, String.class, "thumbnailPicS03", false, "THUMBNAIL_PIC_S03");
    }


    public NewBeanDao(DaoConfig config) {
        super(config);
    }
    
    public NewBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NEW_BEAN\" (" + //
                "\"UNIQUEKEY\" TEXT PRIMARY KEY NOT NULL ," + // 0: uniquekey
                "\"TITLE\" TEXT," + // 1: title
                "\"DATE\" TEXT," + // 2: date
                "\"CATEGORY\" TEXT," + // 3: category
                "\"AUTHOR_NAME\" TEXT," + // 4: authorName
                "\"URL\" TEXT," + // 5: url
                "\"THUMBNAIL_PIC_S\" TEXT," + // 6: thumbnailPicS
                "\"THUMBNAIL_PIC_S02\" TEXT," + // 7: thumbnailPicS02
                "\"THUMBNAIL_PIC_S03\" TEXT);"); // 8: thumbnailPicS03
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NEW_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, NewBean entity) {
        stmt.clearBindings();
 
        String uniquekey = entity.getUniquekey();
        if (uniquekey != null) {
            stmt.bindString(1, uniquekey);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(3, date);
        }
 
        String category = entity.getCategory();
        if (category != null) {
            stmt.bindString(4, category);
        }
 
        String authorName = entity.getAuthorName();
        if (authorName != null) {
            stmt.bindString(5, authorName);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(6, url);
        }
 
        String thumbnailPicS = entity.getThumbnailPicS();
        if (thumbnailPicS != null) {
            stmt.bindString(7, thumbnailPicS);
        }
 
        String thumbnailPicS02 = entity.getThumbnailPicS02();
        if (thumbnailPicS02 != null) {
            stmt.bindString(8, thumbnailPicS02);
        }
 
        String thumbnailPicS03 = entity.getThumbnailPicS03();
        if (thumbnailPicS03 != null) {
            stmt.bindString(9, thumbnailPicS03);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, NewBean entity) {
        stmt.clearBindings();
 
        String uniquekey = entity.getUniquekey();
        if (uniquekey != null) {
            stmt.bindString(1, uniquekey);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(3, date);
        }
 
        String category = entity.getCategory();
        if (category != null) {
            stmt.bindString(4, category);
        }
 
        String authorName = entity.getAuthorName();
        if (authorName != null) {
            stmt.bindString(5, authorName);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(6, url);
        }
 
        String thumbnailPicS = entity.getThumbnailPicS();
        if (thumbnailPicS != null) {
            stmt.bindString(7, thumbnailPicS);
        }
 
        String thumbnailPicS02 = entity.getThumbnailPicS02();
        if (thumbnailPicS02 != null) {
            stmt.bindString(8, thumbnailPicS02);
        }
 
        String thumbnailPicS03 = entity.getThumbnailPicS03();
        if (thumbnailPicS03 != null) {
            stmt.bindString(9, thumbnailPicS03);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public NewBean readEntity(Cursor cursor, int offset) {
        NewBean entity = new NewBean( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // uniquekey
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // title
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // date
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // category
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // authorName
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // url
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // thumbnailPicS
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // thumbnailPicS02
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // thumbnailPicS03
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, NewBean entity, int offset) {
        entity.setUniquekey(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setTitle(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setDate(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCategory(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setAuthorName(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setUrl(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setThumbnailPicS(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setThumbnailPicS02(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setThumbnailPicS03(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    @Override
    protected final String updateKeyAfterInsert(NewBean entity, long rowId) {
        return entity.getUniquekey();
    }
    
    @Override
    public String getKey(NewBean entity) {
        if(entity != null) {
            return entity.getUniquekey();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(NewBean entity) {
        return entity.getUniquekey() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
