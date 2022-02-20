package latmobile.server.subasta;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import latmobile.server.subasta.image.FilesStorageService;

@SpringBootApplication
public class ApiRestApplication implements CommandLineRunner {
	
	@Resource
	FilesStorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(ApiRestApplication.class, args);
	}
	
	@Override
	public void run(String... arg) throws Exception {
		//storageService.deleteAll();
	    storageService.init();
	    
	}

}
