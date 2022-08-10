## Create Certificate using Java keytool (keystore)
### 1. Create Keypairs
      E:\LIBRARY\FILES\UNCAL\AS2\Certificate>keytool -genkey -alias uncal-sender -keyalg RSA -keystore hasil-keystore.jks
### 2. Extract public key from hasil-keystore.jks
      E:\LIBRARY\FILES\UNCAL\AS2\Certificate>keytool -export -alias uncal-sender -keystore hasil-keystore.jks -file hasil-trust.crt
### 3. Import Public key into hasil-keystore.jks
      E:\LIBRARY\FILES\UNCAL\AS2\Certificate>keytool -import -alias uncal-receiver-public -keystore hasil-keystore.jks -file receiver-trust.crt
### Tambahan/catatan ajah
      ========================================================================================================================================================
      keytool -genkey -keyalg RSA -alias uncal -keystore keystore.jks -storepass test1234 -validity 360 -keysize 2048
      ubah ke pkcs12
      keytool -importkeystore -srckeystore keystore.jks -destkeystore keystore12.jks -deststoretype pkcs12
      export ke sertificate
      keytool -export -alias uncal -keystore keystore12.jks -rfc file sertifikat.cer
      ============================================================================================================================================
      keytool -importkeystore -srckeystore jojot.jks -destkeystore jojot.p12 -srcstoretype JKS -deststoretype PKCS12 -deststorepass test1234
      Importing keystore jojot.jks to jojot.p12...
      Enter source keystore password:
      Entry for alias jojot successfully imported.
      Import command completed:  1 entries successfully imported, 0 entries failed or cancelled
      ================verifikasi
      keytool -list -v -keystore jojot.p12 -storetype PKCS12
      Enter keystore password:
      Keystore type: PKCS12
      Keystore provider: SunJSSE

      Your keystore contains 1 entry


      AES 128	keytool -genseckey -alias 128bitkey -keyalg aes -keysize 128 -keystore keystore.jceks -storetype jceks
      AES 256	keytool -genseckey -alias 256bitkey -keyalg aes -keysize 256 -keystore keystore.jceks -storetype jceks
