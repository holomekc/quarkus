package io.quarkus.hibernate.reactive.panache;

import static io.quarkus.hibernate.reactive.panache.runtime.JpaOperations.INSTANCE;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Transient;

import org.hibernate.reactive.mutiny.Mutiny;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import io.quarkus.panache.common.impl.GenerateBridge;
import io.smallrye.common.annotation.CheckReturnValue;
import io.smallrye.mutiny.Uni;

/**
 * <p>
 * Represents an entity. If your Hibernate entities extend this class they gain auto-generated accessors
 * to all their public fields (unless annotated with {@link Transient}), as well as a lot of useful
 * methods. Unless you have a custom ID strategy, you should not extend this class directly but extend
 * {@link PanacheEntity} instead.
 * </p>
 *
 * @author Stéphane Épardaud
 * @see PanacheEntity
 */
public abstract class PanacheEntityBase {

    /**
     * Returns the current {@link Mutiny.Session}
     *
     * @return the current {@link Mutiny.Session}
     */
    public static Uni<Mutiny.Session> getSession() {
        return INSTANCE.getSession();
    }

    /**
     * Persist this entity in the database, if not already persisted. This will set your ID field if it is not already set.
     *
     * @return
     *
     * @see #isPersistent()
     * @see #persist(Iterable)
     * @see #persist(Stream)
     * @see #persist(Object, Object...)
     */
    @CheckReturnValue
    public <T extends PanacheEntityBase> Uni<T> persist() {
        return INSTANCE.persist(this).map(v -> (T) this);
    }

    /**
     * Persist this entity in the database, if not already persisted. This will set your ID field if it is not already set.
     * Then flushes all pending changes to the database.
     *
     * @return
     *
     * @see #isPersistent()
     * @see #persist(Iterable)
     * @see #persist(Stream)
     * @see #persist(Object, Object...)
     */
    @CheckReturnValue
    public <T extends PanacheEntityBase> Uni<T> persistAndFlush() {
        return INSTANCE.persist(this)
                .flatMap(v -> INSTANCE.flush())
                .map(v -> (T) this);
    }

    /**
     * Delete this entity from the database, if it is already persisted.
     *
     * @return
     *
     * @see #isPersistent()
     * @see #delete(String, Object...)
     * @see #delete(String, Map)
     * @see #delete(String, Parameters)
     * @see #deleteAll()
     */
    @CheckReturnValue
    public Uni<Void> delete() {
        return INSTANCE.delete(this);
    }

    /**
     * Returns true if this entity is persistent in the database. If yes, all modifications to
     * its persistent fields will be automatically committed to the database at transaction
     * commit time.
     *
     * @return true if this entity is persistent in the database.
     */
    @JsonbTransient
    // @JsonIgnore is here to avoid serialization of this property with jackson
    @JsonIgnore
    public boolean isPersistent() {
        return INSTANCE.isPersistent(this);
    }

    /**
     * Flushes all pending changes to the database.
     *
     * @return
     */
    @CheckReturnValue
    public Uni<Void> flush() {
        return INSTANCE.flush();
    }

    // Queries

    /**
     * Find an entity of this type by ID.
     *
     * @param id the ID of the entity to find.
     * @return the entity found, or <code>null</code> if not found.
     */
    @GenerateBridge
    public static <T extends PanacheEntityBase> Uni<T> findById(Object id) {
        throw INSTANCE.implementationInjectionMissing();
    }

    /**
     * Find an entity of this type by ID and lock it.
     *
     * @param id the ID of the entity to find.
     * @param lockModeType the locking strategy to be used when retrieving the entity.
     * @return the entity found, or <code>null</code> if not found.
     */
    @CheckReturnValue
    @GenerateBridge
    public static <T extends PanacheEntityBase> Uni<T> findById(Object id, LockModeType lockModeType) {
        throw INSTANCE.implementationInjectionMissing();
    }

    /**
     * Find entities using a query, with optional indexed parameters.
     *
     * @param query a {@link io.quarkus.hibernate.reactive.panache query string}
     * @param params optional sequence of indexed parameters
     * @return a new {@link PanacheQuery} instance for the given query
     * @see #find(String, Sort, Object...)
     * @see #find(String, Map)
     * @see #find(String, Parameters)
     * @see #list(String, Object...)
     */
    @GenerateBridge
    public static <T extends PanacheEntityBase> PanacheQuery<T> find(String query, Object... params) {
        throw INSTANCE.implementationInjectionMissing();
    }

