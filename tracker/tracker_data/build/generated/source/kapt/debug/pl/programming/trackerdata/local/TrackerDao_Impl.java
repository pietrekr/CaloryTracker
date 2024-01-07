package pl.programming.trackerdata.local;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;
import pl.programming.trackerdata.local.entity.TrackedFoodEntity;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class TrackerDao_Impl implements TrackerDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TrackedFoodEntity> __insertionAdapterOfTrackedFoodEntity;

  private final EntityDeletionOrUpdateAdapter<TrackedFoodEntity> __deletionAdapterOfTrackedFoodEntity;

  public TrackerDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTrackedFoodEntity = new EntityInsertionAdapter<TrackedFoodEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `TrackedFoodEntity` (`name`,`carbs`,`protein`,`fat`,`imageUrl`,`type`,`amount`,`dayOfMonth`,`month`,`year`,`calories`,`id`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TrackedFoodEntity value) {
        if (value.getName() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getName());
        }
        stmt.bindLong(2, value.getCarbs());
        stmt.bindLong(3, value.getProtein());
        stmt.bindLong(4, value.getFat());
        if (value.getImageUrl() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getImageUrl());
        }
        if (value.getType() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getType());
        }
        stmt.bindLong(7, value.getAmount());
        stmt.bindLong(8, value.getDayOfMonth());
        stmt.bindLong(9, value.getMonth());
        stmt.bindLong(10, value.getYear());
        stmt.bindLong(11, value.getCalories());
        if (value.getId() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindLong(12, value.getId());
        }
      }
    };
    this.__deletionAdapterOfTrackedFoodEntity = new EntityDeletionOrUpdateAdapter<TrackedFoodEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `TrackedFoodEntity` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TrackedFoodEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
      }
    };
  }

  @Override
  public Object insertTrackedFood(final TrackedFoodEntity trackedFoodEntity,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTrackedFoodEntity.insert(trackedFoodEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteTrackedFood(final TrackedFoodEntity trackedFoodEntity,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfTrackedFoodEntity.handle(trackedFoodEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<TrackedFoodEntity>> getFoodForDate(final int day, final int month,
      final int year) {
    final String _sql = "\n"
            + "            SELECT * FROM trackedfoodentity\n"
            + "            WHERE dayOfMonth = ? AND month = ? AND year = ?\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, day);
    _argIndex = 2;
    _statement.bindLong(_argIndex, month);
    _argIndex = 3;
    _statement.bindLong(_argIndex, year);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"trackedfoodentity"}, new Callable<List<TrackedFoodEntity>>() {
      @Override
      public List<TrackedFoodEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCarbs = CursorUtil.getColumnIndexOrThrow(_cursor, "carbs");
          final int _cursorIndexOfProtein = CursorUtil.getColumnIndexOrThrow(_cursor, "protein");
          final int _cursorIndexOfFat = CursorUtil.getColumnIndexOrThrow(_cursor, "fat");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfDayOfMonth = CursorUtil.getColumnIndexOrThrow(_cursor, "dayOfMonth");
          final int _cursorIndexOfMonth = CursorUtil.getColumnIndexOrThrow(_cursor, "month");
          final int _cursorIndexOfYear = CursorUtil.getColumnIndexOrThrow(_cursor, "year");
          final int _cursorIndexOfCalories = CursorUtil.getColumnIndexOrThrow(_cursor, "calories");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final List<TrackedFoodEntity> _result = new ArrayList<TrackedFoodEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final TrackedFoodEntity _item;
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final int _tmpCarbs;
            _tmpCarbs = _cursor.getInt(_cursorIndexOfCarbs);
            final int _tmpProtein;
            _tmpProtein = _cursor.getInt(_cursorIndexOfProtein);
            final int _tmpFat;
            _tmpFat = _cursor.getInt(_cursorIndexOfFat);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final int _tmpAmount;
            _tmpAmount = _cursor.getInt(_cursorIndexOfAmount);
            final int _tmpDayOfMonth;
            _tmpDayOfMonth = _cursor.getInt(_cursorIndexOfDayOfMonth);
            final int _tmpMonth;
            _tmpMonth = _cursor.getInt(_cursorIndexOfMonth);
            final int _tmpYear;
            _tmpYear = _cursor.getInt(_cursorIndexOfYear);
            final int _tmpCalories;
            _tmpCalories = _cursor.getInt(_cursorIndexOfCalories);
            final Integer _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getInt(_cursorIndexOfId);
            }
            _item = new TrackedFoodEntity(_tmpName,_tmpCarbs,_tmpProtein,_tmpFat,_tmpImageUrl,_tmpType,_tmpAmount,_tmpDayOfMonth,_tmpMonth,_tmpYear,_tmpCalories,_tmpId);
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

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
