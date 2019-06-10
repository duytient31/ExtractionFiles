package org.spongycastle.tls.crypto.impl.bc;

import java.io.IOException;

import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.tls.crypto.TlsAgreement;
import org.spongycastle.tls.crypto.TlsSecret;

/**
 * Support class for ephemeral Elliptic Curve Diffie-Hellman using the BC light-weight library.
 */
public class BcTlsECDH implements TlsAgreement
{
    protected final BcTlsECDomain domain;

    protected AsymmetricCipherKeyPair localKeyPair;
    protected ECPublicKeyParameters peerPublicKey;

    public BcTlsECDH(BcTlsECDomain domain)
    {
        this.domain = domain;
    }

    public byte[] generateEphemeral() throws IOException
    {
        this.localKeyPair = domain.generateKeyPair();

        return domain.encodePublicKey((ECPublicKeyParameters)localKeyPair.getPublic());
    }

    public void receivePeerValue(byte[] peerValue) throws IOException
    {
        this.peerPublicKey = domain.decodePublicKey(peerValue);
    }

    public TlsSecret calculateSecret() throws IOException
    {
        return domain.calculateECDHAgreement((ECPrivateKeyParameters)localKeyPair.getPrivate(), peerPublicKey);
    }
}
