package com.study.eda.shared.support.encryption;

import java.util.Base64;

public enum EncryptionHelper {
	INSTANCE;

	public byte[] encodeBase64(final byte[] bytes) {
		return Base64.getUrlEncoder().encode(bytes);
	}

	public byte[] decodeBase64(final byte[] bytes) {
		return Base64.getUrlDecoder().decode(bytes);
	}
}
