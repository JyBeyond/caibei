package com.facebook.shuiai.project.util.netUtil;

/**
 * @author shuiai
 * 2016年12月8日15:34:24
 */
public class SSLContextUtil {

//    /**
//     * 拿到https证书, SSLContext (NoHttp已经修补了系统的SecureRandom的bug)。
//     */
//    @SuppressLint("TrulyRandom")
//    public static SSLContext getSSLContext() {
//        SSLContext sslContext = null;
//        try {
//            sslContext = SSLContext.getInstance("TLS");
//            InputStream inputStream = DJApplication.mInstance.getAssets().open("srca.cer");
//
//            CertificateFactory cerFactory = CertificateFactory.getInstance("X.509");
//            Certificate cer = cerFactory.generateCertificate(inputStream);
//
//            KeyStore keyStore = KeyStore.getInstance("PKCS12");
//            keyStore.load(null, null);
//            keyStore.setCertificateEntry("trust", cer);
//
//            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
//            keyManagerFactory.init(keyStore, null);
//
//            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//            trustManagerFactory.init(keyStore);
//
//            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return sslContext;
//    }
//
//    /**
//     * 如果不需要https证书.(NoHttp已经修补了系统的SecureRandom的bug)。
//     */
//    public static SSLContext getDefaultSLLContext() {
//        SSLContext sslContext = null;
//        try {
//            sslContext = SSLContext.getInstance("TLS");
//            sslContext.init(null, new TrustManager[]{trustManagers}, new SecureRandom());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return sslContext;
//    }
//
//    private static TrustManager trustManagers = new X509TrustManager() {
//
//        @Override
//        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//        }
//
//        @Override
//        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//
//        }
//
//        @Override
//        public X509Certificate[] getAcceptedIssuers() {
//            return new X509Certificate[0];
//        }
//    };
//
//    public static final HostnameVerifier HOSTNAME_VERIFIER = new HostnameVerifier() {
//        public boolean verify(String hostname, SSLSession session) {
//            return true;
//        }
//    };

}
