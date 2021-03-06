package com.mangofactory.bakehouse.config;

import java.util.List;

import com.mangofactory.bakehouse.core.ResourceCache;
import com.mangofactory.bakehouse.core.ResourceProcessor;
import com.mangofactory.bakehouse.core.io.FileManager;

public class BakehouseConfigBuilder {

	private final ResourceCache resourceCache;
	private final FileManager fileManager;

	private boolean serveResourcesFromCdn;
	
	public BakehouseConfigBuilder(ResourceCache resourceCache,
			FileManager fileManager) {
				this.resourceCache = resourceCache;
				this.fileManager = fileManager;
	}

	public BakehouseConfigBuilder newBuilder()
	{
		return new BakehouseConfigBuilder(resourceCache, fileManager);
	}
	public ProcessorConfigBuilder process(String configurationName) {
		return new ProcessorConfigBuilder(configurationName,this);
	}

	void setProcessors(String configurationName, List<ResourceProcessor> processors)
	{
		resourceCache.setConfiguration(configurationName, processors);
	}
	void addProcessor(String configurationName,
			ResourceProcessor processor) {
		resourceCache.addConfiguration(configurationName, processor);
	}

	public BakehouseConfigBuilder serveResourcesFromCdn() {
		// TODO!
		// See issue #1
		serveResourcesFromCdn = true;
		return this;
	}
	
	public BakehouseConfig build()
	{
		return new BakehouseConfig(fileManager,resourceCache,serveResourcesFromCdn);
	}

	
}