    /**
     * Find entities using a query and the given sort options, with optional indexed parameters.
     *
     * @param query a {@link io.quarkus.hibernate.reactive.panache query string}
     * @param sort the sort strategy to use
     * @param params optional sequence of indexed parameters
     * @return a new {@link PanacheQuery} instance for the given query
     * @see #find(String, Object...)
     * @see #find(String, Sort, Map)
     * @see #find(String, Sort, Parameters)
     * @see #list(String, Sort, Object...)
     */
    @GenerateBridge
    public static <T extends PanacheEntityBase> PanacheQuery<T> find(String query, Sort sort, Object... params) {
        throw INSTANCE.implementationInjectionMissing();
    }

    /**
     * Find entities using a query, with named parameters.
     *
     * @param query a {@link io.quarkus.hibernate.reactive.panache query string}
     * @param params {@link Map} of named parameters
     * @return a new {@link PanacheQuery} instance for the given query
     * @see #find(String, Sort, Map)
     * @see #find(String, Object...)
     * @see #find(String, Parameters)
     * @see #list(String, Map)
     */
    @GenerateBridge
    public static <T extends PanacheEntityBase> PanacheQuery<T> find(String query, Map<String, Object> params) {
        throw INSTANCE.implementationInjectionMissing();
    }

    /**
     * Find entities using a query and the given sort options, with named parameters.
     *
     * @param query a {@link io.quarkus.hibernate.reactive.panache query string}
     * @param sort the sort strategy to use
     * @param params {@link Map} of indexed parameters
     * @return a new {@link PanacheQuery} instance for the given query
     * @see #find(String, Map)
     * @see #find(String, Sort, Object...)
     * @see #find(String, Sort, Parameters)
     * @see #list(String, Sort, Map)
     */
    @GenerateBridge
    public static <T extends PanacheEntityBase> PanacheQuery<T> find(String query, Sort sort, Map<String, Object> params) {
        throw INSTANCE.implementationInjectionMissing();
    }

    /**
     * Find entities using a query, with named parameters.
     *
     * @param query a {@link io.quarkus.hibernate.reactive.panache query string}
     * @param params {@link Parameters} of named parameters
     * @return a new {@link PanacheQuery} instance for the given query
     * @see #find(String, Sort, Parameters)
     * @see #find(String, Map)
     * @see #find(String, Parameters)
     * @see #list(String, Parameters)
     */
    @GenerateBridge
    public static <T extends PanacheEntityBase> PanacheQuery<T> find(String query, Parameters params) {
        throw INSTANCE.implementationInjectionMissing();
    }

    /**
     * Find entities using a query and the given sort options, with named parameters.
     *
     * @param query a {@link io.quarkus.hibernate.reactive.panache query string}
     * @param sort the sort strategy to use
     * @param params {@link Parameters} of indexed parameters
     * @return a new {@link PanacheQuery} instance for the given query
     * @see #find(String, Parameters)
     * @see #find(String, Sort, Map)
     * @see #find(String, Sort, Parameters)
     * @see #list(String, Sort, Parameters)
     */
    @GenerateBridge
    public static <T extends PanacheEntityBase> PanacheQuery<T> find(String query, Sort sort, Parameters params) {
        throw INSTANCE.implementationInjectionMissing();
    }

    /**
     * Find all entities of this type.
     *
     * @return a new {@link PanacheQuery} instance to find all entities of this type.
     * @see #findAll(Sort)
     * @see #listAll()
     */
    @GenerateBridge
    public static <T extends PanacheEntityBase> PanacheQuery<T> findAll() {
        throw INSTANCE.implementationInjectionMissing();
    }

    /**
     * Find all entities of this type, in the given order.
     *
     * @param sort the sort order to use
     * @return a new {@link PanacheQuery} instance to find all entities of this type.
     * @see #findAll()
     * @see #listAll(Sort)
     */
    @GenerateBridge
    public static <T extends PanacheEntityBase> PanacheQuery<T> findAll(Sort sort) {
        throw INSTANCE.implementationInjectionMissing();
    }

