package com.vshare.engine.runner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternSelectFunction;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.SimpleCondition;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.log4j.Logger;

import com.vshare.engine.pojos.PaymentDetailsPojo;
import com.vshare.engine.pojos.PaymentExtended;

public class FlinkEngineRunner {

private static final Logger logger = Logger.getLogger(FlinkEngineRunner.class);	
	
public static BigDecimal minimumAmount = new BigDecimal(1000);
public static BigDecimal maxAmount = new BigDecimal(1500);
	
public void createPatternForFlink(Stream<PaymentDetailsPojo> paymentDetailsPojo) {

	DataStream<PaymentDetailsPojo>dataStream=(DataStream<PaymentDetailsPojo>) paymentDetailsPojo;
	
	
	
	
	
	
	Pattern<PaymentDetailsPojo, ?>pattern=Pattern.<PaymentDetailsPojo>begin("First").where(new SimpleCondition<PaymentDetailsPojo>() {
		@Override
		public boolean filter(PaymentDetailsPojo paymentDetailsPojo) {
            return paymentDetailsPojo.getCurrentbalance().compareTo(minimumAmount) == 0;
        }
		
	}).next("Second").where(new SimpleCondition<PaymentDetailsPojo>() {
		@Override
		public boolean filter(PaymentDetailsPojo paymentDetailsPojo) {
            return paymentDetailsPojo.getCurrentbalance().compareTo(maxAmount) == 0;
        }
		
	});
	
	
	
	PatternStream<PaymentDetailsPojo> paymentDetailsPojoPatternStream = CEP.pattern(dataStream.keyBy("paymentDetailsId"), pattern);
	
	
	
	//Pattern.<PaymentDetailsPojo>begin("First Event").where(paymentDetailsPojo.getCurrentbalance()>=minimumAmount);
	//PatternStream<PaymentDetailsPojo> tempPatternStream = CEP.pattern(paymentExtendedStream, pattern);
		
			
			
			/* inputEventStream.keyBy("paymentDetailsId"),
		    warningPattern);*/

	
	
	DataStream<PaymentExtended> alerts = paymentDetailsPojoPatternStream.select(new PatternSelectFunction<PaymentDetailsPojo, PaymentExtended>() {
		@Override
		public PaymentExtended select(Map<String, List<PaymentDetailsPojo>> pattern) throws Exception {
			return createAlert(pattern);
		}

		private PaymentExtended createAlert(Map<String, List<PaymentDetailsPojo>> pattern) {
			// TODO Auto-generated method stub
			return new PaymentExtended();
		}

	
	});



}
}
