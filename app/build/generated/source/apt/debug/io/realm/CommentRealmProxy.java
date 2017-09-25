package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.LinkView;
import io.realm.internal.OsObject;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import io.realm.internal.android.JsonUtils;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CommentRealmProxy extends com.minh.findtheshipper.models.Comment
    implements RealmObjectProxy, CommentRealmProxyInterface {

    static final class CommentColumnInfo extends ColumnInfo {
        long idCommentIndex;
        long ContentIndex;
        long dateTimeIndex;
        long userIndex;

        CommentColumnInfo(SharedRealm realm, Table table) {
            super(4);
            this.idCommentIndex = addColumnDetails(table, "idComment", RealmFieldType.STRING);
            this.ContentIndex = addColumnDetails(table, "Content", RealmFieldType.STRING);
            this.dateTimeIndex = addColumnDetails(table, "dateTime", RealmFieldType.STRING);
            this.userIndex = addColumnDetails(table, "user", RealmFieldType.OBJECT);
        }

        CommentColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new CommentColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final CommentColumnInfo src = (CommentColumnInfo) rawSrc;
            final CommentColumnInfo dst = (CommentColumnInfo) rawDst;
            dst.idCommentIndex = src.idCommentIndex;
            dst.ContentIndex = src.ContentIndex;
            dst.dateTimeIndex = src.dateTimeIndex;
            dst.userIndex = src.userIndex;
        }
    }

    private CommentColumnInfo columnInfo;
    private ProxyState<com.minh.findtheshipper.models.Comment> proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("idComment");
        fieldNames.add("Content");
        fieldNames.add("dateTime");
        fieldNames.add("user");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    CommentRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (CommentColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.minh.findtheshipper.models.Comment>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$idComment() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.idCommentIndex);
    }

    @Override
    public void realmSet$idComment(String value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'idComment' cannot be changed after object was created.");
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$Content() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.ContentIndex);
    }

    @Override
    public void realmSet$Content(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.ContentIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.ContentIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.ContentIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.ContentIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$dateTime() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.dateTimeIndex);
    }

    @Override
    public void realmSet$dateTime(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.dateTimeIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.dateTimeIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.dateTimeIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.dateTimeIndex, value);
    }

    @Override
    public com.minh.findtheshipper.models.User realmGet$user() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNullLink(columnInfo.userIndex)) {
            return null;
        }
        return proxyState.getRealm$realm().get(com.minh.findtheshipper.models.User.class, proxyState.getRow$realm().getLink(columnInfo.userIndex), false, Collections.<String>emptyList());
    }

    @Override
    public void realmSet$user(com.minh.findtheshipper.models.User value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("user")) {
                return;
            }
            if (value != null && !RealmObject.isManaged(value)) {
                value = ((Realm) proxyState.getRealm$realm()).copyToRealm(value);
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                // Table#nullifyLink() does not support default value. Just using Row.
                row.nullifyLink(columnInfo.userIndex);
                return;
            }
            if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            }
            if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            }
            row.getTable().setLink(columnInfo.userIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().nullifyLink(columnInfo.userIndex);
            return;
        }
        if (!(RealmObject.isManaged(value) && RealmObject.isValid(value))) {
            throw new IllegalArgumentException("'value' is not a valid managed object.");
        }
        if (((RealmObjectProxy)value).realmGet$proxyState().getRealm$realm() != proxyState.getRealm$realm()) {
            throw new IllegalArgumentException("'value' belongs to a different Realm.");
        }
        proxyState.getRow$realm().setLink(columnInfo.userIndex, ((RealmObjectProxy)value).realmGet$proxyState().getRow$realm().getIndex());
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("Comment")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("Comment");
            realmObjectSchema.add("idComment", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("Content", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("dateTime", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            if (!realmSchema.contains("User")) {
                UserRealmProxy.createRealmObjectSchema(realmSchema);
            }
            realmObjectSchema.add("user", RealmFieldType.OBJECT, realmSchema.get("User"));
            return realmObjectSchema;
        }
        return realmSchema.get("Comment");
    }

    public static CommentColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (!sharedRealm.hasTable("class_Comment")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'Comment' class is missing from the schema for this Realm.");
        }
        Table table = sharedRealm.getTable("class_Comment");
        final long columnCount = table.getColumnCount();
        if (columnCount != 4) {
            if (columnCount < 4) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 4 but was " + columnCount);
            }
            if (allowExtraColumns) {
                RealmLog.debug("Field count is more than expected - expected 4 but was %1$d", columnCount);
            } else {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 4 but was " + columnCount);
            }
        }
        Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
        for (long i = 0; i < columnCount; i++) {
            columnTypes.put(table.getColumnName(i), table.getColumnType(i));
        }

        final CommentColumnInfo columnInfo = new CommentColumnInfo(sharedRealm, table);

        if (!table.hasPrimaryKey()) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary key not defined for field 'idComment' in existing Realm file. @PrimaryKey was added.");
        } else {
            if (table.getPrimaryKey() != columnInfo.idCommentIndex) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary Key annotation definition was changed, from field " + table.getColumnName(table.getPrimaryKey()) + " to field idComment");
            }
        }

        if (!columnTypes.containsKey("idComment")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'idComment' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("idComment") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'idComment' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.idCommentIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(),"@PrimaryKey field 'idComment' does not support null values in the existing Realm file. Migrate using RealmObjectSchema.setNullable(), or mark the field as @Required.");
        }
        if (!table.hasSearchIndex(table.getColumnIndex("idComment"))) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Index not defined for field 'idComment' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
        }
        if (!columnTypes.containsKey("Content")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'Content' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("Content") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'Content' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.ContentIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'Content' is required. Either set @Required to field 'Content' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("dateTime")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'dateTime' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("dateTime") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'dateTime' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.dateTimeIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'dateTime' is required. Either set @Required to field 'dateTime' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("user")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'user' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("user") != RealmFieldType.OBJECT) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'User' for field 'user'");
        }
        if (!sharedRealm.hasTable("class_User")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing class 'class_User' for field 'user'");
        }
        Table table_3 = sharedRealm.getTable("class_User");
        if (!table.getLinkTarget(columnInfo.userIndex).hasSameSchema(table_3)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid RealmObject for field 'user': '" + table.getLinkTarget(columnInfo.userIndex).getName() + "' expected - was '" + table_3.getName() + "'");
        }

        return columnInfo;
    }

    public static String getTableName() {
        return "class_Comment";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.minh.findtheshipper.models.Comment createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(1);
        com.minh.findtheshipper.models.Comment obj = null;
        if (update) {
            Table table = realm.getTable(com.minh.findtheshipper.models.Comment.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = Table.NO_MATCH;
            if (json.isNull("idComment")) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("idComment"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.minh.findtheshipper.models.Comment.class), false, Collections.<String> emptyList());
                    obj = new io.realm.CommentRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("user")) {
                excludeFields.add("user");
            }
            if (json.has("idComment")) {
                if (json.isNull("idComment")) {
                    obj = (io.realm.CommentRealmProxy) realm.createObjectInternal(com.minh.findtheshipper.models.Comment.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.CommentRealmProxy) realm.createObjectInternal(com.minh.findtheshipper.models.Comment.class, json.getString("idComment"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'idComment'.");
            }
        }
        if (json.has("Content")) {
            if (json.isNull("Content")) {
                ((CommentRealmProxyInterface) obj).realmSet$Content(null);
            } else {
                ((CommentRealmProxyInterface) obj).realmSet$Content((String) json.getString("Content"));
            }
        }
        if (json.has("dateTime")) {
            if (json.isNull("dateTime")) {
                ((CommentRealmProxyInterface) obj).realmSet$dateTime(null);
            } else {
                ((CommentRealmProxyInterface) obj).realmSet$dateTime((String) json.getString("dateTime"));
            }
        }
        if (json.has("user")) {
            if (json.isNull("user")) {
                ((CommentRealmProxyInterface) obj).realmSet$user(null);
            } else {
                com.minh.findtheshipper.models.User userObj = UserRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("user"), update);
                ((CommentRealmProxyInterface) obj).realmSet$user(userObj);
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.minh.findtheshipper.models.Comment createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.minh.findtheshipper.models.Comment obj = new com.minh.findtheshipper.models.Comment();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("idComment")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((CommentRealmProxyInterface) obj).realmSet$idComment(null);
                } else {
                    ((CommentRealmProxyInterface) obj).realmSet$idComment((String) reader.nextString());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("Content")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((CommentRealmProxyInterface) obj).realmSet$Content(null);
                } else {
                    ((CommentRealmProxyInterface) obj).realmSet$Content((String) reader.nextString());
                }
            } else if (name.equals("dateTime")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((CommentRealmProxyInterface) obj).realmSet$dateTime(null);
                } else {
                    ((CommentRealmProxyInterface) obj).realmSet$dateTime((String) reader.nextString());
                }
            } else if (name.equals("user")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((CommentRealmProxyInterface) obj).realmSet$user(null);
                } else {
                    com.minh.findtheshipper.models.User userObj = UserRealmProxy.createUsingJsonStream(realm, reader);
                    ((CommentRealmProxyInterface) obj).realmSet$user(userObj);
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'idComment'.");
        }
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.minh.findtheshipper.models.Comment copyOrUpdate(Realm realm, com.minh.findtheshipper.models.Comment object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.minh.findtheshipper.models.Comment) cachedRealmObject;
        } else {
            com.minh.findtheshipper.models.Comment realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.minh.findtheshipper.models.Comment.class);
                long pkColumnIndex = table.getPrimaryKey();
                String value = ((CommentRealmProxyInterface) object).realmGet$idComment();
                long rowIndex = Table.NO_MATCH;
                if (value == null) {
                    rowIndex = table.findFirstNull(pkColumnIndex);
                } else {
                    rowIndex = table.findFirstString(pkColumnIndex, value);
                }
                if (rowIndex != Table.NO_MATCH) {
                    try {
                        objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.minh.findtheshipper.models.Comment.class), false, Collections.<String> emptyList());
                        realmObject = new io.realm.CommentRealmProxy();
                        cache.put(object, (RealmObjectProxy) realmObject);
                    } finally {
                        objectContext.clear();
                    }
                } else {
                    canUpdate = false;
                }
            }

            if (canUpdate) {
                return update(realm, realmObject, object, cache);
            } else {
                return copy(realm, object, update, cache);
            }
        }
    }

    public static com.minh.findtheshipper.models.Comment copy(Realm realm, com.minh.findtheshipper.models.Comment newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.minh.findtheshipper.models.Comment) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.minh.findtheshipper.models.Comment realmObject = realm.createObjectInternal(com.minh.findtheshipper.models.Comment.class, ((CommentRealmProxyInterface) newObject).realmGet$idComment(), false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((CommentRealmProxyInterface) realmObject).realmSet$Content(((CommentRealmProxyInterface) newObject).realmGet$Content());
            ((CommentRealmProxyInterface) realmObject).realmSet$dateTime(((CommentRealmProxyInterface) newObject).realmGet$dateTime());

            com.minh.findtheshipper.models.User userObj = ((CommentRealmProxyInterface) newObject).realmGet$user();
            if (userObj != null) {
                com.minh.findtheshipper.models.User cacheuser = (com.minh.findtheshipper.models.User) cache.get(userObj);
                if (cacheuser != null) {
                    ((CommentRealmProxyInterface) realmObject).realmSet$user(cacheuser);
                } else {
                    ((CommentRealmProxyInterface) realmObject).realmSet$user(UserRealmProxy.copyOrUpdate(realm, userObj, update, cache));
                }
            } else {
                ((CommentRealmProxyInterface) realmObject).realmSet$user(null);
            }
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.minh.findtheshipper.models.Comment object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.minh.findtheshipper.models.Comment.class);
        long tableNativePtr = table.getNativePtr();
        CommentColumnInfo columnInfo = (CommentColumnInfo) realm.schema.getColumnInfo(com.minh.findtheshipper.models.Comment.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((CommentRealmProxyInterface) object).realmGet$idComment();
        long rowIndex = Table.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(realm.sharedRealm, table, primaryKeyValue);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$Content = ((CommentRealmProxyInterface)object).realmGet$Content();
        if (realmGet$Content != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.ContentIndex, rowIndex, realmGet$Content, false);
        }
        String realmGet$dateTime = ((CommentRealmProxyInterface)object).realmGet$dateTime();
        if (realmGet$dateTime != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.dateTimeIndex, rowIndex, realmGet$dateTime, false);
        }

        com.minh.findtheshipper.models.User userObj = ((CommentRealmProxyInterface) object).realmGet$user();
        if (userObj != null) {
            Long cacheuser = cache.get(userObj);
            if (cacheuser == null) {
                cacheuser = UserRealmProxy.insert(realm, userObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.userIndex, rowIndex, cacheuser, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.minh.findtheshipper.models.Comment.class);
        long tableNativePtr = table.getNativePtr();
        CommentColumnInfo columnInfo = (CommentColumnInfo) realm.schema.getColumnInfo(com.minh.findtheshipper.models.Comment.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.minh.findtheshipper.models.Comment object = null;
        while (objects.hasNext()) {
            object = (com.minh.findtheshipper.models.Comment) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((CommentRealmProxyInterface) object).realmGet$idComment();
                long rowIndex = Table.NO_MATCH;
                if (primaryKeyValue == null) {
                    rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
                } else {
                    rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
                }
                if (rowIndex == Table.NO_MATCH) {
                    rowIndex = OsObject.createRowWithPrimaryKey(realm.sharedRealm, table, primaryKeyValue);
                } else {
                    Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
                }
                cache.put(object, rowIndex);
                String realmGet$Content = ((CommentRealmProxyInterface)object).realmGet$Content();
                if (realmGet$Content != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.ContentIndex, rowIndex, realmGet$Content, false);
                }
                String realmGet$dateTime = ((CommentRealmProxyInterface)object).realmGet$dateTime();
                if (realmGet$dateTime != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.dateTimeIndex, rowIndex, realmGet$dateTime, false);
                }

                com.minh.findtheshipper.models.User userObj = ((CommentRealmProxyInterface) object).realmGet$user();
                if (userObj != null) {
                    Long cacheuser = cache.get(userObj);
                    if (cacheuser == null) {
                        cacheuser = UserRealmProxy.insert(realm, userObj, cache);
                    }
                    table.setLink(columnInfo.userIndex, rowIndex, cacheuser, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.minh.findtheshipper.models.Comment object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.minh.findtheshipper.models.Comment.class);
        long tableNativePtr = table.getNativePtr();
        CommentColumnInfo columnInfo = (CommentColumnInfo) realm.schema.getColumnInfo(com.minh.findtheshipper.models.Comment.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((CommentRealmProxyInterface) object).realmGet$idComment();
        long rowIndex = Table.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(realm.sharedRealm, table, primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$Content = ((CommentRealmProxyInterface)object).realmGet$Content();
        if (realmGet$Content != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.ContentIndex, rowIndex, realmGet$Content, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.ContentIndex, rowIndex, false);
        }
        String realmGet$dateTime = ((CommentRealmProxyInterface)object).realmGet$dateTime();
        if (realmGet$dateTime != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.dateTimeIndex, rowIndex, realmGet$dateTime, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.dateTimeIndex, rowIndex, false);
        }

        com.minh.findtheshipper.models.User userObj = ((CommentRealmProxyInterface) object).realmGet$user();
        if (userObj != null) {
            Long cacheuser = cache.get(userObj);
            if (cacheuser == null) {
                cacheuser = UserRealmProxy.insertOrUpdate(realm, userObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.userIndex, rowIndex, cacheuser, false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.userIndex, rowIndex);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.minh.findtheshipper.models.Comment.class);
        long tableNativePtr = table.getNativePtr();
        CommentColumnInfo columnInfo = (CommentColumnInfo) realm.schema.getColumnInfo(com.minh.findtheshipper.models.Comment.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.minh.findtheshipper.models.Comment object = null;
        while (objects.hasNext()) {
            object = (com.minh.findtheshipper.models.Comment) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((CommentRealmProxyInterface) object).realmGet$idComment();
                long rowIndex = Table.NO_MATCH;
                if (primaryKeyValue == null) {
                    rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
                } else {
                    rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
                }
                if (rowIndex == Table.NO_MATCH) {
                    rowIndex = OsObject.createRowWithPrimaryKey(realm.sharedRealm, table, primaryKeyValue);
                }
                cache.put(object, rowIndex);
                String realmGet$Content = ((CommentRealmProxyInterface)object).realmGet$Content();
                if (realmGet$Content != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.ContentIndex, rowIndex, realmGet$Content, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.ContentIndex, rowIndex, false);
                }
                String realmGet$dateTime = ((CommentRealmProxyInterface)object).realmGet$dateTime();
                if (realmGet$dateTime != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.dateTimeIndex, rowIndex, realmGet$dateTime, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.dateTimeIndex, rowIndex, false);
                }

                com.minh.findtheshipper.models.User userObj = ((CommentRealmProxyInterface) object).realmGet$user();
                if (userObj != null) {
                    Long cacheuser = cache.get(userObj);
                    if (cacheuser == null) {
                        cacheuser = UserRealmProxy.insertOrUpdate(realm, userObj, cache);
                    }
                    Table.nativeSetLink(tableNativePtr, columnInfo.userIndex, rowIndex, cacheuser, false);
                } else {
                    Table.nativeNullifyLink(tableNativePtr, columnInfo.userIndex, rowIndex);
                }
            }
        }
    }

    public static com.minh.findtheshipper.models.Comment createDetachedCopy(com.minh.findtheshipper.models.Comment realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.minh.findtheshipper.models.Comment unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.minh.findtheshipper.models.Comment)cachedObject.object;
            } else {
                unmanagedObject = (com.minh.findtheshipper.models.Comment)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.minh.findtheshipper.models.Comment();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }
        ((CommentRealmProxyInterface) unmanagedObject).realmSet$idComment(((CommentRealmProxyInterface) realmObject).realmGet$idComment());
        ((CommentRealmProxyInterface) unmanagedObject).realmSet$Content(((CommentRealmProxyInterface) realmObject).realmGet$Content());
        ((CommentRealmProxyInterface) unmanagedObject).realmSet$dateTime(((CommentRealmProxyInterface) realmObject).realmGet$dateTime());

        // Deep copy of user
        ((CommentRealmProxyInterface) unmanagedObject).realmSet$user(UserRealmProxy.createDetachedCopy(((CommentRealmProxyInterface) realmObject).realmGet$user(), currentDepth + 1, maxDepth, cache));
        return unmanagedObject;
    }

    static com.minh.findtheshipper.models.Comment update(Realm realm, com.minh.findtheshipper.models.Comment realmObject, com.minh.findtheshipper.models.Comment newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((CommentRealmProxyInterface) realmObject).realmSet$Content(((CommentRealmProxyInterface) newObject).realmGet$Content());
        ((CommentRealmProxyInterface) realmObject).realmSet$dateTime(((CommentRealmProxyInterface) newObject).realmGet$dateTime());
        com.minh.findtheshipper.models.User userObj = ((CommentRealmProxyInterface) newObject).realmGet$user();
        if (userObj != null) {
            com.minh.findtheshipper.models.User cacheuser = (com.minh.findtheshipper.models.User) cache.get(userObj);
            if (cacheuser != null) {
                ((CommentRealmProxyInterface) realmObject).realmSet$user(cacheuser);
            } else {
                ((CommentRealmProxyInterface) realmObject).realmSet$user(UserRealmProxy.copyOrUpdate(realm, userObj, true, cache));
            }
        } else {
            ((CommentRealmProxyInterface) realmObject).realmSet$user(null);
        }
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Comment = proxy[");
        stringBuilder.append("{idComment:");
        stringBuilder.append(realmGet$idComment() != null ? realmGet$idComment() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{Content:");
        stringBuilder.append(realmGet$Content() != null ? realmGet$Content() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{dateTime:");
        stringBuilder.append(realmGet$dateTime() != null ? realmGet$dateTime() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{user:");
        stringBuilder.append(realmGet$user() != null ? "User" : "null");
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState<?> realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long rowIndex = proxyState.getRow$realm().getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentRealmProxy aComment = (CommentRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aComment.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aComment.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aComment.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
