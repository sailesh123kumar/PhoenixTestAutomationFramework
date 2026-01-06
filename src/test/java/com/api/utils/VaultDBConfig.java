package com.api.utils;

import java.util.Map;

import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.VaultException;
import com.bettercloud.vault.response.LogicalResponse;

public class VaultDBConfig {

	private static VaultConfig vaultConfig;
	private static Vault vault;

	public VaultDBConfig() {
	}

	static {
		vaultConfig = new VaultConfig();
		try {
			
			//1.Instead of harcoding the url and token - setup the variables in Environment Variable in settings
			//2.Restart the eclipse
			//3.call the method System.getenv("VAULT_SERVER")
			//4.call the method System.getenv("VAULT_TOKEN")
			
			vaultConfig.address("http://65.1.112.202:8200").token("root").build();
		} catch (VaultException e) {
			e.printStackTrace();
		}
		vault = new Vault(vaultConfig);
	}

	public static String getSecret(String key) {
		LogicalResponse logicalResponse = null;
		String secret = null;

		try {
			logicalResponse = vault.logical().read("secret/phoenix/qa/database");
		} catch (VaultException e) {
			e.printStackTrace();
		}

		Map<String, String> dataMap = logicalResponse.getData();
		secret = dataMap.get(key);
		return secret;

	}

}
