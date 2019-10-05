package com.vshare.engine.controllers;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vshare.engine.avroclass.serilazation.and.deserilazation.GenerateAvroClassJsonObject;
import com.vshare.engine.runner.KafkaEngineRunner;

@RestController
@RequestMapping("/")
public class VshareController {
private static final Logger logger = Logger.getLogger(VshareController.class);



@RequestMapping(value="startEngine",method=RequestMethod.GET)
public void startEngine() {
	KafkaEngineRunner.runProducer();
	KafkaEngineRunner.runConsumer();
	
}
@RequestMapping(value="check",method=RequestMethod.GET)
public String check() {
	GenerateAvroClassJsonObject generateAvroClassJsonObject= new GenerateAvroClassJsonObject();
	
	generateAvroClassJsonObject.serilizedData();
	generateAvroClassJsonObject.avroProducer(generateAvroClassJsonObject.deserilizedData());
	
	return"Check complete";
	
}

@RequestMapping(value="flink",method=RequestMethod.POST)
public void flink() {
	
	
}
@RequestMapping(value="jsonCreate",method=RequestMethod.POST)
public void jsonCreate(@RequestBody final String jsonBody) throws IOException {
	

}
/*private void set(String jsonFile , String modelName) {
	

        //CapFirst for java classes
        modelName = modelName.substring(0, 1).toUpperCase() + modelName.substring(1);
        System.out.println("Model name :"+modelName);
     
          JCodeModel codeModel = new JCodeModel();
        GenerationConfig config = new DefaultGenerationConfig() {

            @Override
            public boolean isIncludeConstructors() {
                return true;
            }

            @Override
            public boolean isUseDoubleNumbers() {
                return true;
            }

            @Override
            public boolean isUsePrimitives() {
                return true;
            }

            @Override
            public boolean isIncludeToString() {
                return false;
            }

            @Override
            public boolean isIncludeHashcodeAndEquals() {
                return false;
            }

            @Override
            public boolean isIncludeAdditionalProperties() {
                return false;

            }

            @Override
            public Class<? extends RuleFactory> getCustomRuleFactory() {
                return APXCustomRuleFactory.class;
            }

        };

        

        SchemaMapper mapper = new SchemaMapper(new APXCustomRuleFactory(config, new ORMLiteAnotator(), new SchemaStore()), new SchemaGenerator());

        JType m = mapper.generate(codeModel, modelName, PACKAGE_NAME + ".models", jsonFile.toURI().toURL());

         
        File f = new File(PROJECT_SRC);
        codeModel.build(f);
        System.out.print("Model created at :");
        System.out.println(m.fullName().replace('.', File.separatorChar) + ".java");

    }
*/
}
