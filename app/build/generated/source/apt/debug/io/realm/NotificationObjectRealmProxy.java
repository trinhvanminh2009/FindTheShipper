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

public class NotificationObjectRealmProxy extends com.minh.findtheshipper.models.NotificationObject
    implements RealmObjectProxy, NotificationObjectRealmProxyInterface {

    static final class NotificationObjectColumnInfo extends ColumnInfo {
        long notificationIDIndex;
        long iconIndex;
        long contextIndex;

        NotificationObjectColumnInfo(SharedRealm realm, Table table) {
            super(3);
            this.notificationIDIndex = addColumnDetails(table, "notificationID", RealmFieldType.STRING);
            this.iconIndex = addColumnDetails(table, "icon", RealmFieldType.INTEGER);
            this.contextIndex = addColumnDetails(table, "context", RealmFieldType.STRING);
        }

        NotificationObjectColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new NotificationObjectColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final NotificationObjectColumnInfo src = (NotificationObjectColumnInfo) rawSrc;
            final NotificationObjectColumnInfo dst = (NotificationObjectColumnInfo) rawDst;
            dst.notificationIDIndex = src.notificationIDIndex;
            dst.iconIndex = src.iconIndex;
            dst.contextIndex = src.contextIndex;
        }
    }

    private NotificationObjectColumnInfo columnInfo;
    private ProxyState<com.minh.findtheshipper.models.NotificationObject> proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("notificationID");
        fieldNames.add("icon");
        fieldNames.add("context");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    NotificationObjectRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (NotificationObjectColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.minh.findtheshipper.models.NotificationObject>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$notificationID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.notificationIDIndex);
    }

    @Override
    public void realmSet$notificationID(String value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'notificationID' cannot be changed after object was created.");
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$icon() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.iconIndex);
    }

    @Override
    public void realmSet$icon(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.iconIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.iconIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$context() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.contextIndex);
    }

    @Override
    public void realmSet$context(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.contextIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.contextIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.contextIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.contextIndex, value);
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("NotificationObject")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("NotificationObject");
            realmObjectSchema.add("notificationID", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("icon", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
            realmObjectSchema.add("context", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            return realmObjectSchema;
        }
        return realmSchema.get("NotificationObject");
    }

    public static NotificationObjectColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (!sharedRealm.hasTable("class_NotificationObject")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'NotificationObject' class is missing from the schema for this Realm.");
        }
        Table table = sharedRealm.getTable("class_NotificationObject");
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

        final NotificationObjectColumnInfo columnInfo = new NotificationObjectColumnInfo(sharedRealm, table);

        if (!table.hasPrimaryKey()) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary key not defined for field 'notificationID' in existing Realm file. @PrimaryKey was added.");
        } else {
            if (table.getPrimaryKey() != columnInfo.notificationIDIndex) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary Key annotation definition was changed, from field " + table.getColumnName(table.getPrimaryKey()) + " to field notificationID");
            }
        }

        if (!columnTypes.containsKey("notificationID")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'notificationID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("notificationID") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'notificationID' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.notificationIDIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(),"@PrimaryKey field 'notificationID' does not support null values in the existing Realm file. Migrate using RealmObjectSchema.setNullable(), or mark the field as @Required.");
        }
        if (!table.hasSearchIndex(table.getColumnIndex("notificationID"))) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Index not defined for field 'notificationID' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
        }
        if (!columnTypes.containsKey("icon")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'icon' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("icon") != RealmFieldType.INTEGER) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'int' for field 'icon' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.iconIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'icon' does support null values in the existing Realm file. Use corresponding boxed type for field 'icon' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("context")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'context' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("context") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'context' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.contextIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'context' is required. Either set @Required to field 'context' or migrate using RealmObjectSchema.setNullable().");
        }

        return columnInfo;
    }

    public static String getTableName() {
        return "class_NotificationObject";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.minh.findtheshipper.models.NotificationObject createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.minh.findtheshipper.models.NotificationObject obj = null;
        if (update) {
            Table table = realm.getTable(com.minh.findtheshipper.models.NotificationObject.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = Table.NO_MATCH;
            if (json.isNull("notificationID")) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("notificationID"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.minh.findtheshipper.models.NotificationObject.class), false, Collections.<String> emptyList());
                    obj = new io.realm.NotificationObjectRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("notificationID")) {
                if (json.isNull("notificationID")) {
                    obj = (io.realm.NotificationObjectRealmProxy) realm.createObjectInternal(com.minh.findtheshipper.models.NotificationObject.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.NotificationObjectRealmProxy) realm.createObjectInternal(com.minh.findtheshipper.models.NotificationObject.class, json.getString("notificationID"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'notificationID'.");
            }
        }
        if (json.has("icon")) {
            if (json.isNull("icon")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'icon' to null.");
            } else {
                ((NotificationObjectRealmProxyInterface) obj).realmSet$icon((int) json.getInt("icon"));
            }
        }
        if (json.has("context")) {
            if (json.isNull("context")) {
                ((NotificationObjectRealmProxyInterface) obj).realmSet$context(null);
            } else {
                ((NotificationObjectRealmProxyInterface) obj).realmSet$context((String) json.getString("context"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.minh.findtheshipper.models.NotificationObject createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.minh.findtheshipper.models.NotificationObject obj = new com.minh.findtheshipper.models.NotificationObject();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("notificationID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((NotificationObjectRealmProxyInterface) obj).realmSet$notificationID(null);
                } else {
                    ((NotificationObjectRealmProxyInterface) obj).realmSet$notificationID((String) reader.nextString());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("icon")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'icon' to null.");
                } else {
                    ((NotificationObjectRealmProxyInterface) obj).realmSet$icon((int) reader.nextInt());
                }
            } else if (name.equals("context")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((NotificationObjectRealmProxyInterface) obj).realmSet$context(null);
                } else {
                    ((NotificationObjectRealmProxyInterface) obj).realmSet$context((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'notificationID'.");
        }
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.minh.findtheshipper.models.NotificationObject copyOrUpdate(Realm realm, com.minh.findtheshipper.models.NotificationObject object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.minh.findtheshipper.models.NotificationObject) cachedRealmObject;
        } else {
            com.minh.findtheshipper.models.NotificationObject realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.minh.findtheshipper.models.NotificationObject.class);
                long pkColumnIndex = table.getPrimaryKey();
                String value = ((NotificationObjectRealmProxyInterface) object).realmGet$notificationID();
                long rowIndex = Table.NO_MATCH;
                if (value == null) {
                    rowIndex = table.findFirstNull(pkColumnIndex);
                } else {
                    rowIndex = table.findFirstString(pkColumnIndex, value);
                }
                if (rowIndex != Table.NO_MATCH) {
                    try {
                        objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.minh.findtheshipper.models.NotificationObject.class), false, Collections.<String> emptyList());
                        realmObject = new io.realm.NotificationObjectRealmProxy();
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

    public static com.minh.findtheshipper.models.NotificationObject copy(Realm realm, com.minh.findtheshipper.models.NotificationObject newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.minh.findtheshipper.models.NotificationObject) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.minh.findtheshipper.models.NotificationObject realmObject = realm.createObjectInternal(com.minh.findtheshipper.models.NotificationObject.class, ((NotificationObjectRealmProxyInterface) newObject).realmGet$notificationID(), false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((NotificationObjectRealmProxyInterface) realmObject).realmSet$icon(((NotificationObjectRealmProxyInterface) newObject).realmGet$icon());
            ((NotificationObjectRealmProxyInterface) realmObject).realmSet$context(((NotificationObjectRealmProxyInterface) newObject).realmGet$context());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.minh.findtheshipper.models.NotificationObject object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.minh.findtheshipper.models.NotificationObject.class);
        long tableNativePtr = table.getNativePtr();
        NotificationObjectColumnInfo columnInfo = (NotificationObjectColumnInfo) realm.schema.getColumnInfo(com.minh.findtheshipper.models.NotificationObject.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((NotificationObjectRealmProxyInterface) object).realmGet$notificationID();
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
        Table.nativeSetLong(tableNativePtr, columnInfo.iconIndex, rowIndex, ((NotificationObjectRealmProxyInterface)object).realmGet$icon(), false);
        String realmGet$context = ((NotificationObjectRealmProxyInterface)object).realmGet$context();
        if (realmGet$context != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.contextIndex, rowIndex, realmGet$context, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.minh.findtheshipper.models.NotificationObject.class);
        long tableNativePtr = table.getNativePtr();
        NotificationObjectColumnInfo columnInfo = (NotificationObjectColumnInfo) realm.schema.getColumnInfo(com.minh.findtheshipper.models.NotificationObject.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.minh.findtheshipper.models.NotificationObject object = null;
        while (objects.hasNext()) {
            object = (com.minh.findtheshipper.models.NotificationObject) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((NotificationObjectRealmProxyInterface) object).realmGet$notificationID();
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
                Table.nativeSetLong(tableNativePtr, columnInfo.iconIndex, rowIndex, ((NotificationObjectRealmProxyInterface)object).realmGet$icon(), false);
                String realmGet$context = ((NotificationObjectRealmProxyInterface)object).realmGet$context();
                if (realmGet$context != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.contextIndex, rowIndex, realmGet$context, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.minh.findtheshipper.models.NotificationObject object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.minh.findtheshipper.models.NotificationObject.class);
        long tableNativePtr = table.getNativePtr();
        NotificationObjectColumnInfo columnInfo = (NotificationObjectColumnInfo) realm.schema.getColumnInfo(com.minh.findtheshipper.models.NotificationObject.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((NotificationObjectRealmProxyInterface) object).realmGet$notificationID();
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
        Table.nativeSetLong(tableNativePtr, columnInfo.iconIndex, rowIndex, ((NotificationObjectRealmProxyInterface)object).realmGet$icon(), false);
        String realmGet$context = ((NotificationObjectRealmProxyInterface)object).realmGet$context();
        if (realmGet$context != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.contextIndex, rowIndex, realmGet$context, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.contextIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.minh.findtheshipper.models.NotificationObject.class);
        long tableNativePtr = table.getNativePtr();
        NotificationObjectColumnInfo columnInfo = (NotificationObjectColumnInfo) realm.schema.getColumnInfo(com.minh.findtheshipper.models.NotificationObject.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.minh.findtheshipper.models.NotificationObject object = null;
        while (objects.hasNext()) {
            object = (com.minh.findtheshipper.models.NotificationObject) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((NotificationObjectRealmProxyInterface) object).realmGet$notificationID();
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
                Table.nativeSetLong(tableNativePtr, columnInfo.iconIndex, rowIndex, ((NotificationObjectRealmProxyInterface)object).realmGet$icon(), false);
                String realmGet$context = ((NotificationObjectRealmProxyInterface)object).realmGet$context();
                if (realmGet$context != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.contextIndex, rowIndex, realmGet$context, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.contextIndex, rowIndex, false);
                }
            }
        }
    }

    public static com.minh.findtheshipper.models.NotificationObject createDetachedCopy(com.minh.findtheshipper.models.NotificationObject realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.minh.findtheshipper.models.NotificationObject unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.minh.findtheshipper.models.NotificationObject)cachedObject.object;
            } else {
                unmanagedObject = (com.minh.findtheshipper.models.NotificationObject)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.minh.findtheshipper.models.NotificationObject();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }
        ((NotificationObjectRealmProxyInterface) unmanagedObject).realmSet$notificationID(((NotificationObjectRealmProxyInterface) realmObject).realmGet$notificationID());
        ((NotificationObjectRealmProxyInterface) unmanagedObject).realmSet$icon(((NotificationObjectRealmProxyInterface) realmObject).realmGet$icon());
        ((NotificationObjectRealmProxyInterface) unmanagedObject).realmSet$context(((NotificationObjectRealmProxyInterface) realmObject).realmGet$context());
        return unmanagedObject;
    }

    static com.minh.findtheshipper.models.NotificationObject update(Realm realm, com.minh.findtheshipper.models.NotificationObject realmObject, com.minh.findtheshipper.models.NotificationObject newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((NotificationObjectRealmProxyInterface) realmObject).realmSet$icon(((NotificationObjectRealmProxyInterface) newObject).realmGet$icon());
        ((NotificationObjectRealmProxyInterface) realmObject).realmSet$context(((NotificationObjectRealmProxyInterface) newObject).realmGet$context());
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("NotificationObject = proxy[");
        stringBuilder.append("{notificationID:");
        stringBuilder.append(realmGet$notificationID() != null ? realmGet$notificationID() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{icon:");
        stringBuilder.append(realmGet$icon());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{context:");
        stringBuilder.append(realmGet$context() != null ? realmGet$context() : "null");
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
        NotificationObjectRealmProxy aNotificationObject = (NotificationObjectRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aNotificationObject.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aNotificationObject.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aNotificationObject.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