    /**
     * Find entities matching a query, with optional indexed parameters.
     * This method is a shortcut for <code>find(query, params).list()</code>.
     *
     * @param query a {@link io.quarkus.hibernate.reactive.panache query string}
     * @param params optional sequence of indexed parameters
     * @return a {@link List} containing all results, without paging
     * @see #list(String, Sort, Object...)
     * @see #list(String, Map)
     * @see #list(String, Parameters)
     * @see #find(String, Object...)
     */
    @CheckReturnValue
    @GenerateBridge
    public static <T extends PanacheEntityBase> Uni<List<T>> list(String query, Object... params) {
        throw INSTANCE.implementationInjectionMissing();
    }

    /**
     * Find entities matching a query and the given sort options, with optional indexed parameters.
     * This method is a shortcut for <code>find(query, sort, params).list()</code>.
     *
     * @param query a {@link io.quarkus.hibernate.reactive.panache query string}
     * @param sort the sort strategy to use
     * @param params optional sequence of indexed parameters
     * @return a {@link List} containing all results, without paging
     * @see #list(String, Object...)
     * @see #list(String, Sort, Map)
     * @see #list(String, Sort, Parameters)
     * @see #find(String, Sort, Object...)
     */
    @CheckReturnValue
    @GenerateBridge
    public static <T extends PanacheEntityBase> Uni<List<T>> list(String query, Sort sort, Object... params) {
        throw INSTANCE.implementationInjectionMissing();
    }

    /**
     * Find entities matching a query, with named parameters.
     * This method is a shortcut for <code>find(query, params).list()</code>.
     *
     * @param query a {@link io.quarkus.hibernate.reactive.panache query string}
     * @param params {@link Map} of named parameters
     * @return a {@link List} containing all results, without paging
     * @see #list(String, Sort, Map)
     * @see #list(String, Object...)
     * @see #list(String, Parameters)
     * @see #find(String, Map)
     */
    @CheckReturnValue
    @GenerateBridge
    public static <T extends PanacheEntityBase> Uni<List<T>> list(String query, Map<String, Object> params) {
        throw INSTANCE.implementationInjectionMissing();
    }

    /**
     * Find entities matching a query and the given sort options, with named parameters.
     * This method is a shortcut for <code>find(query, sort, params).list()</code>.
     *
     * @param query a {@link io.quarkus.hibernate.reactive.panache query string}
     * @param sort the sort strategy to use
     * @param params {@link Map} of indexed parameters
     * @return a {@link List} containing all results, without paging
     * @see #list(String, Map)
     * @see #list(String, Sort, Object...)
     * @see #list(String, Sort, Parameters)
     * @see #find(String, Sort, Map)
     */
    @CheckReturnValue
    @GenerateBridge
    public static <T extends PanacheEntityBase> Uni<List<T>> list(String query, Sort sort, Map<String, Object> params) {
        throw INSTANCE.implementationInjectionMissing();
    }

    /**
     * Find entities matching a query, with named parameters.
     * This method is a shortcut for <code>find(query, params).list()</code>.
     *
     * @param query a {@link io.quarkus.hibernate.reactive.panache query string}
     * @param params {@link Parameters} of named parameters
     * @return a {@link List} containing all results, without paging
     * @see #list(String, Sort, Parameters)
     * @see #list(String, Object...)
     * @see #list(String, Map)
     * @see #find(String, Parameters)
     */
    @CheckReturnValue
    @GenerateBridge
    public static <T extends PanacheEntityBase> Uni<List<T>> list(String query, Parameters params) {
        throw INSTANCE.implementationInjectionMissing();
    }

    /**
     * Find entities matching a query and the given sort options, with named parameters.
     * This method is a shortcut for <code>find(query, sort, params).list()</code>.
     *
     * @param query a {@link io.quarkus.hibernate.reactive.panache query string}
     * @param sort the sort strategy to use
     * @param params {@link Parameters} of indexed parameters
     * @return a {@link List} containing all results, without paging
     * @see #list(String, Parameters)
     * @see #list(String, Sort, Object...)
     * @see #list(String, Sort, Map)
     * @see #find(String, Sort, Parameters)
     */
    @CheckReturnValue
    @GenerateBridge
    public static <T extends PanacheEntityBase> Uni<List<T>> list(String query, Sort sort, Parameters params) {
        throw INSTANCE.implementationInjectionMissing();
    }

