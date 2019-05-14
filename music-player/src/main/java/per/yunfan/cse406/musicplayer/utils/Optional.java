package per.yunfan.cse406.musicplayer.utils;

import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class Optional<T> implements Serializable {

    private static final Optional<?> EMPTY = new Optional<>();

    private final T value;

    private Optional() {
        value = null;
    }

    private Optional(T value) {
        this.value = value;
    }

    @SuppressWarnings("unchecked")
    public static <X> Optional<X> of(X value) {
        if (value == null) {
            return (Optional<X>) EMPTY;
        }
        return new Optional<>(value);
    }

    @SuppressWarnings("unchecked")
    public static <X> Optional<X> empty() {
        return (Optional<X>) EMPTY;
    }

    public boolean isPresent() {
        return this.value != null;
    }

    public Else ifPresent(Consumer<T> ifDo) {
        if (this.value != null) {
            ifDo.accept(value);
            return Else.DO_NOTHING;
        } else {
            return new Else(true);
        }
    }

    public T get() {
        if (value != null) {
            return value;
        }
        throw new NoSuchElementException("Optional doesn't have nullable value!");
    }

    private static class Else implements Serializable {
        private final boolean isRun;

        private static final Else DO_NOTHING = new Else(false);

        Else(boolean isRun) {
            this.isRun = isRun;
        }

        public void elseDo(Runnable callback) {
            if (isRun) {
                callback.run();
            }
        }

        public Else nothing() {
            return DO_NOTHING;
        }
    }


}
