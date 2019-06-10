package org.spongycastle.jsse.provider;

import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionBindingEvent;
import javax.net.ssl.SSLSessionBindingListener;
import javax.net.ssl.SSLSessionContext;
import javax.security.auth.x500.X500Principal;

import org.spongycastle.tls.SessionParameters;
import org.spongycastle.tls.TlsSession;
import org.spongycastle.util.Arrays;

// TODO[jsse] Serializable ?
class ProvSSLSession
    implements SSLSession
{
    // TODO[jsse] Ensure this behaves according to the javadoc for SSLSocket.getSession and SSLEngine.getSession
    protected final static ProvSSLSession NULL_SESSION = new ProvSSLSession(null, null);

    protected final Map<String, Object> valueMap = Collections.synchronizedMap(new HashMap<String, Object>());

    protected final ProvSSLSessionContext sslSessionContext;
    protected final TlsSession tlsSession;
    protected final SessionParameters sessionParameters;

    ProvSSLSession(ProvSSLSessionContext sslSessionContext, TlsSession tlsSession)
    {
        this.sslSessionContext = sslSessionContext;
        this.tlsSession = tlsSession;
        this.sessionParameters = tlsSession == null ? null : tlsSession.exportSessionParameters();
    }

    public int getApplicationBufferSize()
    {
        // TODO[jsse] See comments for getPacketBufferSize
        return 1 << 14; 
    }

    public String getCipherSuite()
    {
        return sessionParameters == null
            ?   null
            :   sslSessionContext.getSSLContext().getCipherSuiteString(sessionParameters.getCipherSuite());
    }

    public long getCreationTime()
    {
        throw new UnsupportedOperationException();
    }

    public byte[] getId()
    {
        return tlsSession == null
            ?   null
            :   Arrays.clone(tlsSession.getSessionID());
    }

    public long getLastAccessedTime()
    {
        throw new UnsupportedOperationException();
    }

    public Certificate[] getLocalCertificates()
    {
        if (sessionParameters != null)
        {
            X509Certificate[] chain = JsseUtils.getX509CertificateChain(sslSessionContext.getCrypto(), sessionParameters.getLocalCertificate());
            if (chain != null && chain.length > 0)
            {
                return chain;
            }
        }

        return null;
    }

    public Principal getLocalPrincipal()
    {
        return sessionParameters == null
            ?   null
            :   JsseUtils.getSubject(sslSessionContext.getCrypto(), sessionParameters.getLocalCertificate());
    }

    public int getPacketBufferSize()
    {
        /*
         * TODO[jsse] This is the maximum possible per RFC (but see jsse.SSLEngine.acceptLargeFragments System property).
         * It would be nice to dynamically check with the underlying RecordStream, which might know a tighter limit, e.g.
         * when the max_fragment_length extension has been negotiated, or when no compression was negotiated).
         */
        // Header size + Fragment length limit + Compression expansion + Cipher expansion
        return 5 + (1 << 14) + 1024 + 1024; 
    }

    public javax.security.cert.X509Certificate[] getPeerCertificateChain() throws SSLPeerUnverifiedException
    {
        /*
         * TODO[jsse] "Note: this method exists for compatibility with previous releases. New
         * applications should use getPeerCertificates() instead."
         */
        throw new UnsupportedOperationException();
    }

    public Certificate[] getPeerCertificates() throws SSLPeerUnverifiedException
    {
        if (sessionParameters != null)
        {
            X509Certificate[] chain = JsseUtils.getX509CertificateChain(sslSessionContext.getCrypto(), sessionParameters.getPeerCertificate());
            if (chain != null && chain.length > 0)
            {
                return chain;
            }
        }

        throw new SSLPeerUnverifiedException("No peer identity established");
    }

    public String getPeerHost()
    {
        // TODO[jsse] "It is mainly used as a hint for SSLSession caching strategies."
        return null;
    }

    public int getPeerPort()
    {
        // TODO[jsse] "It is mainly used as a hint for SSLSession caching strategies."
        return -1;
    }

    public Principal getPeerPrincipal() throws SSLPeerUnverifiedException
    {
        if (sessionParameters != null)
        {
            X500Principal principal = JsseUtils.getSubject(sslSessionContext.getCrypto(), sessionParameters.getPeerCertificate());
            if (principal != null)
            {
                return principal;
            }
        }

        throw new SSLPeerUnverifiedException("No peer identity established");
    }

    public String getProtocol()
    {
        return sessionParameters == null
            ?   null
            :   sslSessionContext.getSSLContext().getProtocolString(sessionParameters.getNegotiatedVersion());
    }

    public SSLSessionContext getSessionContext()
    {
        return sslSessionContext;
    }

    public Object getValue(String name)
    {
        return valueMap.get(name);
    }

    public String[] getValueNames()
    {
        synchronized (valueMap)
        {
            return valueMap.keySet().toArray(new String[valueMap.size()]);
        }
    }

    public void invalidate()
    {
        if (tlsSession != null)
        {
            tlsSession.invalidate();
        }
    }

    public boolean isValid()
    {
        return tlsSession != null && tlsSession.isResumable();
    }

    public void putValue(String name, Object value)
    {
        notifyUnbound(name, valueMap.put(name, value));
        notifyBound(name, value);
    }

    public void removeValue(String name)
    {
        notifyUnbound(name, valueMap.remove(name));
    }

    protected void notifyBound(String name, Object value)
    {
        if (value instanceof SSLSessionBindingListener)
        {
            new SessionBindingListenerAdapter((SSLSessionBindingListener)value)
                .valueBound(new SSLSessionBindingEvent(this, name));
        }
    }

    protected void notifyUnbound(String name, Object value)
    {
        if (value instanceof SSLSessionBindingListener)
        {
            new SessionBindingListenerAdapter((SSLSessionBindingListener)value)
                .valueUnbound(new SSLSessionBindingEvent(this, name));
        }
    }
}
