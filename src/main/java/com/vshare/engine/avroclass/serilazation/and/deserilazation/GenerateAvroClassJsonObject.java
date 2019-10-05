package com.vshare.engine.avroclass.serilazation.and.deserilazation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.log4j.Logger;

import com.vshare.engine.avro.model.AvroDummData;
import com.vshare.engine.common.constant.KafkaConstants;
import com.vshare.engine.kafkaProducers.TestProducer;



public class GenerateAvroClassJsonObject {

	private static final Logger logger = Logger.getLogger(GenerateAvroClassJsonObject.class);
	

	final AvroDummData  avroDummyData1= new AvroDummData((long) 1, "admin", "shivankurtripathi08@gmail.com", "9899760637", "Shivankur", "Tripathi", "Vijay", "Male", "06/06/1991", "29/9/2019");

	File avroOutput = new File("AvroDummData-test.avro");

public void serilizedData() {
	
	try {
	  DatumWriter<AvroDummData> AvroDummDataDatumWriter = new SpecificDatumWriter<AvroDummData>(AvroDummData.class);
	  DataFileWriter<AvroDummData> dataFileWriter = new DataFileWriter<AvroDummData>(AvroDummDataDatumWriter);
	  dataFileWriter.create(avroDummyData1.getSchema(), avroOutput);
	  dataFileWriter.append(avroDummyData1);
	 // dataFileWriter.append(p2);
	  dataFileWriter.close();
	} catch (IOException e) {System.out.println("Error writing Avro");}	
}


public String deserilizedData() {
	try {
		  DatumReader<AvroDummData> bdPersonDatumReader = new SpecificDatumReader(AvroDummData.class);
		  DataFileReader<AvroDummData> dataFileReader = new DataFileReader<AvroDummData>(avroOutput, bdPersonDatumReader);
		  AvroDummData avroDummData = null;
		  while(dataFileReader.hasNext()){
			  avroDummData = dataFileReader.next(avroDummData);
		    return avroDummData.toString();
		  }
		} catch(IOException e) {System.out.println("Error reading Avro");}
return null;

}



public void avroProducer(String avroDummData) {
	 
			Producer<Long, String> producer = TestProducer.createProducer();

			 ProducerRecord<Long, String> record=null;
		


			 try {
				 
				// BufferedReader bufferReader = new BufferedReader(new FileReader(fileName));
				 String fileLineContent;
				 ProducerRecord<Long, String> recordduplicaterecord ;
				 recordduplicaterecord = new ProducerRecord<Long, String>(KafkaConstants.TOPIC_NAME,
						 avroDummData);
		         
					 RecordMetadata metadata = producer.send(recordduplicaterecord).get(); 
				 
				 /* while ((fileLineContent = bufferReader.readLine()) != null) {
					 recordduplicaterecord = new ProducerRecord<Long, String>(KafkaConstants.TOPIC_NAME,
		   		          fileLineContent);
		         
					 RecordMetadata metadata = producer.send(recordduplicaterecord).get(); 
		         }
*/				// RecordMetadata metadata = producer.send(recordduplicaterecord).get();    
			 } catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			

}



}