    /**
     * Find all entities of this type.
     * This method is a shortcut for <code>findAll().list()</code>.
     *
     * @return a {@link List} containing all results, without paging
     * @see #listAll(Sort)
     * @see #findAll()
     */
    @CheckReturnValue
    @GenerateBridge
    public static <T extends PanacheEntityBase> Uni<List<T>> listAll() {
        throw INSTANCE.implementationInjectionMissing();
    }

    /**
     * Find all entities of this type, in the given order.
     * This method is a shortcut for <code>findAll(sort).list()</code>.
     *
     * @param sort the sort order to use
     * @return a {@link List} containing all results, without paging
     * @see #listAll()
     * @see #findAll(Sort)
     */
    @CheckReturnValue
    @GenerateBridge
    public static <T extends PanacheEntityBase> Uni<List<T>> listAll(Sort sort) {
        throw INSTANCE.implementationInjectionMissing();
    }

    /**
     * Counts the number of this type of entity in the database.
     *
     * @return the number of this type of entity in the database.
     * @see #count(String, Object...)
     * @see #count(String, Map)
     * @see #count(String, Parameters)
     */
    @CheckReturnValue
    @GenerateBridge
    public static Uni<Long> count() {
        throw INSTANCE.implementationInjectionMissing();
    }

    /**
     * Counts the number of this type of entity matching the given query, with optional indexed parameters.
     *
     * @param query a {@link io.quarkus.hibernate.reactive.panache query string}
     * @param params optional sequence of indexed parameters
     * @return the number of entities counted.
     * @see #count()
     * @see #count(String, Map)
     * @see #count(String, Parameters)
     */
    @CheckReturnValue
    @GenerateBridge
    public static Uni<Long> count(String query, Object... params) {
        throw INSTANCE.implementationInjectionMissing();
    }

    /**
     * Counts the number of this type of entity matching the given query, with named parameters.
     *
     * @param query a {@link io.quarkus.hibernate.reactive.panache query string}
     * @param params {@link Map} of named parameters
     * @return the number of entities counted.
     * @see #count()
     * @see #count(String, Object...)
     * @see #count(String, Parameters)
     */
    @CheckReturnValue
    @GenerateBridge
    public static Uni<Long> count(String query, Map<String, Object> params) {
        throw INSTANCE.implementationInjectionMissing();
    }

    /**
     * Counts the number of this type of entity matching the given query, with named parameters.
     *
     * @param query a {@link io.quarkus.hibernate.reactive.panache query string}
     * @param params {@link Parameters} of named parameters
     * @return the number of entities counted.
     * @see #count()
     * @see #count(String, Object...)
     * @see #count(String, Map)
     */
    @CheckReturnValue
    @GenerateBridge
    public static Uni<Long> count(String query, Parameters params) {
        throw INSTANCE.implementationInjectionMissing();
    }

    /**
     * Delete all entities of this type from the database.
     *
     * WARNING: the default implementation of this method uses a bulk delete query and ignores
     * cascading rules from the JPA model.
     *
     * @return the number of entities deleted.
     * @see #delete(String, Object...)
     * @see #delete(String, Map)
     * @see #delete(String, Parameters)
     */
    @CheckReturnValue
    @GenerateBridge
    public static Uni<Long> deleteAll() {
        throw INSTANCE.implementationInjectionMissing();
    }

    /**
     * Delete an entity of this type by ID.
     *
     * @param id the ID of the entity to delete.
     * @return false if the entity was not deleted (not found).
     */
    @CheckReturnValue
    @GenerateBridge
    public static Uni<Boolean> deleteById(Object id) {
        throw INSTANCE.implementationInjectionMissing();
    }

    /**
     * Delete all entities of this type matching the given query, with optional indexed parameters.
     *
     * WARNING: the default implementation of this method uses a bulk delete query and ignores
     * cascading rules from the JPA model.
     *
     * @param query a {@link io.quarkus.hibernate.reactive.panache query string}
     * @param params optional sequence of indexed parameters
     * @return the number of entities deleted.
     * @see #deleteAll()
     * @see #delete(String, Map)
     * @see #delete(String, Parameters)
     */
    @CheckReturnValue
    @GenerateBridge
    public static Uni<Long> delete(String query, Object... params) {
        throw INSTANCE.implementationInjectionMissing();
    }

