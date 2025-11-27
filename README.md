# kafka4 kullanacagiz. 
kafka 4 de , zookeeper yok. Kraft mod var. bu yuzden sadece kafkayi , BINARY olarak indirmemiz yeterli olacaktir. source koduna ihtiyacimiz yok. 

ne yapacagiz, genel anlatim.
1- kafkayi binary olarak indirin. 
2- cluster id uretmemiz gerekiyor
3- urettigimiz id ile , controller ve brokeri formatlamamiz gerekiyor. 1 kere yapilacak. 
4- calistirin ve baglanin. zor degil


cluster id uretmek: (kafkanin dosyasina girdikten sonra, terminale su kodu yapistirin. macOs)
bin/kafka-storage.sh random-uuid
# örnek çıktı: AbCDefGhIjKlMnOpQrStUv

controller formatlamak (standalone argumentini koymak zorunluymus)
bin/kafka-storage.sh format \
  -t AbCDefGhIjKlMnOpQrStUv \
  -c config/controller.properties \
  --standalone

broker icin de aynisi , standalone yok.
bin/kafka-storage.sh format \
  -t AbCDefGhIjKlMnOpQrStUv \
  -c config/broker.properties


bu adimdan sonra sadece calistirmak kaldi. once controller sonra broker
bin/kafka-server-start.sh config/controller.properties
bin/kafka-server-start.sh config/broker.properties

sonra da java uygulamasini baslat , olacaktir. 
eger bu projeyi deniyorsan , 127.0.0.1:8080/rest/api/producerMsg?message=araba istegine chromeden gitmeye calisirsan , ekranda OK gorursun. java konsolunda da mesaji gorursun. 


