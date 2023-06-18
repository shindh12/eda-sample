package com.study.eda.shared.support.jackson.encryption;

import java.nio.charset.Charset;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EncryptionModule extends Module {
	private final Charset charset;

	public EncryptionModule() {
		this(Charset.defaultCharset());
	}

	@Override
	public String getModuleName() {
		return EncryptionModule.class.getSimpleName();
	}

	@Override
	public Version version() {
		return Version.unknownVersion();
	}

	@Override
	public void setupModule(SetupContext context) {
		// context.addBeanSerializerModifier(new EncryptionBeanSerializerModifier(charset));
		// context.addBeanDeserializerModifier(new EncryptionBeanDeserializerModifier(charset));
		new SimpleModule()
			.setSerializerModifier(new EncryptionBeanSerializerModifier(charset))
			.setDeserializerModifier(new EncryptionBeanDeserializerModifier(charset))
			.setupModule(context);
	}
}
