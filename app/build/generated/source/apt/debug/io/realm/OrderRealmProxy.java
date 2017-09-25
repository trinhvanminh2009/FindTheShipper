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

public class OrderRealmProxy extends com.minh.findtheshipper.models.Order
    implements RealmObjectProxy, OrderRealmProxyInterface {

    static final class OrderColumnInfo extends ColumnInfo {
        long orderIDIndex;
        long statusIndex;
        long startPointIndex;
        long finishPointIndex;
        long advancedMoneyIndex;
        long shipMoneyIndex;
        long noteIndex;
        long distanceIndex;
        long phoneNumberIndex;
        long dateTimeIndex;
        long likesIndex;
        long dislikesIndex;
        long commentsIndex;
        long saveOrderIndex;

        OrderColumnInfo(SharedRealm realm, Table table) {
            super(14);
            this.orderIDIndex = addColumnDetails(table, "orderID", RealmFieldType.STRING);
            this.statusIndex = addColumnDetails(table, "status", RealmFieldType.STRING);
            this.startPointIndex = addColumnDetails(table, "startPoint", RealmFieldType.STRING);
            this.finishPointIndex = addColumnDetails(table, "finishPoint", RealmFieldType.STRING);
            this.advancedMoneyIndex = addColumnDetails(table, "advancedMoney", RealmFieldType.STRING);
            this.shipMoneyIndex = addColumnDetails(table, "shipMoney", RealmFieldType.STRING);
            this.noteIndex = addColumnDetails(table, "note", RealmFieldType.STRING);
            this.distanceIndex = addColumnDetails(table, "distance", RealmFieldType.STRING);
            this.phoneNumberIndex = addColumnDetails(table, "phoneNumber", RealmFieldType.STRING);
            this.dateTimeIndex = addColumnDetails(table, "dateTime", RealmFieldType.STRING);
            this.likesIndex = addColumnDetails(table, "likes", RealmFieldType.LIST);
            this.dislikesIndex = addColumnDetails(table, "dislikes", RealmFieldType.LIST);
            this.commentsIndex = addColumnDetails(table, "comments", RealmFieldType.LIST);
            this.saveOrderIndex = addColumnDetails(table, "saveOrder", RealmFieldType.BOOLEAN);
        }

        OrderColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new OrderColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final OrderColumnInfo src = (OrderColumnInfo) rawSrc;
            final OrderColumnInfo dst = (OrderColumnInfo) rawDst;
            dst.orderIDIndex = src.orderIDIndex;
            dst.statusIndex = src.statusIndex;
            dst.startPointIndex = src.startPointIndex;
            dst.finishPointIndex = src.finishPointIndex;
            dst.advancedMoneyIndex = src.advancedMoneyIndex;
            dst.shipMoneyIndex = src.shipMoneyIndex;
            dst.noteIndex = src.noteIndex;
            dst.distanceIndex = src.distanceIndex;
            dst.phoneNumberIndex = src.phoneNumberIndex;
            dst.dateTimeIndex = src.dateTimeIndex;
            dst.likesIndex = src.likesIndex;
            dst.dislikesIndex = src.dislikesIndex;
            dst.commentsIndex = src.commentsIndex;
            dst.saveOrderIndex = src.saveOrderIndex;
        }
    }

    private OrderColumnInfo columnInfo;
    private ProxyState<com.minh.findtheshipper.models.Order> proxyState;
    private RealmList<com.minh.findtheshipper.models.Like> likesRealmList;
    private RealmList<com.minh.findtheshipper.models.Dislike> dislikesRealmList;
    private RealmList<com.minh.findtheshipper.models.Comment> commentsRealmList;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("orderID");
        fieldNames.add("status");
        fieldNames.add("startPoint");
        fieldNames.add("finishPoint");
        fieldNames.add("advancedMoney");
        fieldNames.add("shipMoney");
        fieldNames.add("note");
        fieldNames.add("distance");
        fieldNames.add("phoneNumber");
        fieldNames.add("dateTime");
        fieldNames.add("likes");
        fieldNames.add("dislikes");
        fieldNames.add("comments");
        fieldNames.add("saveOrder");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    OrderRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (OrderColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.minh.findtheshipper.models.Order>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$orderID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.orderIDIndex);
    }

    @Override
    public void realmSet$orderID(String value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'orderID' cannot be changed after object was created.");
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$status() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.statusIndex);
    }

    @Override
    public void realmSet$status(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.statusIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.statusIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.statusIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.statusIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$startPoint() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.startPointIndex);
    }

    @Override
    public void realmSet$startPoint(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.startPointIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.startPointIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.startPointIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.startPointIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$finishPoint() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.finishPointIndex);
    }

    @Override
    public void realmSet$finishPoint(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.finishPointIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.finishPointIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.finishPointIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.finishPointIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$advancedMoney() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.advancedMoneyIndex);
    }

    @Override
    public void realmSet$advancedMoney(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.advancedMoneyIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.advancedMoneyIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.advancedMoneyIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.advancedMoneyIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$shipMoney() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.shipMoneyIndex);
    }

    @Override
    public void realmSet$shipMoney(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.shipMoneyIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.shipMoneyIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.shipMoneyIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.shipMoneyIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$note() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.noteIndex);
    }

    @Override
    public void realmSet$note(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.noteIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.noteIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.noteIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.noteIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$distance() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.distanceIndex);
    }

    @Override
    public void realmSet$distance(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.distanceIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.distanceIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.distanceIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.distanceIndex, value);
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
    public RealmList<com.minh.findtheshipper.models.Like> realmGet$likes() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (likesRealmList != null) {
            return likesRealmList;
        } else {
            LinkView linkView = proxyState.getRow$realm().getLinkList(columnInfo.likesIndex);
            likesRealmList = new RealmList<com.minh.findtheshipper.models.Like>(com.minh.findtheshipper.models.Like.class, linkView, proxyState.getRealm$realm());
            return likesRealmList;
        }
    }

    @Override
    public void realmSet$likes(RealmList<com.minh.findtheshipper.models.Like> value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("likes")) {
                return;
            }
            if (value != null && !value.isManaged()) {
                final Realm realm = (Realm) proxyState.getRealm$realm();
                final RealmList<com.minh.findtheshipper.models.Like> original = value;
                value = new RealmList<com.minh.findtheshipper.models.Like>();
                for (com.minh.findtheshipper.models.Like item : original) {
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealm(item));
                    }
                }
            }
        }

        proxyState.getRealm$realm().checkIfValid();
        LinkView links = proxyState.getRow$realm().getLinkList(columnInfo.likesIndex);
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
    public RealmList<com.minh.findtheshipper.models.Dislike> realmGet$dislikes() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (dislikesRealmList != null) {
            return dislikesRealmList;
        } else {
            LinkView linkView = proxyState.getRow$realm().getLinkList(columnInfo.dislikesIndex);
            dislikesRealmList = new RealmList<com.minh.findtheshipper.models.Dislike>(com.minh.findtheshipper.models.Dislike.class, linkView, proxyState.getRealm$realm());
            return dislikesRealmList;
        }
    }

    @Override
    public void realmSet$dislikes(RealmList<com.minh.findtheshipper.models.Dislike> value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("dislikes")) {
                return;
            }
            if (value != null && !value.isManaged()) {
                final Realm realm = (Realm) proxyState.getRealm$realm();
                final RealmList<com.minh.findtheshipper.models.Dislike> original = value;
                value = new RealmList<com.minh.findtheshipper.models.Dislike>();
                for (com.minh.findtheshipper.models.Dislike item : original) {
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealm(item));
                    }
                }
            }
        }

        proxyState.getRealm$realm().checkIfValid();
        LinkView links = proxyState.getRow$realm().getLinkList(columnInfo.dislikesIndex);
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
    public RealmList<com.minh.findtheshipper.models.Comment> realmGet$comments() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (commentsRealmList != null) {
            return commentsRealmList;
        } else {
            LinkView linkView = proxyState.getRow$realm().getLinkList(columnInfo.commentsIndex);
            commentsRealmList = new RealmList<com.minh.findtheshipper.models.Comment>(com.minh.findtheshipper.models.Comment.class, linkView, proxyState.getRealm$realm());
            return commentsRealmList;
        }
    }

    @Override
    public void realmSet$comments(RealmList<com.minh.findtheshipper.models.Comment> value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("comments")) {
                return;
            }
            if (value != null && !value.isManaged()) {
                final Realm realm = (Realm) proxyState.getRealm$realm();
                final RealmList<com.minh.findtheshipper.models.Comment> original = value;
                value = new RealmList<com.minh.findtheshipper.models.Comment>();
                for (com.minh.findtheshipper.models.Comment item : original) {
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealm(item));
                    }
                }
            }
        }

        proxyState.getRealm$realm().checkIfValid();
        LinkView links = proxyState.getRow$realm().getLinkList(columnInfo.commentsIndex);
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
    @SuppressWarnings("cast")
    public Boolean realmGet$saveOrder() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.saveOrderIndex)) {
            return null;
        }
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.saveOrderIndex);
    }

    @Override
    public void realmSet$saveOrder(Boolean value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.saveOrderIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setBoolean(columnInfo.saveOrderIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.saveOrderIndex);
            return;
        }
        proxyState.getRow$realm().setBoolean(columnInfo.saveOrderIndex, value);
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("Order")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("Order");
            realmObjectSchema.add("orderID", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("status", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("startPoint", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("finishPoint", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("advancedMoney", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("shipMoney", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("note", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("distance", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("phoneNumber", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("dateTime", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            if (!realmSchema.contains("Like")) {
                LikeRealmProxy.createRealmObjectSchema(realmSchema);
            }
            realmObjectSchema.add("likes", RealmFieldType.LIST, realmSchema.get("Like"));
            if (!realmSchema.contains("Dislike")) {
                DislikeRealmProxy.createRealmObjectSchema(realmSchema);
            }
            realmObjectSchema.add("dislikes", RealmFieldType.LIST, realmSchema.get("Dislike"));
            if (!realmSchema.contains("Comment")) {
                CommentRealmProxy.createRealmObjectSchema(realmSchema);
            }
            realmObjectSchema.add("comments", RealmFieldType.LIST, realmSchema.get("Comment"));
            realmObjectSchema.add("saveOrder", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            return realmObjectSchema;
        }
        return realmSchema.get("Order");
    }

    public static OrderColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (!sharedRealm.hasTable("class_Order")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'Order' class is missing from the schema for this Realm.");
        }
        Table table = sharedRealm.getTable("class_Order");
        final long columnCount = table.getColumnCount();
        if (columnCount != 14) {
            if (columnCount < 14) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 14 but was " + columnCount);
            }
            if (allowExtraColumns) {
                RealmLog.debug("Field count is more than expected - expected 14 but was %1$d", columnCount);
            } else {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 14 but was " + columnCount);
            }
        }
        Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
        for (long i = 0; i < columnCount; i++) {
            columnTypes.put(table.getColumnName(i), table.getColumnType(i));
        }

        final OrderColumnInfo columnInfo = new OrderColumnInfo(sharedRealm, table);

        if (!table.hasPrimaryKey()) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary key not defined for field 'orderID' in existing Realm file. @PrimaryKey was added.");
        } else {
            if (table.getPrimaryKey() != columnInfo.orderIDIndex) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary Key annotation definition was changed, from field " + table.getColumnName(table.getPrimaryKey()) + " to field orderID");
            }
        }

        if (!columnTypes.containsKey("orderID")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'orderID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("orderID") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'orderID' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.orderIDIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(),"@PrimaryKey field 'orderID' does not support null values in the existing Realm file. Migrate using RealmObjectSchema.setNullable(), or mark the field as @Required.");
        }
        if (!table.hasSearchIndex(table.getColumnIndex("orderID"))) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Index not defined for field 'orderID' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
        }
        if (!columnTypes.containsKey("status")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'status' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("status") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'status' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.statusIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'status' is required. Either set @Required to field 'status' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("startPoint")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'startPoint' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("startPoint") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'startPoint' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.startPointIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'startPoint' is required. Either set @Required to field 'startPoint' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("finishPoint")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'finishPoint' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("finishPoint") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'finishPoint' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.finishPointIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'finishPoint' is required. Either set @Required to field 'finishPoint' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("advancedMoney")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'advancedMoney' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("advancedMoney") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'advancedMoney' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.advancedMoneyIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'advancedMoney' is required. Either set @Required to field 'advancedMoney' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("shipMoney")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'shipMoney' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("shipMoney") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'shipMoney' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.shipMoneyIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'shipMoney' is required. Either set @Required to field 'shipMoney' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("note")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'note' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("note") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'note' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.noteIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'note' is required. Either set @Required to field 'note' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("distance")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'distance' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("distance") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'distance' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.distanceIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'distance' is required. Either set @Required to field 'distance' or migrate using RealmObjectSchema.setNullable().");
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
        if (!columnTypes.containsKey("dateTime")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'dateTime' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("dateTime") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'dateTime' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.dateTimeIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'dateTime' is required. Either set @Required to field 'dateTime' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("likes")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'likes'");
        }
        if (columnTypes.get("likes") != RealmFieldType.LIST) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Like' for field 'likes'");
        }
        if (!sharedRealm.hasTable("class_Like")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing class 'class_Like' for field 'likes'");
        }
        Table table_10 = sharedRealm.getTable("class_Like");
        if (!table.getLinkTarget(columnInfo.likesIndex).hasSameSchema(table_10)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid RealmList type for field 'likes': '" + table.getLinkTarget(columnInfo.likesIndex).getName() + "' expected - was '" + table_10.getName() + "'");
        }
        if (!columnTypes.containsKey("dislikes")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'dislikes'");
        }
        if (columnTypes.get("dislikes") != RealmFieldType.LIST) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Dislike' for field 'dislikes'");
        }
        if (!sharedRealm.hasTable("class_Dislike")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing class 'class_Dislike' for field 'dislikes'");
        }
        Table table_11 = sharedRealm.getTable("class_Dislike");
        if (!table.getLinkTarget(columnInfo.dislikesIndex).hasSameSchema(table_11)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid RealmList type for field 'dislikes': '" + table.getLinkTarget(columnInfo.dislikesIndex).getName() + "' expected - was '" + table_11.getName() + "'");
        }
        if (!columnTypes.containsKey("comments")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'comments'");
        }
        if (columnTypes.get("comments") != RealmFieldType.LIST) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Comment' for field 'comments'");
        }
        if (!sharedRealm.hasTable("class_Comment")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing class 'class_Comment' for field 'comments'");
        }
        Table table_12 = sharedRealm.getTable("class_Comment");
        if (!table.getLinkTarget(columnInfo.commentsIndex).hasSameSchema(table_12)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid RealmList type for field 'comments': '" + table.getLinkTarget(columnInfo.commentsIndex).getName() + "' expected - was '" + table_12.getName() + "'");
        }
        if (!columnTypes.containsKey("saveOrder")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'saveOrder' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("saveOrder") != RealmFieldType.BOOLEAN) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Boolean' for field 'saveOrder' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.saveOrderIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(),"Field 'saveOrder' does not support null values in the existing Realm file. Either set @Required, use the primitive type for field 'saveOrder' or migrate using RealmObjectSchema.setNullable().");
        }

        return columnInfo;
    }

    public static String getTableName() {
        return "class_Order";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.minh.findtheshipper.models.Order createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(3);
        com.minh.findtheshipper.models.Order obj = null;
        if (update) {
            Table table = realm.getTable(com.minh.findtheshipper.models.Order.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = Table.NO_MATCH;
            if (json.isNull("orderID")) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("orderID"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.minh.findtheshipper.models.Order.class), false, Collections.<String> emptyList());
                    obj = new io.realm.OrderRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("likes")) {
                excludeFields.add("likes");
            }
            if (json.has("dislikes")) {
                excludeFields.add("dislikes");
            }
            if (json.has("comments")) {
                excludeFields.add("comments");
            }
            if (json.has("orderID")) {
                if (json.isNull("orderID")) {
                    obj = (io.realm.OrderRealmProxy) realm.createObjectInternal(com.minh.findtheshipper.models.Order.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.OrderRealmProxy) realm.createObjectInternal(com.minh.findtheshipper.models.Order.class, json.getString("orderID"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'orderID'.");
            }
        }
        if (json.has("status")) {
            if (json.isNull("status")) {
                ((OrderRealmProxyInterface) obj).realmSet$status(null);
            } else {
                ((OrderRealmProxyInterface) obj).realmSet$status((String) json.getString("status"));
            }
        }
        if (json.has("startPoint")) {
            if (json.isNull("startPoint")) {
                ((OrderRealmProxyInterface) obj).realmSet$startPoint(null);
            } else {
                ((OrderRealmProxyInterface) obj).realmSet$startPoint((String) json.getString("startPoint"));
            }
        }
        if (json.has("finishPoint")) {
            if (json.isNull("finishPoint")) {
                ((OrderRealmProxyInterface) obj).realmSet$finishPoint(null);
            } else {
                ((OrderRealmProxyInterface) obj).realmSet$finishPoint((String) json.getString("finishPoint"));
            }
        }
        if (json.has("advancedMoney")) {
            if (json.isNull("advancedMoney")) {
                ((OrderRealmProxyInterface) obj).realmSet$advancedMoney(null);
            } else {
                ((OrderRealmProxyInterface) obj).realmSet$advancedMoney((String) json.getString("advancedMoney"));
            }
        }
        if (json.has("shipMoney")) {
            if (json.isNull("shipMoney")) {
                ((OrderRealmProxyInterface) obj).realmSet$shipMoney(null);
            } else {
                ((OrderRealmProxyInterface) obj).realmSet$shipMoney((String) json.getString("shipMoney"));
            }
        }
        if (json.has("note")) {
            if (json.isNull("note")) {
                ((OrderRealmProxyInterface) obj).realmSet$note(null);
            } else {
                ((OrderRealmProxyInterface) obj).realmSet$note((String) json.getString("note"));
            }
        }
        if (json.has("distance")) {
            if (json.isNull("distance")) {
                ((OrderRealmProxyInterface) obj).realmSet$distance(null);
            } else {
                ((OrderRealmProxyInterface) obj).realmSet$distance((String) json.getString("distance"));
            }
        }
        if (json.has("phoneNumber")) {
            if (json.isNull("phoneNumber")) {
                ((OrderRealmProxyInterface) obj).realmSet$phoneNumber(null);
            } else {
                ((OrderRealmProxyInterface) obj).realmSet$phoneNumber((String) json.getString("phoneNumber"));
            }
        }
        if (json.has("dateTime")) {
            if (json.isNull("dateTime")) {
                ((OrderRealmProxyInterface) obj).realmSet$dateTime(null);
            } else {
                ((OrderRealmProxyInterface) obj).realmSet$dateTime((String) json.getString("dateTime"));
            }
        }
        if (json.has("likes")) {
            if (json.isNull("likes")) {
                ((OrderRealmProxyInterface) obj).realmSet$likes(null);
            } else {
                ((OrderRealmProxyInterface) obj).realmGet$likes().clear();
                JSONArray array = json.getJSONArray("likes");
                for (int i = 0; i < array.length(); i++) {
                    com.minh.findtheshipper.models.Like item = LikeRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    ((OrderRealmProxyInterface) obj).realmGet$likes().add(item);
                }
            }
        }
        if (json.has("dislikes")) {
            if (json.isNull("dislikes")) {
                ((OrderRealmProxyInterface) obj).realmSet$dislikes(null);
            } else {
                ((OrderRealmProxyInterface) obj).realmGet$dislikes().clear();
                JSONArray array = json.getJSONArray("dislikes");
                for (int i = 0; i < array.length(); i++) {
                    com.minh.findtheshipper.models.Dislike item = DislikeRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    ((OrderRealmProxyInterface) obj).realmGet$dislikes().add(item);
                }
            }
        }
        if (json.has("comments")) {
            if (json.isNull("comments")) {
                ((OrderRealmProxyInterface) obj).realmSet$comments(null);
            } else {
                ((OrderRealmProxyInterface) obj).realmGet$comments().clear();
                JSONArray array = json.getJSONArray("comments");
                for (int i = 0; i < array.length(); i++) {
                    com.minh.findtheshipper.models.Comment item = CommentRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    ((OrderRealmProxyInterface) obj).realmGet$comments().add(item);
                }
            }
        }
        if (json.has("saveOrder")) {
            if (json.isNull("saveOrder")) {
                ((OrderRealmProxyInterface) obj).realmSet$saveOrder(null);
            } else {
                ((OrderRealmProxyInterface) obj).realmSet$saveOrder((boolean) json.getBoolean("saveOrder"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.minh.findtheshipper.models.Order createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.minh.findtheshipper.models.Order obj = new com.minh.findtheshipper.models.Order();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("orderID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((OrderRealmProxyInterface) obj).realmSet$orderID(null);
                } else {
                    ((OrderRealmProxyInterface) obj).realmSet$orderID((String) reader.nextString());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("status")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((OrderRealmProxyInterface) obj).realmSet$status(null);
                } else {
                    ((OrderRealmProxyInterface) obj).realmSet$status((String) reader.nextString());
                }
            } else if (name.equals("startPoint")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((OrderRealmProxyInterface) obj).realmSet$startPoint(null);
                } else {
                    ((OrderRealmProxyInterface) obj).realmSet$startPoint((String) reader.nextString());
                }
            } else if (name.equals("finishPoint")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((OrderRealmProxyInterface) obj).realmSet$finishPoint(null);
                } else {
                    ((OrderRealmProxyInterface) obj).realmSet$finishPoint((String) reader.nextString());
                }
            } else if (name.equals("advancedMoney")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((OrderRealmProxyInterface) obj).realmSet$advancedMoney(null);
                } else {
                    ((OrderRealmProxyInterface) obj).realmSet$advancedMoney((String) reader.nextString());
                }
            } else if (name.equals("shipMoney")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((OrderRealmProxyInterface) obj).realmSet$shipMoney(null);
                } else {
                    ((OrderRealmProxyInterface) obj).realmSet$shipMoney((String) reader.nextString());
                }
            } else if (name.equals("note")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((OrderRealmProxyInterface) obj).realmSet$note(null);
                } else {
                    ((OrderRealmProxyInterface) obj).realmSet$note((String) reader.nextString());
                }
            } else if (name.equals("distance")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((OrderRealmProxyInterface) obj).realmSet$distance(null);
                } else {
                    ((OrderRealmProxyInterface) obj).realmSet$distance((String) reader.nextString());
                }
            } else if (name.equals("phoneNumber")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((OrderRealmProxyInterface) obj).realmSet$phoneNumber(null);
                } else {
                    ((OrderRealmProxyInterface) obj).realmSet$phoneNumber((String) reader.nextString());
                }
            } else if (name.equals("dateTime")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((OrderRealmProxyInterface) obj).realmSet$dateTime(null);
                } else {
                    ((OrderRealmProxyInterface) obj).realmSet$dateTime((String) reader.nextString());
                }
            } else if (name.equals("likes")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((OrderRealmProxyInterface) obj).realmSet$likes(null);
                } else {
                    ((OrderRealmProxyInterface) obj).realmSet$likes(new RealmList<com.minh.findtheshipper.models.Like>());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.minh.findtheshipper.models.Like item = LikeRealmProxy.createUsingJsonStream(realm, reader);
                        ((OrderRealmProxyInterface) obj).realmGet$likes().add(item);
                    }
                    reader.endArray();
                }
            } else if (name.equals("dislikes")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((OrderRealmProxyInterface) obj).realmSet$dislikes(null);
                } else {
                    ((OrderRealmProxyInterface) obj).realmSet$dislikes(new RealmList<com.minh.findtheshipper.models.Dislike>());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.minh.findtheshipper.models.Dislike item = DislikeRealmProxy.createUsingJsonStream(realm, reader);
                        ((OrderRealmProxyInterface) obj).realmGet$dislikes().add(item);
                    }
                    reader.endArray();
                }
            } else if (name.equals("comments")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((OrderRealmProxyInterface) obj).realmSet$comments(null);
                } else {
                    ((OrderRealmProxyInterface) obj).realmSet$comments(new RealmList<com.minh.findtheshipper.models.Comment>());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.minh.findtheshipper.models.Comment item = CommentRealmProxy.createUsingJsonStream(realm, reader);
                        ((OrderRealmProxyInterface) obj).realmGet$comments().add(item);
                    }
                    reader.endArray();
                }
            } else if (name.equals("saveOrder")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((OrderRealmProxyInterface) obj).realmSet$saveOrder(null);
                } else {
                    ((OrderRealmProxyInterface) obj).realmSet$saveOrder((boolean) reader.nextBoolean());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'orderID'.");
        }
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.minh.findtheshipper.models.Order copyOrUpdate(Realm realm, com.minh.findtheshipper.models.Order object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.minh.findtheshipper.models.Order) cachedRealmObject;
        } else {
            com.minh.findtheshipper.models.Order realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.minh.findtheshipper.models.Order.class);
                long pkColumnIndex = table.getPrimaryKey();
                String value = ((OrderRealmProxyInterface) object).realmGet$orderID();
                long rowIndex = Table.NO_MATCH;
                if (value == null) {
                    rowIndex = table.findFirstNull(pkColumnIndex);
                } else {
                    rowIndex = table.findFirstString(pkColumnIndex, value);
                }
                if (rowIndex != Table.NO_MATCH) {
                    try {
                        objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.minh.findtheshipper.models.Order.class), false, Collections.<String> emptyList());
                        realmObject = new io.realm.OrderRealmProxy();
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

    public static com.minh.findtheshipper.models.Order copy(Realm realm, com.minh.findtheshipper.models.Order newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.minh.findtheshipper.models.Order) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.minh.findtheshipper.models.Order realmObject = realm.createObjectInternal(com.minh.findtheshipper.models.Order.class, ((OrderRealmProxyInterface) newObject).realmGet$orderID(), false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((OrderRealmProxyInterface) realmObject).realmSet$status(((OrderRealmProxyInterface) newObject).realmGet$status());
            ((OrderRealmProxyInterface) realmObject).realmSet$startPoint(((OrderRealmProxyInterface) newObject).realmGet$startPoint());
            ((OrderRealmProxyInterface) realmObject).realmSet$finishPoint(((OrderRealmProxyInterface) newObject).realmGet$finishPoint());
            ((OrderRealmProxyInterface) realmObject).realmSet$advancedMoney(((OrderRealmProxyInterface) newObject).realmGet$advancedMoney());
            ((OrderRealmProxyInterface) realmObject).realmSet$shipMoney(((OrderRealmProxyInterface) newObject).realmGet$shipMoney());
            ((OrderRealmProxyInterface) realmObject).realmSet$note(((OrderRealmProxyInterface) newObject).realmGet$note());
            ((OrderRealmProxyInterface) realmObject).realmSet$distance(((OrderRealmProxyInterface) newObject).realmGet$distance());
            ((OrderRealmProxyInterface) realmObject).realmSet$phoneNumber(((OrderRealmProxyInterface) newObject).realmGet$phoneNumber());
            ((OrderRealmProxyInterface) realmObject).realmSet$dateTime(((OrderRealmProxyInterface) newObject).realmGet$dateTime());

            RealmList<com.minh.findtheshipper.models.Like> likesList = ((OrderRealmProxyInterface) newObject).realmGet$likes();
            if (likesList != null) {
                RealmList<com.minh.findtheshipper.models.Like> likesRealmList = ((OrderRealmProxyInterface) realmObject).realmGet$likes();
                for (int i = 0; i < likesList.size(); i++) {
                    com.minh.findtheshipper.models.Like likesItem = likesList.get(i);
                    com.minh.findtheshipper.models.Like cachelikes = (com.minh.findtheshipper.models.Like) cache.get(likesItem);
                    if (cachelikes != null) {
                        likesRealmList.add(cachelikes);
                    } else {
                        likesRealmList.add(LikeRealmProxy.copyOrUpdate(realm, likesList.get(i), update, cache));
                    }
                }
            }


            RealmList<com.minh.findtheshipper.models.Dislike> dislikesList = ((OrderRealmProxyInterface) newObject).realmGet$dislikes();
            if (dislikesList != null) {
                RealmList<com.minh.findtheshipper.models.Dislike> dislikesRealmList = ((OrderRealmProxyInterface) realmObject).realmGet$dislikes();
                for (int i = 0; i < dislikesList.size(); i++) {
                    com.minh.findtheshipper.models.Dislike dislikesItem = dislikesList.get(i);
                    com.minh.findtheshipper.models.Dislike cachedislikes = (com.minh.findtheshipper.models.Dislike) cache.get(dislikesItem);
                    if (cachedislikes != null) {
                        dislikesRealmList.add(cachedislikes);
                    } else {
                        dislikesRealmList.add(DislikeRealmProxy.copyOrUpdate(realm, dislikesList.get(i), update, cache));
                    }
                }
            }


            RealmList<com.minh.findtheshipper.models.Comment> commentsList = ((OrderRealmProxyInterface) newObject).realmGet$comments();
            if (commentsList != null) {
                RealmList<com.minh.findtheshipper.models.Comment> commentsRealmList = ((OrderRealmProxyInterface) realmObject).realmGet$comments();
                for (int i = 0; i < commentsList.size(); i++) {
                    com.minh.findtheshipper.models.Comment commentsItem = commentsList.get(i);
                    com.minh.findtheshipper.models.Comment cachecomments = (com.minh.findtheshipper.models.Comment) cache.get(commentsItem);
                    if (cachecomments != null) {
                        commentsRealmList.add(cachecomments);
                    } else {
                        commentsRealmList.add(CommentRealmProxy.copyOrUpdate(realm, commentsList.get(i), update, cache));
                    }
                }
            }

            ((OrderRealmProxyInterface) realmObject).realmSet$saveOrder(((OrderRealmProxyInterface) newObject).realmGet$saveOrder());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.minh.findtheshipper.models.Order object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.minh.findtheshipper.models.Order.class);
        long tableNativePtr = table.getNativePtr();
        OrderColumnInfo columnInfo = (OrderColumnInfo) realm.schema.getColumnInfo(com.minh.findtheshipper.models.Order.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((OrderRealmProxyInterface) object).realmGet$orderID();
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
        String realmGet$status = ((OrderRealmProxyInterface)object).realmGet$status();
        if (realmGet$status != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.statusIndex, rowIndex, realmGet$status, false);
        }
        String realmGet$startPoint = ((OrderRealmProxyInterface)object).realmGet$startPoint();
        if (realmGet$startPoint != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.startPointIndex, rowIndex, realmGet$startPoint, false);
        }
        String realmGet$finishPoint = ((OrderRealmProxyInterface)object).realmGet$finishPoint();
        if (realmGet$finishPoint != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.finishPointIndex, rowIndex, realmGet$finishPoint, false);
        }
        String realmGet$advancedMoney = ((OrderRealmProxyInterface)object).realmGet$advancedMoney();
        if (realmGet$advancedMoney != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.advancedMoneyIndex, rowIndex, realmGet$advancedMoney, false);
        }
        String realmGet$shipMoney = ((OrderRealmProxyInterface)object).realmGet$shipMoney();
        if (realmGet$shipMoney != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.shipMoneyIndex, rowIndex, realmGet$shipMoney, false);
        }
        String realmGet$note = ((OrderRealmProxyInterface)object).realmGet$note();
        if (realmGet$note != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.noteIndex, rowIndex, realmGet$note, false);
        }
        String realmGet$distance = ((OrderRealmProxyInterface)object).realmGet$distance();
        if (realmGet$distance != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.distanceIndex, rowIndex, realmGet$distance, false);
        }
        String realmGet$phoneNumber = ((OrderRealmProxyInterface)object).realmGet$phoneNumber();
        if (realmGet$phoneNumber != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.phoneNumberIndex, rowIndex, realmGet$phoneNumber, false);
        }
        String realmGet$dateTime = ((OrderRealmProxyInterface)object).realmGet$dateTime();
        if (realmGet$dateTime != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.dateTimeIndex, rowIndex, realmGet$dateTime, false);
        }

        RealmList<com.minh.findtheshipper.models.Like> likesList = ((OrderRealmProxyInterface) object).realmGet$likes();
        if (likesList != null) {
            long likesNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.likesIndex, rowIndex);
            for (com.minh.findtheshipper.models.Like likesItem : likesList) {
                Long cacheItemIndexlikes = cache.get(likesItem);
                if (cacheItemIndexlikes == null) {
                    cacheItemIndexlikes = LikeRealmProxy.insert(realm, likesItem, cache);
                }
                LinkView.nativeAdd(likesNativeLinkViewPtr, cacheItemIndexlikes);
            }
        }


        RealmList<com.minh.findtheshipper.models.Dislike> dislikesList = ((OrderRealmProxyInterface) object).realmGet$dislikes();
        if (dislikesList != null) {
            long dislikesNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.dislikesIndex, rowIndex);
            for (com.minh.findtheshipper.models.Dislike dislikesItem : dislikesList) {
                Long cacheItemIndexdislikes = cache.get(dislikesItem);
                if (cacheItemIndexdislikes == null) {
                    cacheItemIndexdislikes = DislikeRealmProxy.insert(realm, dislikesItem, cache);
                }
                LinkView.nativeAdd(dislikesNativeLinkViewPtr, cacheItemIndexdislikes);
            }
        }


        RealmList<com.minh.findtheshipper.models.Comment> commentsList = ((OrderRealmProxyInterface) object).realmGet$comments();
        if (commentsList != null) {
            long commentsNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.commentsIndex, rowIndex);
            for (com.minh.findtheshipper.models.Comment commentsItem : commentsList) {
                Long cacheItemIndexcomments = cache.get(commentsItem);
                if (cacheItemIndexcomments == null) {
                    cacheItemIndexcomments = CommentRealmProxy.insert(realm, commentsItem, cache);
                }
                LinkView.nativeAdd(commentsNativeLinkViewPtr, cacheItemIndexcomments);
            }
        }

        Boolean realmGet$saveOrder = ((OrderRealmProxyInterface)object).realmGet$saveOrder();
        if (realmGet$saveOrder != null) {
            Table.nativeSetBoolean(tableNativePtr, columnInfo.saveOrderIndex, rowIndex, realmGet$saveOrder, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.minh.findtheshipper.models.Order.class);
        long tableNativePtr = table.getNativePtr();
        OrderColumnInfo columnInfo = (OrderColumnInfo) realm.schema.getColumnInfo(com.minh.findtheshipper.models.Order.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.minh.findtheshipper.models.Order object = null;
        while (objects.hasNext()) {
            object = (com.minh.findtheshipper.models.Order) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((OrderRealmProxyInterface) object).realmGet$orderID();
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
                String realmGet$status = ((OrderRealmProxyInterface)object).realmGet$status();
                if (realmGet$status != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.statusIndex, rowIndex, realmGet$status, false);
                }
                String realmGet$startPoint = ((OrderRealmProxyInterface)object).realmGet$startPoint();
                if (realmGet$startPoint != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.startPointIndex, rowIndex, realmGet$startPoint, false);
                }
                String realmGet$finishPoint = ((OrderRealmProxyInterface)object).realmGet$finishPoint();
                if (realmGet$finishPoint != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.finishPointIndex, rowIndex, realmGet$finishPoint, false);
                }
                String realmGet$advancedMoney = ((OrderRealmProxyInterface)object).realmGet$advancedMoney();
                if (realmGet$advancedMoney != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.advancedMoneyIndex, rowIndex, realmGet$advancedMoney, false);
                }
                String realmGet$shipMoney = ((OrderRealmProxyInterface)object).realmGet$shipMoney();
                if (realmGet$shipMoney != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.shipMoneyIndex, rowIndex, realmGet$shipMoney, false);
                }
                String realmGet$note = ((OrderRealmProxyInterface)object).realmGet$note();
                if (realmGet$note != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.noteIndex, rowIndex, realmGet$note, false);
                }
                String realmGet$distance = ((OrderRealmProxyInterface)object).realmGet$distance();
                if (realmGet$distance != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.distanceIndex, rowIndex, realmGet$distance, false);
                }
                String realmGet$phoneNumber = ((OrderRealmProxyInterface)object).realmGet$phoneNumber();
                if (realmGet$phoneNumber != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.phoneNumberIndex, rowIndex, realmGet$phoneNumber, false);
                }
                String realmGet$dateTime = ((OrderRealmProxyInterface)object).realmGet$dateTime();
                if (realmGet$dateTime != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.dateTimeIndex, rowIndex, realmGet$dateTime, false);
                }

                RealmList<com.minh.findtheshipper.models.Like> likesList = ((OrderRealmProxyInterface) object).realmGet$likes();
                if (likesList != null) {
                    long likesNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.likesIndex, rowIndex);
                    for (com.minh.findtheshipper.models.Like likesItem : likesList) {
                        Long cacheItemIndexlikes = cache.get(likesItem);
                        if (cacheItemIndexlikes == null) {
                            cacheItemIndexlikes = LikeRealmProxy.insert(realm, likesItem, cache);
                        }
                        LinkView.nativeAdd(likesNativeLinkViewPtr, cacheItemIndexlikes);
                    }
                }


                RealmList<com.minh.findtheshipper.models.Dislike> dislikesList = ((OrderRealmProxyInterface) object).realmGet$dislikes();
                if (dislikesList != null) {
                    long dislikesNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.dislikesIndex, rowIndex);
                    for (com.minh.findtheshipper.models.Dislike dislikesItem : dislikesList) {
                        Long cacheItemIndexdislikes = cache.get(dislikesItem);
                        if (cacheItemIndexdislikes == null) {
                            cacheItemIndexdislikes = DislikeRealmProxy.insert(realm, dislikesItem, cache);
                        }
                        LinkView.nativeAdd(dislikesNativeLinkViewPtr, cacheItemIndexdislikes);
                    }
                }


                RealmList<com.minh.findtheshipper.models.Comment> commentsList = ((OrderRealmProxyInterface) object).realmGet$comments();
                if (commentsList != null) {
                    long commentsNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.commentsIndex, rowIndex);
                    for (com.minh.findtheshipper.models.Comment commentsItem : commentsList) {
                        Long cacheItemIndexcomments = cache.get(commentsItem);
                        if (cacheItemIndexcomments == null) {
                            cacheItemIndexcomments = CommentRealmProxy.insert(realm, commentsItem, cache);
                        }
                        LinkView.nativeAdd(commentsNativeLinkViewPtr, cacheItemIndexcomments);
                    }
                }

                Boolean realmGet$saveOrder = ((OrderRealmProxyInterface)object).realmGet$saveOrder();
                if (realmGet$saveOrder != null) {
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.saveOrderIndex, rowIndex, realmGet$saveOrder, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.minh.findtheshipper.models.Order object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.minh.findtheshipper.models.Order.class);
        long tableNativePtr = table.getNativePtr();
        OrderColumnInfo columnInfo = (OrderColumnInfo) realm.schema.getColumnInfo(com.minh.findtheshipper.models.Order.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((OrderRealmProxyInterface) object).realmGet$orderID();
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
        String realmGet$status = ((OrderRealmProxyInterface)object).realmGet$status();
        if (realmGet$status != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.statusIndex, rowIndex, realmGet$status, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.statusIndex, rowIndex, false);
        }
        String realmGet$startPoint = ((OrderRealmProxyInterface)object).realmGet$startPoint();
        if (realmGet$startPoint != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.startPointIndex, rowIndex, realmGet$startPoint, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.startPointIndex, rowIndex, false);
        }
        String realmGet$finishPoint = ((OrderRealmProxyInterface)object).realmGet$finishPoint();
        if (realmGet$finishPoint != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.finishPointIndex, rowIndex, realmGet$finishPoint, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.finishPointIndex, rowIndex, false);
        }
        String realmGet$advancedMoney = ((OrderRealmProxyInterface)object).realmGet$advancedMoney();
        if (realmGet$advancedMoney != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.advancedMoneyIndex, rowIndex, realmGet$advancedMoney, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.advancedMoneyIndex, rowIndex, false);
        }
        String realmGet$shipMoney = ((OrderRealmProxyInterface)object).realmGet$shipMoney();
        if (realmGet$shipMoney != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.shipMoneyIndex, rowIndex, realmGet$shipMoney, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.shipMoneyIndex, rowIndex, false);
        }
        String realmGet$note = ((OrderRealmProxyInterface)object).realmGet$note();
        if (realmGet$note != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.noteIndex, rowIndex, realmGet$note, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.noteIndex, rowIndex, false);
        }
        String realmGet$distance = ((OrderRealmProxyInterface)object).realmGet$distance();
        if (realmGet$distance != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.distanceIndex, rowIndex, realmGet$distance, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.distanceIndex, rowIndex, false);
        }
        String realmGet$phoneNumber = ((OrderRealmProxyInterface)object).realmGet$phoneNumber();
        if (realmGet$phoneNumber != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.phoneNumberIndex, rowIndex, realmGet$phoneNumber, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.phoneNumberIndex, rowIndex, false);
        }
        String realmGet$dateTime = ((OrderRealmProxyInterface)object).realmGet$dateTime();
        if (realmGet$dateTime != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.dateTimeIndex, rowIndex, realmGet$dateTime, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.dateTimeIndex, rowIndex, false);
        }

        long likesNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.likesIndex, rowIndex);
        LinkView.nativeClear(likesNativeLinkViewPtr);
        RealmList<com.minh.findtheshipper.models.Like> likesList = ((OrderRealmProxyInterface) object).realmGet$likes();
        if (likesList != null) {
            for (com.minh.findtheshipper.models.Like likesItem : likesList) {
                Long cacheItemIndexlikes = cache.get(likesItem);
                if (cacheItemIndexlikes == null) {
                    cacheItemIndexlikes = LikeRealmProxy.insertOrUpdate(realm, likesItem, cache);
                }
                LinkView.nativeAdd(likesNativeLinkViewPtr, cacheItemIndexlikes);
            }
        }


        long dislikesNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.dislikesIndex, rowIndex);
        LinkView.nativeClear(dislikesNativeLinkViewPtr);
        RealmList<com.minh.findtheshipper.models.Dislike> dislikesList = ((OrderRealmProxyInterface) object).realmGet$dislikes();
        if (dislikesList != null) {
            for (com.minh.findtheshipper.models.Dislike dislikesItem : dislikesList) {
                Long cacheItemIndexdislikes = cache.get(dislikesItem);
                if (cacheItemIndexdislikes == null) {
                    cacheItemIndexdislikes = DislikeRealmProxy.insertOrUpdate(realm, dislikesItem, cache);
                }
                LinkView.nativeAdd(dislikesNativeLinkViewPtr, cacheItemIndexdislikes);
            }
        }


        long commentsNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.commentsIndex, rowIndex);
        LinkView.nativeClear(commentsNativeLinkViewPtr);
        RealmList<com.minh.findtheshipper.models.Comment> commentsList = ((OrderRealmProxyInterface) object).realmGet$comments();
        if (commentsList != null) {
            for (com.minh.findtheshipper.models.Comment commentsItem : commentsList) {
                Long cacheItemIndexcomments = cache.get(commentsItem);
                if (cacheItemIndexcomments == null) {
                    cacheItemIndexcomments = CommentRealmProxy.insertOrUpdate(realm, commentsItem, cache);
                }
                LinkView.nativeAdd(commentsNativeLinkViewPtr, cacheItemIndexcomments);
            }
        }

        Boolean realmGet$saveOrder = ((OrderRealmProxyInterface)object).realmGet$saveOrder();
        if (realmGet$saveOrder != null) {
            Table.nativeSetBoolean(tableNativePtr, columnInfo.saveOrderIndex, rowIndex, realmGet$saveOrder, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.saveOrderIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.minh.findtheshipper.models.Order.class);
        long tableNativePtr = table.getNativePtr();
        OrderColumnInfo columnInfo = (OrderColumnInfo) realm.schema.getColumnInfo(com.minh.findtheshipper.models.Order.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.minh.findtheshipper.models.Order object = null;
        while (objects.hasNext()) {
            object = (com.minh.findtheshipper.models.Order) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((OrderRealmProxyInterface) object).realmGet$orderID();
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
                String realmGet$status = ((OrderRealmProxyInterface)object).realmGet$status();
                if (realmGet$status != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.statusIndex, rowIndex, realmGet$status, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.statusIndex, rowIndex, false);
                }
                String realmGet$startPoint = ((OrderRealmProxyInterface)object).realmGet$startPoint();
                if (realmGet$startPoint != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.startPointIndex, rowIndex, realmGet$startPoint, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.startPointIndex, rowIndex, false);
                }
                String realmGet$finishPoint = ((OrderRealmProxyInterface)object).realmGet$finishPoint();
                if (realmGet$finishPoint != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.finishPointIndex, rowIndex, realmGet$finishPoint, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.finishPointIndex, rowIndex, false);
                }
                String realmGet$advancedMoney = ((OrderRealmProxyInterface)object).realmGet$advancedMoney();
                if (realmGet$advancedMoney != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.advancedMoneyIndex, rowIndex, realmGet$advancedMoney, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.advancedMoneyIndex, rowIndex, false);
                }
                String realmGet$shipMoney = ((OrderRealmProxyInterface)object).realmGet$shipMoney();
                if (realmGet$shipMoney != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.shipMoneyIndex, rowIndex, realmGet$shipMoney, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.shipMoneyIndex, rowIndex, false);
                }
                String realmGet$note = ((OrderRealmProxyInterface)object).realmGet$note();
                if (realmGet$note != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.noteIndex, rowIndex, realmGet$note, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.noteIndex, rowIndex, false);
                }
                String realmGet$distance = ((OrderRealmProxyInterface)object).realmGet$distance();
                if (realmGet$distance != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.distanceIndex, rowIndex, realmGet$distance, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.distanceIndex, rowIndex, false);
                }
                String realmGet$phoneNumber = ((OrderRealmProxyInterface)object).realmGet$phoneNumber();
                if (realmGet$phoneNumber != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.phoneNumberIndex, rowIndex, realmGet$phoneNumber, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.phoneNumberIndex, rowIndex, false);
                }
                String realmGet$dateTime = ((OrderRealmProxyInterface)object).realmGet$dateTime();
                if (realmGet$dateTime != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.dateTimeIndex, rowIndex, realmGet$dateTime, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.dateTimeIndex, rowIndex, false);
                }

                long likesNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.likesIndex, rowIndex);
                LinkView.nativeClear(likesNativeLinkViewPtr);
                RealmList<com.minh.findtheshipper.models.Like> likesList = ((OrderRealmProxyInterface) object).realmGet$likes();
                if (likesList != null) {
                    for (com.minh.findtheshipper.models.Like likesItem : likesList) {
                        Long cacheItemIndexlikes = cache.get(likesItem);
                        if (cacheItemIndexlikes == null) {
                            cacheItemIndexlikes = LikeRealmProxy.insertOrUpdate(realm, likesItem, cache);
                        }
                        LinkView.nativeAdd(likesNativeLinkViewPtr, cacheItemIndexlikes);
                    }
                }


                long dislikesNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.dislikesIndex, rowIndex);
                LinkView.nativeClear(dislikesNativeLinkViewPtr);
                RealmList<com.minh.findtheshipper.models.Dislike> dislikesList = ((OrderRealmProxyInterface) object).realmGet$dislikes();
                if (dislikesList != null) {
                    for (com.minh.findtheshipper.models.Dislike dislikesItem : dislikesList) {
                        Long cacheItemIndexdislikes = cache.get(dislikesItem);
                        if (cacheItemIndexdislikes == null) {
                            cacheItemIndexdislikes = DislikeRealmProxy.insertOrUpdate(realm, dislikesItem, cache);
                        }
                        LinkView.nativeAdd(dislikesNativeLinkViewPtr, cacheItemIndexdislikes);
                    }
                }


                long commentsNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.commentsIndex, rowIndex);
                LinkView.nativeClear(commentsNativeLinkViewPtr);
                RealmList<com.minh.findtheshipper.models.Comment> commentsList = ((OrderRealmProxyInterface) object).realmGet$comments();
                if (commentsList != null) {
                    for (com.minh.findtheshipper.models.Comment commentsItem : commentsList) {
                        Long cacheItemIndexcomments = cache.get(commentsItem);
                        if (cacheItemIndexcomments == null) {
                            cacheItemIndexcomments = CommentRealmProxy.insertOrUpdate(realm, commentsItem, cache);
                        }
                        LinkView.nativeAdd(commentsNativeLinkViewPtr, cacheItemIndexcomments);
                    }
                }

                Boolean realmGet$saveOrder = ((OrderRealmProxyInterface)object).realmGet$saveOrder();
                if (realmGet$saveOrder != null) {
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.saveOrderIndex, rowIndex, realmGet$saveOrder, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.saveOrderIndex, rowIndex, false);
                }
            }
        }
    }

    public static com.minh.findtheshipper.models.Order createDetachedCopy(com.minh.findtheshipper.models.Order realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.minh.findtheshipper.models.Order unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.minh.findtheshipper.models.Order)cachedObject.object;
            } else {
                unmanagedObject = (com.minh.findtheshipper.models.Order)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.minh.findtheshipper.models.Order();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }
        ((OrderRealmProxyInterface) unmanagedObject).realmSet$orderID(((OrderRealmProxyInterface) realmObject).realmGet$orderID());
        ((OrderRealmProxyInterface) unmanagedObject).realmSet$status(((OrderRealmProxyInterface) realmObject).realmGet$status());
        ((OrderRealmProxyInterface) unmanagedObject).realmSet$startPoint(((OrderRealmProxyInterface) realmObject).realmGet$startPoint());
        ((OrderRealmProxyInterface) unmanagedObject).realmSet$finishPoint(((OrderRealmProxyInterface) realmObject).realmGet$finishPoint());
        ((OrderRealmProxyInterface) unmanagedObject).realmSet$advancedMoney(((OrderRealmProxyInterface) realmObject).realmGet$advancedMoney());
        ((OrderRealmProxyInterface) unmanagedObject).realmSet$shipMoney(((OrderRealmProxyInterface) realmObject).realmGet$shipMoney());
        ((OrderRealmProxyInterface) unmanagedObject).realmSet$note(((OrderRealmProxyInterface) realmObject).realmGet$note());
        ((OrderRealmProxyInterface) unmanagedObject).realmSet$distance(((OrderRealmProxyInterface) realmObject).realmGet$distance());
        ((OrderRealmProxyInterface) unmanagedObject).realmSet$phoneNumber(((OrderRealmProxyInterface) realmObject).realmGet$phoneNumber());
        ((OrderRealmProxyInterface) unmanagedObject).realmSet$dateTime(((OrderRealmProxyInterface) realmObject).realmGet$dateTime());

        // Deep copy of likes
        if (currentDepth == maxDepth) {
            ((OrderRealmProxyInterface) unmanagedObject).realmSet$likes(null);
        } else {
            RealmList<com.minh.findtheshipper.models.Like> managedlikesList = ((OrderRealmProxyInterface) realmObject).realmGet$likes();
            RealmList<com.minh.findtheshipper.models.Like> unmanagedlikesList = new RealmList<com.minh.findtheshipper.models.Like>();
            ((OrderRealmProxyInterface) unmanagedObject).realmSet$likes(unmanagedlikesList);
            int nextDepth = currentDepth + 1;
            int size = managedlikesList.size();
            for (int i = 0; i < size; i++) {
                com.minh.findtheshipper.models.Like item = LikeRealmProxy.createDetachedCopy(managedlikesList.get(i), nextDepth, maxDepth, cache);
                unmanagedlikesList.add(item);
            }
        }

        // Deep copy of dislikes
        if (currentDepth == maxDepth) {
            ((OrderRealmProxyInterface) unmanagedObject).realmSet$dislikes(null);
        } else {
            RealmList<com.minh.findtheshipper.models.Dislike> manageddislikesList = ((OrderRealmProxyInterface) realmObject).realmGet$dislikes();
            RealmList<com.minh.findtheshipper.models.Dislike> unmanageddislikesList = new RealmList<com.minh.findtheshipper.models.Dislike>();
            ((OrderRealmProxyInterface) unmanagedObject).realmSet$dislikes(unmanageddislikesList);
            int nextDepth = currentDepth + 1;
            int size = manageddislikesList.size();
            for (int i = 0; i < size; i++) {
                com.minh.findtheshipper.models.Dislike item = DislikeRealmProxy.createDetachedCopy(manageddislikesList.get(i), nextDepth, maxDepth, cache);
                unmanageddislikesList.add(item);
            }
        }

        // Deep copy of comments
        if (currentDepth == maxDepth) {
            ((OrderRealmProxyInterface) unmanagedObject).realmSet$comments(null);
        } else {
            RealmList<com.minh.findtheshipper.models.Comment> managedcommentsList = ((OrderRealmProxyInterface) realmObject).realmGet$comments();
            RealmList<com.minh.findtheshipper.models.Comment> unmanagedcommentsList = new RealmList<com.minh.findtheshipper.models.Comment>();
            ((OrderRealmProxyInterface) unmanagedObject).realmSet$comments(unmanagedcommentsList);
            int nextDepth = currentDepth + 1;
            int size = managedcommentsList.size();
            for (int i = 0; i < size; i++) {
                com.minh.findtheshipper.models.Comment item = CommentRealmProxy.createDetachedCopy(managedcommentsList.get(i), nextDepth, maxDepth, cache);
                unmanagedcommentsList.add(item);
            }
        }
        ((OrderRealmProxyInterface) unmanagedObject).realmSet$saveOrder(((OrderRealmProxyInterface) realmObject).realmGet$saveOrder());
        return unmanagedObject;
    }

    static com.minh.findtheshipper.models.Order update(Realm realm, com.minh.findtheshipper.models.Order realmObject, com.minh.findtheshipper.models.Order newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((OrderRealmProxyInterface) realmObject).realmSet$status(((OrderRealmProxyInterface) newObject).realmGet$status());
        ((OrderRealmProxyInterface) realmObject).realmSet$startPoint(((OrderRealmProxyInterface) newObject).realmGet$startPoint());
        ((OrderRealmProxyInterface) realmObject).realmSet$finishPoint(((OrderRealmProxyInterface) newObject).realmGet$finishPoint());
        ((OrderRealmProxyInterface) realmObject).realmSet$advancedMoney(((OrderRealmProxyInterface) newObject).realmGet$advancedMoney());
        ((OrderRealmProxyInterface) realmObject).realmSet$shipMoney(((OrderRealmProxyInterface) newObject).realmGet$shipMoney());
        ((OrderRealmProxyInterface) realmObject).realmSet$note(((OrderRealmProxyInterface) newObject).realmGet$note());
        ((OrderRealmProxyInterface) realmObject).realmSet$distance(((OrderRealmProxyInterface) newObject).realmGet$distance());
        ((OrderRealmProxyInterface) realmObject).realmSet$phoneNumber(((OrderRealmProxyInterface) newObject).realmGet$phoneNumber());
        ((OrderRealmProxyInterface) realmObject).realmSet$dateTime(((OrderRealmProxyInterface) newObject).realmGet$dateTime());
        RealmList<com.minh.findtheshipper.models.Like> likesList = ((OrderRealmProxyInterface) newObject).realmGet$likes();
        RealmList<com.minh.findtheshipper.models.Like> likesRealmList = ((OrderRealmProxyInterface) realmObject).realmGet$likes();
        likesRealmList.clear();
        if (likesList != null) {
            for (int i = 0; i < likesList.size(); i++) {
                com.minh.findtheshipper.models.Like likesItem = likesList.get(i);
                com.minh.findtheshipper.models.Like cachelikes = (com.minh.findtheshipper.models.Like) cache.get(likesItem);
                if (cachelikes != null) {
                    likesRealmList.add(cachelikes);
                } else {
                    likesRealmList.add(LikeRealmProxy.copyOrUpdate(realm, likesList.get(i), true, cache));
                }
            }
        }
        RealmList<com.minh.findtheshipper.models.Dislike> dislikesList = ((OrderRealmProxyInterface) newObject).realmGet$dislikes();
        RealmList<com.minh.findtheshipper.models.Dislike> dislikesRealmList = ((OrderRealmProxyInterface) realmObject).realmGet$dislikes();
        dislikesRealmList.clear();
        if (dislikesList != null) {
            for (int i = 0; i < dislikesList.size(); i++) {
                com.minh.findtheshipper.models.Dislike dislikesItem = dislikesList.get(i);
                com.minh.findtheshipper.models.Dislike cachedislikes = (com.minh.findtheshipper.models.Dislike) cache.get(dislikesItem);
                if (cachedislikes != null) {
                    dislikesRealmList.add(cachedislikes);
                } else {
                    dislikesRealmList.add(DislikeRealmProxy.copyOrUpdate(realm, dislikesList.get(i), true, cache));
                }
            }
        }
        RealmList<com.minh.findtheshipper.models.Comment> commentsList = ((OrderRealmProxyInterface) newObject).realmGet$comments();
        RealmList<com.minh.findtheshipper.models.Comment> commentsRealmList = ((OrderRealmProxyInterface) realmObject).realmGet$comments();
        commentsRealmList.clear();
        if (commentsList != null) {
            for (int i = 0; i < commentsList.size(); i++) {
                com.minh.findtheshipper.models.Comment commentsItem = commentsList.get(i);
                com.minh.findtheshipper.models.Comment cachecomments = (com.minh.findtheshipper.models.Comment) cache.get(commentsItem);
                if (cachecomments != null) {
                    commentsRealmList.add(cachecomments);
                } else {
                    commentsRealmList.add(CommentRealmProxy.copyOrUpdate(realm, commentsList.get(i), true, cache));
                }
            }
        }
        ((OrderRealmProxyInterface) realmObject).realmSet$saveOrder(((OrderRealmProxyInterface) newObject).realmGet$saveOrder());
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Order = proxy[");
        stringBuilder.append("{orderID:");
        stringBuilder.append(realmGet$orderID() != null ? realmGet$orderID() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{status:");
        stringBuilder.append(realmGet$status() != null ? realmGet$status() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{startPoint:");
        stringBuilder.append(realmGet$startPoint() != null ? realmGet$startPoint() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{finishPoint:");
        stringBuilder.append(realmGet$finishPoint() != null ? realmGet$finishPoint() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{advancedMoney:");
        stringBuilder.append(realmGet$advancedMoney() != null ? realmGet$advancedMoney() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{shipMoney:");
        stringBuilder.append(realmGet$shipMoney() != null ? realmGet$shipMoney() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{note:");
        stringBuilder.append(realmGet$note() != null ? realmGet$note() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{distance:");
        stringBuilder.append(realmGet$distance() != null ? realmGet$distance() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{phoneNumber:");
        stringBuilder.append(realmGet$phoneNumber() != null ? realmGet$phoneNumber() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{dateTime:");
        stringBuilder.append(realmGet$dateTime() != null ? realmGet$dateTime() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{likes:");
        stringBuilder.append("RealmList<Like>[").append(realmGet$likes().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{dislikes:");
        stringBuilder.append("RealmList<Dislike>[").append(realmGet$dislikes().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{comments:");
        stringBuilder.append("RealmList<Comment>[").append(realmGet$comments().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{saveOrder:");
        stringBuilder.append(realmGet$saveOrder() != null ? realmGet$saveOrder() : "null");
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
        OrderRealmProxy aOrder = (OrderRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aOrder.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aOrder.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aOrder.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
