package com.verizon.product;

import java.util.stream.Collectors;


import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.verizon.webkit.datacloud.DataCloud;
import com.verizon.webkit.datacloud.DataServiceRegistry;
import com.verizon.webkit.datacloud.DataSourceCriteria;
import com.verizon.webkit.datacloud.DataSourceResultSet;
import com.vzw.fuze.common.util.CommonUtils;

@Component
public class DataCloudStartup implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			
			CommonUtils.createFuzeOracleDataSource();
			DataServiceRegistry.intialize();
			DataSourceResultSet dbrs=DataCloud.perform("demo.datacloud",new DataSourceCriteria());
			System.out.println(dbrs);
            System.out.println(dbrs.getRows().stream().map(rs -> rs.get("PRODUCT_NAME").toString()).collect(Collectors.toList()));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
