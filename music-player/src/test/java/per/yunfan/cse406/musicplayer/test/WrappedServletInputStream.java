package per.yunfan.cse406.musicplayer.test;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * For test only, it wrapped a ByteArrayInputStream and help testing servlet to pass json
 */
public class WrappedServletInputStream extends ServletInputStream {

    /**
     * Wrapped InputStream for customization
     */
    private final ByteArrayInputStream wrapped;

    /**
     * Constructor for this test stream
     *
     * @param inputStream Wrapped InputStream for customization
     */
    public WrappedServletInputStream(ByteArrayInputStream inputStream) {
        this.wrapped = inputStream;
    }

    /**
     * Returns true when all the data from the stream has been read else
     * it returns false.
     *
     * @return <code>true</code> when all data for this particular request
     * has been read, otherwise returns <code>false</code>.
     * @since Servlet 3.1
     */
    @Override
    public boolean isFinished() {
        return wrapped.available() > 0;
    }

    /**
     * Returns true if data can be read without blocking else returns
     * false.
     *
     * @return <code>true</code> if data can be obtained without blocking,
     * otherwise returns <code>false</code>.
     * @since Servlet 3.1
     */
    @Override
    public boolean isReady() {
        return true;
    }

    /**
     * Instructs the <code>ServletInputStream</code> to invoke the provided
     * {@link ReadListener} when it is possible to read
     *
     * @param readListener the {@link ReadListener} that should be notified
     *                     when it's possible to read.
     * @throws IllegalStateException if one of the following conditions is true
     *                               <ul>
     *                               <li>the associated request is neither upgraded nor the async started
     *                               <li>setReadListener is called more than once within the scope of the same request.
     *                               </ul>
     * @throws NullPointerException  if readListener is null
     * @since Servlet 3.1
     */
    @Override
    public void setReadListener(ReadListener readListener) {

    }

    /**
     * Reads the next byte of data from the input stream. The value byte is
     * returned as an <code>int</code> in the range <code>0</code> to
     * <code>255</code>. If no byte is available because the end of the stream
     * has been reached, the value <code>-1</code> is returned. This method
     * blocks until input data is available, the end of the stream is detected,
     * or an exception is thrown.
     *
     * <p> A subclass must provide an implementation of this method.
     *
     * @return the next byte of data, or <code>-1</code> if the end of the
     * stream is reached.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public int read() throws IOException {
        return wrapped.read();
    }
}
