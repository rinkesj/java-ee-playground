package com.airhacks.ping.test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

public class Filter implements ClientRequestFilter, ClientResponseFilter, WriterInterceptor {

	private static final int LIMIT = 1024 * 8;

	@Override
	public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
		System.out.println("aroundWriteTo for media type" + context.getMediaType());
	}

	@Override
	public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
		System.out.println("filter Response for method " + requestContext.getMethod());
		System.out.println("filter response has entity" + responseContext.hasEntity());
		System.out.println("filter response length" + responseContext.hasEntity());
		if (responseContext.hasEntity()) {
			responseContext.setEntityStream(logStream(responseContext.getEntityStream()));
		}
	}

	private InputStream logStream(InputStream entityStream) throws IOException {
		if(!entityStream.markSupported()) {
			System.out.println("mark not supported");
			entityStream = new BufferedInputStream(entityStream);
		}
		
		entityStream.mark(LIMIT);
		byte[] bytes = new byte[LIMIT];
		int readBytes = entityStream.read(bytes);
		System.out.println("read bytes " + readBytes);
		System.out.println("response body " + new String(bytes));
		entityStream.reset();
		
		return entityStream;
	}

	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {
		System.out.println("filter request for method " + requestContext.getMethod());
	}

}
