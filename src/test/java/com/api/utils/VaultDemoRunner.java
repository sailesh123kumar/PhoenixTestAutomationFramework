package com.api.utils;

import java.util.Map;
import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.VaultException;
import com.bettercloud.vault.response.LogicalResponse;

public class VaultDemoRunner {
	
	public static void main(String[] args) throws VaultException {
		
		VaultConfig vaultConfig = new VaultConfig();
		
		vaultConfig.address("http://65.1.112.202:8200")
					.token("root")	
					.build();
		
		Vault vault = new Vault(vaultConfig);
		
		LogicalResponse logicalResponse = vault.logical().read("secret/phoenix/qa/database");
		
		Map<String, String> data = logicalResponse.getData();
		
		String url = data.get("DB_URL");
		String user = data.get("DB_USER_NAME");
		String password = data.get("DB_PASSWORD");
		
		System.out.println(url);
		System.out.println(user);
		System.out.println(password);
		
		
	}

}
