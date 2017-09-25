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

public class UserRealmProxy extends com.minh.findtheshipper.models.User
    implements RealmObjectProxy, UserRealmProxyInterface {

    static final class UserColumnInfo extends ColumnInfo {
        long emailIndex;
        long fullNameIndex;
        long phoneNumberIndex;
        long avatarIndex;
        long orderArrayListIndex;
        long orderListSaveIndex;

        UserColumnInfo(SharedRealm realm, Table table) {
            super(6);
            this.emailIndex = addColumnDetails(table, "email", RealmFieldType.STRING);
            this.fullNameIndex = addColumnDetails(table, "fullName", RealmFieldType.STRING);
            this.phoneNumberIndex = addColumnDetails(table, "phoneNumber", RealmFieldType.STRING);
            this.avatarIndex = addColumnDetails(table, "avatar", RealmFieldType.INTEGER);
            this.orderArrayListIndex = addColumnDetails(table, "orderArrayList", RealmFieldType.LIST);
            this.orderListSaveIndex = addColumnDetails(table, "orderListSave", RealmFieldType.LIST);
        }

        UserColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new UserColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final UserColumnInfo src = (UserColumnInfo) rawSrc;
            final UserColumnInfo dst = (UserColumnInfo) rawDst;
            dst.emailIndex = src.emailIndex;
            dst.fullNameIndex = src.fullNameIndex;
            dst.phoneNumberIndex = src.phoneNumberIndex;
            dst.avatarIndex = src.avatarIndex;
            dst.orderArrayListIndex = src.orderArrayListIndex;
            dst.orderListSaveIndex = src.orderListSaveIndex;
        }
    }

    private UserColumnInfo columnInfo;
    private ProxyState<com.minh.findtheshipper.models.User> proxyState;
    private RealmList<com.minh.findtheshipper.models.Order> orderArrayListRealmList;
    private RealmList<com.minh.findtheshipper.models.Order> orderListSaveRealmList;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("email");
        fieldNames.add("fullName");
        fieldNames.add("phoneNumber");
        fieldNames.add("avatar");
        fieldNames.add("orderArrayList");
        fieldNames.add("orderListSave");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    UserRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (UserColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.minh.findtheshipper.models.User>(this);
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
    public String realmGet$fullName() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.fullNameIndex);
    }

    @Override
    public void realmSet$fullName(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.fullNameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.fullNameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.fullNameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.fullNameIndex, value);
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
    public int realmGet$avatar() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.avatarIndex);
    }

    @Override
    public void realmSet$avatar(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.avatarIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.avatarIndex, value);
    }

    @Override
    public RealmList<com.minh.findtheshipper.models.Order> realmGet$orderArrayList() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (orderArrayListRealmList != null) {
            return orderArrayListRealmList;
        } else {
            LinkView linkView = proxyState.getRow$realm().getLinkList(columnInfo.orderArrayListIndex);
            orderArrayListRealmList = new RealmList<com.minh.findtheshipper.models.Order>(com.minh.findtheshipper.models.Order.class, linkView, proxyState.getRealm$realm());
            return orderArrayListRealmList;
        }
    }

    @Override
    public void realmSet$orderArrayList(RealmList<com.minh.findtheshipper.models.Order> value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("orderArrayList")) {
                return;
            }
            if (value != null && !value.isManaged()) {
                final Realm realm = (Realm) proxyState.getRealm$realm();
                final RealmList<com.minh.findtheshipper.models.Order> original = value;
                value = new RealmList<com.minh.findtheshipper.models.Order>();
                for (com.minh.findtheshipper.models.Order item : original) {
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealm(item));
                    }
                }
            }
        }

        proxyState.getRealm$realm().checkIfValid();
        LinkView links = proxyState.getRow$realm().getLinkList(columnInfo.orderArrayListIndex);
        links.clear();
        if (value == null) {
            return;
        }
        for (RealmModel linkedObject : (RealmList<? extends RealmModel>) value) {
            if (!(RealmObject.isManaged(linkedObject) && RealmObject.isValid(linkedObject))) {
                throw new IllegalArgumentException("Each element of 'value' must be a valid managed object.");
            }
            if (((RealmObjectProxy)linkedObject).realmGet$proxyState().getRealm$realm() != proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("Each element of 'value' must belong to the same Realm.");
            }
            links.add(((RealmObjectProxy)linkedObject).realmGet$proxyState().getRow$realm().getIndex());
        }
    }

    @Override
    public RealmList<com.minh.findtheshipper.models.Order> realmGet$orderListSave() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (orderListSaveRealmList != null) {
            return orderListSaveRealmList;
        } else {
            LinkView linkView = proxyState.getRow$realm().getLinkList(columnInfo.orderListSaveIndex);
            orderListSaveRealmList = new RealmList<com.minh.findtheshipper.models.Order>(com.minh.findtheshipper.models.Order.class, linkView, proxyState.getRealm$realm());
            return orderListSaveRealmList;
        }
    }

    @Override
    public void realmSet$orderListSave(RealmList<com.minh.findtheshipper.models.Order> value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("orderListSave")) {
                return;
            }
            if (value != null && !value.isManaged()) {
                final Realm realm = (Realm) proxyState.getRealm$realm();
                final RealmList<com.minh.findtheshipper.models.Order> original = value;
                value = new RealmList<com.minh.findtheshipper.models.Order>();
                for (com.minh.findtheshipper.models.Order item : original) {
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealm(item));
                    }
                }
            }
        }

        proxyState.getRealm$realm().checkIfValid();
        LinkView links = proxyState.getRow$realm().getLinkList(columnInfo.orderListSaveIndex);
        links.clear();
        if (value == null) {
            return;
        }
        for (RealmModel linkedObject : (RealmList<? extends RealmModel>) value) {
            if (!(RealmObject.isManaged(linkedObject) && RealmObject.isValid(linkedObject))) {
                throw new IllegalArgumentException("Each element of 'value' must be a valid managed object.");
            }
            if (((RealmObjectProxy)linkedObject).realmGet$proxyState().getRealm$realm() != proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("Each element of 'value' must belong to the same Realm.");
            }
            links.add(((RealmObjectProxy)linkedObject).realmGet$proxyState().getRow$realm().getIndex());
        }
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("User")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("User");
            realmObjectSchema.add("email", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("fullName", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("phoneNumber", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("avatar", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
            if (!realmSchema.contains("Order")) {
                OrderRealmProxy.createRealmObjectSchema(realmSchema);
            }
            realmObjectSchema.add("orderArrayList", RealmFieldType.LIST, realmSchema.get("Order"));
            if (!realmSchema.contains("Order")) {
                OrderRealmProxy.createRealmObjectSchema(realmSchema);
            }
            realmObjectSchema.add("orderListSave", RealmFieldType.LIST, realmSchema.get("Order"));
            return realmObjectSchema;
        }
        return realmSchema.get("User");
    }

    public static UserColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (!sharedRealm.hasTable("class_User")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'User' class is missing from the schema for this Realm.");
        }
        Table table = sharedRealm.getTable("class_User");
        final long columnCount = table.getColumnCount();
        if (columnCount != 6) {
            if (columnCount < 6) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 6 but was " + columnCount);
            }
            if (allowExtraColumns) {
                RealmLog.debug("Field count is more than expected - expected 6 but was %1$d", columnCount);
            } else {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 6 but was " + columnCount);
            }
        }
        Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
        for (long i = 0; i < columnCount; i++) {
            columnTypes.put(table.getColumnName(i), table.getColumnType(i));
        }

        final UserColumnInfo columnInfo = new UserColumnInfo(sharedRealm, table);

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
        if (!columnTypes.containsKey("fullName")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'fullName' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("fullName") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'fullName' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.fullNameIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'fullName' is required. Either set @Required to field 'fullName' or migrate using RealmObjectSchema.setNullable().");
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
        if (columnTypes.get("avatar") != RealmFieldType.INTEGER) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'int' for field 'avatar' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.avatarIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'avatar' does support null values in the existing Realm file. Use corresponding boxed type for field 'avatar' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("orderArrayList")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'orderArrayList'");
        }
        if (columnTypes.get("orderArrayList") != RealmFieldType.LIST) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Order' for field 'orderArrayList'");
        }
        if (!sharedRealm.hasTable("class_Order")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing class 'class_Order' for field 'orderArrayList'");
        }
        Table table_4 = sharedRealm.getTable("class_Order");
        if (!table.getLinkTarget(columnInfo.orderArrayListIndex).hasSameSchema(table_4)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid RealmList type for field 'orderArrayList': '" + table.getLinkTarget(columnInfo.orderArrayListIndex).getName() + "' expected - was '" + table_4.getName() + "'");
        }
        if (!columnTypes.containsKey("orderListSave")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'orderListSave'");
        }
        if (columnTypes.get("orderListSave") != RealmFieldType.LIST) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Order' for field 'orderListSave'");
        }
        if (!sharedRealm.hasTable("class_Order")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing class 'class_Order' for field 'orderListSave'");
        }
        Table table_5 = sharedRealm.getTable("class_Order");
        if (!table.getLinkTarget(columnInfo.orderListSaveIndex).hasSameSchema(table_5)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid RealmList type for field 'orderListSave': '" + table.getLinkTarget(columnInfo.orderListSaveIndex).getName() + "' expected - was '" + table_5.getName() + "'");
        }

        return columnInfo;
    }

    public static String getTableName() {
        return "class_User";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.minh.findtheshipper.models.User createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(2);
        com.minh.findtheshipper.models.User obj = null;
        if (update) {
            Table table = realm.getTable(com.minh.findtheshipper.models.User.class);
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
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.minh.findtheshipper.models.User.class), false, Collections.<String> emptyList());
                    obj = new io.realm.UserRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("orderArrayList")) {
                excludeFields.add("orderArrayList");
            }
            if (json.has("orderListSave")) {
                excludeFields.add("orderListSave");
            }
            if (json.has("email")) {
                if (json.isNull("email")) {
                    obj = (io.realm.UserRealmProxy) realm.createObjectInternal(com.minh.findtheshipper.models.User.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.UserRealmProxy) realm.createObjectInternal(com.minh.findtheshipper.models.User.class, json.getString("email"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'email'.");
            }
        }
        if (json.has("fullName")) {
            if (json.isNull("fullName")) {
                ((UserRealmProxyInterface) obj).realmSet$fullName(null);
            } else {
                ((UserRealmProxyInterface) obj).realmSet$fullName((String) json.getString("fullName"));
            }
        }
        if (json.has("phoneNumber")) {
            if (json.isNull("phoneNumber")) {
                ((UserRealmProxyInterface) obj).realmSet$phoneNumber(null);
            } else {
                ((UserRealmProxyInterface) obj).realmSet$phoneNumber((String) json.getString("phoneNumber"));
            }
        }
        if (json.has("avatar")) {
            if (json.isNull("avatar")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'avatar' to null.");
            } else {
                ((UserRealmProxyInterface) obj).realmSet$avatar((int) json.getInt("avatar"));
            }
        }
        if (json.has("orderArrayList")) {
            if (json.isNull("orderArrayList")) {
                ((UserRealmProxyInterface) obj).realmSet$orderArrayList(null);
            } else {
                ((UserRealmProxyInterface) obj).realmGet$orderArrayList().clear();
                JSONArray array = json.getJSONArray("orderArrayList");
                for (int i = 0; i < array.length(); i++) {
                    com.minh.findtheshipper.models.Order item = OrderRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    ((UserRealmProxyInterface) obj).realmGet$orderArrayList().add(item);
                }
            }
        }
        if (json.has("orderListSave")) {
            if (json.isNull("orderListSave")) {
                ((UserRealmProxyInterface) obj).realmSet$orderListSave(null);
            } else {
                ((UserRealmProxyInterface) obj).realmGet$orderListSave().clear();
                JSONArray array = json.getJSONArray("orderListSave");
                for (int i = 0; i < array.length(); i++) {
                    com.minh.findtheshipper.models.Order item = OrderRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    ((UserRealmProxyInterface) obj).realmGet$orderListSave().add(item);
                }
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.minh.findtheshipper.models.User createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.minh.findtheshipper.models.User obj = new com.minh.findtheshipper.models.User();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("email")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((UserRealmProxyInterface) obj).realmSet$email(null);
                } else {
                    ((UserRealmProxyInterface) obj).realmSet$email((String) reader.nextString());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("fullName")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((UserRealmProxyInterface) obj).realmSet$fullName(null);
                } else {
                    ((UserRealmProxyInterface) obj).realmSet$fullName((String) reader.nextString());
                }
            } else if (name.equals("phoneNumber")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((UserRealmProxyInterface) obj).realmSet$phoneNumber(null);
                } else {
                    ((UserRealmProxyInterface) obj).realmSet$phoneNumber((String) reader.nextString());
                }
            } else if (name.equals("avatar")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'avatar' to null.");
                } else {
                    ((UserRealmProxyInterface) obj).realmSet$avatar((int) reader.nextInt());
                }
            } else if (name.equals("orderArrayList")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((UserRealmProxyInterface) obj).realmSet$orderArrayList(null);
                } else {
                    ((UserRealmProxyInterface) obj).realmSet$orderArrayList(new RealmList<com.minh.findtheshipper.models.Order>());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.minh.findtheshipper.models.Order item = OrderRealmProxy.createUsingJsonStream(realm, reader);
                        ((UserRealmProxyInterface) obj).realmGet$orderArrayList().add(item);
                    }
                    reader.endArray();
                }
            } else if (name.equals("orderListSave")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((UserRealmProxyInterface) obj).realmSet$orderListSave(null);
                } else {
                    ((UserRealmProxyInterface) obj).realmSet$orderListSave(new RealmList<com.minh.findtheshipper.models.Order>());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.minh.findtheshipper.models.Order item = OrderRealmProxy.createUsingJsonStream(realm, reader);
                        ((UserRealmProxyInterface) obj).realmGet$orderListSave().add(item);
                    }
                    reader.endArray();
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

    public static com.minh.findtheshipper.models.User copyOrUpdate(Realm realm, com.minh.findtheshipper.models.User object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.minh.findtheshipper.models.User) cachedRealmObject;
        } else {
            com.minh.findtheshipper.models.User realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.minh.findtheshipper.models.User.class);
                long pkColumnIndex = table.getPrimaryKey();
                String value = ((UserRealmProxyInterface) object).realmGet$email();
                long rowIndex = Table.NO_MATCH;
                if (value == null) {
                    rowIndex = table.findFirstNull(pkColumnIndex);
                } else {
                    rowIndex = table.findFirstString(pkColumnIndex, value);
                }
                if (rowIndex != Table.NO_MATCH) {
                    try {
                        objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.minh.findtheshipper.models.User.class), false, Collections.<String> emptyList());
                        realmObject = new io.realm.UserRealmProxy();
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

    public static com.minh.findtheshipper.models.User copy(Realm realm, com.minh.findtheshipper.models.User newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.minh.findtheshipper.models.User) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.minh.findtheshipper.models.User realmObject = realm.createObjectInternal(com.minh.findtheshipper.models.User.class, ((UserRealmProxyInterface) newObject).realmGet$email(), false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((UserRealmProxyInterface) realmObject).realmSet$fullName(((UserRealmProxyInterface) newObject).realmGet$fullName());
            ((UserRealmProxyInterface) realmObject).realmSet$phoneNumber(((UserRealmProxyInterface) newObject).realmGet$phoneNumber());
            ((UserRealmProxyInterface) realmObject).realmSet$avatar(((UserRealmProxyInterface) newObject).realmGet$avatar());

            RealmList<com.minh.findtheshipper.models.Order> orderArrayListList = ((UserRealmProxyInterface) newObject).realmGet$orderArrayList();
            if (orderArrayListList != null) {
                RealmList<com.minh.findtheshipper.models.Order> orderArrayListRealmList = ((UserRealmProxyInterface) realmObject).realmGet$orderArrayList();
                for (int i = 0; i < orderArrayListList.size(); i++) {
                    com.minh.findtheshipper.models.Order orderArrayListItem = orderArrayListList.get(i);
                    com.minh.findtheshipper.models.Order cacheorderArrayList = (com.minh.findtheshipper.models.Order) cache.get(orderArrayListItem);
                    if (cacheorderArrayList != null) {
                        orderArrayListRealmList.add(cacheorderArrayList);
                    } else {
                        orderArrayListRealmList.add(OrderRealmProxy.copyOrUpdate(realm, orderArrayListList.get(i), update, cache));
                    }
                }
            }


            RealmList<com.minh.findtheshipper.models.Order> orderListSaveList = ((UserRealmProxyInterface) newObject).realmGet$orderListSave();
            if (orderListSaveList != null) {
                RealmList<com.minh.findtheshipper.models.Order> orderListSaveRealmList = ((UserRealmProxyInterface) realmObject).realmGet$orderListSave();
                for (int i = 0; i < orderListSaveList.size(); i++) {
                    com.minh.findtheshipper.models.Order orderListSaveItem = orderListSaveList.get(i);
                    com.minh.findtheshipper.models.Order cacheorderListSave = (com.minh.findtheshipper.models.Order) cache.get(orderListSaveItem);
                    if (cacheorderListSave != null) {
                        orderListSaveRealmList.add(cacheorderListSave);
                    } else {
                        orderListSaveRealmList.add(OrderRealmProxy.copyOrUpdate(realm, orderListSaveList.get(i), update, cache));
                    }
                }
            }

            return realmObject;
        }
    }

    public static long insert(Realm realm, com.minh.findtheshipper.models.User object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.minh.findtheshipper.models.User.class);
        long tableNativePtr = table.getNativePtr();
        UserColumnInfo columnInfo = (UserColumnInfo) realm.schema.getColumnInfo(com.minh.findtheshipper.models.User.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((UserRealmProxyInterface) object).realmGet$email();
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
        String realmGet$fullName = ((UserRealmProxyInterface)object).realmGet$fullName();
        if (realmGet$fullName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.fullNameIndex, rowIndex, realmGet$fullName, false);
        }
        String realmGet$phoneNumber = ((UserRealmProxyInterface)object).realmGet$phoneNumber();
        if (realmGet$phoneNumber != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.phoneNumberIndex, rowIndex, realmGet$phoneNumber, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.avatarIndex, rowIndex, ((UserRealmProxyInterface)object).realmGet$avatar(), false);

        RealmList<com.minh.findtheshipper.models.Order> orderArrayListList = ((UserRealmProxyInterface) object).realmGet$orderArrayList();
        if (orderArrayListList != null) {
            long orderArrayListNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.orderArrayListIndex, rowIndex);
            for (com.minh.findtheshipper.models.Order orderArrayListItem : orderArrayListList) {
                Long cacheItemIndexorderArrayList = cache.get(orderArrayListItem);
                if (cacheItemIndexorderArrayList == null) {
                    cacheItemIndexorderArrayList = OrderRealmProxy.insert(realm, orderArrayListItem, cache);
                }
                LinkView.nativeAdd(orderArrayListNativeLinkViewPtr, cacheItemIndexorderArrayList);
            }
        }


        RealmList<com.minh.findtheshipper.models.Order> orderListSaveList = ((UserRealmProxyInterface) object).realmGet$orderListSave();
        if (orderListSaveList != null) {
            long orderListSaveNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.orderListSaveIndex, rowIndex);
            for (com.minh.findtheshipper.models.Order orderListSaveItem : orderListSaveList) {
                Long cacheItemIndexorderListSave = cache.get(orderListSaveItem);
                if (cacheItemIndexorderListSave == null) {
                    cacheItemIndexorderListSave = OrderRealmProxy.insert(realm, orderListSaveItem, cache);
                }
                LinkView.nativeAdd(orderListSaveNativeLinkViewPtr, cacheItemIndexorderListSave);
            }
        }

        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.minh.findtheshipper.models.User.class);
        long tableNativePtr = table.getNativePtr();
        UserColumnInfo columnInfo = (UserColumnInfo) realm.schema.getColumnInfo(com.minh.findtheshipper.models.User.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.minh.findtheshipper.models.User object = null;
        while (objects.hasNext()) {
            object = (com.minh.findtheshipper.models.User) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((UserRealmProxyInterface) object).realmGet$email();
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
                String realmGet$fullName = ((UserRealmProxyInterface)object).realmGet$fullName();
                if (realmGet$fullName != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.fullNameIndex, rowIndex, realmGet$fullName, false);
                }
                String realmGet$phoneNumber = ((UserRealmProxyInterface)object).realmGet$phoneNumber();
                if (realmGet$phoneNumber != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.phoneNumberIndex, rowIndex, realmGet$phoneNumber, false);
                }
                Table.nativeSetLong(tableNativePtr, columnInfo.avatarIndex, rowIndex, ((UserRealmProxyInterface)object).realmGet$avatar(), false);

                RealmList<com.minh.findtheshipper.models.Order> orderArrayListList = ((UserRealmProxyInterface) object).realmGet$orderArrayList();
                if (orderArrayListList != null) {
                    long orderArrayListNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.orderArrayListIndex, rowIndex);
                    for (com.minh.findtheshipper.models.Order orderArrayListItem : orderArrayListList) {
                        Long cacheItemIndexorderArrayList = cache.get(orderArrayListItem);
                        if (cacheItemIndexorderArrayList == null) {
                            cacheItemIndexorderArrayList = OrderRealmProxy.insert(realm, orderArrayListItem, cache);
                        }
                        LinkView.nativeAdd(orderArrayListNativeLinkViewPtr, cacheItemIndexorderArrayList);
                    }
                }


                RealmList<com.minh.findtheshipper.models.Order> orderListSaveList = ((UserRealmProxyInterface) object).realmGet$orderListSave();
                if (orderListSaveList != null) {
                    long orderListSaveNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.orderListSaveIndex, rowIndex);
                    for (com.minh.findtheshipper.models.Order orderListSaveItem : orderListSaveList) {
                        Long cacheItemIndexorderListSave = cache.get(orderListSaveItem);
                        if (cacheItemIndexorderListSave == null) {
                            cacheItemIndexorderListSave = OrderRealmProxy.insert(realm, orderListSaveItem, cache);
                        }
                        LinkView.nativeAdd(orderListSaveNativeLinkViewPtr, cacheItemIndexorderListSave);
                    }
                }

            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.minh.findtheshipper.models.User object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.minh.findtheshipper.models.User.class);
        long tableNativePtr = table.getNativePtr();
        UserColumnInfo columnInfo = (UserColumnInfo) realm.schema.getColumnInfo(com.minh.findtheshipper.models.User.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((UserRealmProxyInterface) object).realmGet$email();
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
        String realmGet$fullName = ((UserRealmProxyInterface)object).realmGet$fullName();
        if (realmGet$fullName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.fullNameIndex, rowIndex, realmGet$fullName, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.fullNameIndex, rowIndex, false);
        }
        String realmGet$phoneNumber = ((UserRealmProxyInterface)object).realmGet$phoneNumber();
        if (realmGet$phoneNumber != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.phoneNumberIndex, rowIndex, realmGet$phoneNumber, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.phoneNumberIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.avatarIndex, rowIndex, ((UserRealmProxyInterface)object).realmGet$avatar(), false);

        long orderArrayListNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.orderArrayListIndex, rowIndex);
        LinkView.nativeClear(orderArrayListNativeLinkViewPtr);
        RealmList<com.minh.findtheshipper.models.Order> orderArrayListList = ((UserRealmProxyInterface) object).realmGet$orderArrayList();
        if (orderArrayListList != null) {
            for (com.minh.findtheshipper.models.Order orderArrayListItem : orderArrayListList) {
                Long cacheItemIndexorderArrayList = cache.get(orderArrayListItem);
                if (cacheItemIndexorderArrayList == null) {
                    cacheItemIndexorderArrayList = OrderRealmProxy.insertOrUpdate(realm, orderArrayListItem, cache);
                }
                LinkView.nativeAdd(orderArrayListNativeLinkViewPtr, cacheItemIndexorderArrayList);
            }
        }


        long orderListSaveNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.orderListSaveIndex, rowIndex);
        LinkView.nativeClear(orderListSaveNativeLinkViewPtr);
        RealmList<com.minh.findtheshipper.models.Order> orderListSaveList = ((UserRealmProxyInterface) object).realmGet$orderListSave();
        if (orderListSaveList != null) {
            for (com.minh.findtheshipper.models.Order orderListSaveItem : orderListSaveList) {
                Long cacheItemIndexorderListSave = cache.get(orderListSaveItem);
                if (cacheItemIndexorderListSave == null) {
                    cacheItemIndexorderListSave = OrderRealmProxy.insertOrUpdate(realm, orderListSaveItem, cache);
                }
                LinkView.nativeAdd(orderListSaveNativeLinkViewPtr, cacheItemIndexorderListSave);
            }
        }

        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.minh.findtheshipper.models.User.class);
        long tableNativePtr = table.getNativePtr();
        UserColumnInfo columnInfo = (UserColumnInfo) realm.schema.getColumnInfo(com.minh.findtheshipper.models.User.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.minh.findtheshipper.models.User object = null;
        while (objects.hasNext()) {
            object = (com.minh.findtheshipper.models.User) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((UserRealmProxyInterface) object).realmGet$email();
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
                String realmGet$fullName = ((UserRealmProxyInterface)object).realmGet$fullName();
                if (realmGet$fullName != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.fullNameIndex, rowIndex, realmGet$fullName, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.fullNameIndex, rowIndex, false);
                }
                String realmGet$phoneNumber = ((UserRealmProxyInterface)object).realmGet$phoneNumber();
                if (realmGet$phoneNumber != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.phoneNumberIndex, rowIndex, realmGet$phoneNumber, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.phoneNumberIndex, rowIndex, false);
                }
                Table.nativeSetLong(tableNativePtr, columnInfo.avatarIndex, rowIndex, ((UserRealmProxyInterface)object).realmGet$avatar(), false);

                long orderArrayListNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.orderArrayListIndex, rowIndex);
                LinkView.nativeClear(orderArrayListNativeLinkViewPtr);
                RealmList<com.minh.findtheshipper.models.Order> orderArrayListList = ((UserRealmProxyInterface) object).realmGet$orderArrayList();
                if (orderArrayListList != null) {
                    for (com.minh.findtheshipper.models.Order orderArrayListItem : orderArrayListList) {
                        Long cacheItemIndexorderArrayList = cache.get(orderArrayListItem);
                        if (cacheItemIndexorderArrayList == null) {
                            cacheItemIndexorderArrayList = OrderRealmProxy.insertOrUpdate(realm, orderArrayListItem, cache);
                        }
                        LinkView.nativeAdd(orderArrayListNativeLinkViewPtr, cacheItemIndexorderArrayList);
                    }
                }


                long orderListSaveNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.orderListSaveIndex, rowIndex);
                LinkView.nativeClear(orderListSaveNativeLinkViewPtr);
                RealmList<com.minh.findtheshipper.models.Order> orderListSaveList = ((UserRealmProxyInterface) object).realmGet$orderListSave();
                if (orderListSaveList != null) {
                    for (com.minh.findtheshipper.models.Order orderListSaveItem : orderListSaveList) {
                        Long cacheItemIndexorderListSave = cache.get(orderListSaveItem);
                        if (cacheItemIndexorderListSave == null) {
                            cacheItemIndexorderListSave = OrderRealmProxy.insertOrUpdate(realm, orderListSaveItem, cache);
                        }
                        LinkView.nativeAdd(orderListSaveNativeLinkViewPtr, cacheItemIndexorderListSave);
                    }
                }

            }
        }
    }

    public static com.minh.findtheshipper.models.User createDetachedCopy(com.minh.findtheshipper.models.User realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.minh.findtheshipper.models.User unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.minh.findtheshipper.models.User)cachedObject.object;
            } else {
                unmanagedObject = (com.minh.findtheshipper.models.User)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.minh.findtheshipper.models.User();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }
        ((UserRealmProxyInterface) unmanagedObject).realmSet$email(((UserRealmProxyInterface) realmObject).realmGet$email());
        ((UserRealmProxyInterface) unmanagedObject).realmSet$fullName(((UserRealmProxyInterface) realmObject).realmGet$fullName());
        ((UserRealmProxyInterface) unmanagedObject).realmSet$phoneNumber(((UserRealmProxyInterface) realmObject).realmGet$phoneNumber());
        ((UserRealmProxyInterface) unmanagedObject).realmSet$avatar(((UserRealmProxyInterface) realmObject).realmGet$avatar());

        // Deep copy of orderArrayList
        if (currentDepth == maxDepth) {
            ((UserRealmProxyInterface) unmanagedObject).realmSet$orderArrayList(null);
        } else {
            RealmList<com.minh.findtheshipper.models.Order> managedorderArrayListList = ((UserRealmProxyInterface) realmObject).realmGet$orderArrayList();
            RealmList<com.minh.findtheshipper.models.Order> unmanagedorderArrayListList = new RealmList<com.minh.findtheshipper.models.Order>();
            ((UserRealmProxyInterface) unmanagedObject).realmSet$orderArrayList(unmanagedorderArrayListList);
            int nextDepth = currentDepth + 1;
            int size = managedorderArrayListList.size();
            for (int i = 0; i < size; i++) {
                com.minh.findtheshipper.models.Order item = OrderRealmProxy.createDetachedCopy(managedorderArrayListList.get(i), nextDepth, maxDepth, cache);
                unmanagedorderArrayListList.add(item);
            }
        }

        // Deep copy of orderListSave
        if (currentDepth == maxDepth) {
            ((UserRealmProxyInterface) unmanagedObject).realmSet$orderListSave(null);
        } else {
            RealmList<com.minh.findtheshipper.models.Order> managedorderListSaveList = ((UserRealmProxyInterface) realmObject).realmGet$orderListSave();
            RealmList<com.minh.findtheshipper.models.Order> unmanagedorderListSaveList = new RealmList<com.minh.findtheshipper.models.Order>();
            ((UserRealmProxyInterface) unmanagedObject).realmSet$orderListSave(unmanagedorderListSaveList);
            int nextDepth = currentDepth + 1;
            int size = managedorderListSaveList.size();
            for (int i = 0; i < size; i++) {
                com.minh.findtheshipper.models.Order item = OrderRealmProxy.createDetachedCopy(managedorderListSaveList.get(i), nextDepth, maxDepth, cache);
                unmanagedorderListSaveList.add(item);
            }
        }
        return unmanagedObject;
    }

    static com.minh.findtheshipper.models.User update(Realm realm, com.minh.findtheshipper.models.User realmObject, com.minh.findtheshipper.models.User newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((UserRealmProxyInterface) realmObject).realmSet$fullName(((UserRealmProxyInterface) newObject).realmGet$fullName());
        ((UserRealmProxyInterface) realmObject).realmSet$phoneNumber(((UserRealmProxyInterface) newObject).realmGet$phoneNumber());
        ((UserRealmProxyInterface) realmObject).realmSet$avatar(((UserRealmProxyInterface) newObject).realmGet$avatar());
        RealmList<com.minh.findtheshipper.models.Order> orderArrayListList = ((UserRealmProxyInterface) newObject).realmGet$orderArrayList();
        RealmList<com.minh.findtheshipper.models.Order> orderArrayListRealmList = ((UserRealmProxyInterface) realmObject).realmGet$orderArrayList();
        orderArrayListRealmList.clear();
        if (orderArrayListList != null) {
            for (int i = 0; i < orderArrayListList.size(); i++) {
                com.minh.findtheshipper.models.Order orderArrayListItem = orderArrayListList.get(i);
                com.minh.findtheshipper.models.Order cacheorderArrayList = (com.minh.findtheshipper.models.Order) cache.get(orderArrayListItem);
                if (cacheorderArrayList != null) {
                    orderArrayListRealmList.add(cacheorderArrayList);
                } else {
                    orderArrayListRealmList.add(OrderRealmProxy.copyOrUpdate(realm, orderArrayListList.get(i), true, cache));
                }
            }
        }
        RealmList<com.minh.findtheshipper.models.Order> orderListSaveList = ((UserRealmProxyInterface) newObject).realmGet$orderListSave();
        RealmList<com.minh.findtheshipper.models.Order> orderListSaveRealmList = ((UserRealmProxyInterface) realmObject).realmGet$orderListSave();
        orderListSaveRealmList.clear();
        if (orderListSaveList != null) {
            for (int i = 0; i < orderListSaveList.size(); i++) {
                com.minh.findtheshipper.models.Order orderListSaveItem = orderListSaveList.get(i);
                com.minh.findtheshipper.models.Order cacheorderListSave = (com.minh.findtheshipper.models.Order) cache.get(orderListSaveItem);
                if (cacheorderListSave != null) {
                    orderListSaveRealmList.add(cacheorderListSave);
                } else {
                    orderListSaveRealmList.add(OrderRealmProxy.copyOrUpdate(realm, orderListSaveList.get(i), true, cache));
                }
            }
        }
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("User = proxy[");
        stringBuilder.append("{email:");
        stringBuilder.append(realmGet$email() != null ? realmGet$email() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{fullName:");
        stringBuilder.append(realmGet$fullName() != null ? realmGet$fullName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{phoneNumber:");
        stringBuilder.append(realmGet$phoneNumber() != null ? realmGet$phoneNumber() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{avatar:");
        stringBuilder.append(realmGet$avatar());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{orderArrayList:");
        stringBuilder.append("RealmList<Order>[").append(realmGet$orderArrayList().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{orderListSave:");
        stringBuilder.append("RealmList<Order>[").append(realmGet$orderListSave().size()).append("]");
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
        UserRealmProxy aUser = (UserRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aUser.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aUser.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aUser.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
