package latmobile.server.subasta.controller;

import org.springframework.data.jpa.repository.JpaRepository;

import latmobile.server.subasta.entity.Image;

public interface ImageService extends JpaRepository<Image, Integer>{

}
