package com.ag.documentscanner.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.ag.documentscanner.data.local.converters.DataTypeConverter;
import com.ag.documentscanner.data.model.Pdf;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalStateException;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class PdfDao_Impl implements PdfDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Pdf> __insertionAdapterOfPdf;

  private final DataTypeConverter __dataTypeConverter = new DataTypeConverter();

  private final EntityDeletionOrUpdateAdapter<Pdf> __deletionAdapterOfPdf;

  private final EntityDeletionOrUpdateAdapter<Pdf> __updateAdapterOfPdf;

  public PdfDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPdf = new EntityInsertionAdapter<Pdf>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `pdfTable` (`pdfId`,`name`,`size`,`lastModifiedTime`) VALUES (?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Pdf entity) {
        statement.bindString(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getSize() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getSize());
        }
        final Long _tmp = __dataTypeConverter.dateToTimeStamp(entity.getLastModifiedTime());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, _tmp);
        }
      }
    };
    this.__deletionAdapterOfPdf = new EntityDeletionOrUpdateAdapter<Pdf>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `pdfTable` WHERE `pdfId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Pdf entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__updateAdapterOfPdf = new EntityDeletionOrUpdateAdapter<Pdf>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `pdfTable` SET `pdfId` = ?,`name` = ?,`size` = ?,`lastModifiedTime` = ? WHERE `pdfId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Pdf entity) {
        statement.bindString(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getSize() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getSize());
        }
        final Long _tmp = __dataTypeConverter.dateToTimeStamp(entity.getLastModifiedTime());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, _tmp);
        }
        statement.bindString(5, entity.getId());
      }
    };
  }

  @Override
  public Object insertPdf(final Pdf pdf, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfPdf.insertAndReturnId(pdf);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deletePdf(final Pdf pdf, final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total += __deletionAdapterOfPdf.handle(pdf);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updatePdf(final Pdf pdf, final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total += __updateAdapterOfPdf.handle(pdf);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Pdf>> getAllPdf() {
    final String _sql = "SELECT * FROM pdfTable";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"pdfTable"}, new Callable<List<Pdf>>() {
      @Override
      @NonNull
      public List<Pdf> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "pdfId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfSize = CursorUtil.getColumnIndexOrThrow(_cursor, "size");
          final int _cursorIndexOfLastModifiedTime = CursorUtil.getColumnIndexOrThrow(_cursor, "lastModifiedTime");
          final List<Pdf> _result = new ArrayList<Pdf>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Pdf _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpSize;
            if (_cursor.isNull(_cursorIndexOfSize)) {
              _tmpSize = null;
            } else {
              _tmpSize = _cursor.getString(_cursorIndexOfSize);
            }
            final Date _tmpLastModifiedTime;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfLastModifiedTime)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfLastModifiedTime);
            }
            final Date _tmp_1 = __dataTypeConverter.fromTimeStamp(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.util.Date', but it was NULL.");
            } else {
              _tmpLastModifiedTime = _tmp_1;
            }
            _item = new Pdf(_tmpId,_tmpName,_tmpSize,_tmpLastModifiedTime);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
