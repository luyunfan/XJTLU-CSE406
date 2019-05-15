package per.yunfan.cse406.musicplayer.test;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * For test only, it wrapped a ByteArrayOutputStream and help testing servlet to pass byte stream
 */
public class WrappedServletOutputStream extends ServletOutputStream {

    /**
     * Wrapped OutputStream for customization
     */
    private final ByteArrayOutputStream wrapped;

    /**
     * Constructor for this test stream
     *
     * @param wrapped Wrapped OutputStream for customization
     */
    public WrappedServletOutputStream(ByteArrayOutputStream wrapped) {
        this.wrapped = wrapped;
    }

    /**
     * This method can be used to determine if data can be written without blocking.
     *
     * @return <code>true</code> if a write to this <code>ServletOutputStream</code>
     * will succeed, otherwise returns <code>false</code>.
     * @since Servlet 3.1
     */
    @Override
    public boolean isReady() {
        return true;
    }

    /**
     * Instructs the <code>ServletOutputStream</code> to invoke the provided
     * {@link WriteListener} when it is possible to write
     *
     * @param writeListener the {@link WriteListener} that should be notified
     *                      when it's possible to write
     * @throws IllegalStateException if one of the following conditions is true
     *                               <ul>
     *                               <li>the associated request is neither upgraded nor the async started
     *                               <li>setWriteListener is called more than once within the scope of the same request.
     *                               </ul>
     * @throws NullPointerException  if writeListener is null
     * @since Servlet 3.1
     */
    @Override
    public void setWriteListener(WriteListener writeListener) {

    }

    /**
     * Writes the specified byte to this output stream. The general
     * contract for <code>write</code> is that one byte is written
     * to the output stream. The byte to be written is the eight
     * low-order bits of the argument <code>b</code>. The 24
     * high-order bits of <code>b</code> are ignored.
     * <p>
     * Subclasses of <code>OutputStream</code> must provide an
     * implementation for this method.
     *
     * @param b the <code>byte</code>.
     * @throws IOException if an I/O error occurs. In particular,
     *                     an <code>IOException</code> may be thrown if the
     *                     output stream has been closed.
     */
    @Override
    public void write(int b) throws IOException {
        this.wrapped.write(b);
    }
}
