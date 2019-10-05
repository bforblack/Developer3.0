package com.vshare.engine.runner;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.log4j.Logger;

import com.vshare.engine.common.constant.KafkaConstants;
import com.vshare.engine.kafkaProducers.TestProducer;
import com.vshare.engine.kakfkaConsumers.TestConsumer;

public class KafkaEngineRunner {
	 
	
	private static final Logger logger = Logger.getLogger(KafkaEngineRunner.class);
	
	
	public static void runConsumer() {
	        Consumer<Long, String> consumer = TestConsumer.createConsumer();
	        int noMessageFound = 0;
	        while (true) {
	          ConsumerRecords<Long, String> consumerRecords = consumer.poll(1000);
	          // 1000 is the time in milliseconds consumer will wait if no record is found at broker.
	          if (consumerRecords.count() == 0) {
	              noMessageFound++;
	              if (noMessageFound > KafkaConstants.MAX_NO_MESSAGE_FOUND_COUNT)
	                // If no message found count is reached to threshold exit loop.  
	                break;
	              else
	                  continue;
	          }
	          //print each record. 
	          consumerRecords.forEach(record -> {
	            //  System.out.println("Record Key " + record.key());
	              System.out.println( record.value());
	              //System.out.println("Record partition " + record.partition());
	              //System.out.println("Record offset " + record.offset());
	           });
	          // commits the offset of record to broker. 
	           consumer.commitAsync();
	        }
	    consumer.close();
	    }
	  public  static void runProducer() {
	Producer<Long, String> producer = TestProducer.createProducer();

	 ProducerRecord<Long, String> record=null;
	
	try {	
		//String  fileName ="C:\\Users\\Sonal\\Downloads\\Young.Sheldon.S01E14.HDTV.x264-SVA[eztv].mkv";  
		String  fileName ="C:\\Users\\Sonal\\Desktop\\flinkData.csv";  
		
		// String data = ""; 
	  //  data = new String(Files.readAllBytes(Paths.get(fileName))); 
/*File file = new File(fileName);
byte [] fileBytes = Files.readAllBytes(file.toPath());
char singleChar;
for(byte b : fileBytes) {
  singleChar = (char) b;
  //System.out.print(singleChar);
*/

/*	Stream inputStream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8);

	inputStream.forEach(stringData->{
		ProducerRecord<Long, String> recordduplicaterecord = new ProducerRecord<Long, String>(KafkaConstants.TOPIC_NAME,
		          "This is record " +stringData);

	});*/
	


	 try {
		 
		 BufferedReader bufferReader = new BufferedReader(new FileReader(fileName));
		 String fileLineContent;
		 ProducerRecord<Long, String> recordduplicaterecord ;
		 while ((fileLineContent = bufferReader.readLine()) != null) {
			 recordduplicaterecord = new ProducerRecord<Long, String>(KafkaConstants.TOPIC_NAME,
   		          fileLineContent);
         
			 RecordMetadata metadata = producer.send(recordduplicaterecord).get(); 
         }
		// RecordMetadata metadata = producer.send(recordduplicaterecord).get();    
	 } catch (InterruptedException | ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	



/*ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(KafkaConstants.TOPIC_NAME,
	            "This is record " + data);*/
	
	/*  for (int index = 0; index < KafkaConstants.MESSAGE_COUNT; index++) {
	            ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(KafkaConstants.TOPIC_NAME,
	            "This is record " + index);*/
	            try {
	            RecordMetadata metadata = producer.send(record).get();
	                    /*    System.out.println("Record sent with key " + index + " to partition " + metadata.partition()
	                        + " with offset " + metadata.offset());*/
	                 } 
	            catch (ExecutionException e) {
	                     System.out.println("Error in sending record");
	                     System.out.println(e);
	                  } 
	             catch (InterruptedException e) {
	                      System.out.println("Error in sending record");
	                      System.out.println(e);
	                  }
	         } catch (Exception e) {
				System.out.print("This is your root cause"+e);
			}
	    }
	}

