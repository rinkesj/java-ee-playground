package com.airhacks.ping.test;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

public class Filter implements ClientRequestFilter, ClientResponseFilter, WriterInterceptor {

	private static final int LIMIT = 1024 * 8;
	private static final String ENTITY_LOGGER_PROPERTY = Filter.class.getName() + ".entityLogger";;

	@Override
	public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
		System.out.println("filter Response for method " + requestContext.getMethod());
		System.out.println("filter response has entity" + responseContext.hasEntity());
		System.out.println("filter response length" + responseContext.hasEntity());
		
		
		if (responseContext.hasEntity()) {
			responseContext.setEntityStream(logStream(responseContext.getEntityStream()));
		}
		
		printStackTrace();
	}

	private void printStackTrace() {
		
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		Arrays.asList(ste).forEach(System.out::println);
	}

	private InputStream logStream(InputStream entityStream) throws IOException {
		if(!entityStream.markSupported()) {
			System.out.println("mark not supported");
			entityStream = new BufferedInputStream(entityStream);
		}
		
		entityStream.mark(LIMIT+1);
		byte[] bytes = new byte[LIMIT+1];
		int readBytes = entityStream.read(bytes);
		System.out.println("read bytes " + readBytes);
		System.out.println("response body " + new String(bytes));
		entityStream.reset();
		
		return entityStream;
	}
	
	 @Override
	    public void aroundWriteTo(final WriterInterceptorContext writerInterceptorContext)
	            throws IOException, WebApplicationException {
	        final LoggingStream stream = (LoggingStream) writerInterceptorContext.getProperty(ENTITY_LOGGER_PROPERTY);
	        writerInterceptorContext.proceed();
	        if (stream != null) {
	            System.out.println("Request body " + stream.getStringBuilder(StandardCharsets.UTF_8));
	            writerInterceptorContext.removeProperty(ENTITY_LOGGER_PROPERTY);
	        }
	    }

	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {
		System.out.println("filter request for method " + requestContext.getMethod());
		
		StringBuilder b = new StringBuilder();
		
		if (requestContext.hasEntity()) {
            final OutputStream stream = new LoggingStream(b, requestContext.getEntityStream());
            requestContext.setEntityStream(stream);
            requestContext.setProperty(ENTITY_LOGGER_PROPERTY, stream);
            // not calling log(b) here - it will be called by the interceptor
        } else {
            System.out.println("Request - no body.");
        }
	}
	
	private class LoggingStream extends FilterOutputStream {

        private static final int maxEntitySize = LIMIT;
		private final StringBuilder b;
        private final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        LoggingStream(final StringBuilder b, final OutputStream inner) {
            super(inner);

            this.b = b;
        }

        StringBuilder getStringBuilder(final Charset charset) {
            // write entity to the builder
            final byte[] entity = baos.toByteArray();

            b.append(new String(entity, 0, Math.min(entity.length, maxEntitySize), charset));
            if (entity.length > maxEntitySize) {
                b.append("...more...");
            }
            b.append('\n');

            return b;
        }

        @Override
        public void write(final int i) throws IOException {
            if (baos.size() <= maxEntitySize) {
                baos.write(i);
            }
            
            System.out.println("write " + i);
            
            out.write(i);
        }
    }
	

}
