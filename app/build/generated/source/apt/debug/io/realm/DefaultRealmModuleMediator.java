package io.realm;


import android.util.JsonReader;
import io.realm.RealmObjectSchema;
import io.realm.internal.ColumnInfo;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

@io.realm.annotations.RealmModule
class DefaultRealmModuleMediator extends RealmProxyMediator {

    private static final Set<Class<? extends RealmModel>> MODEL_CLASSES;
    static {
        Set<Class<? extends RealmModel>> modelClasses = new HashSet<Class<? extends RealmModel>>();
        modelClasses.add(com.minh.findtheshipper.models.Order.class);
        modelClasses.add(com.minh.findtheshipper.models.NotificationObject.class);
        modelClasses.add(com.minh.findtheshipper.models.User.class);
        modelClasses.add(com.minh.findtheshipper.models.Dislike.class);
        modelClasses.add(com.minh.findtheshipper.models.Comment.class);
        modelClasses.add(com.minh.findtheshipper.models.CurrentUser.class);
        modelClasses.add(com.minh.findtheshipper.models.Like.class);
        MODEL_CLASSES = Collections.unmodifiableSet(modelClasses);
    }

    @Override
    public RealmObjectSchema createRealmObjectSchema(Class<? extends RealmModel> clazz, RealmSchema realmSchema) {
        checkClass(clazz);

        if (clazz.equals(com.minh.findtheshipper.models.Order.class)) {
            return io.realm.OrderRealmProxy.createRealmObjectSchema(realmSchema);
        }
        if (clazz.equals(com.minh.findtheshipper.models.NotificationObject.class)) {
            return io.realm.NotificationObjectRealmProxy.createRealmObjectSchema(realmSchema);
        }
        if (clazz.equals(com.minh.findtheshipper.models.User.class)) {
            return io.realm.UserRealmProxy.createRealmObjectSchema(realmSchema);
        }
        if (clazz.equals(com.minh.findtheshipper.models.Dislike.class)) {
            return io.realm.DislikeRealmProxy.createRealmObjectSchema(realmSchema);
        }
        if (clazz.equals(com.minh.findtheshipper.models.Comment.class)) {
            return io.realm.CommentRealmProxy.createRealmObjectSchema(realmSchema);
        }
        if (clazz.equals(com.minh.findtheshipper.models.CurrentUser.class)) {
            return io.realm.CurrentUserRealmProxy.createRealmObjectSchema(realmSchema);
        }
        if (clazz.equals(com.minh.findtheshipper.models.Like.class)) {
            return io.realm.LikeRealmProxy.createRealmObjectSchema(realmSchema);
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public ColumnInfo validateTable(Class<? extends RealmModel> clazz, SharedRealm sharedRealm, boolean allowExtraColumns) {
        checkClass(clazz);

        if (clazz.equals(com.minh.findtheshipper.models.Order.class)) {
            return io.realm.OrderRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        }
        if (clazz.equals(com.minh.findtheshipper.models.NotificationObject.class)) {
            return io.realm.NotificationObjectRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        }
        if (clazz.equals(com.minh.findtheshipper.models.User.class)) {
            return io.realm.UserRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        }
        if (clazz.equals(com.minh.findtheshipper.models.Dislike.class)) {
            return io.realm.DislikeRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        }
        if (clazz.equals(com.minh.findtheshipper.models.Comment.class)) {
            return io.realm.CommentRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        }
        if (clazz.equals(com.minh.findtheshipper.models.CurrentUser.class)) {
            return io.realm.CurrentUserRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        }
        if (clazz.equals(com.minh.findtheshipper.models.Like.class)) {
            return io.realm.LikeRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public List<String> getFieldNames(Class<? extends RealmModel> clazz) {
        checkClass(clazz);

        if (clazz.equals(com.minh.findtheshipper.models.Order.class)) {
            return io.realm.OrderRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.minh.findtheshipper.models.NotificationObject.class)) {
            return io.realm.NotificationObjectRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.minh.findtheshipper.models.User.class)) {
            return io.realm.UserRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.minh.findtheshipper.models.Dislike.class)) {
            return io.realm.DislikeRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.minh.findtheshipper.models.Comment.class)) {
            return io.realm.CommentRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.minh.findtheshipper.models.CurrentUser.class)) {
            return io.realm.CurrentUserRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.minh.findtheshipper.models.Like.class)) {
            return io.realm.LikeRealmProxy.getFieldNames();
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public String getTableName(Class<? extends RealmModel> clazz) {
        checkClass(clazz);

        if (clazz.equals(com.minh.findtheshipper.models.Order.class)) {
            return io.realm.OrderRealmProxy.getTableName();
        }
        if (clazz.equals(com.minh.findtheshipper.models.NotificationObject.class)) {
            return io.realm.NotificationObjectRealmProxy.getTableName();
        }
        if (clazz.equals(com.minh.findtheshipper.models.User.class)) {
            return io.realm.UserRealmProxy.getTableName();
        }
        if (clazz.equals(com.minh.findtheshipper.models.Dislike.class)) {
            return io.realm.DislikeRealmProxy.getTableName();
        }
        if (clazz.equals(com.minh.findtheshipper.models.Comment.class)) {
            return io.realm.CommentRealmProxy.getTableName();
        }
        if (clazz.equals(com.minh.findtheshipper.models.CurrentUser.class)) {
            return io.realm.CurrentUserRealmProxy.getTableName();
        }
        if (clazz.equals(com.minh.findtheshipper.models.Like.class)) {
            return io.realm.LikeRealmProxy.getTableName();
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> E newInstance(Class<E> clazz, Object baseRealm, Row row, ColumnInfo columnInfo, boolean acceptDefaultValue, List<String> excludeFields) {
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        try {
            objectContext.set((BaseRealm) baseRealm, row, columnInfo, acceptDefaultValue, excludeFields);
            checkClass(clazz);

            if (clazz.equals(com.minh.findtheshipper.models.Order.class)) {
                return clazz.cast(new io.realm.OrderRealmProxy());
            }
            if (clazz.equals(com.minh.findtheshipper.models.NotificationObject.class)) {
                return clazz.cast(new io.realm.NotificationObjectRealmProxy());
            }
            if (clazz.equals(com.minh.findtheshipper.models.User.class)) {
                return clazz.cast(new io.realm.UserRealmProxy());
            }
            if (clazz.equals(com.minh.findtheshipper.models.Dislike.class)) {
                return clazz.cast(new io.realm.DislikeRealmProxy());
            }
            if (clazz.equals(com.minh.findtheshipper.models.Comment.class)) {
                return clazz.cast(new io.realm.CommentRealmProxy());
            }
            if (clazz.equals(com.minh.findtheshipper.models.CurrentUser.class)) {
                return clazz.cast(new io.realm.CurrentUserRealmProxy());
            }
            if (clazz.equals(com.minh.findtheshipper.models.Like.class)) {
                return clazz.cast(new io.realm.LikeRealmProxy());
            }
            throw getMissingProxyClassException(clazz);
        } finally {
            objectContext.clear();
        }
    }

    @Override
    public Set<Class<? extends RealmModel>> getModelClasses() {
        return MODEL_CLASSES;
    }

    @Override
    public <E extends RealmModel> E copyOrUpdate(Realm realm, E obj, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(com.minh.findtheshipper.models.Order.class)) {
            return clazz.cast(io.realm.OrderRealmProxy.copyOrUpdate(realm, (com.minh.findtheshipper.models.Order) obj, update, cache));
        }
        if (clazz.equals(com.minh.findtheshipper.models.NotificationObject.class)) {
            return clazz.cast(io.realm.NotificationObjectRealmProxy.copyOrUpdate(realm, (com.minh.findtheshipper.models.NotificationObject) obj, update, cache));
        }
        if (clazz.equals(com.minh.findtheshipper.models.User.class)) {
            return clazz.cast(io.realm.UserRealmProxy.copyOrUpdate(realm, (com.minh.findtheshipper.models.User) obj, update, cache));
        }
        if (clazz.equals(com.minh.findtheshipper.models.Dislike.class)) {
            return clazz.cast(io.realm.DislikeRealmProxy.copyOrUpdate(realm, (com.minh.findtheshipper.models.Dislike) obj, update, cache));
        }
        if (clazz.equals(com.minh.findtheshipper.models.Comment.class)) {
            return clazz.cast(io.realm.CommentRealmProxy.copyOrUpdate(realm, (com.minh.findtheshipper.models.Comment) obj, update, cache));
        }
        if (clazz.equals(com.minh.findtheshipper.models.CurrentUser.class)) {
            return clazz.cast(io.realm.CurrentUserRealmProxy.copyOrUpdate(realm, (com.minh.findtheshipper.models.CurrentUser) obj, update, cache));
        }
        if (clazz.equals(com.minh.findtheshipper.models.Like.class)) {
            return clazz.cast(io.realm.LikeRealmProxy.copyOrUpdate(realm, (com.minh.findtheshipper.models.Like) obj, update, cache));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public void insert(Realm realm, RealmModel object, Map<RealmModel, Long> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

        if (clazz.equals(com.minh.findtheshipper.models.Order.class)) {
            io.realm.OrderRealmProxy.insert(realm, (com.minh.findtheshipper.models.Order) object, cache);
        } else if (clazz.equals(com.minh.findtheshipper.models.NotificationObject.class)) {
            io.realm.NotificationObjectRealmProxy.insert(realm, (com.minh.findtheshipper.models.NotificationObject) object, cache);
        } else if (clazz.equals(com.minh.findtheshipper.models.User.class)) {
            io.realm.UserRealmProxy.insert(realm, (com.minh.findtheshipper.models.User) object, cache);
        } else if (clazz.equals(com.minh.findtheshipper.models.Dislike.class)) {
            io.realm.DislikeRealmProxy.insert(realm, (com.minh.findtheshipper.models.Dislike) object, cache);
        } else if (clazz.equals(com.minh.findtheshipper.models.Comment.class)) {
            io.realm.CommentRealmProxy.insert(realm, (com.minh.findtheshipper.models.Comment) object, cache);
        } else if (clazz.equals(com.minh.findtheshipper.models.CurrentUser.class)) {
            io.realm.CurrentUserRealmProxy.insert(realm, (com.minh.findtheshipper.models.CurrentUser) object, cache);
        } else if (clazz.equals(com.minh.findtheshipper.models.Like.class)) {
            io.realm.LikeRealmProxy.insert(realm, (com.minh.findtheshipper.models.Like) object, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insert(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        RealmModel object = null;
        Map<RealmModel, Long> cache = new HashMap<RealmModel, Long>(objects.size());
        if (iterator.hasNext()) {
            //  access the first element to figure out the clazz for the routing below
            object = iterator.next();
            // This cast is correct because obj is either
            // generated by RealmProxy or the original type extending directly from RealmObject
            @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

            if (clazz.equals(com.minh.findtheshipper.models.Order.class)) {
                io.realm.OrderRealmProxy.insert(realm, (com.minh.findtheshipper.models.Order) object, cache);
            } else if (clazz.equals(com.minh.findtheshipper.models.NotificationObject.class)) {
                io.realm.NotificationObjectRealmProxy.insert(realm, (com.minh.findtheshipper.models.NotificationObject) object, cache);
            } else if (clazz.equals(com.minh.findtheshipper.models.User.class)) {
                io.realm.UserRealmProxy.insert(realm, (com.minh.findtheshipper.models.User) object, cache);
            } else if (clazz.equals(com.minh.findtheshipper.models.Dislike.class)) {
                io.realm.DislikeRealmProxy.insert(realm, (com.minh.findtheshipper.models.Dislike) object, cache);
            } else if (clazz.equals(com.minh.findtheshipper.models.Comment.class)) {
                io.realm.CommentRealmProxy.insert(realm, (com.minh.findtheshipper.models.Comment) object, cache);
            } else if (clazz.equals(com.minh.findtheshipper.models.CurrentUser.class)) {
                io.realm.CurrentUserRealmProxy.insert(realm, (com.minh.findtheshipper.models.CurrentUser) object, cache);
            } else if (clazz.equals(com.minh.findtheshipper.models.Like.class)) {
                io.realm.LikeRealmProxy.insert(realm, (com.minh.findtheshipper.models.Like) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (iterator.hasNext()) {
                if (clazz.equals(com.minh.findtheshipper.models.Order.class)) {
                    io.realm.OrderRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.minh.findtheshipper.models.NotificationObject.class)) {
                    io.realm.NotificationObjectRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.minh.findtheshipper.models.User.class)) {
                    io.realm.UserRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.minh.findtheshipper.models.Dislike.class)) {
                    io.realm.DislikeRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.minh.findtheshipper.models.Comment.class)) {
                    io.realm.CommentRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.minh.findtheshipper.models.CurrentUser.class)) {
                    io.realm.CurrentUserRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.minh.findtheshipper.models.Like.class)) {
                    io.realm.LikeRealmProxy.insert(realm, iterator, cache);
                } else {
                    throw getMissingProxyClassException(clazz);
                }
            }
        }
    }

    @Override
    public void insertOrUpdate(Realm realm, RealmModel obj, Map<RealmModel, Long> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(com.minh.findtheshipper.models.Order.class)) {
            io.realm.OrderRealmProxy.insertOrUpdate(realm, (com.minh.findtheshipper.models.Order) obj, cache);
        } else if (clazz.equals(com.minh.findtheshipper.models.NotificationObject.class)) {
            io.realm.NotificationObjectRealmProxy.insertOrUpdate(realm, (com.minh.findtheshipper.models.NotificationObject) obj, cache);
        } else if (clazz.equals(com.minh.findtheshipper.models.User.class)) {
            io.realm.UserRealmProxy.insertOrUpdate(realm, (com.minh.findtheshipper.models.User) obj, cache);
        } else if (clazz.equals(com.minh.findtheshipper.models.Dislike.class)) {
            io.realm.DislikeRealmProxy.insertOrUpdate(realm, (com.minh.findtheshipper.models.Dislike) obj, cache);
        } else if (clazz.equals(com.minh.findtheshipper.models.Comment.class)) {
            io.realm.CommentRealmProxy.insertOrUpdate(realm, (com.minh.findtheshipper.models.Comment) obj, cache);
        } else if (clazz.equals(com.minh.findtheshipper.models.CurrentUser.class)) {
            io.realm.CurrentUserRealmProxy.insertOrUpdate(realm, (com.minh.findtheshipper.models.CurrentUser) obj, cache);
        } else if (clazz.equals(com.minh.findtheshipper.models.Like.class)) {
            io.realm.LikeRealmProxy.insertOrUpdate(realm, (com.minh.findtheshipper.models.Like) obj, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insertOrUpdate(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        RealmModel object = null;
        Map<RealmModel, Long> cache = new HashMap<RealmModel, Long>(objects.size());
        if (iterator.hasNext()) {
            //  access the first element to figure out the clazz for the routing below
            object = iterator.next();
            // This cast is correct because obj is either
            // generated by RealmProxy or the original type extending directly from RealmObject
            @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

            if (clazz.equals(com.minh.findtheshipper.models.Order.class)) {
                io.realm.OrderRealmProxy.insertOrUpdate(realm, (com.minh.findtheshipper.models.Order) object, cache);
            } else if (clazz.equals(com.minh.findtheshipper.models.NotificationObject.class)) {
                io.realm.NotificationObjectRealmProxy.insertOrUpdate(realm, (com.minh.findtheshipper.models.NotificationObject) object, cache);
            } else if (clazz.equals(com.minh.findtheshipper.models.User.class)) {
                io.realm.UserRealmProxy.insertOrUpdate(realm, (com.minh.findtheshipper.models.User) object, cache);
            } else if (clazz.equals(com.minh.findtheshipper.models.Dislike.class)) {
                io.realm.DislikeRealmProxy.insertOrUpdate(realm, (com.minh.findtheshipper.models.Dislike) object, cache);
            } else if (clazz.equals(com.minh.findtheshipper.models.Comment.class)) {
                io.realm.CommentRealmProxy.insertOrUpdate(realm, (com.minh.findtheshipper.models.Comment) object, cache);
            } else if (clazz.equals(com.minh.findtheshipper.models.CurrentUser.class)) {
                io.realm.CurrentUserRealmProxy.insertOrUpdate(realm, (com.minh.findtheshipper.models.CurrentUser) object, cache);
            } else if (clazz.equals(com.minh.findtheshipper.models.Like.class)) {
                io.realm.LikeRealmProxy.insertOrUpdate(realm, (com.minh.findtheshipper.models.Like) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (iterator.hasNext()) {
                if (clazz.equals(com.minh.findtheshipper.models.Order.class)) {
                    io.realm.OrderRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.minh.findtheshipper.models.NotificationObject.class)) {
                    io.realm.NotificationObjectRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.minh.findtheshipper.models.User.class)) {
                    io.realm.UserRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.minh.findtheshipper.models.Dislike.class)) {
                    io.realm.DislikeRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.minh.findtheshipper.models.Comment.class)) {
                    io.realm.CommentRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.minh.findtheshipper.models.CurrentUser.class)) {
                    io.realm.CurrentUserRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.minh.findtheshipper.models.Like.class)) {
                    io.realm.LikeRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else {
                    throw getMissingProxyClassException(clazz);
                }
            }
        }
    }

    @Override
    public <E extends RealmModel> E createOrUpdateUsingJsonObject(Class<E> clazz, Realm realm, JSONObject json, boolean update)
        throws JSONException {
        checkClass(clazz);

        if (clazz.equals(com.minh.findtheshipper.models.Order.class)) {
            return clazz.cast(io.realm.OrderRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.minh.findtheshipper.models.NotificationObject.class)) {
            return clazz.cast(io.realm.NotificationObjectRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.minh.findtheshipper.models.User.class)) {
            return clazz.cast(io.realm.UserRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.minh.findtheshipper.models.Dislike.class)) {
            return clazz.cast(io.realm.DislikeRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.minh.findtheshipper.models.Comment.class)) {
            return clazz.cast(io.realm.CommentRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.minh.findtheshipper.models.CurrentUser.class)) {
            return clazz.cast(io.realm.CurrentUserRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.minh.findtheshipper.models.Like.class)) {
            return clazz.cast(io.realm.LikeRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> E createUsingJsonStream(Class<E> clazz, Realm realm, JsonReader reader)
        throws IOException {
        checkClass(clazz);

        if (clazz.equals(com.minh.findtheshipper.models.Order.class)) {
            return clazz.cast(io.realm.OrderRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.minh.findtheshipper.models.NotificationObject.class)) {
            return clazz.cast(io.realm.NotificationObjectRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.minh.findtheshipper.models.User.class)) {
            return clazz.cast(io.realm.UserRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.minh.findtheshipper.models.Dislike.class)) {
            return clazz.cast(io.realm.DislikeRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.minh.findtheshipper.models.Comment.class)) {
            return clazz.cast(io.realm.CommentRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.minh.findtheshipper.models.CurrentUser.class)) {
            return clazz.cast(io.realm.CurrentUserRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.minh.findtheshipper.models.Like.class)) {
            return clazz.cast(io.realm.LikeRealmProxy.createUsingJsonStream(realm, reader));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> E createDetachedCopy(E realmObject, int maxDepth, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) realmObject.getClass().getSuperclass();

        if (clazz.equals(com.minh.findtheshipper.models.Order.class)) {
            return clazz.cast(io.realm.OrderRealmProxy.createDetachedCopy((com.minh.findtheshipper.models.Order) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.minh.findtheshipper.models.NotificationObject.class)) {
            return clazz.cast(io.realm.NotificationObjectRealmProxy.createDetachedCopy((com.minh.findtheshipper.models.NotificationObject) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.minh.findtheshipper.models.User.class)) {
            return clazz.cast(io.realm.UserRealmProxy.createDetachedCopy((com.minh.findtheshipper.models.User) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.minh.findtheshipper.models.Dislike.class)) {
            return clazz.cast(io.realm.DislikeRealmProxy.createDetachedCopy((com.minh.findtheshipper.models.Dislike) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.minh.findtheshipper.models.Comment.class)) {
            return clazz.cast(io.realm.CommentRealmProxy.createDetachedCopy((com.minh.findtheshipper.models.Comment) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.minh.findtheshipper.models.CurrentUser.class)) {
            return clazz.cast(io.realm.CurrentUserRealmProxy.createDetachedCopy((com.minh.findtheshipper.models.CurrentUser) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.minh.findtheshipper.models.Like.class)) {
            return clazz.cast(io.realm.LikeRealmProxy.createDetachedCopy((com.minh.findtheshipper.models.Like) realmObject, 0, maxDepth, cache));
        }
        throw getMissingProxyClassException(clazz);
    }

}
