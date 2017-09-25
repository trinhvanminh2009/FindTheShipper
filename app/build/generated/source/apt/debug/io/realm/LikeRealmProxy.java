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

public class LikeRealmProxy extends com.minh.findtheshipper.models.Like
    implements RealmObjectProxy, LikeRealmProxyInterface {

    static final class LikeColumnInfo extends ColumnInfo {
        long likeIDIndex;
        long dateTimeIndex;
        long userIndex;

        LikeColumnInfo(SharedRealm realm, Table table) {
            super(3);
            this.likeIDIndex = addColumnDetails(table, "likeID", RealmFieldType.STRING);
            this.dateTimeIndex = addColumnDetails(table, "dateTime", RealmFieldType.STRING);
            this.userIndex = addColumnDetails(table, "user", RealmFieldType.OBJECT);
        }

        LikeColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new LikeColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final LikeColumnInfo src = (LikeColumnInfo) rawSrc;
            final LikeColumnInfo dst = (LikeColumnInfo) rawDst;
            dst.likeIDIndex = src.likeIDIndex;
            dst.dateTimeIndex = src.dateTimeIndex;
            dst.userIndex = src.userIndex;
        }
    }

    private LikeColumnInfo columnInfo;
    private ProxyState<com.minh.findtheshipper.models.Like> proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("likeID");
        fieldNames.add("dateTime");
        fieldNames.add("user");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    LikeRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (LikeColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.minh.findtheshipper.models.Like>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$likeID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.likeIDIndex);
    }

    @Override
    public void realmSet$likeID(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.likeIDIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.likeIDIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.likeIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.likeIDIndex, value);
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
        if (!realmSchema.contains("Like")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("Like");
            realmObjectSchema.add("likeID", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("dateTime", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            if (!realmSchema.contains("User")) {
                UserRealmProxy.createRealmObjectSchema(realmSchema);
            }
            realmObjectSchema.add("user", RealmFieldType.OBJECT, realmSchema.get("User"));
            return realmObjectSchema;
        }
        return realmSchema.get("Like");
    }

    public static LikeColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (!sharedRealm.hasTable("class_Like")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'Like' class is missing from the schema for this Realm.");
        }
        Table table = sharedRealm.getTable("class_Like");
        final long columnCount = table.getColumnCount();
        if (columnCount != 3) {
            if (columnCount < 3) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 3 but was " + columnCount);
            }
            if (allowExtraColumns) {
                RealmLog.debug("Field count is more than expected - expected 3 but was %1$d", columnCount);
            } else {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 3 but was " + columnCount);
            }
        }
        Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
        for (long i = 0; i < columnCount; i++) {
            columnTypes.put(table.getColumnName(i), table.getColumnType(i));
        }

        final LikeColumnInfo columnInfo = new LikeColumnInfo(sharedRealm, table);

        if (table.hasPrimaryKey()) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary Key defined for field " + table.getColumnName(table.getPrimaryKey()) + " was removed.");
        }

        if (!columnTypes.containsKey("likeID")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'likeID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("likeID") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'likeID' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.likeIDIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'likeID' is required. Either set @Required to field 'likeID' or migrate using RealmObjectSchema.setNullable().");
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
        Table table_2 = sharedRealm.getTable("class_User");
        if (!table.getLinkTarget(columnInfo.userIndex).hasSameSchema(table_2)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid RealmObject for field 'user': '" + table.getLinkTarget(columnInfo.userIndex).getName() + "' expected - was '" + table_2.getName() + "'");
        }

        return columnInfo;
    }

    public static String getTableName() {
        return "class_Like";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.minh.findtheshipper.models.Like createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(1);
        if (json.has("user")) {
            excludeFields.add("user");
        }
        com.minh.findtheshipper.models.Like obj = realm.createObjectInternal(com.minh.findtheshipper.models.Like.class, true, excludeFields);
        if (json.has("likeID")) {
            if (json.isNull("likeID")) {
                ((LikeRealmProxyInterface) obj).realmSet$likeID(null);
            } else {
                ((LikeRealmProxyInterface) obj).realmSet$likeID((String) json.getString("likeID"));
            }
        }
        if (json.has("dateTime")) {
            if (json.isNull("dateTime")) {
                ((LikeRealmProxyInterface) obj).realmSet$dateTime(null);
            } else {
                ((LikeRealmProxyInterface) obj).realmSet$dateTime((String) json.getString("dateTime"));
            }
        }
        if (json.has("user")) {
            if (json.isNull("user")) {
                ((LikeRealmProxyInterface) obj).realmSet$user(null);
            } else {
                com.minh.findtheshipper.models.User userObj = UserRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("user"), update);
                ((LikeRealmProxyInterface) obj).realmSet$user(userObj);
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.minh.findtheshipper.models.Like createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        com.minh.findtheshipper.models.Like obj = new com.minh.findtheshipper.models.Like();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("likeID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((LikeRealmProxyInterface) obj).realmSet$likeID(null);
                } else {
                    ((LikeRealmProxyInterface) obj).realmSet$likeID((String) reader.nextString());
                }
            } else if (name.equals("dateTime")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((LikeRealmProxyInterface) obj).realmSet$dateTime(null);
                } else {
                    ((LikeRealmProxyInterface) obj).realmSet$dateTime((String) reader.nextString());
                }
            } else if (name.equals("user")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((LikeRealmProxyInterface) obj).realmSet$user(null);
                } else {
                    com.minh.findtheshipper.models.User userObj = UserRealmProxy.createUsingJsonStream(realm, reader);
                    ((LikeRealmProxyInterface) obj).realmSet$user(userObj);
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.minh.findtheshipper.models.Like copyOrUpdate(Realm realm, com.minh.findtheshipper.models.Like object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.minh.findtheshipper.models.Like) cachedRealmObject;
        } else {
            return copy(realm, object, update, cache);
        }
    }

    public static com.minh.findtheshipper.models.Like copy(Realm realm, com.minh.findtheshipper.models.Like newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.minh.findtheshipper.models.Like) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.minh.findtheshipper.models.Like realmObject = realm.createObjectInternal(com.minh.findtheshipper.models.Like.class, false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((LikeRealmProxyInterface) realmObject).realmSet$likeID(((LikeRealmProxyInterface) newObject).realmGet$likeID());
            ((LikeRealmProxyInterface) realmObject).realmSet$dateTime(((LikeRealmProxyInterface) newObject).realmGet$dateTime());

            com.minh.findtheshipper.models.User userObj = ((LikeRealmProxyInterface) newObject).realmGet$user();
            if (userObj != null) {
                com.minh.findtheshipper.models.User cacheuser = (com.minh.findtheshipper.models.User) cache.get(userObj);
                if (cacheuser != null) {
                    ((LikeRealmProxyInterface) realmObject).realmSet$user(cacheuser);
                } else {
                    ((LikeRealmProxyInterface) realmObject).realmSet$user(UserRealmProxy.copyOrUpdate(realm, userObj, update, cache));
                }
            } else {
                ((LikeRealmProxyInterface) realmObject).realmSet$user(null);
            }
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.minh.findtheshipper.models.Like object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.minh.findtheshipper.models.Like.class);
        long tableNativePtr = table.getNativePtr();
        LikeColumnInfo columnInfo = (LikeColumnInfo) realm.schema.getColumnInfo(com.minh.findtheshipper.models.Like.class);
        long rowIndex = OsObject.createRow(realm.sharedRealm, table);
        cache.put(object, rowIndex);
        String realmGet$likeID = ((LikeRealmProxyInterface)object).realmGet$likeID();
        if (realmGet$likeID != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.likeIDIndex, rowIndex, realmGet$likeID, false);
        }
        String realmGet$dateTime = ((LikeRealmProxyInterface)object).realmGet$dateTime();
        if (realmGet$dateTime != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.dateTimeIndex, rowIndex, realmGet$dateTime, false);
        }

        com.minh.findtheshipper.models.User userObj = ((LikeRealmProxyInterface) object).realmGet$user();
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
        Table table = realm.getTable(com.minh.findtheshipper.models.Like.class);
        long tableNativePtr = table.getNativePtr();
        LikeColumnInfo columnInfo = (LikeColumnInfo) realm.schema.getColumnInfo(com.minh.findtheshipper.models.Like.class);
        com.minh.findtheshipper.models.Like object = null;
        while (objects.hasNext()) {
            object = (com.minh.findtheshipper.models.Like) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = OsObject.createRow(realm.sharedRealm, table);
                cache.put(object, rowIndex);
                String realmGet$likeID = ((LikeRealmProxyInterface)object).realmGet$likeID();
                if (realmGet$likeID != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.likeIDIndex, rowIndex, realmGet$likeID, false);
                }
                String realmGet$dateTime = ((LikeRealmProxyInterface)object).realmGet$dateTime();
                if (realmGet$dateTime != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.dateTimeIndex, rowIndex, realmGet$dateTime, false);
                }

                com.minh.findtheshipper.models.User userObj = ((LikeRealmProxyInterface) object).realmGet$user();
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

    public static long insertOrUpdate(Realm realm, com.minh.findtheshipper.models.Like object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.minh.findtheshipper.models.Like.class);
        long tableNativePtr = table.getNativePtr();
        LikeColumnInfo columnInfo = (LikeColumnInfo) realm.schema.getColumnInfo(com.minh.findtheshipper.models.Like.class);
        long rowIndex = OsObject.createRow(realm.sharedRealm, table);
        cache.put(object, rowIndex);
        String realmGet$likeID = ((LikeRealmProxyInterface)object).realmGet$likeID();
        if (realmGet$likeID != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.likeIDIndex, rowIndex, realmGet$likeID, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.likeIDIndex, rowIndex, false);
        }
        String realmGet$dateTime = ((LikeRealmProxyInterface)object).realmGet$dateTime();
        if (realmGet$dateTime != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.dateTimeIndex, rowIndex, realmGet$dateTime, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.dateTimeIndex, rowIndex, false);
        }

        com.minh.findtheshipper.models.User userObj = ((LikeRealmProxyInterface) object).realmGet$user();
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
        Table table = realm.getTable(com.minh.findtheshipper.models.Like.class);
        long tableNativePtr = table.getNativePtr();
        LikeColumnInfo columnInfo = (LikeColumnInfo) realm.schema.getColumnInfo(com.minh.findtheshipper.models.Like.class);
        com.minh.findtheshipper.models.Like object = null;
        while (objects.hasNext()) {
            object = (com.minh.findtheshipper.models.Like) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = OsObject.createRow(realm.sharedRealm, table);
                cache.put(object, rowIndex);
                String realmGet$likeID = ((LikeRealmProxyInterface)object).realmGet$likeID();
                if (realmGet$likeID != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.likeIDIndex, rowIndex, realmGet$likeID, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.likeIDIndex, rowIndex, false);
                }
                String realmGet$dateTime = ((LikeRealmProxyInterface)object).realmGet$dateTime();
                if (realmGet$dateTime != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.dateTimeIndex, rowIndex, realmGet$dateTime, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.dateTimeIndex, rowIndex, false);
                }

                com.minh.findtheshipper.models.User userObj = ((LikeRealmProxyInterface) object).realmGet$user();
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

    public static com.minh.findtheshipper.models.Like createDetachedCopy(com.minh.findtheshipper.models.Like realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.minh.findtheshipper.models.Like unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.minh.findtheshipper.models.Like)cachedObject.object;
            } else {
                unmanagedObject = (com.minh.findtheshipper.models.Like)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.minh.findtheshipper.models.Like();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }
        ((LikeRealmProxyInterface) unmanagedObject).realmSet$likeID(((LikeRealmProxyInterface) realmObject).realmGet$likeID());
        ((LikeRealmProxyInterface) unmanagedObject).realmSet$dateTime(((LikeRealmProxyInterface) realmObject).realmGet$dateTime());

        // Deep copy of user
        ((LikeRealmProxyInterface) unmanagedObject).realmSet$user(UserRealmProxy.createDetachedCopy(((LikeRealmProxyInterface) realmObject).realmGet$user(), currentDepth + 1, maxDepth, cache));
        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Like = proxy[");
        stringBuilder.append("{likeID:");
        stringBuilder.append(realmGet$likeID() != null ? realmGet$likeID() : "null");
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
        LikeRealmProxy aLike = (LikeRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aLike.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aLike.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aLike.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
