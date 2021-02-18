package com.springtest.springtest.service;

import com.springtest.springtest.entity.Album;
import com.springtest.springtest.exception.ValidationException;
import com.springtest.springtest.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public Album createAlbum (Album album) throws ValidationException{
        if(album.getTitle() == null || album.getTitle() == "") {
            throw new ValidationException("Cannot creat Album without title", HttpStatus.BAD_REQUEST);
        }
        if(album.getCount() < 1) {
            throw new ValidationException("Cannot creat Album less then 0 count", HttpStatus.BAD_REQUEST);
        }
        return albumRepository.save(album);
    }

    public List<Album> listAlbums() {
        List<Album> albumsList = albumRepository.findAll();
        return albumsList;
    }

    public Album getAlbumById(String id) throws ValidationException {
        Optional<Album> optionalAlbum = albumRepository.findById(id);
        if(optionalAlbum.isEmpty()) {
            throw new ValidationException("Invalid id", HttpStatus.BAD_REQUEST);
        }

        Album album = optionalAlbum.get();
        return album;
    }

    public Album albumUpdate(String id, Album album) throws ValidationException {
        Optional<Album> optionalAlbum = albumRepository.findById(id);
        if(optionalAlbum.isEmpty()){
            throw new ValidationException("Cannot update Album", HttpStatus.BAD_REQUEST);
        }

        Album album1 = optionalAlbum.get();
        album1.setTitle(album1.getTitle());
        album1.setCount(album1.getCount());
        Album updatedAlbum = albumRepository.save(album1);
        return updatedAlbum;
    }
}
