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

public class CurrentUserRealmProxy extends com.minh.findtheshipper.models.CurrentUser
    implements RealmObjectProxy, CurrentUserRealmProxyInterface {

    static final class CurrentUserColumnInfo extends ColumnInfo {
        long emailIndex;
        long nameIndex;
        long phoneNumberIndex;
        long avatarIndex;
        long genderIndex;

        CurrentUserColumnInfo(SharedRealm realm, Table table) {
            super(5);
            this.emailIndex = addColumnDetails(table, "email", RealmFieldType.STRING);
            this.nameIndex = addColumnDetails(table, "name", RealmFieldType.STRING);
            this.phoneNumberIndex = addColumnDetails(table, "phoneNumber", RealmFieldType.STRING);
            this.avatarIndex = addColumnDetails(table, "avatar", RealmFieldType.STRING);
            this.genderIndex = addColumnDetails(table, "gender", RealmFieldType.STRING);
        }

        CurrentUserColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new CurrentUserColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final CurrentUserColumnInfo src = (CurrentUserColumnInfo) rawSrc;
            final CurrentUserColumnInfo dst = (CurrentUserColumnInfo) rawDst;
            dst.emailIndex = src.emailIndex;
            dst.nameIndex = src.nameIndex;
            dst.phoneNumberIndex = src.phoneNumberIndex;
            dst.avatarIndex = src.avatarIndex;
            dst.genderIndex = src.genderIndex;
        }
    }

    private CurrentUserColumnInfo columnInfo;
    private ProxyState<com.minh.findtheshipper.models.CurrentUser> proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("email");
        fieldNames.add("name");
        fieldNames.add("phoneNumber");
        fieldNames.add("avatar");
        fieldNames.add("gender");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    CurrentUserRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (CurrentUserColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.minh.findtheshipper.models.CurrentUser>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$email() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.emailIndex);
    }

    @Override
    public void realmSet$email(String value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'email' cannot be changed after object was created.");
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$name() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.nameIndex);
    }

    @Override
    public void realmSet$name(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.nameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.nameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.nameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.nameIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$phoneNumber() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.phoneNumberIndex);
    }

    @Override
    public void realmSet$phoneNumber(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.phoneNumberIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.phoneNumberIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.phoneNumberIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.phoneNumberIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$avatar() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.avatarIndex);
    }

    @Override
    public void realmSet$avatar(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.avatarIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.avatarIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.avatarIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.avatarIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$gender() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.genderIndex);
    }

    @Override
    public void realmSet$gender(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.genderIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.genderIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.genderIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.genderIndex, value);
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("CurrentUser")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("CurrentUser");
            realmObjectSchema.add("email", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("phoneNumber", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("avatar", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("gender", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            return realmObjectSchema;
        }
        return realmSchema.get("CurrentUser");
    }

    public static CurrentUserColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (!sharedRealm.hasTable("class_CurrentUser")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'CurrentUser' class is missing from the schema for this Realm.");
        }
        Table table = sharedRealm.getTable("class_CurrentUser");
        final long columnCount = table.getColumnCount();
        if (columnCount != 5) {
            if (columnCount < 5) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 5 but was " + columnCount);
            }
            if (allowExtraColumns) {
                RealmLog.debug("Field count is more than expected - expected 5 but was %1$d", columnCount);
            } else {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 5 but was " + columnCount);
            }
        }
        Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
        for (long i = 0; i < columnCount; i++) {
            columnTypes.put(table.getColumnName(i), table.getColumnType(i));
        }

        final CurrentUserColumnInfo columnInfo = new CurrentUserColumnInfo(sharedRealm, table);

        if (!table.hasPrimaryKey()) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary key not defined for field 'email' in existing Realm file. @PrimaryKey was added.");
        } else {
            if (table.getPrimaryKey() != columnInfo.emailIndex) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary Key annotation definition was changed, from field " + table.getColumnName(table.getPrimaryKey()) + " to field email");
            }
        }

        if (!columnTypes.containsKey("email")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'email' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("email") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'email' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.emailIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(),"@PrimaryKey field 'email' does not support null values in the existing Realm file. Migrate using RealmObjectSchema.setNullable(), or mark the field as @Required.");
        }
        if (!table.hasSearchIndex(table.getColumnIndex("email"))) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Index not defined for field 'email' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
        }
        if (!columnTypes.containsKey("name")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'name' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("name") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'name' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.nameIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'name' is required. Either set @Required to field 'name' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("phoneNumber")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'phoneNumber' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("phoneNumber") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'phoneNumber' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.phoneNumberIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'phoneNumber' is required. Either set @Required to field 'phoneNumber' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("avatar")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'avatar' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("avatar") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'avatar' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.avatarIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'avatar' is required. Either set @Required to field 'avatar' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("gender")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'gender' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("gender") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'gender' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.genderIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'gender' is required. Either set @Required to field 'gender' or migrate using RealmObjectSchema.setNullable().");
        }

        return columnInfo;
    }

    public static String getTableName() {
        return "class_CurrentUser";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.minh.findtheshipper.models.CurrentUser createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.minh.findtheshipper.models.CurrentUser obj = null;
        if (update) {
            Table table = realm.getTable(com.minh.findtheshipper.models.CurrentUser.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = Table.NO_MATCH;
            if (json.isNull("email")) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("email"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.minh.findtheshipper.models.CurrentUser.class), false, Collections.<String> emptyList());
                    obj = new io.realm.CurrentUserRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("email")) {
                if (json.isNull("email")) {
                    obj = (io.realm.CurrentUserRealmProxy) realm.createObjectInternal(com.minh.findtheshipper.models.CurrentUser.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.CurrentUserRealmProxy) realm.createObjectInternal(com.minh.findtheshipper.models.CurrentUser.class, json.getString("email"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'email'.");
            }
        }
        if (json.has("name")) {
            if (json.isNull("name")) {
                ((CurrentUserRealmProxyInterface) obj).realmSet$name(null);
            } else {
                ((CurrentUserRealmProxyInterface) obj).realmSet$name((String) json.getString("name"));
            }
        }
        if (json.has("phoneNumber")) {
            if (json.isNull("phoneNumber")) {
                ((CurrentUserRealmProxyInterface) obj).realmSet$phoneNumber(null);
            } else {
                ((CurrentUserRealmProxyInterface) obj).realmSet$phoneNumber((String) json.getString("phoneNumber"));
            }
        }
        if (json.has("avatar")) {
            if (json.isNull("avatar")) {
                ((CurrentUserRealmProxyInterface) obj).realmSet$avatar(null);
            } else {
                ((CurrentUserRealmProxyInterface) obj).realmSet$avatar((String) json.getString("avatar"));
            }
        }
        if (json.has("gender")) {
            if (json.isNull("gender")) {
                ((CurrentUserRealmProxyInterface) obj).realmSet$gender(null);
            } else {
                ((CurrentUserRealmProxyInterface) obj).realmSet$gender((String) json.getString("gender"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.minh.findtheshipper.models.CurrentUser createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.minh.findtheshipper.models.CurrentUser obj = new com.minh.findtheshipper.models.CurrentUser();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("email")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((CurrentUserRealmProxyInterface) obj).realmSet$email(null);
                } else {
                    ((CurrentUserRealmProxyInterface) obj).realmSet$email((String) reader.nextString());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("name")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((CurrentUserRealmProxyInterface) obj).realmSet$name(null);
                } else {
                    ((CurrentUserRealmProxyInterface) obj).realmSet$name((String) reader.nextString());
                }
            } else if (name.equals("phoneNumber")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((CurrentUserRealmProxyInterface) obj).realmSet$phoneNumber(null);
                } else {
                    ((CurrentUserRealmProxyInterface) obj).realmSet$phoneNumber((String) reader.nextString());
                }
            } else if (name.equals("avatar")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((CurrentUserRealmProxyInterface) obj).realmSet$avatar(null);
                } else {
                    ((CurrentUserRealmProxyInterface) obj).realmSet$avatar((String) reader.nextString());
                }
            } else if (name.equals("gender")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((CurrentUserRealmProxyInterface) obj).realmSet$gender(null);
                } else {
                    ((CurrentUserRealmProxyInterface) obj).realmSet$gender((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'email'.");
        }
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.minh.findtheshipper.models.CurrentUser copyOrUpdate(Realm realm, com.minh.findtheshipper.models.CurrentUser object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.minh.findtheshipper.models.CurrentUser) cachedRealmObject;
        } else {
            com.minh.findtheshipper.models.CurrentUser realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.minh.findtheshipper.models.CurrentUser.class);
                long pkColumnIndex = table.getPrimaryKey();
                String value = ((CurrentUserRealmProxyInterface) object).realmGet$email();
                long rowIndex = Table.NO_MATCH;
                if (value == null) {
                    rowIndex = table.findFirstNull(pkColumnIndex);
                } else {
                    rowIndex = table.findFirstString(pkColumnIndex, value);
                }
                if (rowIndex != Table.NO_MATCH) {
                    try {
                        objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.minh.findtheshipper.models.CurrentUser.class), false, Collections.<String> emptyList());
                        realmObject = new io.realm.CurrentUserRealmProxy();
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

    public static com.minh.findtheshipper.models.CurrentUser copy(Realm realm, com.minh.findtheshipper.models.CurrentUser newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.minh.findtheshipper.models.CurrentUser) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.minh.findtheshipper.models.CurrentUser realmObject = realm.createObjectInternal(com.minh.findtheshipper.models.CurrentUser.class, ((CurrentUserRealmProxyInterface) newObject).realmGet$email(), false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((CurrentUserRealmProxyInterface) realmObject).realmSet$name(((CurrentUserRealmProxyInterface) newObject).realmGet$name());
            ((CurrentUserRealmProxyInterface) realmObject).realmSet$phoneNumber(((CurrentUserRealmProxyInterface) newObject).realmGet$phoneNumber());
            ((CurrentUserRealmProxyInterface) realmObject).realmSet$avatar(((CurrentUserRealmProxyInterface) newObject).realmGet$avatar());
            ((CurrentUserRealmProxyInterface) realmObject).realmSet$gender(((CurrentUserRealmProxyInterface) newObject).realmGet$gender());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.minh.findtheshipper.models.CurrentUser object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.minh.findtheshipper.models.CurrentUser.class);
        long tableNativePtr = table.getNativePtr();
        CurrentUserColumnInfo columnInfo = (CurrentUserColumnInfo) realm.schema.getColumnInfo(com.minh.findtheshipper.models.CurrentUser.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((CurrentUserRealmProxyInterface) object).realmGet$email();
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
        String realmGet$name = ((CurrentUserRealmProxyInterface)object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        String realmGet$phoneNumber = ((CurrentUserRealmProxyInterface)object).realmGet$phoneNumber();
        if (realmGet$phoneNumber != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.phoneNumberIndex, rowIndex, realmGet$phoneNumber, false);
        }
        String realmGet$avatar = ((CurrentUserRealmProxyInterface)object).realmGet$avatar();
        if (realmGet$avatar != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.avatarIndex, rowIndex, realmGet$avatar, false);
        }
        String realmGet$gender = ((CurrentUserRealmProxyInterface)object).realmGet$gender();
        if (realmGet$gender != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.genderIndex, rowIndex, realmGet$gender, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.minh.findtheshipper.models.CurrentUser.class);
        long tableNativePtr = table.getNativePtr();
        CurrentUserColumnInfo columnInfo = (CurrentUserColumnInfo) realm.schema.getColumnInfo(com.minh.findtheshipper.models.CurrentUser.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.minh.findtheshipper.models.CurrentUser object = null;
        while (objects.hasNext()) {
            object = (com.minh.findtheshipper.models.CurrentUser) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((CurrentUserRealmProxyInterface) object).realmGet$email();
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
                String realmGet$name = ((CurrentUserRealmProxyInterface)object).realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                }
                String realmGet$phoneNumber = ((CurrentUserRealmProxyInterface)object).realmGet$phoneNumber();
                if (realmGet$phoneNumber != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.phoneNumberIndex, rowIndex, realmGet$phoneNumber, false);
                }
                String realmGet$avatar = ((CurrentUserRealmProxyInterface)object).realmGet$avatar();
                if (realmGet$avatar != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.avatarIndex, rowIndex, realmGet$avatar, false);
                }
                String realmGet$gender = ((CurrentUserRealmProxyInterface)object).realmGet$gender();
                if (realmGet$gender != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.genderIndex, rowIndex, realmGet$gender, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.minh.findtheshipper.models.CurrentUser object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.minh.findtheshipper.models.CurrentUser.class);
        long tableNativePtr = table.getNativePtr();
        CurrentUserColumnInfo columnInfo = (CurrentUserColumnInfo) realm.schema.getColumnInfo(com.minh.findtheshipper.models.CurrentUser.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((CurrentUserRealmProxyInterface) object).realmGet$email();
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
        String realmGet$name = ((CurrentUserRealmProxyInterface)object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        String realmGet$phoneNumber = ((CurrentUserRealmProxyInterface)object).realmGet$phoneNumber();
        if (realmGet$phoneNumber != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.phoneNumberIndex, rowIndex, realmGet$phoneNumber, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.phoneNumberIndex, rowIndex, false);
        }
        String realmGet$avatar = ((CurrentUserRealmProxyInterface)object).realmGet$avatar();
        if (realmGet$avatar != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.avatarIndex, rowIndex, realmGet$avatar, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.avatarIndex, rowIndex, false);
        }
        String realmGet$gender = ((CurrentUserRealmProxyInterface)object).realmGet$gender();
        if (realmGet$gender != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.genderIndex, rowIndex, realmGet$gender, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.genderIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.minh.findtheshipper.models.CurrentUser.class);
        long tableNativePtr = table.getNativePtr();
        CurrentUserColumnInfo columnInfo = (CurrentUserColumnInfo) realm.schema.getColumnInfo(com.minh.findtheshipper.models.CurrentUser.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.minh.findtheshipper.models.CurrentUser object = null;
        while (objects.hasNext()) {
            object = (com.minh.findtheshipper.models.CurrentUser) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((CurrentUserRealmProxyInterface) object).realmGet$email();
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
                String realmGet$name = ((CurrentUserRealmProxyInterface)object).realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
                }
                String realmGet$phoneNumber = ((CurrentUserRealmProxyInterface)object).realmGet$phoneNumber();
                if (realmGet$phoneNumber != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.phoneNumberIndex, rowIndex, realmGet$phoneNumber, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.phoneNumberIndex, rowIndex, false);
                }
                String realmGet$avatar = ((CurrentUserRealmProxyInterface)object).realmGet$avatar();
                if (realmGet$avatar != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.avatarIndex, rowIndex, realmGet$avatar, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.avatarIndex, rowIndex, false);
                }
                String realmGet$gender = ((CurrentUserRealmProxyInterface)object).realmGet$gender();
                if (realmGet$gender != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.genderIndex, rowIndex, realmGet$gender, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.genderIndex, rowIndex, false);
                }
            }
        }
    }

    public static com.minh.findtheshipper.models.CurrentUser createDetachedCopy(com.minh.findtheshipper.models.CurrentUser realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.minh.findtheshipper.models.CurrentUser unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.minh.findtheshipper.models.CurrentUser)cachedObject.object;
            } else {
                unmanagedObject = (com.minh.findtheshipper.models.CurrentUser)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.minh.findtheshipper.models.CurrentUser();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }
        ((CurrentUserRealmProxyInterface) unmanagedObject).realmSet$email(((CurrentUserRealmProxyInterface) realmObject).realmGet$email());
        ((CurrentUserRealmProxyInterface) unmanagedObject).realmSet$name(((CurrentUserRealmProxyInterface) realmObject).realmGet$name());
        ((CurrentUserRealmProxyInterface) unmanagedObject).realmSet$phoneNumber(((CurrentUserRealmProxyInterface) realmObject).realmGet$phoneNumber());
        ((CurrentUserRealmProxyInterface) unmanagedObject).realmSet$avatar(((CurrentUserRealmProxyInterface) realmObject).realmGet$avatar());
        ((CurrentUserRealmProxyInterface) unmanagedObject).realmSet$gender(((CurrentUserRealmProxyInterface) realmObject).realmGet$gender());
        return unmanagedObject;
    }

    static com.minh.findtheshipper.models.CurrentUser update(Realm realm, com.minh.findtheshipper.models.CurrentUser realmObject, com.minh.findtheshipper.models.CurrentUser newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((CurrentUserRealmProxyInterface) realmObject).realmSet$name(((CurrentUserRealmProxyInterface) newObject).realmGet$name());
        ((CurrentUserRealmProxyInterface) realmObject).realmSet$phoneNumber(((CurrentUserRealmProxyInterface) newObject).realmGet$phoneNumber());
        ((CurrentUserRealmProxyInterface) realmObject).realmSet$avatar(((CurrentUserRealmProxyInterface) newObject).realmGet$avatar());
        ((CurrentUserRealmProxyInterface) realmObject).realmSet$gender(((CurrentUserRealmProxyInterface) newObject).realmGet$gender());
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("CurrentUser = proxy[");
        stringBuilder.append("{email:");
        stringBuilder.append(realmGet$email() != null ? realmGet$email() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{phoneNumber:");
        stringBuilder.append(realmGet$phoneNumber() != null ? realmGet$phoneNumber() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{avatar:");
        stringBuilder.append(realmGet$avatar() != null ? realmGet$avatar() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{gender:");
        stringBuilder.append(realmGet$gender() != null ? realmGet$gender() : "null");
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
        CurrentUserRealmProxy aCurrentUser = (CurrentUserRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aCurrentUser.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aCurrentUser.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aCurrentUser.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
