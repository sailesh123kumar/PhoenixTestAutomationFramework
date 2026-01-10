package com.api.utils;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.VaultException;
import com.bettercloud.vault.response.LogicalResponse;

public class VaultDBConfig {

	private static final Logger LOGGER = LogManager.getLogger(VaultDBConfig.class);
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
			LOGGER.error("Something went wrong with the vault config",e);
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
			LOGGER.error("Something went wrong with the reading on vault response",e);
			e.printStackTrace();
			return null;
		}

		Map<String, String> dataMap = logicalResponse.getData();
		secret = dataMap.get(key);
		LOGGER.info("Secret found in the vault");
		return secret;

	}

}
