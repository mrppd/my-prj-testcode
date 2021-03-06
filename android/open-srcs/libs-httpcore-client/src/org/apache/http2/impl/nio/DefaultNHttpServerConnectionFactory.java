/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package org.apache.http2.impl.nio;

import org.apache.http2.HttpRequestFactory;
import org.apache.http2.annotation.Immutable;
import org.apache.http2.impl.DefaultHttpRequestFactory;
import org.apache.http2.nio.NHttpConnectionFactory;
import org.apache.http2.nio.NHttpServerConnection;
import org.apache.http2.nio.reactor.IOSession;
import org.apache.http2.nio.util.ByteBufferAllocator;
import org.apache.http2.nio.util.HeapByteBufferAllocator;
import org.apache.http2.params.HttpConnectionParams;
import org.apache.http2.params.HttpParams;

/**
 * Factory for plain (non-encrypted), non-blocking {@link NHttpServerConnection}s.
 * <p>
 * The following parameters can be used to customize the behavior of this
 * class:
 * <ul>
 *  <li>{@link org.apache.http2.params.CoreProtocolPNames#HTTP_ELEMENT_CHARSET}</li>
 *  <li>{@link org.apache.http2.params.CoreConnectionPNames#SO_TIMEOUT}</li>
 *  <li>{@link org.apache.http2.params.CoreConnectionPNames#SOCKET_BUFFER_SIZE}</li>
 *  <li>{@link org.apache.http2.params.CoreConnectionPNames#MAX_HEADER_COUNT}</li>
 *  <li>{@link org.apache.http2.params.CoreConnectionPNames#MAX_LINE_LENGTH}</li>
 * </ul>
 *
 * @since 4.2
 */
@Immutable
public class DefaultNHttpServerConnectionFactory
    implements NHttpConnectionFactory<DefaultNHttpServerConnection> {

    private final HttpRequestFactory requestFactory;
    private final ByteBufferAllocator allocator;
    private final HttpParams params;

    public DefaultNHttpServerConnectionFactory(
            final HttpRequestFactory requestFactory,
            final ByteBufferAllocator allocator,
            final HttpParams params) {
        super();
        if (requestFactory == null) {
            throw new IllegalArgumentException("HTTP request factory may not be null");
        }
        if (allocator == null) {
            throw new IllegalArgumentException("Byte buffer allocator may not be null");
        }
        if (params == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        this.requestFactory = requestFactory;
        this.allocator = allocator;
        this.params = params;
    }

    public DefaultNHttpServerConnectionFactory(final HttpParams params) {
        this(new DefaultHttpRequestFactory(), new HeapByteBufferAllocator(), params);
    }

    protected DefaultNHttpServerConnection createConnection(
            final IOSession session,
            final HttpRequestFactory requestFactory,
            final ByteBufferAllocator allocator,
            final HttpParams params) {
        return new DefaultNHttpServerConnection(session, requestFactory, allocator, params);
    }

    public DefaultNHttpServerConnection createConnection(final IOSession session) {
        DefaultNHttpServerConnection conn = createConnection(
                session, this.requestFactory, this.allocator, this.params);
        int timeout = HttpConnectionParams.getSoTimeout(this.params);
        conn.setSocketTimeout(timeout);
        return conn;
    }

}
