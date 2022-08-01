package com.glauber.nfeprocessservice.domain.service;

import java.io.File;
import java.util.List;

public interface NotaFiscalStorageService {

	List<File> loadFiles();
	File findFileBy(String fileName);
	void moveToOutputDirectory(File xmlFile);
	void moveToErrorDirectory(File xml);
}
