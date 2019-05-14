package per.yunfan.cse406.musicplayer.utils;

import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * Serializable nullable wrapped object, is seem as java.util.Optional
 *
 * @param <T> nullable  wrapped object type
 */
public class Nullable<T> implements Serializable {

    /**
     * Empty nullable object
     */
    private static final Nullable<?> EMPTY = new Nullable<>();

    /**
     * Wrapped value
     */
    private final T value;

    /**
     * Constructor for create an empty nullable object
     */
    private Nullable() {
        value = null;
    }

    /**
     * Constructor for create a nullable object
     *
     * @param value Wrapped object
     */
    private Nullable(T value) {
        this.value = value;
    }

    /**
     * Factory method for create a nullable object
     *
     * @param value Wrapped object
     * @param <X>   Wrapped object type
     * @return Nullable object that wrapped value
     */
    @SuppressWarnings("unchecked")
    public static <X> Nullable<X> of(X value) {
        if (value == null) {
            return (Nullable<X>) EMPTY;
        }
        return new Nullable<>(value);
    }

    /**
     * Return an empty nullable object
     *
     * @param <X> Wrapped object type
     * @return An empty nullable object
     */
    @SuppressWarnings("unchecked")
    public static <X> Nullable<X> empty() {
        return (Nullable<X>) EMPTY;
    }

    /**
     * @return Does this nullable object have value
     */
    public boolean isPresent() {
        return this.value != null;
    }

    /**
     * If this nullable object have value, then do the action
     *
     * @param ifDo The action callback when have value
     * @return Else object
     */
    public Else ifPresent(Consumer<T> ifDo) {
        if (this.value != null) {
            ifDo.accept(value);
            return Else.DO_NOTHING;
        } else {
            return new Else(true);
        }
    }

    /**
     * @return Get if present
     */
    public T get() {
        if (value != null) {
            return value;
        }
        throw new NoSuchElementException("Nullable doesn't have nullable value!");
    }

    /**
     * Return type for ifPresent method, it could make call like: nullable.ifPresent(func).elseDo(otherFunc)
     */
    public static class Else implements Serializable {

        /**
         * Need run callback
         */
        private final boolean needRun;

        /**
         * Empty else object
         */
        private static final Else DO_NOTHING = new Else(false);

        /**
         * Constructor for else, pass need run callback value
         *
         * @param needRun Does need run callback
         */
        Else(boolean needRun) {
            this.needRun = needRun;
        }

        /**
         * Else do callback if need run
         *
         * @param callback callback function
         */
        public void elseDo(Runnable callback) {
            if (needRun) {
                callback.run();
            }
        }

        /**
         * @return Empty else object
         */
        public Else nothing() {
            return DO_NOTHING;
        }
    }


}
