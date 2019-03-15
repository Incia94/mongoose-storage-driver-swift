package com.emc.mongoose.storage.driver.coop.netty.http.swift;

import com.emc.mongoose.base.data.DataInput;
import com.emc.mongoose.base.env.ExtensionBase;
import com.emc.mongoose.base.config.IllegalConfigurationException;
import com.emc.mongoose.base.item.Item;
import com.emc.mongoose.base.item.op.Operation;
import com.emc.mongoose.base.storage.driver.StorageDriverFactory;
import static com.emc.mongoose.base.Constants.APP_NAME;

import com.github.akurilov.confuse.Config;
import com.github.akurilov.confuse.SchemaProvider;
import com.github.akurilov.confuse.io.yaml.YamlSchemaProviderBase;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SwiftStorageDriverExtension<I extends Item, O extends Operation<I>, T extends SwiftStorageDriver<I, O>>
				extends ExtensionBase
				implements StorageDriverFactory<I, O, T> {

	private static final String NAME = "swift";
	private static final String DEFAULTS_FILE_NAME = "defaults-storage-swift.yaml";
	private static final List<String> RES_INSTALL_FILES = Collections.unmodifiableList(
					Arrays.asList(
									"config/" + DEFAULTS_FILE_NAME));

	@Override
	public String id() {
		return NAME;
	}

	@Override
	public T create(
					final String stepId, final DataInput dataInput, final Config storageConfig, final boolean verifyFlag,
					final int batchSize) throws IllegalConfigurationException, InterruptedException {
		return (T) new SwiftStorageDriver<>(stepId, dataInput, storageConfig, verifyFlag, batchSize);
	}

	@Override
	public final SchemaProvider schemaProvider() {
		return new YamlSchemaProviderBase() {
			@Override
			protected final InputStream schemaInputStream() {
				return getClass().getResourceAsStream("/config-schema-storage-swift.yaml");
			}

			@Override
			public final String id() {
				return APP_NAME;
			}
		};
	}

	@Override
	protected final String defaultsFileName() {
		return DEFAULTS_FILE_NAME;
	}

	@Override
	protected final List<String> resourceFilesToInstall() {
		return RES_INSTALL_FILES;
	}
}
