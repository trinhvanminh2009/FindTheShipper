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

public class DislikeRealmProxy extends com.minh.findtheshipper.models.Dislike
    implements RealmObjectProxy, DislikeRealmProxyInterface {

    static final class DislikeColumnInfo extends ColumnInfo {
        long dislikeIDIndex;
        long dateTimeIndex;
        long userIndex;

        DislikeColumnInfo(SharedRealm realm, Table table) {
            super(3);
            this.dislikeIDIndex = addColumnDetails(table, "dislikeID", RealmFieldType.STRING);
            this.dateTimeIndex = addColumnDetails(table, "dateTime", RealmFieldType.STRING);
            this.userIndex = addColumnDetails(table, "user", RealmFieldType.OBJECT);
        }

        DislikeColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new DislikeColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final DislikeColumnInfo src = (DislikeColumnInfo) rawSrc;
            final DislikeColumnInfo dst = (DislikeColumnInfo) rawDst;
            dst.dislikeIDIndex = src.dislikeIDIndex;
            dst.dateTimeIndex = src.dateTimeIndex;
            dst.userIndex = src.userIndex;
        }
    }

    private DislikeColumnInfo columnInfo;
    private ProxyState<com.minh.findtheshipper.models.Dislike> proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("dislikeID");
        fieldNames.add("dateTime");
        fieldNames.add("user");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    DislikeRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (DislikeColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.minh.findtheshipper.models.Dislike>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$dislikeID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.dislikeIDIndex);
    }

    @Override
    public void realmSet$dislikeID(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.dislikeIDIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.dislikeIDIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.dislikeIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.dislikeIDIndex, value);
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
        if (!realmSchema.contains("Dislike")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("Dislike");
            realmObjectSchema.add("dislikeID", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("dateTime", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            if (!realmSchema.contains("User")) {
                UserRealmProxy.createRealmObjectSchema(realmSchema);
            }
            realmObjectSchema.add("user", RealmFieldType.OBJECT, realmSchema.get("User"));
            return realmObjectSchema;
        }
        return realmSchema.get("Dislike");
    }

    public static DislikeColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (!sharedRealm.hasTable("class_Dislike")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'Dislike' class is missing from the schema for this Realm.");
        }
        Table table = sharedRealm.getTable("class_Dislike");
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

        final DislikeColumnInfo columnInfo = new DislikeColumnInfo(sharedRealm, table);

        if (table.hasPrimaryKey()) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary Key defined for field " + table.getColumnName(table.getPrimaryKey()) + " was removed.");
        }

        if (!columnTypes.containsKey("dislikeID")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'dislikeID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("dislikeID") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'dislikeID' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.dislikeIDIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'dislikeID' is required. Either set @Required to field 'dislikeID' or migrate using RealmObjectSchema.setNullable().");
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
        return "class_Dislike";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.minh.findtheshipper.models.Dislike createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(1);
        if (json.has("user")) {
            excludeFields.add("user");
        }
        com.minh.findtheshipper.models.Dislike obj = realm.createObjectInternal(com.minh.findtheshipper.models.Dislike.class, true, excludeFields);
        if (json.has("dislikeID")) {
            if (json.isNull("dislikeID")) {
                ((DislikeRealmProxyInterface) obj).realmSet$dislikeID(null);
            } else {
                ((DislikeRealmProxyInterface) obj).realmSet$dislikeID((String) json.getString("dislikeID"));
            }
        }
        if (json.has("dateTime")) {
            if (json.isNull("dateTime")) {
                ((DislikeRealmProxyInterface) obj).realmSet$dateTime(null);
            } else {
                ((DislikeRealmProxyInterface) obj).realmSet$dateTime((String) json.getString("dateTime"));
            }
        }
        if (json.has("user")) {
            if (json.isNull("user")) {
                ((DislikeRealmProxyInterface) obj).realmSet$user(null);
            } else {
                com.minh.findtheshipper.models.User userObj = UserRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("user"), update);
                ((DislikeRealmProxyInterface) obj).realmSet$user(userObj);
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.minh.findtheshipper.models.Dislike createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        com.minh.findtheshipper.models.Dislike obj = new com.minh.findtheshipper.models.Dislike();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("dislikeID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((DislikeRealmProxyInterface) obj).realmSet$dislikeID(null);
                } else {
                    ((DislikeRealmProxyInterface) obj).realmSet$dislikeID((String) reader.nextString());
                }
            } else if (name.equals("dateTime")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((DislikeRealmProxyInterface) obj).realmSet$dateTime(null);
                } else {
                    ((DislikeRealmProxyInterface) obj).realmSet$dateTime((String) reader.nextString());
                }
            } else if (name.equals("user")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((DislikeRealmProxyInterface) obj).realmSet$user(null);
                } else {
                    com.minh.findtheshipper.models.User userObj = UserRealmProxy.createUsingJsonStream(realm, reader);
                    ((DislikeRealmProxyInterface) obj).realmSet$user(userObj);
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.minh.findtheshipper.models.Dislike copyOrUpdate(Realm realm, com.minh.findtheshipper.models.Dislike object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.minh.findtheshipper.models.Dislike) cachedRealmObject;
        } else {
            return copy(realm, object, update, cache);
        }
    }

    public static com.minh.findtheshipper.models.Dislike copy(Realm realm, com.minh.findtheshipper.models.Dislike newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.minh.findtheshipper.models.Dislike) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.minh.findtheshipper.models.Dislike realmObject = realm.createObjectInternal(com.minh.findtheshipper.models.Dislike.class, false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((DislikeRealmProxyInterface) realmObject).realmSet$dislikeID(((DislikeRealmProxyInterface) newObject).realmGet$dislikeID());
            ((DislikeRealmProxyInterface) realmObject).realmSet$dateTime(((DislikeRealmProxyInterface) newObject).realmGet$dateTime());

            com.minh.findtheshipper.models.User userObj = ((DislikeRealmProxyInterface) newObject).realmGet$user();
            if (userObj != null) {
                com.minh.findtheshipper.models.User cacheuser = (com.minh.findtheshipper.models.User) cache.get(userObj);
                if (cacheuser != null) {
                    ((DislikeRealmProxyInterface) realmObject).realmSet$user(cacheuser);
                } else {
                    ((DislikeRealmProxyInterface) realmObject).realmSet$user(UserRealmProxy.copyOrUpdate(realm, userObj, update, cache));
                }
            } else {
                ((DislikeRealmProxyInterface) realmObject).realmSet$user(null);
            }
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.minh.findtheshipper.models.Dislike object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.minh.findtheshipper.models.Dislike.class);
        long tableNativePtr = table.getNativePtr();
        DislikeColumnInfo columnInfo = (DislikeColumnInfo) realm.schema.getColumnInfo(com.minh.findtheshipper.models.Dislike.class);
        long rowIndex = OsObject.createRow(realm.sharedRealm, table);
        cache.put(object, rowIndex);
        String realmGet$dislikeID = ((DislikeRealmProxyInterface)object).realmGet$dislikeID();
        if (realmGet$dislikeID != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.dislikeIDIndex, rowIndex, realmGet$dislikeID, false);
        }
        String realmGet$dateTime = ((DislikeRealmProxyInterface)object).realmGet$dateTime();
        if (realmGet$dateTime != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.dateTimeIndex, rowIndex, realmGet$dateTime, false);
        }

        com.minh.findtheshipper.models.User userObj = ((DislikeRealmProxyInterface) object).realmGet$user();
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
        Table table = realm.getTable(com.minh.findtheshipper.models.Dislike.class);
        long tableNativePtr = table.getNativePtr();
        DislikeColumnInfo columnInfo = (DislikeColumnInfo) realm.schema.getColumnInfo(com.minh.findtheshipper.models.Dislike.class);
        com.minh.findtheshipper.models.Dislike object = null;
        while (objects.hasNext()) {
            object = (com.minh.findtheshipper.models.Dislike) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = OsObject.createRow(realm.sharedRealm, table);
                cache.put(object, rowIndex);
                String realmGet$dislikeID = ((DislikeRealmProxyInterface)object).realmGet$dislikeID();
                if (realmGet$dislikeID != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.dislikeIDIndex, rowIndex, realmGet$dislikeID, false);
                }
                String realmGet$dateTime = ((DislikeRealmProxyInterface)object).realmGet$dateTime();
                if (realmGet$dateTime != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.dateTimeIndex, rowIndex, realmGet$dateTime, false);
                }

                com.minh.findtheshipper.models.User userObj = ((DislikeRealmProxyInterface) object).realmGet$user();
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

    public static long insertOrUpdate(Realm realm, com.minh.findtheshipper.models.Dislike object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.minh.findtheshipper.models.Dislike.class);
        long tableNativePtr = table.getNativePtr();
        DislikeColumnInfo columnInfo = (DislikeColumnInfo) realm.schema.getColumnInfo(com.minh.findtheshipper.models.Dislike.class);
        long rowIndex = OsObject.createRow(realm.sharedRealm, table);
        cache.put(object, rowIndex);
        String realmGet$dislikeID = ((DislikeRealmProxyInterface)object).realmGet$dislikeID();
        if (realmGet$dislikeID != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.dislikeIDIndex, rowIndex, realmGet$dislikeID, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.dislikeIDIndex, rowIndex, false);
        }
        String realmGet$dateTime = ((DislikeRealmProxyInterface)object).realmGet$dateTime();
        if (realmGet$dateTime != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.dateTimeIndex, rowIndex, realmGet$dateTime, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.dateTimeIndex, rowIndex, false);
        }

        com.minh.findtheshipper.models.User userObj = ((DislikeRealmProxyInterface) object).realmGet$user();
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
        Table table = realm.getTable(com.minh.findtheshipper.models.Dislike.class);
        long tableNativePtr = table.getNativePtr();
        DislikeColumnInfo columnInfo = (DislikeColumnInfo) realm.schema.getColumnInfo(com.minh.findtheshipper.models.Dislike.class);
        com.minh.findtheshipper.models.Dislike object = null;
        while (objects.hasNext()) {
            object = (com.minh.findtheshipper.models.Dislike) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = OsObject.createRow(realm.sharedRealm, table);
                cache.put(object, rowIndex);
                String realmGet$dislikeID = ((DislikeRealmProxyInterface)object).realmGet$dislikeID();
                if (realmGet$dislikeID != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.dislikeIDIndex, rowIndex, realmGet$dislikeID, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.dislikeIDIndex, rowIndex, false);
                }
                String realmGet$dateTime = ((DislikeRealmProxyInterface)object).realmGet$dateTime();
                if (realmGet$dateTime != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.dateTimeIndex, rowIndex, realmGet$dateTime, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.dateTimeIndex, rowIndex, false);
                }

                com.minh.findtheshipper.models.User userObj = ((DislikeRealmProxyInterface) object).realmGet$user();
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

    public static com.minh.findtheshipper.models.Dislike createDetachedCopy(com.minh.findtheshipper.models.Dislike realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.minh.findtheshipper.models.Dislike unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.minh.findtheshipper.models.Dislike)cachedObject.object;
            } else {
                unmanagedObject = (com.minh.findtheshipper.models.Dislike)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.minh.findtheshipper.models.Dislike();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }
        ((DislikeRealmProxyInterface) unmanagedObject).realmSet$dislikeID(((DislikeRealmProxyInterface) realmObject).realmGet$dislikeID());
        ((DislikeRealmProxyInterface) unmanagedObject).realmSet$dateTime(((DislikeRealmProxyInterface) realmObject).realmGet$dateTime());

        // Deep copy of user
        ((DislikeRealmProxyInterface) unmanagedObject).realmSet$user(UserRealmProxy.createDetachedCopy(((DislikeRealmProxyInterface) realmObject).realmGet$user(), currentDepth + 1, maxDepth, cache));
        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Dislike = proxy[");
        stringBuilder.append("{dislikeID:");
        stringBuilder.append(realmGet$dislikeID() != null ? realmGet$dislikeID() : "null");
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
        DislikeRealmProxy aDislike = (DislikeRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aDislike.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aDislike.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aDislike.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
