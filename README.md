# kafka4 kullanacagiz. 
kafka 4 de , zookeeper yok. Kraft mod var. bu yuzden sadece kafkayi , BINARY olarak indirmemiz yeterli olacaktir. source koduna ihtiyacimiz yok. 

ne yapacagiz, genel anlatim.
1- kafkayi binary olarak indirin. 
2- cluster id uretmemiz gerekiyor
3- urettigimiz id ile , controller ve brokeri formatlamamiz gerekiyor. 1 kere yapilacak. macOS ise , kafkanin meta.propertieslerini degistirmemiz gerekiyor. cunku bu arkadas format sonrasi datalari /tmp klasorune kaydediyor. macosta bu dosya her pc restartinda silindigi icin , ekstra is lazim. en altta o var. 
4- calistirin ve baglanin. zor degil


cluster id uretmek: (kafkanin dosyasina girdikten sonra, terminale su kodu yapistirin.)
`bin/kafka-storage.sh random-uuid`
 örnek çıktı: AbCDefGhIjKlMnOpQrStUv

controller formatlamak (standalone argumentini koymak zorunluymus)
`bin/kafka-storage.sh format \
  -t AbCDefGhIjKlMnOpQrStUv \
  -c config/controller.properties \
  --standalone`

broker icin de aynisi , standalone yok.
`bin/kafka-storage.sh format \
  -t AbCDefGhIjKlMnOpQrStUv \
  -c config/broker.properties`


bu adimdan sonra sadece calistirmak kaldi. once controller sonra broker
`bin/kafka-server-start.sh config/controller.properties`
`bin/kafka-server-start.sh config/broker.properties`

sonra da java uygulamasini baslat , olacaktir. 
eger bu projeyi deniyorsan , `127.0.0.1:8080/rest/api/producerMsg?message=araba` istegine chromeden gitmeye calisirsan , ekranda OK gorursun. java konsolunda da mesaji gorursun. 


macos icin ekstra ayarlar:
yukarda da bahsettim , birdaha konuyu genisletelim.
kafka , biz cluster id'mizi aldiktan sonra attigimiz formatta , yeni gelen datalari /tmp dosyasina yaziyor. buraya kadar problem yok. 

lakin macosta , /tmp klasoru isletim sistemi tarafindan temporary klasor olarak gozuktugu icin , her pc restartinda siliniyor. bunu config dosyalarindaki verileri degistirip kaydedersek cozebiliriz. zor degil.
1- controller ayari:
kafkanin ana dizininde , config klasorunu acin.
config>controller.properties
`log.dirs=/tmp/kraft-controller-logs`
bu satiri bulun.
gordugunuz uzere , tmp klasorunun icine yaziyor. bunu , farkli bir yere cekecegiz. kafkanin icinde bir klasore de cekebiliriz , bilgisayarin dokumanlarina da. kafkanin icine cekerseniz , pathi tamamen uzun vermeniz gerekecek. keyfiniz bilir. benimkinin pathi buna benziyor: /Users/karakoc/Downloads/kafka_2.13-4.0.1


ana dizine yeni bir klasor olusturun. kafka-logs diye, altina da controller ve broker diye 2 tane daha klasor.
controller icin, 
`log.dirs=/ /Users/karakoc/Downloads/kafka_2.13-4.0.1/kafka-logs/controller` yapin.
broker icin de aynisi , /broker.

bu sayede , macbook kullandiginiz zaman her seferinde cluster id resetlemek zorunda kalmayacaksiniz. 



# CALISTIRMA
(2 farkli terminal ile kafkanin klasorune girin.)
`bin/kafka-server-start.sh config/controller.properties`
`bin/kafka-server-start.sh config/broker.properties`

iki farkli terminalde acin. 

ilk once controller , sonra broker.
end
daha sonra bu java programini da calistirirsaniz , cincik.