    /**
     * Delete all entities of this type matching the given query, with named parameters.
     *
     * WARNING: the default implementation of this method uses a bulk delete query and ignores
     * cascading rules from the JPA model.
     *
     * @param query a {@link io.quarkus.hibernate.reactive.panache query string}
     * @param params {@link Map} of named parameters
     * @return the number of entities deleted.
     * @see #deleteAll()
     * @see #delete(String, Object...)
     * @see #delete(String, Parameters)
     */
    @CheckReturnValue
    @GenerateBridge
    public static Uni<Long> delete(String query, Map<String, Object> params) {
        throw INSTANCE.implementationInjectionMissing();
    }

    /**
     * Delete all entities of this type matching the given query, with named parameters.
     *
     * WARNING: the default implementation of this method uses a bulk delete query and ignores
     * cascading rules from the JPA model.
     *
     * @param query a {@link io.quarkus.hibernate.reactive.panache query string}
     * @param params {@link Parameters} of named parameters
     * @return the number of entities deleted.
     * @see #deleteAll()
     * @see #delete(String, Object...)
     * @see #delete(String, Map)
     */
    @CheckReturnValue
    @GenerateBridge
    public static Uni<Long> delete(String query, Parameters params) {
        throw INSTANCE.implementationInjectionMissing();
    }

    /**
     * Persist all given entities.
     *
     * @param entities the entities to persist
     * @return
     * @see #persist()
     * @see #persist(Stream)
     * @see #persist(Object,Object...)
     */
    @CheckReturnValue
    @GenerateBridge(callSuperMethod = true)
    public static Uni<Void> persist(Iterable<?> entities) {
        return INSTANCE.persist(entities);
    }

    /**
     * Persist all given entities.
     *
     * @param entities the entities to persist
     * @return
     * @see #persist()
     * @see #persist(Iterable)
     * @see #persist(Object,Object...)
     */
    @CheckReturnValue
    @GenerateBridge(callSuperMethod = true)
    public static Uni<Void> persist(Stream<?> entities) {
        return INSTANCE.persist(entities);
    }

    /**
     * Persist all given entities.
     *
     * @param entities the entities to persist
     * @return
     * @see #persist()
     * @see #persist(Stream)
     * @see #persist(Iterable)
     */
    @CheckReturnValue
    @GenerateBridge(callSuperMethod = true)
    public static Uni<Void> persist(Object firstEntity, Object... entities) {
        return INSTANCE.persist(firstEntity, entities);
    }

    /**
     * Update all entities of this type matching the given query, with optional indexed parameters.
     *
     * @param query a {@link io.quarkus.hibernate.reactive.panache query string}
     * @param params optional sequence of indexed parameters
     * @return the number of entities updated.
     * @see #update(String, Map)
     * @see #update(String, Parameters)
     */
    @CheckReturnValue
    @GenerateBridge
    public static Uni<Integer> update(String query, Object... params) {
        throw INSTANCE.implementationInjectionMissing();
    }

    /**
     * Update all entities of this type matching the given query, with named parameters.
     *
     * @param query a {@link io.quarkus.hibernate.reactive.panache query string}
     * @param params {@link Map} of named parameters
     * @return the number of entities updated.
     * @see #update(String, Object...)
     * @see #update(String, Parameters)
     *
     */
    @CheckReturnValue
    @GenerateBridge
    public static Uni<Integer> update(String query, Map<String, Object> params) {
        throw INSTANCE.implementationInjectionMissing();
    }

    /**
     * Update all entities of this type matching the given query, with named parameters.
     *
     * @param query a {@link io.quarkus.hibernate.reactive.panache query string}
     * @param params {@link Parameters} of named parameters
     * @return the number of entities updated.
     * @see #update(String, Object...)
     * @see #update(String, Map)
     */
    @CheckReturnValue
    @GenerateBridge
    public static Uni<Integer> update(String query, Parameters params) {
        throw INSTANCE.implementationInjectionMissing();
    }
}
