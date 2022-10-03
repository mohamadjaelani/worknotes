package com.pe.ge.pe;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Helper class to unify stream handling
 *
 * @author iulius
 *
 */
public class IOUtils {

  /**
   * the default buffer size when handling stream data
   */
  private static final int BUFFER_SIZE = 4096;

  /**
   *  StreamHandler defines a generic method to handle the stream data
   */
  public interface StreamHandler {

    /**
     * generic method handling the buffered part of the stream
     *
     * @param buffer
     *    the buffer to handle
     * @param offset
     *    the offset to start handling from
     * @param length
     *    the length to read
     * @throws IOException
     */
    void handleStreamBuffer(byte[] buffer, int offset, int length) throws IOException;

  }

  /**
   * copies the input stream to the output stream
   *
   * @param inputStream
   *    the source stream
   * @param outputStream
   *    the target stream
   * @throws IOException
   */
  public static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
	  copy(inputStream, outputStream, new byte[BUFFER_SIZE]);
  }

  /**
   * copies the input stream to the output stream using a custom buffer size
   *
   * @param inputStream
   *    the source stream
   * @param outputStream
   *    the target strem
   * @param buffer
   *    the custom buffer
   * @throws IOException
   */
  public static void copy(InputStream inputStream, OutputStream outputStream, byte[] buffer) throws IOException {
    copy(inputStream, outputStream, buffer, null);
  }

  /**
   * copies the input stream to the output stream using a custom buffer size and applying additional stream handling
   *
   * @param inputStream
   *    the source stream
   * @param outputStream
   *    the target stream
   * @param buffer
   *    the custom buffer
   * @param addtionalHandling
   *    a stream handler that allows additional handling of the stream
   * @throws IOException
   */
  public static void copy(InputStream inputStream, final OutputStream outputStream, byte[] buffer, final StreamHandler addtionalHandling) throws IOException {
    process(inputStream, new StreamHandler() {
      @Override
      public void handleStreamBuffer(byte[] buffer, int offset, int length) throws IOException {
        outputStream.write(buffer, offset, length);
        if( addtionalHandling != null ) {
          addtionalHandling.handleStreamBuffer(buffer, offset, length);
        }
      }

    }, buffer);
  }

  /**
   * generic processing of a stream
   *
   * @param inputStream
   *    the input stream to process
   * @param handler
   *    the handler to apply on the stream
   * @throws IOException
   */
  public static void process(InputStream inputStream, StreamHandler handler) throws IOException {
    process(inputStream, handler, new byte[BUFFER_SIZE]);
  }

  /**
   * generic processing of a stream with a custom buffer
   *
   * @param inputStream
   *    the input stream to process
   * @param handler
   *    the handler to apply on the stream
   * @param buffer
   *    the buffer to use for stream handling
   * @throws IOException
   */
  public static void process(InputStream inputStream, StreamHandler handler, byte[] buffer) throws IOException {
    int read = -1;
    while( (read = inputStream.read(buffer)) != -1 ) {
      handler.handleStreamBuffer(buffer, 0, read);
    }
  }

}
