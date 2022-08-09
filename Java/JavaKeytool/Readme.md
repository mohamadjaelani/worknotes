## Create Certificate using Java keytool (keystore)
### 1. Create Keypairs
      E:\LIBRARY\FILES\UNCAL\AS2\Certificate>keytool -genkey -alias uncal-sender -keyalg RSA -keystore hasil-keystore.jks
      Enter keystore password:
      Re-enter new password:
      What is your first and last name?
        [Unknown]:  Mohamad Jaelani
      What is the name of your organizational unit?
        [Unknown]:  Uncal-dt
      What is the name of your organization?
        [Unknown]:  CIC
      What is the name of your City or Locality?
        [Unknown]:  Bogor
      What is the name of your State or Province?
        [Unknown]:  Jawa Barat
      What is the two-letter country code for this unit?
        [Unknown]:  ID
      Is CN=Mohamad Jaelani, OU=Uncal-dt, O=CIC, L=Bogor, ST=Jawa Barat, C=ID correct?
        [no]:  y

      Enter key password for <uncal-sender>
              (RETURN if same as keystore password):

      Warning:
      The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore sender-keystore.jks -destkeystore sender-keystore.jks -deststoretype pkcs12".

### 2. Extract public key from hasil-keystore.jks
      E:\LIBRARY\FILES\UNCAL\AS2\Certificate>keytool -export -alias uncal-sender -keystore hasil-keystore.jks -file sender-trust.crt
      Enter keystore password:
      Certificate stored in file <sender-trust.crt>

      Warning:
      The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore sender-keystore.jks -destkeystore sender-keystore.jks -deststoretype pkcs12".

      ======== extract public key from receiver keystore
      E:\LIBRARY\FILES\UNCAL\AS2\Certificate>keytool -export -alias uncal-receiver -keystore receiver-keystore.jks -file receiver-trust.crt
      Enter keystore password:
      Certificate stored in file <receiver-trust.crt>

      Warning:
      The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore receiver-keystore.jks -destkeystore receiver-keystore.jks -deststoretype pkcs12".

### 3. Import Public key into hasil-keystore.jks
      E:\LIBRARY\FILES\UNCAL\AS2\Certificate>keytool -import -alias uncal-receiver-public -keystore hasil-keystore.jks -file receiver-trust.crt
      Enter keystore password:
      Owner: CN=Mang Jojot, OU=Uncal-dt, O=CIC, L=Bogor, ST=Jawa Barat, C=ID
      Issuer: CN=Mang Jojot, OU=Uncal-dt, O=CIC, L=Bogor, ST=Jawa Barat, C=ID
      Serial number: 644a07ad
      Valid from: Mon Oct 19 10:36:57 ICT 2020 until: Sun Jan 17 10:36:57 ICT 2021
      Certificate fingerprints:
               MD5:  6E:86:C0:E7:61:DF:8A:BF:08:E1:D8:E8:BF:08:1D:F0
               SHA1: 5A:5D:02:6E:95:8C:37:70:71:79:7D:AC:CA:6F:93:CC:07:06:49:35
               SHA256: 50:78:29:67:46:3A:5B:E6:A6:1D:C9:F0:70:AB:E9:8A:EE:4D:3B:83:C9:6F:5F:9E:FA:12:05:FB:B2:43:65:AE
      Signature algorithm name: SHA256withRSA
      Subject Public Key Algorithm: 2048-bit RSA key
      Version: 3

      Extensions:

      #1: ObjectId: 2.5.29.14 Criticality=false
      SubjectKeyIdentifier [
      KeyIdentifier [
      0000: 45 0D FA B8 73 13 69 F2   84 28 05 00 7C 8C BE 39  E...s.i..(.....9
      0010: F3 B4 90 6F                                        ...o
      ]
      ]

      Trust this certificate? [no]:  y
      Certificate was added to keystore

      Warning:
      The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore sender-keystore.jks -destkeystore sender-keystore.jks -deststoretype pkcs12".

======== extract public key from receiver keystore

E:\LIBRARY\FILES\UNCAL\AS2\Certificate>keytool -import -alias uncal-sender-public -keystore receiver-keystore.jks -file sender-trust.crt
Enter keystore password:
Owner: CN=Mohamad Jaelani, OU=Uncal-dt, O=CIC, L=Bogor, ST=Jawa Barat, C=ID
Issuer: CN=Mohamad Jaelani, OU=Uncal-dt, O=CIC, L=Bogor, ST=Jawa Barat, C=ID
Serial number: 4098baab
Valid from: Mon Oct 19 10:33:07 ICT 2020 until: Sun Jan 17 10:33:07 ICT 2021
Certificate fingerprints:
         MD5:  FB:E4:8D:AD:77:CC:8D:4F:8A:71:1F:D9:39:1E:68:D6
         SHA1: 1E:74:F3:F1:01:9B:0E:89:60:19:49:13:2E:87:1A:28:E6:AD:A0:24
         SHA256: 20:5A:DD:E1:9C:B1:98:3E:AA:A5:62:A8:5D:6D:85:A5:C5:45:7D:60:22:A5:45:7B:BE:6D:27:FC:73:61:74:BB
Signature algorithm name: SHA256withRSA
Subject Public Key Algorithm: 2048-bit RSA key
Version: 3

Extensions:

#1: ObjectId: 2.5.29.14 Criticality=false
SubjectKeyIdentifier [
KeyIdentifier [
0000: 19 A4 C3 D4 ED 94 87 B4   86 D9 65 79 B3 8B 49 C4  ..........ey..I.
0010: B6 73 B5 9D                                        .s..
]
]

Trust this certificate? [no]:  y
Certificate was added to keystore

Warning:
The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore receiver-keystore.jks -destkeystore receiver-keystore.jks -deststoretype pkcs12".

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
